/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */

public class SvtSwitchHubDTO extends SvtDTO {
    public String switchHubType;
    public int portAmount;

    public SvtSwitchHubDTO() {
    }

    
    public SvtSwitchHubDTO(String switchHubType, int portAmount) {
        this.switchHubType = switchHubType;
        this.portAmount = portAmount;
    }

    public String getSwitchHubType() {
        return switchHubType;
    }

    public void setSwitchHubType(String switchHubType) {
        this.switchHubType = switchHubType;
    }

    public int getPortAmount() {
        return portAmount;
    }

    public void setPortAmount(int portAmount) {
        this.portAmount = portAmount;
    }

    @Override
    public String toString() {
        return "SvtSwitchHubDTO{" + "id=" + this.getId() + ", serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + ", switchHubType=" + switchHubType + ", portAmount=" + portAmount + '}';
    }
    
    
}
