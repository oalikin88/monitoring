/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class CartridgesDtoByModelAndLocation {
    public Long idModel;
    public Long location;
    public String itemCode;
    public String modelName;

    public CartridgesDtoByModelAndLocation() {
    }

    public CartridgesDtoByModelAndLocation(Long idModel, Long location, String itemCode, String modelName) {
        this.idModel = idModel;
        this.location = location;
        this.itemCode = itemCode;
        this.modelName = modelName;
    }

    public Long getIdModel() {
        return idModel;
    }

    public void setIdModel(Long idModel) {
        this.idModel = idModel;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    
    
}
