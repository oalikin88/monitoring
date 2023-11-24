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
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.PrintersByLocationandModelDto;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */



@Component
public class PrinterService {
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private PrinterRepo printerRepo;

    public Map<LocationDTO, List<PrintersByLocationandModelDto>> getPrintersByLocationAndModel() {
        List<Location> locations = locationRepo.findAll();
        List<Model> allModels = modelPrinterRepo.findAll();
        Map<LocationDTO, List<PrintersByLocationandModelDto>> map = new HashMap<>();
        for (Location loc : locations) {
            LocationDTO locDto = new LocationDTO(loc.getId(), loc.getName());
            List<PrintersByLocationandModelDto> listDtoes = new ArrayList<>();
            for (Model model : allModels) {
                List<Printer> printersByLocationAnModel = printerRepo.findByLocationNameAndModelIdAndPrinterStatusEquals(loc.getName(), model.getId(), PrinterStatus.OK);
                PrintersByLocationandModelDto printersByLocationandModelDto = new PrintersByLocationandModelDto();
                printersByLocationandModelDto.setIdModel(model.getId());
                printersByLocationandModelDto.setModelName(model.getName());
                printersByLocationandModelDto.setAmountPrinters(printersByLocationAnModel.size());
                printersByLocationandModelDto.setIdLocation(loc.getId());
                printersByLocationandModelDto.setLocationName(loc.getName());
                listDtoes.add(printersByLocationandModelDto);
            }
            map.put(locDto, listDtoes);
        }
        return map;
    }
    
}
