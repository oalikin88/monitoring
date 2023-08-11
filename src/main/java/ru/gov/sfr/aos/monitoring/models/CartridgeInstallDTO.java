/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class CartridgeInstallDTO {
    
    public Long idPrinter;
    public Long idCartridge;
    public Long count;

    public CartridgeInstallDTO(Long idPrinter, Long idCartridge, Long count) {
        this.idPrinter = idPrinter;
        this.idCartridge = idCartridge;
        this.count = count;
    }

    public Long getIdPrinter() {
        return idPrinter;
    }

    public void setIdPrinter(Long idPrinter) {
        this.idPrinter = idPrinter;
    }

    public Long getIdCartridge() {
        return idCartridge;
    }

    public void setIdCartridge(Long idCartridge) {
        this.idCartridge = idCartridge;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    
    
    
}
