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
public class SystemBlockModel extends SvtModel {

    @OneToMany(targetEntity = SystemBlock.class, mappedBy = "systemBlockModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SystemBlock> systemBlocks = new HashSet<>();

    public SystemBlockModel() {
    }

    
    public SystemBlockModel(String model) {
        this.model = model;
    }



    public Set<SystemBlock> getSystemBlocks() {
        return systemBlocks;
    }

    public void setSystemBlocks(Set<SystemBlock> systemBlocks) {
        this.systemBlocks = systemBlocks;
    }
    
        @Override
    public String toString() {
        return "SystemBlockModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
