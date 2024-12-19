/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author Alikin Oleg
 */
public class UpsModelDto {
    private Long id;
    private Long manufacturerId;
    private String manufacturer;
    private String model;
    private Long batteryTypeId;
    private String batteryType;
    private int batteryAmount;

    public UpsModelDto() {
    }

    public UpsModelDto(Long id, Long manufacturerId, String manufacturer, String model, Long batteryTypeId, String batteryType, int batteryAmount) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.batteryTypeId = batteryTypeId;
        this.batteryType = batteryType;
        this.batteryAmount = batteryAmount;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getBatteryTypeId() {
        return batteryTypeId;
    }

    public void setBatteryTypeId(Long batteryTypeId) {
        this.batteryTypeId = batteryTypeId;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public int getBatteryAmount() {
        return batteryAmount;
    }

    public void setBatteryAmount(int batteryAmount) {
        this.batteryAmount = batteryAmount;
    }
    
    
}
