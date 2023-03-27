/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.services.ManufacturerMapper;
import ru.gov.sfr.aos.monitoring.services.ManufacturerServiceImpl;

/**
 *
 * @author 041AlikinOS
 */
@RestController
public class ManufacturersController {
    
    
    @Autowired
    private ManufacturerMapper mapper;
        
    @RequestMapping(value = "/manufacturers", method = RequestMethod.GET)
    public List<ManufacturerDTO> showPrinters() {
        
        List<ManufacturerDTO> manufacturers = mapper.getDto();
        
        
        return manufacturers;
        
}
    
}
