/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class ChangePrinterInventaryNumberDTO {
    
    public Long id;
    public String inventaryNumber;

    public ChangePrinterInventaryNumberDTO() {
    }

    public ChangePrinterInventaryNumberDTO(Long id, String inventaryNumber) {
        this.id = id;
        this.inventaryNumber = inventaryNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }
    
    
    
}
