/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ru.gov.sfr.aos.monitoring;

/**
 *
 * @author 041AlikinOS
 */
public enum CartridgeType {
    
    ORIGINAL("Оригинальный"), 
    ANALOG("Совместимый"),
    START("Стартовый");
    
    private String name;

    private CartridgeType(String name) {
            this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
