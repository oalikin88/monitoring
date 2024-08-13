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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "SERVER_ID")
public class Server extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ServerModel serverModel;
    private int yearCreated;
    private Date dateExploitationBegin;
    private String nameFromOneC;
    private Date dateUpgrade;
    private String numberRoom;
    private String ipAdress;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cpu cpu;
    private int cpuAmount;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ram ram;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Server_Hdd", 
        joinColumns = { @JoinColumn(name = "SERVER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "HDD_ID") }
    )
    private Set<Hdd> hdd = new HashSet<>();
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Server_OperationSystem", 
        joinColumns = { @JoinColumn(name = "SERVER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "OPERATION_SYSTEM_ID") }
    )
    private Set<OperationSystem> operationSystems = new HashSet<>();

    public Server() {
    }

    public ServerModel getServerModel() {
        return serverModel;
    }

    public void setServerModel(ServerModel serverModel) {
        this.serverModel = serverModel;
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

    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public Date getDateUpgrade() {
        return dateUpgrade;
    }

    public void setDateUpgrade(Date dateUpgrade) {
        this.dateUpgrade = dateUpgrade;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public int getCpuAmount() {
        return cpuAmount;
    }

    public void setCpuAmount(int cpuAmount) {
        this.cpuAmount = cpuAmount;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Set<Hdd> getHdd() {
        return hdd;
    }

    public void setHdd(Set<Hdd> hdd) {
        this.hdd = hdd;
    }

    public Set<OperationSystem> getOperationSystems() {
        return operationSystems;
    }

    public void setOperationSystems(Set<OperationSystem> operationSystems) {
        this.operationSystems = operationSystems;
    }

    
    
}
