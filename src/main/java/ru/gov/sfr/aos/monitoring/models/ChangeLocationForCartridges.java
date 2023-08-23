/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class ChangeLocationForCartridges {
    
    public Long location;
    public List<Long> idCartridge;

    public ChangeLocationForCartridges() {
    }

    public ChangeLocationForCartridges(Long location, List<Long> idCartridge) {
        this.location = location;
        this.idCartridge = idCartridge;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public List<Long> getIdCartridge() {
        return idCartridge;
    }

    public void setIdCartridge(List<Long> idCartridge) {
        this.idCartridge = idCartridge;
    }
    
    
    
}
