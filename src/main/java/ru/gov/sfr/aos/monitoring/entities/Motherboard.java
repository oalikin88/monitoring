/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import javax.persistence.Entity;


/**
 *
 * @author 041AlikinOS
 */
@Entity
public class Motherboard extends SvtModel implements Serializable{


    public Motherboard() {
    }

    public Motherboard(String model) {
        super(model);
    }


    
    @Override
    public String toString() {
        return "MotherboardModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
    
    
}
