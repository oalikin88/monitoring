/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import java.util.ArrayList;
import java.util.List;
import ru.gov.sfr.aos.monitoring.entities.ManufacturerModel;
import ru.gov.sfr.aos.monitoring.entities.SvtModel;
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */

public interface ManufacturerMapper<R extends ManufacturerModel<M>, E extends ManufacturerDTO, M extends SvtModel> {
   
    default R getEntity(E dto){
        R r = (R)new ManufacturerModel();
        r.setId(dto.getId());
        r.setName(dto.getName()); 
        return  r;
    }
    
    
     default E getDto(R entity) {
        E dto = (E)new ManufacturerDTO<>();
        List<SvtModelDto> modelDtoes = new ArrayList<>();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        for(M el : entity.getModels()) {
            SvtModelDto modelDto = new SvtModelDto();
            modelDto.setModel(el.getModel());
            modelDto.setId(el.getId());
            modelDtoes.add(modelDto);
        }
        dto.setModels(modelDtoes);
        return dto;
    }
    
}
