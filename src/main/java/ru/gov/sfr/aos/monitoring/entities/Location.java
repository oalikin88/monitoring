/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "LOCATION_ID")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(targetEntity = Printer.class, mappedBy = "location", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set <Printer> printer = new HashSet<>();
    @OneToMany(targetEntity = Cartridge.class, mappedBy = "location", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set <Cartridge> cartridge = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location(String name) {
        this.name = name;
    }

    public Set<Printer> getPrinters() {
        return printer;
    }

    public void setPrinters(Set<Printer> printer) {
        this.printer = printer;
    }

    public Set<Cartridge> getCartridges() {
        return cartridge;
    }

    public void setCartridges(Set<Cartridge> cartridge) {
        this.cartridge = cartridge;
    }
    
    

    public Location() {
    }

    public Location(String name, Set<Printer> printer, Set<Cartridge> cartridge) {
        this.name = name;
        this.printer = printer;
        this.cartridge = cartridge;
    }

    @Override
    public String toString() {
        return  "Area: " + this.name;
    }
    
    
    
    
    
}
