/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ups;

import ru.gov.sfr.aos.monitoring.ups.UpsModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventary;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "UPS_ID")
public class Ups extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UpsModel upsModel;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String inventaryNumber;
    private String serialNumber;
    private int yearCreated;
    private int yearReplacement;
    
    private Date dateExploitationBegin;
    private String numberRoom;
    private String nameFromOneC;

    public Ups() {
    }


    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    
    public UpsModel getUpsModel() {
        return upsModel;
    }

    public void setUpsModel(UpsModel upsModel) {
        this.upsModel = upsModel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public int getYearReplacement() {
        return yearReplacement;
    }

    public void setYearReplacement(int yearReplacement) {
        this.yearReplacement = yearReplacement;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Date getDateExploitationBegin() {
        return dateExploitationBegin;
    }

    public void setDateExploitationBegin(Date dateExploitationBegin) {
        this.dateExploitationBegin = dateExploitationBegin;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    @Override
    public String toString() {
        return "Ups{" + "id=" + this.id + ", upsModel=" + upsModel + ", status=" + status + ", inventaryNumber=" + inventaryNumber + ", serialNumber=" + serialNumber + ", yearCreated=" + yearCreated + ", yearReplacement=" + yearReplacement + ", batteryType=" + ", dateExploitationBegin=" + dateExploitationBegin + ", numberRoom=" + numberRoom + ", nameFromOneC=" + nameFromOneC + '}';
    }
    
    
}
