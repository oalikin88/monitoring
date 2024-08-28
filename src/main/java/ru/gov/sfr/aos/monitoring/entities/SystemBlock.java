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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "SYSTEM_BLOCK_ID")
public class SystemBlock extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SystemBlockModel systemBlockModel;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String inventaryNumber;
    private String serialNumber;
    private int yearCreated;
    private Date dateExploitationBegin;
    private String nameFromOneC;
    private Date dateUpgrade;
    private String numberRoom;
    private String ipAdress;
    @NotNull(message = "Поле \"Материнская плата\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Motherboard motherBoard;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cpu cpu;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ram ram;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Systemblock_Hdd", 
        joinColumns = { @JoinColumn(name = "SYSTEM_BLOCK_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "HDD_ID") }
    )
    private Set<Hdd> hdd = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private VideoCard videoCard;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CdDrive cdDrive;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SoundCard soundCard;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LanCard lanCard;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Keyboard keyboard;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Mouse mouse;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Speakers speakers;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Systemblock_OperationSystem", 
        joinColumns = { @JoinColumn(name = "SYSTEM_BLOCK_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "OPERATION_SYSTEM_ID") }
    )
    private Set<OperationSystem> operationSystems = new HashSet<>();
    
    
    public SystemBlock() {
    }
    
    

    public SystemBlock(SystemBlockModel systemBlockModel, Status status, String inventaryNumber, String serialNumber, int yearCreated, Date dateExploitationBegin, String nameFromOneC, Date dateUpgrade, String numberRoom, Motherboard motherBoard, Cpu cpu, Ram ram, Set<Hdd> hdd, VideoCard videoCard, CdDrive cdDrive, SoundCard soundCard, LanCard lanCard, Keyboard keyboard, Mouse mouse, Speakers speakers, Contract contract, boolean archived, String ipAdress, Set<OperationSystem> operationSystems) {
        super(status, inventaryNumber, serialNumber, contract);
        this.systemBlockModel = systemBlockModel;
        this.yearCreated = yearCreated;
        this.dateExploitationBegin = dateExploitationBegin;
        this.nameFromOneC = nameFromOneC;
        this.dateUpgrade = dateUpgrade;
        this.numberRoom = numberRoom;
        this.motherBoard = motherBoard;
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
        this.videoCard = videoCard;
        this.cdDrive = cdDrive;
        this.soundCard = soundCard;
        this.lanCard = lanCard;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.speakers = speakers;
        this.ipAdress = ipAdress;
        this.operationSystems = operationSystems;
    }

    public SystemBlockModel getSystemBlockModel() {
        return systemBlockModel;
    }

    public void setSystemBlockModel(SystemBlockModel systemBlockModel) {
        this.systemBlockModel = systemBlockModel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public Motherboard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(Motherboard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
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

    public VideoCard getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }

    public CdDrive getCdDrive() {
        return cdDrive;
    }

    public void setCdDrive(CdDrive cdDrive) {
        this.cdDrive = cdDrive;
    }

    public SoundCard getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
    }

    public LanCard getLanCard() {
        return lanCard;
    }

    public void setLanCard(LanCard lanCard) {
        this.lanCard = lanCard;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public Speakers getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Speakers speakers) {
        this.speakers = speakers;
    }

    public Set<OperationSystem> getOperationSystems() {
        return operationSystems;
    }

    public void setOperationSystems(Set<OperationSystem> operationSystems) {
        this.operationSystems = operationSystems;
    }

 
    
    
}
