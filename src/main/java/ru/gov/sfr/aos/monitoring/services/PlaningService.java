/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.ConsumptionDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.ModelCartridgeByModelPrinters;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.models.PlaningBuyDto;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ListenerOperationRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class PlaningService {

    @Autowired
    private ListenerOperationRepo listenerOperationRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;

    public Map<String, List<ConsumptionDTO>> showUtilled(PlaningBuyDto dto) {
        long amountDaysFromDto = ChronoUnit.DAYS.between(dto.dateBegin, dto.dateEnd);
        List<ConsumptionDTO> result = new ArrayList<>();
        
        
        List<ListenerOperation> listenersByDateOperationAfterAndDateOperationBefore = listenerOperationRepo.findByDateOperationAfterAndDateOperationBefore(dto.dateBegin.atStartOfDay(), dto.dateEnd.atTime(23, 59, 59));
        List<ListenerOperation> utilled = listenersByDateOperationAfterAndDateOperationBefore.stream()
                .filter(e -> e.getOperationType().equals(OperationType.UTIL))
                .collect(Collectors.toList());
        
        for(ListenerOperation listener : utilled) {
            LocationDTO locDto = new LocationDTO();
            locDto.setId(listener.getLocation().getId());
            locDto.setName(listener.getLocation().getName());
            
            CartridgeModelDTO cartrDto = new CartridgeModelDTO();
            cartrDto.setId(listener.getModel().getId());
            cartrDto.setModel(listener.getModel().getModel());
            cartrDto.setType(listener.getModel().getType().getName());
            
            ConsumptionDTO consDto = new ConsumptionDTO();
            consDto.setModel(cartrDto);
            consDto.setLocation(locDto);
            consDto.setPeriod(amountDaysFromDto);
            result.add(consDto);
        }
        
        Map<String, List<ConsumptionDTO>> collect = result.stream().collect(Collectors.groupingBy(e -> e.getModel().getModel(), Collectors.toList()));



        return collect;
    }
    
    public Map<String, List<ConsumptionDTO>> showPurchased(PlaningBuyDto dto) {
        long amountDaysFromDto = ChronoUnit.DAYS.between(dto.dateBegin, dto.dateEnd);
        Map<Long, List<Integer>> amountCartridgesByModel = new HashMap<>();
        List<ListenerOperation> listenersByDateOperationAfterAndDateOperationBefore = listenerOperationRepo.findByDateOperationAfterAndDateOperationBefore(dto.dateBegin.atStartOfDay(), dto.dateEnd.atTime(23, 59, 59));
        List<ListenerOperation> purchased = listenersByDateOperationAfterAndDateOperationBefore.stream()
                .filter(e -> e.getOperationType().equals(OperationType.BUY))
                .collect(Collectors.toList());
        
        List<ConsumptionDTO> result = new ArrayList<>();
        for(ListenerOperation listener : purchased) {
            if(amountCartridgesByModel.get(listener.getModel().getId()) == null || amountCartridgesByModel.get(listener.getModel().getId()).isEmpty()) {
               amountCartridgesByModel.put(listener.getModel().getId(), new ArrayList<>(Arrays.asList(listener.getAmount())));
            } else {
                List<Integer> getList = amountCartridgesByModel.get(listener.getModel().getId());
                getList.add(listener.getAmount());
            }
        }
        
        for(Map.Entry<Long, List<Integer>> entry : amountCartridgesByModel.entrySet()) {
            LocationDTO locDto = new LocationDTO();
            locDto.setId(purchased.get(0).getLocation().getId());
            locDto.setName(purchased.get(0).getLocation().getName());
            
            Optional<CartridgeModel> findModelById = cartridgeModelRepo.findById(entry.getKey());
            CartridgeModelDTO cartrDto = new CartridgeModelDTO();
            cartrDto.setId(entry.getKey());
            cartrDto.setModel(findModelById.get().getModel());
            cartrDto.setType(findModelById.get().getType().getName());
            
            ConsumptionDTO consDto = new ConsumptionDTO();
            consDto.setModel(cartrDto);
            consDto.setLocation(locDto);
            consDto.setPeriod(amountDaysFromDto);
            Integer getAmountCart = entry.getValue().stream().collect(Collectors.summingInt(e -> e.intValue()));
            consDto.setIncoming(getAmountCart);
            result.add(consDto);     
        }     
        Map<String, List<ConsumptionDTO>> collect = result.stream().collect(Collectors.groupingBy(e -> e.getModel().getModel(), Collectors.toList()));
        return collect;
    }
    
    public Map<String, ConsumptionDTO> getAmountAllCartridgesByModelAndPeriod(PlaningBuyDto dto) {
         List<ListenerOperation> listenersByDateOperationAfterAndDateOperationBefore = listenerOperationRepo.findByDateOperationAfterAndDateOperationBefore(dto.dateBegin.atStartOfDay(), dto.dateEnd.atTime(23, 59, 59));
         Comparator<ListenerOperation> ListenerOperarionDateComparator = Comparator.comparing(ListenerOperation::getDateOperation);
         Map<String, List<ListenerOperation>> collectListenersByModel = listenersByDateOperationAfterAndDateOperationBefore.stream().collect(Collectors.groupingBy(e -> e.getModel().getModel(), Collectors.toList()));
         Map<String, ConsumptionDTO> out = new HashMap<>();
         for(Map.Entry<String, List<ListenerOperation>> entry : collectListenersByModel.entrySet()) {
             ListenerOperation getMax = entry.getValue().stream().max(ListenerOperarionDateComparator).get();
             LocationDTO locDto = new LocationDTO(getMax.getLocation().getId(), getMax.getLocation().getName());
             CartridgeModelDTO modelDto = new CartridgeModelDTO();
             modelDto.setId(getMax.getModel().getId());
             modelDto.setModel(getMax.getModel().getModel());
             modelDto.setResource(getMax.getModel().getDefaultNumberPrintPage().toString());
             modelDto.setType(getMax.getModel().getType().getName());
             ConsumptionDTO outDto = new ConsumptionDTO();
             outDto.setLocation(locDto);
             outDto.setBalance(getMax.getAmountAllCartridgesByModel());
             out.put(entry.getKey(), outDto);
             
         }
         
         return out;
         
    }
    
    public Map<String, Set<CartridgeModelDTO>> getAmountCartridgeModel() {
        
        List<CartridgeModel> findAll = cartridgeModelRepo.findAll();
        
        Map<String, Set<CartridgeModelDTO>> ooout = new HashMap<>();
        List<Model> modelsPrinter = modelPrinterRepo.findAll();
        
        for(Model m : modelsPrinter) {
            Set<CartridgeModelDTO> modelsCart = new HashSet<>();
            Set<CartridgeModel> modelCartridges = m.getModelCartridges();
            for(CartridgeModel carmod : modelCartridges) {
                CartridgeModelDTO dto = new CartridgeModelDTO();
                StringBuilder sb = new StringBuilder();
                dto.setId(carmod.getId());
                dto.setModel(carmod.getModel());
                dto.setResource(carmod.getDefaultNumberPrintPage().toString());
                dto.setType(carmod.getType().getName());
                Iterator<Model> iterator = carmod.getModelsPrinters().iterator();
                
                List<Long> collectPrintersID = carmod.getModelsPrinters().stream().flatMap(e -> e.getPrinters().stream())
                        .map(el -> el.getId())
                        .collect(Collectors.toList());
                dto.setIdModel(collectPrintersID);
                while (iterator.hasNext()) {
                    Model element = iterator.next();
                    sb.append(element.getManufacturer().getName());
                    sb.append(" ");
                    sb.append(element.getName());
                    if(iterator.hasNext()) {
                    sb.append("/");
                    }
                modelsCart.add(dto);
                
                    
                }
                if(ooout.get(sb.toString()) == null || ooout.get(sb.toString()).isEmpty()) {
                    ooout.put(sb.toString(), modelsCart);
                } else {
                    ooout.get(sb.toString()).add(dto);
                }
                }
            }
        
        
        
        
        return ooout;
    }

    
    public Map<String, List<ModelCartridgeByModelPrinters>> getPrintersByCartridgesModel() {
            List<Cartridge> cartridges = cartridgeRepo.findAll();
        List<Location> locations = locationRepo.findAll();
        Set<Cartridge> collectCartridges = cartridges.stream() // Все актуальные картриджи
                .filter(e -> !e.isUseInPrinter())
                .filter(e -> !e.isUtil())
                .collect(Collectors.toSet());
                
            List<ModelCartridgeByModelPrinters> out = new ArrayList<>();
            ModelCartridgeByModelPrinters dto = null;
            List<CartridgeModel> findAllModelsCartrtridge = cartridgeModelRepo.findAll(); // Все модели картрмджей
            List<ModelCartridgeByModelPrinters> list = new ArrayList<>();
            for (CartridgeModel cartridgeModel : findAllModelsCartrtridge) {
                List<Long> cartridgesID = new ArrayList<>();
                List<Long> printersID = new ArrayList<>();
                List<Model> models = cartridgeModel.getModelsPrinters();
                dto = new ModelCartridgeByModelPrinters();
                List<ModelDTO> modelPrinterDTOes = new ArrayList<>();
                for (Model modelPrinter : models) {
                    ModelDTO modelDTO = new ModelDTO(modelPrinter.getId(), modelPrinter.getName(), modelPrinter.getManufacturer().getName());
                    modelPrinterDTOes.add(modelDTO);
                }
                for(Location storage : locations) {
                    
                for (ModelDTO modelDTO : modelPrinterDTOes) {
                    for (Printer printer : storage.getPrinters()) {
                        if ((modelDTO.getIdModel() == printer.getModel().getId()) && !printer.getPrinterStatus().equals(PrinterStatus.DELETE)
                                && !printer.getPrinterStatus().equals(PrinterStatus.MONITORING) && !printer.getPrinterStatus().equals(PrinterStatus.UTILIZATION)) {
                            printersID.add(printer.getId());
                        }
                    }
                }
            }

                for (Cartridge cartridge : cartridges) {

                    if ((cartridgeModel.getId() == cartridge.getModel().getId()) && (!cartridge.isUtil() && !cartridge.isUseInPrinter())) {

                        cartridgesID.add(cartridge.getId());

                    }
                }
                dto.setId(cartridgeModel.getId());
                dto.setModel(cartridgeModel.getModel());
                dto.setModelsPrinter(modelPrinterDTOes);
                dto.setCartridgesId(cartridgesID);
                dto.setPrintersID(printersID);
                list.add(dto);
            }
            
            Map<String, List<ModelCartridgeByModelPrinters>> output = list.stream().collect(Collectors.groupingBy(e -> e.getModel(), Collectors.toList()));
            return output;
    }
    
    
    public static List<ListenerOperation> getListenersBetweenDay(List<ListenerOperation> input, LocalDate start, LocalDate end) {

        List<ListenerOperation> result = new ArrayList<>();

        for (ListenerOperation listener : input) {

            if (!listener.getDateOperation().isBefore(start.atStartOfDay()) && !listener.getDateOperation().isAfter(end.atStartOfDay().plusDays(1))) {
                result.add(listener);
            }
        }

        return result;
    }

}
