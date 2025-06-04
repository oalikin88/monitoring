/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.conditioner;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.conditioner.SvtConditionerDTO;
import ru.gov.sfr.aos.monitoring.conditioner.ConditionerModelRepo;
import ru.gov.sfr.aos.monitoring.conditioner.ConditionerRepo;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring")
public abstract class ConditionerMapper implements SvtMapper<Conditioner, SvtConditionerDTO> {
    
    @Autowired
    protected ConditionerRepo conditionerRepo;
    @Autowired
    protected ConditionerModelRepo conditionerModelRepo;
    
    
    @Mapping(source = "conditionerModel.model", target = "model")
    @Mapping(source = "conditionerModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "yearCreated", target = "yearCreated")
    @Mapping(source = "conditionerType", target = "conditionerType")
    @Mapping(source = "splitSystem", target = "splitSystem")
    @Mapping(source = "winterKit", target = "winterKit")
    @Mapping(source = "havePomp", target = "havePomp")
    @Mapping(source = "conditionerModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "conditionerModel.manufacturer.id", target = "manufacturerId")
    @Override
    public abstract SvtConditionerDTO getDto(Conditioner conditioner);
    
    @Mapping(target = "conditionerModel.model", source = "model")
    @Mapping(target = "conditionerModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "nameFromOneC", source = "nameFromOneC")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "yearCreated", source = "yearCreated")
    @Mapping(target = "conditionerType", source = "conditionerType")
    @Mapping(target = "splitSystem", source = "splitSystem")
    @Mapping(target = "winterKit", source = "winterKit")
    @Mapping(target = "havePomp", source = "havePomp")
    @Mapping(target = "contract", expression = "java(conditionerRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "conditionerModel", expression = "java(conditionerModelRepo.findById(dto.getModelId()).get())")
    @Override
    public abstract Conditioner getEntityFromDto(SvtConditionerDTO dto);
    
}
