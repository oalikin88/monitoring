/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;

import javax.persistence.Entity;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;


/**
 *
 * @author 041AlikinOS
 */
@Entity
public class SoundCard extends SvtModel {


    public SoundCard() {
    }

    public SoundCard(String model) {
        super(model);
    }

      @Override
    public String toString() {
        return "SoundCardModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
    
    
}
