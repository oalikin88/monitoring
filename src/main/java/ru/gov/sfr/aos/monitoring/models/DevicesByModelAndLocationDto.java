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
    public Long idModel;
    public Long location;
    public String contractNumber;
    

    public DevicesByModelAndLocationDto(Long idPrinter, Long location, String contractNumber) {
        this.idModel = idPrinter;
        this.location = location;
        this.contractNumber = contractNumber;
    }

    public DevicesByModelAndLocationDto() {
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

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    
    
}
