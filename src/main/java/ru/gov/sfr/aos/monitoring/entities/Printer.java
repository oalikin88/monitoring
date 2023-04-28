/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "PRINTER_ID")
public class Printer extends ObjectBuing {

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    protected Location location;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    protected Manufacturer manufacturer;
    @NotNull
    protected String serialNumber; 
    @NotNull
    protected String inventoryNumber;
    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Cartridge.class, mappedBy = "printer", cascade = CascadeType.ALL)
    protected List<ObjectBuing> cartridge;
    
    @ManyToOne(cascade = CascadeType.ALL)
    protected Model model;
   
    
    public Printer() {
    }

    public Printer(Location location, Manufacturer manufacturer, String serialNumber, String inventoryNumber, 
            List<ObjectBuing> cartridge, Model model) {
        this.location = location;
        this.model = model;
        this.serialNumber = serialNumber;
        this.inventoryNumber = inventoryNumber;
        this.cartridge = cartridge;
        this.manufacturer = manufacturer;
        
    }  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<ObjectBuing> getCartridge() {
        return cartridge;
    }

    public void setCartridge(List<ObjectBuing> cartridge) {
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
    
    

    @Override
    public String toString() {
        return "Printer [ID: " + super.id + "; Models: " + this.manufacturer.getModelList() + "; Serial: " + this.serialNumber + 
                "; Invertary number: " + this.inventoryNumber + "; Location: " + this.location + "; Cartridge: " + this.cartridge + "]";
    }
    
    
    
    
}
