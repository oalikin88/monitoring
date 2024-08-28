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
public class InfomatModel extends SvtModel {

    @OneToMany(targetEntity = Infomat.class, mappedBy = "infomatModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Infomat> infomates = new HashSet<>();

    public InfomatModel() {
    }
    
    public InfomatModel(String model) {
        this.model = model;
    }


    public Set<Infomat> getInfomates() {
        return infomates;
    }

    public void setInfomates(Set<Infomat> infomates) {
        this.infomates = infomates;
    }
    
    
    
}
