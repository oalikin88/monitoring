/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo;

import ru.gov.sfr.aos.monitoring.asuo.subdisplay.SubDisplayModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.asuo.hub.Hub;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import ru.gov.sfr.aos.monitoring.asuo.terminal.Terminal;
import ru.gov.sfr.aos.monitoring.asuo.tv.Display;


/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "ASUO_ID")
public class Asuo extends ObjectBuing implements Serializable {
    private String inventaryNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "asuo_terminal", 
        joinColumns = { @JoinColumn(name = "ASUO_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "TERMINAL_ID") }
    )
    private List <Terminal> terminal = new ArrayList<>();
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "asuo_display", 
        joinColumns = { @JoinColumn(name = "ASUO_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "DISPLAY_ID") }
    )
    private Set <Display> display = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProgramSoftware programSoftware;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SubDisplayModel subDisplayModel;
    private int subDisplayAmount;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "asuo_hub", 
        joinColumns = { @JoinColumn(name = "ASUO_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "HUB_ID") }
    )
    private Set<Hub> hubSet = new HashSet<>();
    private String nameFromOneC;
    private Date dateExploitationBegin;
    private int yearCreated;

    public Asuo() {
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    
    
    public List<Terminal> getTerminal() {
        return terminal;
    }

    public void setTerminal(List<Terminal> terminal) {
        this.terminal = terminal;
    }

    public Set<Display> getDisplay() {
        return display;
    }

    public void setDisplay(Set<Display> display) {
        this.display = display;
    }

    public void addTerminal(Terminal terminal) {
        this.terminal.add(terminal);
    }
    
    public void addDisplay(Display display) {
        this.display.add(display);
    }
    

    public Set<Hub> getHubSet() {
        return hubSet;
    }

    public void setHubSet(Set<Hub> hubSet) {
        this.hubSet = hubSet;
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


    public ProgramSoftware getProgramSoftware() {
        return programSoftware;
    }

    public void setProgramSoftware(ProgramSoftware programSoftware) {
        this.programSoftware = programSoftware;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.terminal);
        hash = 13 * hash + Objects.hashCode(this.display);
        hash = 13 * hash + Objects.hashCode(this.programSoftware);
        hash = 13 * hash + Objects.hashCode(this.subDisplayModel);
        hash = 13 * hash + this.subDisplayAmount;
        hash = 13 * hash + Objects.hashCode(this.hubSet);
        hash = 13 * hash + Objects.hashCode(this.nameFromOneC);
        hash = 13 * hash + Objects.hashCode(this.dateExploitationBegin);
        hash = 13 * hash + this.yearCreated;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asuo other = (Asuo) obj;
        if (this.subDisplayAmount != other.subDisplayAmount) {
            return false;
        }
        if (this.yearCreated != other.yearCreated) {
            return false;
        }
        if (!Objects.equals(this.nameFromOneC, other.nameFromOneC)) {
            return false;
        }
        if (!Objects.equals(this.terminal, other.terminal)) {
            return false;
        }

        if (!Objects.equals(this.display, other.display)) {
            return false;
        }
        if (!Objects.equals(this.programSoftware, other.programSoftware)) {
            return false;
        }
        if (!Objects.equals(this.subDisplayModel, other.subDisplayModel)) {
            return false;
        }
        if (!Objects.equals(this.hubSet, other.hubSet)) {
            return false;
        }
        return Objects.equals(this.dateExploitationBegin, other.dateExploitationBegin);
    }

    @Override
    public String toString() {
        return "Asuo{" + "terminal=" + terminal +  ", display=" + display + ", programSoftware=" + programSoftware + ", subDisplayModel=" + subDisplayModel + ", subDisplayAmount=" + subDisplayAmount + ", hubSet=" + hubSet + ", nameFromOneC=" + nameFromOneC + ", dateExploitationBegin=" + dateExploitationBegin + ", yearCreated=" + yearCreated + '}';
    }
    
    
    

    
    
}
