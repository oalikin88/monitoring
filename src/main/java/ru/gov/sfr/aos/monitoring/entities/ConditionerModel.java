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
public class ConditionerModel extends SvtModel {

    @OneToMany(targetEntity = Conditioner.class, mappedBy = "conditionerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Conditioner> conditioners = new HashSet<>();

    public ConditionerModel() {
    }


    public Set<Conditioner> getConditioners() {
        return conditioners;
    }

    public void setConditioners(Set<Conditioner> conditioners) {
        this.conditioners = conditioners;
    }
    
    
}
