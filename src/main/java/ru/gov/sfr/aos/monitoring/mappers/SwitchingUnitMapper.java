/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.SwitchingUnit;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.SwitchingUnitModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.SwitchingUnitRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class SwitchingUnitMapper implements SvtMapper<SwitchingUnit, SvtDTO>  {
    
    @Autowired
    protected SwitchingUnitRepo swunitRepo;
    @Autowired
    protected SwitchingUnitModelRepo swunitModelRepo;

    @Mapping(source = "switchingUnitModel.model", target = "model")
    @Mapping(source = "switchingUnitModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "inventaryNumber", target = "inventaryNumber")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Override
    public abstract SvtDTO getDto(SwitchingUnit entity);
    
    @Mapping(target = "contract", expression = "java(swunitRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "switchingUnitModel", expression = "java(swunitModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "switchingUnitModel.model", source = "model")
    @Mapping(target = "switchingUnitModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(source = "inventaryNumber", target = "inventaryNumber")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Override
    public abstract SwitchingUnit getEntityFromDto(SvtDTO dto); 
    
    
}
