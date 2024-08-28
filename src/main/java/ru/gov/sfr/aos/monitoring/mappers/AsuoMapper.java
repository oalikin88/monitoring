/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Asuo;
import ru.gov.sfr.aos.monitoring.models.AsuoDTO;
import ru.gov.sfr.aos.monitoring.repositories.AsuoRepo;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.SwitchHubRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring",
        uses = SwitchHubMapper.class)
public abstract class AsuoMapper implements SvtObjectBuingMapper<Asuo, AsuoDTO>{

    @Autowired
    protected AsuoRepo asuoRepo;
    @Autowired
    protected PlaceRepo placeRepo;
    @Autowired
    protected ContractRepo contractRepo;
    @Autowired
    protected SwitchHubRepo switchHubRepo;
    
    
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "dateExploitationBegin", target = "dateExploitationBegin")
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "yearCreated", target = "yearCreated")
    @Mapping(source = "subDisplayAmount", target = "subDisplayAmount")
    @Mapping(source = "display.id", target = "displayId")
    @Mapping(source = "display.displayModel.model", target = "displayModel")
    @Mapping(source = "display.inventaryNumber", target = "displayInventary")
    @Mapping(source = "display.serialNumber", target = "displaySerial")
    @Mapping(source = "terminal.id", target = "terminalId")
    @Mapping(source = "terminal.terminalModel.model", target = "terminalModel")
    @Mapping(source = "terminal.inventaryNumber", target = "terminalInventary")
    @Mapping(source = "terminal.serialNumber", target = "terminalSerial")
    @Mapping(source = "thermoPrinter.id", target = "thermoprinterId")
    @Mapping(source = "thermoPrinter.thermoPrinterModel.model", target = "thermoprinterModel")
    @Mapping(source = "thermoPrinter.inventaryNumber", target = "thermoprinterInventary")
    @Mapping(source = "thermoPrinter.serialNumber", target = "thermoprinterSerial")
    @Mapping(source = "subDisplayModel.id", target = "subDisplayModelId")
    @Mapping(source = "subDisplayModel.model", target = "subDisplayModel")
    @Mapping(source = "switchingUnit.id", target = "switchingUnitId")
    @Mapping(source = "switchingUnit.switchingUnitModel.model", target = "switchingUnitModel")
    @Mapping(source = "switchingUnit.inventaryNumber", target = "switchingUnitInventary")
    @Mapping(source = "switchingUnit.serialNumber", target = "switchingUnitSerial")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "switchHubSet", target = "switches")
    @Override
    public abstract AsuoDTO getDto(Asuo entity);
    
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "display", expression = "java(asuoRepo.findById(dto.getId()).get().getDisplay())")
    @Mapping(target = "nameFromOneC", source = "nameFromOneC")
    @Mapping(target = "dateExploitationBegin", source = "dateExploitationBegin")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "yearCreated", source = "yearCreated")
    @Mapping(target = "subDisplayAmount", source = "subDisplayAmount")
    @Mapping(target = "terminal", expression = "java(asuoRepo.findById(dto.getId()).get().getTerminal())")
    @Mapping(target = "thermoPrinter", expression = "java(asuoRepo.findById(dto.getId()).get().getThermoPrinter())")
    @Mapping(target = "subDisplayModel", expression = "java(asuoRepo.findById(dto.getId()).get().getSubDisplayModel())")
    @Mapping(target = "switchingUnit", expression = "java(asuoRepo.findById(dto.getId()).get().getSwitchingUnit())")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "switchHubSet", expression = "java(dto.getSwitchId().stream().map(e -> switchHubRepo.findById(e).get()).collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "contract", expression = "java(asuoRepo.findById(dto.getId()).get().getContract())")
    @Override
    public abstract Asuo getEntityFromDto(AsuoDTO dto);
    
}
