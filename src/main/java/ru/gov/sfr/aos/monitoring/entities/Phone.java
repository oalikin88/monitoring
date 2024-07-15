/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "PHONE_ID")
public class Phone extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PhoneModel phoneModel;
    private int yearCreated;
    private Date dateExploitationBegin;
    private String phoneNumber;
    private String nameFromeOneC;

     public Phone() {
    }
 

    public Phone(PhoneModel phoneModel, Status status, String inventaryNumber,
            String serialNumber, int yearCreated, Date dateExploitationBegin, String phoneNumber, String nameFromeOneC, Contract contract, boolean archived) {
        super(status, inventaryNumber, serialNumber, contract, archived);
        this.phoneModel = phoneModel;
        this.status = status;
        this.inventaryNumber = inventaryNumber;
        this.serialNumber = serialNumber;
        this.yearCreated = yearCreated;
        this.dateExploitationBegin = dateExploitationBegin;
        this.phoneNumber = phoneNumber;
        this.nameFromeOneC = nameFromeOneC;
    }
    
    

    public PhoneModel getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(PhoneModel phoneModel) {
        this.phoneModel = phoneModel;
    }
    
   

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public Date getDateExploitationBegin() {
        return dateExploitationBegin;
    }

    public void setDateExploitationBegin(Date dateExploitationBegin) {
        this.dateExploitationBegin = dateExploitationBegin;
    }
    @Override
    public Place getPlace() {
        return place;
    }
    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getNameFromeOneC() {
        return nameFromeOneC;
    }

    public void setNameFromeOneC(String nameFromeOneC) {
        this.nameFromeOneC = nameFromeOneC;
    }
    
    
    
}
