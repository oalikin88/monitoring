/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.OperationSystem;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.repositories.OperationSystemRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class OperationSystemService {
    @Autowired
    private OperationSystemRepo operationSystemRepo;
    
    public List<OperationSystem> getAllOperationSystem() {
        return operationSystemRepo.findAll();
    }
    
    public OperationSystem getOperationSystem(Long id) {
        return operationSystemRepo.findById(id).get();
    }
    
    public void saveOperationSystem(OperationSystem operationSystem) throws ObjectAlreadyExists {
        if(operationSystemRepo.existsByNameIgnoreCase(operationSystem.getName())) {
            throw new ObjectAlreadyExists("Операционная система с названием " + operationSystem.getName() + " уже есть в базе данных");
        } else {
            operationSystemRepo.save(operationSystem);
        }
    }
}
