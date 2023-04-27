/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ManufacturerRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;

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
   

    public void createNewContract(List<Map<String, String>> input) {

        List<ObjectBuing> objectsBuing = new ArrayList<>();
        
        Long contractNumber;
        Contract contract = new Contract();
        int amountPrinters = 0;
        int amountCartridges = 0;

        for (int i = 0; i < input.size(); i++) {

            // Основные параметры контракта
            if (i == 0) {
                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {
                    switch (entry.getKey()) {
                        case "numberContract":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                contractNumber = Long.parseLong(entry.getValue());
                                contract.setContractNumber(contractNumber);
                            } else {
                                contract.setContractNumber(0L);
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

            if (input.get(i).size() == 9) {

                // Добавление объекта покупки в контракт
                Printer printer = new Printer();
                Manufacturer manufacturer = null;
                Model model = null;
                Cartridge cartridgeInclude = null;
                Location location = null;
                List<Location> findLocation = locationRepo.findByName("Склад");
                boolean cartridgeIncluded = false;

                if (findLocation.size() != 0) {
                    location = findLocation.get(0);
                } else {
                    location = new Location("Склад");
                }
                printer.setLocation(location);

                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {

                    switch (entry.getKey()) {
                        case "manufacturer":
                            
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                List<Manufacturer> manufacturers = manufacturerRepo.findByName(entry.getValue());
                                if(!manufacturers.isEmpty()) {
                                    manufacturer = manufacturers.get(0);
                                    
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
                               
                                List<Model> models = modelPrinterRepo.findByName(entry.getValue());
                                if(!models.isEmpty()) {
                                    model= models.get(0);
                                    
                                } else {
                                    
                                    model = new Model();
                                    model.setName(entry.getValue());
                                }
                                
                                
                            } else {
                                model = new Model("default", new Manufacturer());
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
                                cartridgeIncluded = true;
                                cartridgeInclude.setLocation(location);

                            }
                            break;
                        case "cartridgeIncludedType":
                            if (cartridgeIncluded) {

                                switch (entry.getValue()) {
                                    case "START":
                                        cartridgeInclude.setType(CartridgeType.START);
                                        break;
                                    case "ORIGINAL":
                                        cartridgeInclude.setType(CartridgeType.ORIGINAL);
                                        break;
                                    case "ANALOG":
                                        cartridgeInclude.setType(CartridgeType.ANALOG);
                                        break;
                                    default:
                                        cartridgeInclude.setType(CartridgeType.START);
                                        break;
                                }
                            }
                            break;
                        case "cartridgeIncludeModel":
                            if (cartridgeIncluded) {
                                cartridgeInclude.setModel(entry.getValue());
                            }
                            break;
                        case "cartridgeIncludeResource":
                            if (cartridgeIncluded) {
                                try {
                                    cartridgeInclude.setDefaultNumberPrintPage(Long.parseLong(entry.getValue()));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                    }

                }

                if (cartridgeIncluded) {
                    cartridgeInclude.setContract(contract);
                    cartridgeInclude.setPrinter(printer);
                    
                    objectsBuing.add(cartridgeInclude);

                }
                model.setManufacturer(manufacturer);
                manufacturer.addModel(model);
                printer.setManufacturer(manufacturer);
                printer.setContract(contract);
                objectsBuing.add(printer);

            }
            if (input.get(i).size() == 4) {

                Cartridge cartridge = new Cartridge();
                List<Location> findLocation = locationRepo.findByName("Склад");
                Location location = null;
                if (findLocation.size() != 0) {
                    location = findLocation.get(0);
                } else {
                    location = new Location("Склад");
                }
                cartridge.setLocation(location);

                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {

                    switch (entry.getKey()) {
                        case "model":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                cartridge.setModel(entry.getValue());
                            } else {
                                cartridge.setModel("Отсутствует");
                            }
                            break;
                        case "type":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                switch (entry.getValue()) {
                                    case "START":
                                        cartridge.setType(CartridgeType.START);
                                        break;
                                    case "ORIGINAL":
                                        cartridge.setType(CartridgeType.ORIGINAL);
                                        break;
                                    case "ANALOG":
                                        cartridge.setType(CartridgeType.ANALOG);
                                        break;
                                }
                            } else {
                                cartridge.setType(CartridgeType.START);
                            }
                            break;
                        case "resource":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                try {
                                    cartridge.setDefaultNumberPrintPage(Long.parseLong(entry.getValue()));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                cartridge.setDefaultNumberPrintPage(0L);
                            }
                            break;
                    }

                }
                cartridge.setContract(contract);
                objectsBuing.add(cartridge);
            }
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

}
