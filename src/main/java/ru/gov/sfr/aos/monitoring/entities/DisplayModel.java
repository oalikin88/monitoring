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
public class DisplayModel extends SvtModel {

    @OneToMany(targetEntity = Display.class, mappedBy = "displayModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Display> displays = new HashSet<>();

    public DisplayModel() {
    }



    public Set<Display> getDisplays() {
        return displays;
    }

    public void setDisplays(Set<Display> displays) {
        this.displays = displays;
    }
    
    
}
