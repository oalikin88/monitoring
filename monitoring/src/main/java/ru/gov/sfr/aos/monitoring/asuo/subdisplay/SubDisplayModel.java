/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo.subdisplay;

import ru.gov.sfr.aos.monitoring.asuo.Asuo;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import ru.gov.sfr.aos.monitoring.asuo.Asuo;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;

/**
 *
 * @author 041AlikinOS
 */
@Entity
public class SubDisplayModel extends SvtModel {

    @OneToMany(targetEntity = Asuo.class, mappedBy = "subDisplayModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private String model;



    public SubDisplayModel() {
    }



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    
        @Override
    public String toString() {
        return "SubDisplayModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
