/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author 041AlikinOS
 */

public class CpuModelDto extends SvtModelDto {
    
    private int core;
    private int freq;

    public CpuModelDto(int core, int freq, String model) {
        super(model);
        this.core = core;
        this.freq = freq;
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
        return "CpuModelDto{" + "id=" + this.getId() +  ", model=" + this.getModel() + ", core=" + core + ", freq=" + freq + '}';
    }
    
    
    
}
