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
    public Long id;
    public String model;
    public String type;
    public String resource;
    //Printers
    public List<Long> idModel;

    public CartridgeModelDTO() {
    }

    public CartridgeModelDTO(String model, String type, String resource) {
        this.model = model;
        this.type = type;
        this.resource = resource;
        
    }

    public CartridgeModelDTO(String model, String type, String resource, List<Long> idModel) {
        this.model = model;
        this.type = type;
        this.resource = resource;
        this.idModel = idModel;
    }

    public List<Long> getIdModel() {
        return idModel;
    }

    public void setIdModel(List<Long> idModel) {
        this.idModel = idModel;
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
  
    
}
