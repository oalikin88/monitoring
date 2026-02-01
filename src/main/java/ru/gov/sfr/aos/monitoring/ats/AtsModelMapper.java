/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ats;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.gov.sfr.aos.monitoring.models.ModelMapper;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class AtsModelMapper implements ModelMapper<AtsModel, SvtModelDto>{

    @Override
    public AtsModel getModel(SvtModelDto dto) {
        AtsManufacturer manufacturer = new AtsManufacturer();
        manufacturer.setId(dto.getManufacturerId());
        manufacturer.setName(dto.getManufacturerName());
        AtsModel model = new AtsModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(manufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(AtsModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<AtsModel> inputList) {
         List<SvtModelDto> out = new ArrayList<>();
        for(AtsModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
    }
         return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(AtsModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
    
    
 
    
}
