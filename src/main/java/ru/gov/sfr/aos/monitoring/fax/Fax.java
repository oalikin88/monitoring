/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.fax;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventary;

/**
 *
 * @author Alikin Oleg
 */
@Entity
@PrimaryKeyJoinColumn(name = "FAX_ID")
public class Fax extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FaxModel model;
    private Date dateExploitationBegin;
    private int yearCreated;
    private String ipAdress;
    private String nameFromeOneC;
    private String numberRoom;

    public Fax() {
    }


    public FaxModel getModel() {
        return model;
    }

    public void setModel(FaxModel model) {
        this.model = model;
    }

    public Date getDateExploitationBegin() {
        return dateExploitationBegin;
    }

    public void setDateExploitationBegin(Date dateExploitationBegin) {
        this.dateExploitationBegin = dateExploitationBegin;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
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
        return "Fax{" + "id=" + this.id + ", serial=" + this.serialNumber + "inventary=" + this.inventaryNumber + ", model=" + model + ", dateExploitationBegin=" + dateExploitationBegin + ", yearCreated=" + yearCreated + ", ipAdress=" + ipAdress + ", nameFromeOneC=" + nameFromeOneC + ", numberRoom=" + numberRoom + '}';
    }
    
    
    
}
