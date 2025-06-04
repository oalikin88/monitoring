/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.fax;

import ru.gov.sfr.aos.monitoring.fax.FaxManufacturer;
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
 * @author Alikin Oleg
 */
@Entity
public class FaxModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private FaxManufacturer manufacturer;
    @OneToMany(targetEntity = Fax.class, mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fax> faxes = new HashSet<>();

    public FaxModel() {
    }

    public Set<Fax> getFaxes() {
        return faxes;
    }

    public void setFaxes(Set<Fax> faxes) {
        this.faxes = faxes;
    }

    public FaxManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(FaxManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 53 * hash + Objects.hashCode(this.manufacturer);
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
        final FaxModel other = (FaxModel) obj;
        
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
        return "FaxModel{" + "id=" + this.id + ", model=" + this.model + '}';
    }
    
    
    
}
