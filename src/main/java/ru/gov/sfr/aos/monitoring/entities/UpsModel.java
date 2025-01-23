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
import javax.validation.constraints.NotNull;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class UpsModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UpsManufacturer manufacturer;
    @NotNull(message = "Поле \"Тип батареи\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BatteryType batteryType;
    private int batteryAmount;
    @OneToMany(targetEntity = Ups.class, mappedBy = "upsModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ups> upsList = new HashSet<>();
    
    public UpsModel() {
    }

    public UpsModel(String model) {
        this.model = model;
    }


    public Set<Ups> getUpsList() {
        return upsList;
    }

    public void setUpsList(Set<Ups> upsList) {
        this.upsList = upsList;
    }

    public ManufacturerModel getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(UpsManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BatteryType getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(BatteryType batteryType) {
        this.batteryType = batteryType;
    }

    public int getBatteryAmount() {
        return batteryAmount;
    }

    public void setBatteryAmount(int batteryAmount) {
        this.batteryAmount = batteryAmount;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + Objects.hashCode(this.manufacturer);
        hash = 97 * hash + Objects.hashCode(this.batteryType);
        hash = 97 * hash + this.batteryAmount;
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
        final UpsModel other = (UpsModel) obj;
        
        if (this.batteryAmount != other.batteryAmount) {
            return false;
        }
        
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
        return "UpsModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
