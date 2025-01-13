/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.UpsRepo;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring")
public abstract class UpsMapper implements SvtMapper<Ups, SvtDTO>{
    
    @Autowired
    protected UpsRepo upsRepo;
    
    
    @Mapping(source = "upsModel.model", target = "model")
    @Mapping(source = "upsModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "upsModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "upsModel.manufacturer.id", target = "manufacturerId")
    public abstract SvtDTO getDto(Ups ups);
    
    @Mapping(target = "contract", expression = "java(upsRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "upsModel.model", source = "model")
    @Mapping(target = "upsModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "nameFromOneC", source = "nameFromOneC")
    public abstract Ups getEntityFromDto(SvtDTO dto);
}
