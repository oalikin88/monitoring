/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.systemblock;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import ru.gov.sfr.aos.monitoring.components.CdDriveRepo;
import ru.gov.sfr.aos.monitoring.components.CpuRepo;
import ru.gov.sfr.aos.monitoring.components.HddRepo;
import ru.gov.sfr.aos.monitoring.components.KeyboardRepo;
import ru.gov.sfr.aos.monitoring.components.LanCardRepo;
import ru.gov.sfr.aos.monitoring.components.MotherboardRepo;
import ru.gov.sfr.aos.monitoring.components.MouseRepo;
import ru.gov.sfr.aos.monitoring.components.RamRepo;
import ru.gov.sfr.aos.monitoring.components.SoundCardRepo;
import ru.gov.sfr.aos.monitoring.components.SpeakersRepo;
import ru.gov.sfr.aos.monitoring.components.VideoCardRepo;
import ru.gov.sfr.aos.monitoring.repositories.OperationSystemRepo;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;

/**
 *
 * @author 041AlikinOS
 */

@Mapper(componentModel = "spring")
public abstract class SystemBlockMapper implements SvtMapper <SystemBlock, SvtSystemBlockDTO> {
    @Autowired
    protected SystemBlockRepo systemblockRepo;
    @Autowired
    protected SystemBlockModelRepo systemblockModelRepo;
    @Autowired
    protected MotherboardRepo motherboardRepo;
    @Autowired
    protected CpuRepo cpuRepo;
    @Autowired
    protected RamRepo ramRepo;
    @Autowired
    protected HddRepo hddRepo;
    @Autowired
    protected VideoCardRepo videoCardRepo;
    @Autowired
    protected SoundCardRepo soundCardRepo;
    @Autowired
    protected LanCardRepo lanCardRepo;
    @Autowired
    protected CdDriveRepo cdDriveRepo;
    @Autowired
    protected KeyboardRepo keyboardRepo;
    @Autowired
    protected MouseRepo mouseRepo;
    @Autowired
    protected SpeakersRepo speakersRepo;
    @Autowired
    protected OperationSystemRepo operationSystemRepo;
   
    
    
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
    @Mapping(source = "systemBlockModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "systemBlockModel.manufacturer.id", target = "manufacturerId")
    @Mapping(expression = "java(systemBlock.getOperationSystems().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toList()))", target = "operationSystemId")
    @Mapping(expression = "java(systemBlock.getHdd().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toList()))", target = "hddIdList")
    @Override
    public abstract SvtSystemBlockDTO getDto(SystemBlock systemBlock);
    
    @Mapping(target = "contract", expression = "java(systemblockRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "systemBlockModel", expression = "java(systemblockModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "nameFromOneC", source = "nameFromOneC")
    @Mapping(target = "dateUpgrade", source = "dateUpgrade")
    @Mapping(target = "motherBoard", expression = "java(motherboardRepo.findById(dto.getMotherboardId()).get())")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "ipAdress", source = "ipAdress")
    @Mapping(target = "cpu", expression = "java(cpuRepo.findById(dto.getCpuId()).get())")
    @Mapping(target = "ram", expression = "java(ramRepo.findById(dto.getRamId()).get())")
    @Mapping(target = "videoCard", expression = "java(videoCardRepo.findById(dto.getVideoCardId()).get())")
    @Mapping(target = "cdDrive", expression = "java(cdDriveRepo.findById(dto.getCdDriveId()).get())")
    @Mapping(target = "soundCard", expression = "java(soundCardRepo.findById(dto.getSoundCardId()).get())")
    @Mapping(target = "lanCard", expression = "java(lanCardRepo.findById(dto.getLanCardId()).get())")
    @Mapping(target = "keyboard", expression = "java(keyboardRepo.findById(dto.getKeyboardId()).get())")
    @Mapping(target = "mouse", expression = "java(mouseRepo.findById(dto.getMouseId()).get())")
    @Mapping(target = "speakers", expression = "java(speakersRepo.findById(dto.getSpeakersId()).get())")
    @Mapping(target = "operationSystems", expression = "java(operationSystemRepo.findAllById(dto.getOperationSystemId()).stream().collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "hdd", expression = "java(hddRepo.findAllById(dto.getHddIdList()).stream().collect(java.util.stream.Collectors.toSet()))")
    @Override
    public abstract SystemBlock getEntityFromDto(SvtSystemBlockDTO dto);
    
}
