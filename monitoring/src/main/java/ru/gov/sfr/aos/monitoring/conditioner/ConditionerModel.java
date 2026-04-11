/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.conditioner;

import ru.gov.sfr.aos.monitoring.conditioner.ConditionerManufacturer;
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
public class ConditionerModel extends SvtModel {
     
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private ConditionerManufacturer manufacturer;
    @OneToMany(targetEntity = Conditioner.class, mappedBy = "conditionerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Conditioner> conditioners = new HashSet<>();

    public ConditionerModel() {
    }


    public Set<Conditioner> getConditioners() {
        return conditioners;
    }

    public void setConditioners(Set<Conditioner> conditioners) {
        this.conditioners = conditioners;
    }

    public ConditionerManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ConditionerManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + Objects.hashCode(this.manufacturer);
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
        final ConditionerModel other = (ConditionerModel) obj;
        
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
        return "ConditionerModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
    
    
}
