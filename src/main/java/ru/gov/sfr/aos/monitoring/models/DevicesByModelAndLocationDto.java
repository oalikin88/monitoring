/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class DevicesByModelAndLocationDto {
    public Long idPrinter;
    public Long location;
    public String contractNumber;
    

    public DevicesByModelAndLocationDto(Long idPrinter, Long location, String contractNumber) {
        this.idPrinter = idPrinter;
        this.location = location;
        this.contractNumber = contractNumber;
    }

    public DevicesByModelAndLocationDto() {
    }

    
    
    public Long getIdPrinter() {
        return idPrinter;
    }

    public void setIdPrinter(Long idPrinter) {
        this.idPrinter = idPrinter;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    
    
}
