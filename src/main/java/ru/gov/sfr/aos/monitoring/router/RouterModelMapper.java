/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.router;

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
public class RouterModelMapper implements ModelMapper<RouterModel, SvtModelDto> {

    @Override
    public RouterModel getModel(SvtModelDto dto) {
        RouterManufacturer manufacturer = new RouterManufacturer();
        manufacturer.setId(dto.getManufacturerId());
        manufacturer.setName(dto.getManufacturerName());
        RouterModel model = new RouterModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(manufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(RouterModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<RouterModel> inputList) {
         List<SvtModelDto> out = new ArrayList<>();
        for(RouterModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
    }
         return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(RouterModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    
    }
    
}
