/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;


/**
 *
 * @author 041AlikinOS
 */
public class ModelDTO implements Serializable {
    
    public Long idModel;
    @NotEmpty(message = "Поле \"Модель\" не может быть пустым")
    public String model;
    @NotEmpty(message = "Поле \"Производитель\" не может быть пустым")
    public String manufacturer;
    @NotEmpty(message = "Поле \"Цветность печати\" не может быть пустым")
    public String printColorType;
    @NotEmpty(message = "Поле \"Формат печати\" не может быть пустым")
    public String printFormatType;
    @NotEmpty(message = "Поле \"Скорость печати\" не может быть пустым")
    public String printSpeed;
    @NotEmpty(message = "Поле \"Тип оборудования\" не может быть пустым")
    public String deviceType;
    
    
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

    public ModelDTO(Long idModel, String model, String manufacturer, String printColorType, String printFormatType, String printSpeed, String deviceType) {
        this.idModel = idModel;
        this.model = model;
        this.manufacturer = manufacturer;
        this.printColorType = printColorType;
        this.printFormatType = printFormatType;
        this.printSpeed = printSpeed;
        this.deviceType = deviceType;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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
