/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.svtobject;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.gov.sfr.aos.monitoring.ats.AtsModel;
import ru.gov.sfr.aos.monitoring.components.CdDrive;
import ru.gov.sfr.aos.monitoring.conditioner.ConditionerModel;
import ru.gov.sfr.aos.monitoring.components.Cpu;
import ru.gov.sfr.aos.monitoring.asuo.tv.DisplayModel;
import ru.gov.sfr.aos.monitoring.fax.FaxModel;
import ru.gov.sfr.aos.monitoring.components.Hdd;
import ru.gov.sfr.aos.monitoring.infomat.InfomatModel;
import ru.gov.sfr.aos.monitoring.components.Keyboard;
import ru.gov.sfr.aos.monitoring.components.LanCard;
import ru.gov.sfr.aos.monitoring.monitor.MonitorModel;
import ru.gov.sfr.aos.monitoring.components.Motherboard;
import ru.gov.sfr.aos.monitoring.components.Mouse;
import ru.gov.sfr.aos.monitoring.phone.PhoneModel;
import ru.gov.sfr.aos.monitoring.components.Ram;
import ru.gov.sfr.aos.monitoring.router.RouterModel;
import ru.gov.sfr.aos.monitoring.scanner.ScannerModel;
import ru.gov.sfr.aos.monitoring.server.ServerModel;
import ru.gov.sfr.aos.monitoring.components.SoundCard;
import ru.gov.sfr.aos.monitoring.components.Speakers;
import ru.gov.sfr.aos.monitoring.asuo.subdisplay.SubDisplayModel;
import ru.gov.sfr.aos.monitoring.switchhub.SwitchHubModel;
import ru.gov.sfr.aos.monitoring.systemblock.SystemBlockModel;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalModel;
import ru.gov.sfr.aos.monitoring.ups.UpsModel;
import ru.gov.sfr.aos.monitoring.components.VideoCard;
import ru.gov.sfr.aos.monitoring.components.CpuModelDto;
import ru.gov.sfr.aos.monitoring.components.HddDto;
import ru.gov.sfr.aos.monitoring.components.RamDto;

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
    public abstract Set<SvtModelDto> getPhoneModelsDtoes(Set<PhoneModel> phoneModels);
    public abstract Set<SvtModelDto> getMonitorModelsDtoes(Set<MonitorModel> monitorModels);
    public abstract Set<SvtModelDto> getUpsModelsDtoes(Set<UpsModel> monitorModels);
    
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
    
    public abstract RouterModel getModelRouter (SvtModelDto dto);
    public abstract SvtModelDto getModelRouterDto(RouterModel routerModel);
    public abstract List<RouterModel> getModelRouterList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelRouterDtoes (List<RouterModel> modelRouterList);
    
    public abstract AtsModel getModelAts (SvtModelDto dto);
    public abstract SvtModelDto getModelAtsDto(AtsModel atsModel);
    public abstract List<AtsModel> getModelAtsList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelAtsDtoes (List<AtsModel> modelAtsList);
    
    public abstract ConditionerModel getModelConditioner (SvtModelDto dto);
    public abstract SvtModelDto getModelConditionerDto(ConditionerModel conditionerModel);
    public abstract List<ConditionerModel> getModelConditionerList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelConditionerDtoes (List<ConditionerModel> modelConditionerList);
    
    public abstract InfomatModel getModelInfomat (SvtModelDto dto);
    public abstract SvtModelDto getModelInfomatDto(InfomatModel infomatModel);
    public abstract List<InfomatModel> getModelInfomatList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelInfomatDtoes (List<InfomatModel> modelInfomatList);
    
    public abstract TerminalModel getModelTerminal (SvtModelDto dto);
    public abstract SvtModelDto getModelTerminalDto(TerminalModel terminalModel);
    public abstract List<TerminalModel> getModelTerminalList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelTerminalDtoes (List<TerminalModel> modelTerminalList);
    
    
    public abstract DisplayModel getModelDisplay (SvtModelDto dto);
    public abstract SvtModelDto getModelDisplayDto(DisplayModel displayModel);
    public abstract List<DisplayModel> getModelDisplayList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelDisplayDtoes (List<DisplayModel> modelDisplayList);

    
    public abstract SubDisplayModel getModelSubDisplay (SvtModelDto dto);
    public abstract SvtModelDto getModelSubDisplayDto(SubDisplayModel subDisplayModel);
    public abstract List<SubDisplayModel> getModelSubDisplayList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelSubDisplayDtoes (List<SubDisplayModel> modelSubDisplayList);
    
    public abstract FaxModel getModelFax (SvtModelDto dto);
    public abstract SvtModelDto getModelFaxDto(FaxModel faxModel);
    public abstract List<FaxModel> getModelFaxList(List<SvtModelDto> dtoes);
    public abstract List<SvtModelDto> getModelFaxDtoes (List<FaxModel> modelFaxList);
    
}
