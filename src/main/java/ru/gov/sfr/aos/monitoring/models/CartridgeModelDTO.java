/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 041AlikinOS
 */
public class CartridgeModelDTO implements Serializable {
    public Long id;
    @NotEmpty (message = "Поле \"модель картриджа\" не может быть пустым")
    public String model;
    @NotEmpty (message = "Поле \"производитель\" не может быть пустым")
    public String manufacturer;
    @NotEmpty(message = "Поле \"тип картриджа\" не может быть пустым")
    public String type;
    @NotEmpty(message = "Поле \"Номинальный ресурс\" не может быть пустым")
    public String resource;
    public Long idManufacturer;
    //Printers
    @NotNull
    public List<Long> idModel;
    
    public Set<String> printers;

    public CartridgeModelDTO() {
    }

    public CartridgeModelDTO(String manufacturer, String model, String type, String resource) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.resource = resource;
        
    }

    public CartridgeModelDTO(String manufacturer, String model, String type, String resource, List<Long> idModel) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.resource = resource;
        this.idModel = idModel;
    }

    public CartridgeModelDTO(String manufacturer, String model, String type, String resource, Long idManufacturer, List<Long> idModel, Set<String> printers) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.resource = resource;
        this.idManufacturer = idManufacturer;
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

    public Long getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(Long idManufacturer) {
        this.idManufacturer = idManufacturer;
    }
    

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
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
