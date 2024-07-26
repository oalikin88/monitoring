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

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "ROUTER_ID")
public class Router extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private RouterModel routerModel;
    private String nameFromOneC;
    private int portAmount;
    private String numberRoom;

    public Router() {
    }

    public Router(RouterModel routerModel, String nameFromOneC, int portAmount, String numberRoom, Status status, String inventaryNumber, String serialNumber, Contract contract, boolean archived) {
        super(status, inventaryNumber, serialNumber, contract, archived);
        this.routerModel = routerModel;
        this.nameFromOneC = nameFromOneC;
        this.portAmount = portAmount;
        this.numberRoom = numberRoom;
    }

    public RouterModel getRouterModel() {
        return routerModel;
    }

    public void setRouterModel(RouterModel routerModel) {
        this.routerModel = routerModel;
    }

    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public int getPortAmount() {
        return portAmount;
    }

    public void setPortAmount(int portAmount) {
        this.portAmount = portAmount;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }
    
    
    
}
