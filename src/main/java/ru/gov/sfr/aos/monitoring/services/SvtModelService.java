/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.entities.SvtModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.repositories.SvtModelsRepo;

/**
 *
 * @author 041AlikinOS
 */

public abstract class SvtModelService <E extends SvtModel, R extends SvtModelsRepo>  {

    @Autowired
    private R repository;



    public <E> List<E> getAllModels() {

        List<E> models = repository.findAll();
        return models;
    }

    public void saveModel(SvtModel e) throws ObjectAlreadyExists {
        
        List<? extends SvtModel> list = repository.findByModelIgnoreCase(e.getModel());
        if (list.isEmpty()) {
            repository.save(e);
        } else {
            if(e instanceof Ram) {
                boolean flag = false;
                for(Ram ram : (List<Ram>)list) {
                    if(((Ram) e).getCapacity() == ram.getCapacity()) {
                        flag = true;
                    }
                    
                }
                if(!flag) {
                    repository.save(e);
                } else {
                   throw new ObjectAlreadyExists("такая модель уже есть в базе данных"); 
                }
            } else {
                throw new ObjectAlreadyExists("такая модель уже есть в базе данных");
            }
            
        }
    }

}
