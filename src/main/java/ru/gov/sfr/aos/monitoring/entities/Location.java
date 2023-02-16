/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(targetEntity = Printer.class, mappedBy = "location", fetch = FetchType.LAZY)
    private List <Printer> printer = new ArrayList<>();
    @OneToMany(targetEntity = Cartridge.class, mappedBy = "location", fetch = FetchType.LAZY)
    private List <Cartridge> cartridge = new ArrayList<>();

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

    public List<Printer> getPrinters() {
        return printer;
    }

    public void setPrinters(List<Printer> printer) {
        this.printer = printer;
    }

    public List<Cartridge> getCartridges() {
        return cartridge;
    }

    public void setCartridges(List<Cartridge> cartridge) {
        this.cartridge = cartridge;
    }
    
    

    public Location() {
    }

    public Location(String name, List<Printer> printer, List<Cartridge> cartridge) {
        this.name = name;
        this.printer = printer;
        this.cartridge = cartridge;
    }
    
    
    
    
    
}
