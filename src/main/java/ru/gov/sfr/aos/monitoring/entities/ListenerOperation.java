/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author 041AlikinOS
 */

@Entity
public class ListenerOperation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOperation;
    private String currentOperation;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cartridge cartridge;
    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;
    private boolean util;

    public ListenerOperation() {
    }

    public ListenerOperation(LocalDate dateOperation, String operation, Cartridge cartridge, Location location, boolean util) {
        this.dateOperation = dateOperation;
        this.currentOperation = operation;
        this.cartridge = cartridge;
        this.location = location;
        this.util = util;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation = dateOperation;
    }


    
    public String getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(String operation) {
        this.currentOperation = operation;
    }

    public Cartridge getCartridge() {
        return cartridge;
    }

    public void setCartridge(Cartridge cartridge) {
        this.cartridge = cartridge;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public boolean isUtil() {
        return util;
    }

    public void setUtil(boolean util) {
        this.util = util;
    }
    
    

    
    
}
