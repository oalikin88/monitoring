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
public class SwitchHubModel extends SvtModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @OneToMany(targetEntity = SwitchHub.class, mappedBy = "switchHubModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SwitchHub> switchHubs = new HashSet<>();

    public SwitchHubModel() {
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

    public Set<SwitchHub> getSwitchHubs() {
        return switchHubs;
    }

    public void setSwitchHubs(Set<SwitchHub> switchHubs) {
        this.switchHubs = switchHubs;
    }
    
    
    
    
}
