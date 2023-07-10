/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
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


    public List<PrinterDTO> showPrinters() {

        List<PrinterDTO> printers = new ArrayList<>();
        List<Cartridge> cartridges = cartridgeRepo.findAll();
        List<Printer> printerList = printerRepo.findAll();
        List<Model> models = modelPrinterRepo.findAll();

        for (Printer el : printerList) {

            PrinterDTO dto = new PrinterDTO();
            dto.setManufacturer(el.getManufacturer().getName());

            dto.setModel(el.getModel().getName());

            dto.setInventaryNumber(el.getInventoryNumber());
            dto.setSerialNumber(el.getSerialNumber());

            for (Cartridge o : cartridges) {
                if (o.getPrinter() != null) {
                    if (el.getId() == o.getPrinter().getId()) {
                        dto.setCartridge(o.getModel().getModel());
                    }
                }
            }

            if (dto.getCartridge() == null) {
                dto.setCartridge("Отсутствует");
            }

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
    


}
