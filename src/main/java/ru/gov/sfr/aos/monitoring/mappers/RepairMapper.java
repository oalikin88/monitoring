/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Repair;
import ru.gov.sfr.aos.monitoring.models.RepairDto;
import ru.gov.sfr.aos.monitoring.services.ObjectBuingFactory;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class RepairMapper  {
    
 
    @Autowired
    protected ObjectBuingFactory factory;

    public RepairDto toRepairDto(Repair repair) {
        RepairDto dto = new RepairDto();
        dto.setId(repair.getId());
        dto.setDateRepair(repair.getDateRepair());
        dto.setDocumentNumber(repair.getDocumentNumber());
        dto.setDefinition(repair.getDefinition());
        return dto;
    }
    

}
