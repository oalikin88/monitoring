package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

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
public abstract class TerminalUpsMapper implements ObjectBuingMapper<TerminalUps, TerminalComponentDto> {

    
    
    @Autowired
    protected TerminalUpsRepo terminalUpsRepo;
    @Autowired
    protected TerminalUpsModelRepo terminalUpsModelRepo;
    @Autowired
    protected TerminalRepo terminalRepo;

    @Mapping(source = "terminalUpsModel.id", target = "modelId")
    @Mapping(source = "terminalUpsModel.model", target = "model")
    @Mapping(source = "terminalUpsModel.manufacturer.name", target = "manufacturer")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "terminal.id", target = "terminalId")
    @Mapping(expression = "java(null == entity.getTerminal() ? false : true)", target = "hasInstalled")
    @Override
    public abstract TerminalComponentDto getDto(TerminalUps entity);
    
    @Mapping(target = "contract", expression = "java(terminalUpsRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "terminalUpsModel", expression = "java(terminalUpsModelRepo.findById(dto.getModel()).get())")
    @Mapping(target = "terminalUpsModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(expression = "java(null == dto.terminalId ? null : terminalRepo.findById(dto.terminalId).get())", target = "terminal")
    @Override
    public abstract TerminalUps getEntityFromDto(TerminalComponentDto dto);
}
