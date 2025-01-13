/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Fax;
import ru.gov.sfr.aos.monitoring.models.FaxDto;
import ru.gov.sfr.aos.monitoring.repositories.FaxModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.FaxRepo;

/**
 *
 * @author Alikin Oleg
 */

@Mapper(componentModel = "spring")
public abstract class FaxMapper implements SvtMapper <Fax, FaxDto>  {

    @Autowired
    protected FaxRepo faxRepo;
    @Autowired
    protected FaxModelRepo faxModelRepo;
    
    @Mapping(source = "model.model", target = "model")
    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "nameFromeOneC", target = "nameFromOneC")
    @Mapping(source = "ipAdress", target = "ipAdress")
    @Mapping(source = "yearCreated", target = "yearCreated")
    @Mapping(source = "dateExploitationBegin", target = "dateExploitationBegin")
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "model.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "model.manufacturer.id", target = "manufacturerId")
    @Override
    public abstract FaxDto getDto(Fax entity);
    
    @Mapping(target = "contract", expression = "java(faxRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "model", expression = "java(faxModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "model.model", source = "model")
    @Mapping(target = "model.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "nameFromeOneC", source = "nameFromOneC")
    @Mapping(target = "ipAdress", source = "ipAdress")
    @Mapping(target = "yearCreated", source = "yearCreated")
    @Mapping(target = "dateExploitationBegin", source = "dateExploitationBegin")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Override
    public abstract Fax getEntityFromDto(FaxDto dto);
    
}
