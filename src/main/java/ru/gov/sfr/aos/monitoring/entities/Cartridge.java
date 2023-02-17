/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.CartridgeType;

/**
 *
 * @author 041AlikinOS
 */

@Entity
@PrimaryKeyJoinColumn(name = "CARTRIDGE_ID")
public class Cartridge extends ObjectBuing {
    @NotNull
    protected String model;
    @NotNull
    @Enumerated(EnumType.STRING)
    protected CartridgeType type;
    @NotNull
    @ManyToOne
    protected Location location;
    @NotNull
    protected LocalDateTime dateStartExploitation;
    @NotNull
    protected LocalDateTime dateEndExploitation;
    @NotNull
    protected boolean util;
    @NotNull
    protected int count;
    @NotNull
    protected int defaultNumberPrintPage;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRINTER_ID",
			foreignKey = @ForeignKey(name = "PRINTER_ID_FK"))
    protected Printer printer;

    
    
    public Cartridge() {
        
    }
   
    public Cartridge(String model, CartridgeType type, Location location, LocalDateTime dateStartExploitation, 
            LocalDateTime dateEndExploitation, boolean util, int count, int defaultNumberPrintPage) {
        this.model = model;
        this.type = type;
        this.location = location;
        this.dateStartExploitation = dateStartExploitation;
        this.dateEndExploitation = dateEndExploitation;
        this.util = util;
        this.count = count;
        this.defaultNumberPrintPage = defaultNumberPrintPage;
    } 

    public Long getId() {
        return id;
    }

    public CartridgeType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public LocalDateTime getDateStartExploitation() {
        return dateStartExploitation;
    }

    public LocalDateTime getDateEndExploitation() {
        return dateEndExploitation;
    }

    public boolean isUtil() {
        return util;
    }

    public int getCount() {
        return count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(CartridgeType type) {
        this.type = type;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public void setDateStartExploitation(LocalDateTime dateStartExploitation) {
        this.dateStartExploitation = dateStartExploitation;
    }

    public void setDateEndExploitation(LocalDateTime dateEndExploitation) {
        this.dateEndExploitation = dateEndExploitation;
    }

    public void setUtil(boolean util) {
        this.util = util;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDefaultNumberPrintPage() {
        return defaultNumberPrintPage;
    }

    public void setDefaultNumberPrintPage(int defaultNumberPrintPage) {
        this.defaultNumberPrintPage = defaultNumberPrintPage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    @Override
    public String toString() {
        return "Model: " + this.model + "; Type: " + this.type.getName() + "; Location: " + this.location +
                "; Date start exploitation: " + this.dateStartExploitation + "; Date end exploitation: " + this.dateEndExploitation 
                + "; Count: " + this.count + "; Default number print page: " + this.defaultNumberPrintPage + "; Printer: " + this.printer;
    }

}
