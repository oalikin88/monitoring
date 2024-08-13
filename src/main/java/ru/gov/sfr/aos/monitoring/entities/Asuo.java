/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;


/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "ASUO_ID")
public class Asuo extends ObjectBuingWithSerialAndInventary implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Terminal terminal;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ThermoPrinter thermoPrinter;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SwitchingUnit switchingUnit;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Display display;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MainDisplay mainDisplay;
    private int displayAmount;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SwitchHub switchHub;
    private String nameFromOneC;
    private Date dateExploitationBegin;
    private int yearCreated;

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

    public MainDisplay getMainDisplay() {
        return mainDisplay;
    }

    public void setMainDisplay(MainDisplay mainDisplay) {
        this.mainDisplay = mainDisplay;
    }

    public int getDisplayAmount() {
        return displayAmount;
    }

    public void setDisplayAmount(int displayAmount) {
        this.displayAmount = displayAmount;
    }

    public SwitchHub getSwitchHub() {
        return switchHub;
    }

    public void setSwitchHub(SwitchHub switchHub) {
        this.switchHub = switchHub;
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
    
    
}
