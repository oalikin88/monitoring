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
import ru.gov.sfr.aos.monitoring.models.TerminalDto;
import ru.gov.sfr.aos.monitoring.repositories.AsuoRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalDisplayRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalSensorRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalServerRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalUpsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class TerminalMapper implements SvtMapper<Terminal, TerminalDto>  {
    @Autowired
    protected TerminalRepo terminalRepo;
    @Autowired
    protected TerminalModelRepo terminalModelRepo;
    @Autowired
    protected TerminalDisplayRepo terminalDisplayRepo;
    @Autowired
    protected TerminalUpsRepo terminalUpsRepo;
    @Autowired
    protected TerminalSensorRepo terminalSensorRepo;
    @Autowired
    protected TerminalServerRepo terminalServerRepo;
    @Autowired
    protected TerminalPrinterRepo terminalPrinterRepo;
    @Autowired
    protected AsuoRepo asuoRepo;

    @Mapping(source = "terminalModel.model", target = "model")
    @Mapping(source = "terminalModel.id", target = "modelId")
    @Mapping(source = "terminalModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "terminalDisplay.id", target = "terminalDisplayId")
    @Mapping(source = "terminalUps.id", target = "terminalUpsId")
    @Mapping(source = "terminalPrinter.id", target = "terminalPrinterId")
    @Mapping(source = "terminalSensor.id", target = "terminalSensorId")
    @Mapping(source = "terminalServer.id", target = "terminalServerId")
    @Mapping(expression = "java(entity.getAsuos().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toSet()))", target = "asuos")
    @Override
    public abstract TerminalDto getDto(Terminal entity);
    
    @Mapping(target = "contract", expression = "java(terminalRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "terminalModel", expression = "java(terminalModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "terminalDisplay", expression = "java(terminalDisplayRepo.findById(dto.getTerminalDisplayId()).get())")
    @Mapping(target = "terminalUps", expression = "java(terminalUpsRepo.findById(dto.getTerminalUpsId()).get())")
    @Mapping(target = "terminalPrinter", expression = "java(terminalPrinterRepo.findById(dto.getTerminalPrinterId()).get())")
    @Mapping(target = "terminalServer", expression = "java(terminalServerRepo.findById(dto.getTerminalServerId()).get())")
    @Mapping(target = "terminalSensor", expression = "java(terminalSensorRepo.findById(dto.getTerminalSensorId()).get())")
    @Mapping(target = "terminalModel.model", source = "model")
    @Mapping(target = "terminalModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "asuos", expression = "java(asuoRepo.findAllById(dto.getAsuos()).stream().collect(java.util.stream.Collectors.toSet()))")
    @Override
    public abstract Terminal getEntityFromDto(TerminalDto dto);
    
    
}
