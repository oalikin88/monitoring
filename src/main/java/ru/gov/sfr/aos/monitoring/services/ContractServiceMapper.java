/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.ContractDTO;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ManufacturerRepo;

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

    public void createNewContract(ContractDTO dto) {
        List<ObjectBuing> objectsBuing = new ArrayList<>();
        Long contractNumber;
        Contract contract = new Contract();
        if (dto.contractNumber != null && !dto.contractNumber.isBlank()) {
            contractNumber = Long.parseLong(dto.contractNumber);
            contract.setContractNumber(contractNumber);
        }
        if (dto.dateStartContract != null) {
            contract.setDateStartContract(dto.dateStartContract);
        }

        if (dto.dateEndContract != null) {
            contract.setDateEndContract(dto.dateEndContract);
        }

        if (dto.selectObjectBuing.equals("printer")) {
            Printer printer = new Printer();
            if (dto.manufacturer != null) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setName(dto.manufacturer);
                if (dto.modelPrinter != null) {
                    
                    manufacturer.setModel(dto.modelPrinter);
                } else {
                    manufacturer.setModel("default");
                }
                printer.setManufacturer(manufacturer);

            } else {
                printer.setManufacturer(new Manufacturer("default", "default"));
            }

            if (dto.serialNumberPrinter != null) {
                printer.setSerialNumber(dto.serialNumberPrinter);
            } else {
                printer.setSerialNumber("default");
            }
            if (dto.inventoryNumberPrinter != null) {
                printer.setInventoryNumber(dto.inventoryNumberPrinter);
            } else {
                printer.setInventoryNumber("default");
            }
            if (dto.location != null) {
                printer.setLocation(new Location(dto.location));
            } else {
                Location location;
                List<Location> findLocation = locationRepo.findByName("Склад");
                if (findLocation.size() != 0) {
                    location = findLocation.get(0);
                } else {
                    location = new Location("Склад");
                }

                printer.setLocation(location);
            }
            if (dto.isSwitched.equals("on")) {

                Cartridge cartridge = new Cartridge();
                if (dto.typeCartridge != null) {
                     String[] target = dto.typeCartridge.split(" ");
                    switch (target[2]) {
                        case ("ORIGINAL"):
                            cartridge.setType(CartridgeType.ORIGINAL);
                            break;

                        case ("ANALOG"):
                            cartridge.setType(CartridgeType.ANALOG);
                            break;

                        case ("START"):
                            cartridge.setType(CartridgeType.START);
                            break;
                    }

                } else {
                    cartridge.setType(CartridgeType.START);
                }

                if (dto.modelCartridge != null) {
                    cartridge.setModel(dto.modelCartridge);
                }

                if (dto.nominalResource != null) {
                    cartridge.setDefaultNumberPrintPage(Integer.parseInt(dto.nominalResource)); // Нужен Long!
                }
                if (dto.location != null) {
                    cartridge.setLocation(new Location(dto.location));
                } else {
                    Location location;
                    List<Location> findLocation = locationRepo.findByName("Склад");
                    if (findLocation.size() != 0) {
                        location = findLocation.get(0);
                    } else {
                        location = new Location("Склад");
                    }
                 cartridge.setLocation(location);
                }
                    cartridge.setContract(contract);
                    cartridge.setPrinter(printer);
                    objectsBuing.add(cartridge);

                }

                printer.setContract(contract);
                objectsBuing.add(printer);

            } else if (dto.selectObjectBuing.equals("cartridge")) {
                Cartridge cartridge = new Cartridge();

                if (dto.typeCartridge != null) {
                    String[] target = dto.typeCartridge.split(" ");
                    switch (target[2]) {
                        case ("ORIGINAL"):
                            cartridge.setType(CartridgeType.ORIGINAL);
                            break;

                        case ("ANALOG"):
                            cartridge.setType(CartridgeType.ANALOG);
                            break;

                        case ("START"):
                            cartridge.setType(CartridgeType.START);
                            break;
                    }

                } else {
                    cartridge.setType(CartridgeType.START);
                }

                if (dto.modelCartridge != null) {
                    cartridge.setModel(dto.modelCartridge);
                }

                if (dto.nominalResource != null) {
                    cartridge.setDefaultNumberPrintPage(Integer.parseInt(dto.nominalResource));
                }
                
                if (dto.location != null) {
                    cartridge.setLocation(new Location(dto.location));
                } else {
                    Location location;
                    List<Location> findLocation = locationRepo.findByName("Склад");
                    if (findLocation.size() != 0) {
                        location = findLocation.get(0);
                    } else {
                        location = new Location("Склад");
                    }
                    
                 cartridge.setLocation(location);
                }
                
                cartridge.setContract(contract);

                objectsBuing.add(cartridge);

            }

            contract.setObjectBuing(objectsBuing);
            contractServiceImpl.saveContract(contract);

        }

    }
