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
public class Cpu extends SvtModel {

    private int core;
    private int freq;

    public Cpu() {
    }


    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
    
      @Override
    public String toString() {
        return "CpuModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
