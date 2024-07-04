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
    
    public List<BatteryType> getAllBatteryTypes() {
        List<BatteryType> batteryTypes = batteryTypeRepo.findAll();
        return batteryTypes;
    }
    
    public void saveBatteryType(BatteryType batteryType) throws ObjectAlreadyExists {
        List<BatteryType> batteryTypes = batteryTypeRepo.findByTypeIgnoreCase(batteryType.getType());
        if(batteryTypes.isEmpty()) {
            batteryTypeRepo.save(batteryType);
        } else {
            throw new ObjectAlreadyExists("Такой тип батареи ИБП уже есть в базе данных");
        }
    }
     
}
