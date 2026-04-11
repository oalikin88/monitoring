package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingMapper;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalRepo;
import ru.gov.sfr.aos.monitoring.asuo.terminal.sensor.TerminalSensorModelRepo;
import ru.gov.sfr.aos.monitoring.asuo.terminal.sensor.TerminalSensorRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class TerminalSensorMapper implements ObjectBuingMapper<TerminalSensor, TerminalComponentDto>  {
     
    @Autowired
    protected TerminalSensorRepo terminalSensorRepo;
    @Autowired
    protected TerminalSensorModelRepo terminalSensorModelRepo;
    @Autowired
    protected TerminalRepo terminalRepo;
    

    @Mapping(source = "terminalSensorModel.id", target = "modelId")
    @Mapping(source = "terminalSensorModel.model", target = "model")
    @Mapping(source = "terminalSensorModel.manufacturer.name", target = "manufacturer")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "terminal.id", target = "terminalId")
    @Mapping(expression = "java(null == entity.getTerminal() ? false : true)", target = "hasInstalled")
    @Override
    public abstract TerminalComponentDto getDto(TerminalSensor entity);
    
    @Mapping(target = "contract", expression = "java(terminalSensorRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "terminalSensorModel", expression = "java(terminalSensorModelRepo.findById(dto.getModel()).get())")
    @Mapping(target = "terminalSensorModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(expression = "java(null == dto.terminalId ? null : terminalRepo.findById(dto.terminalId).get())", target = "terminal")
    @Override
    public abstract TerminalSensor getEntityFromDto(TerminalComponentDto dto);
}
