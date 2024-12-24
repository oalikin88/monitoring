/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.ManufacturerModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.repositories.ManufacturerModelRepo;

/**
 *
 * @author Alikin Oleg
 * @param <R>
 * @param <E>
 */

public abstract class ManufacturerService <R extends ManufacturerModelRepo, E extends ManufacturerModel> {
    
    
    @Autowired
    private R repository;
    
    
    public List<E> getAllManufacturers() {
        return repository.findAll();
    }
    
    public E getManufacturer(Long id) {
        return (E)repository.findById(id).get();
    }
    
    public E create(E e, String name) {
        
        e.setName(name.strip());
        return e;
    }
    
    public E save(E entity) throws ObjectAlreadyExists {
        String manufacturerClearNew = RegularOperation.getForCompareValue(entity.getName().strip().toLowerCase());
        List<E> listPontencialDublers = repository.findAll();
        if(listPontencialDublers.size() != 0) {
            for(E el : listPontencialDublers) {
                String manufacturerFromDBClear = RegularOperation.getForCompareValue(el.getName().strip().toLowerCase());
                if(manufacturerClearNew.equals(manufacturerFromDBClear)) {
                    throw new ObjectAlreadyExists("Производитель " + entity.getName() + " уже есть в базе данных");
                }
            }
        }
        E savedManufacturer = (E)repository.save(entity);
        return savedManufacturer;
        
    }
    
}
