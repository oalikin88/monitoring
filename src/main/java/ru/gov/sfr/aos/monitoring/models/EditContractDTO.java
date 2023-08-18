/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author 041AlikinOS
 */
public class EditContractDTO {
    public Long id;
    public String contractNumber;
    public Date dateStartContract;
    public Date dateEndContract;
    public Set<CartridgeDTO> cartridges;
    public Set<PrinterDTO> printers;

    public EditContractDTO() {
    }

    public EditContractDTO(Long id, String contractNumber, Date dateStartContract, Date dateEndContract, Set<CartridgeDTO> cartridges, Set<PrinterDTO> printers) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.dateStartContract = dateStartContract;
        this.dateEndContract = dateEndContract;
        this.cartridges = cartridges;
        this.printers = printers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getDateStartContract() {
        return dateStartContract;
    }

    public void setDateStartContract(Date dateStartContract) {
        this.dateStartContract = dateStartContract;
    }

    public Date getDateEndContract() {
        return dateEndContract;
    }

    public void setDateEndContract(Date dateEndContract) {
        this.dateEndContract = dateEndContract;
    }

    public Set<CartridgeDTO> getCartridges() {
        return cartridges;
    }

    public void setCartridges(Set<CartridgeDTO> cartridges) {
        this.cartridges = cartridges;
    }

    public Set<PrinterDTO> getPrinters() {
        return printers;
    }

    public void setPrinters(Set<PrinterDTO> printers) {
        this.printers = printers;
    }
    
    
    
}
