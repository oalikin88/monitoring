/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class CartridgeDTO {
    
    public String type;
    public String model;
    public String modelValue;
    public String resource;

    public CartridgeDTO() {
    }

    public CartridgeDTO(String type, String model, String modelValue, String resource) {
        this.type = type;
        this.model = model;
        this.modelValue = modelValue;
        this.resource = resource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getModelValue() {
        return modelValue;
    }

    public void setModelValue(String modelValue) {
        this.modelValue = modelValue;
    }
    
    
    
}
