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
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.dictionaries.SwitchHubType;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "SWITCH_HUB_ID")
public class SwitchHub extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SwitchHubModel switchHubModel;
    private SwitchHubType switchHubType;
    private int portAmount;
    private String numberRoom;
    private String nameFromOneC;

    public SwitchHub() {
    }

    public SwitchHub(SwitchHubModel switchHubModel, SwitchHubType switchHubType, int portAmount, String numberRoom, String nameFromOneC, Status status,
            String inventaryNumber, String serialNumber, Contract contract, boolean archived) {
        super(status, inventaryNumber, serialNumber, contract, archived);
        this.switchHubModel = switchHubModel;
        this.switchHubType = switchHubType;
        this.portAmount = portAmount;
        this.numberRoom = numberRoom;
        this.nameFromOneC = nameFromOneC;
    }

    public SwitchHubModel getSwitchHubModel() {
        return switchHubModel;
    }

    public void setSwitchHubModel(SwitchHubModel switchHubModel) {
        this.switchHubModel = switchHubModel;
    }

    public int getPortAmount() {
        return portAmount;
    }

    public void setPortAmount(int portAmount) {
        this.portAmount = portAmount;
    }

    public SwitchHubType getSwitchHubType() {
        return switchHubType;
    }

    public void setSwitchHubType(SwitchHubType switchHubType) {
        this.switchHubType = switchHubType;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }
    
    
    
}
