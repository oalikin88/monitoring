package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalRepo;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingMapper;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class TerminalServerMapper implements ObjectBuingMapper<TerminalServer, TerminalComponentDto> {
    @Autowired
    protected TerminalServerRepo terminalServerRepo;
    @Autowired
    protected TerminalServerModelRepo terminalServerModelRepo;
    @Autowired
    protected TerminalRepo terminalRepo;
    

    @Mapping(source = "terminalServerModel.id", target = "modelId")
    @Mapping(source = "terminalServerModel.model", target = "model")
    @Mapping(source = "terminalServerModel.manufacturer.name", target = "manufacturer")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "terminal.id", target = "terminalId")
    @Mapping(expression = "java(null == entity.getTerminal() ? false : true)", target = "hasInstalled")
    @Override
    public abstract TerminalComponentDto getDto(TerminalServer entity);
    
    @Mapping(target = "contract", expression = "java(terminalServerRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "terminalServerModel", expression = "java(terminalServerModelRepo.findById(dto.getModel()).get())")
    @Mapping(target = "terminalServerModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
        @Mapping(expression = "java(null == dto.terminalId ? null : terminalRepo.findById(dto.terminalId).get())", target = "terminal")
    @Override
    public abstract TerminalServer getEntityFromDto(TerminalComponentDto dto);
}
