/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author 041AlikinOS
 */

public class CartridgeFromInputDto {
    @NotBlank
    private String model;
    @NotBlank
    private String type;
    @NotBlank
    private String itemCode;
    @NotBlank
    private String nameMaterial;

    public CartridgeFromInputDto() {
    }

    public CartridgeFromInputDto(String model, String type, String itemCode, String nameMaterial) {
        this.model = model;
        this.type = type;
        this.itemCode = itemCode;
        this.nameMaterial = nameMaterial;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }
    
    
}
