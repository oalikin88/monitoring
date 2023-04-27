/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class ManufacturerDTO {
    public String manufacturer;
    public List<String> models = new ArrayList<>();

    public ManufacturerDTO() {
    }

    public ManufacturerDTO(String manufacturer, List<String> models) {
        this.manufacturer = manufacturer;
        this.models = models;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List <String> getModels() {
        return models;
    }

    public void setModels(List <String> models) {
        this.models = models;
    }
    
    
}
