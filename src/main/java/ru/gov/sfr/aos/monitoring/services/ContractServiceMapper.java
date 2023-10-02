/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.ContractForViewDTO;
import ru.gov.sfr.aos.monitoring.models.EditContractDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ManufacturerRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class ContractServiceMapper {

    @Autowired
    private ContractServiceImpl contractServiceImpl;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private ManufacturerRepo manufacturerRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private ListenerOperationService listenerOperationService;

    public void createNewContract(List<Map<String, String>> input) throws NumberFormatException {
        
        CartridgeModel cartridgeModel;
        CartridgeModel cartridgeModelIndepended;
        Set<ObjectBuing> objectsBuing = new HashSet<>();
        Set<Cartridge> cartridges = new HashSet<>();
        String contractNumber;
        Contract contract = new Contract();
        Map<Long, Integer> modelsCartridges = new HashMap<>();
         
                Location location = null;
        int amountPrinters = 0;
        int amountCartridges = 0;

        for (int i = 0; i < input.size(); i++) {

            // Основные параметры контракта
            if (i == 0) {
                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {
                    switch (entry.getKey()) {
                        case "numberContract":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                contractNumber = entry.getValue();
                                contract.setContractNumber(contractNumber);
                            } else {
                                contract.setContractNumber("Отсутствует");
                            }
                            break;
                        case "dateStartContract":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                contract.setDateStartContract(convertDate(entry.getValue()));
                            } else {
                                contract.setDateStartContract(new Date());
                            }
                            break;
                        case "dateEndContract":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                contract.setDateEndContract(convertDate(entry.getValue()));
                            } else {
                                contract.setDateEndContract(new Date());
                            }
                            break;
                        case "amountPrinters":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                try {
                                    amountPrinters = Integer.parseInt(entry.getValue());
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case "amountCartridges":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                try {
                                    amountCartridges = Integer.parseInt(entry.getValue());
                             
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;

                    }
                }
            }

            if (input.get(i).size() == 8) {
                Printer printer = new Printer();
                Manufacturer manufacturer = null;
                Model model = null;
                Cartridge cartridgeInclude = null;
                // Добавление объекта покупки в контракт
               
                Optional<Location> findLocation = locationRepo.findByNameIgnoreCase("Склад".toLowerCase());
                
                cartridgeModel = null;
                boolean cartridgeIncluded = false;

                if (findLocation != null) {
                    location = findLocation.get();
                } else {
                    location = new Location("Склад");
                }
                printer.setLocation(location);
                printer.setPrinterStatus(PrinterStatus.OK);
                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {

                    switch (entry.getKey()) {
                        case "manufacturer":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                Optional<Manufacturer> optManufacturer = manufacturerRepo.findByNameContainingIgnoreCase(entry.getValue());
                                if (optManufacturer.isPresent()) {
                                    manufacturer = optManufacturer.get();
                                } else {
                                    manufacturer = new Manufacturer();
                                    manufacturer.setName(entry.getValue());
                                }
                                } else {
                                    manufacturer = new Manufacturer();
                                    manufacturer.setName("Отсутствует");
                                }
                                break;
                        case "serialNumber":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {

                                printer.setSerialNumber(entry.getValue());
                            } else {
                                printer.setSerialNumber("Отсутствует");
                            }
                            break;
                        case "model":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                Optional<Model> optModel = modelPrinterRepo.findByName(entry.getValue());
                                if (optModel.isPresent()) {
                                    model = optModel.get();
                                } else {
                                    model = new Model();
                                    model.setName(entry.getValue());
                                }

                            } else {
                                model = new Model();
                                model.setName("По умолчанию");
                            }
                            break;
                        case "inventoryNumber":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                printer.setInventoryNumber(entry.getValue());
                            } else {
                                printer.setInventoryNumber("Отсутствует");
                            }
                            break;
                        
                        case "cartridgeIncluded":
                            if (entry.getValue().contains("true")) {
                                cartridgeInclude = new Cartridge();
                                cartridgeModel = new CartridgeModel();
                                cartridgeIncluded = true;
                                cartridgeInclude.setLocation(location);
                            }
                            break;
                      
                        case "cartridgeIncludeModel":
                            if (cartridgeIncluded) {
                                String targetModel;
                                if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                    targetModel = entry.getValue();
                                } else {
                                    targetModel = "Отсутствует";
                                }
                                Optional<CartridgeModel> cartridgeModelsEntities = cartridgeModelRepo.findByModel(targetModel);
                                if (cartridgeModelsEntities.isPresent()) {
                                    cartridgeModel = cartridgeModelsEntities.get();
                                } else {

                                    cartridgeModel.setModel(targetModel);
                                }
                            }
                            break;
                        
                    }
                }
                if (cartridgeIncluded) {
                    cartridgeInclude.setContract(contract);
                    cartridgeInclude.setUtil(true);
                    cartridgeInclude.setUseInPrinter(true);
                    cartridgeInclude.setDateStartExploitation(LocalDateTime.now());
                    cartridgeInclude.setPrinter(printer);
                    cartridgeInclude.setModel(cartridgeModel);
                    cartridges.add(cartridgeInclude);
                    objectsBuing.add(cartridgeInclude);

                }
                model.setManufacturer(manufacturer);
                model.addPrinter(printer);
                manufacturer.addModel(model);
                printer.setManufacturer(manufacturer);
                printer.setModel(model);
                printer.setCartridge(cartridges);
                printer.setContract(contract);
                objectsBuing.add(printer);

            }
            if (input.get(i).size() == 6) {
                String getAmount = input.get(i).get("amount");
                int amount = Integer.parseInt(getAmount);
                for(int amountCount = 0; amountCount < amount; amountCount++) {
                Cartridge cartridge = new Cartridge();
                Optional<Location> findLocation = locationRepo.findByNameIgnoreCase("Склад".toLowerCase());
                
                cartridgeModelIndepended = null;
                if (findLocation != null) {
                    location = findLocation.get();
                } else {
                    location = new Location("Склад");
                }
                cartridge.setLocation(location);

                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {

                    switch (entry.getKey()) {
                        case "model":
                            String targetModel;
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                targetModel = entry.getValue();
                                
                            } else {
                                targetModel = "Отсутствует";
                            }
                            Optional<CartridgeModel> cartridgeModelsEntities = cartridgeModelRepo.findByModel(targetModel);
                            modelsCartridges.put(cartridgeModelsEntities.get().getId(), amount);
                            if (cartridgeModelsEntities.isPresent()) {
                                cartridgeModelIndepended = cartridgeModelsEntities.get();
                            } else {
                                cartridgeModelIndepended = new CartridgeModel();
                                cartridgeModelIndepended.setModel(targetModel);
                            }

                            cartridge.setModel(cartridgeModelIndepended);

                            break;
                             case "itemCode":
                                 cartridge.setItemCode(entry.getValue());
                                 break;
                             case "nameMaterial":
                             cartridge.setNameMaterial(entry.getValue());
                             break;
                    }

                }
                cartridge.setContract(contract);
                   
                
                objectsBuing.add(cartridge);
            }
                  
                        
            }
        }
        Optional<Location> findLocationById = locationRepo.findById(location.getId());
        Location currLoc = findLocationById.get();
        int cartridgesOnSklad = currLoc.getCartridges().size();
        
          for(Map.Entry<Long, Integer> entry : modelsCartridges.entrySet()) {
                    
                    ListenerOperation listener = new ListenerOperation();
                    Optional<CartridgeModel> findModelCartridgeByName = cartridgeModelRepo.findById(entry.getKey());
                    List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(currLoc.getId(), findModelCartridgeByName.get().getId());
                    listener.setModel(findModelCartridgeByName.get());
                    listener.setDateOperation(LocalDateTime.now());
                    listener.setLocation(currLoc);
                    cartridgesOnSklad = cartridgesOnSklad + entry.getValue();
                    listener.setAmountDevicesOfLocation(cartridgesOnSklad);
                    listener.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size() + entry.getValue());
                    listener.setCurrentOperation("Закуплен по контракту");
                    listener.setModel(findModelCartridgeByName.get());
                    listener.setOperationType(OperationType.BUY);
                    
                    listenerOperationService.saveListenerOperation(listener);      
                    
                    }
        
        contract.setObjectBuing(objectsBuing);
        
        contractServiceImpl.saveContract(contract);

        System.out.println(
                "Контракт сохранён успешно");
    }

    public static Date convertDate(String input) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date targetDate = null;
        try {
            targetDate = dateFormat.parse(input);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetDate;
    }
    
    
    public List<ContractForViewDTO> getAllContracts() {
        
        List<Contract> contracts = contractRepo.findAll();
        List<Printer> findAllPrinters = printerRepo.findAll();
        List<Cartridge> findAllCartridges = cartridgeRepo.findAll();
     
        List<ContractForViewDTO> listDtoes = new ArrayList<>();
        
        for(Contract contract : contracts) {
            ContractForViewDTO dto = new ContractForViewDTO();
            List<Printer> printersInContract = new ArrayList<>();
            List<Cartridge> cartridgesInContract = new ArrayList<>();
            dto.setId(contract.getId());
            dto.setContractNumber(contract.getContractNumber());
            dto.setDateStartContract(contract.getDateStartContract());
            dto.setDateEndContract(contract.getDateEndContract());
            Set<ObjectBuing> objectBuings = contract.getObjectBuing();
            
            // Собираю список устройств закупленных по данному контракту
            
            for(ObjectBuing objectBuing : objectBuings) {
                boolean find = false;
                Long idSearch = objectBuing.getId();
                for(Printer printer : findAllPrinters) {
                    if(idSearch == printer.getId()) {
                        printersInContract.add(printer);
                        find = true;
                        break;
                    }
                }
                
                if(!find) {
                    for(Cartridge cartridge : findAllCartridges) {
                        if(idSearch == cartridge.getId()) {
                            cartridgesInContract.add(cartridge);
                            find = true;
                            break;
                        }
                    }
                }
                
            }
            
            StringBuilder itemsBuilder = new StringBuilder();
            if(printersInContract.size() > 0) {
                itemsBuilder.append("принтеров: " + printersInContract.size());
            } 
            
            if(printersInContract.size() > 0 && cartridgesInContract.size() > 0) {
                itemsBuilder.append(", ");
            }
            
            if(cartridgesInContract.size() > 0) {
                itemsBuilder.append("картриджей: " + cartridgesInContract.size());
            }
            dto.setItems(itemsBuilder.toString());
            itemsBuilder.setLength(0);
            listDtoes.add(dto);
        }
        return listDtoes;
    }
    
    public EditContractDTO getContract(Long id) {
        Optional<Contract> findContractById = contractRepo.findById(id);
        List<Printer> findAllPrinters = printerRepo.findAll();
        List<Cartridge> findAllCartridges = cartridgeRepo.findAll();
        Contract contract = findContractById.get();
        EditContractDTO dto = new EditContractDTO();
        dto.setId(contract.getId());
        dto.setContractNumber(contract.getContractNumber());
        dto.setDateStartContract(contract.getDateStartContract());
        dto.setDateEndContract(contract.getDateEndContract());
        
        // Собираю список устройств закупленных по данному контракту
        List<Printer> printersInContract = new ArrayList<>();
        List<Cartridge> cartridgesInContract = new ArrayList<>();
        Set<ObjectBuing> objectBuings = contract.getObjectBuing();
        
        
        for(ObjectBuing objectBuing : objectBuings) {
                boolean find = false;
                Long idSearch = objectBuing.getId();
                for(Printer printer : findAllPrinters) {
                    if(idSearch == printer.getId()) {
                        printersInContract.add(printer);
                        find = true;
                        break;
                    }
                }
                
                if(!find) {
                    for(Cartridge cartridge : findAllCartridges) {
                        if(idSearch == cartridge.getId()) {
                            cartridgesInContract.add(cartridge);
                            find = true;
                            break;
                        }
                    }
                }
                
            }
        
        Set<PrinterDTO> listPrinterDto = new HashSet<>();
        for(Printer printer : printersInContract) {
            
            PrinterDTO printerDto = new PrinterDTO();
            printerDto.setId(printer.getId());
            printerDto.setInventaryNumber(printer.getInventoryNumber());
            printerDto.setSerialNumber(printer.getSerialNumber());
            printerDto.setModel(printer.getModel().getName());
            printerDto.setManufacturer(printer.getManufacturer().getName());
            printerDto.setLocation(printer.getLocation().getName());
            Set<Cartridge> cartridges = printer.getCartridge();
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
            printerDto.setCartridge(cartridgesForPrinterDTO);
            listPrinterDto.add(printerDto);
        }
        Set<CartridgeDTO> listCartridgesDto = new HashSet<>();
        for(Cartridge cartridge : cartridgesInContract) {
            
            CartridgeDTO cartridgeDto = new CartridgeDTO();
            cartridgeDto.setId(cartridge.getId());
            cartridgeDto.setDateStartExploitation(cartridge.getDateStartExploitation());
            cartridgeDto.setDateEndExploitation(cartridge.getDateEndExploitation());
            cartridgeDto.setLocation(cartridge.getLocation().getName());
            cartridgeDto.setType(cartridge.getModel().getType().getName());
            cartridgeDto.setModel(cartridge.getModel().getModel());
            cartridgeDto.setResource(cartridge.getModel().getDefaultNumberPrintPage().toString());
            cartridgeDto.setUsePrinter(cartridge.isUseInPrinter());
            cartridgeDto.setItemCode(cartridge.getItemCode());
            cartridgeDto.setUtil(cartridge.isUtil());
            listCartridgesDto.add(cartridgeDto);
        }
        
        dto.setPrinters(listPrinterDto);
        dto.setCartridges(listCartridgesDto);
        
        return dto;
    }
        
}
