/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.dictionaries;

/**
 *
 * @author 041AlikinOS
 */
public enum Status {
    
    REPAIR("Ремонт"),
    MONITORING("Списание"),
    UTILIZATION("Утилизирован"),
    OK("Исправен"),
    DEFECTIVE("Неисправен");
    
    
    private String status;

    private Status(String status) {
        this.status = status;
    }



    public String getStatus() {
        return status;
    }
    
}
