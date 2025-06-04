/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.switchhub;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;
import ru.gov.sfr.aos.monitoring.switchhub.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.switchhub.SwitchHubModelRepo;
import ru.gov.sfr.aos.monitoring.switchhub.SwitchHubRepo;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring")
public abstract class SwitchHubMapper implements SvtMapper<SwitchHub, SvtSwitchHubDTO> {
    
    @Autowired
    protected SwitchHubRepo switchHubRepo;
    @Autowired
    protected SwitchHubModelRepo switchHubModelRepo;
    
    
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "yearCreated", target = "yearCreated")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "switchHubModel.model", target = "model")
    @Mapping(source = "switchHubModel.id", target = "modelId")
    @Mapping(source = "portAmount", target = "portAmount")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "inventaryNumber", target = "inventaryNumber")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Mapping(source = "switchHubModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "switchHubModel.manufacturer.id", target = "manufacturerId")
    @Override
    public abstract SvtSwitchHubDTO getDto(SwitchHub switchHub);
    @Mapping(target = "contract", expression = "java(switchHubRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "switchHubModel", expression = "java(switchHubModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "yearCreated", source = "yearCreated")
    @Mapping(target = "portAmount", source = "portAmount")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "inventaryNumber", target = "inventaryNumber")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Override
    public abstract SwitchHub getEntityFromDto(SvtSwitchHubDTO dto);
    
}
