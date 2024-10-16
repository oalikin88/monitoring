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
public class AtsModel extends SvtModel {

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

    @Override
    public String toString() {
        return "AtsModel{" + "model=" + this.getModel() + '}';
    }
    
    
}
