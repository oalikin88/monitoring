/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.router;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventary;

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
    private int yearCreated;
    private String ipAdressInner;
    private String ipAdressOuter;

    public Router() {
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

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getIpAdressInner() {
        return ipAdressInner;
    }

    public void setIpAdressInner(String ipAdressInner) {
        this.ipAdressInner = ipAdressInner;
    }

    public String getIpAdressOuter() {
        return ipAdressOuter;
    }

    public void setIpAdressOuter(String ipAdressOuter) {
        this.ipAdressOuter = ipAdressOuter;
    }

    
    
    
    @Override
    public String toString() {
        return "Router{" + "id=" + this.id +  ", serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + 
                ", routerModel=" + routerModel + ", nameFromOneC=" + nameFromOneC + ", portAmount=" + 
                portAmount + ", numberRoom=" + numberRoom + ", ipAdressInner=" + this.ipAdressInner + ", ipAdressOuter" + this.ipAdressOuter + '}';
    }
    
    
    
}
