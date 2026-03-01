/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.conditioner;

import ru.gov.sfr.aos.monitoring.conditioner.ConditionerModel;
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
@PrimaryKeyJoinColumn(name = "CONDITIONER_ID")
public class Conditioner extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ConditionerModel conditionerModel;
    private String nameFromOneC;
    private String description;
    private String numberRoom;
    private double price;
    private int yearCreated;
    private ConditionerType conditionerType;
    private boolean splitSystem;
    private boolean winterKit;
    private boolean havePomp;

    public Conditioner() {
    }

    public ConditionerModel getConditionerModel() {
        return conditionerModel;
    }

    public void setConditionerModel(ConditionerModel conditionerModel) {
        this.conditionerModel = conditionerModel;
    }

    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public ConditionerType getConditionerType() {
        return conditionerType;
    }

    public void setConditionerType(ConditionerType conditionerType) {
        this.conditionerType = conditionerType;
    }

    public boolean isSplitSystem() {
        return splitSystem;
    }

    public void setSplitSystem(boolean splitSystem) {
        this.splitSystem = splitSystem;
    }

    public boolean isWinterKit() {
        return winterKit;
    }

    public void setWinterKit(boolean winterKit) {
        this.winterKit = winterKit;
    }

    public boolean isHavePomp() {
        return havePomp;
    }

    public void setHavePomp(boolean havePomp) {
        this.havePomp = havePomp;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    @Override
    public String toString() {
        return "Conditioner{" + "id=" + this.id + ", serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + ", conditionerModel=" + conditionerModel + ", nameFromOneC=" + nameFromOneC + ", description=" + description + ", numberRoom=" + numberRoom + ", price=" + price + ", yearCreated=" + yearCreated + ", conditionerType=" + conditionerType + ", splitSystem=" + splitSystem + ", winterKit=" + winterKit + ", havePomp=" + havePomp + '}';
    }
    
    
}
