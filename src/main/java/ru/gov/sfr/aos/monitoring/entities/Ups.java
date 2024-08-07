/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

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
    @NotNull(message = "Поле \"Тип батареи\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BatteryType batteryType;
    private int batteryAmount;
    private Date dateExploitationBegin;
    private String numberRoom;
    private String nameFromOneC;

    public Ups() {
    }

    public Ups(UpsModel upsModel, Status status, String inventaryNumber, String serialNumber, int yearCreated,
            int yearReplacement, BatteryType batteryType, int batteryAmount, Date dateExploitationBegin, Contract contract, boolean archived) {
        super(status, inventaryNumber, serialNumber, contract, archived);
        this.upsModel = upsModel;
        this.yearReplacement = yearReplacement;
        this.batteryType = batteryType;
        this.batteryAmount = batteryAmount;
        this.dateExploitationBegin = dateExploitationBegin;
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

    public BatteryType getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(BatteryType batteryType) {
        this.batteryType = batteryType;
    }

    public int getBatteryAmount() {
        return batteryAmount;
    }

    public void setBatteryAmount(int batteryAmount) {
        this.batteryAmount = batteryAmount;
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
    
    
}
