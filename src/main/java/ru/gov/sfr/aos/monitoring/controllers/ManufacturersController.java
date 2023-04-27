/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.services.ManufacturersMapper;


/**
 *
 * @author 041AlikinOS
 */
@RestController
public class ManufacturersController {
    
    
    @Autowired
    private ManufacturersMapper mapper;
        
    @RequestMapping(value = "/manufacturers", method = RequestMethod.GET)
    public List<ManufacturerDTO> showPrinters() {
        
        List<ManufacturerDTO> list = mapper.showManufacturers();
        
       return list;
        
       
        
}
    
}
