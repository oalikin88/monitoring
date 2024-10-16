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
public class PhoneModel extends SvtModel {

    @OneToMany(targetEntity = Phone.class, mappedBy = "phoneModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Phone> phones = new HashSet<>();

    public PhoneModel() {
    }

    public PhoneModel(String model) {
        this.model = model;
    }
    

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }
    
        @Override
    public String toString() {
        return "PhoneModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
    
}
