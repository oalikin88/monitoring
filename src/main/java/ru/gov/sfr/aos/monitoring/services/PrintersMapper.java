/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.ChangePrinterInventaryNumberDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangePrinterSerialNumberDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterStatusDto;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ListenerOperationRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class PrintersMapper {

    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private ListenerOperationRepo listenerOperationRepo;
    @Autowired
    private DictionaryEmployeeHolder dictionaryEmployeeHolder;

    public List<PrinterDTO> showPrinters() {

        List<PrinterDTO> printers = new ArrayList<>();
        List<Printer> printerList = printerRepo.findAll();
        List<Model> models = modelPrinterRepo.findAll();

        for (Printer el : printerList) {

            PrinterDTO dto = new PrinterDTO();
            dto.setManufacturer(el.getManufacturer().getName());

            dto.setModel(el.getModel().getName());

            dto.setInventaryNumber(el.getInventoryNumber());
            dto.setSerialNumber(el.getSerialNumber());

            Set<Cartridge> cartridges = el.getCartridge();
            List<CartridgeDTO> cartridgesForPrinterDTO = new ArrayList<>();
            for(Cartridge car : cartridges) {
                  CartridgeDTO cartDto = new CartridgeDTO();
                    cartDto.setContract(car.getContract().getId());
                    cartDto.setContractNumber(car.getContract().getContractNumber());
                    cartDto.setId(car.getId());
                    cartDto.setLocation(car.getLocation().getName());
                    cartDto.setDateEndExploitation(car.getDateEndExploitation());
                    cartDto.setDateStartExploitation(car.getDateStartExploitation());
                    cartDto.setType(car.getModel().getType().getName());
                    cartDto.setResource(car.getModel().getDefaultNumberPrintPage().toString());
                    cartDto.setUtil(car.isUtil());
                    cartDto.setModel(car.getModel().getModel());
                    cartridgesForPrinterDTO.add(cartDto);
            }
            dto.setCartridge(cartridgesForPrinterDTO);

            dto.setLocation(el.getLocation().getName());

            printers.add(dto);
        }

        return printers;
    }

    public Map<String, List<PrinterDTO>> showPrintersOnModels() {
        Map<String, List<PrinterDTO>> map = new HashMap<>();

        List<Model> models = modelPrinterRepo.findAll();
        for (int i = 0; i < models.size(); i++) {
            List<PrinterDTO> dtoes = new ArrayList<>();
            for (int j = 0; j < models.get(i).getPrintersList().size(); j++) {
                PrinterDTO dto = new PrinterDTO();
                dto.setManufacturer(models.get(i).getPrintersList().get(j).getManufacturer().getName());
                dto.setModel(models.get(i).getPrintersList().get(j).getModel().getName());
                dto.setLocation(models.get(i).getPrintersList().get(j).getLocation().getName());
                dtoes.add(dto);
            }
            map.put(models.get(i).getName(), dtoes);
        }

        return map;

    }

    public Map<String, List<PrinterDTO>> showPrintersByLocation() {

        String location = "Губкин";

        Map<String, List<PrinterDTO>> map = new HashMap<>();

        List<Model> models = modelPrinterRepo.findAll();

        List<Location> locations = locationRepo.findAll();

        for (int i = 0; i < locations.size(); i++) {
            List<PrinterDTO> list = new ArrayList<>();
           
                List<Printer> printers = printerRepo.findByLocationName(locations.get(i).getName());
                for (int j = 0; j < printers.size(); j++) {
                    PrinterDTO dto = new PrinterDTO();
                    if (!printers.get(j).getManufacturer().getName().isEmpty()) {
                        dto.setManufacturer(printers.get(j).getManufacturer().getName());
                    } else {
                        dto.setManufacturer("По умолчанию");
                    }

                    if (!printers.get(j).getModel().getName().isEmpty()) {
                        dto.setModel(printers.get(j).getModel().getName());
                    } else {
                        dto.setModel("По умолчанию");
                    }

                    if (!printers.get(j).getLocation().getName().isEmpty()) {
                        dto.setLocation(printers.get(j).getLocation().getName());
                    } else {
                        dto.setLocation("Склад");
                    }

                   
                       list.add(dto);
                   
                }
                
                if(!list.isEmpty()) {
                 map.put(locations.get(i).getName(), list);
                }
            
            
        }

        return map;
    }
    
    
    public Map<LocationDTO, List<PrinterDTO>> getPrintersByLocation(Long idLocation, List<Long> idModel) {
    
        
        Optional<Location> locationById = locationRepo.findById(idLocation);
        
        Map<LocationDTO, List<PrinterDTO>> map = new HashMap<>();
        LocationDTO locationDTO = new LocationDTO(locationById.get().getId(), locationById.get().getName());
        List <PrinterDTO> printersDtoes = new ArrayList<>();
        for(Long el : idModel) {
            List<Printer> findPrintersByLocationIdAndModelId = printerRepo.findByLocationIdAndModelId(idLocation, el); 
            
            
            for(Printer printer : findPrintersByLocationIdAndModelId) {
            PrinterDTO dto = new PrinterDTO();
            dto.setId(printer.getId());
            dto.setModel(printer.getModel().getName());
            dto.setManufacturer(printer.getModel().getManufacturer().getName());
            dto.setSerialNumber(printer.getSerialNumber());
            dto.setInventaryNumber(printer.getInventoryNumber());
            dto.setContractNumber(printer.getContract().getContractNumber().toString());
            List<CartridgeDTO> list = new ArrayList<>();
            for(Cartridge car : printer.getCartridge()) {
                CartridgeDTO cartDto = new CartridgeDTO();
                cartDto.setContract(car.getContract().getId());
                    cartDto.setContractNumber(car.getContract().getContractNumber());
                    cartDto.setId(car.getId());
                    cartDto.setLocation(car.getLocation().getName());
                    cartDto.setDateEndExploitation(car.getDateEndExploitation());
                    cartDto.setDateStartExploitation(car.getDateStartExploitation());
                    cartDto.setType(car.getModel().getType().getName());
                    cartDto.setResource(car.getModel().getDefaultNumberPrintPage().toString());
                    cartDto.setUtil(car.isUtil());
                    cartDto.setModel(car.getModel().getModel());
                    list.add(cartDto);
            }
            dto.setCartridge(list);
            printersDtoes.add(dto);
        }
            map.put(locationDTO, printersDtoes);
        }
        
        
        return map;
    
    }
    
    
    public PrinterDTO getPrinterById(Long id) {
    
        PrinterDTO dto = new PrinterDTO();
        
        Optional<Printer> findPrinterById = printerRepo.findById(id);
        
        dto.setId(findPrinterById.get().getId());
        dto.setContractNumber(findPrinterById.get().getContract().getContractNumber().toString());
        dto.setInventaryNumber(findPrinterById.get().getInventoryNumber());
        dto.setSerialNumber(findPrinterById.get().getSerialNumber());
        dto.setLocation(findPrinterById.get().getLocation().getName());
        dto.setManufacturer(findPrinterById.get().getManufacturer().getName());
        dto.setModel(findPrinterById.get().getModel().getName());
        dto.setStartContract(findPrinterById.get().getContract().getDateStartContract());
        dto.setEndContract(findPrinterById.get().getContract().getDateEndContract());
        dto.setContractId(findPrinterById.get().getContract().getId());
        dto.setPrinterStatus(findPrinterById.get().getPrinterStatus().getStatus());
        
        List<CartridgeDTO> cartridgesForPrinter = new ArrayList<>();
        for(Cartridge car : findPrinterById.get().getCartridge()) {
              CartridgeDTO cartDto = new CartridgeDTO();
                    List<ListenerOperation> listListenerOperationsByCartridgeID = listenerOperationRepo.findByCartridgeID(car.getId());
                    for(ListenerOperation listener : listListenerOperationsByCartridgeID) {
                        if(listener.getOperationType().equals(OperationType.UTIL)) {
                            List<DictionaryEmployee> employees = dictionaryEmployeeHolder.getEmployees();
                            Map<String, String> employeesMap = employees.stream().collect(Collectors.toMap(DictionaryEmployee::getCode, (e) -> e.getSurname() + " " + e.getName() + " " + e.getMiddlename()));
                            
                            cartDto.setEmployeeToDoWork(employeesMap.get(listener.getEmployeeToDoWork()));
                            cartDto.setEmployeeToSetDevice(employeesMap.get(listener.getEmployeeToSetDevice()));
                            cartDto.setEmployeeMOL(employeesMap.get(listener.getEmployeeMOL()));
                            cartDto.setListenerId(listener.getId());
                            break;
                        }
                    }
                    cartDto.setContract(car.getContract().getId());
                    cartDto.setContractNumber(car.getContract().getContractNumber());
                    cartDto.setStartContract(car.getContract().getDateStartContract());
                    cartDto.setId(car.getId());
                    cartDto.setLocation(car.getLocation().getName());
                    cartDto.setDateEndExploitation(car.getDateEndExploitation());
                    cartDto.setDateStartExploitation(car.getDateStartExploitation());
                    cartDto.setType(car.getModel().getType().getName());
                    cartDto.setResource(car.getModel().getDefaultNumberPrintPage().toString());
                    cartDto.setUtil(car.isUtil());
                    cartDto.setModel(car.getModel().getModel());
                    cartDto.setUsePrinter(car.isUseInPrinter());
                    cartDto.setCount(car.getCount());
                    
                    
                    cartridgesForPrinter.add(cartDto);
        }
        
        dto.setCartridge(cartridgesForPrinter);
        
        
        return dto;
    }
    
    
    public void editPrinterStatus(PrinterStatusDto dto) {
        Optional<Printer> findById = printerRepo.findById(dto.getId());
        if(findById.isPresent()) {
            Printer printer = findById.get();
            switch (dto.getStatus()) {
                case "OK":
                    printer.setPrinterStatus(PrinterStatus.OK);
                    break;
                case "REPAIR":
                    printer.setPrinterStatus(PrinterStatus.REPAIR);
                    break;
                case "MONITORING":
                    printer.setPrinterStatus(PrinterStatus.MONITORING);
                    break;
                case "UTILIZATION":
                    printer.setPrinterStatus(PrinterStatus.UTILIZATION);
                    break;
                case "DEFECTIVE":
                    printer.setPrinterStatus(PrinterStatus.DEFECTIVE);
                    break;
                case "DELETE":
                    printer.setPrinterStatus(PrinterStatus.DELETE);
                    break;
                default:
                    throw new AssertionError();
            }
            
            printerRepo.save(printer);
            
        }
    }
    
    public void editPrinterLocation(ChangeDeviceLocationDTO dto) {
    
        
        
        Optional<Printer> findPrinterById = printerRepo.findById(dto.getId());
        Optional<Location> locationTemp = locationRepo.findByNameIgnoreCase(dto.getLocation().toLowerCase());
        Set<Cartridge> cartridges = findPrinterById.get().getCartridge();
        for(Cartridge cart : cartridges) {
            if(cart.isUseInPrinter()) {
                cart.setLocation(locationTemp.get());
            }
        }
        findPrinterById.get().setLocation(locationTemp.get());
        printerRepo.save(findPrinterById.get());
    }
    
    
    public void editPrinterSerialNumber(ChangePrinterSerialNumberDTO dto) {
        
        Optional<Printer> findPrinterById = printerRepo.findById(dto.getId());
        
        findPrinterById.get().setSerialNumber(dto.getSerialNumber());
        
        printerRepo.save(findPrinterById.get());
    }
    
    
    public void editPrinterInventaryNumber(ChangePrinterInventaryNumberDTO dto) {
    
        Optional<Printer> findPrinterById = printerRepo.findById(dto.getId());
        findPrinterById.get().setInventoryNumber(dto.getInventaryNumber());
        printerRepo.save(findPrinterById.get());
        
        
        
    }

}
