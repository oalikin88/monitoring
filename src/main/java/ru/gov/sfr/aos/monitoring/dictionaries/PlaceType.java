/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.dictionaries;

/**
 *
 * @author 041AlikinOS
 */
public enum PlaceType {
    SERVERROOM("Серверная"),
    OFFICEEQUIPMENT("Оргтехника"),
    STORAGE("Склад"),
    EMPLOYEE("Сотрудник");
    
    private String type;
    
    private PlaceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
