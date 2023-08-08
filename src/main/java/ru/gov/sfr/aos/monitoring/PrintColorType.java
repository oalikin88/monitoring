/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring;

/**
 *
 * @author 041AlikinOS
 */
public enum PrintColorType {
    COLOR("Цветной"),
    BLACKANDWHITE("Чернобелый");
            
    private String type;
    
    private PrintColorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    
}
