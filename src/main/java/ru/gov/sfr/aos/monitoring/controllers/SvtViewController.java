/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Ats;
import ru.gov.sfr.aos.monitoring.entities.AtsModel;
import ru.gov.sfr.aos.monitoring.entities.BatteryType;
import ru.gov.sfr.aos.monitoring.entities.CdDrive;
import ru.gov.sfr.aos.monitoring.entities.Conditioner;
import ru.gov.sfr.aos.monitoring.entities.ConditionerModel;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
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
import ru.gov.sfr.aos.monitoring.entities.SwitchHub;
import ru.gov.sfr.aos.monitoring.entities.SwitchHubModel;
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.entities.SystemBlockModel;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.entities.VideoCard;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.mappers.AtsMapper;
import ru.gov.sfr.aos.monitoring.mappers.BatteryTypeMapper;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorMapper;
import ru.gov.sfr.aos.monitoring.mappers.OperationSystemMapper;
import ru.gov.sfr.aos.monitoring.mappers.PhoneMapper;
import ru.gov.sfr.aos.monitoring.mappers.RouterMapper;
import ru.gov.sfr.aos.monitoring.mappers.ScannerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ServerMapper;
import ru.gov.sfr.aos.monitoring.mappers.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchHubMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemBlockMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsMapper;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.BatteryTypeDto;
import ru.gov.sfr.aos.monitoring.models.CpuModelDto;
import ru.gov.sfr.aos.monitoring.models.DepartmentDTO;
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
import ru.gov.sfr.aos.monitoring.services.SwitchHubModelService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockModelService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockService;
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
    @PostMapping("/places")
    public String addPlace(@RequestBody PlaceDTO dto) {

        if(null != dto.getPlaceId()) {
            placeService.updatePlace(dto);
        } else {
            placeService.createPlace(dto);
        }
        return "redirect:/places";
    }
    
    @PostMapping("/placetoarchive")
    public String sendPlaceToArchived(@RequestBody PlaceDTO dto) {
        placeService.sendPlaceToArchive(dto.getPlaceId());
        return "redirect:/places";
    }

    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mphones")
    public String getModelPhones(Model model) {

        List<PhoneModel> phoneModels = phoneModelService.getAllModels();
        List<SvtModelDto> phoneModelsDtoes = svtModelMapper.getPhoneModelsDtoes(phoneModels);
        model.addAttribute("dtoes", phoneModelsDtoes);
        model.addAttribute("namePage", "Модели телефонов");
        model.addAttribute("attribute", "mphones");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mphones")
    public String saveModelPhone(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        PhoneModel phoneModel = svtModelMapper.getPhoneModel(dto);
        phoneModelService.saveModel(phoneModel);
        return "redirect:/mphones";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmonitors")
    public String getModelMonitors(Model model) {

        List<MonitorModel> monitorModels = monitorModelService.getAllModels();
        List<SvtModelDto> monitorModelsDtoes = svtModelMapper.getMonitorModelsDtoes(monitorModels);
        model.addAttribute("dtoes", monitorModelsDtoes);
        model.addAttribute("namePage", "Модели мониторов");
        model.addAttribute("attribute", "mmonitors");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mmonitors")
    public String saveModelMonitor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        MonitorModel monitorModel = svtModelMapper.getMonitorModel(dto);
        monitorModelService.saveModel(monitorModel);
        return "redirect:/mmonitors";
        
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mups")
    public String getModelUps(Model model) {

        List<UpsModel> upsModels = upsModelService.getAllModels();
        List<SvtModelDto> upsModelsDtoes = svtModelMapper.getUpsModelsDtoes(upsModels);
        model.addAttribute("dtoes", upsModelsDtoes);
        model.addAttribute("namePage", "Модели ИБП");
        model.addAttribute("attribute", "mups");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mups")
    public String saveModelUps(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        UpsModel upsModel = svtModelMapper.getUpsModel(dto);
        upsModelService.saveModel(upsModel);
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
    public String getPhones(Model model, @RequestParam(value="username",required=false) String username) {
        Map<Location, List<Phone>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = phoneService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = phoneService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        
        List<LocationByTreeDto> treeSvtDtoByEmployee = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Phone>> svtObjectsByStorage = null;
        if(null != username) {
            svtObjectsByStorage = phoneService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = phoneService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        
        List<LocationByTreeDto> treeSvtDtoByStorage = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "phones");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Телефоны");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/ups")
    public String getUps(Model model, @RequestParam(value="username",required=false) String username) {
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        if(null != username) {
            svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
           svtObjectsByStorage = upsService.getSvtObjectsByPlaceType(PlaceType.STORAGE); 
        }
        
        List<LocationByTreeDto> treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "ups");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","ИБП");
        
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/ups")
    public String saveUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.createSvtObj(dto);
        return "redirect:/ups";
     
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/upsforserver")
    public String getServerUps(Model model, @RequestParam(value="username", required=false) String username) {
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        if(null != username) {
            svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
           svtObjectsByStorage = upsService.getSvtObjectsByPlaceType(PlaceType.STORAGE); 
        }
        
        List<LocationByTreeDto> treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "ups");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","ИБП");
        
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/upsforserver")
    public String saveServerUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.createSvtObj(dto);
        return "redirect:/upsforserver";
     
    }
    
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updupsforserver")
    public String updateServerUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.updateSvtObj(dto);
        return "redirect:/upsforserver";
     
    }
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/phones")
    public String savePhone(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        phoneService.createSvtObj(dto);
        return "redirect:/phones";
     
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updphone")
    public String updatePhone(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        phoneService.updateSvtObj(dto);
        return "redirect:/phones";
     
    }
    
  //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updups")
    public String updateUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.updateSvtObj(dto);
        return "redirect:/ups";
     
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updmonitor")
    public String updateMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        monitorService.updateSvtObj(dto);
        return "redirect:/monitors";
     
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/phonetostor")
    public String sendToStorage (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Phone phone = phoneMapper.getEntityFromDto(dto);
        phoneService.sendToStorage(phone);
        return "redirect:/phones";
        
    }
    
  //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/upstostor")
    public String sendToStorageUps (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Ups ups = upsMapper.getEntityFromDto(dto);
        BatteryType batteryType = batteryTypeRepo.findById(ups.getBatteryType().getId()).get();
        ups.setBatteryType(batteryType);
        upsService.sendToStorage(ups);
        return "redirect:/ups";
        
    }
    
  //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/monitortostor")
    public String sendToStorageMonitor (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Monitor monitor = monitorMapper.getEntityFromDto(dto);
        monitorService.sendToStorage(monitor);
        return "redirect:/monitors";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/monitorbackstor")
    public String backFromStorageMonitor (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Monitor monitor = monitorMapper.getEntityFromDto(dto);
        monitorService.backFromStorage(monitor, dto.getPlaceId());
        return "redirect:/monitors";
        
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/upsbackstor")
    public String backFromStorageUps (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Ups ups = upsMapper.getEntityFromDto(dto);
        BatteryType batteryType = batteryTypeRepo.findById(ups.getBatteryType().getId()).get();
        ups.setBatteryType(batteryType);
        upsService.backFromStorage(ups, dto.getPlaceId());
        return "redirect:/ups";
        
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/phonebackstor")
    public String backFromStorage (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Phone phone = phoneMapper.getEntityFromDto(dto);
        phoneService.backFromStorage(phone, dto.getPlaceId());
        return "redirect:/phones";
        
    }
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/monitors")
    public String getMonitors(Model model, @RequestParam(value="username",required=false) String username) {
        Map<Location, List<Monitor>> svtObjectsByEmployee = null;
        if(null != username) {
        svtObjectsByEmployee = monitorService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else {
            svtObjectsByEmployee = monitorService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
        
         
        List<LocationByTreeDto> treeSvtDtoByEmployee = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        Map<Location, List<Monitor>> svtObjectsByStorage = null;
        if(null != username) {
             svtObjectsByStorage = monitorService.getSvtObjectsByName(username, PlaceType.STORAGE);
         } else {
            svtObjectsByStorage = monitorService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "monitors");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Мониторы");
        return "svtobj";
    }
  
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/monitors")
    public String saveMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        monitorService.createSvtObj(dto);
        return "redirect:/monitors";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/monitorarchived")
    public String sendMonitorToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        monitorService.svtObjToArchive(dto);
        return "redirect:/monitors";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/phonearchived")
    public String sendPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        phoneService.svtObjToArchive(dto);
        return "redirect:/phones";
    }   
    
    
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/msysblock")
    public String getModelSystemblock(Model model) {

        List<SystemBlockModel> systemBlockModels = systemBlockModelService.getAllModels();
        List<SvtModelDto> systemBlockModelsDtoes = svtModelMapper.getSystemBlockModelsDtoes(systemBlockModels);
        model.addAttribute("dtoes", systemBlockModelsDtoes);
        model.addAttribute("namePage", "Модели системных блоков");
        model.addAttribute("attribute", "systemBlock");
        return "models";
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/msysblock")
    public String saveModelSystemBlock(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SystemBlockModel systemBlockModel = svtModelMapper.getSystemBlockModel(dto);
        systemBlockModelService.saveModel(systemBlockModel);
        return "redirect:/msysblock";
        
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/sysblocks")
    public String getSysBlocks(Model model, @RequestParam(value="username",required=false) String username) {
        Map<Location, List<SystemBlock>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = systemblockService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = systemblockService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<SystemBlock>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = systemblockService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = systemblockService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "systemblock");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Системные блоки");
        
        return "svtobj";
    }
    
 
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/sysblocks")
    public String saveSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
        systemblockService.createSvtObj(dto);
        return "redirect:/sysblocks";
    }
    
    
      //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/sysblockstostor")
    public String sendToStorageSystemblock (@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
           // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            SystemBlock systemblock = systemblockMapper.getEntityFromDto(dto);
        systemblockService.sendToStorage(systemblock);
        return "redirect:/sysblocks";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/sysblocksbackstor")
    public String backFromStorageSystemblock (@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
            SystemBlock systemblock = systemblockMapper.getEntityFromDto(dto);
        systemblockService.backFromStorage(systemblock, dto.getPlaceId());
        return "redirect:/sysblocks";
        
    }
    
        @PostMapping("/updsysblocks")
    public String updateSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
        systemblockService.updateSvtObj(dto);
        return "redirect:/sysblocks";
     
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/sysblocksarchived")
    public String sendSystemBlockToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        systemblockService.svtObjToArchive(dto);
        return "redirect:/sysblocks";
    }
    
    
  //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmotherboard")
    public String getModelMotherboard(Model model) {

        List<Motherboard> motherboardModels = motherboardModelService.getAllModels();
        List<SvtModelDto> getMotherboardModelsDtoes = svtModelMapper.getModelMotherboardModelsDtoes(motherboardModels);
        model.addAttribute("dtoes", getMotherboardModelsDtoes);
        model.addAttribute("namePage", "Модели материнской платы");
        model.addAttribute("attribute", "mmotherboard");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mmotherboard")
    public String saveModelMotherboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
            Motherboard modelMotherboard = svtModelMapper.getModelMotherboard(dto);
        motherboardModelService.saveModel(modelMotherboard);
        return "redirect:/mmotherboard";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mcpu")
    public String getModelCpu(Model model) {

        List<Cpu> cpuModels = cpuModelService.getAllModels();
        List<CpuModelDto> getCpuDtoes = svtModelMapper.getCpuModelDtoes(cpuModels);
        model.addAttribute("dtoes", getCpuDtoes);
        model.addAttribute("namePage", "Модели процессоров");
        model.addAttribute("attribute", "mcpu");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mcpu")
    public String saveModelCpu(@RequestBody CpuModelDto dto) throws ObjectAlreadyExists {
        Cpu cpu = svtModelMapper.getCpu(dto);
        cpuModelService.saveModel(cpu);
        return "redirect:/mcpu";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mram")
    public String getModelRam(Model model) {

        List<Ram> ramModels = ramModelService.getAllModels();
        List<RamDto> getRamDtoes = svtModelMapper.getRamDtoes(ramModels);
        model.addAttribute("dtoes", getRamDtoes);
        model.addAttribute("namePage", "Модели ОЗУ");
        model.addAttribute("attribute", "mram");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mram")
    public String saveModelRam(@RequestBody RamDto dto) throws ObjectAlreadyExists {
        Ram ram = svtModelMapper.getRam(dto);
        ramModelService.saveModel(ram);
        return "redirect:/mram";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mhdd")
    public String getModelHdd(Model model) {

        List<Hdd> hddModels = hddModelService.getAllModels();
        List<HddDto> getHddDtoes = svtModelMapper.getHddDtoes(hddModels);
        model.addAttribute("dtoes", getHddDtoes);
        model.addAttribute("namePage", "Модели НЖМД");
        model.addAttribute("attribute", "mhdd");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mhdd")
    public String saveModelHdd(@RequestBody HddDto dto) throws ObjectAlreadyExists {
        Hdd hdd = svtModelMapper.getHdd(dto);
        hddModelService.saveModel(hdd);
        return "redirect:/mhdd";
        
    }
    
  //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mvideo")
    public String getModelVideoCard(Model model) {

        List<VideoCard> videoCardModels = videoCardModelService.getAllModels();
        List<SvtModelDto> getVideoCardDtoes = svtModelMapper.getVideoCardDtoes(videoCardModels);
        model.addAttribute("dtoes", getVideoCardDtoes);
        model.addAttribute("namePage", "Модели видеоадаптеров");
        model.addAttribute("attribute", "mvideo");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mvideo")
    public String saveModelVideoCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        VideoCard videocard = svtModelMapper.getVideoCard(dto);
        videoCardModelService.saveModel(videocard);
        return "redirect:/mvideo";
        
    }
    
    
  //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mcddrive")
    public String getModelCdDrive(Model model) {

        List<CdDrive> cdDriveModels = cdDriveModelService.getAllModels();
        List<SvtModelDto> getCdDriveDtoes = svtModelMapper.getCdDriveDtoes(cdDriveModels);
        model.addAttribute("dtoes", getCdDriveDtoes);
        model.addAttribute("namePage", "Модели приводов");
        model.addAttribute("attribute", "mcddrive");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mcddrive")
    public String saveModelCdDrive(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        CdDrive cdDrive = svtModelMapper.getCdDrive(dto);
        cdDriveModelService.saveModel(cdDrive);
        return "redirect:/mcddrive";
        
    }
    
 //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mscard")
    public String getModelSCard(Model model) {

        List<SoundCard> soundCardModels = soundCardModelService.getAllModels();
        List<SvtModelDto> getSoundCardDtoes = svtModelMapper.getSoundCardDtoes(soundCardModels);
        model.addAttribute("dtoes", getSoundCardDtoes);
        model.addAttribute("namePage", "Модели звуковых карт");
        model.addAttribute("attribute", "mscard");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mscard")
    public String saveModelSCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SoundCard soundCard = svtModelMapper.getSoundCard(dto);
        soundCardModelService.saveModel(soundCard);
        return "redirect:/mscard";
        
    }
    
    
 //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mlcard")
    public String getLansCard(Model model) {

        List<LanCard> lanCardModels = lanCardModelService.getAllModels();
        List<SvtModelDto> getLanCardDtoes = svtModelMapper.getLanCardDtoes(lanCardModels);
        model.addAttribute("dtoes", getLanCardDtoes);
        model.addAttribute("namePage", "Модели сетевых карт");
        model.addAttribute("attribute", "mlcard");
        
        return "models";
    }
    
    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mlcard")
    public String saveModelLanCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        LanCard lanCard = svtModelMapper.getLanCard(dto);
        lanCardModelService.saveModel(lanCard);
        return "redirect:/mlcard";
        
    }
    
    
 //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mkeyboard")
    public String getKeyboard(Model model) {

        List<Keyboard> keyboardModels = keyboardModelService.getAllModels();
        List<SvtModelDto> getKeyboardDtoes = svtModelMapper.getKeyboardDtoes(keyboardModels);
        model.addAttribute("dtoes", getKeyboardDtoes);
        model.addAttribute("namePage", "Модели клавиатур");
        model.addAttribute("attribute", "mkeyboard");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mkeyboard")
    public String saveModelKeyboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Keyboard keyboard = svtModelMapper.getKeyboard(dto);
        keyboardModelService.saveModel(keyboard);
        return "redirect:/mkeyboard";
        
    }
    
 //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmouse")
    public String getMouse(Model model) {

        List<Mouse> mouseModels = mouseModelService.getAllModels();
        List<SvtModelDto> getMouseModelsDtoes = svtModelMapper.getMouseDtoes(mouseModels);
        model.addAttribute("dtoes", getMouseModelsDtoes);
        model.addAttribute("namePage", "Модели мышей");
        model.addAttribute("attribute", "mmouse");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mmouse")
    public String saveModelMouse(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Mouse mouse = svtModelMapper.getMouse(dto);
        mouseModelService.saveModel(mouse);
        return "redirect:/mmouse";
        
    }
    
 //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mspeakers")
    public String getSpeakers(Model model) {

        List<Speakers> speakersModels = speakersModelService.getAllModels();
        List<SvtModelDto> getSpeakersModelsDtoes = svtModelMapper.getSpeakersDtoes(speakersModels);
        model.addAttribute("dtoes", getSpeakersModelsDtoes);
        model.addAttribute("namePage", "Модели колонок");
        model.addAttribute("attribute", "mspeakers");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mspeakers")
    public String saveModelSpeakers(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Speakers speakers = svtModelMapper.getSpeakers(dto);
        speakersModelService.saveModel(speakers);
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
    @PostMapping("/os")
    public String saveOperationSystem(@RequestBody OperationSystemDto dto) throws ObjectAlreadyExists {
            OperationSystem operationSystem = operationSystemMapper.getOperationSystem(dto);
            operationSystemService.saveOperationSystem(operationSystem);
        return "redirect:/os";
        
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mscanner")
    public String getModelScanner(Model model) {

        List<ScannerModel> scannerModels = scannerModelService.getAllModels();
        List<SvtModelDto> scannerModelsDtoes = svtModelMapper.getModelScannerDtoes(scannerModels);
        model.addAttribute("dtoes", scannerModelsDtoes);
        model.addAttribute("namePage", "Модели сканеров");
        model.addAttribute("attribute", "mscanner");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mscanner")
    public String saveModelScanner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ScannerModel scannerModel = svtModelMapper.getModelScanner(dto);
        scannerModelService.saveModel(scannerModel);
        return "redirect:/mscanner";
        
    }
    
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/scanner")
    public String getScanners(Model model, @RequestParam(value="username", required=false) String username) {
        Map<Location, List<Scanner>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = scannerService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = scannerService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        Map<Location, List<Scanner>> svtObjectsByStorage = null;
        if(null != username) {
            svtObjectsByStorage = scannerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
           svtObjectsByStorage = scannerService.getSvtObjectsByPlaceType(PlaceType.STORAGE); 
        }
        
        List<LocationByTreeDto> treeSvtDtoByStorage = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "scanner");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Сканеры");
        
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/scanner")
    public String saveScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
        scannerService.createSvtObj(dto);
        return "redirect:/scanner";
     
    }
    
    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/scannertostor")
    public String sendToStorageScanner (@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
           // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Scanner scanner = scannerMapper.getEntityFromDto(dto);
        scannerService.sendToStorage(scanner);
        return "redirect:/scanner";
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/scannerbackstor")
    public String backFromStorageScanner (@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
            Scanner scanner = scannerMapper.getEntityFromDto(dto);
        scannerService.backFromStorage(scanner, dto.getPlaceId());
        return "redirect:/scanner";
        
    }
    
        @PostMapping("/updscanner")
    public String updateScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
        scannerService.updateSvtObj(dto);
        return "redirect:/scanner";
     
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/scannerarchived")
    public String sendScannerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        scannerService.svtObjToArchive(dto);
        return "redirect:/scanner";
    }
    
     //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mserver")
    public String getModelServer(Model model) {

        List<ServerModel> serverModels = serverModelService.getAllModels();
        List<SvtModelDto> getServerModelsDtoes = svtModelMapper.getModelServerDtoes(serverModels);
        model.addAttribute("dtoes", getServerModelsDtoes);
        model.addAttribute("namePage", "Модели серверов");
        model.addAttribute("attribute", "mserver");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mserver")
    public String saveModelServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ServerModel serverModel = svtModelMapper.getModelServer(dto);
        serverModelService.saveModel(serverModel);
        return "redirect:/mserver";
        
    }
    
    
    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/server")
    public String getServers(Model model, @RequestParam(value="username",required=false) String username) {
        Map<Location, List<Server>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = serverService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = serverService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Server>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = serverService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = serverService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "server");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Серверы");
        
        return "svtobj";
    }
    
 
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/server")
    public String saveServer(@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
        serverService.createSvtObj(dto);
        return "redirect:/server";
    }
    
    
        //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/servertostor")
    public String sendToStorageServer (@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Server server = serverMapper.getEntityFromDto(dto);
        serverService.sendToStorage(server);
        return "redirect:/server";
        
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/serverbackstor")
    public String backFromStorageServer (@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
            Server server = serverMapper.getEntityFromDto(dto);
        serverService.backFromStorage(server, dto.getPlaceId());
        return "redirect:/server";
        
    }
    
     @PostMapping("/updserver")
    public String updateServer (@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
        serverService.updateSvtObj(dto);
        return "redirect:/server";
     
    }
    
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/serverarchived")
    public String sendServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        serverService.svtObjToArchive(dto);
        return "redirect:/server";
    }
    
    
         //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mswitch")
    public String getModelSwitchHub(Model model) {

        List<SwitchHubModel> switchHubModels = switchHubModelService.getAllModels();
        List<SvtModelDto> getSwitchHubModelsDtoes = svtModelMapper.getModelSwitchHubDtoes(switchHubModels);
        model.addAttribute("dtoes", getSwitchHubModelsDtoes);
        model.addAttribute("namePage", "Модели коммутаторов/концентраторов");
        model.addAttribute("attribute", "mswitch");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mswitch")
    public String saveModelSwitchHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SwitchHubModel switchHubModel = svtModelMapper.getModelSwitchHub(dto);
        switchHubModelService.saveModel(switchHubModel);
        return "redirect:/mswitch";
        
    }
    
    
     //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/switch")
    public String getSwitchHub(Model model, @RequestParam(value="username",required=false) String username) {
        Map<Location, List<SwitchHub>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = switchHubService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = switchHubService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<SwitchHub>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = switchHubService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = switchHubService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "switch");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Коммутаторы/Концентраторы");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/switch")
    public String saveSwitchHub(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        switchHubService.createSvtObj(dto);
        return "redirect:/switch";
    }
    
           //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/switchtostor")
    public String sendToStorageSwitchHub (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            SwitchHub switchHub = switchHubMapper.getEntityFromDto(dto);
        switchHubService.sendToStorage(switchHub);
        return "redirect:/switch";
        
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/switchbackstor")
    public String backFromStorageSwitchHub (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            SwitchHub switchHub = switchHubMapper.getEntityFromDto(dto);
        switchHubService.backFromStorage(switchHub, dto.getPlaceId());
        return "redirect:/switch";
        
    }
    
     @PostMapping("/updswitch")
    public String updateSwitchHub (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        switchHubService.updateSvtObj(dto);
        return "redirect:/switch";
     
    }
    

    
            //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mrouter")
    public String getModelRouterb(Model model) {
        List<RouterModel> routerModels = routerModelService.getAllModels();
        List<SvtModelDto> getRouterModelsDtoes = svtModelMapper.getModelRouterDtoes(routerModels);
        model.addAttribute("dtoes", getRouterModelsDtoes);
        model.addAttribute("namePage", "Модели маршрутизаторов");
        model.addAttribute("attribute", "mrouter");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mrouter")
    public String saveModelRouter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        RouterModel routerModel = svtModelMapper.getModelRouter(dto);
        routerModelService.saveModel(routerModel);
        return "redirect:/mrouter";
        
    }
    
         //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/router")
    public String getRouter(Model model, @RequestParam(value="username",required=false) String username) {
        Map<Location, List<Router>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = routerService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = routerService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Router>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = routerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = routerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "router");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Маршрутизаторы");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/router")
    public String saveRouter(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        routerService.createSvtObj(dto);
        return "redirect:/router";
    }
    
           //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/routertostor")
    public String sendToStorageRouter(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Router router = routerMapper.getEntityFromDto(dto);
        routerService.sendToStorage(router);
        return "redirect:/router";
        
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/routerbackstor")
    public String backFromStorageRouter (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            Router router = routerMapper.getEntityFromDto(dto);
        routerService.backFromStorage(router, dto.getPlaceId());
        return "redirect:/router";
        
    }
    
     @PostMapping("/updrouter")
    public String updateRouter (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        routerService.updateSvtObj(dto);
        return "redirect:/router";
     
    }
    
        //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/routerarchived")
    public String sendRouterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        routerService.svtObjToArchive(dto);
        return "redirect:/router";
    }
    
    
                //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mats")
    public String getModelAts(Model model) {
        List<AtsModel> atsModels = atsModelService.getAllModels();
        List<SvtModelDto> getAtsModelsDtoes = svtModelMapper.getModelAtsDtoes(atsModels);
        model.addAttribute("dtoes", getAtsModelsDtoes);
        model.addAttribute("namePage", "Модели АТС");
        model.addAttribute("attribute", "mats");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mats")
    public String saveModelAts(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        AtsModel atsModel = svtModelMapper.getModelAts(dto);
        atsModelService.saveModel(atsModel);
        return "redirect:/mats";
        
    }
    
             //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/ats")
    public String getAts(Model model, @RequestParam(value="username",required=false) String username) {
        Map<Location, List<Ats>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = atsService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = atsService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = atsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Ats>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = atsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = atsService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = atsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "ats");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","АТС");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/ats")
    public String saveAts(@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
        atsService.createSvtObj(dto);
        return "redirect:/ats";
    }
    
    
        @PostMapping("/updats")
    public String updateAts (@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
        atsService.updateSvtObj(dto);
        return "redirect:/ats";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/atstostor")
    public String sendToStorageAts(@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Ats ats = atsMapper.getEntityFromDto(dto);
        atsService.sendToStorage(ats);
        return "redirect:/ats";
        
    }
    
       //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/atsbackstor")
    public String backFromStorageAts (@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
            Ats ats = atsMapper.getEntityFromDto(dto);
        atsService.backFromStorage(ats, dto.getPlaceId());
        return "redirect:/ats";
        
    }
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/atsarchived")
    public String sendAtsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        atsService.svtObjToArchive(dto);
        return "redirect:/ats";
    }
    
    
                   //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mconditioner")
    public String getModelConditioner(Model model) {
        List<ConditionerModel> conditionerModels = conditionerModelService.getAllModels();
        List<SvtModelDto> getAtsModelsDtoes = svtModelMapper.getModelConditionerDtoes(conditionerModels);
        model.addAttribute("dtoes", getAtsModelsDtoes);
        model.addAttribute("namePage", "Модели кондиционеров");
        model.addAttribute("attribute", "mconditioner");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/mconditioner")
    public String saveModelConditioner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ConditionerModel conditionerModel = svtModelMapper.getModelConditioner(dto);
        conditionerModelService.saveModel(conditionerModel);
        return "redirect:/mconditioner";
        
    }
    
                 //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/conditioner")
    public String getConditioner(Model model, @RequestParam(value="username", required=false) String username) {
        Map<Location, List<Conditioner>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = conditionerService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = conditionerService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = conditionerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Conditioner>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = conditionerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = conditionerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = conditionerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "conditioner");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Кондиционеры");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/conditioner")
    public String saveConditioner(@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
        conditionerService.createSvtObj(dto);
        return "redirect:/conditioner";
    }
    
          @PostMapping("/updconditioner")
    public String updateConditioner (@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
        conditionerService.updateSvtObj(dto);
        return "redirect:/conditioner";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/conditionertostor")
    public String sendToStorageConditioner(@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Conditioner conditioner = conditionerMapper.getEntityFromDto(dto);
        conditionerService.sendToStorage(conditioner);
        return "redirect:/conditioner";
        
    }
    
       //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/conditionerbackstor")
    public String backFromStorageConditioner (@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
            Conditioner conditioner = conditionerMapper.getEntityFromDto(dto);
        conditionerService.backFromStorage(conditioner, dto.getPlaceId());
        return "redirect:/conditioner";
        
    }
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/conditionerarchived")
    public String sendConditionerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        conditionerService.svtObjToArchive(dto);
        return "redirect:/conditioner";
    }
    
     //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/minfomat")
    public String getModelInfomat(Model model) {
        List<InfomatModel> infomatModels = infomatModelService.getAllModels();
        List<SvtModelDto> getInfomatModelsDtoes = svtModelMapper.getModelInfomatDtoes(infomatModels);
        model.addAttribute("dtoes", getInfomatModelsDtoes);
        model.addAttribute("namePage", "Модели инфоматов");
        model.addAttribute("attribute", "minfomat");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/minfomat")
    public String saveModelInfomat(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        InfomatModel infomatModel = svtModelMapper.getModelInfomat(dto);
        infomatModelService.saveModel(infomatModel);
        return "redirect:/minfomat";
    }
    
    
                  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/infomat")
    public String getInfomat(Model model, @RequestParam(value="username", required = false) String username) {
        Map<Location, List<Infomat>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = infomatService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = infomatService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = infomatOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Infomat>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = infomatService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = infomatService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = infomatOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "infomat");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Инфоматы");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/infomat")
    public String saveInfomat(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        infomatService.createSvtObj(dto);
        return "redirect:/infomat";
    }
    
          @PostMapping("/updinfomat")
    public String updateInfomat (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        infomatService.updateSvtObj(dto);
        return "redirect:/infomat";
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/infomattostor")
    public String sendToStorageInfomat(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
            // SystemBlock findById = sysrepo.findById(dto.getId()).get();
            Infomat infomat = infomatMapper.getEntityFromDto(dto);
        infomatService.sendToStorage(infomat);
        return "redirect:/infomat";
        
    }
    
       //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/infomatbackstor")
    public String backFromStorageInfomat (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
            Infomat infomat = infomatMapper.getEntityFromDto(dto);
        infomatService.backFromStorage(infomat, dto.getPlaceId());
        return "redirect:/infomat";
        
    }
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/infomatarchived")
    public String sendInfomatToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        infomatService.svtObjToArchive(dto);
        return "redirect:/infomat";
    }
    
}
