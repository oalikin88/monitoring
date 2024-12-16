/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.dictionaries;

/**
 *
 * @author 041AlikinOS
 */
public enum ConditionerType {
    WINDOW("оконный"),
    WALL("настенный"),
    CEILING("потолочный"),
    FLOOR("напольный");
    
    private String conditionerType;
    
    private ConditionerType(String conditionerType) {
        this.conditionerType = conditionerType;
    }

    public String getConditionerTypeString() {
        return conditionerType;
    }
    
    
}
