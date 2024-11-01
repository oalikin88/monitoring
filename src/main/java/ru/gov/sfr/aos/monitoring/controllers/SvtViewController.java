/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gov.sfr.aos.monitoring.anotations.Log;
import ru.gov.sfr.aos.monitoring.anotations.SendArchive;
import ru.gov.sfr.aos.monitoring.anotations.UpdLog;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Asuo;
import ru.gov.sfr.aos.monitoring.entities.Ats;
import ru.gov.sfr.aos.monitoring.entities.AtsModel;
import ru.gov.sfr.aos.monitoring.entities.BatteryType;
import ru.gov.sfr.aos.monitoring.entities.CdDrive;
import ru.gov.sfr.aos.monitoring.entities.Conditioner;
import ru.gov.sfr.aos.monitoring.entities.ConditionerModel;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Display;
import ru.gov.sfr.aos.monitoring.entities.DisplayModel;
import ru.gov.sfr.aos.monitoring.entities.Fax;
import ru.gov.sfr.aos.monitoring.entities.FaxModel;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Infomat;
import ru.gov.sfr.aos.monitoring.entities.InfomatModel;
import ru.gov.sfr.aos.monitoring.entities.Keyboard;
import ru.gov.sfr.aos.monitoring.entities.LanCard;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Monitor;
import ru.gov.sfr.aos.monitoring.entities.MonitorModel;
import ru.gov.sfr.aos.monitoring.entities.Motherboard;
import ru.gov.sfr.aos.monitoring.entities.Mouse;
import ru.gov.sfr.aos.monitoring.entities.OperationSystem;
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.entities.PhoneModel;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.entities.Router;
import ru.gov.sfr.aos.monitoring.entities.RouterModel;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.entities.ScannerModel;
import ru.gov.sfr.aos.monitoring.entities.Server;
import ru.gov.sfr.aos.monitoring.entities.ServerModel;
import ru.gov.sfr.aos.monitoring.entities.SoundCard;
import ru.gov.sfr.aos.monitoring.entities.Speakers;
import ru.gov.sfr.aos.monitoring.entities.SubDisplayModel;
import ru.gov.sfr.aos.monitoring.entities.SwitchHub;
import ru.gov.sfr.aos.monitoring.entities.SwitchHubModel;
import ru.gov.sfr.aos.monitoring.entities.SwitchingUnit;
import ru.gov.sfr.aos.monitoring.entities.SwitchingUnitModel;
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.entities.SystemBlockModel;
import ru.gov.sfr.aos.monitoring.entities.Terminal;
import ru.gov.sfr.aos.monitoring.entities.TerminalModel;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinter;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinterModel;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.entities.VideoCard;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.mappers.AsuoMapper;
import ru.gov.sfr.aos.monitoring.mappers.AtsMapper;
import ru.gov.sfr.aos.monitoring.mappers.BatteryTypeMapper;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerMapper;
import ru.gov.sfr.aos.monitoring.mappers.DisplayMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorMapper;
import ru.gov.sfr.aos.monitoring.mappers.OperationSystemMapper;
import ru.gov.sfr.aos.monitoring.mappers.PhoneMapper;
import ru.gov.sfr.aos.monitoring.mappers.RouterMapper;
import ru.gov.sfr.aos.monitoring.mappers.ScannerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ServerMapper;
import ru.gov.sfr.aos.monitoring.mappers.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchHubMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchingUnitMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemBlockMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalMapper;
import ru.gov.sfr.aos.monitoring.mappers.ThermoprinterMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsMapper;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.AsuoDTO;
import ru.gov.sfr.aos.monitoring.models.BatteryTypeDto;
import ru.gov.sfr.aos.monitoring.models.CpuModelDto;
import ru.gov.sfr.aos.monitoring.models.DepartmentDTO;
import ru.gov.sfr.aos.monitoring.models.FaxDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.HddDto;
import ru.gov.sfr.aos.monitoring.models.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.models.PlaceDTO;
import ru.gov.sfr.aos.monitoring.models.RamDto;
import ru.gov.sfr.aos.monitoring.models.SvtAtsDTO;
import ru.gov.sfr.aos.monitoring.models.SvtConditionerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.models.SvtModelDto;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtServerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSystemBlockDTO;
import ru.gov.sfr.aos.monitoring.repositories.BatteryTypeRepo;
import ru.gov.sfr.aos.monitoring.services.AsuoOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.AsuoService;
import ru.gov.sfr.aos.monitoring.services.AtsModelService;
import ru.gov.sfr.aos.monitoring.services.AtsOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.AtsService;
import ru.gov.sfr.aos.monitoring.services.BatteryTypeService;
import ru.gov.sfr.aos.monitoring.services.CdDriveModelService;
import ru.gov.sfr.aos.monitoring.services.ConditionerModelService;
import ru.gov.sfr.aos.monitoring.services.ConditionerOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.ConditionerService;
import ru.gov.sfr.aos.monitoring.services.CpuModelService;
import ru.gov.sfr.aos.monitoring.services.DepartmentService;
import ru.gov.sfr.aos.monitoring.services.DisplayModelService;
import ru.gov.sfr.aos.monitoring.services.DisplayOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.DisplayService;
import ru.gov.sfr.aos.monitoring.services.FaxModelService;
import ru.gov.sfr.aos.monitoring.services.FaxOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.FaxService;
import ru.gov.sfr.aos.monitoring.services.HddModelService;
import ru.gov.sfr.aos.monitoring.services.InfomatModelService;
import ru.gov.sfr.aos.monitoring.services.InfomatOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.InfomatService;
import ru.gov.sfr.aos.monitoring.services.KeyboardModelService;
import ru.gov.sfr.aos.monitoring.services.LanCardModelService;
import ru.gov.sfr.aos.monitoring.services.MonitorModelService;
import ru.gov.sfr.aos.monitoring.services.MonitorOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.MonitorService;
import ru.gov.sfr.aos.monitoring.services.MotherboardModelService;
import ru.gov.sfr.aos.monitoring.services.MouseModelService;
import ru.gov.sfr.aos.monitoring.services.OperationSystemService;
import ru.gov.sfr.aos.monitoring.services.PhoneModelService;
import ru.gov.sfr.aos.monitoring.services.PhoneOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.PhoneService;
import ru.gov.sfr.aos.monitoring.services.PlaceService;
import ru.gov.sfr.aos.monitoring.services.RamModelService;
import ru.gov.sfr.aos.monitoring.services.RouterModelService;
import ru.gov.sfr.aos.monitoring.services.RouterOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.RouterService;
import ru.gov.sfr.aos.monitoring.services.ScannerModelService;
import ru.gov.sfr.aos.monitoring.services.ScannerOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.ScannerService;
import ru.gov.sfr.aos.monitoring.services.ServerModelService;
import ru.gov.sfr.aos.monitoring.services.ServerOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.ServerService;
import ru.gov.sfr.aos.monitoring.services.SoundCardModelService;
import ru.gov.sfr.aos.monitoring.services.SpeakersModelService;
import ru.gov.sfr.aos.monitoring.services.SubDisplayModelService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubModelService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubService;
import ru.gov.sfr.aos.monitoring.services.SwitchingUnitModelService;
import ru.gov.sfr.aos.monitoring.services.SwitchingUnitOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.SwitchingUnitService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockModelService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockService;
import ru.gov.sfr.aos.monitoring.services.TerminalModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.TerminalService;
import ru.gov.sfr.aos.monitoring.services.ThermoprinterModelService;
import ru.gov.sfr.aos.monitoring.services.ThermoprinterOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.ThermoprinterService;
import ru.gov.sfr.aos.monitoring.services.UpsModelService;
import ru.gov.sfr.aos.monitoring.services.UpsOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.UpsService;
import ru.gov.sfr.aos.monitoring.services.VideoCardModelService;

/**
 *
 * @author 041AlikinOS
 */
@Controller
public class SvtViewController {
    
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;
    @Autowired
    private PhoneModelService phoneModelService;
    @Autowired
    private MonitorModelService monitorModelService;
    @Autowired
    private PhoneOutDtoTreeService phoneOutDtoTreeService;
    @Autowired
    private MonitorOutDtoTreeService monitorOutDtoTreeService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private MonitorMapper monitorMapper;
    @Autowired
    private UpsService upsService;
    @Autowired
    private UpsMapper upsMapper;
    @Autowired
    private UpsOutDtoTreeService upsOutDtoTreeService;
    @Autowired
    private UpsModelService upsModelService;
    @Autowired
    private BatteryTypeService batteryTypeService;
    @Autowired
    private BatteryTypeMapper batteryTypeMapper;
    @Autowired
    private BatteryTypeRepo batteryTypeRepo;
    @Autowired
    private SystemBlockModelService systemBlockModelService;
    @Autowired
    private SystemBlockService systemblockService;
    @Autowired
    private SystemBlockOutDtoTreeService systemBlockOutDtoTreeService;
    @Autowired
    private SystemBlockMapper systemblockMapper;
    @Autowired
    private MotherboardModelService motherboardModelService;
    @Autowired
    private CpuModelService cpuModelService;
    @Autowired
    private RamModelService ramModelService;
    @Autowired
    private HddModelService hddModelService;
    @Autowired
    private VideoCardModelService videoCardModelService;
    @Autowired
    private CdDriveModelService cdDriveModelService;
    @Autowired
    private SoundCardModelService soundCardModelService;
    @Autowired
    private LanCardModelService lanCardModelService;
    @Autowired
    private KeyboardModelService keyboardModelService;
    @Autowired
    private MouseModelService mouseModelService;
    @Autowired
    private SpeakersModelService speakersModelService;
    @Autowired
    private OperationSystemService operationSystemService;
    @Autowired
    private OperationSystemMapper operationSystemMapper;
    @Autowired
    private ScannerModelService scannerModelService;
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private ScannerOutDtoTreeService scannerOutDtoTreeService;
    @Autowired
    private ScannerMapper scannerMapper;
    @Autowired
    private ServerModelService serverModelService;
    @Autowired
    private ServerService serverService;
    @Autowired
    private ServerOutDtoTreeService serverOutDtoTreeService;
    @Autowired
    private ServerMapper serverMapper;
    @Autowired
    private SwitchHubModelService switchHubModelService;
    @Autowired
    private SwitchHubService switchHubService;
    @Autowired
    private SwitchHubOutDtoTreeService switchHubOutDtoTreeService;
    @Autowired
    private SwitchHubMapper switchHubMapper;
    @Autowired
    private RouterModelService routerModelService;
    @Autowired
    private RouterService routerService;
    @Autowired
    private RouterMapper routerMapper;
    @Autowired
    private RouterOutDtoTreeService routerOutDtoTreeService;
    @Autowired
    private AtsModelService atsModelService;
    @Autowired
    private AtsService atsService;
    @Autowired
    private AtsOutDtoTreeService atsOutDtoTreeService;
    @Autowired
    private AtsMapper atsMapper;
    @Autowired
    private ConditionerModelService conditionerModelService;
    @Autowired
    private ConditionerService conditionerService;
    @Autowired
    private ConditionerOutDtoTreeService conditionerOutDtoTreeService;
    @Autowired
    private ConditionerMapper conditionerMapper;
    @Autowired
    private InfomatModelService infomatModelService;
    @Autowired
    private InfomatOutDtoTreeService infomatOutDtoTreeService;
    @Autowired
    private InfomatService infomatService;
    @Autowired
    private InfomatMapper infomatMapper;
    @Autowired
    private TerminalModelService terminalModelService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private TerminalOutDtoTreeService terminalOutDtoTreeService;
    @Autowired
    private TerminalMapper terminalMapper;
    @Autowired
    private ThermoprinterModelService thermoprinterModelService;
    @Autowired
    private ThermoprinterService thermoprinterService;
    @Autowired
    private ThermoprinterOutDtoTreeService thermoprinterOutDtoTreeService;
    @Autowired
    private ThermoprinterMapper thermoprinterMapper;
    @Autowired
    private DisplayModelService displayModelService;
    @Autowired
    private DisplayService displayService;
    @Autowired
    private DisplayOutDtoTreeService displayOutDtoTreeService;
    @Autowired
    private DisplayMapper displayMapper;
    @Autowired
    private SwitchingUnitModelService swunitModelService;
    @Autowired
    private SwitchingUnitService swunitService;
    @Autowired
    private SwitchingUnitOutDtoTreeService swunitOutDtoTreeService;
    @Autowired
    private SwitchingUnitMapper swunitMapper;
    @Autowired
    private SubDisplayModelService subDisplayModelService;
    @Autowired
    private AsuoService asuoService;
    @Autowired
    private AsuoOutDtoTreeService asuoOutDtoTreeService;
    @Autowired
    private AsuoMapper asuoMapper;
    @Autowired
    private FaxModelService faxModelService;
    @Autowired
    private FaxService faxService;
    @Autowired
    private FaxOutDtoTreeService faxOutDtoTreeService;
    @Autowired
    private FaxMapper faxMapper;
    
//  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/svt")
    public String getSvt(Model model) {

        return "svt";
    }

 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/places")
    public String getPlaces(Model model, @RequestParam(value = "username", required = false) String username) {
        List<PlaceDTO> places = null;
        if(null != username) {
            places = placeService.getPlaceByUsername(username);
        } else {
            places = placeService.getPlaces();
        }
        model.addAttribute("dtoes", places);
        
        return "places";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/places")
    public String addPlace(@RequestBody PlaceDTO dto) {

        if(null != dto.getPlaceId()) {
            placeService.updatePlace(dto);
        } else {
            placeService.createPlace(dto);
        }
        return "redirect:/places";
    }
    @SendArchive
    @PostMapping("/placetoarchive")
    public String sendPlaceToArchived(@RequestBody PlaceDTO dto) {
        placeService.sendPlaceToArchive(dto.getPlaceId());
        return "redirect:/places";
    }

    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mphones")
    public String getModelPhones(Model model) {

        List<PhoneModel> phoneModels = phoneModelService.getAllActualModels();
        List<SvtModelDto> phoneModelsDtoes = svtModelMapper.getPhoneModelsDtoes(phoneModels);
        model.addAttribute("dtoes", phoneModelsDtoes);
        model.addAttribute("namePage", "Модели телефонов");
        model.addAttribute("attribute", "mphones");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mphones")
    public String saveModelPhone(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        PhoneModel phoneModel = svtModelMapper.getPhoneModel(dto);
        phoneModelService.saveModel(phoneModel);
        return "redirect:/mphones";
        
    }
    
    @SendArchive
    @PostMapping("/mphonesarchived")
    public String sendModelPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        phoneModelService.sendModelToArchive(dto.getId());
        return "redirect:/mphones";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmonitors")
    public String getModelMonitors(Model model) {

        List<MonitorModel> monitorModels = monitorModelService.getAllActualModels();
        List<SvtModelDto> monitorModelsDtoes = svtModelMapper.getMonitorModelsDtoes(monitorModels);
        model.addAttribute("dtoes", monitorModelsDtoes);
        model.addAttribute("namePage", "Модели мониторов");
        model.addAttribute("attribute", "mmonitors");
        
        return "models";
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/mmonitorsarchived")
    public String sendModelMonitorToArchive(@RequestBody ArchivedDto dto) {
        monitorModelService.sendModelToArchive(dto.getId());
        return "redirect:/mmonitors";
    }
 
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mmonitors")
    public String saveModelMonitor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        MonitorModel monitorModel = svtModelMapper.getMonitorModel(dto);
        monitorModelService.saveModel(monitorModel);
        return "redirect:/mmonitors";
        
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/mmonitorsupd")
    public String updateModelMonitor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        MonitorModel monitorModel = svtModelMapper.getMonitorModel(dto);
        monitorModelService.update(monitorModel);
        return "redirect:/mmonitors";
        
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mups")
    public String getModelUps(Model model) {

        List<UpsModel> upsModels = upsModelService.getAllActualModels();
        List<SvtModelDto> upsModelsDtoes = svtModelMapper.getUpsModelsDtoes(upsModels);
        model.addAttribute("dtoes", upsModelsDtoes);
        model.addAttribute("namePage", "Модели ИБП");
        model.addAttribute("attribute", "mups");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mups")
    public String saveModelUps(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        UpsModel upsModel = svtModelMapper.getUpsModel(dto);
        upsModelService.saveModel(upsModel);
        return "redirect:/mups";
        
    }
    
    @SendArchive
    @PostMapping("/mupsarchived")
    public String sendModelUpsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        upsModelService.sendModelToArchive(dto.getId());
        return "redirect:/mups";
        
    }
    
    
 //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mupsbat")
    public String getBatteryTypeUps(Model model) {

        List<BatteryType> allBatteryTypes = batteryTypeService.getAllBatteryTypes();
        List<BatteryTypeDto> batteryTypeDtoesList = batteryTypeMapper.getBatteryTypeDtoesList(allBatteryTypes);
        model.addAttribute("dtoes", batteryTypeDtoesList);
        model.addAttribute("namePage", "Типы батарей для ИБП");
        model.addAttribute("attribute", "mupsbat");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mupsbat")
    public String saveBatteryTypeUps(@RequestBody BatteryTypeDto dto) throws ObjectAlreadyExists {
        BatteryType batteryType = batteryTypeMapper.getBatteryType(dto);
        batteryTypeService.saveBatteryType(batteryType);
        return "redirect:/mupsbat";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/dep")
    public String getDepartments(Model model) {

        List<DepartmentDTO> departments = departmentService.getDepartments();
        model.addAttribute("dtoes", departments);
        
        return "departments";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/phones")
    public String getPhones(Model model, @RequestParam(value="username", required=false) String username, @ModelAttribute FilterDto dto) {
        List<SvtDTO> filter = null;
        Map<Location, List<Phone>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Phone>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = phoneService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = phoneService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        
        treeSvtDtoByEmployee = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = phoneService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = phoneService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        
          treeSvtDtoByStorage = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        } else {
            List<Phone> phonesByFilter = phoneService.getPhonesByFilter(dto);
             filter = new ArrayList<>();
            for(Phone p : phonesByFilter) {
                SvtDTO phoneDto = phoneMapper.getDto(p);
                filter.add(phoneDto);
            }
            
            svtObjectsByEmployee = phoneService.getPhonesByPlaceTypeAndFilter(PlaceType.EMPLOYEE, phonesByFilter);
            treeSvtDtoByEmployee = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = phoneService.getPhonesByPlaceTypeAndFilter(PlaceType.STORAGE, phonesByFilter);
               treeSvtDtoByStorage = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        }
        
      
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "phones");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Телефоны");
        
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/ups")
    public String getUps(Model model, @RequestParam(value="username",required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
         treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
           svtObjectsByStorage = upsService.getSvtObjectsByPlaceType(PlaceType.STORAGE); 
        }
        
           treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        }else {
        
        
               List<Ups> upsByFilter = upsService.getUpsByFilter(dto);
             filter = new ArrayList<>();
            for(Ups p : upsByFilter) {
                SvtDTO upsDto = upsMapper.getDto(p);
                filter.add(upsDto);
            }
            
            svtObjectsByEmployee = upsService.getUpsByPlaceTypeAndFilter(PlaceType.EMPLOYEE, upsByFilter);
            treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = upsService.getUpsByPlaceTypeAndFilter(PlaceType.STORAGE, upsByFilter);
               treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
      
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "ups");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","ИБП");
        
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/ups")
    public String saveUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.createSvtObj(dto);
        return "redirect:/ups";
     
    }
    
    @SendArchive
    @PostMapping("/upsarchived")
    public String sendUpsToArchive(@RequestBody ArchivedDto dto) {
        upsService.svtObjToArchive(dto);
        return "redirect:/ups";
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/upsforserver")
    public String getServerUps(Model model, @RequestParam(value="username", required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
         List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
           svtObjectsByStorage = upsService.getSvtObjectsByPlaceType(PlaceType.STORAGE); 
        }
        
        treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        } else {
        
            List<Ups> upsByFilter = upsService.getUpsByFilter(dto);
             filter = new ArrayList<>();
            for(Ups p : upsByFilter) {
                SvtDTO upsDto = upsMapper.getDto(p);
                filter.add(upsDto);
            }
            
            svtObjectsByEmployee = upsService.getUpsByPlaceTypeAndFilter(PlaceType.SERVERROOM, upsByFilter);
            treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = upsService.getUpsByPlaceTypeAndFilter(PlaceType.STORAGE, upsByFilter);
               treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        }
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "ups");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","ИБП");
        
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/upsforserver")
    public String saveServerUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.createSvtObj(dto);
        return "redirect:/upsforserver";
     
    }
    
    @SendArchive
    @PostMapping("/upsforserverarchived")
    public String sendUpsForServerToArchive(@RequestBody ArchivedDto dto) {
        upsService.svtObjToArchive(dto);
        return "redirect:/upsforserver";
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/updupsforserver")
    public String updateServerUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.updateSvtObj(dto);
        return "redirect:/upsforserver";
     
    }
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/phones")
    public String savePhone(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        phoneService.createSvtObj(dto);
        return "redirect:/phones";
     
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/updphone")
    public String updatePhone(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        phoneService.updateSvtObj(dto);
        return "redirect:/phones";
     
    }
    
  //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/updups")
    public String updateUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.updateSvtObj(dto);
        return "redirect:/ups";
     
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/updmonitor")
    public String updateMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        monitorService.updateSvtObj(dto);
        return "redirect:/monitors";
     
    }
    @SendArchive
   @PostMapping("/monitorsarchived") 
   public String sendMonitorToArchive(@RequestBody ArchivedDto dto) {
        monitorService.svtObjToArchive(dto);
        return "redirect:/monitors";
    }
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
   @UpdLog
    @PostMapping("/phonetostor")
    public String sendToStorage (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Phone phone = phoneMapper.getEntityFromDto(dto);
        phoneService.sendToStorage(phone);
        return "redirect:/phones";
        
    }
    
  //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/upstostor")
    public String sendToStorageUps (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Ups ups = upsMapper.getEntityFromDto(dto);
        BatteryType batteryType = batteryTypeRepo.findById(ups.getBatteryType().getId()).get();
        ups.setBatteryType(batteryType);
        upsService.sendToStorage(ups);
        return "redirect:/ups";
        
    }
    
  //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/monitortostor")
    public String sendToStorageMonitor (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Monitor monitor = monitorMapper.getEntityFromDto(dto);
        monitorService.sendToStorage(monitor);
        return "redirect:/monitors";
        
    }
    
 
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/upsbackstor")
    public String backFromStorageUps (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Ups ups = upsMapper.getEntityFromDto(dto);
        BatteryType batteryType = batteryTypeRepo.findById(ups.getBatteryType().getId()).get();
        ups.setBatteryType(batteryType);
        upsService.backFromStorage(ups, dto.getPlaceId());
        return "redirect:/ups";
        
    }
    

    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/monitors")
    public String getMonitors(Model model, @RequestParam(value="username",required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Monitor>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
         Map<Location, List<Monitor>> svtObjectsByStorage = null;
         List<LocationByTreeDto> treeSvtDtoByStorage = null;
         List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
        svtObjectsByEmployee = monitorService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else {
            svtObjectsByEmployee = monitorService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
        
         
        treeSvtDtoByEmployee = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
       
        if(null != username) {
             svtObjectsByStorage = monitorService.getSvtObjectsByName(username, PlaceType.STORAGE);
         } else {
            svtObjectsByStorage = monitorService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        } else {
        
        
             List<Monitor> monitorByFilter = monitorService.getMonitorByFilter(dto);
             filter = new ArrayList<>();
            for(Monitor p : monitorByFilter) {
                SvtDTO upsDto = monitorMapper.getDto(p);
                filter.add(upsDto);
            }
            
            svtObjectsByEmployee = monitorService.getMonitorByPlaceTypeAndFilter(PlaceType.EMPLOYEE, monitorByFilter);
            treeSvtDtoByEmployee = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = monitorService.getMonitorByPlaceTypeAndFilter(PlaceType.STORAGE, monitorByFilter);
               treeSvtDtoByStorage = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "monitors");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Мониторы");
        return "svtobj";
    }
  
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/monitors")
    public String saveMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        monitorService.createSvtObj(dto);
        return "redirect:/monitors";
    }
    

    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/phonesarchived")
    public String sendPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        phoneService.svtObjToArchive(dto);
        return "redirect:/phones";
    }   
    
    
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/msysblock")
    public String getModelSystemblock(Model model) {

        List<SystemBlockModel> systemBlockModels = systemBlockModelService.getAllActualModels();
        List<SvtModelDto> systemBlockModelsDtoes = svtModelMapper.getSystemBlockModelsDtoes(systemBlockModels);
        model.addAttribute("dtoes", systemBlockModelsDtoes);
        model.addAttribute("namePage", "Модели системных блоков");
        model.addAttribute("attribute", "msysblock");
        return "models";
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/msysblock")
    public String saveModelSystemBlock(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SystemBlockModel systemBlockModel = svtModelMapper.getSystemBlockModel(dto);
        systemBlockModelService.saveModel(systemBlockModel);
        return "redirect:/msysblock";
        
    }
    
    @SendArchive
    @PostMapping("/msysblockarchived")
    public String sendModelSystemBlockToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        systemBlockModelService.sendModelToArchive(dto.getId());
        return "redirect:/msysblock";
        
    }
    
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/sysblocks")
    public String getSysBlocks(Model model, @RequestParam(value="username",required=false) String username,  @ModelAttribute FilterDto dto) {
        Map<Location, List<SystemBlock>> svtObjectsByEmployee = null;
         List<SvtDTO> filter = null;
         List<LocationByTreeDto> treeSvtDtoByEmployee = null;
         Map<Location, List<SystemBlock>> svtObjectsByStorage = null;
         List<LocationByTreeDto> treeSvtDtoByStorage = null;
         
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = systemblockService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = systemblockService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        treeSvtDtoByEmployee = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = systemblockService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = systemblockService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        } else {
            
              List<SystemBlock> systemblockByFilter = systemblockService.getSystemBlockByFilter(dto);
             filter = new ArrayList<>();
            for(SystemBlock p : systemblockByFilter) {
                SvtDTO upsDto = systemblockMapper.getDto(p);
                filter.add(upsDto);
            }
            
            svtObjectsByEmployee = systemblockService.getSystemblockByPlaceTypeAndFilter(PlaceType.EMPLOYEE, systemblockByFilter);
            treeSvtDtoByEmployee = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = systemblockService.getSystemblockByPlaceTypeAndFilter(PlaceType.STORAGE, systemblockByFilter);
               treeSvtDtoByStorage = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        }
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "systemblock");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Системные блоки");
        
        return "svtobj";
    }
    
 
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/sysblocks")
    public String saveSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
        systemblockService.createSvtObj(dto);
        return "redirect:/sysblocks";
    }
    
    
      //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/sysblockstostor")
    public String sendToStorageSystemblock (@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
           // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            SystemBlock systemblock = systemblockMapper.getEntityFromDto(dto);
        systemblockService.sendToStorage(systemblock);
        return "redirect:/sysblocks";
        
    }
    

    @UpdLog
        @PostMapping("/updsysblocks")
    public String updateSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
        systemblockService.updateSvtObj(dto);
        return "redirect:/sysblocks";
     
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/systemblockarchived")
    public String sendSystemBlockToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        systemblockService.svtObjToArchive(dto);
        return "redirect:/sysblocks";
    }
    
    
  //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmotherboard")
    public String getModelMotherboard(Model model) {

        List<Motherboard> motherboardModels = motherboardModelService.getAllActualModels();
        List<SvtModelDto> getMotherboardModelsDtoes = svtModelMapper.getModelMotherboardModelsDtoes(motherboardModels);
        model.addAttribute("dtoes", getMotherboardModelsDtoes);
        model.addAttribute("namePage", "Модели материнской платы");
        model.addAttribute("attribute", "mmotherboard");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mmotherboard")
    public String saveModelMotherboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
            Motherboard modelMotherboard = svtModelMapper.getModelMotherboard(dto);
        motherboardModelService.saveModel(modelMotherboard);
        return "redirect:/mmotherboard";
        
    }
    
    @SendArchive
    @PostMapping("/mmotherboardarchived")
    public String sendModelMotherboardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        motherboardModelService.sendModelToArchive(dto.getId());
        return "redirect:/mmotherboard";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mcpu")
    public String getModelCpu(Model model) {

        List<Cpu> cpuModels = cpuModelService.getAllActualModels();
        List<CpuModelDto> getCpuDtoes = svtModelMapper.getCpuModelDtoes(cpuModels);
        model.addAttribute("dtoes", getCpuDtoes);
        model.addAttribute("namePage", "Модели процессоров");
        model.addAttribute("attribute", "mcpu");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mcpu")
    public String saveModelCpu(@RequestBody CpuModelDto dto) throws ObjectAlreadyExists {
        Cpu cpu = svtModelMapper.getCpu(dto);
        cpuModelService.saveModel(cpu);
        return "redirect:/mcpu";
        
    }
    
    @SendArchive
    @PostMapping("/mcpuarchived")
    public String sendModelCpuToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        cpuModelService.sendModelToArchive(dto.getId());
        return "redirect:/mcpu";
        
    }
    
    @UpdLog
    @PostMapping("/mcpuupd")
    public String updateModelCpu(@RequestBody CpuModelDto dto) throws ObjectAlreadyExists {
        Cpu cpu = svtModelMapper.getCpu(dto);
        cpuModelService.update(cpu);
        return "redirect:/mcpu";
    }
    
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mram")
    public String getModelRam(Model model) {

        List<Ram> ramModels = ramModelService.getAllActualModels();
        List<RamDto> getRamDtoes = svtModelMapper.getRamDtoes(ramModels);
        model.addAttribute("dtoes", getRamDtoes);
        model.addAttribute("namePage", "Модели ОЗУ");
        model.addAttribute("attribute", "mram");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mram")
    public String saveModelRam(@RequestBody RamDto dto) throws ObjectAlreadyExists {
        Ram ram = svtModelMapper.getRam(dto);
        ramModelService.saveModel(ram);
        return "redirect:/mram";
        
    }
    
     @SendArchive
    @PostMapping("/mramarchived")
    public String sendModelRamToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        ramModelService.sendModelToArchive(dto.getId());
        return "redirect:/mram";
        
    }
    
    
    
       @UpdLog
    @PostMapping("/mramupd")
    public String updateModelRam(@RequestBody RamDto dto) throws ObjectAlreadyExists {
        Ram ram = svtModelMapper.getRam(dto);
        ramModelService.update(ram);
        return "redirect:/mram";
        
    }
    
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mhdd")
    public String getModelHdd(Model model) {

        List<Hdd> hddModels = hddModelService.getAllActualModels();
        List<HddDto> getHddDtoes = svtModelMapper.getHddDtoes(hddModels);
        model.addAttribute("dtoes", getHddDtoes);
        model.addAttribute("namePage", "Модели НЖМД");
        model.addAttribute("attribute", "mhdd");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mhdd")
    public String saveModelHdd(@RequestBody HddDto dto) throws ObjectAlreadyExists {
        Hdd hdd = svtModelMapper.getHdd(dto);
        hddModelService.saveModel(hdd);
        return "redirect:/mhdd";
        
    }
    
    
    @SendArchive
    @PostMapping("/mhddarchived")
    public String sendModelHddToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        hddModelService.sendModelToArchive(dto.getId());
        return "redirect:/mhdd";
        
    }
    

        @PostMapping("/mhddupd")
    public String updateModelHdd(@RequestBody HddDto dto) throws ObjectAlreadyExists {
        Hdd hdd = svtModelMapper.getHdd(dto);
        hddModelService.update(hdd);
        return "redirect:/mhdd";
     
    }
    
  //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mvideo")
    public String getModelVideoCard(Model model) {

        List<VideoCard> videoCardModels = videoCardModelService.getAllActualModels();
        List<SvtModelDto> getVideoCardDtoes = svtModelMapper.getVideoCardDtoes(videoCardModels);
        model.addAttribute("dtoes", getVideoCardDtoes);
        model.addAttribute("namePage", "Модели видеоадаптеров");
        model.addAttribute("attribute", "mvideo");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mvideo")
    public String saveModelVideoCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        VideoCard videocard = svtModelMapper.getVideoCard(dto);
        videoCardModelService.saveModel(videocard);
        return "redirect:/mvideo";
        
    }
    
      @SendArchive
    @PostMapping("/mvideoarchived")
    public String sendModelVideoCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        videoCardModelService.sendModelToArchive(dto.getId());
        return "redirect:/mvideo";
        
    }
    
    @UpdLog
    @PostMapping("/mvideoupd")
    public String updateModelVideoCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        VideoCard videocard = svtModelMapper.getVideoCard(dto);
        videoCardModelService.update(videocard);
        return "redirect:/mvideo";
        
    }
    
  //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mcddrive")
    public String getModelCdDrive(Model model) {

        List<CdDrive> cdDriveModels = cdDriveModelService.getAllActualModels();
        List<SvtModelDto> getCdDriveDtoes = svtModelMapper.getCdDriveDtoes(cdDriveModels);
        model.addAttribute("dtoes", getCdDriveDtoes);
        model.addAttribute("namePage", "Модели приводов");
        model.addAttribute("attribute", "mcddrive");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mcddrive")
    public String saveModelCdDrive(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        CdDrive cdDrive = svtModelMapper.getCdDrive(dto);
        cdDriveModelService.saveModel(cdDrive);
        return "redirect:/mcddrive";
    }
    
    
    @SendArchive
    @PostMapping("/mcddrivearchived")
    public String sendModelCdDriveToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        cdDriveModelService.sendModelToArchive(dto.getId());
        return "redirect:/mcddrive";
    }
    
    @UpdLog
    @PostMapping("/mcddriveupd")
    public String updateModelCdDrive(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        CdDrive cdDrive = svtModelMapper.getCdDrive(dto);
        cdDriveModelService.update(cdDrive);
        return "redirect:/mcddrive";
        
    }
    
 //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mscard")
    public String getModelSCard(Model model) {

        List<SoundCard> soundCardModels = soundCardModelService.getAllActualModels();
        List<SvtModelDto> getSoundCardDtoes = svtModelMapper.getSoundCardDtoes(soundCardModels);
        model.addAttribute("dtoes", getSoundCardDtoes);
        model.addAttribute("namePage", "Модели звуковых карт");
        model.addAttribute("attribute", "mscard");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mscard")
    public String saveModelSCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SoundCard soundCard = svtModelMapper.getSoundCard(dto);
        soundCardModelService.saveModel(soundCard);
        return "redirect:/mscard";
    }
    
    
     @SendArchive
    @PostMapping("/mscardarchived")
    public String sendModelSCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        soundCardModelService.sendModelToArchive(dto.getId());
        return "redirect:/mscard";
    }
    
    @UpdLog
    @PostMapping("/mscardupd")
    public String updateModelSCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SoundCard soundCard = svtModelMapper.getSoundCard(dto);
        soundCardModelService.update(soundCard);
        return "redirect:/mscard";
        
    }
    
    
 //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mlcard")
    public String getLansCard(Model model) {

        List<LanCard> lanCardModels = lanCardModelService.getAllActualModels();
        List<SvtModelDto> getLanCardDtoes = svtModelMapper.getLanCardDtoes(lanCardModels);
        model.addAttribute("dtoes", getLanCardDtoes);
        model.addAttribute("namePage", "Модели сетевых карт");
        model.addAttribute("attribute", "mlcard");
        
        return "models";
    }
    
    //@PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mlcard")
    public String saveModelLanCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        LanCard lanCard = svtModelMapper.getLanCard(dto);
        lanCardModelService.saveModel(lanCard);
        return "redirect:/mlcard";
    }
    
      @SendArchive
    @PostMapping("/mlcardarchived")
    public String sendModelLanCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        lanCardModelService.sendModelToArchive(dto.getId());
        return "redirect:/mlcard";
    }
    
    
       @Log
    @PostMapping("/mlcardupd")
    public String updateModelLanCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        LanCard lanCard = svtModelMapper.getLanCard(dto);
        lanCardModelService.update(lanCard);
        return "redirect:/mlcard";
        
    }
    
    
 //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mkeyboard")
    public String getKeyboard(Model model) {

        List<Keyboard> keyboardModels = keyboardModelService.getAllActualModels();
        List<SvtModelDto> getKeyboardDtoes = svtModelMapper.getKeyboardDtoes(keyboardModels);
        model.addAttribute("dtoes", getKeyboardDtoes);
        model.addAttribute("namePage", "Модели клавиатур");
        model.addAttribute("attribute", "mkeyboard");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mkeyboard")
    public String saveModelKeyboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Keyboard keyboard = svtModelMapper.getKeyboard(dto);
        keyboardModelService.saveModel(keyboard);
        return "redirect:/mkeyboard";
    }
    
      @SendArchive
    @PostMapping("/mkeyboardarchived")
    public String sendModelKeyboardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        keyboardModelService.sendModelToArchive(dto.getId());
        return "redirect:/mkeyboard";
    }
    
    
        @UpdLog
    @PostMapping("/mkeyboardupd")
    public String updateModelKeyboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Keyboard keyboard = svtModelMapper.getKeyboard(dto);
        keyboardModelService.update(keyboard);
        return "redirect:/mkeyboard";
        
    }
    
 //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmouse")
    public String getMouse(Model model) {

        List<Mouse> mouseModels = mouseModelService.getAllActualModels();
        List<SvtModelDto> getMouseModelsDtoes = svtModelMapper.getMouseDtoes(mouseModels);
        model.addAttribute("dtoes", getMouseModelsDtoes);
        model.addAttribute("namePage", "Модели мышей");
        model.addAttribute("attribute", "mmouse");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mmouse")
    public String saveModelMouse(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Mouse mouse = svtModelMapper.getMouse(dto);
        mouseModelService.saveModel(mouse);
        return "redirect:/mmouse";
        
    }
    
    @SendArchive
    @PostMapping("/mmousearchived")
    public String sendModelMouseToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        mouseModelService.sendModelToArchive(dto.getId());
        return "redirect:/mmouse";
    }
    
    
    @UpdLog
    @PostMapping("/mmouseupd")
    public String updateModelMouse(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Mouse mouse = svtModelMapper.getMouse(dto);
        mouseModelService.update(mouse);
        return "redirect:/mmouse";
        
    }
    
 //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mspeakers")
    public String getSpeakers(Model model) {

        List<Speakers> speakersModels = speakersModelService.getAllActualModels();
        List<SvtModelDto> getSpeakersModelsDtoes = svtModelMapper.getSpeakersDtoes(speakersModels);
        model.addAttribute("dtoes", getSpeakersModelsDtoes);
        model.addAttribute("namePage", "Модели колонок");
        model.addAttribute("attribute", "mspeakers");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mspeakers")
    public String saveModelSpeakers(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Speakers speakers = svtModelMapper.getSpeakers(dto);
        speakersModelService.saveModel(speakers);
        return "redirect:/mspeakers";
    }
    
    
     @SendArchive
    @PostMapping("/mspeakersarchived")
    public String sendModelSpeakersToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        speakersModelService.sendModelToArchive(dto.getId());
        return "redirect:/mspeakers";
    }
    
    
     @UpdLog
    @PostMapping("/mspeakersupd")
    public String updateModelSpeakers(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Speakers speakers = svtModelMapper.getSpeakers(dto);
        speakersModelService.update(speakers);
        return "redirect:/mspeakers";
    }
    
 //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/os")
    public String getOperationSystems(Model model) {

        List<OperationSystem> allOperationSystem = operationSystemService.getAllOperationSystem();
        List<OperationSystemDto> operationSystemDtoesList = operationSystemMapper.getOperationSystemDtoesList(allOperationSystem);
        model.addAttribute("dtoes", operationSystemDtoesList);
        model.addAttribute("namePage", "Операционные системы");
        model.addAttribute("attribute", "os");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/os")
    public String saveOperationSystem(@RequestBody OperationSystemDto dto) throws ObjectAlreadyExists {
            OperationSystem operationSystem = operationSystemMapper.getOperationSystem(dto);
            operationSystemService.saveOperationSystem(operationSystem);
        return "redirect:/os";
        
    }
    
    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/osupd")
    public String updateOperationSystem(@RequestBody OperationSystemDto dto) throws ObjectAlreadyExists {
            OperationSystem operationSystem = operationSystemMapper.getOperationSystem(dto);
            operationSystemService.updateOperationSystem(operationSystem);
        return "redirect:/os";
        
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mscanner")
    public String getModelScanner(Model model) {

        List<ScannerModel> scannerModels = scannerModelService.getAllActualModels();
        List<SvtModelDto> scannerModelsDtoes = svtModelMapper.getModelScannerDtoes(scannerModels);
        model.addAttribute("dtoes", scannerModelsDtoes);
        model.addAttribute("namePage", "Модели сканеров");
        model.addAttribute("attribute", "mscanner");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mscanner")
    public String saveModelScanner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ScannerModel scannerModel = svtModelMapper.getModelScanner(dto);
        scannerModelService.saveModel(scannerModel);
        return "redirect:/mscanner";
        
    }
    
     @SendArchive
    @PostMapping("/mscannerarchived")
    public String sendModelScannerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        
        scannerModelService.sendModelToArchive(dto.getId());
        return "redirect:/mscanner";
        
    }
    
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/scanner")
    public String getScanners(Model model, @RequestParam(value="username", required=false) String username, @ModelAttribute FilterDto dto) {
        
        Map<Location, List<Scanner>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Scanner>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = scannerService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = scannerService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
       treeSvtDtoByEmployee = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = scannerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
           svtObjectsByStorage = scannerService.getSvtObjectsByPlaceType(PlaceType.STORAGE); 
        }
        
        treeSvtDtoByStorage = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        } else {
            
             List<Scanner> scannerByFilter = scannerService.getScannerByFilter(dto);
             filter = new ArrayList<>();
            for(Scanner p : scannerByFilter) {
                SvtDTO scannerDto = scannerMapper.getDto(p);
                filter.add(scannerDto);
            }
            
            svtObjectsByEmployee = scannerService.getScannerByPlaceTypeAndFilter(PlaceType.EMPLOYEE, scannerByFilter);
            treeSvtDtoByEmployee = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = scannerService.getScannerByPlaceTypeAndFilter(PlaceType.STORAGE, scannerByFilter);
               treeSvtDtoByStorage = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "scanner");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Сканеры");
        
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/scanner")
    public String saveScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
        scannerService.createSvtObj(dto);
        return "redirect:/scanner";
     
    }
    
    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/scannertostor")
    public String sendToStorageScanner (@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
           // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Scanner scanner = scannerMapper.getEntityFromDto(dto);
        scannerService.sendToStorage(scanner);
        return "redirect:/scanner";
        
    }
    

        @UpdLog
        @PostMapping("/updscanner")
    public String updateScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
        scannerService.updateSvtObj(dto);
        return "redirect:/scanner";
     
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/scannerarchived")
    public String sendScannerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        scannerService.svtObjToArchive(dto);
        return "redirect:/scanner";
    }
    
     //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mserver")
    public String getModelServer(Model model) {

        List<ServerModel> serverModels = serverModelService.getAllActualModels();
        List<SvtModelDto> getServerModelsDtoes = svtModelMapper.getModelServerDtoes(serverModels);
        model.addAttribute("dtoes", getServerModelsDtoes);
        model.addAttribute("namePage", "Модели серверов");
        model.addAttribute("attribute", "mserver");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mserver")
    public String saveModelServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ServerModel serverModel = svtModelMapper.getModelServer(dto);
        serverModelService.saveModel(serverModel);
        return "redirect:/mserver";
    }
    
    @SendArchive
    @PostMapping("/mserverarchived")
    public String sendModelServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        serverModelService.sendModelToArchive(dto.getId());
        return "redirect:/mserver";
    }
    
    
    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/server")
    public String getServers(Model model, @RequestParam(value="username",required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Server>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Server>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = serverService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = serverService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = serverService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = serverService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        }else {
        
            List<Server> serverByFilter = serverService.getServerByFilter(dto);
             filter = new ArrayList<>();
            for(Server p : serverByFilter) {
                SvtDTO serverDto = serverMapper.getDto(p);
                filter.add(serverDto);
            }
            
            svtObjectsByEmployee = serverService.getServerByPlaceTypeAndFilter(PlaceType.SERVERROOM, serverByFilter);
            treeSvtDtoByEmployee = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = serverService.getServerByPlaceTypeAndFilter(PlaceType.STORAGE, serverByFilter);
               treeSvtDtoByStorage = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "server");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Серверы");
        
        return "svtobj";
    }
    
 
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/server")
    public String saveServer(@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
        serverService.createSvtObj(dto);
        return "redirect:/server";
    }
    
    
        //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/servertostor")
    public String sendToStorageServer (@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Server server = serverMapper.getEntityFromDto(dto);
        serverService.sendToStorage(server);
        return "redirect:/server";
        
    }
    

    @UpdLog
     @PostMapping("/updserver")
    public String updateServer (@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
        serverService.updateSvtObj(dto);
        return "redirect:/server";
     
    }
    
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/serverarchived")
    public String sendServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        serverService.svtObjToArchive(dto);
        return "redirect:/server";
    }
    
    
         //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mswitch")
    public String getModelSwitchHub(Model model) {

        List<SwitchHubModel> switchHubModels = switchHubModelService.getAllActualModels();
        List<SvtModelDto> getSwitchHubModelsDtoes = svtModelMapper.getModelSwitchHubDtoes(switchHubModels);
        model.addAttribute("dtoes", getSwitchHubModelsDtoes);
        model.addAttribute("namePage", "Модели коммутаторов/концентраторов");
        model.addAttribute("attribute", "mswitch");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mswitch")
    public String saveModelSwitchHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SwitchHubModel switchHubModel = svtModelMapper.getModelSwitchHub(dto);
        switchHubModelService.saveModel(switchHubModel);
        return "redirect:/mswitch";
    }
    
      @SendArchive
    @PostMapping("/mswitcharchived")
    public String sendModelSwitchHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        switchHubModelService.sendModelToArchive(dto.getId());
        return "redirect:/mswitch";
    }
    
    
     //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/switch")
    public String getSwitchHub(Model model, @RequestParam(value="username",required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<SwitchHub>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<SwitchHub>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = switchHubService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = switchHubService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = switchHubService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = switchHubService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        } else {
        
            List<SwitchHub> switchHubByFilter = switchHubService.getSwitchHubByFilter(dto);
             filter = new ArrayList<>();
            for(SwitchHub p : switchHubByFilter) {
                SvtDTO switchHubDto = switchHubMapper.getDto(p);
                filter.add(switchHubDto);
            }
            
            svtObjectsByEmployee = switchHubService.getSwitchHubByPlaceTypeAndFilter(PlaceType.SERVERROOM, switchHubByFilter);
            treeSvtDtoByEmployee = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = switchHubService.getSwitchHubByPlaceTypeAndFilter(PlaceType.STORAGE, switchHubByFilter);
               treeSvtDtoByStorage = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "switch");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Коммутаторы/Концентраторы");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/switch")
    public String saveSwitchHub(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        switchHubService.createSvtObj(dto);
        return "redirect:/switch";
    }
    
           //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/switchtostor")
    public String sendToStorageSwitchHub (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            SwitchHub switchHub = switchHubMapper.getEntityFromDto(dto);
        switchHubService.sendToStorage(switchHub);
        return "redirect:/switch";
        
    }
    

    @UpdLog
     @PostMapping("/updswitch")
    public String updateSwitchHub (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        switchHubService.updateSvtObj(dto);
        return "redirect:/switch";
     
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/switcharchived")
    public String sendSwitchHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        switchHubService.svtObjToArchive(dto);
        return "redirect:/switch";
    }
    
            //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mrouter")
    public String getModelRouterb(Model model) {
        List<RouterModel> routerModels = routerModelService.getAllActualModels();
        List<SvtModelDto> getRouterModelsDtoes = svtModelMapper.getModelRouterDtoes(routerModels);
        model.addAttribute("dtoes", getRouterModelsDtoes);
        model.addAttribute("namePage", "Модели маршрутизаторов");
        model.addAttribute("attribute", "mrouter");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mrouter")
    public String saveModelRouter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        RouterModel routerModel = svtModelMapper.getModelRouter(dto);
        routerModelService.saveModel(routerModel);
        return "redirect:/mrouter";
    }
    
    @SendArchive
    @PostMapping("/mrouterarchived")
    public String sendModelRouterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        routerModelService.sendModelToArchive(dto.getId());
        return "redirect:/mrouter";
    }
    
         //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/router")
    public String getRouter(Model model, @RequestParam(value="username",required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Router>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Router>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = routerService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = routerService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = routerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = routerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        } else {
        
            List<Router> routerByFilter = routerService.getRouterByFilter(dto);
             filter = new ArrayList<>();
            for(Router p : routerByFilter) {
                SvtDTO routerHubDto = routerMapper.getDto(p);
                filter.add(routerHubDto);
            }
            
            svtObjectsByEmployee = routerService.getRouterByPlaceTypeAndFilter(PlaceType.SERVERROOM, routerByFilter);
            treeSvtDtoByEmployee = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = routerService.getRouterByPlaceTypeAndFilter(PlaceType.STORAGE, routerByFilter);
               treeSvtDtoByStorage = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "router");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Маршрутизаторы");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/router")
    public String saveRouter(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        routerService.createSvtObj(dto);
        return "redirect:/router";
    }
    
           //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/routertostor")
    public String sendToStorageRouter(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Router router = routerMapper.getEntityFromDto(dto);
        routerService.sendToStorage(router);
        return "redirect:/router";
        
    }
    

    @UpdLog
     @PostMapping("/updrouter")
    public String updateRouter (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        routerService.updateSvtObj(dto);
        return "redirect:/router";
     
    }
    
        //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/routerarchived")
    public String sendRouterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        routerService.svtObjToArchive(dto);
        return "redirect:/router";
    }
    
    
                //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mats")
    public String getModelAts(Model model) {
        List<AtsModel> atsModels = atsModelService.getAllActualModels();
        List<SvtModelDto> getAtsModelsDtoes = svtModelMapper.getModelAtsDtoes(atsModels);
        model.addAttribute("dtoes", getAtsModelsDtoes);
        model.addAttribute("namePage", "Модели АТС");
        model.addAttribute("attribute", "mats");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mats")
    public String saveModelAts(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        AtsModel atsModel = svtModelMapper.getModelAts(dto);
        atsModelService.saveModel(atsModel);
        return "redirect:/mats";
    }
    
    
     @SendArchive
    @PostMapping("/matsarchived")
    public String sendModelAtsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        atsModelService.sendModelToArchive(dto.getId());
        return "redirect:/mats";
    }
    
             //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/ats")
    public String getAts(Model model, @RequestParam(value="username",required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Ats>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Ats>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = atsService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = atsService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = atsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = atsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = atsService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = atsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        } else {
            
             List<Ats> atsByFilter = atsService.getAtsByFilter(dto);
             filter = new ArrayList<>();
            for(Ats p : atsByFilter) {
                SvtDTO atsDto = atsMapper.getDto(p);
                filter.add(atsDto);
            }
            svtObjectsByEmployee = atsService.getAtsByPlaceTypeAndFilter(PlaceType.SERVERROOM, atsByFilter);
            treeSvtDtoByEmployee = atsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = atsService.getAtsByPlaceTypeAndFilter(PlaceType.STORAGE, atsByFilter);
               treeSvtDtoByStorage = atsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        }
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "ats");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","АТС");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/ats")
    public String saveAts(@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
        atsService.createSvtObj(dto);
        return "redirect:/ats";
    }
    
    @UpdLog
        @PostMapping("/updats")
    public String updateAts (@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
        atsService.updateSvtObj(dto);
        return "redirect:/ats";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/atstostor")
    public String sendToStorageAts(@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Ats ats = atsMapper.getEntityFromDto(dto);
        atsService.sendToStorage(ats);
        return "redirect:/ats";
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/atsarchived")
    public String sendAtsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        atsService.svtObjToArchive(dto);
        return "redirect:/ats";
    }
    
    
                   //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mconditioner")
    public String getModelConditioner(Model model) {
        List<ConditionerModel> conditionerModels = conditionerModelService.getAllActualModels();
        List<SvtModelDto> getAtsModelsDtoes = svtModelMapper.getModelConditionerDtoes(conditionerModels);
        model.addAttribute("dtoes", getAtsModelsDtoes);
        model.addAttribute("namePage", "Модели кондиционеров");
        model.addAttribute("attribute", "mconditioner");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mconditioner")
    public String saveModelConditioner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ConditionerModel conditionerModel = svtModelMapper.getModelConditioner(dto);
        conditionerModelService.saveModel(conditionerModel);
        return "redirect:/mconditioner";
    }
    
     @SendArchive
    @PostMapping("/mconditionerarchived")
    public String sendModelConditionerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        conditionerModelService.sendModelToArchive(dto.getId());
        return "redirect:/mconditioner";
    }
    
                 //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/conditioner")
    public String getConditioner(Model model, @RequestParam(value="username", required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Conditioner>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Conditioner>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
         List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = conditionerService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = conditionerService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = conditionerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = conditionerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = conditionerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = conditionerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        } else {
        
             List<Conditioner> conditionerByFilter = conditionerService.getConditionerByFilter(dto);
             filter = new ArrayList<>();
            for(Conditioner p : conditionerByFilter) {
                SvtDTO conditionerDto = conditionerMapper.getDto(p);
                filter.add(conditionerDto);
            }
            svtObjectsByEmployee = conditionerService.getConditionerByPlaceTypeAndFilter(PlaceType.SERVERROOM, conditionerByFilter);
            treeSvtDtoByEmployee = conditionerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = conditionerService.getConditionerByPlaceTypeAndFilter(PlaceType.STORAGE, conditionerByFilter);
               treeSvtDtoByStorage = conditionerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "conditioner");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Кондиционеры");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/conditioner")
    public String saveConditioner(@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
        conditionerService.createSvtObj(dto);
        return "redirect:/conditioner";
    }
          @UpdLog
          @PostMapping("/updconditioner")
    public String updateConditioner (@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
        conditionerService.updateSvtObj(dto);
        return "redirect:/conditioner";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/conditionertostor")
    public String sendToStorageConditioner(@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Conditioner conditioner = conditionerMapper.getEntityFromDto(dto);
        conditionerService.sendToStorage(conditioner);
        return "redirect:/conditioner";
        
    }
    
       //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/conditionerbackstor")
    public String backFromStorageConditioner (@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
            Conditioner conditioner = conditionerMapper.getEntityFromDto(dto);
        conditionerService.backFromStorage(conditioner, dto.getPlaceId());
        return "redirect:/conditioner";
        
    }
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/conditionerarchived")
    public String sendConditionerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        conditionerService.svtObjToArchive(dto);
        return "redirect:/conditioner";
    }
    
                       //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mfax")
    public String getModelFax(Model model) {
        List<FaxModel> faxModels = faxModelService.getAllActualModels();
        List<SvtModelDto> getFaxModelsDtoes = svtModelMapper.getModelFaxDtoes(faxModels);
        model.addAttribute("dtoes", getFaxModelsDtoes);
        model.addAttribute("namePage", "Модели факсов");
        model.addAttribute("attribute", "mfax");
        return "models";
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/mfaxupd")
    public String updateModelFax(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        FaxModel faxModel = svtModelMapper.getModelFax(dto);
        faxModelService.update(faxModel);
        return "redirect:/mfax";
        
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mfax")
    public String saveModelFax(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        FaxModel faxModel = svtModelMapper.getModelFax(dto);
        faxModelService.saveModel(faxModel);
        return "redirect:/mfax";
    }
    
    @SendArchive
    @PostMapping("/mfaxarchived")
    public String sendModelFaxToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        faxModelService.sendModelToArchive(dto.getId());
        return "redirect:/mfax";
    }
    
    
                     //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/fax")
    public String getFax(Model model, @RequestParam(value="username", required=false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Fax>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Fax>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<FaxDto> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = faxService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = faxService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        treeSvtDtoByEmployee = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = faxService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = faxService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        } else {
        
             List<Fax> faxByFilter = faxService.getFaxByFilter(dto);
             filter = new ArrayList<>();
            for(Fax p : faxByFilter) {
                FaxDto faxDto = faxMapper.getDto(p);
                filter.add(faxDto);
            }
            svtObjectsByEmployee = faxService.getFaxByPlaceTypeAndFilter(PlaceType.OFFICEEQUIPMENT, faxByFilter);
            treeSvtDtoByEmployee = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = faxService.getFaxByPlaceTypeAndFilter(PlaceType.STORAGE, faxByFilter);
               treeSvtDtoByStorage = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "fax");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Факсы");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/fax")
    public String saveFax(@RequestBody FaxDto dto) throws ObjectAlreadyExists {
        faxService.createSvtObj(dto);
        return "redirect:/fax";
    }
     @UpdLog
     @PostMapping("/updfax")
    public String updateFax (@RequestBody FaxDto dto) throws ObjectAlreadyExists {
        faxService.updateSvtObj(dto);
        return "redirect:/fax";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/faxtostor")
    public String sendToStorageFax(@RequestBody FaxDto dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Fax fax = faxMapper.getEntityFromDto(dto);
        faxService.sendToStorage(fax);
        return "redirect:/fax";
        
    }
    
       //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/faxbackstor")
    public String backFromStorageFax (@RequestBody FaxDto dto) throws ObjectAlreadyExists {
            Fax fax = faxMapper.getEntityFromDto(dto);
        faxService.backFromStorage(fax, dto.getPlaceId());
        return "redirect:/fax";
        
    }
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/faxarchived")
    public String sendFaxToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        faxService.svtObjToArchive(dto);
        return "redirect:/fax";
    }
    
     //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/minfomat")
    public String getModelInfomat(Model model) {
        List<InfomatModel> infomatModels = infomatModelService.getAllActualModels();
        List<SvtModelDto> getInfomatModelsDtoes = svtModelMapper.getModelInfomatDtoes(infomatModels);
        model.addAttribute("dtoes", getInfomatModelsDtoes);
        model.addAttribute("namePage", "Модели инфоматов");
        model.addAttribute("attribute", "minfomat");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/minfomat")
    public String saveModelInfomat(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        InfomatModel infomatModel = svtModelMapper.getModelInfomat(dto);
        infomatModelService.saveModel(infomatModel);
        return "redirect:/minfomat";
    }
    
    @SendArchive
    @PostMapping("/minfomatarchived")
    public String sendModelInfomatToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        infomatModelService.sendModelToArchive(dto.getId());
        return "redirect:/minfomat";
    }
    
    
                  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/infomat")
    public String getInfomat(Model model, @RequestParam(value="username", required = false) String username, @ModelAttribute FilterDto dto) {
        Map<Location, List<Infomat>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Infomat>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = infomatService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = infomatService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
       treeSvtDtoByEmployee = infomatOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = infomatService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = infomatService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = infomatOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        } else {
            
            List<Infomat> infomatByFilter = infomatService.getInfomatByFilter(dto);
             filter = new ArrayList<>();
            for(Infomat p : infomatByFilter) {
                SvtDTO infomatDto = infomatMapper.getDto(p);
                filter.add(infomatDto);
            }
            svtObjectsByEmployee = infomatService.getInfomatByPlaceTypeAndFilter(PlaceType.OFFICEEQUIPMENT, infomatByFilter);
            treeSvtDtoByEmployee = infomatOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = infomatService.getInfomatByPlaceTypeAndFilter(PlaceType.STORAGE, infomatByFilter);
               treeSvtDtoByStorage = infomatOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "infomat");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Инфоматы");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/infomat")
    public String saveInfomat(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        infomatService.createSvtObj(dto);
        return "redirect:/infomat";
    }
            @UpdLog
          @PostMapping("/updinfomat")
    public String updateInfomat (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        infomatService.updateSvtObj(dto);
        return "redirect:/infomat";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/infomattostor")
    public String sendToStorageInfomat(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Infomat infomat = infomatMapper.getEntityFromDto(dto);
        infomatService.sendToStorage(infomat);
        return "redirect:/infomat";
        
    }
    

    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/infomatarchived")
    public String sendInfomatToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        infomatService.svtObjToArchive(dto);
        return "redirect:/infomat";
    }
    

         //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal")
    public String getModelTerminal(Model model) {
        List<TerminalModel> terminalModels = terminalModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = svtModelMapper.getModelTerminalDtoes(terminalModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели терминалов");
        model.addAttribute("attribute", "mterminal");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mterminal")
    public String saveModelTerminal(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalModel terminalModel = svtModelMapper.getModelTerminal(dto);
        terminalModelService.saveModel(terminalModel);
        return "redirect:/mterminal";
    }
    
    @SendArchive
    @PostMapping("/mterminalarchived")
    public String sendModelTerminalToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalModelService.sendModelToArchive(dto.getId());
        return "redirect:/mterminal";
    }
    
                      //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/terminal")
    public String getTerminal(Model model, @RequestParam(value="username", required = false) String username) {
        Map<Location, List<Terminal>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = terminalService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Terminal>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = terminalService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminal");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Терминалы");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/terminal")
    public String saveTerminal(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        terminalService.createSvtObj(dto);
        return "redirect:/terminal";
    }
          @UpdLog
          @PostMapping("/updterminal")
    public String updateTerminal (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        terminalService.updateSvtObj(dto);
        return "redirect:/terminal";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/terminaltostor")
    public String sendToStorageTerminal(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Terminal terminal = terminalMapper.getEntityFromDto(dto);
        terminalService.sendToStorage(terminal);
        return "redirect:/terminal";
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/terminalarchived")
    public String sendTerminalToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalService.svtObjToArchive(dto);
        return "redirect:/terminal";
    }
    
             //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mthermoprinter")
    public String getModelThermoprinter(Model model) {
        List<ThermoPrinterModel> thermoprinterModels = thermoprinterModelService.getAllActualModels();
        List<SvtModelDto> getThermoprinterModelsDtoes = svtModelMapper.getModelThermoprinterDtoes(thermoprinterModels);
        model.addAttribute("dtoes", getThermoprinterModelsDtoes);
        model.addAttribute("namePage", "Модели термопринтеров");
        model.addAttribute("attribute", "mthermoprinter");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mthermoprinter")
    public String saveModelThermoprinter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ThermoPrinterModel thermoprinterModel = svtModelMapper.getModelThermoprinter(dto);
        thermoprinterModelService.saveModel(thermoprinterModel);
        return "redirect:/mthermoprinter";
    }
    
      @SendArchive
    @PostMapping("/mthermoprinterarchived")
    public String sendModelThermoprinterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        thermoprinterModelService.sendModelToArchive(dto.getId());
        return "redirect:/mthermoprinter";
    }
    
                         //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/thermoprinter")
    public String getThermoprinter(Model model, @RequestParam(value="username", required = false) String username) {
        Map<Location, List<ThermoPrinter>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = thermoprinterService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = thermoprinterService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = thermoprinterOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<ThermoPrinter>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = thermoprinterService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = thermoprinterService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = thermoprinterOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "thermoprinter");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Термопринтеры");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/thermoprinter")
    public String saveThermoprinter(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        thermoprinterService.createSvtObj(dto);
        return "redirect:/thermoprinter";
    }
          @UpdLog
          @PostMapping("/updthermoprinter")
    public String updateThermoprinter (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        thermoprinterService.updateSvtObj(dto);
        return "redirect:/thermoprinter";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/thermoprintertostor")
    public String sendToStorageThermoprinter(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
            ThermoPrinter thermoprinter = thermoprinterMapper.getEntityFromDto(dto);
        thermoprinterService.sendToStorage(thermoprinter);
        return "redirect:/thermoprinter";
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/thermoprinterarchived")
    public String sendThermoprinterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        thermoprinterService.svtObjToArchive(dto);
        return "redirect:/thermoprinter";
    }
    
    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mdisplay")
    public String getModelDisplay(Model model) {
        List<DisplayModel> displayModels = displayModelService.getAllActualModels();
        List<SvtModelDto> getDisplayModelsDtoes = svtModelMapper.getModelDisplayDtoes(displayModels);
        model.addAttribute("dtoes", getDisplayModelsDtoes);
        model.addAttribute("namePage", "Модели главного табло");
        model.addAttribute("attribute", "mdisplay");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mdisplay")
    public String saveModelDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        DisplayModel displayModel = svtModelMapper.getModelDisplay(dto);
        displayModelService.saveModel(displayModel);
        return "redirect:/mdisplay";
    }
    
     @SendArchive
    @PostMapping("/mdisplayarchived")
    public String sendModelDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        displayModelService.sendModelToArchive(dto.getId());
        return "redirect:/mdisplay";
    }
    
    
                         //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/display")
    public String getDisplay(Model model, @RequestParam(value="username", required = false) String username) {
        Map<Location, List<Display>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = displayService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = displayService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = displayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Display>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = displayService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = displayService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = displayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "display");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Главное табло");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/display")
    public String saveDisplay(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        displayService.createSvtObj(dto);
        return "redirect:/display";
    }
          @UpdLog
          @PostMapping("/upddisplay")
    public String updateDisplay (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        displayService.updateSvtObj(dto);
        return "redirect:/display";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/displaytostor")
    public String sendToStorageDisplay(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
            Display display = displayMapper.getEntityFromDto(dto);
        displayService.sendToStorage(display);
        return "redirect:/display";
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/displayarchived")
    public String sendDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        displayService.svtObjToArchive(dto);
        return "redirect:/display";
    }
    
    
    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mswunit")
    public String getModelSwunit(Model model) {
        List<SwitchingUnitModel> swunitModels = swunitModelService.getAllActualModels();
        List<SvtModelDto> getDisplayModelsDtoes = svtModelMapper.getModelSwunitDtoes(swunitModels);
        model.addAttribute("dtoes", getDisplayModelsDtoes);
        model.addAttribute("namePage", "Модели блоков коммутации");
        model.addAttribute("attribute", "mswunit");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/mswunit")
    public String saveModelSwunit(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SwitchingUnitModel swunitModel = svtModelMapper.getModelSwunit(dto);
        swunitModelService.saveModel(swunitModel);
        return "redirect:/mswunit";
    }
    
     @SendArchive
    @PostMapping("/mswunitarchived")
    public String sendModelSwunitToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        swunitModelService.sendModelToArchive(dto.getId());
        return "redirect:/mswunit";
    }
    
    
              //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/swunit")
    public String getSwunit(Model model, @RequestParam(value="username", required = false) String username) {
        Map<Location, List<SwitchingUnit>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = swunitService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = swunitService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = swunitOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<SwitchingUnit>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = swunitService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = swunitService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = swunitOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("attribute", "swunit");
        model.addAttribute("namePage","Блок коммутации");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/swunit")
    public String saveSwunit(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        swunitService.createSvtObj(dto);
        return "redirect:/swunit";
    }
          @UpdLog
          @PostMapping("/updswunit")
    public String updateSwunit (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        swunitService.updateSvtObj(dto);
        return "redirect:/swunit";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/swunittostor")
    public String sendToStorageSwunit(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
            SwitchingUnit swunit = swunitMapper.getEntityFromDto(dto);
        swunitService.sendToStorage(swunit);
        return "redirect:/swunit";
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/swunitarchived")
    public String sendSwunitToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        swunitService.svtObjToArchive(dto);
        return "redirect:/swunit";
    }

      //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/msubdisplay")
    public String getModelSubDisplay(Model model) {
        List<SubDisplayModel> subDisplayModels = subDisplayModelService.getAllActualModels();
        List<SvtModelDto> getSubDisplayModelsDtoes = svtModelMapper.getModelSubDisplayDtoes(subDisplayModels);
        model.addAttribute("dtoes", getSubDisplayModelsDtoes);
        model.addAttribute("namePage", "Модели электронных табло");
        model.addAttribute("attribute", "msubdisplay");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/msubdisplay")
    public String saveModelSubDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SubDisplayModel subDisplayModel = svtModelMapper.getModelSubDisplay(dto);
        subDisplayModelService.saveModel(subDisplayModel);
        return "redirect:/msubdisplay";
    }
    
    @SendArchive
    @PostMapping("/msubdisplayarchived")
    public String sendModelSubDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        subDisplayModelService.sendModelToArchive(dto.getId());
        return "redirect:/msubdisplay";
    }
    

            //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/asuo")
    public String getAsuo(Model model, @RequestParam(value="username", required = false) String username) {
        Map<Location, List<Asuo>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = asuoService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = asuoService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = asuoOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Asuo>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = asuoService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = asuoService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = asuoOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("attribute", "asuo");
        model.addAttribute("namePage","Электронные очереди");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @Log
    @PostMapping("/asuo")
    public String saveAsuo(@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
        asuoService.createSvtObj(dto);
        return "redirect:/asuo";
    }
           @UpdLog
          @PostMapping("/updasuo")
    public String updateAsuo (@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
        Asuo asuo = asuoMapper.getEntityFromDto(dto);
        asuoService.updateSvtObj(dto);
        return "redirect:/asuo";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @UpdLog
    @PostMapping("/asuotostor")
    public String sendToStorageAsuo(@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
            Asuo asuo = asuoMapper.getEntityFromDto(dto);
        asuoService.sendToStorage(asuo);
        return "redirect:/asuo";
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @SendArchive
    @PostMapping("/asuoarchived")
    public String sendAsuoToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        asuoService.svtObjToArchive(dto);
        return "redirect:/asuo";
    }
    
}

