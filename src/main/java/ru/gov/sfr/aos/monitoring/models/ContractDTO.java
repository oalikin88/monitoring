/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;

/**
 *
 * @author 041AlikinOS
 */
public class ContractDTO {
    
   public String contractNumber;
   public Date dateStartContract;
   public Date dateEndContract;
   public String selectObjectBuing;
   public String manufacturer;
   public String modelPrinter;
   public String modelCartridge;
   public String serialNumberPrinter;
   public String inventoryNumberPrinter;
   public String typeCartridge;
   public String nominalResource;
   public String isSwitched;
   public String location;

    public ContractDTO() {
    }

    public ContractDTO(String contractNumber, Date dateStartContract, Date dateEndContract,
            String objectBuing, String manufacturer, String modelPrinter, String modelCartridge, String serialNumberPrinter,
            String inventoryNumberPrinter, String typeCartridge, String nominalResource, String isSwitched, String location) {
        
        this.contractNumber = contractNumber;
        this.dateStartContract = dateStartContract;
        this.dateEndContract = dateEndContract;
        this.selectObjectBuing = objectBuing;
        this.manufacturer = manufacturer;
        this.modelPrinter = modelPrinter;
        this.modelCartridge = modelCartridge;
        this.serialNumberPrinter = serialNumberPrinter;
        this.inventoryNumberPrinter = inventoryNumberPrinter;
        this.typeCartridge = typeCartridge;
        this.nominalResource = nominalResource;
        this.isSwitched = isSwitched;
        this.location = location;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getDateStartContract() {
        return dateStartContract;
    }

    public void setDateStartContract(Date dateStartContract) {
        this.dateStartContract = dateStartContract;
    }

    public Date getDateEndContract() {
        return dateEndContract;
    }

    public void setDateEndContract(Date dateEndContract) {
        this.dateEndContract = dateEndContract;
    }

    public String getSelectObjectBuing() {
        return selectObjectBuing;
    }

    public void setSelectObjectBuing(String selectObjectBuing) {
        this.selectObjectBuing = selectObjectBuing;
    }

    public String getModelPrinter() {
        return modelPrinter;
    }

    public void setModelPrinter(String modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    public String getModelCartridge() {
        return modelCartridge;
    }

    public void setModelCartridge(String modelCartridge) {
        this.modelCartridge = modelCartridge;
    }

    public String getSerialNumberPrinter() {
        return serialNumberPrinter;
    }

    public void setSerialNumberPrinter(String serialNumberPrinter) {
        this.serialNumberPrinter = serialNumberPrinter;
    }

    public String getInventoryNumberPrinter() {
        return inventoryNumberPrinter;
    }

    public void setInventoryNumberPrinter(String inventoryNumberPrinter) {
        this.inventoryNumberPrinter = inventoryNumberPrinter;
    }

    public String getTypeCartridge() {
        return typeCartridge;
    }

    public void setTypeCartridge(String typeCartridge) {
        this.typeCartridge = typeCartridge;
    }

    public String getNominalResource() {
        return nominalResource;
    }

    public void setNominalResource(String nominalResource) {
        this.nominalResource = nominalResource;
    }

    public String getIsSwitched() {
        return isSwitched;
    }

    public void setIsSwitched(String isSwitched) {
        this.isSwitched = isSwitched;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    
   
   
    
}
