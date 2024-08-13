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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class MainDisplayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @OneToMany(targetEntity = MainDisplay.class, mappedBy = "mainDisplayModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MainDisplay> mainDisplays = new HashSet<>();

    public MainDisplayModel() {
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

    public Set<MainDisplay> getMainDisplays() {
        return mainDisplays;
    }

    public void setMainDisplays(Set<MainDisplay> mainDisplays) {
        this.mainDisplays = mainDisplays;
    }
    
    
}
