/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ups;

/**
 *
 * @author 041AlikinOS
 */
public class BatteryTypeDto {
    
    private Long id;
    private String type;

    public BatteryTypeDto(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BatteryTypeDto{" + "id=" + id + ", type=" + type + '}';
    }
    
    
    
}
