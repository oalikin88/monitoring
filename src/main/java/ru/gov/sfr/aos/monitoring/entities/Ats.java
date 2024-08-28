/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.dictionaries.OuterConnectionType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "ATS_ID")
public class Ats extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AtsModel atsModel;
    private String nameFromOneC;
    private String numberRoom;
    private int cityNumberAmount;
    private OuterConnectionType outerConnectionType;
    private int innerConnectionIp;
    private int innerConnectionAnalog; 
    private int yearCreated;

    public Ats() {
    }

    public Ats(AtsModel atsModel, String nameFromOneC, String numberRoom, int cityNumberAmount, OuterConnectionType outerConnectionType, int innerConnectionIp, int innerConnectionAnalog, int yearCreated, Status status, String inventaryNumber, String serialNumber, Contract contract, boolean archived) {
        super(status, inventaryNumber, serialNumber, contract);
        this.atsModel = atsModel;
        this.nameFromOneC = nameFromOneC;
        this.numberRoom = numberRoom;
        this.cityNumberAmount = cityNumberAmount;
        this.outerConnectionType = outerConnectionType;
        this.innerConnectionIp = innerConnectionIp;
        this.innerConnectionAnalog = innerConnectionAnalog;
        this.yearCreated = yearCreated;
    }

    

    public AtsModel getAtsModel() {
        return atsModel;
    }

    public void setAtsModel(AtsModel atsModel) {
        this.atsModel = atsModel;
    }

    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public int getCityNumberAmount() {
        return cityNumberAmount;
    }

    public void setCityNumberAmount(int cityNumberAmount) {
        this.cityNumberAmount = cityNumberAmount;
    }

    public OuterConnectionType getOuterConnectionType() {
        return outerConnectionType;
    }

    public void setOuterConnectionType(OuterConnectionType outerConnectionType) {
        this.outerConnectionType = outerConnectionType;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public int getInnerConnectionIp() {
        return innerConnectionIp;
    }

    public void setInnerConnectionIp(int innerConnectionIp) {
        this.innerConnectionIp = innerConnectionIp;
    }

    public int getInnerConnectionAnalog() {
        return innerConnectionAnalog;
    }

    public void setInnerConnectionAnalog(int innerConnectionAnalog) {
        this.innerConnectionAnalog = innerConnectionAnalog;
    }
    
    
    
}
