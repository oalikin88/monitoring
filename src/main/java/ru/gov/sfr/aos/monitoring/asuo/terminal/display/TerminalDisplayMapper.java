package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalRepo;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingMapper;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class TerminalDisplayMapper implements ObjectBuingMapper<TerminalDisplay, TerminalDisplayDto>  {
    @Autowired
    protected TerminalDisplayRepo terminalDisplayRepo;
    @Autowired
    protected TerminalDisplayModelRepo terminalDisplayModelRepo;
    @Autowired
    protected TerminalRepo terminalRepo;
    

    @Mapping(source = "terminalDisplayModel.id", target = "modelId")
    @Mapping(source = "terminalDisplayModel.model", target = "model")
    @Mapping(source = "terminalDisplayModel.manufacturer.name", target = "manufacturer")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "screenDiagonal", target = "screenDiagonal")
    @Mapping(source = "terminal.id", target = "terminalId")
    @Mapping(expression = "java(null == entity.getTerminal() ? false : true)", target = "hasInstalled")
    @Override
    public abstract TerminalDisplayDto getDto(TerminalDisplay entity);
    
    @Mapping(target = "contract", expression = "java(terminalDisplayRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "terminalDisplayModel", expression = "java(terminalDisplayModelRepo.findById(dto.getModel()).get())")
    @Mapping(target = "terminalDisplayModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(source = "screenDiagonal", target = "screenDiagonal")
    @Mapping(expression = "java(null == dto.terminalId ? null : terminalRepo.findById(dto.terminalId).get())", target = "terminal")
    @Override
    public abstract TerminalDisplay getEntityFromDto(TerminalDisplayDto dto);
    
}
