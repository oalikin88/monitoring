/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    public Set<String> printers;

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

    public CartridgeModelDTO(String model, String type, String resource, List<Long> idModel, Set<String> printers) {
        this.model = model;
        this.type = type;
        this.resource = resource;
        this.idModel = idModel;
        this.printers = printers;
    }
    
    

    public Set<String> getPrinters() {
        return printers;
    }

    public void setPrinters(Set<String> printers) {
        this.printers = printers;
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartridgeModelDTO other = (CartridgeModelDTO) obj;
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.resource, other.resource)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idModel, other.idModel)) {
            return false;
        }
        return Objects.equals(this.printers, other.printers);
    }
  
    
}
