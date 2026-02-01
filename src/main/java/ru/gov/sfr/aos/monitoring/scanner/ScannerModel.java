/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.scanner;

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
public class ScannerModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private ScannerManufacturer manufacturer;
    @OneToMany(targetEntity = Scanner.class, mappedBy = "scannerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Scanner> scanners = new HashSet<>();

    public ScannerModel() {
    }


    public Set<Scanner> getScanners() {
        return scanners;
    }

    public void setScanners(Set<Scanner> scanners) {
        this.scanners = scanners;
    }

    public ScannerManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ScannerManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    
    
    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 23 * hash + Objects.hashCode(this.manufacturer);
        hash = 23 * hash + Objects.hashCode(this.scanners);
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
        final ScannerModel other = (ScannerModel) obj;
        
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
        return "ScannerModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
