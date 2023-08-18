/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;

/**
 *
 * @author 041AlikinOS
 */
public class ContractForViewDTO {
    
    public Long id;
    public String contractNumber;
    public Date dateStartContract;
    public Date dateEndContract;
    public String items;

    public ContractForViewDTO() {
    }

    public ContractForViewDTO(Long id, String contractNumber, Date dateStartContract, Date dateEndContract, String items) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.dateStartContract = dateStartContract;
        this.dateEndContract = dateEndContract;
        this.items = items;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
    
    
    
    
    
}
