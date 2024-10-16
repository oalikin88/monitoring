/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class OperationSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private boolean license;
    @ManyToMany(mappedBy = "operationSystems")
    private Set<SystemBlock> systemBlocks = new HashSet<>();

    public OperationSystem() {
    }

    
    
    public OperationSystem(String name, boolean license) {
        this.model = name;
        this.license = license;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isLicense() {
        return license;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

    public Set<SystemBlock> getSystemBlocks() {
        return systemBlocks;
    }

    public void setSystemBlocks(Set<SystemBlock> systemBlocks) {
        this.systemBlocks = systemBlocks;
    }

    @Override
    public String toString() {
        return "OperationSystem{" + "id=" + id + ", model=" + model + ", license=" + license + ", systemBlocks=" + systemBlocks + '}';
    }
    
    
}
