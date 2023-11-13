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

public class PrinterFromInputDto implements Serializable {
    @NotBlank
    private String manufacturer;
    @NotBlank
    private String model;
    @NotBlank
    private String serialNumber;
    @NotBlank
    private String inventoryNumber;
    private CartridgeIncludeFromInputDto cartridgeInclude;

    public PrinterFromInputDto() {
    }

    public PrinterFromInputDto(String manufacturer, String model, String serialNumber, String inventoryNumber, CartridgeIncludeFromInputDto cartridgeInclude) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.serialNumber = serialNumber;
        this.inventoryNumber = inventoryNumber;
        this.cartridgeInclude = cartridgeInclude;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public CartridgeIncludeFromInputDto getCartridgeInclude() {
        return cartridgeInclude;
    }

    public void setCartridgeInclude(CartridgeIncludeFromInputDto cartridgeInclude) {
        this.cartridgeInclude = cartridgeInclude;
    }
    
    

}