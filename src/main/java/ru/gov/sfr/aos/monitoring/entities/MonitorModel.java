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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class MonitorModel extends SvtModel {
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private MonitorManufacturer manufacturer;
    
    @OneToMany(targetEntity = Monitor.class, mappedBy = "monitorModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Monitor> monitors = new HashSet<>();

    public MonitorModel() {
    }

    public MonitorModel(String model) {
        this.model = model;
    }


    public Set<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(Set<Monitor> monitors) {
        this.monitors = monitors;
    }

    public MonitorManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(MonitorManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    
    
    @Override
    public String toString() {
        return "MonitorModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }


    
    
}
