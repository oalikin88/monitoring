/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.place;

/**
 *
 * @author 041AlikinOS
 */
public class PlaceStatusDto {
    private String value;
    private String label;

    public PlaceStatusDto() {
    }

    public PlaceStatusDto(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "PlaceStatusDto{" + "value=" + value + ", label=" + label + '}';
    }
    
    
    
}
