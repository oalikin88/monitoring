/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.BatteryType;
import ru.gov.sfr.aos.monitoring.entities.UpsManufacturer;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.UpsManufacturerDto;
import ru.gov.sfr.aos.monitoring.models.UpsModelDto;
import ru.gov.sfr.aos.monitoring.repositories.BatteryTypeRepo;


/**
 *
 * @author Alikin Oleg
 */
@Component
public class UpsManufacturerMapper {
    @Autowired
    private UpsModelMapper upsModelMapper;
    @Autowired
    private BatteryTypeRepo batteryTypeRepo;
    
    public UpsManufacturerDto getDto(UpsManufacturer entity) {
        UpsManufacturerDto dto = new UpsManufacturerDto();
        Set<UpsModelDto> modelDtoes = new HashSet<>();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        for(UpsModel el : entity.getModels()) {
            UpsModelDto upsModelDto = upsModelMapper.getDto(el);
            modelDtoes.add(upsModelDto);
        }
        dto.setModels(modelDtoes);
        return dto;
    }
    
    public List<UpsManufacturerDto> getListDtoes(List<UpsManufacturer> inputList) {
        List<UpsManufacturerDto> out = new ArrayList<>();
        for(UpsManufacturer el : inputList) {
            UpsManufacturerDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    public UpsManufacturer getEntity(UpsManufacturerDto dto) throws ObjectAlreadyExists {
        UpsManufacturer upsManufacturer = new UpsManufacturer();
        upsManufacturer.setId(dto.getId());
        if(dto.getName().isEmpty() || dto.getName().isBlank()) {
            throw new ObjectAlreadyExists("Поле производитель не может быть пусто");
        } else {
            upsManufacturer.setName(dto.getName().strip());
        }
        Set<UpsModel> listModels = new HashSet<>();
        for(UpsModelDto el : dto.getModels()) {
            UpsModel upsModel = new UpsModel();
            upsModel.setId(el.getId());
            upsModel.setBatteryAmount(el.getBatteryAmount());
            upsModel.setModel(el.getModel());
            BatteryType batType = batteryTypeRepo.findById(el.getBatteryTypeId()).get();
            upsModel.setBatteryType(batType);
            listModels.add(upsModel);
        }
        upsManufacturer.setModels(listModels);
        return upsManufacturer;
    }
    
    public List<UpsManufacturer> getListEntityes(List<UpsManufacturerDto> inputList) throws ObjectAlreadyExists {
        List<UpsManufacturer> out = new ArrayList<>();
        for(UpsManufacturerDto dto : inputList) {
            UpsManufacturer entity = getEntity(dto);
            out.add(entity);
        }
        return out;
    }
}
