/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author 041AlikinOS
 */
public class ManufacturerDTO <E> {
    public Long id;
    public String name;
    public Set<E> models = new HashSet<E>();

    public ManufacturerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<E> getModels() {
        return models;
    }

    public void setModels(Set<E> models) {
        this.models = models;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ManufacturerDTO<?> other = (ManufacturerDTO<?>) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    
    


    @Override
    public String toString() {
        return "ManufacturerDTO{" + "manufacturer=" + name + ", models=" + models + '}';
    }
    
    
}
