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
import org.springframework.transaction.annotation.Transactional;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.NameFromOneC;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.ChangePrinterInventaryNumberDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangePrinterSerialNumberDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterStatusDto;
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
    private LocationRepo locationRepo;
    @Autowired
    private ListenerOperationRepo listenerOperationRepo;
    @Autowired
    private DictionaryEmployeeHolder dictionaryEmployeeHolder;

    public Map<String, List<PrinterDTO>> showPrintersByLocation() {

        String location = "Губкин";

        Map<String, List<PrinterDTO>> map = new HashMap<>();

        List<Model> models = modelPrinterRepo.findAll();

        List<Location> locations = locationRepo.findAll();

        for (int i = 0; i < locations.size(); i++) {
            List<PrinterDTO> list = new ArrayList<>();
           
                List<Printer> printers = printerRepo.findByPlaceLocationName(locations.get(i).getName());
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

                    if (!printers.get(j).getPlace().getLocation().getName().isEmpty()) {
                        dto.setLocation(printers.get(j).getPlace().getLocation().getName());
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
                    cartDto.setLocation(car.getPlace().getLocation().getName());
                    cartDto.setDateEndExploitation(car.getDateEndExploitation());
                    cartDto.setDateStartExploitation(car.getDateStartExploitation());
                    cartDto.setType(car.getModel().getType().getName());
                    cartDto.setResource(car.getModel().getDefaultNumberPrintPage().toString());
                    cartDto.setUtil(car.isUtil());
                    cartDto.setModel(car.getModel().getModel());
                    cartridgesForPrinterDTO.add(cartDto);
            }
            dto.setCartridge(cartridgesForPrinterDTO);

            dto.setLocation(el.getPlace().getLocation().getName());

            printers.add(dto);
        }

        return printers;
    }


   
    
    public PrinterDTO getPrinterById(Long id) {
    
        PrinterDTO dto = new PrinterDTO();
        Printer findPrinterById = printerRepo.findByPrinterId(id);
        dto.setId(findPrinterById.getId());
        dto.setContractNumber(findPrinterById.getContract().getContractNumber().toString());
        dto.setInventaryNumber(findPrinterById.getInventoryNumber());
        dto.setSerialNumber(findPrinterById.getSerialNumber());
        dto.setLocation(findPrinterById.getPlace().getLocation().getName());
        dto.setManufacturer(findPrinterById.getManufacturer().getName());
        dto.setModel(findPrinterById.getModel().getName());
        dto.setStartContract(findPrinterById.getContract().getDateStartContract());
        dto.setEndContract(findPrinterById.getContract().getDateEndContract());
        dto.setContractId(findPrinterById.getContract().getId());
        dto.setPrinterStatus(findPrinterById.getPrinterStatus().getStatus());
        dto.setLocationId(findPrinterById.getPlace().getLocation().getId());
        dto.setDeviceType(findPrinterById.getModel().getDeviceType().getValue());
        if(null != findPrinterById.getNameFromOneC()) {
            dto.setNameFromOneC(findPrinterById.getNameFromOneC().getName());
        } else {
            dto.setNameFromOneC("Отсутствует");
        }
        
        List<CartridgeDTO> cartridgesForPrinter = new ArrayList<>();
        for(Cartridge car : findPrinterById.getCartridge()) {
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
                    cartDto.setLocation(car.getPlace().getLocation().getName());
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
    
    
    public List<Printer> getPrintersByModelId(Long id) {
       return printerRepo.findByModelId(id);  
    }
    
    @Transactional
    public void editPrinterStatus(PrinterStatusDto dto) {
        Printer printer = printerRepo.findByPrinterId(dto.getId());
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
    
    @Transactional
    public void editPrinterNameFromOneC(Long id, String value) {
        Printer findById = printerRepo.findByPrinterId(id);
        if(!value.isBlank() && !value.isEmpty()) {
            if(null != findById.getNameFromOneC()) {
                findById.getNameFromOneC().setName(value);
            } else {
                NameFromOneC nameFronOneC = new NameFromOneC(value);
                findById.setNameFromOneC(nameFronOneC);
            }
            printerRepo.save(findById);
        }
    }
    
    @Transactional
    public void editPrinterLocation(ChangeDeviceLocationDTO dto) {
        Printer findPrinterById = printerRepo.findByPrinterId(dto.getId());
        Optional<Location> locationTemp = locationRepo.findByNameIgnoreCase(dto.getLocation().toLowerCase());
        Set<Cartridge> cartridges = findPrinterById.getCartridge();
        for(Cartridge cart : cartridges) {
            if(cart.isUseInPrinter()) {
                cart.getPlace().setLocation(locationTemp.get());
            }
        }
        findPrinterById.getPlace().setLocation(locationTemp.get());
        printerRepo.save(findPrinterById);
    }
    
    @Transactional
    public void editPrinterSerialNumber(ChangePrinterSerialNumberDTO dto) {
        Printer findPrinterById = printerRepo.getReferenceById(dto.getId());
        findPrinterById.setSerialNumber(dto.getSerialNumber());
        printerRepo.save(findPrinterById);
    }
    
    @Transactional
    public void editPrinterInventaryNumber(ChangePrinterInventaryNumberDTO dto) {
        Printer findPrinterById = printerRepo.getReferenceById(dto.getId());
        findPrinterById.setInventoryNumber(dto.getInventaryNumber());
        printerRepo.save(findPrinterById);
    }

    
    public PrinterDTO printerToPrinterDto(Printer printer) {
        PrinterDTO dto = new PrinterDTO();
        dto.setId(printer.getId());
        dto.setContractId(printer.getContract().getId());
        dto.setContractNumber(printer.getContract().getContractNumber());
        dto.setEndContract(printer.getContract().getDateEndContract());
        dto.setInventaryNumber(printer.getInventoryNumber());
        dto.setLocation(printer.getPlace().getLocation().getName());
        dto.setLocationId(printer.getPlace().getLocation().getId());
        dto.setManufacturer(printer.getManufacturer().getName());
        dto.setModel(printer.getModel().getName());
        if(null != printer.getNameFromOneC()) {
            dto.setNameFromOneC(printer.getNameFromOneC().getName());
        } else {
            dto.setNameFromOneC("Отсутствует");
        }
        
        dto.setPrinterStatus(printer.getPrinterStatus().getStatus());
        dto.setSerialNumber(printer.getSerialNumber());
        dto.setStartContract(printer.getContract().getDateStartContract());
        
       return dto;
    }

}
