/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.systemblock;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class SystemBlockModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private SystemBlockManufacturer manufacturer;
    @OneToMany(targetEntity = SystemBlock.class, mappedBy = "systemBlockModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SystemBlock> systemBlocks = new HashSet<>();

    public SystemBlockModel() {
    }

    
    public SystemBlockModel(String model) {
        this.model = model;
    }

    

    public Set<SystemBlock> getSystemBlocks() {
        return systemBlocks;
    }

    public void setSystemBlocks(Set<SystemBlock> systemBlocks) {
        this.systemBlocks = systemBlocks;
    }

    public SystemBlockManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(SystemBlockManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 41 * hash + Objects.hashCode(this.manufacturer);
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
        final SystemBlockModel other = (SystemBlockModel) obj;
        
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        
        if (!Objects.equals(this.archived, other.archived)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        
        return Objects.equals(this.manufacturer, other.manufacturer);
    }
    
    
    
        @Override
    public String toString() {
        return "SystemBlockModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
