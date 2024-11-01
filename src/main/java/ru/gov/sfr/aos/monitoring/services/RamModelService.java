/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.repositories.RamRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class RamModelService extends SvtModelService<Ram, RamRepo> {

    @Autowired
    private RamRepo ramRepo;
  
    public void update(Ram e) throws ObjectAlreadyExists {
        Ram ram = ramRepo.findById(e.getId()).get();
        ram.setCapacity(e.getCapacity());
        ram.setModel(e.getModel());
        ramRepo.save(ram);
    }
    
}
