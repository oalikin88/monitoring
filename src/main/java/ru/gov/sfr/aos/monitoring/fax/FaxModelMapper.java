/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.fax;

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
public class FaxModelMapper implements ModelMapper<FaxModel, SvtModelDto> {
     @Override
     public FaxModel getModel(SvtModelDto dto) {
        FaxManufacturer manufacturer = new FaxManufacturer();
        manufacturer.setId(dto.getManufacturerId());
        manufacturer.setName(dto.getManufacturerName());
        FaxModel model = new FaxModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(manufacturer);
        return model;
        
    }
    
     @Override
    public SvtModelDto getDto(FaxModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
    
     @Override
    public List<SvtModelDto> getListDtoes(List<FaxModel> inputList) {
        List<SvtModelDto> out = new ArrayList<>();
        for(FaxModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    
     @Override
    public SvtModelDto getDtoForSelectize(FaxModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
}
