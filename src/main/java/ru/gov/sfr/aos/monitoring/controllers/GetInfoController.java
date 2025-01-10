/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.opfr.springBootStarterDictionary.fallback.FallbackOrganizationClient;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;
import org.opfr.springBootStarterDictionary.models.DictionaryOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Asuo;
import ru.gov.sfr.aos.monitoring.entities.Ats;
import ru.gov.sfr.aos.monitoring.entities.AtsManufacturer;
import ru.gov.sfr.aos.monitoring.entities.AtsModel;
import ru.gov.sfr.aos.monitoring.entities.BatteryType;
import ru.gov.sfr.aos.monitoring.entities.CdDrive;
import ru.gov.sfr.aos.monitoring.entities.Conditioner;
import ru.gov.sfr.aos.monitoring.entities.ConditionerModel;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Display;
import ru.gov.sfr.aos.monitoring.entities.DisplayModel;
import ru.gov.sfr.aos.monitoring.entities.Fax;
import ru.gov.sfr.aos.monitoring.entities.FaxManufacturer;
import ru.gov.sfr.aos.monitoring.entities.FaxModel;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Infomat;
import ru.gov.sfr.aos.monitoring.entities.InfomatModel;
import ru.gov.sfr.aos.monitoring.entities.Keyboard;
import ru.gov.sfr.aos.monitoring.entities.LanCard;
import ru.gov.sfr.aos.monitoring.entities.Monitor;
import ru.gov.sfr.aos.monitoring.entities.MonitorManufacturer;
import ru.gov.sfr.aos.monitoring.entities.MonitorModel;
import ru.gov.sfr.aos.monitoring.entities.Motherboard;
import ru.gov.sfr.aos.monitoring.entities.Mouse;
import ru.gov.sfr.aos.monitoring.entities.OperationSystem;
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.entities.PhoneManufacturer;
import ru.gov.sfr.aos.monitoring.entities.PhoneModel;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.entities.Router;
import ru.gov.sfr.aos.monitoring.entities.RouterManufacturer;
import ru.gov.sfr.aos.monitoring.entities.RouterModel;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.entities.ScannerManufacturer;
import ru.gov.sfr.aos.monitoring.entities.ScannerModel;
import ru.gov.sfr.aos.monitoring.entities.Server;
import ru.gov.sfr.aos.monitoring.entities.ServerManufacturer;
import ru.gov.sfr.aos.monitoring.entities.ServerModel;
import ru.gov.sfr.aos.monitoring.entities.SoundCard;
import ru.gov.sfr.aos.monitoring.entities.Speakers;
import ru.gov.sfr.aos.monitoring.entities.SubDisplayModel;
import ru.gov.sfr.aos.monitoring.entities.SwitchHub;
import ru.gov.sfr.aos.monitoring.entities.SwitchHubManufacturer;
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
import ru.gov.sfr.aos.monitoring.entities.UpsManufacturer;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.entities.VideoCard;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.mappers.AsuoMapper;
import ru.gov.sfr.aos.monitoring.mappers.AtsManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.AtsMapper;
import ru.gov.sfr.aos.monitoring.mappers.AtsModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.BatteryTypeMapper;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerMapper;
import ru.gov.sfr.aos.monitoring.mappers.DisplayMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.OperationSystemMapper;
import ru.gov.sfr.aos.monitoring.mappers.PhoneManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.models.DepartmentDTO;
import ru.gov.sfr.aos.monitoring.models.EmployeeDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.models.SvtModelDto;
import ru.gov.sfr.aos.monitoring.models.PlaceDTO;
import ru.gov.sfr.aos.monitoring.models.PlaceStatusDto;
import ru.gov.sfr.aos.monitoring.services.DepartmentService;
import ru.gov.sfr.aos.monitoring.services.DictionaryEmployeeHolder;
import ru.gov.sfr.aos.monitoring.services.LocationService;
import ru.gov.sfr.aos.monitoring.services.MonitorModelService;
import ru.gov.sfr.aos.monitoring.services.PhoneModelService;
import ru.gov.sfr.aos.monitoring.services.PhoneService;
import ru.gov.sfr.aos.monitoring.services.PlaceService;
import ru.gov.sfr.aos.monitoring.mappers.PhoneMapper;
import ru.gov.sfr.aos.monitoring.mappers.PhoneModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.RouterManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.RouterMapper;
import ru.gov.sfr.aos.monitoring.mappers.RouterModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.ScannerManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ScannerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ScannerModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.ServerManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ServerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ServerModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchHubManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchHubMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchHubModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchingUnitMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemBlockMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalMapper;
import ru.gov.sfr.aos.monitoring.mappers.ThermoprinterMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsModelMapper;
import ru.gov.sfr.aos.monitoring.models.AsuoDTO;
import ru.gov.sfr.aos.monitoring.models.BatteryTypeDto;
import ru.gov.sfr.aos.monitoring.models.CpuModelDto;
import ru.gov.sfr.aos.monitoring.models.DepDto;
import ru.gov.sfr.aos.monitoring.models.FaxDto;
import ru.gov.sfr.aos.monitoring.models.HddDto;
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.models.PhoneManufacturerDto;
import ru.gov.sfr.aos.monitoring.models.RamDto;
import ru.gov.sfr.aos.monitoring.models.RepairDto;
import ru.gov.sfr.aos.monitoring.models.SvtAtsDTO;
import ru.gov.sfr.aos.monitoring.models.SvtConditionerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtServerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSystemBlockDTO;
import ru.gov.sfr.aos.monitoring.models.TransferDto;
import ru.gov.sfr.aos.monitoring.models.UpsManufacturerDto;
import ru.gov.sfr.aos.monitoring.models.UpsModelDto;
import ru.gov.sfr.aos.monitoring.repositories.AsuoRepo;
import ru.gov.sfr.aos.monitoring.repositories.BatteryTypeRepo;
import ru.gov.sfr.aos.monitoring.services.AsuoService;
import ru.gov.sfr.aos.monitoring.services.AtsManufacturerService;
import ru.gov.sfr.aos.monitoring.services.AtsModelService;
import ru.gov.sfr.aos.monitoring.services.AtsService;
import ru.gov.sfr.aos.monitoring.services.BatteryTypeService;
import ru.gov.sfr.aos.monitoring.services.CdDriveModelService;
import ru.gov.sfr.aos.monitoring.services.ClientDAO;
import ru.gov.sfr.aos.monitoring.services.ConditionerModelService;
import ru.gov.sfr.aos.monitoring.services.ConditionerService;
import ru.gov.sfr.aos.monitoring.services.CpuModelService;
import ru.gov.sfr.aos.monitoring.services.DisplayModelService;
import ru.gov.sfr.aos.monitoring.services.DisplayService;
import ru.gov.sfr.aos.monitoring.services.FaxManufacturerService;
import ru.gov.sfr.aos.monitoring.services.FaxModelService;
import ru.gov.sfr.aos.monitoring.services.FaxService;
import ru.gov.sfr.aos.monitoring.services.HddModelService;
import ru.gov.sfr.aos.monitoring.services.InfomatModelService;
import ru.gov.sfr.aos.monitoring.services.InfomatService;
import ru.gov.sfr.aos.monitoring.services.KeyboardModelService;
import ru.gov.sfr.aos.monitoring.services.LanCardModelService;
import ru.gov.sfr.aos.monitoring.services.MonitorManufacturerService;
import ru.gov.sfr.aos.monitoring.services.MonitorService;
import ru.gov.sfr.aos.monitoring.services.MotherboardModelService;
import ru.gov.sfr.aos.monitoring.services.MouseModelService;
import ru.gov.sfr.aos.monitoring.services.OperationSystemService;
import ru.gov.sfr.aos.monitoring.services.PhoneManufacturerService;
import ru.gov.sfr.aos.monitoring.services.RamModelService;
import ru.gov.sfr.aos.monitoring.services.RepairInfoService;
import ru.gov.sfr.aos.monitoring.services.RouterManufacturerService;
import ru.gov.sfr.aos.monitoring.services.RouterModelService;
import ru.gov.sfr.aos.monitoring.services.RouterService;
import ru.gov.sfr.aos.monitoring.services.ScannerManufacturerService;
import ru.gov.sfr.aos.monitoring.services.ScannerModelService;
import ru.gov.sfr.aos.monitoring.services.ScannerService;
import ru.gov.sfr.aos.monitoring.services.ServerManufacturerService;
import ru.gov.sfr.aos.monitoring.services.ServerModelService;
import ru.gov.sfr.aos.monitoring.services.ServerService;
import ru.gov.sfr.aos.monitoring.services.SoundCardModelService;
import ru.gov.sfr.aos.monitoring.services.SpeakersModelService;
import ru.gov.sfr.aos.monitoring.services.SubDisplayModelService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubManufacturerService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubModelService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubService;
import ru.gov.sfr.aos.monitoring.services.SwitchingUnitModelService;
import ru.gov.sfr.aos.monitoring.services.SwitchingUnitService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockModelService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockService;
import ru.gov.sfr.aos.monitoring.services.TerminalModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalService;
import ru.gov.sfr.aos.monitoring.services.ThermoprinterModelService;
import ru.gov.sfr.aos.monitoring.services.ThermoprinterService;
import ru.gov.sfr.aos.monitoring.services.TransferInfoService;
import ru.gov.sfr.aos.monitoring.services.UpsManufacturerService;
import ru.gov.sfr.aos.monitoring.services.UpsModelService;
import ru.gov.sfr.aos.monitoring.services.UpsService;
import ru.gov.sfr.aos.monitoring.services.VideoCardModelService;

/**
 *
 * @author 041AlikinOS
 */
@RestController
public class GetInfoController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private DictionaryEmployeeHolder dictionaryEmployeeHolder;

    @Autowired
    private FallbackOrganizationClient organizationClient;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private PhoneModelService phoneModelService;
    @Autowired
    private SvtModelMapper svtModelMapper;
    @Autowired
    private MonitorModelService monitorModelService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private MonitorMapper monitorMapper;
    @Autowired
    private UpsMapper upsMapper;
    @Autowired
    private UpsService upsService;
    @Autowired
    private UpsModelService upsModelService;
    @Autowired 
    private BatteryTypeRepo batteryTypeRepo;
    @Autowired
    private BatteryTypeService batteryTypeService;
    @Autowired
    private BatteryTypeMapper batteryTypeMapper;
    @Autowired
    private SystemBlockModelService systemBlockModelService;
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
    private OperationSystemMapper operationSystemMapper;
    @Autowired
    private OperationSystemService operationSystemService;
    @Autowired
    private SystemBlockService systemBlockService;
    @Autowired
    private SystemBlockMapper systemBlockMapper;
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private ScannerMapper scannerMapper;
    @Autowired
    private ScannerModelService scannerModelService;
    @Autowired
    private ServerModelService serverModelService;
    @Autowired
    private ServerMapper serverMapper;
    @Autowired
    private ServerService serverService;
    @Autowired
    private SwitchHubModelService switchHubModelService;
    @Autowired
    private SwitchHubService switchHubService;
    @Autowired
    private SwitchHubMapper switchHubMapper;
    @Autowired
    private RouterModelService routerModelService;
    @Autowired
    private RouterService routerService;
    @Autowired
    private RouterMapper routerMapper;
    @Autowired
    private AtsModelService atsModelService;
    @Autowired
    private AtsService atsService;
    @Autowired
    private AtsMapper atsMapper;
    @Autowired
    private ConditionerModelService conditionerModelService;
    @Autowired
    private ConditionerService conditionerService;
    @Autowired
    private ConditionerMapper conditionerMapper;
    @Autowired
    private InfomatModelService infomatModelService;
    @Autowired
    private InfomatMapper infomatMapper;
    @Autowired
    private InfomatService infomatService;
    @Autowired
    private TerminalModelService terminalModelService;
    @Autowired
    private TerminalMapper terminalMapper;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private ThermoprinterModelService thermoprinterModelService;
    @Autowired
    private ThermoprinterMapper thermoprinterMapper;
    @Autowired
    private ThermoprinterService thermoprinterService;
    @Autowired
    private DisplayMapper displayMapper;
    @Autowired
    private DisplayService displayService;
    @Autowired
    private DisplayModelService displayModelService;
    @Autowired
    private SwitchingUnitMapper swunitMapper;
    @Autowired
    private SwitchingUnitService swunitService;
    @Autowired
    private SwitchingUnitModelService swunitModelService;
    @Autowired
    private AsuoRepo asuoRepo;
    @Autowired
    private SubDisplayModelService subDisplayModelService;
    @Autowired
    private AsuoService asuoService;
    @Autowired
    private AsuoMapper asuoMapper;
    @Autowired
    private FaxModelService faxModelService;
    @Autowired
    private FaxMapper faxMapper;
    @Autowired
    private FaxService faxService;
    @Autowired
    private RepairInfoService repairInfoService;
    @Autowired
    private ClientDAO clientDao;
    @Autowired
    private TransferInfoService transferInfoService;
    @Autowired
    private UpsManufacturerService upsManufacturerService;
    @Autowired
    private UpsManufacturerMapper upsManufacturerMapper;
    @Autowired
    private UpsModelMapper upsModelMapper;
    @Autowired
    private PhoneManufacturerService phoneManufacturerService;
    @Autowired
    private PhoneManufacturerMapper phoneManufacturerMapper;
    @Autowired 
    private PhoneModelMapper phoneModelMapper;
    @Autowired
    private FaxManufacturerService faxManufacturerService;
    @Autowired
    private FaxManufacturerMapper faxManufacturerMapper;
    @Autowired
    private FaxModelMapper faxModelMapper;
    @Autowired
    private MonitorModelMapper monitorModelMapper;
    @Autowired
    private MonitorManufacturerService monitorManufacturerService;
    @Autowired
    private MonitorManufacturerMapper monitorManufacturerMapper;
    @Autowired
    private ScannerManufacturerService scannerManufacturerService;
    @Autowired
    private ScannerManufacturerMapper scannerManufacturerMapper;
    @Autowired
    private ScannerModelMapper scannerModelMapper;
    @Autowired
    private ServerManufacturerService serverManufacturerService;
    @Autowired
    private ServerManufacturerMapper serverManufacturerMapper;
    @Autowired
    private ServerModelMapper serverModelMapper;
    @Autowired
    private SwitchHubManufacturerService switchHubManufacturerService;
    @Autowired
    private SwitchHubManufacturerMapper switchHubManufacturerMapper;
    @Autowired
    private SwitchHubModelMapper switchHubModelMapper;
    @Autowired
    private RouterManufacturerService routerManufacturerService;
    @Autowired
    private RouterManufacturerMapper routerManufacturerMapper;
    @Autowired
    private RouterModelMapper routerModelMapper;
    @Autowired
    private AtsManufacturerService atsManufacturerService;
    @Autowired
    private AtsManufacturerMapper atsManufacturerMapper;
    @Autowired
    private AtsModelMapper atsModelMapper;
    
    @GetMapping("/batterytype")
    public List<BatteryTypeDto> getBatteryTypes(@RequestParam(value="id", required = false) Long id) {
        List<BatteryType> list = new ArrayList<>();
        if(null != id) {
            BatteryType batteryType = batteryTypeService.getBatteryType(id);
            list.add(batteryType);
        } else {
            list = batteryTypeService.getAllActualBatteryTypes();
        }
        
        List<BatteryTypeDto> out = batteryTypeMapper.getBatteryTypeDtoesList(list);
        return out;
    }
    
    @PostMapping("/save-ups-manufacturer")
    public ResponseEntity<?> saveUpsManufacturer(String name) throws ObjectAlreadyExists {
        UpsManufacturer savedManufacturer = null;
        UpsManufacturer potencialManufacturer = new UpsManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = upsManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        UpsManufacturerDto dto = upsManufacturerMapper.getDto(savedManufacturer);
        
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/save-fax-manufacturer")
    public ResponseEntity<?> saveFaxManufacturer(String name) throws ObjectAlreadyExists {
        FaxManufacturer savedManufacturer = null;
        FaxManufacturer potencialManufacturer = new FaxManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = faxManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        ManufacturerDTO dto = faxManufacturerMapper.getDto(savedManufacturer);
        
        return ResponseEntity.ok(dto);
    }
    
     @PostMapping("/save-monitor-manufacturer")
    public ResponseEntity<?> saveMonitorManufacturer(String name) throws ObjectAlreadyExists {
        MonitorManufacturer savedManufacturer = null;
        MonitorManufacturer potencialManufacturer = new MonitorManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = monitorManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = monitorManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    
    @PostMapping("/save-server-manufacturer")
    public ResponseEntity<?> saveServerManufacturer(String name) throws ObjectAlreadyExists {
        ServerManufacturer savedManufacturer = null;
        ServerManufacturer potencialManufacturer = new ServerManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = serverManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = serverManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/save-switch-manufacturer")
    public ResponseEntity<?> saveSwitchHubManufacturer(String name) throws ObjectAlreadyExists {
        SwitchHubManufacturer savedManufacturer = null;
        SwitchHubManufacturer potencialManufacturer = new SwitchHubManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = switchHubManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = switchHubManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/save-router-manufacturer")
    public ResponseEntity<?> saveRouterHubManufacturer(String name) throws ObjectAlreadyExists {
        RouterManufacturer savedManufacturer = null;
        RouterManufacturer potencialManufacturer = new RouterManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = routerManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = routerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/save-ats-manufacturer")
    public ResponseEntity<?> saveAtsManufacturer(String name) throws ObjectAlreadyExists {
        AtsManufacturer savedManufacturer = null;
        AtsManufacturer potencialManufacturer = new AtsManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = atsManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = atsManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    
    @PostMapping("/save-scanner-manufacturer")
    public ResponseEntity<?> saveScannerManufacturer(String name) throws ObjectAlreadyExists {
        ScannerManufacturer savedManufacturer = null;
        ScannerManufacturer potencialManufacturer = new ScannerManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = scannerManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = scannerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    
        @PostMapping("/save-phone-manufacturer")
    public ResponseEntity<?> savePhoneManufacturer(String name) throws ObjectAlreadyExists {
            PhoneManufacturer savedManufacturer = null;
        PhoneManufacturer potencialManufacturer = new PhoneManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = phoneManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        PhoneManufacturerDto dto = phoneManufacturerMapper.getDto(savedManufacturer);
        
        return ResponseEntity.ok(dto);
    }
    
    @GetMapping("/get-ups-manufacturers")
    public List<UpsManufacturerDto> getUpsManufacturers() {
        List<UpsManufacturer> allManufacturers = upsManufacturerService.getAllManufacturers();
        List<UpsManufacturerDto> out = upsManufacturerMapper.getListDtoes(allManufacturers);
        return out;
    }
    
    @GetMapping("/get-fax-manufacturers")
    public List<ManufacturerDTO> getFaxManufacturers() {
        List<FaxManufacturer> allManufacturers = faxManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(FaxManufacturer el : allManufacturers) {
            ManufacturerDTO dto = faxManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
     @GetMapping("/get-monitor-manufacturers")
    public List<ManufacturerDTO> getMonitorManufacturers() {
        List<MonitorManufacturer> allManufacturers = monitorManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(MonitorManufacturer el : allManufacturers) {
            ManufacturerDTO dto = monitorManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    @GetMapping("/get-server-manufacturers")
    public List<ManufacturerDTO> getServerManufacturers() {
        List<ServerManufacturer> allManufacturers = serverManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(ServerManufacturer el : allManufacturers) {
            ManufacturerDTO dto = serverManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    @GetMapping("/get-switch-manufacturers")
    public List<ManufacturerDTO> getSwitchHubManufacturers() {
        List<SwitchHubManufacturer> allManufacturers = switchHubManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(SwitchHubManufacturer el : allManufacturers) {
            ManufacturerDTO dto = switchHubManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    @GetMapping("/get-router-manufacturers")
    public List<ManufacturerDTO> getRouterManufacturers() {
        List<RouterManufacturer> allManufacturers = routerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(RouterManufacturer el : allManufacturers) {
            ManufacturerDTO dto = routerManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    @GetMapping("/get-ats-manufacturers")
    public List<ManufacturerDTO> getAtsManufacturers() {
        List<AtsManufacturer> allManufacturers = atsManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(AtsManufacturer el : allManufacturers) {
            ManufacturerDTO dto = atsManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    
    @GetMapping("/get-scanner-manufacturers")
    public List<ManufacturerDTO> getScannerManufacturers() {
        List<ScannerManufacturer> allManufacturers = scannerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(ScannerManufacturer el : allManufacturers) {
            ManufacturerDTO dto = scannerManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    @GetMapping("/get-phone-manufacturers")
    public List<PhoneManufacturerDto> getPhoneManufacturers() {
        List<PhoneManufacturer> allManufacturers = phoneManufacturerService.getAllManufacturers();
        List<PhoneManufacturerDto> out = phoneManufacturerMapper.getListDtoes(allManufacturers);
        return out;
    }
    
    @GetMapping("/get-modelsby-manufacturer")
    public List<UpsModelDto> getUpsModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        UpsManufacturer manufacturer = upsManufacturerService.getManufacturer(id);
        Set<UpsModel> upsListModel = manufacturer.getModels();
        List<UpsModelDto> out = new ArrayList<>();
        for(UpsModel model : upsListModel) {
            UpsModelDto modeDto = upsModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    @GetMapping("/get-monitor-modelsby-manufacturer")
    public List<SvtModelDto> getMonitorModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        MonitorManufacturer manufacturer = monitorManufacturerService.getManufacturer(id);
        Set<MonitorModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(MonitorModel model : upsListModel) {
            SvtModelDto modelDto = svtModelMapper.getMonitorModelDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
    @GetMapping("/get-server-modelsby-manufacturer")
    public List<SvtModelDto> getServerModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        ServerManufacturer manufacturer = serverManufacturerService.getManufacturer(id);
        Set<ServerModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(ServerModel model : upsListModel) {
            SvtModelDto modelDto = svtModelMapper.getModelServerDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
    @GetMapping("/get-switch-modelsby-manufacturer")
    public List<SvtModelDto> getSwitchHubModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        SwitchHubManufacturer manufacturer = switchHubManufacturerService.getManufacturer(id);
        Set<SwitchHubModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(SwitchHubModel model : upsListModel) {
            SvtModelDto modelDto = switchHubModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
    
    @GetMapping("/get-router-modelsby-manufacturer")
    public Set<SvtModelDto> getRouterModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        RouterManufacturer manufacturer = routerManufacturerService.getManufacturer(id);
        Set<RouterModel> upsListModel = manufacturer.getModels();
        Set<SvtModelDto> out = new HashSet<>();
        for(RouterModel model : upsListModel) {
            SvtModelDto modelDto = routerModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
    @GetMapping("/get-ats-modelsby-manufacturer")
    public Set<SvtModelDto> getAtsModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        AtsManufacturer manufacturer = atsManufacturerService.getManufacturer(id);
        Set<AtsModel> upsListModel = manufacturer.getModels();
        Set<SvtModelDto> out = new HashSet<>();
        for(AtsModel model : upsListModel) {
            SvtModelDto modelDto = atsModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
    
    @GetMapping("/get-scanner-modelsby-manufacturer")
    public List<SvtModelDto> getScannerModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        ScannerManufacturer manufacturer = scannerManufacturerService.getManufacturer(id);
        Set<ScannerModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(ScannerModel model : upsListModel) {
            SvtModelDto modelDto = svtModelMapper.getModelScannerDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
    
    @GetMapping("/get-phone-modelsby-manufacturer")
    public List<SvtModelDto> getPhoneModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        PhoneManufacturer manufacturer = phoneManufacturerService.getManufacturer(id);
        Set<PhoneModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(PhoneModel model : upsListModel) {
            SvtModelDto modeDto = svtModelMapper.getPhoneModelDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    @GetMapping("/get-fax-modelsby-manufacturer")
    public List<SvtModelDto> getFaxModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        FaxManufacturer manufacturer = faxManufacturerService.getManufacturer(id);
        Set<FaxModel> listModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(FaxModel model : listModel) {
            SvtModelDto modeDto = svtModelMapper.getModelFaxDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    @GetMapping("/getinfooo")
    public List<EmployeeDTO> getEmpl() {
        List<DictionaryEmployee> employees = dictionaryEmployeeHolder.getEmployees();
        List<EmployeeDTO> list = new ArrayList<>();
        for (DictionaryEmployee dEmployee : employees) {
            EmployeeDTO dto = new EmployeeDTO(dEmployee.getCode(), dEmployee.getSurname() + " " + dEmployee.getName() + " " + dEmployee.getMiddlename());
            list.add(dto);
        }
        return list;

    }

    @GetMapping("/departments")
    public List<DepartmentDTO> getDepartments() {
        List<DictionaryOrganization> list = organizationClient.getList();
        List<DepartmentDTO> out = new ArrayList<>();
        for (DictionaryOrganization el : list) {
            DepartmentDTO dto = new DepartmentDTO(el.getFullName(), el.getCode());
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/locations")
    public List<LocationDTO> getLocations(@RequestParam(value="id", required = false) String id) {
        List<LocationDTO> locations = null;
        if(null != id) {
            locations = new ArrayList<>();
            long parseLong = Long.parseLong(id);
            LocationDTO locationById = locationService.getLocationById(parseLong);
            locations.add(locationById);
        } else {
        locations = locationService.getAllLocations();
        }
        return locations;
    }
//
    @GetMapping("/locplacetype")
    public List<LocationDTO> getLocByPlaceType(String placeType) {
        PlaceType currentPlaceType = null;
        switch (placeType) {
            case "EMPLOYEE":
                currentPlaceType = PlaceType.EMPLOYEE;
                break;
            case "SERVERROOM":
                currentPlaceType = PlaceType.SERVERROOM;
                break;
            case "STORAGE":
                currentPlaceType = PlaceType.STORAGE;
                break;
            case "OFFICEEQUIPMENT":
                currentPlaceType = PlaceType.OFFICEEQUIPMENT;
                break;

        }
        List<LocationDTO> dtoes = placeService.getLocationByPlaceType(currentPlaceType);
        return dtoes;

    }
    
    @GetMapping("deplocplacetype")
    public List<DepDto> getDepsByLocAndPlaceType(String placeType, Long idLocation) {
         PlaceType currentPlaceType = null;
        switch (placeType) {
            case "EMPLOYEE":
                currentPlaceType = PlaceType.EMPLOYEE;
                break;
            case "SERVERROOM":
                currentPlaceType = PlaceType.SERVERROOM;
                break;
            case "STORAGE":
                currentPlaceType = PlaceType.STORAGE;
                break;
            case "OFFICEEQUIPMENT":
                currentPlaceType = PlaceType.OFFICEEQUIPMENT;
                break;
        
    }
        List<DepDto> dtoes = placeService.getDepartmentsByPlaceTypeAndLocation(currentPlaceType, idLocation);
        return dtoes;
    }
    
    @GetMapping("/placelocdepplacetype")
    public List<PlaceDTO> getPlacesByPlaceTypeLocationDeps(String placeType, Long idLocation, String departmentCode) {
         PlaceType currentPlaceType = null;
        switch (placeType) {
            case "EMPLOYEE":
                currentPlaceType = PlaceType.EMPLOYEE;
                break;
            case "SERVERROOM":
                currentPlaceType = PlaceType.SERVERROOM;
                break;
            case "STORAGE":
                currentPlaceType = PlaceType.STORAGE;
                break;
            case "OFFICEEQUIPMENT":
                currentPlaceType = PlaceType.OFFICEEQUIPMENT;
                break;
        
    }
        List<PlaceDTO> dtoes = placeService.getPlaceByPlaceTypeAndLocationAndDepartmentCode(currentPlaceType, idLocation, departmentCode);
        return dtoes;
    }
    

    @PostMapping("/AJAXPing")
    public Integer getAllContracts() {
        return 0;
    }

    
    @GetMapping("/placesel")
    public List<PlaceDTO> getPlacesEmployee() {
        List<PlaceDTO> places = placeService.getPlacesByPlaceType(PlaceType.EMPLOYEE);
        return places;
    }
    
    @GetMapping("/placeserver")
    public List<PlaceDTO> getPlacesServer() {
        List<PlaceDTO> places = placeService.getPlacesByPlaceType(PlaceType.SERVERROOM);
        return places;
    }

    @GetMapping("/placebyid")
    public PlaceDTO getPlacesById(Long placeId) {
        PlaceDTO place = placeService.getPlaceById(placeId);
        return place;
    }

    @GetMapping("/modphones")
    public List<SvtModelDto> getModelPhones() {
        List<PhoneModel> allModels = phoneModelService.getAllModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(PhoneModel el : allModels) {
            SvtModelDto dto = phoneModelMapper.getDtoForSelectize(el);
            out.add(dto);
        }
        return out;
    }
    
       @GetMapping("/modmonitors")
    public List<SvtModelDto> getModelMonitors() {
        List<MonitorModel> allModels = monitorModelService.getAllModels();
       List<SvtModelDto> monitorModelsDtoes = new ArrayList<>();
       for(MonitorModel model : allModels) {
          SvtModelDto dtoForSelectize = monitorModelMapper.getDtoForSelectize(model);
          monitorModelsDtoes.add(dtoForSelectize);
       }
        
        return monitorModelsDtoes;
    }
    
    
     @GetMapping("/modups")
    public List<SvtModelDto> getModelUps() {
        List<UpsModel> allModels = upsModelService.getAllModels();
        List<SvtModelDto> upsModelDtoForSelectize = upsModelMapper.getUpsModelDtoForSelectize(allModels);
        return upsModelDtoForSelectize;
    }
    
     @GetMapping("/modsysblock")
    public List<SvtModelDto> getModelSysBlocks() {

        List<SystemBlockModel> allModels = systemBlockModelService.getAllModels();
        List<SvtModelDto> systemBlockModelsDtoes = svtModelMapper.getSystemBlockModelsDtoes(allModels);

        return systemBlockModelsDtoes;
    }
    
    @GetMapping("/modmotherboard")
    public List<SvtModelDto> getModelMotherboard() {

        List<Motherboard> allModels = motherboardModelService.getAllModels();
        List<SvtModelDto> motherboardModelsDtoes = svtModelMapper.getModelMotherboardModelsDtoes(allModels);

        return motherboardModelsDtoes;
    }
    
     @GetMapping("/modcpu")
    public List<CpuModelDto> getModelCpu() {

        List<Cpu> allModels = cpuModelService.getAllModels();
        List<CpuModelDto> cpuDtoes = svtModelMapper.getCpuModelDtoes(allModels);

        return cpuDtoes;
    }
    
    @GetMapping("/modram")
    public List<RamDto> getModelRam() {

        List<Ram> allModels = ramModelService.getAllModels();
        List<RamDto> ramDtoes = svtModelMapper.getRamDtoes(allModels);

        return ramDtoes;
    }
    
    @GetMapping("/modhdd")
    public List<HddDto> getModelHdd() {

        List<Hdd> allModels = hddModelService.getAllModels();
        List<HddDto> hddDtoes = svtModelMapper.getHddDtoes(allModels);

        return hddDtoes;
    }
    
     @GetMapping("/modvideo")
    public List<SvtModelDto> getModelVideoCard() {

        List<VideoCard> allModels = videoCardModelService.getAllModels();
        List<SvtModelDto> videoCardDtoes = svtModelMapper.getVideoCardDtoes(allModels);

        return videoCardDtoes;
    }
    
       @GetMapping("/modcddrive")
    public List<SvtModelDto> getModelCdDrive() {

        List<CdDrive> allModels = cdDriveModelService.getAllModels();
        List<SvtModelDto> cdDriveDtoes = svtModelMapper.getCdDriveDtoes(allModels);

        return cdDriveDtoes;
    }
    
    @GetMapping("/modscard")
    public List<SvtModelDto> getModelSoundCard() {

        List<SoundCard> allModels = soundCardModelService.getAllModels();
        List<SvtModelDto> soundCardDtoes = svtModelMapper.getSoundCardDtoes(allModels);

        return soundCardDtoes;
    }
    
     @GetMapping("/modlcard")
    public List<SvtModelDto> getModelLanCard() {

        List<LanCard> allModels = lanCardModelService.getAllModels();
        List<SvtModelDto> lanCardDtoes = svtModelMapper.getLanCardDtoes(allModels);

        return lanCardDtoes;
    }
    
       @GetMapping("/modkeyboard")
    public List<SvtModelDto> getModelKeyboard() {

        List<Keyboard> allModels = keyboardModelService.getAllModels();
        List<SvtModelDto> keyboardDtoes = svtModelMapper.getKeyboardDtoes(allModels);

        return keyboardDtoes;
    }
    
     @GetMapping("/modmouse")
    public List<SvtModelDto> getModelMouse() {

        List<Mouse> allModels = mouseModelService.getAllModels();
        List<SvtModelDto> mouseDtoes = svtModelMapper.getMouseDtoes(allModels);

        return mouseDtoes;
    }
    
    @GetMapping("/modos")
    public List<OperationSystemDto> getOses() {

        List<OperationSystem> allOperationSystem = operationSystemService.getAllOperationSystem();
        List<OperationSystemDto> operationSystemDtoesList = operationSystemMapper.getOperationSystemDtoesList(allOperationSystem);
        return operationSystemDtoesList;
    }
    
      @GetMapping("/modspeakers")
    public List<SvtModelDto> getModelSpeakers() {

        List<Speakers> allModels = speakersModelService.getAllModels();
        List<SvtModelDto> speakersDtoes = svtModelMapper.getSpeakersDtoes(allModels);

        return speakersDtoes;
    }
    
     @GetMapping("/typebatups")
    public List<BatteryTypeDto> getBatteryTypeUps() {

        List<BatteryType> allBatteryTypes = batteryTypeService.getAllBatteryTypes();
        List<BatteryTypeDto> batteryTypeDtoesList = batteryTypeMapper.getBatteryTypeDtoesList(allBatteryTypes);

        return batteryTypeDtoesList;
    }

    @GetMapping("/placebyloc")
    public List<PlaceDTO> getPlacesByLocation(Long locationId) {

        List<PlaceDTO> dtoes = placeService.getPlacesByLocationId(locationId);

        return dtoes;
    }

    @GetMapping("/placebydep")
    public List<PlaceDTO> getPlacesByDepartment(String departmentCode) {

        List<PlaceDTO> dtoes = placeService.getPlacesByDepartmentCode(departmentCode);

        return dtoes;
    }

    @GetMapping("/depbyloc")
    public Set<DepartmentDTO> getDepartmentsByLocation(Long locationId) {

        Set<DepartmentDTO> dtoes = placeService.getDepartmentsByLocation(locationId);

        return dtoes;
    }

    @GetMapping("/placebydepandloc")
    public List<PlaceDTO> getplacesByLocationAndDepartments(Long locationId, String departmentCode) {
        List<PlaceDTO> dtoes = null;
      
        dtoes = placeService.getPlacesByLocationAndDepartment(locationId, departmentCode);
     
        return dtoes;
    }
    
    @GetMapping("/placeserverbydepandloc")
    public List<PlaceDTO> getplacesServerByLocationAndDepartments(Long locationId, String departmentCode, PlaceType placetype) {

        List<PlaceDTO> dtoes = placeService.getPlacesByLocationAndDepartmentAndPlaceType(locationId, departmentCode, placetype);
        return dtoes;
    }

    @GetMapping("/loc")
    public List<LocationDTO> getCurrentLocations() {

        List<LocationDTO> dtoes = placeService.getLocations();

        return dtoes;
    }

    @GetMapping("/getphone")
    public SvtDTO getPhoneById(Long phoneId) {

        Phone phone = phoneService.getById(phoneId);
        SvtDTO phoneDto = phoneMapper.getDto(phone);

        return phoneDto;
    }
    
    @GetMapping("/getmonitor")
    public SvtDTO getMonitorById(Long monitorId) {

        Monitor monitor = monitorService.getById(monitorId);
        SvtDTO monitorDto = monitorMapper.getDto(monitor);

        return monitorDto;
    }
    
    @GetMapping("/getups")
    public SvtDTO getUpsById(Long upsId) {

        Ups ups = upsService.getById(upsId);
        SvtDTO upsDto = upsMapper.getDto(ups);

        return upsDto;
    }
    
    @GetMapping("/getsystemblock")
    public SvtDTO getSystemblock(Long systemblockId) {

        SystemBlock systemblock = systemBlockService.getById(systemblockId);
        SvtSystemBlockDTO systemblockDto = systemBlockMapper.getDto(systemblock);

        return systemblockDto;
    }
    
    @GetMapping("/getserver")
    public SvtServerDTO getServer(Long serverId) {

        Server server = serverService.getById(serverId);
        SvtServerDTO serverDto = serverMapper.getDto(server);

        return serverDto;
    }

    @GetMapping("/getplacesbystatus")
    public List<PlaceStatusDto> getplacesbystatus() {

        List<PlaceStatusDto> placesByStatus = placeService.getPlacesByStatus();

        return placesByStatus;
    }

    @GetMapping("/depbyplaces")
    public Set<DepartmentDTO> getDepsByPlaces() {

        Set<DepartmentDTO> departmentsByPlaces = departmentService.getDepartmentsByPlaces();
        return departmentsByPlaces;
    }

    @GetMapping("/getstor")
    public PlaceDTO getStorageByPlace(Long locationId) {

        PlaceDTO dto = placeService.getPlaceStorageByLocation(locationId);
        return dto;
    }

    @GetMapping("/getscanner")
    public SvtDTO getScannerById(Long scannerId) {

        Scanner scanner = scannerService.getById(scannerId);
        SvtScannerDTO scannerDto = scannerMapper.getDto(scanner);

        return scannerDto;
    }
    
    
    @GetMapping("/modscanner")
    public List<SvtModelDto> getModelScanner() {
        List<ScannerModel> allModels = scannerModelService.getAllModels();
        List<SvtModelDto> scannerModelsDtoes = new ArrayList<>();
        for(ScannerModel model : allModels) {
            SvtModelDto dtoForSelectize = scannerModelMapper.getDtoForSelectize(model);
            scannerModelsDtoes.add(dtoForSelectize);
        }
        return scannerModelsDtoes;
    }
    
    @GetMapping("/modserver")
    public List<SvtModelDto> getModelServer() {
        List<ServerModel> allModels = serverModelService.getAllModels();
        List<SvtModelDto> serverDtoes = new ArrayList<>();
        for(ServerModel model : allModels) {
            SvtModelDto dto = serverModelMapper.getDtoForSelectize(model);
            serverDtoes.add(dto);
        }
        return serverDtoes;
    } 
    
    @GetMapping("/modswitch")
    public List<SvtModelDto> getModelSwitchHub() {
        List<SwitchHubModel> allModels = switchHubModelService.getAllModels();
        List<SvtModelDto> dtoes = new ArrayList<>();
        for(SwitchHubModel model : allModels) {
            SvtModelDto dto = switchHubModelMapper.getDtoForSelectize(model);
            dtoes.add(dto);
        }
        return dtoes;
    }
    
    @GetMapping("/getswitch")
    public SvtDTO getSwitchById(Long switchId) {

        SwitchHub switchHub = switchHubService.getById(switchId);
        SvtSwitchHubDTO switchHubDto = switchHubMapper.getDto(switchHub);

        return switchHubDto;
    }
    
       @GetMapping("/modrouter")
    public Set<SvtModelDto> getModelRouter() {
        List<RouterModel> allModels = routerModelService.getAllModels();
        Set<SvtModelDto> dtoes = new HashSet<>();
        for(RouterModel model : allModels) {
            SvtModelDto dtoForSelectize = routerModelMapper.getDtoForSelectize(model);
            dtoes.add(dtoForSelectize);
        }
        return dtoes;
    }
    
    @GetMapping("/getrouter")
    public SvtDTO getRouterById(Long routerId) {

        Router router = routerService.getById(routerId);
        SvtSwitchHubDTO routerDto = routerMapper.getDto(router);

        return routerDto;
    }
    
    @GetMapping("/modats")
    public Set<SvtModelDto> getModelAts() {
        List<AtsModel> allModels = atsModelService.getAllModels();
        Set<SvtModelDto> dtoes = new HashSet<>();
        for(AtsModel model : allModels) {
            SvtModelDto dto = atsModelMapper.getDto(model);
            dtoes.add(dto);
        }
        
        return dtoes;
    }
    
    
    @GetMapping("/getats")
    public SvtDTO getAtsById(Long atsId) {
        Ats ats = atsService.getById(atsId);
        SvtAtsDTO atsDto = atsMapper.getDto(ats);

        return atsDto;
    }
    
       @GetMapping("/modconditioner")
    public List<SvtModelDto> getModelConditioner() {
        List<ConditionerModel> allModels = conditionerModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelConditionerDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getconditioner")
    public SvtDTO getConditionerById(Long conditionerId) {
        Conditioner conditioner = conditionerService.getById(conditionerId);
        SvtConditionerDTO conditionerDto = conditionerMapper.getDto(conditioner);
        return conditionerDto;
    }
    
         @GetMapping("/modinfomat")
    public List<SvtModelDto> getModelInfomat() {
        List<InfomatModel> allModels = infomatModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelInfomatDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getinfomat")
    public SvtDTO getInfomatById(Long infomatId) {
        Infomat infomat = infomatService.getById(infomatId);
        SvtDTO dto = infomatMapper.getDto(infomat);
        return dto;
    }
    
    @GetMapping("/modterminal")
    public List<SvtModelDto> getModelTermial() {
        List<TerminalModel> allModels = terminalModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelTerminalDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getterminal")
    public SvtDTO getTerminalById(Long terminalId) {
        Terminal terminal = terminalService.getById(terminalId);
        SvtDTO dto = terminalMapper.getDto(terminal);
        return dto;
    }
    
        @GetMapping("/modthermoprinter")
    public List<SvtModelDto> getModelThermoprinter() {
        List<ThermoPrinterModel> allModels = thermoprinterModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelThermoprinterDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getthermoprinter")
    public SvtDTO getThermoprinterById(Long thermoprinterId) {
        ThermoPrinter thermoprinter = thermoprinterService.getById(thermoprinterId);
        SvtDTO dto = thermoprinterMapper.getDto(thermoprinter);
        return dto;
    }
    
         @GetMapping("/moddisplay")
    public List<SvtModelDto> getModelDisplay() {
        List<DisplayModel> allModels = displayModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelDisplayDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getdisplay")
    public SvtDTO getDisplayById(Long displayId) {
        Display display = displayService.getById(displayId);
        SvtDTO dto = displayMapper.getDto(display);
        return dto;
    }
    
    @GetMapping("/getalldisplay")
    public List<SvtDTO> getAllDisplay() {
        List<Display> allDisplays = displayService.getAllDisplays();
        List<SvtDTO> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for(Display disp : allDisplays) {
            boolean anyMatch = asuos.stream().anyMatch(e -> e.getDisplay().getId() == disp.getId());
            if(!anyMatch) {
                SvtDTO dto = displayMapper.getDto(disp);
                 dtoes.add(dto);
            }
            
           
        }
        return dtoes;
    }
    
    @GetMapping("/getdisplays")
    public List<SvtDTO> getDisplays() {
        List<Display> allDisplays = displayService.getAllDisplays();
        List<SvtDTO> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for(Display disp : allDisplays) {
                SvtDTO dto = displayMapper.getDto(disp);
                 dtoes.add(dto);
        }
        return dtoes;
    }
    
         @GetMapping("/modswunit")
    public List<SvtModelDto> getModelSwunit() {
        List<SwitchingUnitModel> allModels = swunitModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelSwunitDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getswunit")
    public SvtDTO getSwunitById(Long swunitId) {
        SwitchingUnit swunit = swunitService.getById(swunitId);
        SvtDTO dto = swunitMapper.getDto(swunit);
        return dto;
    }
    
     @GetMapping("/modsubdisplay")
    public List<SvtModelDto> getModelSubDisplay() {
        List<SubDisplayModel> allModels = subDisplayModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelSubDisplayDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getallterminal")
    public List<SvtDTO> getAllTerminal() {
        List<Terminal> allTerminal = terminalService.getAllTerminal();
        List<SvtDTO> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for(Terminal terminal : allTerminal) {
            boolean anyMatch = asuos.stream().anyMatch(e -> e.getTerminal().getId() == terminal.getId());
            if(!anyMatch) {
                SvtDTO dto = terminalMapper.getDto(terminal);
                dtoes.add(dto);
            }
        }
        return dtoes;
    }
    
     @GetMapping("/getterminals")
    public List<SvtDTO> getTerminals() {
        List<Terminal> allTerminal = terminalService.getAllTerminal();
        List<SvtDTO> dtoes = new ArrayList<>();
        for(Terminal terminal : allTerminal) {
                SvtDTO dto = terminalMapper.getDto(terminal);
                dtoes.add(dto);
        }
        return dtoes;
    }
    
    @GetMapping("/getallthermoprinter")
    public List<SvtDTO> getAllThermoprinter() {
        List<ThermoPrinter> allThermoprinter = thermoprinterService.getAllThermoprinter();
        List<SvtDTO> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for(ThermoPrinter thermoprinter : allThermoprinter) {
            boolean anyMatch = asuos.stream().anyMatch(e -> e.getThermoPrinter().getId() == thermoprinter.getId());
            if(!anyMatch) {
                SvtDTO dto = thermoprinterMapper.getDto(thermoprinter);
                dtoes.add(dto);
            }
        }
        return dtoes;
    }
    
    
     @GetMapping("/getthermoprinters")
    public List<SvtDTO> getThermoprinters() {
        List<ThermoPrinter> allThermoprinter = thermoprinterService.getAllThermoprinter();
        List<SvtDTO> dtoes = new ArrayList<>();
        for(ThermoPrinter thermoprinter : allThermoprinter) {
                SvtDTO dto = thermoprinterMapper.getDto(thermoprinter);
                dtoes.add(dto);
        }
        return dtoes;
    }
    
    
       @GetMapping("/getallswunit")
    public List<SvtDTO> getAllSwitchingUnit() {
        List<SwitchingUnit> allSwitchingUnits = swunitService.getAllSwitchingUnit();
        List<SvtDTO> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for(SwitchingUnit switchingUnit : allSwitchingUnits) {
            boolean anyMatch = asuos.stream().anyMatch(e -> e.getSwitchingUnit().getId() == switchingUnit.getId());
            if(!anyMatch) {
                SvtDTO dto = swunitMapper.getDto(switchingUnit);
                dtoes.add(dto);
            }
        }
        return dtoes;
    }
    
         @GetMapping("/getswunits")
    public List<SvtDTO> getSwitchingUnits() {
        List<SwitchingUnit> allSwitchingUnits = swunitService.getAllSwitchingUnit();
        List<SvtDTO> dtoes = new ArrayList<>();
        for(SwitchingUnit switchingUnit : allSwitchingUnits) {
                SvtDTO dto = swunitMapper.getDto(switchingUnit);
                dtoes.add(dto);
        }
        return dtoes;
    }
    
       @GetMapping("/getallswitch")
    public List<SvtDTO> getAllSwitch() {
        List<SwitchHub> allSwitches = switchHubService.getAllSwitch();
        List<SvtDTO> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for(SwitchHub switchHub : allSwitches) {
            boolean anyMatch = asuos.stream().anyMatch(e -> e.getSwitchHubSet().contains(switchHub));
            if(!anyMatch) {
                SvtDTO dto = switchHubMapper.getDto(switchHub);
                dtoes.add(dto);
            }
        }
        return dtoes;
    }
    
      @GetMapping("/getswitches")
    public List<SvtDTO> getSwitches() {
        List<SwitchHub> allSwitches = switchHubService.getAllSwitch();
        List<SvtDTO> dtoes = new ArrayList<>();
        for(SwitchHub switchHub : allSwitches) {
                SvtDTO dto = switchHubMapper.getDto(switchHub);
                dtoes.add(dto);
        }
        return dtoes;
    }
    
      @GetMapping("/getasuo")
    public AsuoDTO getAsuoById(Long asuoId) {
        Asuo asuo = asuoService.getById(asuoId);
        AsuoDTO dto = asuoMapper.getDto(asuo);
        return dto;
    }
    
          @GetMapping("/modfax")
    public List<SvtModelDto> getModelFax() {
        List<FaxModel> allModels = faxModelService.getAllModels();
        List<SvtModelDto> dtoes = new ArrayList<>();
        for(FaxModel model : allModels) {
            SvtModelDto dtoForSelectize = faxModelMapper.getDtoForSelectize(model);
            dtoes.add(dtoForSelectize);
        }
        
        return dtoes;
    }
    
    @GetMapping("/getfax")
    public FaxDto getFaxById(Long faxId) {

        Fax fax = faxService.getById(faxId);
        FaxDto faxDto = faxMapper.getDto(fax);

        return faxDto;
    }
    
    @GetMapping("/repairs")
    public List<RepairDto> getRepairs(Long id) {
        List<RepairDto> repairs = repairInfoService.getRepairs(id);
        return repairs;
    }
 //   @Log
    @PostMapping("/repairs")
    public ResponseEntity<String> sendRepair(@RequestBody RepairDto dto) throws IOException {
        clientDao.addRepair(dto);
        return ResponseEntity.ok("Запись сохранена");
    }
 //   @Log
    @DeleteMapping("/repairs")
    public ResponseEntity<String> deleteRepair(Long id) throws IOException {
        clientDao.deleteRepair(id);
        return ResponseEntity.ok("Запись удалена");
    }
 //   @Log
    @PutMapping("/repairs")
    public ResponseEntity<String> updateRepair(@RequestBody RepairDto dto) throws IOException {
        clientDao.editRepair(dto);
        return ResponseEntity.ok("Запись обновлена");
    }
    
    @GetMapping("/transfers")
    public List<TransferDto> getTransfers(Long id) {
        List <TransferDto> transfers = transferInfoService.getTransfers(id);
        return transfers;
    }
   //     @Log("Save transfer")
      @PostMapping("/transfers")
    public ResponseEntity<String> sendTransfer(@RequestBody TransferDto dto) throws IOException {
        clientDao.addTransfer(dto);
        return ResponseEntity.ok("Запись сохранена");
    }
//        @Log
        @DeleteMapping("/transfers")
    public ResponseEntity<String> deleteTransfer(Long id) throws IOException {
        clientDao.deleteTransfer(id);
        return ResponseEntity.ok("Запись удалена");
    }
  //  @Log
    @PutMapping("/transfers")
    public ResponseEntity<String> updateTransfer(@RequestBody TransferDto dto) throws IOException {
        clientDao.editTransfer(dto);
        return ResponseEntity.ok("Запись обновлена");
    }
}
