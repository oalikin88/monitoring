/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
    
    public List<OperationSystem> getOperationSystemBySystemblock(Long idSysBlock) {
        return operationSystemRepo.findBySystemBlocksId(idSysBlock);
    }
    
    public List<OperationSystem> getAllOperationSystem() {
        return operationSystemRepo.findAll();
    }
    
    public OperationSystem getOperationSystem(Long id) {
        return operationSystemRepo.findById(id).get();
    }
    public Set<OperationSystem> getOperationSystemsFromDtoes(List<Long> operationSystemsFromDtoes) {
        return operationSystemRepo.findAllById(operationSystemsFromDtoes).stream().collect(Collectors.toSet());
    }
    
    public void saveOperationSystem(OperationSystem operationSystem) throws ObjectAlreadyExists {
        if(operationSystemRepo.existsByModelIgnoreCase(operationSystem.getModel())) {
            throw new ObjectAlreadyExists("Операционная система с названием " + operationSystem.getModel() + " уже есть в базе данных");
        } else {
            operationSystemRepo.save(operationSystem);
        }
    }
    
    public void updateOperationSystem(OperationSystem operationSystem) throws ObjectAlreadyExists {
        OperationSystem osFromDB = operationSystemRepo.findById(operationSystem.getId()).get();
        if (operationSystemRepo.existsByModelIgnoreCase(operationSystem.getModel()) && !operationSystem.getModel().equals(osFromDB.getModel())) {
          throw new ObjectAlreadyExists("Операционная система с названием " + operationSystem.getModel() + " уже есть в базе данных");
        }
        osFromDB.setModel(operationSystem.getModel());
        osFromDB.setLicense(operationSystem.isLicense());
        operationSystemRepo.save(osFromDB);

    }
    
    public void sendOsToArchive(Long id) throws ObjectAlreadyExists {
        if(operationSystemRepo.existsById(id)) {
            OperationSystem os = operationSystemRepo.findById(id).get();
            os.setArchived(true);
        } else {
            throw new ObjectAlreadyExists("Операционной системы с id:" + id + ", нет в базе данных");
        }
    }
}
