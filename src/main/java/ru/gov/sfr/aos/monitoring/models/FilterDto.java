/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import ru.gov.sfr.aos.monitoring.dictionaries.Status;

/**
 *
 * @author Alikin Oleg
 */
public class FilterDto {
    public String yearCreatedOne;
    public String yearCreatedTwo;
    public String model;
    public String status;

    public FilterDto() {
    }

    public String getYearCreatedOne() {
        return yearCreatedOne;
    }

    public void setYearCreatedOne(String yearCreatedOne) {
        this.yearCreatedOne = yearCreatedOne;
    }

    public String getYearCreatedTwo() {
        return yearCreatedTwo;
    }

    public void setYearCreatedTwo(String yearCreatedTwo) {
        this.yearCreatedTwo = yearCreatedTwo;
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

    
    
    
}
