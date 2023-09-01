/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.time.LocalDate;

/**
 *
 * @author 041AlikinOS
 */

public class PlaningResultDTO {

    public LocalDate date;
    public Long cartridgeModelId;
    public Long locationId;
    public int amountAllCartridges;
    public int amountCurrentModelCartridges;

    public PlaningResultDTO() {
    }

    public PlaningResultDTO(LocalDate date, Long cartridgeModelId, Long locationId, int amountAllCartridges, int amountCurrentModelCartridges) {
        this.date = date;
        this.cartridgeModelId = cartridgeModelId;
        this.locationId = locationId;
        this.amountAllCartridges = amountAllCartridges;
        this.amountCurrentModelCartridges = amountCurrentModelCartridges;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getCartridgeModelId() {
        return cartridgeModelId;
    }

    public void setCartridgeModelId(Long cartridgeModelId) {
        this.cartridgeModelId = cartridgeModelId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public int getAmountAllCartridges() {
        return amountAllCartridges;
    }

    public void setAmountAllCartridges(int amountAllCartridges) {
        this.amountAllCartridges = amountAllCartridges;
    }

    public int getAmountCurrentModelCartridges() {
        return amountCurrentModelCartridges;
    }

    public void setAmountCurrentModelCartridges(int amountCurrentModelCartridges) {
        this.amountCurrentModelCartridges = amountCurrentModelCartridges;
    }
    
    
    
    
}
