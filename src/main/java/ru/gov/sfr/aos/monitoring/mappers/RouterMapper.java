/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Router;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.repositories.RouterModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.RouterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring")
public abstract class RouterMapper implements SvtMapper <Router, SvtSwitchHubDTO> {
    @Autowired
    protected RouterRepo routerRepo;
    @Autowired
    protected RouterModelRepo routerModelRepo;
    
    
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "routerModel.model", target = "model")
    @Mapping(source = "routerModel.id", target = "modelId")
    @Mapping(source = "portAmount", target = "portAmount")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Override
    public abstract SvtSwitchHubDTO getDto(Router router);
    
    @Mapping(target = "contract", expression = "java(routerRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "routerModel", expression = "java(routerModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "portAmount", source = "portAmount")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Override
    public abstract Router getEntityFromDto(SvtSwitchHubDTO dto);
}
