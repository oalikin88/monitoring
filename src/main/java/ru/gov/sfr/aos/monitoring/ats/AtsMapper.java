/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ats;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;

/**
 *
 * @author 041AlikinOS
 */

@Mapper(componentModel = "spring")
public abstract class AtsMapper implements SvtMapper<Ats, SvtAtsDTO> {
    @Autowired
    protected AtsRepo atsRepo;
    @Autowired
    protected AtsModelRepo atsModelRepo;
    
    @Mapping(source = "atsModel.model", target = "model")
    @Mapping(source = "atsModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "cityNumberAmount", target = "cityNumberAmount")
    @Mapping(source = "innerConnectionIp", target = "innerConnectionIp")
    @Mapping(source = "innerConnectionAnalog", target = "innerConnectionAnalog")
    @Mapping(source = "yearCreated", target = "yearCreated")
    @Mapping(source = "outerConnectionType", target = "outerConnectionType")
    @Mapping(source = "atsModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "atsModel.manufacturer.id", target = "manufacturerId")
    @Override
    public abstract SvtAtsDTO getDto(Ats ats);
    
    @Mapping(target = "contract", expression = "java(atsRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "atsModel", expression = "java(atsModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "atsModel.model", source = "model")
    @Mapping(target = "atsModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "nameFromOneC", source = "nameFromOneC")
    @Mapping(target = "cityNumberAmount", source = "cityNumberAmount")
    @Mapping(target = "innerConnectionIp", source = "innerConnectionIp")
    @Mapping(target = "innerConnectionAnalog", source = "innerConnectionAnalog")
    @Mapping(target = "yearCreated", source = "yearCreated")
    @Mapping(target = "outerConnectionType", source = "outerConnectionType")
    @Override
    public abstract Ats getEntityFromDto(SvtAtsDTO dto);
    
}
