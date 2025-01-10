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
public class RouterModel extends SvtModel {
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private RouterManufacturer manufacturer;

    @OneToMany(targetEntity = Router.class, mappedBy = "routerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Router> routers = new HashSet<>();

    public RouterModel() {
    }


    public Set<Router> getRouters() {
        return routers;
    }

    public void setRouters(Set<Router> routers) {
        this.routers = routers;
    }

    public RouterManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(RouterManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 47 * hash + Objects.hashCode(this.manufacturer);
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
        final RouterModel other = (RouterModel) obj;
        
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
        return "RouterModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
