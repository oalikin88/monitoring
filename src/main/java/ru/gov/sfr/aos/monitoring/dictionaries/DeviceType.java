/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.dictionaries;

/**
 *
 * @author 041AlikinOS
 */
public enum DeviceType {
    PRINTER("Принтер"),
    MFU("МФУ");
    
    private String value;
    private DeviceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    
}
