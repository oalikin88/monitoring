/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.server;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;
import ru.gov.sfr.aos.monitoring.components.CpuRepo;
import ru.gov.sfr.aos.monitoring.components.HddRepo;
import ru.gov.sfr.aos.monitoring.repositories.OperationSystemRepo;
import ru.gov.sfr.aos.monitoring.components.RamRepo;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring")
public abstract class ServerMapper implements SvtMapper<Server, SvtServerDTO> {
    @Autowired
    protected ServerRepo serverRepo;
    @Autowired
    protected ServerModelRepo serverModelRepo;
    @Autowired
    protected CpuRepo cpuRepo;
    @Autowired
    protected RamRepo ramRepo;
    @Autowired
    protected HddRepo hddRepo;
    @Autowired
    protected OperationSystemRepo operationSystemRepo;
    
    
    
    @Mapping(source = "serverModel.model", target = "model")
    @Mapping(source = "serverModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "dateUpgrade", target = "dateUpgrade")
    @Mapping(source = "yearCreated", target = "yearCreated")
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "ipAdress", target = "ipAdress")
    @Mapping(source = "cpu.id", target = "cpuId")
    @Mapping(source = "cpu.model", target = "cpuModel")
    @Mapping(source = "cpu.core", target = "cpuCore")
    @Mapping(source = "cpu.freq", target = "cpuFreq")
    @Mapping(source = "cpuAmount", target = "cpuAmount")
    @Mapping(source = "dateExploitationBegin", target = "dateExploitationBegin")
    @Mapping(source = "ram.id", target = "ramId")
    @Mapping(source = "ram.model", target = "ramModel")
    @Mapping(source = "ram.capacity", target = "ramCapacity")
    @Mapping(source = "serverModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "serverModel.manufacturer.id", target = "manufacturerId")
    @Mapping(expression = "java(server.getOperationSystems().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toList()))", target = "operationSystemId")
    @Mapping(expression = "java(server.getHdd().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toList()))", target = "hddIdList")
    @Override
    public abstract SvtServerDTO getDto(Server server);
    
    @Mapping(target = "contract", expression = "java(serverRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "serverModel", expression = "java(serverModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "nameFromOneC", source = "nameFromOneC")
    @Mapping(target = "dateUpgrade", source = "dateUpgrade")
    @Mapping(target = "yearCreated", source = "yearCreated")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "ipAdress", source = "ipAdress")
    @Mapping(target = "dateExploitationBegin", source = "dateExploitationBegin")
    @Mapping(target = "cpu", expression = "java(cpuRepo.findById(dto.getCpuId()).get())")
    @Mapping(target = "cpuAmount", source = "cpuAmount")
    @Mapping(target = "ram", expression = "java(ramRepo.findById(dto.getRamId()).get())")
    @Mapping(target = "operationSystems", expression = "java(operationSystemRepo.findAllById(dto.getOperationSystemId()).stream().collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "hdd", expression = "java(hddRepo.findAllById(dto.getHddIdList()).stream().collect(java.util.stream.Collectors.toSet()))")
    @Override
    public abstract Server getEntityFromDto(SvtServerDTO dto); 
    
    
}
