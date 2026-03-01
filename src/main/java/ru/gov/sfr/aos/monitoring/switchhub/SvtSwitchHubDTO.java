/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.switchhub;

import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;

/**
 *
 * @author 041AlikinOS
 */

public class SvtSwitchHubDTO extends SvtDTO {
    public String switchHubType;
    public int portAmount;
    public boolean ignoreCheck;
    public String ipAdressInner;
    public String ipAdressOuter;

    public SvtSwitchHubDTO() {
    }

    
    public SvtSwitchHubDTO(String switchHubType, int portAmount) {
        this.switchHubType = switchHubType;
        this.portAmount = portAmount;
    }

    public String getSwitchHubType() {
        String result= "";
        if(null == switchHubType) {
            result = "не указан";
        } else {
            result = switchHubType;
        }
        return result;
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

    public boolean isIgnoreCheck() {
        return ignoreCheck;
    }

    public void setIgnoreCheck(boolean ignoreCheck) {
        this.ignoreCheck = ignoreCheck;
    }

    public String getIpAdressInner() {
        return ipAdressInner;
    }

    public void setIpAdressInner(String ipAdressInner) {
        this.ipAdressInner = ipAdressInner;
    }

    public String getIpAdressOuter() {
        return ipAdressOuter;
    }

    public void setIpAdressOuter(String ipAdressOuter) {
        this.ipAdressOuter = ipAdressOuter;
    }
    
    

    @Override
    public String toString() {
        return "SvtSwitchHubDTO{" + "id=" + this.getId() + ", serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + ", switchHubType=" + switchHubType + ", portAmount=" + portAmount + '}';
    }
    
    
}
