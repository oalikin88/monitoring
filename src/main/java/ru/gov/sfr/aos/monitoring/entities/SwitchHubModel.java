/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.HashSet;
import java.util.Objects;
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
public class SwitchHubModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private SwitchHubManufacturer manufacturer;
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

    public SwitchHubManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(SwitchHubManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + Objects.hashCode(this.manufacturer);
        hash = 29 * hash + Objects.hashCode(this.switchHubs);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SwitchHubModel other = (SwitchHubModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        
        if (!Objects.equals(this.archived, other.archived)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        
        return Objects.equals(this.manufacturer, other.manufacturer);
    }
    
    
    
        @Override
    public String toString() {
        return "SwitchHubModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
    
}
