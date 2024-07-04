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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.enums.BaseType;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "MONITOR_ID")
public class Monitor extends ObjectBuingWithSerialAndInventary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MonitorModel monitorModel;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int yearCreated;
    private Date dateExploitationBegin;
    private String nameFromeOneC;
    @Enumerated(EnumType.STRING)
    private BaseType baseType;

    public Monitor() {
    }

    public Monitor(MonitorModel monitorModel, Status status, String inventaryNumber, String serialNumber, 
            int yearCreated, Date dateExploitationBegin, String nameFromeOneC, BaseType baseType, Contract contract, boolean archived) {
        super(status, inventaryNumber, serialNumber, contract, archived);
        this.monitorModel = monitorModel;
        this.dateExploitationBegin = dateExploitationBegin;
        this.nameFromeOneC = nameFromeOneC;
        this.baseType = baseType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MonitorModel getMonitorModel() {
        return monitorModel;
    }

    public void setMonitorModel(MonitorModel monitorModel) {
        this.monitorModel = monitorModel;
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

    public Date getDateExploitationBegin() {
        return dateExploitationBegin;
    }

    public void setDateExploitationBegin(Date dateExploitationBegin) {
        this.dateExploitationBegin = dateExploitationBegin;
    }

    public String getNameFromeOneC() {
        return nameFromeOneC;
    }

    public void setNameFromeOneC(String nameFromeOneC) {
        this.nameFromeOneC = nameFromeOneC;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public BaseType getBaseType() {
        return baseType;
    }

    public void setBaseType(BaseType baseType) {
        this.baseType = baseType;
    }
    
    
}
