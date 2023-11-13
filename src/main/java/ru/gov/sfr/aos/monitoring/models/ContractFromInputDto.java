/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author 041AlikinOS
 */

public class ContractFromInputDto implements Serializable {
    @NotEmpty(message = "поле не может быть пустым")
    private String contractNumber;
    @NotBlank
    private Date dateStartContract;
    @NotBlank
    private Date dateEndContract;
    
    private List<PrinterFromInputDto> printers;
    private List<CartridgeFromInputDto> cartridges;

    public ContractFromInputDto() {
    }

    public ContractFromInputDto(String contractNumber, Date dateStartContract, Date dateEndContract, List<PrinterFromInputDto> printers, List<CartridgeFromInputDto> cartridges) {
        this.contractNumber = contractNumber;
        this.dateStartContract = dateStartContract;
        this.dateEndContract = dateEndContract;
        this.printers = printers;
        this.cartridges = cartridges;
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

    public List<PrinterFromInputDto> getPrinters() {
        return printers;
    }

    public void setPrinters(List<PrinterFromInputDto> printers) {
        this.printers = printers;
    }

    public List<CartridgeFromInputDto> getCartridges() {
        return cartridges;
    }

    public void setCartridges(List<CartridgeFromInputDto> cartridges) {
        this.cartridges = cartridges;
    }
    
    
    
}
