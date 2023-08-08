/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class InventoriesByLocationDTO {
    
    public String idLocation;
    public String idModel;

    public InventoriesByLocationDTO() {
    }

    public InventoriesByLocationDTO(String idLocation, String idModel) {
        this.idLocation = idLocation;
        this.idModel = idModel;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdModel() {
        return idModel;
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }
    
     
    
}
