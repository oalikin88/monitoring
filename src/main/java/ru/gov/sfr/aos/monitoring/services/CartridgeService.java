/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.CartridgeChoiceDto;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeInstallDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeLocationForCartridges;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.ModelCartridgeByModelPrinters;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class CartridgeService {

    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private ListenerOperationService listenerOperationService;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private CartridgeMapper cartridgeMapper;

    public void installCartridge(CartridgeInstallDTO dto) {
        ListenerOperation listener = new ListenerOperation();
        Optional<Printer> findPrinterById = printerRepo.findById(dto.getIdPrinter());
        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getIdCartridge());
        Set<Cartridge> cartridges = findPrinterById.get().getCartridge();
        if (!cartridges.isEmpty()) {
            for (Cartridge cartr : cartridges) {
                if (cartr.isUseInPrinter()) {
                    cartr.setDateEndExploitation(LocalDateTime.now());
                    cartr.setUseInPrinter(false);
                    cartr.setUtil(true);
                    cartr.setCount(dto.getCount());
                }
            }
        }
        findCartridgeById.get().setUseInPrinter(true);
        findCartridgeById.get().setPrinter(findPrinterById.get());
        findPrinterById.get().setCartridge(cartridges);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findCartridgeById.get().getLocation());
        listener.setCurrentOperation("Установлен в принтер " + findPrinterById.get().getManufacturer().getName() + " " + findPrinterById.get().getModel().getName() + " серийный номер: " + findPrinterById.get().getSerialNumber());
        listener.setOperationType(OperationType.UTIL);
        Set<Cartridge> collectCartridgesOfLocation = findCartridgeById.get().getLocation().getCartridges().stream()
                .filter(e -> !e.isUtil())
                .filter(e -> !e.isUseInPrinter())
                .collect(Collectors.toSet());
        listener.setAmountDevicesOfLocation(collectCartridgesOfLocation.size());

        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(findCartridgeById.get().getLocation().getId(), findCartridgeById.get().getModel().getId());
        List<Cartridge> collect = findByLocationIdAndModelId.stream()
                .filter(e -> !e.isUtil())
                .filter(e -> !e.isUseInPrinter())
                .collect(Collectors.toList());

        listener.setAmountCurrentModelOfLocation(collect.size());
        listener.setModel(findCartridgeById.get().getModel());
        listener.setPrinterID(dto.getIdPrinter());
        listener.setEmployeeToDoWork(dto.getEmployeeToDoWork());
        listener.setEmployeeToSetDevice(dto.getEmployeeToSetDevice());
        listener.setEmployeeMOL(dto.getEmployeeMOL());
        listener.setCartridgeID(dto.getIdCartridge());
        findCartridgeById.get().setDateStartExploitation(LocalDateTime.now());
        List<Cartridge> findAllCartridgesByModelId = cartridgeRepo.findByModelId(findCartridgeById.get().getModel().getId());
        List<Cartridge> findAllCartridgesExceptUtil = findAllCartridgesByModelId.stream()
                .filter(e -> !e.isUtil())
                .filter(el -> !el.isUseInPrinter())
                .collect(Collectors.toList());
        listener.setAmount(1);
        listener.setAmountAllCartridgesByModel(findAllCartridgesExceptUtil.size());
        printerRepo.save(findPrinterById.get());
        cartridgeRepo.save(findCartridgeById.get());
        listenerOperationService.saveListenerOperation(listener);

    }

    public void changeCartridgesLocation(ChangeLocationForCartridges dto) {
        Optional<Location> findLocationById = locationRepo.findById(dto.getLocation());
        Location currentLocation = findLocationById.get();
        Optional<Location> beforeTransferLocation = Optional.empty();
        List<Cartridge> cartridgesChangeLocation = new ArrayList<>();
        Map<Long, Integer> mapForListener = new HashMap<>();
        for (int i = 0; i < dto.getIdCartridge().size(); i++) {
            Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getIdCartridge().get(i));
            if (findCartridgeById.isPresent()) {
                beforeTransferLocation = Optional.of(findCartridgeById.get().getLocation());
                findCartridgeById.get().setLocation(currentLocation);
                cartridgeRepo.save(findCartridgeById.get());
                cartridgesChangeLocation.add(findCartridgeById.get());
            }
        }
        Optional<Location> findLocById = locationRepo.findById(dto.getLocation());
        Set<Cartridge> cartridges = findLocById.get().getCartridges();
        Set<Cartridge> actualCartSet = new HashSet<>();
        for (Cartridge cart : cartridges) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualCartSet.add(cart);
            }
        }
        int actualAmountSizeLocation = actualCartSet.size();
        Set<Cartridge> cartridgesFromLocationBeforeTransfer = beforeTransferLocation.get().getCartridges();
        Set<Cartridge> actualcartridgesFromLocationBeforeTransfer = new HashSet<>();
        for (Cartridge cart : cartridgesFromLocationBeforeTransfer) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualcartridgesFromLocationBeforeTransfer.add(cart);
            }
        }
        int actualAmountSizeLocationBeforeTransfer = actualcartridgesFromLocationBeforeTransfer.size();
        Map<CartridgeModel, List<Cartridge>> collectByCartridgeModel = cartridgesChangeLocation.stream().collect(Collectors.groupingBy(Cartridge::getModel));
        for (Map.Entry<CartridgeModel, List<Cartridge>> entry : collectByCartridgeModel.entrySet()) {
            Set<Long> cartridgesId = new HashSet<>();
            for (Cartridge cart : entry.getValue()) {
                cartridgesId.add(cart.getId());
            }
            mapForListener.put(entry.getKey().getId(), cartridgesId.size());
            ListenerOperation listenerOperation = new ListenerOperation();
            List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(findLocById.get().getId(), entry.getKey().getId(), Pageable.ofSize(25));
            listenerOperation.setCurrentOperation("Перемещение из " + beforeTransferLocation.get().getName() + " в " + currentLocation.getName());
            listenerOperation.setLocation(currentLocation);
            listenerOperation.setOperationType(OperationType.TRANSFER);
            listenerOperation.setDateOperation(LocalDateTime.now());
            actualAmountSizeLocation = actualAmountSizeLocation + cartridgesId.size();
            listenerOperation.setAmountDevicesOfLocation(actualAmountSizeLocation);
            listenerOperation.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size());
            listenerOperation.setModel(entry.getKey());
            listenerOperation.setLocationBeforeTransfer(beforeTransferLocation.get());
            List<Cartridge> findByBeforeTransferLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(beforeTransferLocation.get().getId(), entry.getKey().getId(), Pageable.ofSize(25));
            listenerOperation.setAmountCurrentModelOfTransferedLocation(findByBeforeTransferLocationIdAndModelId.size());
            listenerOperation.setAmountDevicesOfTransferedLocation(actualAmountSizeLocationBeforeTransfer - cartridgesId.size());
            listenerOperationService.saveListenerOperation(listenerOperation);
        }
    }

    public List<CartridgeDTO> findModelCartridgeByType(String type) {
        CartridgeType currentType;
        if (type.trim().toLowerCase().equals(CartridgeType.ORIGINAL.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ORIGINAL;
        } else if (type.trim().toLowerCase().equals(CartridgeType.ANALOG.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ANALOG;
        } else {
            currentType = CartridgeType.START;
        }
        List<CartridgeDTO> dtoes = new ArrayList<>();
        List<CartridgeModel> list = cartridgeModelRepo.findByType(currentType);
        for (int i = 0; i < list.size(); i++) {
            CartridgeDTO dto = new CartridgeDTO();
            dto.setType(list.get(i).getType().getName());
            dto.setModel(list.get(i).getModel());
            dtoes.add(dto);
        }
        return dtoes;
    }

    public List<CartridgeDTO> showCartridgesByModelPrinter(Long idPrinter, String location) {
        List<Cartridge> cartridges = cartridgeRepo.findByLocationName(location);
        Optional<Location> findByNameIgnoreCase = locationRepo.findByNameIgnoreCase(location);
        LocationDTO locDto = new LocationDTO();
        locDto.setId(findByNameIgnoreCase.get().getId());
        locDto.setName(findByNameIgnoreCase.get().getName());
        List<CartridgeDTO> cartridgesByModelPrinter = new ArrayList<>();
        Optional<Printer> findPrinterById = printerRepo.findById(idPrinter);
        Long idModelPrinter = findPrinterById.get().getModel().getId();
        for (Cartridge cartridge : cartridges) {
            List<Model> modelsPrinters = cartridge.getModel().getModelsPrinters();
            for (Model modelPrinter : modelsPrinters) {
                if ((idModelPrinter == modelPrinter.getId()) && (!cartridge.isUtil() && !cartridge.isUseInPrinter())) {
                    CartridgeDTO dto = cartridgeMapper.getCartridgeDtoWhithoutListener(cartridge);
                    cartridgesByModelPrinter.add(dto);
                }
            }
        }
        return cartridgesByModelPrinter;
    }

    public void saveCartridgeModel(CartridgeModel cartridgeModel) throws ObjectAlreadyExists {
        String replaceAllBreakesFromInput = cartridgeModel.getModel().replaceAll(" ", "");
        String replaceAllDeficesAndBreakesFromInput = replaceAllBreakesFromInput.replaceAll("-", "");
        
        if (cartridgeModelRepo.existsByModelIgnoreCase(replaceAllDeficesAndBreakesFromInput)) {
            throw new ObjectAlreadyExists("Модель " + cartridgeModel.getModel() + " уже есть в базе данных");
        } else {
            
            cartridgeModelRepo.save(cartridgeModel);
        }
    }

    public Map<String, List<CartridgeDTO>> showCatridgesByLocation() {
        Map<String, List<CartridgeDTO>> map = new HashMap<>();
        List<Location> locations = locationRepo.findAll();
        for (int i = 0; i < locations.size(); i++) {
            List<CartridgeDTO> list = new ArrayList<>();
            List<Cartridge> cartridges = cartridgeRepo.findByLocationName(locations.get(i).getName());
            for (int j = 0; j < cartridges.size(); j++) {
                CartridgeDTO dto = new CartridgeDTO();
                if (!cartridges.get(j).getModel().getModel().isEmpty()) {
                    dto.setModel(cartridges.get(j).getModel().getModel());
                } else {
                    dto.setModel("По умолчанию");
                }
                if (!cartridges.get(j).getModel().getType().getName().isEmpty()) {
                    dto.setType(cartridges.get(j).getModel().getType().getName());
                } else {
                    dto.setType("По умолчанию");
                }
                if (!cartridges.get(j).getLocation().getName().isEmpty()) {
                    dto.setLocation(cartridges.get(j).getLocation().getName());
                } else {
                    dto.setLocation("Склад");
                }
                list.add(dto);
            }
            if (!list.isEmpty()) {
                map.put(locations.get(i).getName(), list);
            }
        }
        return map;
    }

    public Map<LocationDTO, List<ModelCartridgeByModelPrinters>> showCartridgesAndPrintersByModelAndLocation() {
        List<Location> locations = locationRepo.findAll();
        Map<LocationDTO, List<ModelCartridgeByModelPrinters>> out = new HashMap<>();
        ModelCartridgeByModelPrinters dto = null;
        for (Location storage : locations) {
            LocationDTO locationDTO = new LocationDTO(storage.getId(), storage.getName());
            Set<Cartridge> cartridges = storage.getCartridges();
            List<CartridgeModel> findAllModelsCartrtridge = cartridgeModelRepo.findAll();
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
                for (ModelDTO modelDTO : modelPrinterDTOes) {
                    for (Printer printer : storage.getPrinters()) {
                        if ((modelDTO.getIdModel() == printer.getModel().getId()) && !printer.getPrinterStatus().equals(PrinterStatus.DELETE) && !printer.getPrinterStatus().equals(PrinterStatus.MONITORING) && !printer.getPrinterStatus().equals(PrinterStatus.UTILIZATION)) {
                            printersID.add(printer.getId());
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
            out.put(locationDTO, list);
        }
        return out;
    }

    public void changeCartridgeLocation(ChangeDeviceLocationDTO dto) {
        String location1;
        String location2;
        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getId());
        location1 = findCartridgeById.get().getLocation().getName();
        Optional<Location> findLocationByName = locationRepo.findByNameIgnoreCase(dto.location.toLowerCase());
        findCartridgeById.get().setLocation(findLocationByName.get());
        location2 = findCartridgeById.get().getLocation().getName();
        ListenerOperation listener = new ListenerOperation();
        Set<Cartridge> cartridges = findCartridgeById.get().getLocation().getCartridges();
        Set<Cartridge> actualCartSet = new HashSet<>();
        for (Cartridge cart : cartridges) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualCartSet.add(cart);
            }
        }
        listener.setAmountDevicesOfLocation(actualCartSet.size() + 1);
        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(findCartridgeById.get().getLocation().getId(), findCartridgeById.get().getModel().getId(), Pageable.ofSize(25));
        listener.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size() + 1);
        listener.setOperationType(OperationType.TRANSFER);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findLocationByName.get());
        listener.setModel(findCartridgeById.get().getModel());
        listener.setCurrentOperation("Перемещение из " + location1 + " в " + location2);
        cartridgeRepo.save(findCartridgeById.get());
        listenerOperationService.saveListenerOperation(listener);
    }

    public void utilCartridge(Long id) {
        ListenerOperation listener = new ListenerOperation();
        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(id);
        findCartridgeById.get().setUtil(true);
        Set<Cartridge> cartridges = findCartridgeById.get().getLocation().getCartridges();
        Set<Cartridge> actualCartSet = new HashSet<>();
        for (Cartridge cart : cartridges) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualCartSet.add(cart);
            }
        }
        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(findCartridgeById.get().getLocation().getId(), findCartridgeById.get().getId(), Pageable.ofSize(25));
        listener.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size() - 1);
        listener.setModel(findCartridgeById.get().getModel());
        listener.setAmountDevicesOfLocation(actualCartSet.size() - 1);
        listener.setOperationType(OperationType.UTIL);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findCartridgeById.get().getLocation());
        listener.setCurrentOperation("Списан");
        cartridgeRepo.save(findCartridgeById.get());
    }

    public List<CartridgeModelDTO> showCartridgeModelByPrinterModel(Long id) {
        List<CartridgeModel> findByModelsPrintersLikeId = cartridgeModelRepo.findByModelsPrintersId(id);
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        for (CartridgeModel cartModel : findByModelsPrintersLikeId) {
            CartridgeModelDTO dto = new CartridgeModelDTO();
            dto.setId(cartModel.getId());
            dto.setModel(cartModel.getModel());
            dto.setResource(cartModel.getDefaultNumberPrintPage().toString());
            dto.setType(cartModel.getType().getName());
            dtoes.add(dto);
        }
        return dtoes;
    }

    public List<CartridgeModelDTO> showCartridgeModelByPrinterModelAndType(String model, String type) {
        CartridgeType cartridgeType = null;
        switch (type) {
            case "ORIGINAL":
                cartridgeType = cartridgeType.ORIGINAL;
                break;
            case "ANALOG":
                cartridgeType = cartridgeType.ANALOG;
                break;
            case "START":
                cartridgeType = CartridgeType.START;
                break;
        }
        Optional<Model> findModelPrinterByName = modelPrinterRepo.findByNameIgnoreCase(model.toLowerCase());
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        Set<CartridgeModel> modelCartridges = null;
        if (findModelPrinterByName.isPresent()) {
            modelCartridges = findModelPrinterByName.get().getModelCartridges();
        }
        List<Long> idModel = new ArrayList<>(Arrays.asList(0L));
        Set<String> printr = new HashSet<>(Arrays.asList("отсутствует"));
        for (Iterator<CartridgeModel> it = modelCartridges.iterator(); it.hasNext();) {
            CartridgeModel cartModel = it.next();
            if (cartModel.getType().equals(cartridgeType)) {
                CartridgeModelDTO dto = new CartridgeModelDTO();
                dto.setId(cartModel.getId());
                dto.setModel(cartModel.getModel());
                dto.setResource(cartModel.getDefaultNumberPrintPage().toString());
                dto.setType(cartModel.getType().getName());
                dto.setIdModel(idModel);
                dto.setPrinters(printr);
                dtoes.add(dto);
            }
        }
        return dtoes;
    }

    public List<CartridgeChoiceDto> showCartridgesForChoice(Long idPrinter, String location) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        List<Cartridge> cartridges = cartridgeRepo.findByLocationName(location);
        Optional<Location> findByNameIgnoreCase = locationRepo.findByNameIgnoreCase(location);
        LocationDTO locDto = new LocationDTO();
        locDto.setId(findByNameIgnoreCase.get().getId());
        locDto.setName(findByNameIgnoreCase.get().getName());
        List<CartridgeChoiceDto> cartridgesByModelPrinter = new ArrayList<>();
        Optional<Printer> findPrinterById = printerRepo.findById(idPrinter);
        Long idModelPrinter = findPrinterById.get().getModel().getId();
        for (Cartridge cartridge : cartridges) {
            List<Model> modelsPrinters = cartridge.getModel().getModelsPrinters();
            boolean duplicate = false;
            for (Model modelPrinter : modelsPrinters) {
                if ((idModelPrinter == modelPrinter.getId()) && (!cartridge.isUtil() && !cartridge.isUseInPrinter())) {
                    CartridgeChoiceDto dto = new CartridgeChoiceDto();
                    dto.setId(cartridge.getId());
                    dto.setName("Модель: " + cartridge.getModel().getModel() + ", контракт № " + cartridge.getContract().getContractNumber() + " от " + sdf.format(cartridge.getContract().getDateStartContract()));
                    cartridgesByModelPrinter.add(dto);
                }
            }
        }
        return cartridgesByModelPrinter;
    }

}
