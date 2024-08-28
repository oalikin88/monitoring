/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class ThermoPrinterModel extends SvtModel {

    @OneToMany(targetEntity = ThermoPrinter.class, mappedBy = "thermoPrinterModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ThermoPrinter> thermoPrinters = new HashSet<>();

    public ThermoPrinterModel() {
    }


    public Set<ThermoPrinter> getThermoPrinters() {
        return thermoPrinters;
    }

    public void setThermoPrinters(Set<ThermoPrinter> thermoPrinters) {
        this.thermoPrinters = thermoPrinters;
    }
    
    
    
}
