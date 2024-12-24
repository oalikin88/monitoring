/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class ManufacturerDTO <E> {
    public Long id;
    public String name;
    public List<E> models = new ArrayList<>();

    public ManufacturerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<E> getModels() {
        return models;
    }

    public void setModels(List<E> models) {
        this.models = models;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    


    @Override
    public String toString() {
        return "ManufacturerDTO{" + "manufacturer=" + name + ", models=" + models + '}';
    }
    
    
}
