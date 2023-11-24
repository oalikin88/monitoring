/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ru.gov.sfr.aos.monitoring;

/**
 *
 * @author 041AlikinOS
 */
public enum PrinterStatus {
 
    REPAIR("Ремонт"),
    MONITORING("Списание"),
    UTILIZATION("Утилизация"),
    OK("Исправен"),
    DEFECTIVE("Неисправен"),
    DELETE("Снят с учёта");
    
    
    private String status;

    private PrinterStatus(String status) {
        this.status = status;
    }



    public String getStatus() {
        return status;
    }

 
    
    
    
}
