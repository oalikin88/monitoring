/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.repositories.ManufacturerRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class ManufacturerMapper {
    
    @Autowired
    private ManufacturerRepo manufacturerRepo;
    
    public List<ManufacturerDTO> getDto() {
        
        List<Manufacturer> manufacturersList = manufacturerRepo.findAll();
        List<ManufacturerDTO> manufactureList = new ArrayList<>();
        for(Manufacturer m : manufacturersList) {
            ManufacturerDTO manufacturerDTO = new ManufacturerDTO(m.getName(), m.getModel());
            manufactureList.add(manufacturerDTO);
        }
        return manufactureList;
    }
    
}
