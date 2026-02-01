/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author Alikin Oleg
 */
public class FilterDto {
    public String location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isEmpty() {
    	
    	return model == null &&
    			status == null &&
    			yearCreatedOne == null &&
    			yearCreatedTwo == null;
    	
    }
    
    
}
