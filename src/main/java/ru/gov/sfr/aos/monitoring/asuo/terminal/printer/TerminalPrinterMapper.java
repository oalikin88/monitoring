package ru.gov.sfr.aos.monitoring.asuo.terminal.printer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingMapper;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.asuo.terminal.printer.TerminalPrinterModelRepo;
import ru.gov.sfr.aos.monitoring.asuo.terminal.printer.TerminalPrinterRepo;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class TerminalPrinterMapper implements ObjectBuingMapper<TerminalPrinter, TerminalComponentDto>  {
    
    @Autowired
    protected TerminalPrinterRepo terminalPrinterRepo;
    @Autowired
    protected TerminalPrinterModelRepo terminalPrinterModelRepo;
    @Autowired
    protected TerminalRepo terminalRepo;
    

    @Mapping(source = "terminalPrinterModel.id", target = "modelId")
    @Mapping(source = "terminalPrinterModel.model", target = "model")
    @Mapping(source = "terminalPrinterModel.manufacturer.name", target = "manufacturer")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "terminal.id", target = "terminalId")
    @Mapping(expression = "java(null == entity.getTerminal() ? false : true)", target = "hasInstalled")
    @Override
    public abstract TerminalComponentDto getDto(TerminalPrinter entity);
    
    @Mapping(target = "contract", expression = "java(terminalPrinterRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "terminalPrinterModel", expression = "java(terminalPrinterModelRepo.findById(dto.getModel()).get())")
    @Mapping(target = "terminalPrinterModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(expression = "java(null == dto.terminalId ? null : terminalRepo.findById(dto.terminalId).get())", target = "terminal")
    @Override
    public abstract TerminalPrinter getEntityFromDto(TerminalComponentDto dto);
}
