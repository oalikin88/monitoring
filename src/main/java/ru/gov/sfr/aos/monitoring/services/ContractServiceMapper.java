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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
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
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;

    public void createNewContract(List<Map<String, String>> input) {
        
        CartridgeModel cartridgeModel;
        CartridgeModel cartridgeModelIndepended;
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

            if (input.get(i).size() == 8) {

                // Добавление объекта покупки в контракт
                Printer printer = new Printer();
                Manufacturer manufacturer = null;
                Model model = null;
                Cartridge cartridgeInclude = null;
                Location location = null;
                Location findLocation = locationRepo.findByName("Склад");
                cartridgeModel = null;
                boolean cartridgeIncluded = false;

                if (findLocation != null) {
                    location = findLocation;
                } else {
                    location = new Location("Склад");
                }
                printer.setLocation(location);

                for (Map.Entry<String, String> entry : input.get(i).entrySet()) {

                    switch (entry.getKey()) {
                        case "manufacturer":
                            if (!entry.getValue().isEmpty() || !entry.getValue().isBlank()) {
                                List<Manufacturer> manufacturers = manufacturerRepo.findByNameContainingIgnoreCase(entry.getValue());
                                if (!manufacturers.isEmpty()) {
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
                                if (!models.isEmpty()) {
                                    model = models.get(0);
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
                                List<CartridgeModel> cartridgeModelsEntities = cartridgeModelRepo.findByModel(targetModel);
                                if (cartridgeModelsEntities.size() > 0) {
                                    cartridgeModel = cartridgeModelsEntities.get(0);
                                } else {

                                    cartridgeModel.setModel(targetModel);
                                }
                            }
                            break;
                        
                    }
                }
                if (cartridgeIncluded) {
                    cartridgeInclude.setContract(contract);
                    cartridgeInclude.setPrinter(printer);
                    cartridgeInclude.setModel(cartridgeModel);
                    objectsBuing.add(cartridgeInclude);

                }
                model.setManufacturer(manufacturer);
                model.addPrinter(printer);
                manufacturer.addModel(model);
                printer.setManufacturer(manufacturer);
                printer.setModel(model);
                printer.setContract(contract);
                objectsBuing.add(printer);

            }
            if (input.get(i).size() == 3) {

                Cartridge cartridge = new Cartridge();
                Location findLocation = locationRepo.findByName("Склад");
                Location location = null;
                cartridgeModelIndepended = null;
                if (findLocation != null) {
                    location = findLocation;
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
                            List<CartridgeModel> cartridgeModelsEntities = cartridgeModelRepo.findByModel(targetModel);
                            if (cartridgeModelsEntities.size() > 0) {
                                cartridgeModelIndepended = cartridgeModelsEntities.get(0);
                            } else {
                                cartridgeModelIndepended = new CartridgeModel();
                                cartridgeModelIndepended.setModel(targetModel);
                            }

                            cartridge.setModel(cartridgeModelIndepended);

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
