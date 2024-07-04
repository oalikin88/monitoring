/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author 041AlikinOS
 */
@MappedSuperclass
public class SvtModel {
    protected String model;

    protected SvtModel() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

 
    
    
   
}
