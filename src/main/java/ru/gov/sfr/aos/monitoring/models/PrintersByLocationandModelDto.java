/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Set;

/**
 *
 * @author 041AlikinOS
 */
public class PrintersByLocationandModelDto {
    public Long idModel;
    public String modelName;
    public int amountPrinters;
    public String locationName;
    public Long idLocation;

    public PrintersByLocationandModelDto() {
    }

    public PrintersByLocationandModelDto(Long idModel, String modelName, int amountPrinters, String locationName, Long idLocation) {
        this.idModel = idModel;
        this.modelName = modelName;
        this.amountPrinters = amountPrinters;
        this.locationName = locationName;
        this.idLocation = idLocation;
    }

    public Long getIdModel() {
        return idModel;
    }

    public void setIdModel(Long idModel) {
        this.idModel = idModel;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    public int getAmountPrinters() {
        return amountPrinters;
    }

    public void setAmountPrinters(int amountPrinters) {
        this.amountPrinters = amountPrinters;
    }
    
    
}
