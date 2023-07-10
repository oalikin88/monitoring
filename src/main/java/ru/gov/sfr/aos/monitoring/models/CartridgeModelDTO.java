/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class CartridgeModelDTO implements Serializable {
    
    public String model;
    public String type;
    public String resource;
    public List<String> modelPrinters;

    public CartridgeModelDTO() {
    }

    public CartridgeModelDTO(String model, String type, String resource, List<String> modelPrinters) {
        this.model = model;
        this.type = type;
        this.resource = resource;
        this.modelPrinters = modelPrinters;
    }
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public List<String> getModelPrinters() {
        return modelPrinters;
    }

    public void setModelPrinters(List<String> modelPrinters) {
        this.modelPrinters = modelPrinters;
    }
  
    
}
