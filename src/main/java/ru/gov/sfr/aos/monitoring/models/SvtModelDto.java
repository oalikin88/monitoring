/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Objects;

/**
 *
 * @author 041AlikinOS
 */

public class SvtModelDto {
    private Long id;
    private String model;
    private Long manufacturerId;
    private String manufacturerName;

    public SvtModelDto() {
    }

    public SvtModelDto(String model) {
        this.model = model;
    }

    public SvtModelDto(String model, String manufacturerName) {
        this.model = model;
        this.manufacturerName = manufacturerName;
    }

    public SvtModelDto(Long id, String model) {
        this.id = id;
        this.model = model;
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

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        String result = "не указано";
        if(manufacturerName != null) {
            result = manufacturerName;
        }
        return result;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.model);
        hash = 37 * hash + Objects.hashCode(this.manufacturerId);
        hash = 37 * hash + Objects.hashCode(this.manufacturerName);
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
        final SvtModelDto other = (SvtModelDto) obj;
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.manufacturerName, other.manufacturerName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.manufacturerId, other.manufacturerId);
    }


    
    

    @Override
    public String toString() {
        return "SvtModelDto{" + "id=" + id + ", model=" + model + '}';
    }
    
    
    
}
