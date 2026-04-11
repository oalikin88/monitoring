package ru.gov.sfr.aos.monitoring.models;

import ru.gov.sfr.aos.monitoring.enums.ObjectBuingType;

/**
 *
 * @author Alikin Oleg
 */

public class DeviceDto {
    private Long id;
    private ObjectBuingType deviceType;
    private String model;
    private String inventaryNumber;

    public DeviceDto() {
    }

    public DeviceDto(Long id, ObjectBuingType deviceType, String model, String inventaryNumber) {
        this.id = id;
        this.deviceType = deviceType;
        this.model = model;
        this.inventaryNumber = inventaryNumber;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectBuingType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(ObjectBuingType deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }
    
    

}
