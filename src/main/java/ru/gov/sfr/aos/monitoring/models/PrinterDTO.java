/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;
import java.util.List;

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
    public List<CartridgeDTO> cartridge;
    public Long locationId;
    public String location;
    public String contractNumber;
    public Date startContract;
    public Date endContract;
    public Long contractId;
    public String printerStatus;
    
    public PrinterDTO() {};

    public PrinterDTO(Long id, String manufacturer, String model, String inventaryNumber, String serialNumber, 
            List<CartridgeDTO> cartridge, String location) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.inventaryNumber = inventaryNumber;
        this.serialNumber = serialNumber;
        this.cartridge = cartridge;
        this.location = location;
    }
    
        public PrinterDTO(Long id, String manufacturer, String model, String inventaryNumber, String serialNumber, 
            List<CartridgeDTO> cartridge, String location, String contractNumber, Date startContract, Date endContract) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.inventaryNumber = inventaryNumber;
        this.serialNumber = serialNumber;
        this.cartridge = cartridge;
        this.location = location;
        this.contractNumber = contractNumber;
        this.startContract = startContract;
        this.endContract = endContract;
    }

    public PrinterDTO(Long id, String manufacturer, String model, String inventaryNumber, String serialNumber, List<CartridgeDTO> cartridge, String location, String contractNumber, Date startContract, Date endContract, Long contractId, String printerStatus) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.inventaryNumber = inventaryNumber;
        this.serialNumber = serialNumber;
        this.cartridge = cartridge;
        this.location = location;
        this.contractNumber = contractNumber;
        this.startContract = startContract;
        this.endContract = endContract;
        this.contractId = contractId;
        this.printerStatus = printerStatus;
    }
        
        
     

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
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

    public List<CartridgeDTO> getCartridge() {
        return cartridge;
    }

    public void setCartridge(List<CartridgeDTO> cartridge) {
        this.cartridge = cartridge;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getStartContract() {
        return startContract;
    }

    public void setStartContract(Date startContract) {
        this.startContract = startContract;
    }

    public Date getEndContract() {
        return endContract;
    }

    public void setEndContract(Date endContract) {
        this.endContract = endContract;
    }

    public String getPrinterStatus() {
        return printerStatus;
    }

    public void setPrinterStatus(String printerStatus) {
        this.printerStatus = printerStatus;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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
