/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.services.LocationService;
import ru.gov.sfr.aos.monitoring.services.PrintersMapper;

/**
 *
 * @author 041AlikinOS
 */

@RestController
public class GetInfoController {
    
    @Autowired
    private LocationService locationService;
    
    
    @Autowired
    private PrintersMapper mapper;
    
    @GetMapping("/getinfo")
    public Map<String, List<PrinterDTO>>  getInfo() {
       
   //     Map<String, List<PrinterDTO>> map = mapper.showPrintersByLocation();
            
            return null;
        
    }
    
    
    @GetMapping("/locations")
    public List<LocationDTO> getLocations() {
        
        List<LocationDTO> locations = locationService.getAllLocations();
        return locations;
    }
}
