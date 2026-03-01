/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.monitor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.monitor.MonitorRepo;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring")
public abstract class MonitorMapper implements SvtMapper<Monitor, SvtDTO> {
    
    @Autowired
    protected MonitorRepo monitorRepo;
    
    @Mapping(source = "monitorModel.model", target = "model")
    @Mapping(source = "monitorModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "baseType", target = "baseType")
    @Mapping(source = "nameFromeOneC", target = "nameFromOneC")
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "monitorModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "monitorModel.manufacturer.id", target = "manufacturerId")
    public abstract SvtDTO getDto(Monitor monitor);
    
    @Mapping(target = "contract", expression = "java(monitorRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "monitorModel.model", source = "model")
    @Mapping(target = "monitorModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "baseType", source = "baseType")
    @Mapping(target = "nameFromeOneC", source = "nameFromOneC")
    @Mapping(target = "numberRoom", source = "numberRoom")
    public abstract Monitor getEntityFromDto(SvtDTO dto);
}
