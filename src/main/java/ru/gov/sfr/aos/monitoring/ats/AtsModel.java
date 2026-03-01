/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ats;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class AtsModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private AtsManufacturer manufacturer;
    @OneToMany(targetEntity = Ats.class, mappedBy = "atsModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ats> atses = new HashSet<>();

    public AtsModel() {
    }




    public Set<Ats> getAtses() {
        return atses;
    }

    public void setAtses(Set<Ats> atses) {
        this.atses = atses;
    }

    public AtsManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(AtsManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 79 * hash + Objects.hashCode(this.manufacturer);
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
        final AtsModel other = (AtsModel) obj;
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
        return "AtsModel{" + "model=" + this.getModel() + '}';
    }
    
    
}
