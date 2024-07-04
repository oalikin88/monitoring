/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.models.SvtSystemBlockDTO;
import ru.gov.sfr.aos.monitoring.repositories.SystemBlockRepo;

/**
 *
 * @author 041AlikinOS
 */

@Mapper(componentModel = "spring")
public abstract class SystemBlockMapper implements SvtMapper <SystemBlock, SvtSystemBlockDTO> {
    @Autowired
    protected SystemBlockRepo systemblockRepo;
    
    
    @Mapping(source = "systemBlockModel.model", target = "model")
    @Mapping(source = "systemBlockModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "dateUpgrade", target = "dateUpgrade")
    @Mapping(source = "motherBoard.id", target = "motherboardId")
    @Mapping(source = "motherBoard.model", target = "motherboardModel")
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "ipAdress", target = "ipAdress")
    @Mapping(source = "cpu.id", target = "cpuId")
    @Mapping(source = "cpu.model", target = "cpuModel")
    @Mapping(source = "cpu.core", target = "cpuCore")
    @Mapping(source = "cpu.freq", target = "cpuFreq")
    @Mapping(source = "ram.id", target = "ramId")
    @Mapping(source = "ram.model", target = "ramModel")
    @Mapping(source = "ram.capacity", target = "ramCapacity")
    @Mapping(source = "hdd.id", target = "hddId")
    @Mapping(source = "hdd.model", target = "hddModel")
    @Mapping(source = "hdd.capacity", target = "hddCapacity")
    @Mapping(source = "hdd.unit", target = "hddUnit")
    @Mapping(source = "hdd.serialNumber", target = "hddSerialNumber")
    @Mapping(source = "hdd.inventaryNumber", target = "hddInventaryNumber")
    @Mapping(source = "videoCard.id", target = "videoCardId")
    @Mapping(source = "videoCard.model", target = "videoCardModel")
    @Mapping(source = "cdDrive.id", target = "cdDriveId")
    @Mapping(source = "cdDrive.model", target = "cdDriveModel")
    @Mapping(source = "soundCard.id", target = "soundCardId")
    @Mapping(source = "soundCard.model", target = "soundCardModel")
    @Mapping(source = "lanCard.id", target = "lanCardId")
    @Mapping(source = "lanCard.model", target = "lanCardModel")
    @Mapping(source = "keyboard.id", target = "keyboardId")
    @Mapping(source = "keyboard.model", target = "keyboardModel")
    @Mapping(source = "mouse.id", target = "mouseId")
    @Mapping(source = "mouse.model", target = "mouseModel")
    @Mapping(source = "speakers.id", target = "speakersId")
    @Mapping(source = "speakers.model", target = "speakersModel")
    @Mapping(expression = "java(systemBlock.getOperationSystems().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toList()))", target = "operationSystemId")
    @Override
    public abstract SvtSystemBlockDTO getDto(SystemBlock systemBlock);
    
    @Mapping(target = "contract", expression = "java(systemblockRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "systemBlockModel.model", source = "model")
    @Mapping(target = "systemBlockModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "nameFromOneC", source = "nameFromOneC")
    @Mapping(target = "dateUpgrade", source = "dateUpgrade")
    @Mapping(target = "motherBoard.id", source = "motherboardId")
    @Mapping(target = "motherBoard.model", source = "motherboardModel")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "ipAdress", source = "ipAdress")
    @Mapping(target = "cpu.id", source = "cpuId")
    @Mapping(target = "cpu.model", source = "cpuModel")
    @Mapping(target = "cpu.core", source = "cpuCore")
    @Mapping(target = "cpu.freq", source = "cpuFreq")
    @Mapping(target = "ram.id", source = "ramId")
    @Mapping(target = "ram.model", source = "ramModel")
    @Mapping(target = "ram.capacity", source = "ramCapacity")
    @Mapping(target = "hdd.id", source = "hddId")
    @Mapping(target = "hdd.model", source = "hddModel")
    @Mapping(target = "hdd.capacity", source = "hddCapacity")
    @Mapping(target = "hdd.unit", source = "hddUnit")
    @Mapping(target = "hdd.serialNumber", source = "hddSerialNumber")
    @Mapping(target = "hdd.inventaryNumber", source = "hddInventaryNumber")
    @Mapping(target = "videoCard.id", source = "videoCardId")
    @Mapping(target = "videoCard.model", source = "videoCardModel")
    @Mapping(target = "cdDrive.id", source = "cdDriveId")
    @Mapping(target = "cdDrive.model", source = "cdDriveModel")
    @Mapping(target = "soundCard.id", source = "soundCardId")
    @Mapping(target = "soundCard.model", source = "soundCardModel")
    @Mapping(target = "lanCard.id", source = "lanCardId")
    @Mapping(target = "lanCard.model", source = "lanCardModel")
    @Mapping(target = "keyboard.id", source = "keyboardId")
    @Mapping(target = "keyboard.model", source = "keyboardModel")
    @Mapping(target = "mouse.id", source = "mouseId")
    @Mapping(target = "mouse.model", source = "mouseModel")
    @Mapping(target = "speakers.id", source = "speakersId")
    @Mapping(target = "speakers.model", source = "speakersModel")
    @Override
    public abstract SystemBlock getEntityFromDto(SvtSystemBlockDTO dto);
    
}
