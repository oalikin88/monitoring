/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author oalikin88
 */
@Setter
@Getter
public class FilterRequest {
    private String username;
    private String inventaryNumber;
    private String serialNumber;
    private String location;
    private String model;
    private String status;
    private String numberRoom;
    private String yearCreatedFrom;
    private String yearCreatedTo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getYearCreatedFrom() {
        return yearCreatedFrom;
    }

    public void setYearCreatedFrom(String yearCreatedFrom) {
        this.yearCreatedFrom = yearCreatedFrom;
    }

    public String getYearCreatedTo() {
        return yearCreatedTo;
    }

    public void setYearCreatedTo(String yearCreatedTo) {
        this.yearCreatedTo = yearCreatedTo;
    }
    
    
    
}
