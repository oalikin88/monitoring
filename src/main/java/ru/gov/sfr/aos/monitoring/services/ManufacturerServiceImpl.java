/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.interfaces.ManufacturerServiceInterface;
import ru.gov.sfr.aos.monitoring.repositories.ManufacturerRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class ManufacturerServiceImpl implements ManufacturerServiceInterface {
    
    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Override
    public List<Manufacturer> getManufacturers() {
        
        return manufacturerRepo.findAll();
        
    }

    @Override
    public List<Manufacturer> getManufacturerNames(String name) {
        List<Manufacturer> findByNameList = manufacturerRepo.findByNameContainingIgnoreCase(name);
        return findByNameList;
    }
    
}
