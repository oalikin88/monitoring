/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.BatteryType;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.repositories.BatteryTypeRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class BatteryTypeService {
    @Autowired
    private BatteryTypeRepo batteryTypeRepo;
    
    public BatteryType getBatteryType(Long id) {
        return batteryTypeRepo.findById(id).get();
    }
    
    public List<BatteryType> getAllBatteryTypes() {
        List<BatteryType> batteryTypes = batteryTypeRepo.findAll();
        return batteryTypes;
    }
    
    
    public List<BatteryType> getAllActualBatteryTypes() {
        List<BatteryType> batteryTypes = batteryTypeRepo.findByArchivedFalse();
        return batteryTypes;
    }
    
    public void saveBatteryType(BatteryType batteryType) throws ObjectAlreadyExists {
        
        List<BatteryType> batteryTypes = batteryTypeRepo.findByTypeIgnoreCase(batteryType.getType().strip());
        if(batteryTypes.isEmpty()) {
            batteryTypeRepo.save(batteryType);
        } else {
            throw new ObjectAlreadyExists("Такой тип батареи ИБП уже есть в базе данных");
        }
    }
    
    public void sendToArchive(Long id) throws ObjectAlreadyExists {
        if(batteryTypeRepo.existsById(id)) {
            BatteryType batType = batteryTypeRepo.findById(id).get();
            batType.setArchived(true);
            batteryTypeRepo.save(batType);
        } else {
            throw new ObjectAlreadyExists("Тип батареи с id:" + id + ", отсутствует в базе данных.");
        }
    }
    
    
    public void update(BatteryType batteryType) throws ObjectAlreadyExists {
        if(batteryTypeRepo.existsById(batteryType.getId())) {
            if(batteryTypeRepo.existsByTypeIgnoreCase(batteryType.getType().trim())) {
                throw new ObjectAlreadyExists(batteryType.getType() + " уже есть в базе данных.");
            }
            batteryTypeRepo.save(batteryType);
        } else {
            throw new ObjectAlreadyExists("Тип батареи с id:" + batteryType.getId() + ", отсутствует в базе данных." );
        }
    }
     
}
