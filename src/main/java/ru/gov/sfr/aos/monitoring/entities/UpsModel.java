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
public class UpsModel extends SvtModel {

    @OneToMany(targetEntity = Ups.class, mappedBy = "upsModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ups> upsList = new HashSet<>();

    public UpsModel() {
    }

    public UpsModel(String model) {
        this.model = model;
    }


    public Set<Ups> getUpsList() {
        return upsList;
    }

    public void setUpsList(Set<Ups> upsList) {
        this.upsList = upsList;
    }
    
    
}
