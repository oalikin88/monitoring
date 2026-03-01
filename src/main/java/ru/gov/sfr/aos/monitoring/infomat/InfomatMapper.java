/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.infomat;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring")
public abstract class InfomatMapper implements SvtMapper <Infomat, SvtDTO> {

    @Autowired
    protected InfomatRepo infomatRepo;
    @Autowired
    protected InfomatModelRepo infomatModelRepo;
    
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "infomatModel.model", target = "model")
    @Mapping(source = "infomatModel.id", target = "modelId")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "infomatModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "infomatModel.manufacturer.id", target = "manufacturerId")
    @Override
    public abstract SvtDTO getDto(Infomat entity);
    
    @Mapping(target = "contract", expression = "java(infomatRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "infomatModel", expression = "java(infomatModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Override
    public abstract Infomat getEntityFromDto(SvtDTO dto);
    
}
