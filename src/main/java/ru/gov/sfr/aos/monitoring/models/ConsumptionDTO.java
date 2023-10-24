/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class ConsumptionDTO {
    
    public CartridgeModelDTO model;
    public LocationDTO location;
    public int consumption;
    public long period;
    public int incoming;
    public int balance;

    public ConsumptionDTO() {
    }

    public ConsumptionDTO(CartridgeModelDTO model, LocationDTO location, int consumption, long period, int incoming, int balance) {
        this.model = model;
        this.location = location;
        this.consumption = consumption;
        this.period = period;
        this.incoming = incoming;
        this.balance = balance;
    }

    public CartridgeModelDTO getModel() {
        return model;
    }

    public void setModel(CartridgeModelDTO model) {
        this.model = model;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public int getIncoming() {
        return incoming;
    }

    public void setIncoming(int incoming) {
        this.incoming = incoming;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
    
    
}
