/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
import ru.gov.sfr.aos.monitoring.entities.Hub;
import ru.gov.sfr.aos.monitoring.entities.HubModel;
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
import ru.gov.sfr.aos.monitoring.entities.ProgramSoftware;
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
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.entities.SystemBlockModel;
import ru.gov.sfr.aos.monitoring.entities.Terminal;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplay;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplayModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinter;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinterModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensor;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensorModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalServer;
import ru.gov.sfr.aos.monitoring.entities.TerminalServerModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalUps;
import ru.gov.sfr.aos.monitoring.entities.TerminalUpsModel;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.entities.VideoCard;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.mappers.AtsMapper;
import ru.gov.sfr.aos.monitoring.mappers.AtsModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.BatteryTypeMapper;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.DisplayMapper;
import ru.gov.sfr.aos.monitoring.mappers.DisplayModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.HubMapper;
import ru.gov.sfr.aos.monitoring.mappers.HubModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.OperationSystemMapper;
import ru.gov.sfr.aos.monitoring.mappers.PhoneMapper;
import ru.gov.sfr.aos.monitoring.mappers.PhoneModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.RouterMapper;
import ru.gov.sfr.aos.monitoring.mappers.RouterModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.ScannerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ScannerModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.ServerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ServerModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchHubMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchHubModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemBlockMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemblockModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalDisplayModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalPrinterModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalSensorModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalServerModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalUpsModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsModelMapper;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.AsuoDTO;
import ru.gov.sfr.aos.monitoring.models.BatteryTypeDto;
import ru.gov.sfr.aos.monitoring.models.CpuModelDto;
import ru.gov.sfr.aos.monitoring.models.DepartmentDTO;
import ru.gov.sfr.aos.monitoring.models.FaxDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.HddDto;
import ru.gov.sfr.aos.monitoring.models.HubDto;
import ru.gov.sfr.aos.monitoring.models.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.models.PlaceDTO;
import ru.gov.sfr.aos.monitoring.models.ProgramSoftwareDto;
import ru.gov.sfr.aos.monitoring.models.RamDto;
import ru.gov.sfr.aos.monitoring.models.SvtAtsDTO;
import ru.gov.sfr.aos.monitoring.models.SvtConditionerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.models.SvtModelDto;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtServerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSystemBlockDTO;
import ru.gov.sfr.aos.monitoring.models.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.models.TerminalDisplayDto;
import ru.gov.sfr.aos.monitoring.models.TerminalDto;
import ru.gov.sfr.aos.monitoring.models.UpsModelDto;
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
import ru.gov.sfr.aos.monitoring.services.HubModelService;
import ru.gov.sfr.aos.monitoring.services.HubOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.HubService;
import ru.gov.sfr.aos.monitoring.services.InfomatModelService;
import ru.gov.sfr.aos.monitoring.services.InfomatOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.InfomatService;
import ru.gov.sfr.aos.monitoring.services.KeyboardModelService;
import ru.gov.sfr.aos.monitoring.services.LanCardModelService;
import ru.gov.sfr.aos.monitoring.services.LocationService;
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
import ru.gov.sfr.aos.monitoring.services.SystemBlockModelService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockService;
import ru.gov.sfr.aos.monitoring.services.TerminalDisplayModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalDisplayOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.TerminalDisplayService;
import ru.gov.sfr.aos.monitoring.services.TerminalModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.TerminalPrinterModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalPrinterOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.TerminalPrinterService;
import ru.gov.sfr.aos.monitoring.services.TerminalProgramSoftwareService;
import ru.gov.sfr.aos.monitoring.services.TerminalSensorModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalSensorOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.TerminalSensorService;
import ru.gov.sfr.aos.monitoring.services.TerminalServerModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalServerOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.TerminalServerService;
import ru.gov.sfr.aos.monitoring.services.TerminalService;
import ru.gov.sfr.aos.monitoring.services.TerminalUpsModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalUpsOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.TerminalUpsService;
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
    private DisplayModelService displayModelService;
    @Autowired
    private DisplayService displayService;
    @Autowired
    private DisplayOutDtoTreeService displayOutDtoTreeService;
    @Autowired
    private SubDisplayModelService subDisplayModelService;
    @Autowired
    private AsuoService asuoService;
    @Autowired
    private AsuoOutDtoTreeService asuoOutDtoTreeService;
    @Autowired
    private FaxModelService faxModelService;
    @Autowired
    private FaxService faxService;
    @Autowired
    private FaxOutDtoTreeService faxOutDtoTreeService;
    @Autowired
    private FaxMapper faxMapper;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UpsModelMapper upsModelMapper;
    @Autowired
    private PhoneModelMapper phoneModelMapper;
    @Autowired
    private FaxModelMapper faxModelMapper;
    @Autowired
    private MonitorModelMapper monitorModelMapper;
    @Autowired
    private ScannerModelMapper scannerModelMapper;
    @Autowired
    private ServerModelMapper serverModelMapper;
    @Autowired
    private SwitchHubModelMapper switchHubModelMapper;
    @Autowired
    private HubModelMapper hubModelMapper;
    @Autowired
    private RouterModelMapper routerModelMapper;
    @Autowired
    private AtsModelMapper atsModelMapper;
    @Autowired
    private ConditionerModelMapper conditionerModelMapper;
    @Autowired
    private InfomatModelMapper infomatModelMapper;
    @Autowired
    private SystemblockModelMapper sysblockModelMapper;
    @Autowired
    private HubModelService hubModelService;
    @Autowired
    private HubService hubService;
    @Autowired
    private HubOutDtoTreeService hubOutDtoTreeService;
    @Autowired
    private HubMapper hubMapper;
    @Autowired
    private TerminalDisplayService terminalDisplayService;
    @Autowired
    private TerminalDisplayOutDtoTreeService terminalDisplayOutDtoTreeService;
    @Autowired
    private TerminalDisplayModelService terminalDisplayModelService;
    @Autowired
    private TerminalDisplayModelMapper terminalDisplayModelMapper;
    @Autowired
    private TerminalUpsService terminalUpsService;
    @Autowired
    private TerminalUpsOutDtoTreeService terminalUpsOutDtoTreeService;
    @Autowired
    private TerminalPrinterService terminalPrinterService;
    @Autowired
    private TerminalPrinterOutDtoTreeService terminalPrinterOutDtoTreeService;
    @Autowired
    private TerminalPrinterModelService terminalPrinterModelService;
    @Autowired
    private TerminalPrinterModelMapper terminalPrinterModelMapper;
    @Autowired
    private TerminalUpsModelService terminalUpsModelService;
    @Autowired
    private TerminalUpsModelMapper terminalUpsModelMapper;
    @Autowired
    private TerminalServerService terminalServerService;
    @Autowired
    private TerminalServerOutDtoTreeService terminalServerOutDtoTreeService;
    @Autowired
    private TerminalServerModelService terminalServerModelService;
    @Autowired
    private TerminalServerModelMapper terminalServerModelMapper;
    @Autowired
    private TerminalSensorService terminalSensorService;
    @Autowired
    private TerminalSensorOutDtoTreeService terminalSensorOutDtoTreeService;
    @Autowired
    private TerminalSensorModelService terminalSensorModelService;
    @Autowired
    private TerminalSensorModelMapper terminalSensorModelMapper;
    @Autowired
    private TerminalProgramSoftwareService terminalProgramSoftwareService;
    @Autowired
    private TerminalModelMapper terminalModelMapper;
    @Autowired
    private DisplayModelMapper displayModelMapper;
    
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
   // @Log
    @PostMapping("/places")
    public ResponseEntity<String> addPlace(@RequestBody PlaceDTO dto) throws ObjectAlreadyExists {
            placeService.createPlace(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
        @PutMapping("/places")
    public ResponseEntity<String> updatePlace(@RequestBody PlaceDTO dto) throws ObjectAlreadyExists {

            placeService.updatePlace(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
   // @SendArchive
    @DeleteMapping("/placetoarchive")
    public ResponseEntity<String> sendPlaceToArchived(@RequestBody ArchivedDto dto) {
        placeService.sendPlaceToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mphones")
    public String getModelPhones(Model model) {

        List<PhoneModel> phoneModels = phoneModelService.getAllActualModels();
        List<SvtModelDto> phoneModelsDtoes = phoneModelMapper.getListDtoes(phoneModels);
        model.addAttribute("dtoes", phoneModelsDtoes);
        model.addAttribute("namePage", "Модели телефонов");
        model.addAttribute("attribute", "mphones");
        model.addAttribute("manufacturersSaveLink", "/save-phone-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-phone-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-phone-manufacturers");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mphones")
    public ResponseEntity<String> saveModelPhone(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        PhoneModel phoneModel = phoneModelMapper.getModel(dto);
        try{
            phoneModelService.saveModel(phoneModel);
        }catch(Exception e) {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    @PutMapping("/mphonesupd")
    public ResponseEntity<String> updateModelPhone(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        PhoneModel phoneModel = phoneModelMapper.getModel(dto);
        try{
            phoneModelService.update(phoneModel);
        } catch(Exception e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
  //  @SendArchive
    @DeleteMapping("/mphonesarchived")
    public ResponseEntity<String> sendModelPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        phoneModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmonitors")
    public String getModelMonitors(Model model) {

        List<MonitorModel> monitorModels = monitorModelService.getAllActualModels();
        List<SvtModelDto> monitorModelsDtoes = monitorModelMapper.getListDtoes(monitorModels);
        model.addAttribute("dtoes", monitorModelsDtoes);
        model.addAttribute("namePage", "Модели мониторов");
        model.addAttribute("attribute", "mmonitors");
        model.addAttribute("manufacturersSaveLink", "/save-monitor-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-monitor-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-monitor-manufacturers");
        return "models";
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @SendArchive
    @DeleteMapping("/mmonitorsarchived")
    public ResponseEntity<String> sendModelMonitorToArchive(@RequestBody ArchivedDto dto) {
        monitorModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/mmonitors")
    public ResponseEntity<String> saveModelMonitor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        MonitorModel monitorModel = monitorModelMapper.getModel(dto);
        try{
            monitorModelService.saveModel(monitorModel);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/mmonitorsupd")
    public ResponseEntity<String> updateModelMonitor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        MonitorModel monitorModel = monitorModelMapper.getModel(dto);
        try{
            monitorModelService.update(monitorModel);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mups")
    public String getModelUps(Model model) {
        
        List<UpsModel> upsModels = upsModelService.getAllActualModels();
        List<UpsModelDto> upsModelsDtoes = new ArrayList<>();
        for(UpsModel upsModel : upsModels) {
            UpsModelDto dto = upsModelMapper.getDto(upsModel);
            upsModelsDtoes.add(dto);
        }
        
        
        model.addAttribute("dtoes", upsModelsDtoes);
        model.addAttribute("namePage", "Модели ИБП");
        model.addAttribute("attribute", "mups");
        model.addAttribute("manufacturersSaveLink", "/save-ups-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-ups-manufacturers");
        
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/mups")
    public ResponseEntity<String> saveModelUps(@RequestBody UpsModelDto dto) throws ObjectAlreadyExists {
        UpsModel upsModel = upsModelMapper.getEntityFromDto(dto);
        try{
            upsModelService.saveModel(upsModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED); 
    }
    
    @PutMapping("/mupsupd")
    public ResponseEntity<String> updateModelUps(@RequestBody UpsModelDto dto) throws ObjectAlreadyExists {
        UpsModel upsModel = upsModelMapper.getEntityFromDto(dto);
        try{
            upsModelService.update(upsModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED); 
    }
    
  //  @SendArchive
    @DeleteMapping("/mupsarchived")
    public ResponseEntity<String> sendModelUpsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        upsModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
 //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mupsbat")
    public String getBatteryTypeUps(Model model) {

        List<BatteryType> allBatteryTypes = batteryTypeService.getAllActualBatteryTypes();
        List<BatteryTypeDto> batteryTypeDtoesList = batteryTypeMapper.getBatteryTypeDtoesList(allBatteryTypes);
        model.addAttribute("dtoes", batteryTypeDtoesList);
        model.addAttribute("namePage", "Типы батарей для ИБП");
        model.addAttribute("attribute", "mupsbat");
        
        return "models";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mupsbat")
    public ResponseEntity<String> saveBatteryTypeUps(@RequestBody BatteryTypeDto dto) throws ObjectAlreadyExists {
        BatteryType batteryType = batteryTypeMapper.getBatteryType(dto);
        try {
            batteryTypeService.saveBatteryType(batteryType);
        } catch(Exception e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/mupsbatupd")
    public ResponseEntity<String> updateBatteryTypeUps(@RequestBody BatteryTypeDto dto) throws ObjectAlreadyExists {
        BatteryType batteryType = batteryTypeMapper.getBatteryType(dto);
        try{
            batteryTypeService.update(batteryType);
        } catch(Exception e) {
            return new ResponseEntity(e. getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/mupsbatarchived")
    public ResponseEntity<String> sendToArchiveBatteryTypeUps(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        batteryTypeService.sendToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
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
    public String getPhones(Model model, @RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber, 
            @RequestParam(value="serialNumber", required = false) String serialNumber, 
            @ModelAttribute FilterDto dto) {
        
        List<SvtDTO> filter = null;
        Map<Location, List<Phone>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Phone>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = phoneService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else if(null != inventaryNumber) {
            svtObjectsByEmployee = phoneService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
        }else if(null != serialNumber) {
            svtObjectsByEmployee = phoneService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
        }else {
            svtObjectsByEmployee = phoneService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        
        treeSvtDtoByEmployee = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = phoneService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = phoneService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = phoneService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<PhoneModel> optModel = phoneModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "phones");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Телефоны");
        model.addAttribute("amountDevice", amount);
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/ups")
    public String getUps(Model model, @RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber, 
            @RequestParam(value="serialNumber", required = false) String serialNumber, 
            @ModelAttribute FilterDto dto) {
        
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        }else if(null != inventaryNumber) {
            svtObjectsByEmployee = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
        }else if(null != serialNumber) {
            svtObjectsByEmployee = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
        }else {
            svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
         treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<UpsModel> optModel = upsModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "ups");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","ИБП");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
   // @Log
    @PostMapping("/ups")
    public ResponseEntity<String> saveUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
   // @SendArchive
    @DeleteMapping("/upsarchived")
    public ResponseEntity<String> sendUpsToArchive(@RequestBody ArchivedDto dto) {
        upsService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/upsforserver")
    public String getServerUps(Model model, @RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber, 
            @RequestParam(value="serialNumber", required = false) String serialNumber, 
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
         List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        }else if(null != inventaryNumber) {
            svtObjectsByEmployee = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
        }else if(null != serialNumber) {
            svtObjectsByEmployee = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        }else {
            svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<UpsModel> optModel = upsModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "upsforserver");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","ИБП");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/upsforserver")
    public ResponseEntity<String> saveServerUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
  //  @SendArchive
    @DeleteMapping("/upsforserverarchived")
    public ResponseEntity<String> sendUpsForServerToArchive(@RequestBody ArchivedDto dto) {
        upsService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/updupsforserver")
    public ResponseEntity<String> updateServerUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/phones")
    public ResponseEntity<String> savePhone(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        phoneService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/updphone")
    public ResponseEntity<String> updatePhone(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        phoneService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
  //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/updups")
    public ResponseEntity<String> updateUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        upsService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/updmonitor")
    public ResponseEntity<String> updateMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        monitorService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
  //  @SendArchive
   @DeleteMapping("/monitorsarchived") 
   public ResponseEntity<String> sendMonitorToArchive(@RequestBody ArchivedDto dto) {
        monitorService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //  @UpdLog
    @PutMapping("/phonetostor")
    public ResponseEntity<String> sendToStorage (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Phone phone = phoneService.getById(dto.getId());
        phoneService.sendToStorage(phone);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
  //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/upstostor")
    public ResponseEntity<String> sendToStorageUps (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        
        Ups ups = upsService.getById(dto.getId());
        upsService.sendToStorage(ups);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
  //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/monitortostor")
    public ResponseEntity<String> sendToStorageMonitor (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Monitor monitor = monitorService.getById(dto.getId());
        monitorService.sendToStorage(monitor);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/monitors")
    public String getMonitors(Model model, @RequestParam(value="username",required=false) String username, 
             @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
             @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Monitor>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
         Map<Location, List<Monitor>> svtObjectsByStorage = null;
         List<LocationByTreeDto> treeSvtDtoByStorage = null;
         List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
        svtObjectsByEmployee = monitorService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else if(null != inventaryNumber) {
                svtObjectsByEmployee = monitorService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
            }else if(null != serialNumber) {
                svtObjectsByEmployee = monitorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
            } else {
            svtObjectsByEmployee = monitorService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
        treeSvtDtoByEmployee = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        if(null != username) {
             svtObjectsByStorage = monitorService.getSvtObjectsByName(username, PlaceType.STORAGE);
         } else if(null != inventaryNumber) {
             svtObjectsByStorage = monitorService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
         }else if(null != serialNumber) {
             svtObjectsByStorage = monitorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<MonitorModel> optModel = monitorModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "monitors");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Мониторы");
        model.addAttribute("amountDevice", amount);
        
        return "svtobj";
    }
  
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/monitors")
    public ResponseEntity<String> saveMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        monitorService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    

    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @SendArchive
    @DeleteMapping("/phonesarchived")
    public ResponseEntity<String> sendPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        phoneService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }   
    
    
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/msysblock")
    public String getModelSystemblock(Model model) {

        List<SystemBlockModel> systemBlockModels = systemBlockModelService.getAllActualModels();
        List<SvtModelDto> systemBlockModelsDtoes = sysblockModelMapper.getListDtoes(systemBlockModels);
        model.addAttribute("dtoes", systemBlockModelsDtoes);
        model.addAttribute("namePage", "Модели системных блоков");
        model.addAttribute("attribute", "msysblock");
        model.addAttribute("manufacturersSaveLink", "/save-sysblock-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-sysblock-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-sysblock-manufacturers");
        return "models";
    }
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/msysblock")
    public ResponseEntity<String> saveModelSystemBlock(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SystemBlockModel systemBlockModel = sysblockModelMapper.getModel(dto);
        try{
            systemBlockModelService.saveModel(systemBlockModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/msysblockupd")
    public ResponseEntity<String> updateModelSystemBlock(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SystemBlockModel systemBlockModel = sysblockModelMapper.getModel(dto);
        try{
            systemBlockModelService.update(systemBlockModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
  //  @SendArchive
    @DeleteMapping("/msysblockarchived")
    public ResponseEntity<String> sendModelSystemBlockToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        systemBlockModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
    
  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/sysblocks")
    public String getSysBlocks(Model model, @RequestParam(value="username",required=false) String username,  
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<SystemBlock>> svtObjectsByEmployee = null;
         List<SvtDTO> filter = null;
         List<LocationByTreeDto> treeSvtDtoByEmployee = null;
         Map<Location, List<SystemBlock>> svtObjectsByStorage = null;
         List<LocationByTreeDto> treeSvtDtoByStorage = null;
         
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = systemblockService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        }else if(null != inventaryNumber) {
            svtObjectsByEmployee = systemblockService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
        }else if(null != serialNumber) {
            svtObjectsByEmployee = systemblockService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
        }else {
            svtObjectsByEmployee = systemblockService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        treeSvtDtoByEmployee = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = systemblockService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = systemblockService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = systemblockService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<SystemBlockModel> optModel = systemBlockModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "systemblock");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Системные блоки");
        model.addAttribute("amountDevice", amount);
        
        return "svtobj";
    }
    
 
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/sysblocks")
    public ResponseEntity<String> saveSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
        systemblockService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
  //@PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/sysblockstostor")
    public ResponseEntity<String> sendToStorageSystemblock (@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
        SystemBlock systemblock = systemblockService.getById(dto.getId());
        systemblockService.sendToStorage(systemblock);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

 //   @UpdLog
        @PutMapping("/updsysblocks")
    public ResponseEntity<String> updateSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
        systemblockService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @SendArchive
    @DeleteMapping("/systemblockarchived")
    public ResponseEntity<String> sendSystemBlockToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        systemblockService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
 //  @Log
    @PostMapping("/mmotherboard")
    public ResponseEntity<String> saveModelMotherboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
            Motherboard modelMotherboard = svtModelMapper.getModelMotherboard(dto);
            try{
                motherboardModelService.saveModel(modelMotherboard);
            }catch(Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    @PutMapping("/mmotherboardupd")
    public ResponseEntity<String> updateModelMotherboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
            Motherboard modelMotherboard = svtModelMapper.getModelMotherboard(dto);
            try{
                motherboardModelService.update(modelMotherboard);
            }catch(Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
 //   @SendArchive
    @DeleteMapping("/mmotherboardarchived")
    public ResponseEntity<String> sendModelMotherboardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        motherboardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
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
 //   @Log
    @PostMapping("/mcpu")
    public ResponseEntity<String> saveModelCpu(@RequestBody CpuModelDto dto) throws ObjectAlreadyExists {
        Cpu cpu = svtModelMapper.getCpu(dto);
        try{
            cpuModelService.saveModel(cpu);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
 //   @SendArchive
    @DeleteMapping("/mcpuarchived")
    public ResponseEntity<String> sendModelCpuToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        cpuModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
 //   @UpdLog
    @PutMapping("/mcpuupd")
    public ResponseEntity<String> updateModelCpu(@RequestBody CpuModelDto dto) throws ObjectAlreadyExists {
        Cpu cpu = svtModelMapper.getCpu(dto);
        try{
            cpuModelService.update(cpu);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
 //   @Log
    @PostMapping("/mram")
    public ResponseEntity<String> saveModelRam(@RequestBody RamDto dto) throws ObjectAlreadyExists {
        Ram ram = svtModelMapper.getRam(dto);
        try{
            ramModelService.saveModel(ram);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
  //   @SendArchive
    @DeleteMapping("/mramarchived")
    public ResponseEntity<String> sendModelRamToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        ramModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
    
  //     @UpdLog
    @PutMapping("/mramupd")
    public ResponseEntity<String> updateModelRam(@RequestBody RamDto dto) throws ObjectAlreadyExists {
        Ram ram = svtModelMapper.getRam(dto);
        try{
            ramModelService.update(ram);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
  //  @Log
    @PostMapping("/mhdd")
    public ResponseEntity<String> saveModelHdd(@RequestBody HddDto dto) throws ObjectAlreadyExists {
        Hdd hdd = svtModelMapper.getHdd(dto);
        try{
            hddModelService.saveModel(hdd);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
//    @SendArchive
    @DeleteMapping("/mhddarchived")
    public ResponseEntity<String> sendModelHddToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        hddModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

        @PutMapping("/mhddupd")
    public ResponseEntity<String> updateModelHdd(@RequestBody HddDto dto) throws ObjectAlreadyExists {
        Hdd hdd = svtModelMapper.getHdd(dto);
        try{
            hddModelService.update(hdd);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
 //   @Log
    @PostMapping("/mvideo")
    public ResponseEntity<String> saveModelVideoCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        VideoCard videocard = svtModelMapper.getVideoCard(dto);
        try{
            videoCardModelService.saveModel(videocard);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //     @SendArchive
    @DeleteMapping("/mvideoarchived")
    public ResponseEntity<String> sendModelVideoCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        videoCardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
 //   @UpdLog
    @PutMapping("/mvideoupd")
    public ResponseEntity<String> updateModelVideoCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        VideoCard videocard = svtModelMapper.getVideoCard(dto);
        try{
            videoCardModelService.update(videocard);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
  //  @Log
    @PostMapping("/mcddrive")
    public ResponseEntity<String> saveModelCdDrive(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        CdDrive cdDrive = svtModelMapper.getCdDrive(dto);
        try{
            cdDriveModelService.saveModel(cdDrive);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
  //  @SendArchive
    @DeleteMapping("/mcddrivearchived")
    public ResponseEntity<String> sendModelCdDriveToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        cdDriveModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //   @UpdLog
    @PutMapping("/mcddriveupd")
    public ResponseEntity<String> updateModelCdDrive(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        CdDrive cdDrive = svtModelMapper.getCdDrive(dto);
        try{
            cdDriveModelService.update(cdDrive);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
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
 //   @Log
    @PostMapping("/mscard")
    public ResponseEntity<String> saveModelSCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SoundCard soundCard = svtModelMapper.getSoundCard(dto);
        try{
            soundCardModelService.saveModel(soundCard);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //    @SendArchive
    @DeleteMapping("/mscardarchived")
    public ResponseEntity<String> sendModelSCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        soundCardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //   @UpdLog
    @PutMapping("/mscardupd")
    public ResponseEntity<String> updateModelSCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SoundCard soundCard = svtModelMapper.getSoundCard(dto);
        try{
            soundCardModelService.update(soundCard);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
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
 //   @Log
    @PostMapping("/mlcard")
    public ResponseEntity<String> saveModelLanCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        LanCard lanCard = svtModelMapper.getLanCard(dto);
        try{
            lanCardModelService.saveModel(lanCard);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //     @SendArchive
    @DeleteMapping("/mlcardarchived")
    public ResponseEntity<String> sendModelLanCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        lanCardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //      @Log
    @PutMapping("/mlcardupd")
    public ResponseEntity<String> updateModelLanCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        LanCard lanCard = svtModelMapper.getLanCard(dto);
        try{
            lanCardModelService.update(lanCard);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
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
//    @Log
    @PostMapping("/mkeyboard")
    public ResponseEntity<String> saveModelKeyboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Keyboard keyboard = svtModelMapper.getKeyboard(dto);
        try{
           keyboardModelService.saveModel(keyboard); 
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
  //    @SendArchive
    @DeleteMapping("/mkeyboardarchived")
    public ResponseEntity<String> sendModelKeyboardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        keyboardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //       @UpdLog
    @PutMapping("/mkeyboardupd")
    public ResponseEntity<String> updateModelKeyboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Keyboard keyboard = svtModelMapper.getKeyboard(dto);
        try{
            keyboardModelService.update(keyboard);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
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
 //   @Log
    @PostMapping("/mmouse")
    public ResponseEntity<String> saveModelMouse(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Mouse mouse = svtModelMapper.getMouse(dto);
        try{
            mouseModelService.saveModel(mouse);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
//    @SendArchive
    @DeleteMapping("/mmousearchived")
    public ResponseEntity<String> sendModelMouseToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        mouseModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //   @UpdLog
    @PutMapping("/mmouseupd")
    public ResponseEntity<String> updateModelMouse(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Mouse mouse = svtModelMapper.getMouse(dto);
        try{
            mouseModelService.update(mouse);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
 //   @Log
    @PostMapping("/mspeakers")
    public ResponseEntity<String> saveModelSpeakers(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Speakers speakers = svtModelMapper.getSpeakers(dto);
        try{
            speakersModelService.saveModel(speakers);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //    @SendArchive
    @DeleteMapping("/mspeakersarchived")
    public ResponseEntity<String> sendModelSpeakersToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        speakersModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //    @UpdLog
    @PutMapping("/mspeakersupd")
    public ResponseEntity<String> updateModelSpeakers(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Speakers speakers = svtModelMapper.getSpeakers(dto);
        try{
            speakersModelService.update(speakers);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
//    @Log
    @PostMapping("/os")
    public ResponseEntity<String> saveOperationSystem(@RequestBody OperationSystemDto dto) throws ObjectAlreadyExists {
            OperationSystem operationSystem = operationSystemMapper.getOperationSystem(dto);
            operationSystemService.saveOperationSystem(operationSystem);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/osupd")
    public ResponseEntity<String> updateOperationSystem(@RequestBody OperationSystemDto dto) throws ObjectAlreadyExists {
            OperationSystem operationSystem = operationSystemMapper.getOperationSystem(dto);
            operationSystemService.updateOperationSystem(operationSystem);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
        @DeleteMapping("/osarchived")
    public ResponseEntity<String> osToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        operationSystemService.sendOsToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mscanner")
    public String getModelScanner(Model model) {

        List<ScannerModel> scannerModels = scannerModelService.getAllActualModels();
        List<SvtModelDto> scannerModelsDtoes = scannerModelMapper.getListDtoes(scannerModels);
        model.addAttribute("dtoes", scannerModelsDtoes);
        model.addAttribute("namePage", "Модели сканеров");
        model.addAttribute("attribute", "mscanner");
        model.addAttribute("manufacturersSaveLink", "/save-scanner-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-scanner-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-scanner-manufacturers");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mscanner")
    public ResponseEntity<String> saveModelScanner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ScannerModel scannerModel = scannerModelMapper.getModel(dto);
        try{
            scannerModelService.saveModel(scannerModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
    @PutMapping("/mscannerupd")
    public ResponseEntity<String> updateModelScanner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ScannerModel scannerModel = scannerModelMapper.getModel(dto);
        try{
            scannerModelService.update(scannerModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
 //    @SendArchive
    @DeleteMapping("/mscannerarchived")
    public ResponseEntity<String> sendModelScannerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        
        scannerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/scanner")
    public String getScanners(Model model, @RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        
        Map<Location, List<Scanner>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Scanner>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = scannerService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        }else if(null != inventaryNumber) {
             svtObjectsByEmployee = scannerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
        }else if(null != serialNumber) {
             svtObjectsByEmployee = scannerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
        } else {
            svtObjectsByEmployee = scannerService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
       treeSvtDtoByEmployee = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = scannerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if(null != inventaryNumber) {
            svtObjectsByStorage = scannerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = scannerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<ScannerModel> optModel = scannerModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "scanner");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Сканеры");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/scanner")
    public ResponseEntity<String> saveScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
        scannerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/scannertostor")
    public ResponseEntity<String> sendToStorageScanner (@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
        Scanner scanner = scannerService.getById(dto.getId());
        scannerService.sendToStorage(scanner);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

 //       @UpdLog
        @PutMapping("/updscanner")
    public ResponseEntity<String> updateScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
        scannerService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @SendArchive
    @DeleteMapping("/scannerarchived")
    public ResponseEntity<String> sendScannerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        scannerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
     //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mserver")
    public String getModelServer(Model model) {
        List<ServerModel> serverModels = serverModelService.getAllActualModels();
        List<SvtModelDto> getServerModelsDtoes = serverModelMapper.getListDtoes(serverModels);
        model.addAttribute("dtoes", getServerModelsDtoes);
        model.addAttribute("namePage", "Модели серверов");
        model.addAttribute("attribute", "mserver");
        model.addAttribute("manufacturersSaveLink", "/save-server-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-server-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-server-manufacturers");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mserver")
    public ResponseEntity<String> saveModelServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ServerModel serverModel = serverModelMapper.getModel(dto);
        try{
            serverModelService.saveModel(serverModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/mserverupd")
    public ResponseEntity<String> updateModelServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ServerModel serverModel = serverModelMapper.getModel(dto);
        try{
            serverModelService.update(serverModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //   @SendArchive
    @DeleteMapping("/mserverarchived")
    public ResponseEntity<String> sendModelServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        serverModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/server")
    public String getServers(Model model, @RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Server>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Server>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = serverService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        }else if(null != inventaryNumber) {
            svtObjectsByEmployee = serverService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
        }else if(null != serialNumber) {
            svtObjectsByEmployee = serverService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = serverService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = serverService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = serverService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = serverService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<ServerModel> optModel = serverModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "server");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Серверы");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
 
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PostMapping("/server")
    public ResponseEntity<String> saveServer(@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
        serverService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
        //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PostMapping("/servertostor")
    public ResponseEntity<String> sendToStorageServer (@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
            Server server = serverService.getById(dto.getId());
        serverService.sendToStorage(server);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

   // @UpdLog
     @PutMapping("/updserver")
    public ResponseEntity<String> updateServer (@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
        serverService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @SendArchive
    @DeleteMapping("/serverarchived")
    public ResponseEntity<String> sendServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        serverService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
         //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mswitch")
    public String getModelSwitchHub(Model model) {

        List<SwitchHubModel> switchHubModels = switchHubModelService.getAllActualModels();
        List<SvtModelDto> getSwitchHubModelsDtoes = switchHubModelMapper.getListDtoes(switchHubModels);
        model.addAttribute("dtoes", getSwitchHubModelsDtoes);
        model.addAttribute("namePage", "Модели коммутаторов/концентраторов");
        model.addAttribute("attribute", "mswitch");
        model.addAttribute("manufacturersSaveLink", "/save-switch-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-switch-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-switch-manufacturers");
        return "models";
    }
    
    
    
             //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mhub")
    public String getModelHub(Model model) {

        List<HubModel> models = hubModelService.getAllActualModels();
        List<SvtModelDto> modelDtoes = hubModelMapper.getListDtoes(models);
        model.addAttribute("dtoes", modelDtoes);
        model.addAttribute("namePage", "Модели коммутаторов");
        model.addAttribute("attribute", "mhub");
        model.addAttribute("manufacturersSaveLink", "/save-hub-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-hub-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-hub-manufacturers");
        return "models";
    }
    
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mswitch")
    public ResponseEntity<String> saveModelSwitchHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SwitchHubModel switchHubModel = switchHubModelMapper.getModel(dto);
        try{
            switchHubModelService.saveModel(switchHubModel);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/mswitchupd")
    public ResponseEntity<String> updateModelSwitchHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SwitchHubModel switchHubModel = switchHubModelMapper.getModel(dto);
        try{
            switchHubModelService.update(switchHubModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //     @SendArchive
    @DeleteMapping("/mswitcharchived")
    public ResponseEntity<String> sendModelSwitchHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        switchHubModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    
    
    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mhub")
    public ResponseEntity<String> saveModelHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        HubModel hubModel = hubModelMapper.getModel(dto);
        try{
            hubModelService.saveModel(hubModel);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/mhub")
    public ResponseEntity<String> updateModelHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        HubModel hubModel = hubModelMapper.getModel(dto);
        try{
            hubModelService.update(hubModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //     @SendArchive
    @DeleteMapping("/mhubarchived")
    public ResponseEntity<String> sendModelHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        hubModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    
     //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/hub")
    public String getHub(Model model, @RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Hub>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Hub>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<HubDto> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = hubService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        }else if(null != inventaryNumber) {
            svtObjectsByEmployee = hubService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
        }else if(null != serialNumber) {
            svtObjectsByEmployee = hubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = hubService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = hubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = hubService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if(null != inventaryNumber) {
            svtObjectsByStorage = hubService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = hubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = hubService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = hubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        } else {
        
            List<Hub> hubByFilter = hubService.getHubByFilter(dto);
             filter = new ArrayList<>();
            for(Hub p : hubByFilter) {
                HubDto hubDto = hubMapper.getDto(p);
                filter.add(hubDto);
            }
            
            svtObjectsByEmployee = hubService.getHubByPlaceTypeAndFilter(PlaceType.SERVERROOM, hubByFilter);
            treeSvtDtoByEmployee = hubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = hubService.getHubByPlaceTypeAndFilter(PlaceType.STORAGE, hubByFilter);
               treeSvtDtoByStorage = hubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
        }
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<HubModel> optModel = hubModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "hub");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Коммутаторы");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/hub")
    public ResponseEntity<String> saveHub(@RequestBody HubDto dto) throws ObjectAlreadyExists {
        hubService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
           //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/hubtostor")
    public ResponseEntity<String> sendToStorageHub (@RequestBody HubDto dto) throws ObjectAlreadyExists {
        Hub hub = hubService.getById(dto.getId());
        hubService.sendToStorage(hub);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

 //   @UpdLog
     @PutMapping("/hub")
    public ResponseEntity<String> updateHub (@RequestBody HubDto dto) throws ObjectAlreadyExists {
        hubService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @SendArchive
    @DeleteMapping("/hubarchived")
    public ResponseEntity<String> sendHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        hubService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    
     //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/switch")
    public String getSwitchHub(Model model, @RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<SwitchHub>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<SwitchHub>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = switchHubService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        }else if(null != inventaryNumber) {
            svtObjectsByEmployee = switchHubService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
        }else if(null != serialNumber) {
            svtObjectsByEmployee = switchHubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = switchHubService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = switchHubService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if(null != inventaryNumber) {
            svtObjectsByStorage = switchHubService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if(null != serialNumber) {
            svtObjectsByStorage = switchHubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<SwitchHubModel> optModel = switchHubModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "switch");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Коммутаторы/Концентраторы");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/switch")
    public ResponseEntity<String> saveSwitchHub(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        switchHubService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
           //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/switchtostor")
    public ResponseEntity<String> sendToStorageSwitchHub (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            SwitchHub switchHub = switchHubService.getById(dto.getId());
        switchHubService.sendToStorage(switchHub);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

 //   @UpdLog
     @PutMapping("/updswitch")
    public ResponseEntity<String> updateSwitchHub (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        switchHubService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @SendArchive
    @DeleteMapping("/switcharchived")
    public ResponseEntity<String> sendSwitchHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        switchHubService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
            //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mrouter")
    public String getModelRouterb(Model model) {
        List<RouterModel> routerModels = routerModelService.getAllActualModels();
        List<SvtModelDto> getRouterModelsDtoes = routerModelMapper.getListDtoes(routerModels);
        model.addAttribute("dtoes", getRouterModelsDtoes);
        model.addAttribute("namePage", "Модели маршрутизаторов");
        model.addAttribute("attribute", "mrouter");
        model.addAttribute("manufacturersSaveLink", "/save-router-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-router-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-router-manufacturers");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mrouter")
    public ResponseEntity<String> saveModelRouter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        RouterModel routerModel = routerModelMapper.getModel(dto);
        try{
        routerModelService.saveModel(routerModel);
        }catch(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/mrouterupd")
    public ResponseEntity<String> updateModelRouter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        RouterModel routerModel = routerModelMapper.getModel(dto);
        try{
            routerModelService.update(routerModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
  //  @SendArchive
    @DeleteMapping("/mrouterarchived")
    public ResponseEntity<String> sendModelRouterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        routerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
         //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/router")
    public String getRouter(Model model, @RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Router>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Router>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = routerService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else if(null != inventaryNumber) {
            svtObjectsByEmployee = routerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
        } else if(null != serialNumber) {
            svtObjectsByEmployee = routerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        }else {
            svtObjectsByEmployee = routerService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        if(null != username) {
            svtObjectsByStorage = routerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if(null != inventaryNumber) {
            svtObjectsByStorage = routerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<RouterModel> optModel = routerModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "router");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Маршрутизаторы");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/router")
    public ResponseEntity<String> saveRouter(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        routerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
           //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/routertostor")
    public ResponseEntity<String> sendToStorageRouter(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            Router router = routerService.getById(dto.getId());
        routerService.sendToStorage(router);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

 //   @UpdLog
     @PutMapping("/updrouter")
    public ResponseEntity<String> updateRouter (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        routerService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
        //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @SendArchive
    @PutMapping("/routerarchived")
    public ResponseEntity<String> sendRouterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        routerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
                //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mats")
    public String getModelAts(Model model) {
        List<AtsModel> atsModels = atsModelService.getAllActualModels();
        List<SvtModelDto> getAtsModelsDtoes = atsModelMapper.getListDtoes(atsModels);
        model.addAttribute("dtoes", getAtsModelsDtoes);
        model.addAttribute("namePage", "Модели АТС");
        model.addAttribute("attribute", "mats");
        model.addAttribute("manufacturersSaveLink", "/save-ats-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-ats-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-ats-manufacturers");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mats")
    public ResponseEntity<String> saveModelAts(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        AtsModel atsModel = atsModelMapper.getModel(dto);
        try{
            atsModelService.saveModel(atsModel);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/matsupd")
    public ResponseEntity<String> updateModelAts(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        AtsModel atsModel = atsModelMapper.getModel(dto);
        try{
            atsModelService.update(atsModel);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //    @SendArchive
    @DeleteMapping("/matsarchived")
    public ResponseEntity<String> sendModelAtsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        atsModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
             //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/ats")
    public String getAts(Model model, @RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Ats>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Ats>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = atsService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        }else if(null != inventaryNumber) {
        svtObjectsByEmployee = atsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
        }else if(null != serialNumber) {
        svtObjectsByEmployee = atsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = atsService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = atsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = atsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = atsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = atsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<AtsModel> optModel = atsModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "ats");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","АТС");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/ats")
    public ResponseEntity<String> saveAts(@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
        atsService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //   @UpdLog
        @PutMapping("/updats")
    public ResponseEntity<String> updateAts (@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
        atsService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/atstostor")
    public ResponseEntity<String> sendToStorageAts(@RequestBody SvtAtsDTO dto) throws ObjectAlreadyExists {
            Ats ats = atsService.getById(dto.getId());
        atsService.sendToStorage(ats);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @SendArchive
    @DeleteMapping("/atsarchived")
    public ResponseEntity<String> sendAtsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        atsService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
                   //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mconditioner")
    public String getModelConditioner(Model model) {
        List<ConditionerModel> conditionerModels = conditionerModelService.getAllActualModels();
        List<SvtModelDto> getAtsModelsDtoes = conditionerModelMapper.getListDtoes(conditionerModels);
        model.addAttribute("dtoes", getAtsModelsDtoes);
        model.addAttribute("namePage", "Модели кондиционеров");
        model.addAttribute("attribute", "mconditioner");
        model.addAttribute("manufacturersSaveLink", "/save-conditioner-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-conditioner-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-conditioner-manufacturers");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mconditioner")
    public ResponseEntity<String> saveModelConditioner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ConditionerModel conditionerModel = conditionerModelMapper.getModel(dto);
        try{
            conditionerModelService.saveModel(conditionerModel);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/mconditionerupd")
    public ResponseEntity<String> updModelConditioner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ConditionerModel conditionerModel = conditionerModelMapper.getModel(dto);
        try{
            conditionerModelService.update(conditionerModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
//     @SendArchive
    @DeleteMapping("/mconditionerarchived")
    public ResponseEntity<String> sendModelConditionerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        conditionerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
                 //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/conditioner")
    public String getConditioner(Model model, @RequestParam(value="username", required=false) String username,
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Conditioner>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Conditioner>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
         List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = conditionerService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        }else if(null != inventaryNumber) {
        svtObjectsByEmployee = conditionerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
        }else if(null != serialNumber) {
        svtObjectsByEmployee = conditionerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        } else {
            svtObjectsByEmployee = conditionerService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = conditionerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = conditionerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
        svtObjectsByStorage = conditionerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if(null != serialNumber) {
        svtObjectsByStorage = conditionerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
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
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<ConditionerModel> optModel = conditionerModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "conditioner");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Кондиционеры");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/conditioner")
    public ResponseEntity<String> saveConditioner(@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
        conditionerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
          @PutMapping("/updconditioner")
    public ResponseEntity<String> updateConditioner (@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
        conditionerService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/conditionertostor")
    public ResponseEntity<String> sendToStorageConditioner(@RequestBody SvtConditionerDTO dto) throws ObjectAlreadyExists {
            Conditioner conditioner = conditionerService.getById(dto.getId());
        conditionerService.sendToStorage(conditioner);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

   
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @SendArchive
    @DeleteMapping("/conditionerarchived")
    public ResponseEntity<String> sendConditionerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        conditionerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
                       //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mfax")
    public String getModelFax(Model model) {
        List<FaxModel> faxModels = faxModelService.getAllActualModels();
        List<SvtModelDto> getFaxModelsDtoes = faxModelMapper.getListDtoes(faxModels);
        model.addAttribute("dtoes", getFaxModelsDtoes);
        model.addAttribute("namePage", "Модели факсов");
        model.addAttribute("attribute", "mfax");
        model.addAttribute("manufacturersSaveLink", "/save-fax-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-fax-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-fax-manufacturers");
        return "models";
    }
    
    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/mfaxupd")
    public ResponseEntity<String> updateModelFax(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        FaxModel faxModel = faxModelMapper.getModel(dto);
        try{
            faxModelService.update(faxModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mfax")
    public ResponseEntity<String> saveModelFax(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        FaxModel faxModel = faxModelMapper.getModel(dto);
        try{
            faxModelService.saveModel(faxModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
 //   @SendArchive
    @DeleteMapping("/mfaxarchived")
    public ResponseEntity<String> sendModelFaxToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        faxModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
                     //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/fax")
    public String getFax(Model model, @RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Fax>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Fax>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<FaxDto> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = faxService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != inventaryNumber) {
             svtObjectsByEmployee = faxService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
        }else if(null != serialNumber) {
             svtObjectsByEmployee = faxService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = faxService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        treeSvtDtoByEmployee = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = faxService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = faxService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = faxService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<FaxModel> optModel = faxModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "fax");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Факсы");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/fax")
    public ResponseEntity<String> saveFax(@RequestBody FaxDto dto) throws ObjectAlreadyExists {
        faxService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 //    @UpdLog
     @PutMapping("/updfax")
    public ResponseEntity<String> updateFax (@RequestBody FaxDto dto) throws ObjectAlreadyExists {
        faxService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @DeleteMapping("/faxtostor")
    public ResponseEntity<String> sendToStorageFax(@RequestBody FaxDto dto) throws ObjectAlreadyExists {
            Fax fax = faxService.getById(dto.getId());
        faxService.sendToStorage(fax);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @SendArchive
    @DeleteMapping("/faxarchived")
    public ResponseEntity<String> sendFaxToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        faxService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
     //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/minfomat")
    public String getModelInfomat(Model model) {
        List<InfomatModel> infomatModels = infomatModelService.getAllActualModels();
        List<SvtModelDto> getInfomatModelsDtoes = infomatModelMapper.getListDtoes(infomatModels);
        model.addAttribute("dtoes", getInfomatModelsDtoes);
        model.addAttribute("namePage", "Модели инфоматов");
        model.addAttribute("attribute", "minfomat");
        model.addAttribute("manufacturersSaveLink", "/save-infomat-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-infomat-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-infomat-manufacturers");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/minfomat")
    public ResponseEntity<String> saveModelInfomat(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        InfomatModel infomatModel = infomatModelMapper.getModel(dto);
        try{
            infomatModelService.saveModel(infomatModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/minfomatupd")
    public ResponseEntity<String> updateModelInfomat(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        InfomatModel infomatModel = infomatModelMapper.getModel(dto);
        try{
            infomatModelService.update(infomatModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
//    @SendArchive
    @DeleteMapping("/minfomatarchived")
    public ResponseEntity<String> sendModelInfomatToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        infomatModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
                  //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/infomat")
    public String getInfomat(Model model, @RequestParam(value="username", required = false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Infomat>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Infomat>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = infomatService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != inventaryNumber) {
        svtObjectsByEmployee = infomatService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
        } else if(null != serialNumber) {
        svtObjectsByEmployee = infomatService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        }else {
            svtObjectsByEmployee = infomatService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
       treeSvtDtoByEmployee = infomatOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = infomatService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = infomatService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if(null != serialNumber) {
            svtObjectsByStorage = infomatService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
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
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<InfomatModel> optModel = infomatModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }
            
        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "infomat");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Инфоматы");
        model.addAttribute("amountDevice", amount);
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/infomat")
    public ResponseEntity<String> saveInfomat(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        infomatService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
//            @UpdLog
          @PutMapping("/updinfomat")
    public ResponseEntity<String> updateInfomat (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        infomatService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @DeleteMapping("/infomattostor")
    public ResponseEntity<String> sendToStorageInfomat(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Infomat infomat = infomatService.getById(dto.getId());
        infomatService.sendToStorage(infomat);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @SendArchive
    @DeleteMapping("/infomatarchived")
    public ResponseEntity<String> sendInfomatToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        infomatService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    

         //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal")
    public String getModelTerminal(Model model) {
        List<TerminalModel> terminalModels = terminalModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalModelMapper.getListDtoes(terminalModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели терминалов");
        model.addAttribute("attribute", "mterminal");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-manufacturers");
        return "models";
    }
    
          //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-printer")
    public String getModelTerminalPrinter(Model model) {
        List<TerminalPrinterModel> terminalModelsPrinter = terminalPrinterModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalPrinterModelMapper.getListDtoes(terminalModelsPrinter);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели принтеров терминала");
        model.addAttribute("attribute", "mterminalPrinter");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-printer-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-printer-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-printer-manufacturers");
        return "models";
    }
    
    
             //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-display")
    public String getModelTerminalDisplay(Model model) {
        List<TerminalDisplayModel> terminalDisplayModels = terminalDisplayModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalDisplayModelMapper.getListDtoes(terminalDisplayModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели экранов терминала");
        model.addAttribute("attribute", "mterminalDisplay");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-display-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-display-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-display-manufacturers");
        return "models";
    }
    
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-ups")
    public String getModelTerminalUps(Model model) {
        List<TerminalUpsModel> terminalUpsModels = terminalUpsModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalUpsModelMapper.getListDtoes(terminalUpsModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели ИБП терминала");
        model.addAttribute("attribute", "mterminalUps");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-ups-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-ups-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-ups-manufacturers");
        return "models";
    }
    
                  //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-server")
    public String getModelTerminalServer(Model model) {
        List<TerminalServerModel> terminalServerModels = terminalServerModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalServerModelMapper.getListDtoes(terminalServerModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели серверов терминала");
        model.addAttribute("attribute", "mterminalServer");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-server-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-server-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-server-manufacturers");
        return "models";
    }
    
                  //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-sensor")
    public String getModelTerminalSensor(Model model) {
        List<TerminalSensorModel> terminalSensorModels = terminalSensorModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalSensorModelMapper.getListDtoes(terminalSensorModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели сенсоров терминала");
        model.addAttribute("attribute", "mterminalSensor");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-sensor-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-sensor-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-sensor-manufacturers");
        return "models";
    }
    
    
    @GetMapping("/po")
    public String getProgramSoftwares(Model model) {
        List<ProgramSoftware> programSoftwares = terminalProgramSoftwareService.getProgramSoftwares();
        List<ProgramSoftwareDto> programSoftwareDtoesList = terminalProgramSoftwareService.getProgramSoftwareDtoesList(programSoftwares);

        model.addAttribute("dtoes", programSoftwareDtoesList);
        model.addAttribute("namePage", "Програмное обеспечение терминала");
        model.addAttribute("attribute", "programSoftware");
        return "models";
    }
    
    
        @PostMapping("/po")
    public ResponseEntity<String> saveProgramSoftware(@RequestBody ProgramSoftwareDto dto) throws ObjectAlreadyExists {
        try{
            terminalProgramSoftwareService.createProgramSoftware(dto);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/po")
    public ResponseEntity<String> updateProgramSoftware(@RequestBody ProgramSoftwareDto dto) throws ObjectAlreadyExists {
        try{
            terminalProgramSoftwareService.updateProgramSoftware(dto);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @DeleteMapping("/po")
    public ResponseEntity<String> deleteProgramSoftware(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        try{
            terminalProgramSoftwareService.deleteProgramSoftware(dto);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mterminal")
    public ResponseEntity<String> saveModelTerminal(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalModel terminalModel = terminalModelMapper.getModel(dto);
        try{
            terminalModelService.saveModel(terminalModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mterminal-printer")
    public ResponseEntity<String> saveModelTerminalPrinter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalPrinterModel terminalPrinterModel = terminalPrinterModelMapper.getModel(dto);
        try{
            terminalPrinterModelService.saveModel(terminalPrinterModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
     @PutMapping("/mterminal-printer")
    public ResponseEntity<String> updateModelTerminalPrinter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalPrinterModel terminalPrinterModel = terminalPrinterModelMapper.getModel(dto);
        try{
            terminalPrinterModelService.update(terminalPrinterModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mterminal-display")
    public ResponseEntity<String> saveModelTerminalDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalDisplayModel terminalDisplayModel = terminalDisplayModelMapper.getModel(dto);
        try{
            terminalDisplayModelService.saveModel(terminalDisplayModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/mterminal-display")
    public ResponseEntity<String> updateModelTerminalDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalDisplayModel terminalDisplayModel = terminalDisplayModelMapper.getModel(dto);
        try{
            terminalDisplayModelService.update(terminalDisplayModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/mterminal")
    public ResponseEntity<String> updateModelTerminal(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalModel terminalModel = terminalModelMapper.getModel(dto);
        try{
            terminalModelService.update(terminalModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
     //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mterminal-ups")
    public ResponseEntity<String> saveModelTerminalUps(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalUpsModel terminalUpsModel = terminalUpsModelMapper.getModel(dto);
        try{
            terminalUpsModelService.saveModel(terminalUpsModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/mterminal-ups")
    public ResponseEntity<String> updateModelTerminalUps(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalUpsModel terminalUpsModel = terminalUpsModelMapper.getModel(dto);
        try{
            terminalUpsModelService.update(terminalUpsModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    
       //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mterminal-server")
    public ResponseEntity<String> saveModelTerminalServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalServerModel terminalServerModel = terminalServerModelMapper.getModel(dto);
        try{
            terminalServerModelService.saveModel(terminalServerModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
     @PutMapping("/mterminal-server")
    public ResponseEntity<String> updateModelTerminalServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalServerModel terminalServerModel = terminalServerModelMapper.getModel(dto);
        try{
            terminalServerModelService.update(terminalServerModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
           //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mterminal-sensor")
    public ResponseEntity<String> saveModelTerminalSensor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalSensorModel terminalSensorModel = terminalSensorModelMapper.getModel(dto);
        try{
            terminalSensorModelService.saveModel(terminalSensorModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
               //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PutMapping("/mterminal-sensor")
    public ResponseEntity<String> updateModelTerminalSensor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalSensorModel terminalSensorModel = terminalSensorModelMapper.getModel(dto);
        try{
            terminalSensorModelService.update(terminalSensorModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //   @SendArchive
    @DeleteMapping("/mterminalarchived")
    public ResponseEntity<String> sendModelTerminalToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
     //   @SendArchive
    @DeleteMapping("/mterminal-display-archived")
    public ResponseEntity<String> sendModelTerminalDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalDisplayModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
        //   @SendArchive
    @DeleteMapping("/mterminal-printer-archived")
    public ResponseEntity<String> sendModelTerminalPrinterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalPrinterModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
        //   @SendArchive
    @DeleteMapping("/mterminal-ups-archived")
    public ResponseEntity<String> sendModelTerminalUpsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalUpsModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
        //   @SendArchive
    @DeleteMapping("/mterminal-server-archived")
    public ResponseEntity<String> sendModelTerminalServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalServerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
     //   @SendArchive
    @DeleteMapping("/mterminal-sensor-archived")
    public ResponseEntity<String> sendModelTerminalSensorToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalSensorModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
   
    
    
    
     //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/terminal")
    public String getTerminal(Model model,
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber
                                                                                            ) {
        Map<Location, List<Terminal>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = terminalService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != inventaryNumber) {
        svtObjectsByEmployee = terminalService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
        } else if(null != serialNumber) {
        svtObjectsByEmployee = terminalService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
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
        }else if(null != inventaryNumber) {
        svtObjectsByStorage = terminalService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if(null != serialNumber) {
        svtObjectsByStorage = terminalService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminal");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Терминалы");
        
        return "svtobj";
    }
    
    @GetMapping("/terminal-display")
    public String getTerminalDisplay(Model model, 
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="serialNumber", required = false) String serialNumber ) {
        Map<Location, List<TerminalDisplay>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = terminalDisplayService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != serialNumber) {
        svtObjectsByEmployee = terminalDisplayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalDisplayService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalDisplayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalDisplay>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalDisplayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalDisplay");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Экран для терминала");
        
        return "svtobj";
    }
    
    
    @GetMapping("/terminal-server")
    public String getTerminalServer(Model model,
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="serialNumber", required = false) String serialNumber ) {
        Map<Location, List<TerminalServer>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = terminalServerService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != serialNumber) {
        svtObjectsByEmployee = terminalServerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalServerService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalServerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalServer>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = terminalServerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != serialNumber) {
        svtObjectsByStorage = terminalServerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        }  else {
            svtObjectsByStorage = terminalServerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalServerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalServer");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Сервер для терминала");
        
        return "svtobj";
    }
    
    
    
     @GetMapping("/terminal-ups")
    public String getTerminalUps(Model model, 
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="serialNumber", required = false) String serialNumber) {
        Map<Location, List<TerminalUps>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = terminalUpsService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != serialNumber) {
        svtObjectsByEmployee = terminalUpsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalUpsService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalUpsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalUps>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = terminalUpsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if(null != serialNumber) {
        svtObjectsByStorage = terminalUpsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
            svtObjectsByStorage = terminalUpsService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalUpsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalUps");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","ИБП для терминала");
        
        return "svtobj";
    }
    
    
      @GetMapping("/terminal-printer")
    public String getTerminalPrinter(Model model, 
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="serialNumber", required = false) String serialNumber) {
        Map<Location, List<TerminalPrinter>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = terminalPrinterService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != serialNumber) {
        svtObjectsByEmployee = terminalPrinterService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalPrinterService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalPrinterOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalPrinter>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = terminalPrinterService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != serialNumber) {
        svtObjectsByStorage = terminalPrinterService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalPrinterService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalPrinterOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalPrinter");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Принтер для терминала");
        
        return "svtobj";
    }
    
    
    @GetMapping("/terminal-sensor")
    public String getTerminalSensor(Model model,
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="serialNumber", required = false) String serialNumber) {
        Map<Location, List<TerminalSensor>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = terminalSensorService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != serialNumber) {
        svtObjectsByEmployee = terminalSensorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalSensorService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalSensorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalSensor>> svtObjectsByStorage = null;
        
        if(null != username) {
            svtObjectsByStorage = terminalSensorService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != serialNumber) {
        svtObjectsByStorage = terminalSensorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalSensorService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalSensorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalSensor");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Сенсор для терминала");
        
        return "svtobj";
    }
    
    
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal")
    public ResponseEntity<String> saveTerminal(@RequestBody TerminalDto dto) throws ObjectAlreadyExists {
        terminalService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
  //        @UpdLog
          @PutMapping("/terminal")
    public ResponseEntity<String> updateTerminal (@RequestBody TerminalDto dto) throws ObjectAlreadyExists {
        try{
            terminalService.updateSvtObj(dto);
        }catch(Exception e) {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-printer")
    public ResponseEntity<String> saveTerminalPrinter(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        terminalPrinterService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
         //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-server")
    public ResponseEntity<String> saveTerminalServer(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        terminalServerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    
         //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-sensor")
    public ResponseEntity<String> saveTerminalSensor(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        terminalSensorService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    

    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-display")
    public ResponseEntity<String> saveTerminalDisplay(@RequestBody TerminalDisplayDto dto) throws ObjectAlreadyExists {
        terminalDisplayService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

             //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-ups")
    public ResponseEntity<String> saveTerminalUps(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        terminalUpsService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
      //        @UpdLog
          @PutMapping("/terminal-printer")
    public ResponseEntity<String> updateTerminalPrinter (@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        try{
            terminalPrinterService.updateSvtObj(dto);
        }catch(Exception e) {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
          //        @UpdLog
    @PutMapping("/terminal-server")
    public ResponseEntity<String> updateTerminalServer (@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        try{
            terminalServerService.updateSvtObj(dto);
        }catch(Exception e) {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
          //        @UpdLog
    @PutMapping("/terminal-sensor")
    public ResponseEntity<String> updateTerminalSensor (@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        try{
            terminalSensorService.updateSvtObj(dto);
        }catch(Exception e) {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
     //        @UpdLog
          @PutMapping("/terminal-display")
    public ResponseEntity<String> updateTerminalDisplay (@RequestBody TerminalDisplayDto dto) throws ObjectAlreadyExists {
        try{
            terminalDisplayService.updateSvtObj(dto);
        }catch(Exception e) {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
      //        @UpdLog
          @PutMapping("/terminal-ups")
    public ResponseEntity<String> updateTerminalUps (@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        try{
            terminalUpsService.updateSvtObj(dto);
        }catch(Exception e) {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/terminaltostor")
    public ResponseEntity<String> sendToStorageTerminal(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Terminal terminal = terminalService.getById(dto.getId());
        terminalService.sendToStorage(terminal);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
                  //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/terminal-printer-tostor")
    public ResponseEntity<String> sendToStorageTerminalPrinter(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalPrinter terminalPrinter = terminalPrinterService.getById(dto.getId());
        terminalPrinterService.sendToStorage(terminalPrinter);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
                  //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/terminal-display-tostor")
    public ResponseEntity<String> sendToStorageTerminalDisplay(@RequestBody TerminalDisplayDto dto) throws ObjectAlreadyExists {
        TerminalDisplay terminalDisplay = terminalDisplayService.getById(dto.getId());
        terminalDisplayService.sendToStorage(terminalDisplay);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
                      //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/terminal-ups-tostor")
    public ResponseEntity<String> sendToStorageTerminalUps(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalUps terminalUps = terminalUpsService.getById(dto.getId());
        terminalUpsService.sendToStorage(terminalUps);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
                          //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/terminal-server-tostor")
    public ResponseEntity<String> sendToStorageTerminalServer(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalServer terminalServer = terminalServerService.getById(dto.getId());
        terminalServerService.sendToStorage(terminalServer);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
                              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/terminal-sensor-tostor")
    public ResponseEntity<String> sendToStorageTerminalSensor(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalSensor terminalSensor = terminalSensorService.getById(dto.getId());
        terminalSensorService.sendToStorage(terminalSensor);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @SendArchive
    @DeleteMapping("/terminalarchived")
    public ResponseEntity<String> sendTerminalToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    //   @SendArchive
    @DeleteMapping("/terminal-ups-archived")
    public ResponseEntity<String> sendTerminalUpsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalUpsService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    //   @SendArchive
    @DeleteMapping("/terminal-printer-archived")
    public ResponseEntity<String> sendTerminalPrinterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalPrinterService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
       //   @SendArchive
    @DeleteMapping("/terminal-server-archived")
    public ResponseEntity<String> sendTerminalServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalServerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
        //   @SendArchive
    @DeleteMapping("/terminal-sensor-archived")
    public ResponseEntity<String> sendTerminalSensorToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalSensorService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    //   @SendArchive
    @DeleteMapping("/terminal-display-archived")
    public ResponseEntity<String> sendTerminalDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalDisplayService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mdisplay")
    public String getModelDisplay(Model model) {
        List<DisplayModel> displayModels = displayModelService.getAllActualModels();
        List<SvtModelDto> getDisplayModelsDtoes = displayModelMapper.getListDtoes(displayModels);
        model.addAttribute("dtoes", getDisplayModelsDtoes);
        model.addAttribute("namePage", "Модели главного табло");
        model.addAttribute("attribute", "mdisplay");
        model.addAttribute("manufacturersSaveLink", "/save-display-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-display-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-display-manufacturers");
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mdisplay")
    public ResponseEntity<String> saveModelDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        DisplayModel displayModel = displayModelMapper.getModel(dto);
        try{
            displayModelService.saveModel(displayModel);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
        @PutMapping("/mdisplay")
    public ResponseEntity<String> updateModelDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        DisplayModel displayModel = displayModelMapper.getModel(dto);
        try{
            displayModelService.update(displayModel);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
 //    @SendArchive
    @DeleteMapping("/mdisplayarchived")
    public ResponseEntity<String> sendModelDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        displayModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
                         //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/display")
    public String getDisplay(Model model, 
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber) {
        Map<Location, List<Display>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = displayService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != inventaryNumber) {
        svtObjectsByEmployee = displayService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
        } else if(null != serialNumber) {
        svtObjectsByEmployee = displayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
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
        }else if(null != inventaryNumber) {
        svtObjectsByStorage = displayService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if(null != serialNumber) {
        svtObjectsByStorage = displayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = displayService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = displayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "display");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage","Главное табло");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/display")
    public ResponseEntity<String> saveDisplay(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        displayService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 //         @UpdLog
          @PutMapping("/upddisplay")
    public ResponseEntity<String> updateDisplay (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        displayService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @DeleteMapping("/displaytostor")
    public ResponseEntity<String> sendToStorageDisplay(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
            Display display = displayService.getById(dto.getId());
        displayService.sendToStorage(display);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//   @SendArchive
    @DeleteMapping("/displayarchived")
    public ResponseEntity<String> sendDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        displayService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
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
 //   @Log
    @PostMapping("/msubdisplay")
    public ResponseEntity<String> saveModelSubDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SubDisplayModel subDisplayModel = svtModelMapper.getModelSubDisplay(dto);
        try{
            subDisplayModelService.saveModel(subDisplayModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
      @PutMapping("/msubdisplay")
    public ResponseEntity<String> updateModelSubDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SubDisplayModel subDisplayModel = svtModelMapper.getModelSubDisplay(dto);
        try{
            subDisplayModelService.update(subDisplayModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
//    @SendArchive
    @DeleteMapping("/msubdisplayarchived")
    public ResponseEntity<String> sendModelSubDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        subDisplayModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    

            //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/asuo")
    public String getAsuo(Model model, 
            @RequestParam(value="username", required = false) String username,
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber) {
        Map<Location, List<Asuo>> svtObjectsByEmployee = null;
        if(null != username) {
            svtObjectsByEmployee = asuoService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        }else if(null != inventaryNumber) {
        svtObjectsByEmployee = asuoService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
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
        }else if(null != inventaryNumber) {
        svtObjectsByStorage = asuoService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = asuoService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = asuoOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("attribute", "asuo");
        model.addAttribute("namePage","Электронные очереди");
        
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/asuo")
    public ResponseEntity<String> saveAsuo(@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
        asuoService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
 //          @UpdLog
          @PutMapping("/asuo")
    public ResponseEntity<String> updateAsuo (@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
        asuoService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/asuotostor")
    public ResponseEntity<String> sendToStorageAsuo(@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
        Asuo asuo = asuoService.getById(dto.getId());
        asuoService.sendToStorage(asuo);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
          //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//   @SendArchive
    @DeleteMapping("/asuoarchived")
    public ResponseEntity<String> sendAsuoToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        asuoService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    
    public static int getAmountDevices(List<LocationByTreeDto> listNoStorage, List<LocationByTreeDto> listStorage) {
    int result = 0;
        for(LocationByTreeDto loc : listNoStorage) {
            result = result + loc.getAmount();
        }
        for(LocationByTreeDto loc : listStorage) {
            result = result + loc.getAmount();
        }
        return result;
    }
    
    
}

