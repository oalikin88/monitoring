/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.UpsManufacturer;
import ru.gov.sfr.aos.monitoring.repositories.UpsManufacturerRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class UpsManufacturerService {
    @Autowired
    private UpsManufacturerRepo upsManufacturerRepo;
    
    public List<UpsManufacturer> getAllManufacturers() {
        return upsManufacturerRepo.findAll();
    }
    
    public UpsManufacturer getManufacturer(Long id) {
        return upsManufacturerRepo.findById(id).get();
    }
    
    public UpsManufacturer create(String name) {
        UpsManufacturer manufacturer = new UpsManufacturer();
        manufacturer.setName(name.strip());
        return manufacturer;
    }
    
    public UpsManufacturer save(UpsManufacturer entity) {
        String manufacturerClearNew = RegularOperation.getForCompareValue(entity.getName().strip().toLowerCase());
        List<UpsManufacturer> listPontencialDublers = upsManufacturerRepo.findAll();
        if(listPontencialDublers.size() != 0) {
            for(UpsManufacturer el : listPontencialDublers) {
                String manufacturerFromDBClear = RegularOperation.getForCompareValue(el.getName().strip().toLowerCase());
                if(manufacturerClearNew.equals(manufacturerFromDBClear)) {
                    throw new ObjectAccessException("Производитель " + entity.getName() + " уже есть в базе данных");
                }
            }
        }
        UpsManufacturer savedManufacturer = upsManufacturerRepo.save(entity);
        return savedManufacturer;
        
    }
}
