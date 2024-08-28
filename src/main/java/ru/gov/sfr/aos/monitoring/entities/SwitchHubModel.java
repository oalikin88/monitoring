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
public class SwitchHubModel extends SvtModel {

    @OneToMany(targetEntity = SwitchHub.class, mappedBy = "switchHubModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SwitchHub> switchHubs = new HashSet<>();

    public SwitchHubModel() {
    }

  

    public Set<SwitchHub> getSwitchHubs() {
        return switchHubs;
    }

    public void setSwitchHubs(Set<SwitchHub> switchHubs) {
        this.switchHubs = switchHubs;
    }
    
    
    
    
}
