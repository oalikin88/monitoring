/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;

import org.springframework.stereotype.Component;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class HddMapper {
    
    public HddDto getHddDto(Hdd hdd) {
        HddDto dto = new HddDto();
        
        if(null == hdd.getModel()) {
            dto.setModel("Без наименования");
        } else {
            dto.setModel(hdd.getModel());
        }
        dto.setId(hdd.getId());
        dto.setCapacity(hdd.getCapacity());
        dto.setUnit(hdd.getUnit().name());
        if(null == hdd.getInventaryNumber()) {
            dto.setInventaryNumber("без инвентарного номера");
        } else {
            dto.setInventaryNumber(hdd.getInventaryNumber());
        }
        
        if(null == hdd.getSerialNumber()) {
            dto.setSerialNumber("без серийного номера");
        } else {
            dto.setInventaryNumber(hdd.getSerialNumber());
        }
        
        
        return dto;
    }
    
}
