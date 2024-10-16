/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alikin Oleg
 */
public class AllRepairsDto {
    
    private Long objectBuingId;
    private List<RepairDto> repairsDtoes = new ArrayList<>();

    public AllRepairsDto() {
    }

    public Long getObjectBuingId() {
        return objectBuingId;
    }

    public void setObjectBuingId(Long objectBuingId) {
        this.objectBuingId = objectBuingId;
    }

    public List<RepairDto> getRepairsDtoes() {
        return repairsDtoes;
    }

    public void setRepairsDtoes(List<RepairDto> repairsDtoes) {
        this.repairsDtoes = repairsDtoes;
    }

    @Override
    public String toString() {
        return "AllRepairsDto{" + "objectBuingId=" + objectBuingId + ", repairsDtoes=" + repairsDtoes + '}';
    }
    
    
    
}
