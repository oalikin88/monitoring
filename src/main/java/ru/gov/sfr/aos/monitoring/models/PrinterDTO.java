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

public class PrinterDTO {
    public Long id;
    public String manufacturer;
    public String model;
    public String inventaryNumber;
    public String serialNumber;
    public String cartridge;
    public String location;
    
    public PrinterDTO() {};

    public PrinterDTO(Long id, String manufacturer, String model, String inventaryNumber, String serialNumber, 
            String cartridge, String location) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.inventaryNumber = inventaryNumber;
        this.serialNumber = serialNumber;
        this.cartridge = cartridge;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCartridge() {
        return cartridge;
    }

    public void setCartridge(String cartridge) {
        this.cartridge = cartridge;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
       
        final PrinterDTO other = (PrinterDTO) obj;
        
        return (manufacturer != null && manufacturer.equals(other.manufacturer)) && (model != null && model.equals(other.model));
        
    }


  

    @Override
    public String toString() {
        return "PrinterDTO{" + "manufacturer=" + manufacturer + ", model=" + model + ", inventaryNumber=" + inventaryNumber + ", serialNumber=" + serialNumber + ", cartridge=" + cartridge + ", location=" + location + '}';
    }
    
    
    
}
