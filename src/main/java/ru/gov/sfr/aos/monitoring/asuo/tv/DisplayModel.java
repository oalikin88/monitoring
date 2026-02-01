/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo.tv;

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
public class DisplayModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private DisplayManufacturer manufacturer;
    @OneToMany(targetEntity = Display.class, mappedBy = "displayModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Display> displays = new HashSet<>();

    public DisplayModel() {
    }

    public DisplayManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(DisplayManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    
    
    public Set<Display> getDisplays() {
        return displays;
    }

    public void setDisplays(Set<Display> displays) {
        this.displays = displays;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.manufacturer);
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
        final DisplayModel other = (DisplayModel) obj;
        return Objects.equals(this.manufacturer, other.manufacturer);
    }

    @Override
    public String toString() {
        return "DisplayModel{" + "manufacturer=" + manufacturer + ", displays=" + displays + '}';
    }
    
   
}
