/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import ru.gov.sfr.aos.monitoring.enums.UnitHdd;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;
import ru.gov.sfr.aos.monitoring.systemblock.SystemBlock;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class Hdd extends SvtModel {

    private int capacity;
    @Enumerated(EnumType.STRING)
    private UnitHdd unit;
    private String serialNumber;
    private String inventaryNumber;
    @ManyToMany(mappedBy = "hdd")
    private Set<SystemBlock> systemblocks = new HashSet<>();
 
    



    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public UnitHdd getUnit() {
        return unit;
    }

    public void setUnit(UnitHdd unit) {
        this.unit = unit;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public Set<SystemBlock> getSystemblocks() {
        return systemblocks;
    }

    public void setSystemblocks(Set<SystemBlock> systemblocks) {
        this.systemblocks = systemblocks;
    }

    @Override
    public String toString() {
        return "Hdd{" + "id=" + this.id + ", serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + ", capacity=" + capacity + ", unit=" + unit + ", serialNumber=" + serialNumber + ", inventaryNumber=" + inventaryNumber + ", systemblocks=" + systemblocks + '}';
    }
    
    
    
}
