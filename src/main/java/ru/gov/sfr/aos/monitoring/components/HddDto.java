/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author 041AlikinOS
 */
public class HddDto extends SvtModelDto {
    private int capacity;
    private String unit;
    private String serialNumber;
    private String inventaryNumber;

    public HddDto() {
    }
    
    public HddDto(int capacity, String unit, String serialNumber, String inventaryNumber, String model) {
        super(model);
        this.capacity = capacity;
        this.unit = unit;
        this.serialNumber = serialNumber;
        this.inventaryNumber = inventaryNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
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

    @Override
    public String toString() {
        return "HddDto{" + "id=" + this.getId() + ", model=" + this.getModel() + ", capacity=" + capacity + ", unit=" + unit + ", serialNumber=" + serialNumber + ", inventaryNumber=" + inventaryNumber + '}';
    }
    
    
    
}
