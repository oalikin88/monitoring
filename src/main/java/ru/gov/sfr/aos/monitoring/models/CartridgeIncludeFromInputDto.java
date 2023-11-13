/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author 041AlikinOS
 */
public class CartridgeIncludeFromInputDto implements Serializable {
    @NotBlank
    private String type;
    @NotBlank
    private String model;

    public CartridgeIncludeFromInputDto() {
    }

    public CartridgeIncludeFromInputDto(String type, String model) {
        this.type = type;
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    
}
