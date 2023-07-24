/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class ModelCartridgeByModelPrinters {
    public Long id;
    public String model;
    public List<ModelDTO> modelsPrinter;
    public List<Long> printersID;
    public List<Long> cartridgesId;
    public String location;
    public Long idLocation;

    public ModelCartridgeByModelPrinters() {
    }

    public ModelCartridgeByModelPrinters(Long id, String model, List<ModelDTO> modelsPrinter, List<Long> cartridgesId, String location, Long idLocation) {
        this.id = id;
        this.model = model;
        this.modelsPrinter = modelsPrinter;
        this.cartridgesId = cartridgesId;
        this.location = location;
        this.idLocation = idLocation;
    }

    public List<Long> getCartridgesId() {
        return cartridgesId;
    }

    public void setCartridgesId(List<Long> cartridgesId) {
        this.cartridgesId = cartridgesId;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ModelDTO> getModelsPrinter() {
        return modelsPrinter;
    }

    public void setModelsPrinter(List<ModelDTO> modelsPrinter) {
        this.modelsPrinter = modelsPrinter;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    public List<Long> getPrintersID() {
        return printersID;
    }

    public void setPrintersID(List<Long> printersID) {
        this.printersID = printersID;
    }
    
    
    
}
