/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.PrinterStatus;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "PRINTER_ID")
public class Printer extends ObjectBuing implements Serializable {

    
    
    @NotNull(message = "Поле \"Производитель\" не может быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Manufacturer manufacturer;
    @NotEmpty(message = "Поле \"Серийный номер\" не может быть пустым")
    @Column(unique = true)
    protected String serialNumber; 
    @NotEmpty(message = "Поле \"Инвентарный номер\" не может быть пустым")
    @Column(unique=true)
    protected String inventoryNumber;
    
    @OneToMany(mappedBy = "printer", fetch = FetchType.LAZY)
    protected Set<Cartridge> cartridge;
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Model model;
    @NotNull
    @Enumerated(EnumType.STRING)
    protected PrinterStatus printerStatus;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected NameFromOneC nameFromOneC;
   
    
    public Printer() {
    }

    public Printer(Manufacturer manufacturer, String serialNumber, String inventoryNumber, 
            Set<Cartridge> cartridge, Model model, PrinterStatus printerStatus, NameFromOneC nameFromOneC) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.inventoryNumber = inventoryNumber;
        this.cartridge = cartridge;
        this.manufacturer = manufacturer;
        this.printerStatus = printerStatus;
        this.nameFromOneC = nameFromOneC;
    }  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<Cartridge> getCartridge() {
        return cartridge;
    }

    public void setCartridge(Set<Cartridge> cartridge) {
        this.cartridge = cartridge;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public PrinterStatus getPrinterStatus() {
        return printerStatus;
    }

    public void setPrinterStatus(PrinterStatus printerStatus) {
        this.printerStatus = printerStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public NameFromOneC getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(NameFromOneC nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    
    

    @Override
    public String toString() {
        return "Printer [ID: " + super.id + "; Models: " + this.manufacturer.getModelList() + "; Serial: " + this.serialNumber + 
                "; Invertary number: " + this.inventoryNumber + "; Location: " + this.location + "; Cartridge: " + this.cartridge + "; NameFrom1C: " + this.nameFromOneC.getName() + "]";
    }
    
    
}
