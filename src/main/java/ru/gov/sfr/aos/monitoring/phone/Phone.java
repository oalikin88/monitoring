/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.phone;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventary;

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
    private String numberRoom;

     public Phone() {
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
    @Override
    public Status getStatus() {
        return status;
    }
    @Override
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

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    
    
    @Override
    public String toString() {
        return "Phone{" + "id=" +this.id + "serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + ", phoneModel=" + phoneModel + ", yearCreated=" + yearCreated + ", dateExploitationBegin=" + dateExploitationBegin + ", phoneNumber=" + phoneNumber + ", nameFromeOneC=" + nameFromeOneC + '}';
    }
    
    
    
}
