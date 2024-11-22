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
public class Ram extends SvtModel {

    private int capacity;

    public Ram() {
    }

    public Ram(String model) {
        super(model);
    }
    
    
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
        @Override
    public String toString() {
        return "RamModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
    
}
