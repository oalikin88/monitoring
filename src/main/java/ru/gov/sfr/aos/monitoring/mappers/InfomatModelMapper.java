/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.InfomatManufacturer;
import ru.gov.sfr.aos.monitoring.entities.InfomatModel;
import ru.gov.sfr.aos.monitoring.interfaces.ModelMapper;
import ru.gov.sfr.aos.monitoring.models.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class InfomatModelMapper implements ModelMapper<InfomatModel>{

    @Override
    public InfomatModel getModel(SvtModelDto dto) {
        InfomatManufacturer manufacturer = new InfomatManufacturer();
        manufacturer.setId(dto.getManufacturerId());
        manufacturer.setName(dto.getManufacturerName());
        InfomatModel model = new InfomatModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(manufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(InfomatModel entity) {
         SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<InfomatModel> inputList) {
          List<SvtModelDto> out = new ArrayList<>();
        for(InfomatModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
    }
         return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(InfomatModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getManufacturer().getName() + " " + entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
    
}
