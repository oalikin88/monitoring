/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import javax.persistence.Entity;


/**
 *
 * @author 041AlikinOS
 */
@Entity
public class Speakers extends SvtModel {


    public Speakers() {
    }

    public Speakers(String model) {
        super(model);
    }
    

        @Override
    public String toString() {
        return "SpeakersModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }

    
    
}
