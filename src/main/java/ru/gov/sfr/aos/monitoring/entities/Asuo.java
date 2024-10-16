/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;


/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "ASUO_ID")
public class Asuo extends ObjectBuing implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Terminal terminal;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ThermoPrinter thermoPrinter;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SwitchingUnit switchingUnit;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Display display;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SubDisplayModel subDisplayModel;
    private int subDisplayAmount;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<SwitchHub> switchHubSet = new HashSet<>();
    private String nameFromOneC;
    private Date dateExploitationBegin;
    private int yearCreated;
    private String numberRoom;

    public Asuo() {
    }

  
    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public ThermoPrinter getThermoPrinter() {
        return thermoPrinter;
    }

    public void setThermoPrinter(ThermoPrinter thermoPrinter) {
        this.thermoPrinter = thermoPrinter;
    }

    public SwitchingUnit getSwitchingUnit() {
        return switchingUnit;
    }

    public void setSwitchingUnit(SwitchingUnit switchingUnit) {
        this.switchingUnit = switchingUnit;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Set<SwitchHub> getSwitchHubSet() {
        return switchHubSet;
    }

    public void setSwitchHubSet(Set<SwitchHub> switchHubSet) {
        this.switchHubSet = switchHubSet;
    }

    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
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

    public SubDisplayModel getSubDisplayModel() {
        return subDisplayModel;
    }

    public void setSubDisplayModel(SubDisplayModel subDisplayModel) {
        this.subDisplayModel = subDisplayModel;
    }

    public int getSubDisplayAmount() {
        return subDisplayAmount;
    }

    public void setSubDisplayAmount(int subDisplayAmount) {
        this.subDisplayAmount = subDisplayAmount;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    @Override
    public String toString() {
        return "Asuo{" + "terminal=" + terminal + ", thermoPrinter=" + thermoPrinter + ", switchingUnit=" + switchingUnit + ", display=" + display + ", subDisplayModel=" + subDisplayModel + ", subDisplayAmount=" + subDisplayAmount + ", switchHubSet=" + switchHubSet + ", nameFromOneC=" + nameFromOneC + ", dateExploitationBegin=" + dateExploitationBegin + ", yearCreated=" + yearCreated + ", numberRoom=" + numberRoom + '}';
    }
    
    
    
}
