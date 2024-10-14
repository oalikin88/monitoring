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
 * @author Alikin Oleg
 */
@Entity
public class FaxModel extends SvtModel {
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
    
    
    
}
