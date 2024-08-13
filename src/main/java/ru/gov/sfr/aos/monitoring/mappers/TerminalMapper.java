/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Terminal;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.TerminalModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class TerminalMapper implements SvtMapper<Terminal, SvtDTO>  {
    @Autowired
    protected TerminalRepo terminalRepo;
    @Autowired
    protected TerminalModelRepo terminalModelRepo;

    @Mapping(source = "terminalModel.model", target = "model")
    @Mapping(source = "terminalModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Override
    public abstract SvtDTO getDto(Terminal entity);
    
    @Mapping(target = "contract", expression = "java(terminalRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "terminalModel", expression = "java(terminalModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "terminalModel.model", source = "model")
    @Mapping(target = "terminalModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Override
    public abstract Terminal getEntityFromDto(SvtDTO dto);
    
    
}
