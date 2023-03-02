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
   public String objectBuing;
   public String modelPrinter;
   public String modelCartridge;
   public String serialNumberPrinter;
   public String inventoryNumberPrinter;
   public String typeCartridge;
   public String nominalResource;
   public String isSwitched;

    public ContractDTO() {
    }

    public ContractDTO(String contractNumber, Date dateStartContract, Date dateEndContract,
            String objectBuing, String modelPrinter, String modelCartridge, String serialNumberPrinter,
            String inventoryNumberPrinter, String typeCartridge, String nominalResource, String isSwitched) {
        this.contractNumber = contractNumber;
        this.dateStartContract = dateStartContract;
        this.dateEndContract = dateEndContract;
        this.objectBuing = objectBuing;
        this.modelPrinter = modelPrinter;
        this.modelCartridge = modelCartridge;
        this.serialNumberPrinter = serialNumberPrinter;
        this.inventoryNumberPrinter = inventoryNumberPrinter;
        this.typeCartridge = typeCartridge;
        this.nominalResource = nominalResource;
        this.isSwitched = isSwitched;
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

    public String getObjectBuing() {
        return objectBuing;
    }

    public void setObjectBuing(String objectBuing) {
        this.objectBuing = objectBuing;
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
   
   
    
}
