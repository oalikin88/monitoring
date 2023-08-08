/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;


/**
 *
 * @author 041AlikinOS
 */
public class ModelDTO implements Serializable {
    
    public Long idModel;
    public String model;
    public String manufacturer;
    public String printColorType;
    public String printFormatType;
    public String printSpeed;

    public ModelDTO() {
    }
    
    public ModelDTO(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public ModelDTO(Long idModel, String model, String manufacturer) {
        this.idModel = idModel;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public ModelDTO(Long idModel, String model, String manufacturer, String printColorType, String printFormatType, String printSpeed) {
        this.idModel = idModel;
        this.model = model;
        this.manufacturer = manufacturer;
        this.printColorType = printColorType;
        this.printFormatType = printFormatType;
        this.printSpeed = printSpeed;
    }

    public String getPrintSpeed() {
        return printSpeed;
    }

    public void setPrintSpeed(String printSpeed) {
        this.printSpeed = printSpeed;
    }

    
    
    public String getPrintColorType() {
        return printColorType;
    }

    public void setPrintColorType(String printColorType) {
        this.printColorType = printColorType;
    }

    public String getPrintFormatType() {
        return printFormatType;
    }

    public void setPrintFormatType(String printFormatType) {
        this.printFormatType = printFormatType;
    }
    
     

    public Long getIdModel() {
        return idModel;
    }

    public void setIdModel(Long idModel) {
        this.idModel = idModel;
    }
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return "";
        }
    }
    
    
}
