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
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
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
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeFromInputDto;
import ru.gov.sfr.aos.monitoring.models.CartridgeIncludeFromInputDto;
import ru.gov.sfr.aos.monitoring.models.ContractForViewDTO;
import ru.gov.sfr.aos.monitoring.models.ContractFromInputDto;
import ru.gov.sfr.aos.monitoring.models.EditContractDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterFromInputDto;
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
@Validated
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


    
    public ContractFromInputDto transformInputToDto(List<Map<String, String>> input) {
        ContractFromInputDto dto = new ContractFromInputDto();
         List<PrinterFromInputDto> printers = new ArrayList<>();
         List<CartridgeFromInputDto> cartridges = new ArrayList<>();
        int amountPrinters = 0;
        int amountCartridges = 0;
        // Цикл по всему листу
        for (int i = 0; i < input.size(); i++) {
             // Основные параметры контракта
            if (i == 0) {
                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {
                    switch (entry.getKey()) {
                        case "numberContract":
                            dto.setContractNumber(entry.getValue());
                            break;
                        case "dateStartContract":
                            dto.setDateStartContract(convertDate(entry.getValue()));
                            break;
                        case "dateEndContract":
                            dto.setDateEndContract(convertDate(entry.getValue()));
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
            
            // Добавление принтера в контракт
            if (input.get(i).size() == 8) {
              PrinterFromInputDto printerDto = new PrinterFromInputDto();
              CartridgeIncludeFromInputDto cartridgeIncudeDto = null;
               boolean cartridgeIncluded = false;
                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {
                     
                     
                    
                    switch (entry.getKey()) {
                        case "manufacturer":
                            printerDto.setManufacturer(entry.getValue());
                            break;
                        case "serialNumber":
                            printerDto.setSerialNumber(entry.getValue());
                            break;
                        case "model":
                            printerDto.setModel(entry.getValue());
                            break;
                        case "inventoryNumber":
                            printerDto.setInventoryNumber(entry.getValue());
                            break;
                        case "cartridgeIncluded":
                            if (entry.getValue().contains("true")) {
                                cartridgeIncluded = true;
                                cartridgeIncudeDto = new CartridgeIncludeFromInputDto();
                                
                                
                            }
                            break;
                      
                        case "cartridgeIncludeModel":
                            if (cartridgeIncluded) {
                                cartridgeIncudeDto.setModel(entry.getValue());
                            }
                            break; 
                            case "cartridgeIncludedType":
                            if (cartridgeIncluded) {
                                cartridgeIncudeDto.setType(entry.getValue());
                            }
                            break;
                    }
                    
                    
                }
                if(cartridgeIncluded) {
                        printerDto.setCartridgeInclude(cartridgeIncudeDto);
                    }
                    
                printers.add(printerDto);
            }
            
            //Добавление картриджей в контракт
            if (input.get(i).size() == 6) {
                String getAmount = input.get(i).get("amount");
                int amount = Integer.parseInt(getAmount);
                
                for(int amountCount = 0; amountCount < amount; amountCount++) {
                     CartridgeFromInputDto cartridge = new CartridgeFromInputDto();
                    for (Map.Entry<String, String> entry : input.get(i).entrySet()) {
                       
                        switch (entry.getKey()) {
                            case "type":
                                cartridge.setType(entry.getValue());
                                break;
                            case "model":
                                cartridge.setModel(entry.getValue());
                                break;
                            case "itemCode":
                                 cartridge.setItemCode(entry.getValue());
                                 break;
                            case "nameMaterial":
                                cartridge.setNameMaterial(entry.getValue());
                                break;
                        }
                       
                    }
                cartridges.add(cartridge);
            }
                               
            }
            
        }
        dto.setCartridges(cartridges);
        dto.setPrinters(printers);
        return dto;
    }
    
    public void createContract(ContractFromInputDto dto) throws ObjectAlreadyExists {
        CartridgeModel cartridgeModel;
        CartridgeModel cartridgeModelIndepended;
        Set<ObjectBuing> objectsBuing = new HashSet<>();
        Set<Cartridge> cartridges = new HashSet<>();
        String contractNumber;
        Contract contract = new Contract();
        Map<Long, Integer> modelsCartridges = new HashMap<>();
        Location location = null;
        Optional<Location> findLocation = locationRepo.findByNameIgnoreCase("Склад".toLowerCase());
        if(findLocation.isPresent()) {
            location = findLocation.get();
        } else {
            location = new Location("Склад");
        }
        contract.setContractNumber(dto.getContractNumber());
        contract.setDateStartContract(dto.getDateStartContract());
        contract.setDateEndContract(dto.getDateEndContract());
        List<PrinterFromInputDto> printersDtoList = dto.getPrinters();
        for(PrinterFromInputDto printerDto : printersDtoList) {
            Printer printer = new Printer();
            Optional<Manufacturer> findByNameManufacturer = Optional.empty();
            if(!printerDto.getManufacturer().isBlank() || !printerDto.getManufacturer().isEmpty()) {
           findByNameManufacturer = manufacturerRepo.findByNameContainingIgnoreCase(printerDto.getManufacturer());
            }
            if(findByNameManufacturer.isPresent()) {
                printer.setManufacturer(findByNameManufacturer.get());
            }
            Optional<Model> findByNameModelPrinter = modelPrinterRepo.findByNameIgnoreCase(printerDto.getModel());
            if(findByNameModelPrinter.isPresent()) {
                printer.setModel(findByNameModelPrinter.get());
            }
            printer.setSerialNumber(printerDto.getSerialNumber());
            printer.setInventoryNumber(printerDto.getInventoryNumber());
            printer.setLocation(location);
            Set<Cartridge> cartridgesInclude = new HashSet<>();
            if(null != printerDto.getCartridgeInclude()){
                CartridgeModel cartModelInclude = null;
                Optional<CartridgeModel> findCartridgeModel = cartridgeModelRepo.findByModelIgnoreCase(printerDto.getCartridgeInclude().getModel());
                if(findCartridgeModel.isPresent()) {
                    cartModelInclude = findCartridgeModel.get();
                }
                Cartridge cartridge = new Cartridge();
                cartridge.setModel(cartModelInclude);
                cartridge.setLocation(location);
                cartridge.setDateStartExploitation(LocalDateTime.now());
                cartridge.setModel(cartModelInclude);
                cartridge.setUseInPrinter(true);
                cartridge.setUtil(true);
                cartridge.setItemCode("0000000");
                cartridge.setNameMaterial("Картридж стартовый");
                cartridge.setContract(contract);
                cartridge.setPrinter(printer);
                cartridgesInclude.add(cartridge);
                objectsBuing.add(cartridge);
            }
            printer.setCartridge(cartridgesInclude);
            printer.setPrinterStatus(PrinterStatus.OK);
            printer.setContract(contract);
            objectsBuing.add(printer);
        }
        List<CartridgeFromInputDto> cartridgesDtoList = dto.getCartridges();
        for(CartridgeFromInputDto cartridgeDto : cartridgesDtoList) {
            Cartridge cartridge = new Cartridge();
            Optional<CartridgeModel> findCartridgeModel = cartridgeModelRepo.findByModelIgnoreCase(cartridgeDto.getModel());
            if(findCartridgeModel.isPresent()) {
                cartridge.setModel(findCartridgeModel.get());
            }
            cartridge.setLocation(location);
            cartridge.setContract(contract);
            cartridge.setItemCode(cartridgeDto.getItemCode());
            cartridge.setNameMaterial(cartridgeDto.getNameMaterial());
            objectsBuing.add(cartridge);
        }
        contract.setObjectBuing(objectsBuing);
        contractServiceImpl.saveContract(contract);
        Map<String, List<CartridgeFromInputDto>> collect = cartridgesDtoList.stream().collect(Collectors.groupingBy(CartridgeFromInputDto::getModel));
        for(Map.Entry<String, List<CartridgeFromInputDto>> entry : collect.entrySet()) {
            Optional<CartridgeModel> cartridgeModelOptional = cartridgeModelRepo.findByModelIgnoreCase(entry.getKey());
            CartridgeModel cartridgeModelFromInput = cartridgeModelOptional.get();
            modelsCartridges.put(cartridgeModelFromInput.getId(), entry.getValue().size());
        }
        List<Cartridge> findCartridgeOnStorage = cartridgeRepo.findByLocationId(1L);
        int cartridgesOnSklad = findCartridgeOnStorage.size();
        int addedCartridges = cartridgesDtoList.size();
          for(Map.Entry<Long, Integer> entry : modelsCartridges.entrySet()) {
                   
                    ListenerOperation listener = new ListenerOperation();
                    Optional<CartridgeModel> findModelCartridgeByName = cartridgeModelRepo.findById(entry.getKey());
                    List<Cartridge> findAllCartridgesByModelId = cartridgeRepo.findByModelId(entry.getKey());
                    List<Cartridge> collectCartridgesByModelExceptUtil = findAllCartridgesByModelId.stream()
                    .filter(e -> !e.isUtil())
                    .filter(el -> !el.isUseInPrinter())
                    .collect(Collectors.toList());
                            
                    List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(location.getId(), findModelCartridgeByName.get().getId(), Pageable.ofSize(25));
                    listener.setModel(findModelCartridgeByName.get());
                    listener.setDateOperation(LocalDateTime.now());
                    listener.setLocation(location);
                    cartridgesOnSklad = cartridgesOnSklad + entry.getValue();
                    listener.setAmountDevicesOfLocation(cartridgesOnSklad);
                    listener.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size());
                    listener.setCurrentOperation("Закуплен по контракту");
                    listener.setOperationType(OperationType.BUY);
                    listener.setAmount(entry.getValue());
                    listener.setAmountAllCartridgesByModel(collectCartridgesByModelExceptUtil.size());
                    listenerOperationService.saveListenerOperation(listener);      
                    
                    }
        
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
