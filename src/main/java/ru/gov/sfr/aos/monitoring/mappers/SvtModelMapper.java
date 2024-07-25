/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.gov.sfr.aos.monitoring.entities.CdDrive;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Keyboard;
import ru.gov.sfr.aos.monitoring.entities.LanCard;
import ru.gov.sfr.aos.monitoring.entities.MonitorModel;
import ru.gov.sfr.aos.monitoring.entities.Motherboard;
import ru.gov.sfr.aos.monitoring.entities.Mouse;
import ru.gov.sfr.aos.monitoring.entities.PhoneModel;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.entities.ScannerModel;
import ru.gov.sfr.aos.monitoring.entities.ServerModel;
import ru.gov.sfr.aos.monitoring.entities.SoundCard;
import ru.gov.sfr.aos.monitoring.entities.Speakers;
import ru.gov.sfr.aos.monitoring.entities.SwitchHubModel;
import ru.gov.sfr.aos.monitoring.entities.SystemBlockModel;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.entities.VideoCard;
import ru.gov.sfr.aos.monitoring.models.CpuModelDto;
import ru.gov.sfr.aos.monitoring.models.HddDto;
import ru.gov.sfr.aos.monitoring.models.RamDto;
import ru.gov.sfr.aos.monitoring.models.SvtModelDto;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class SvtModelMapper {
    public abstract PhoneModel getPhoneModel (SvtModelDto dto);
    public abstract MonitorModel getMonitorModel (SvtModelDto dto);
    public abstract UpsModel getUpsModel (SvtModelDto dto);
    public abstract SvtModelDto getPhoneModelDto(PhoneModel phoneModel);
    public abstract SvtModelDto getMonitorModelDto(MonitorModel monitorModel);
    public abstract SvtModelDto getUpsModelDto(UpsModel upsModel);
    public abstract List<PhoneModel> getPhoneModelList(List<SvtModelDto> dtoes);
    public abstract List<MonitorModel> getMonitorModelList(List<SvtModelDto> dtoes);
    public abstract List<UpsModel> getUpsModelList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getPhoneModelsDtoes(List<PhoneModel> phoneModels);
    public abstract List<SvtModelDto> getMonitorModelsDtoes(List<MonitorModel> monitorModels);
    public abstract List<SvtModelDto> getUpsModelsDtoes(List<UpsModel> monitorModels);
    
    public abstract SystemBlockModel getSystemBlockModel (SvtModelDto dto);
    public abstract SvtModelDto getSystemBlockModelDto(SystemBlockModel systemBlockModel);
    public abstract List<SystemBlockModel> getSystemBLockModelList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getSystemBlockModelsDtoes (List<SystemBlockModel> systemBlockModels);
    
    public abstract VideoCard getVideoCard (SvtModelDto dto);
    public abstract SvtModelDto getVideoCardDto(VideoCard videoCard);
    public abstract List<VideoCard> getVideoCardList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getVideoCardDtoes (List<VideoCard> videoCardList);
    
    public abstract CdDrive getCdDrive (SvtModelDto dto);
    public abstract SvtModelDto getCdDriveDto(CdDrive cdDrive);
    public abstract List<CdDrive> getCdDriveList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getCdDriveDtoes (List<CdDrive> cdDriveList);
    
    public abstract SoundCard getSoundCard (SvtModelDto dto);
    public abstract SvtModelDto getSoundCardDto(SoundCard soundCard);
    public abstract List<SoundCard> getSoundCardList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getSoundCardDtoes (List<SoundCard> soundCardList);
    
    public abstract LanCard getLanCard (SvtModelDto dto);
    public abstract SvtModelDto getLanCardDto(LanCard lanCard);
    public abstract List<LanCard> getLanCardList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getLanCardDtoes (List<LanCard> lanCardList);
    
    public abstract Keyboard getKeyboard(SvtModelDto dto);
    public abstract SvtModelDto getKeyboardDto(Keyboard keyboard);
    public abstract List<Keyboard> getKeyboardList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getKeyboardDtoes (List<Keyboard> keyboardList);
    
    
    public abstract Mouse getMouse(SvtModelDto dto);
    public abstract SvtModelDto getMouseDto(Mouse mouse);
    public abstract List<Keyboard> getMouseList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getMouseDtoes (List<Mouse> mouseList);
    
    public abstract Speakers getSpeakers(SvtModelDto dto);
    public abstract SvtModelDto getSpeakersDto(Speakers speakers);
    public abstract List<Speakers> getSpeakersList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getSpeakersDtoes (List<Speakers> speakersList);
    
    public abstract Motherboard getModelMotherboard (SvtModelDto dto);
    public abstract SvtModelDto getModelMotherboardDto(Motherboard motherboard);
    public abstract List<Motherboard> getModelMotherboardList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelMotherboardModelsDtoes (List<Motherboard> motherboardModels);
    
    public abstract Cpu getCpu (CpuModelDto dto);
    public abstract CpuModelDto getCpuDto(Cpu cpu);
    public abstract List<Cpu> getCpuList(List<CpuModelDto> dtoes);
    public abstract List<CpuModelDto> getCpuModelDtoes (List<Cpu> cpuList);
    
    
    public abstract Ram getRam (RamDto dto);
    public abstract RamDto getCpuDto(Ram ram);
    public abstract List<Ram> getRamList(List<RamDto> dtoes);
    public abstract List<RamDto> getRamDtoes (List<Ram> ramList);
    
    
    public abstract Hdd getHdd (HddDto dto);
    public abstract HddDto getHddDto(Hdd hdd);
    public abstract List<Hdd> getHddList(List<HddDto> dtoes);
    public abstract List<HddDto> getHddDtoes (List<Hdd> ramList);
    
    
    
    public abstract ScannerModel getModelScanner (SvtModelDto dto);
    public abstract SvtModelDto getModelScannerDto(ScannerModel scannerModel);
    public abstract List<ScannerModel> getModelScannerList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelScannerDtoes (List<ScannerModel> modelScannerList);
    
    public abstract ServerModel getModelServer (SvtModelDto dto);
    public abstract SvtModelDto getModelServerDto(ServerModel serverModel);
    public abstract List<ServerModel> getModelServerList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelServerDtoes (List<ServerModel> modelServerList);
    
    public abstract SwitchHubModel getModelSwitchHub (SvtModelDto dto);
    public abstract SvtModelDto getModelSwitchHubDto(SwitchHubModel switchHubModel);
    public abstract List<SwitchHubModel> getModelSwitchHubList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelSwitchHubDtoes (List<SwitchHubModel> modelSwitchHubList);
}
