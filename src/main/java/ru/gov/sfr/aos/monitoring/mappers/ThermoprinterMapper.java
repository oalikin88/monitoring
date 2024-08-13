/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinter;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ThermoprinterModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.ThermoprinterRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class ThermoprinterMapper implements SvtMapper<ThermoPrinter, SvtDTO> {
    
    @Autowired
    protected ThermoprinterRepo thermoprinterRepo;
    @Autowired
    protected ThermoprinterModelRepo thermoprinterModelRepo;
    
    @Mapping(source = "thermoPrinterModel.model", target = "model")
    @Mapping(source = "thermoPrinterModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Override
    public abstract SvtDTO getDto(ThermoPrinter entity);

    @Mapping(target = "contract", expression = "java(thermoprinterRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "thermoPrinterModel", expression = "java(thermoprinterModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "thermoPrinterModel.model", source = "model")
    @Mapping(target = "thermoPrinterModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Override
    public abstract ThermoPrinter getEntityFromDto(SvtDTO dto);
    
    
    
}
