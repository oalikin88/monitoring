/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class PhoneModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private PhoneManufacturer manufacturer;
    @OneToMany(targetEntity = Phone.class, mappedBy = "phoneModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Phone> phones = new HashSet<>();

    public PhoneModel() {
    }

    public PhoneModel(String model) {
        this.model = model;
    }
    

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public PhoneManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(PhoneManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    
    
        @Override
    public String toString() {
        return "PhoneModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 31 * hash + Objects.hashCode(this.manufacturer);
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
        final PhoneModel other = (PhoneModel) obj;
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
    
    
    
    
}
