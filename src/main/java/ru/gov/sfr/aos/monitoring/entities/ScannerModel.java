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
public class ScannerModel extends SvtModel {

    @OneToMany(targetEntity = Scanner.class, mappedBy = "scannerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Scanner> scanners = new HashSet<>();

    public ScannerModel() {
    }


    public Set<Scanner> getScanners() {
        return scanners;
    }

    public void setScanners(Set<Scanner> scanners) {
        this.scanners = scanners;
    }
    
        @Override
    public String toString() {
        return "ScannerModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
