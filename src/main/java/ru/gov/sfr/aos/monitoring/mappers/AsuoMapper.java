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
import ru.gov.sfr.aos.monitoring.repositories.HubRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.ProgramSoftwareRepo;

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
    protected HubRepo hubRepo;
    @Autowired
    protected ProgramSoftwareRepo programSoftwareRepo;
    
    
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "dateExploitationBegin", target = "dateExploitationBegin")
    @Mapping(source = "yearCreated", target = "yearCreated")
    @Mapping(source = "subDisplayAmount", target = "subDisplayAmount")
    @Mapping(expression = "java(entity.getDisplay().stream().map(e -> new  ru.gov.sfr.aos.monitoring.models.AsuoComponentDto(e.getId(), e.getAsuo().isEmpty() ? false : true)).collect(java.util.stream.Collectors.toSet()))", target = "displays")
    @Mapping(expression = "java(entity.getTerminal().stream().map(e -> new  ru.gov.sfr.aos.monitoring.models.AsuoComponentDto(e.getId(), e.getAsuos().isEmpty() ? false : true)).collect(java.util.stream.Collectors.toSet()))", target = "terminals")
    @Mapping(source = "subDisplayModel.id", target = "subDisplayModelId")
    @Mapping(source = "subDisplayModel.model", target = "subDisplayModel")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "programSoftware.id", target = "programSoftware")
    @Mapping(source = "inventaryNumber", target = "inventaryNumber")
    @Mapping(expression = "java(entity.getHubSet().stream().map(e -> new  ru.gov.sfr.aos.monitoring.models.AsuoComponentDto(e.getId(), e.getAsuo().isEmpty() ? false : true)).collect(java.util.stream.Collectors.toSet()))", target = "hubs")
    @Override
    public abstract AsuoDTO getDto(Asuo entity);
    
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "display", expression = "java(asuoRepo.findById(dto.getId()).get().getDisplay())")
    @Mapping(target = "nameFromOneC", source = "nameFromOneC")
    @Mapping(target = "dateExploitationBegin", source = "dateExploitationBegin")
    @Mapping(target = "yearCreated", source = "yearCreated")
    @Mapping(target = "inventaryNumber", source = "inventaryNumber")
    @Mapping(target = "subDisplayAmount", source = "subDisplayAmount")
    @Mapping(target = "terminal", expression = "java(asuoRepo.findById(dto.getId()).get().getTerminal())")
    @Mapping(target = "programSoftware", expression = "java(programSoftwareRepo.findById(dto.getProgramSoftware()).get())")
    @Mapping(target = "subDisplayModel", expression = "java(asuoRepo.findById(dto.getId()).get().getSubDisplayModel())")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "hubSet", expression = "java(dto.getHubs().stream().map(e -> hubRepo.findById(e.getId()).get()).collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "contract", expression = "java(asuoRepo.findById(dto.getId()).get().getContract())")
    @Override
    public abstract Asuo getEntityFromDto(AsuoDTO dto);
    
}
