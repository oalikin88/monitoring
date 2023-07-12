/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class ModelPrinterByModelCartridgeDTO {
    public Long idCartridge;
    public String manufacturer;
    public String modelPrinter;
    public String modelCartridge;
    

    public ModelPrinterByModelCartridgeDTO() {
    }

    public ModelPrinterByModelCartridgeDTO(String manufacturer, String modelPrinter, String modelCartridge, Long idCartridge) {
        this.manufacturer = manufacturer;
        this.modelPrinter = modelPrinter;
        this.modelCartridge = modelCartridge;
        this.idCartridge = idCartridge;
    }

    
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public Long getIdCartridge() {
        return idCartridge;
    }

    public void setIdCartridge(Long idCartridge) {
        this.idCartridge = idCartridge;
    }

    

   
    
    
    
}
