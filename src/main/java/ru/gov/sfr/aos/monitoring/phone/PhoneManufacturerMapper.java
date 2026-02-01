/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.phone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class PhoneManufacturerMapper {

    @Autowired
    private SvtModelMapper mapper;
    
    
    public PhoneManufacturerDto getDto(PhoneManufacturer entity) {
        PhoneManufacturerDto dto = new PhoneManufacturerDto();
        Set<SvtModelDto> modelDtoes = new HashSet<>();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        modelDtoes = mapper.getPhoneModelsDtoes(entity.getModels());
        dto.setModels(modelDtoes);
        return dto;
    }
    
    public List<PhoneManufacturerDto> getListDtoes(List<PhoneManufacturer> inputList) {
        List<PhoneManufacturerDto> out = new ArrayList<>();
        for(PhoneManufacturer el : inputList) {
            PhoneManufacturerDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }
}
