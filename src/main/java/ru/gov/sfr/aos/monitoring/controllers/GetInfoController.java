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
import ru.gov.sfr.aos.monitoring.entities.ConditionerManufacturer;
import ru.gov.sfr.aos.monitoring.entities.ConditionerModel;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Display;
import ru.gov.sfr.aos.monitoring.entities.DisplayManufacturer;
import ru.gov.sfr.aos.monitoring.entities.DisplayModel;
import ru.gov.sfr.aos.monitoring.entities.Fax;
import ru.gov.sfr.aos.monitoring.entities.FaxManufacturer;
import ru.gov.sfr.aos.monitoring.entities.FaxModel;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Hub;
import ru.gov.sfr.aos.monitoring.entities.HubManufacturer;
import ru.gov.sfr.aos.monitoring.entities.HubModel;
import ru.gov.sfr.aos.monitoring.entities.Infomat;
import ru.gov.sfr.aos.monitoring.entities.InfomatManufacturer;
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
import ru.gov.sfr.aos.monitoring.entities.ProgramSoftware;
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
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.entities.SystemBlockManufacturer;
import ru.gov.sfr.aos.monitoring.entities.SystemBlockModel;
import ru.gov.sfr.aos.monitoring.entities.Terminal;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplay;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplayManufacturer;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplayModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalManufacturer;
import ru.gov.sfr.aos.monitoring.entities.TerminalModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinter;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinterManufacturer;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinterModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensor;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensorManufacturer;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensorModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalServer;
import ru.gov.sfr.aos.monitoring.entities.TerminalServerManufacturer;
import ru.gov.sfr.aos.monitoring.entities.TerminalServerModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalUps;
import ru.gov.sfr.aos.monitoring.entities.TerminalUpsManufacturer;
import ru.gov.sfr.aos.monitoring.entities.TerminalUpsModel;
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
import ru.gov.sfr.aos.monitoring.mappers.ConditionerManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.DisplayManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.DisplayMapper;
import ru.gov.sfr.aos.monitoring.mappers.DisplayModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.HubManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.HubMapper;
import ru.gov.sfr.aos.monitoring.mappers.HubModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatModelMapper;
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
import ru.gov.sfr.aos.monitoring.mappers.SystemBlockMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemblockManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemblockModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalDisplayManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalDisplayMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalDisplayModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalPrinterManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalPrinterMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalPrinterModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalSensorManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalSensorMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalSensorModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalServerManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalServerMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalServerModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalUpsManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalUpsMapper;
import ru.gov.sfr.aos.monitoring.mappers.TerminalUpsModelMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsManufacturerMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsModelMapper;
import ru.gov.sfr.aos.monitoring.models.AsuoDTO;
import ru.gov.sfr.aos.monitoring.models.BatteryTypeDto;
import ru.gov.sfr.aos.monitoring.models.CpuModelDto;
import ru.gov.sfr.aos.monitoring.models.DepDto;
import ru.gov.sfr.aos.monitoring.models.DisplayDto;
import ru.gov.sfr.aos.monitoring.models.FaxDto;
import ru.gov.sfr.aos.monitoring.models.HddDto;
import ru.gov.sfr.aos.monitoring.models.HubDto;
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.models.PhoneManufacturerDto;
import ru.gov.sfr.aos.monitoring.models.ProgramSoftwareDto;
import ru.gov.sfr.aos.monitoring.models.RamDto;
import ru.gov.sfr.aos.monitoring.models.RepairDto;
import ru.gov.sfr.aos.monitoring.models.SvtAtsDTO;
import ru.gov.sfr.aos.monitoring.models.SvtConditionerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtServerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSystemBlockDTO;
import ru.gov.sfr.aos.monitoring.models.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.models.TerminalDisplayDto;
import ru.gov.sfr.aos.monitoring.models.TerminalDto;
import ru.gov.sfr.aos.monitoring.models.TransferDto;
import ru.gov.sfr.aos.monitoring.models.UpsManufacturerDto;
import ru.gov.sfr.aos.monitoring.models.UpsModelDto;
import ru.gov.sfr.aos.monitoring.repositories.AsuoRepo;
import ru.gov.sfr.aos.monitoring.services.AsuoService;
import ru.gov.sfr.aos.monitoring.services.AtsManufacturerService;
import ru.gov.sfr.aos.monitoring.services.AtsModelService;
import ru.gov.sfr.aos.monitoring.services.AtsService;
import ru.gov.sfr.aos.monitoring.services.BatteryTypeService;
import ru.gov.sfr.aos.monitoring.services.CdDriveModelService;
import ru.gov.sfr.aos.monitoring.services.ClientDAO;
import ru.gov.sfr.aos.monitoring.services.ConditionerManufacturerService;
import ru.gov.sfr.aos.monitoring.services.ConditionerModelService;
import ru.gov.sfr.aos.monitoring.services.ConditionerService;
import ru.gov.sfr.aos.monitoring.services.CpuModelService;
import ru.gov.sfr.aos.monitoring.services.DisplayManufacturerService;
import ru.gov.sfr.aos.monitoring.services.DisplayModelService;
import ru.gov.sfr.aos.monitoring.services.DisplayService;
import ru.gov.sfr.aos.monitoring.services.FaxManufacturerService;
import ru.gov.sfr.aos.monitoring.services.FaxModelService;
import ru.gov.sfr.aos.monitoring.services.FaxService;
import ru.gov.sfr.aos.monitoring.services.HddModelService;
import ru.gov.sfr.aos.monitoring.services.HubManufacturerService;
import ru.gov.sfr.aos.monitoring.services.HubModelService;
import ru.gov.sfr.aos.monitoring.services.HubService;
import ru.gov.sfr.aos.monitoring.services.InfomatManufacturerService;
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
import ru.gov.sfr.aos.monitoring.services.SystemBlockModelService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockService;
import ru.gov.sfr.aos.monitoring.services.SystemblockManufacturerService;
import ru.gov.sfr.aos.monitoring.services.TerminalDisplayManufacturerService;
import ru.gov.sfr.aos.monitoring.services.TerminalDisplayModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalDisplayService;
import ru.gov.sfr.aos.monitoring.services.TerminalManufacturerService;
import ru.gov.sfr.aos.monitoring.services.TerminalModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalPrinterManufacturerService;
import ru.gov.sfr.aos.monitoring.services.TerminalPrinterModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalPrinterService;
import ru.gov.sfr.aos.monitoring.services.TerminalProgramSoftwareService;
import ru.gov.sfr.aos.monitoring.services.TerminalSensorManufacturerService;
import ru.gov.sfr.aos.monitoring.services.TerminalSensorModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalSensorService;
import ru.gov.sfr.aos.monitoring.services.TerminalServerManufacturerService;
import ru.gov.sfr.aos.monitoring.services.TerminalServerModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalServerService;
import ru.gov.sfr.aos.monitoring.services.TerminalService;
import ru.gov.sfr.aos.monitoring.services.TerminalUpsManufacturerService;
import ru.gov.sfr.aos.monitoring.services.TerminalUpsModelService;
import ru.gov.sfr.aos.monitoring.services.TerminalUpsService;
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
    private DisplayMapper displayMapper;
    @Autowired
    private DisplayService displayService;
    @Autowired
    private DisplayModelService displayModelService;
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
    @Autowired
    private ConditionerManufacturerService conditionerManufacturerService;
    @Autowired
    private ConditionerManufacturerMapper conditionerManufacturerMapper;
    @Autowired
    private ConditionerModelMapper conditionerModelMapper;
    @Autowired
    private InfomatManufacturerMapper infomatManufacturerMapper;
    @Autowired
    private InfomatManufacturerService infomatManufacturerService;
    @Autowired
    private InfomatModelMapper infomatModelMapper;
    @Autowired
    private SystemblockManufacturerMapper sysblockManufacturerMapper;
    @Autowired
    private SystemblockManufacturerService sysblockManufacturerService;
    @Autowired
    private SystemblockModelMapper sysblockModelMapper;
    @Autowired
    private TerminalDisplayManufacturerService terminalDisplayManufacturerService;
    @Autowired
    private TerminalDisplayModelMapper terminalDisplayModelMapper;
    @Autowired
    private TerminalPrinterManufacturerService terminalPrinterManufacturerService;
    @Autowired
    private TerminalPrinterModelMapper terminalPrinterModelMapper;
    @Autowired
    private TerminalServerManufacturerService terminalServerManufacturerService;
    @Autowired
    private TerminalServerModelMapper terminalServerModelMapper;
    @Autowired
    private TerminalUpsManufacturerService terminalUpsManufacturerService;
    @Autowired
    private TerminalUpsModelMapper terminalUpsModelMapper;
    @Autowired
    private TerminalSensorManufacturerService terminalSensorManufacturerService;
    @Autowired
    private TerminalSensorModelMapper terminalSensorModelMapper;
    @Autowired
    private TerminalPrinterManufacturerMapper terminalPrinterManufacturerMapper;
    @Autowired
    private TerminalSensorManufacturerMapper terminalSensorManufacturerMapper;
    @Autowired
    private TerminalUpsManufacturerMapper terminalUpsManufacturerMapper;
    @Autowired
    private TerminalServerManufacturerMapper terminalServerManufacturerMapper;
    @Autowired
    private TerminalDisplayManufacturerMapper terminalDisplayManufacturerMapper;
    @Autowired
    private TerminalDisplayModelService terminalDisplayModelService;
    @Autowired
    private TerminalDisplayService terminalDisplayService;
    @Autowired
    private TerminalDisplayMapper terminalDisplayMapper;
    @Autowired
    private TerminalSensorModelService terminalSensorModelService;
    @Autowired
    private TerminalServerModelService terminalServerModelService;
    @Autowired
    private TerminalPrinterModelService terminalPrinterModelService;
    @Autowired
    private TerminalUpsModelService terminalUpsModelService;
    @Autowired
    private TerminalManufacturerService terminalManufacturerService;
    @Autowired
    private TerminalManufacturerMapper terminalManufacturerMapper;
    @Autowired
    private TerminalModelMapper terminalModelMapper;
    @Autowired
    private TerminalSensorService terminalSensorService;
    @Autowired
    private TerminalSensorMapper terminalSensorMapper;
    @Autowired
    private TerminalServerService terminalServerService;
    @Autowired
    private TerminalServerMapper terminalServerMapper;
    @Autowired
    private TerminalPrinterService terminalPrinterService;
    @Autowired
    private TerminalPrinterMapper terminalPrinterMapper;
    @Autowired
    private TerminalUpsService terminalUpsService;
    @Autowired
    private TerminalUpsMapper terminalUpsMapper;
    @Autowired
    private HubService hubService;
    @Autowired
    private HubMapper hubMapper;
    @Autowired
    private HubManufacturerService hubManufacturerService;
    @Autowired
    private HubManufacturerMapper hubManufacturerMapper;
    @Autowired
    private HubModelService hubModelService;
    @Autowired
    private HubModelMapper hubModelMapper;
    @Autowired
    private TerminalProgramSoftwareService terminalProgramSoftwareService;
    @Autowired
    private DisplayManufacturerService displayManufacturerService;
    @Autowired
    private DisplayManufacturerMapper displayManufacturerMapper;
    @Autowired
    private DisplayModelMapper displayModelMapper;
    
    
  
    
    
    
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
    

    @PostMapping("/save-display-manufacturer")
    public ResponseEntity<?> saveDisplayManufacturer(String name) throws ObjectAlreadyExists {
        DisplayManufacturer savedManufacturer = null;
        DisplayManufacturer potencialManufacturer = new DisplayManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = displayManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        ManufacturerDTO dto = displayManufacturerMapper.getDto(savedManufacturer);
        
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
    
    
    @PostMapping("/save-sysblock-manufacturer")
    public ResponseEntity<?> saveSysblockManufacturer(String name) throws ObjectAlreadyExists {
        SystemBlockManufacturer savedManufacturer = null;
        SystemBlockManufacturer potencialManufacturer = new SystemBlockManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = sysblockManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = sysblockManufacturerMapper.getDto(savedManufacturer);
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
    
    
    @PostMapping("/save-hub-manufacturer")
    public ResponseEntity<?> saveHubManufacturer(String name) throws ObjectAlreadyExists {
        HubManufacturer savedManufacturer = null;
        HubManufacturer potencialManufacturer = new HubManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = hubManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = hubManufacturerMapper.getDto(savedManufacturer);
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
    
    @PostMapping("/save-conditioner-manufacturer")
    public ResponseEntity<?> saveConditionerManufacturer(String name) throws ObjectAlreadyExists {
        ConditionerManufacturer savedManufacturer = null;
        ConditionerManufacturer potencialManufacturer = new ConditionerManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = conditionerManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = conditionerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/save-infomat-manufacturer")
    public ResponseEntity<?> saveInfomatManufacturer(String name) throws ObjectAlreadyExists {
        InfomatManufacturer savedManufacturer = null;
        InfomatManufacturer potencialManufacturer = new InfomatManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = infomatManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = infomatManufacturerMapper.getDto(savedManufacturer);
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
    
    @PostMapping("/save-terminal-display-manufacturer")
    public ResponseEntity<?> saveTerminalDisplayManufacturer(String name) throws ObjectAlreadyExists {
        TerminalDisplayManufacturer savedManufacturer = null;
        TerminalDisplayManufacturer potencialManufacturer = new TerminalDisplayManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = terminalDisplayManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalDisplayManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    
    @PostMapping("/save-terminal-printer-manufacturer")
    public ResponseEntity<?> saveTerminalPrinterManufacturer(String name) throws ObjectAlreadyExists {
        TerminalPrinterManufacturer savedManufacturer = null;
        TerminalPrinterManufacturer potencialManufacturer = new TerminalPrinterManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = terminalPrinterManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalPrinterManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/save-terminal-server-manufacturer")
    public ResponseEntity<?> saveTerminalServerManufacturer(String name) throws ObjectAlreadyExists {
        TerminalServerManufacturer savedManufacturer = null;
        TerminalServerManufacturer potencialManufacturer = new TerminalServerManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = terminalServerManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalServerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/save-terminal-ups-manufacturer")
    public ResponseEntity<?> saveTerminalUpsManufacturer(String name) throws ObjectAlreadyExists {
        TerminalUpsManufacturer savedManufacturer = null;
        TerminalUpsManufacturer potencialManufacturer = new TerminalUpsManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = terminalUpsManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalUpsManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
     @PostMapping("/save-terminal-manufacturer")
    public ResponseEntity<?> saveTerminalManufacturer(String name) throws ObjectAlreadyExists {
        TerminalManufacturer savedManufacturer = null;
        TerminalManufacturer potencialManufacturer = new TerminalManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = terminalManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/save-terminal-sensor-manufacturer")
    public ResponseEntity<?> saveTerminalSensorManufacturer(String name) throws ObjectAlreadyExists {
        TerminalSensorManufacturer savedManufacturer = null;
        TerminalSensorManufacturer potencialManufacturer = new TerminalSensorManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = terminalSensorManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalSensorManufacturerMapper.getDto(savedManufacturer);
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
    
    
    @GetMapping("/get-sysblock-manufacturers")
    public List<ManufacturerDTO> getSysblockManufacturers() {
        List<SystemBlockManufacturer> allManufacturers = sysblockManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(SystemBlockManufacturer el : allManufacturers) {
            ManufacturerDTO dto = sysblockManufacturerMapper.getDto(el);
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
    
    @GetMapping("/get-conditioner-manufacturers")
    public List<ManufacturerDTO> getConditionerManufacturers() {
        List<ConditionerManufacturer> allManufacturers = conditionerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(ConditionerManufacturer el : allManufacturers) {
            ManufacturerDTO dto = conditionerManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    @GetMapping("/get-infomat-manufacturers")
    public List<ManufacturerDTO> getInfomatManufacturers() {
        List<InfomatManufacturer> allManufacturers = infomatManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(InfomatManufacturer el : allManufacturers) {
            ManufacturerDTO dto = infomatManufacturerMapper.getDto(el);
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
    
    
    @GetMapping("/get-hub-manufacturers")
    public List<ManufacturerDTO> getHubManufacturers() {
        List<HubManufacturer> allManufacturers = hubManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(HubManufacturer el : allManufacturers) {
            ManufacturerDTO dto = hubManufacturerMapper.getDto(el);
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
    
    @GetMapping("/get-terminal-printer-manufacturers")
    public List<ManufacturerDTO> getTerminalPrinterManufacturers() {
        List<TerminalPrinterManufacturer> allManufacturers = terminalPrinterManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalPrinterManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }
    
    @GetMapping("/get-display-manufacturers")
    public List<ManufacturerDTO> getDisplayManufacturers() {
        List<DisplayManufacturer> allManufacturers = displayManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = displayManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }
    
    @GetMapping("/get-terminal-sensor-manufacturers")
    public List<ManufacturerDTO> getTerminalSensorManufacturers() {
        List<TerminalSensorManufacturer> allManufacturers = terminalSensorManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalSensorManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }
    
    @GetMapping("/get-terminal-ups-manufacturers")
    public List<ManufacturerDTO> getTerminalUpsManufacturers() {
        List<TerminalUpsManufacturer> allManufacturers = terminalUpsManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalUpsManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }
    
      @GetMapping("/get-terminal-server-manufacturers")
    public List<ManufacturerDTO> getTerminalServerManufacturers() {
        List<TerminalServerManufacturer> allManufacturers = terminalServerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalServerManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }
    
       @GetMapping("/get-terminal-display-manufacturers")
    public List<ManufacturerDTO> getTerminalDisplayManufacturers() {
        List<TerminalDisplayManufacturer> allManufacturers = terminalDisplayManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalDisplayManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }
    
    
      @GetMapping("/get-terminal-manufacturers")
    public List<ManufacturerDTO> getTerminalManufacturers() {
        List<TerminalManufacturer> allManufacturers = terminalManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }
    
    
    @GetMapping("/get-terminal-display")
    public TerminalDisplayDto getTerminalDisplayById(Long id) {
        TerminalDisplay byId = terminalDisplayService.getById(id);
        return terminalDisplayMapper.getDto(byId);
    }
    @GetMapping("/get-terminal-sensor")
    public TerminalComponentDto getTerminalSensorById(Long id) {
        TerminalSensor byId = terminalSensorService.getById(id);
        return terminalSensorMapper.getDto(byId);
    }
    @GetMapping("/get-terminal-server")
    public TerminalComponentDto getTerminalServerById(Long id) {
        TerminalServer byId = terminalServerService.getById(id);
        return terminalServerMapper.getDto(byId);
    }
    @GetMapping("/get-terminal-printer")
    public TerminalComponentDto getTerminalPrinterById(Long id) {
        TerminalPrinter byId = terminalPrinterService.getById(id);
        return terminalPrinterMapper.getDto(byId);
    }
    @GetMapping("/get-terminal-ups")
    public TerminalComponentDto getTerminalUpsById(Long id) {
        TerminalUps byId = terminalUpsService.getById(id);
        return terminalUpsMapper.getDto(byId);
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
    
    @GetMapping("/get-models-terminal-display-by-manufacturer")
    public List<SvtModelDto> getTerminalDisplayModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        List<TerminalDisplayModel> terminalDisplayModelList = terminalDisplayModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalDisplayModel model : terminalDisplayModelList) {
            SvtModelDto modeDto = terminalDisplayModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
     @GetMapping("/get-models-display-by-manufacturer")
    public List<SvtModelDto> getDisplayModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        List<DisplayModel> displayModelList = displayModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for(DisplayModel model : displayModelList) {
            SvtModelDto modeDto = displayModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    
    @GetMapping("/get-models-terminal-by-manufacturer")
    public List<SvtModelDto> getTerminalModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        List<TerminalModel> terminalModelList = terminalModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalModel model : terminalModelList) {
            SvtModelDto modeDto = terminalModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    
    @GetMapping("/get-hub-modelsby-manufacturer")
    public List<SvtModelDto> getHubModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        List<HubModel> modelList = hubModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for(HubModel model : modelList) {
            SvtModelDto modeDto = hubModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    
    
      @GetMapping("/get-models-terminal-display-all")
    public List<SvtModelDto> getTerminalDisplayModelsAll() {
        List<TerminalDisplayModel> terminalDisplayModelList = terminalDisplayModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalDisplayModel model : terminalDisplayModelList) {
            SvtModelDto modeDto = terminalDisplayModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
      @GetMapping("/get-models-terminal-sensor-all")
    public List<SvtModelDto> getTerminalSensorModelsAll() {
        List<TerminalSensorModel> terminalSensorModelList = terminalSensorModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalSensorModel model : terminalSensorModelList) {
            SvtModelDto modeDto = terminalSensorModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
      @GetMapping("/get-models-terminal-printer-all")
    public List<SvtModelDto> getTerminalPrinterModelsAll() {
        List<TerminalPrinterModel> modelList = terminalPrinterModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalPrinterModel model : modelList) {
            SvtModelDto modeDto = terminalPrinterModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
      @GetMapping("/get-models-terminal-server-all")
    public List<SvtModelDto> getTerminalServerModelsAll() {
        List<TerminalServerModel> modelList = terminalServerModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalServerModel model : modelList) {
            SvtModelDto modeDto = terminalServerModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
         @GetMapping("/get-models-hub-all")
    public List<SvtModelDto> getHubModelsAll() {
        List<HubModel> modelList = hubModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(HubModel model : modelList) {
            SvtModelDto modeDto = hubModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    
        @GetMapping("/get-all-po")
    public List<ProgramSoftwareDto> getPoAll() {
        List<ProgramSoftware> programSoftwares = terminalProgramSoftwareService.getProgramSoftwares();
        List<ProgramSoftwareDto> out = new ArrayList<>();
        return terminalProgramSoftwareService.getProgramSoftwareDtoesList(programSoftwares);
    }
    
      @GetMapping("/get-models-terminal-ups-all")
    public List<SvtModelDto> getTerminalUpsModelsAll() {
        List<TerminalUpsModel> modelList = terminalUpsModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalUpsModel model : modelList) {
            SvtModelDto modeDto = terminalUpsModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    
    @GetMapping("/get-models-terminal-printer-by-manufacturer")
    public List<SvtModelDto> getTerminalPrinterModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        List<TerminalPrinterModel> terminalPrinterModelList = terminalPrinterModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalPrinterModel model : terminalPrinterModelList) {
            SvtModelDto modeDto = terminalPrinterModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    
    @GetMapping("/get-models-terminal-server-by-manufacturer")
    public List<SvtModelDto> getTerminalServerModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        List<TerminalServerModel> terminalServerModelList = terminalServerModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalServerModel model : terminalServerModelList) {
            SvtModelDto modeDto = terminalServerModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
     @GetMapping("/get-models-terminal-ups-by-manufacturer")
    public List<SvtModelDto> getTerminalUpsModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        List<TerminalUpsModel> terminalUpsModelList = terminalUpsModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalUpsModel model : terminalUpsModelList) {
            SvtModelDto modeDto = terminalUpsModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }
    
    
     @GetMapping("/get-models-terminal-sensor-by-manufacturer")
    public List<SvtModelDto> getTerminalSensorModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        List<TerminalSensorModel> modelsByManufacturerId = terminalSensorModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalSensorModel model : modelsByManufacturerId) {
            SvtModelDto modeDto = terminalSensorModelMapper.getDto(model);
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
            SvtModelDto modelDto = serverModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
    @GetMapping("/get-sysblock-modelsby-manufacturer")
    public List<SvtModelDto> getSysblockModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        SystemBlockManufacturer manufacturer = sysblockManufacturerService.getManufacturer(id);
        Set<SystemBlockModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(SystemBlockModel model : upsListModel) {
            SvtModelDto modelDto = sysblockModelMapper.getDto(model);
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
    
    
     @GetMapping("/get-conditioner-modelsby-manufacturer")
    public Set<SvtModelDto> getConditionerModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        ConditionerManufacturer manufacturer = conditionerManufacturerService.getManufacturer(id);
        Set<ConditionerModel> upsListModel = manufacturer.getModels();
        Set<SvtModelDto> out = new HashSet<>();
        for(ConditionerModel model : upsListModel) {
            SvtModelDto modelDto = conditionerModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
    
    @GetMapping("/get-infomat-modelsby-manufacturer")
    public Set<SvtModelDto> getInfomatModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        InfomatManufacturer manufacturer = infomatManufacturerService.getManufacturer(id);
        Set<InfomatModel> upsListModel = manufacturer.getModels();
        Set<SvtModelDto> out = new HashSet<>();
        for(InfomatModel model : upsListModel) {
            SvtModelDto modelDto = infomatModelMapper.getDto(model);
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
        PlaceType currentPlaceType = PlaceService.getPlaceType(placeType);
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
    
    @GetMapping("/place-by-loc-and-placetype")
    public List<PlaceDTO> getPlacesByLocationAndPlaceType(Long locationId, String placeType) {
        PlaceType curentPlaceType = PlaceService.getPlaceType(placeType);
        return placeService.getPlacesByLocationAndPlaceType(locationId, curentPlaceType);
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
    
    @GetMapping("/placebyplacetype")
    public List<PlaceDTO> getPlacesByPlaceType(String placeType) {
        PlaceType curentType = PlaceService.getPlaceType(placeType);
        List<PlaceDTO> places = placeService.getPlacesByPlaceType(curentType);
        return places;
    }

    @GetMapping("/placebyid")
    public PlaceDTO getPlacesById(Long placeId) {
        PlaceDTO place = placeService.getPlaceById(placeId);
        return place;
    }

    @GetMapping("/modphones")
    public List<SvtModelDto> getModelPhones() {
        List<PhoneModel> allModels = phoneModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(PhoneModel el : allModels) {
            SvtModelDto dto = phoneModelMapper.getDtoForSelectize(el);
            out.add(dto);
        }
        return out;
    }
    
       @GetMapping("/modmonitors")
    public List<SvtModelDto> getModelMonitors() {
        List<MonitorModel> allModels = monitorModelService.getAllActualModels();
       List<SvtModelDto> monitorModelsDtoes = new ArrayList<>();
       for(MonitorModel model : allModels) {
          SvtModelDto dtoForSelectize = monitorModelMapper.getDtoForSelectize(model);
          monitorModelsDtoes.add(dtoForSelectize);
       }
        
        return monitorModelsDtoes;
    }
    
    
     @GetMapping("/modups")
    public List<SvtModelDto> getModelUps() {
        List<UpsModel> allModels = upsModelService.getAllActualModels();
        List<SvtModelDto> upsModelDtoForSelectize = upsModelMapper.getUpsModelDtoForSelectize(allModels);
        return upsModelDtoForSelectize;
    }
    
     @GetMapping("/modsysblock")
    public Set<SvtModelDto> getModelSysBlocks() {

        List<SystemBlockModel> allModels = systemBlockModelService.getAllActualModels();
        Set<SvtModelDto> systemBlockModelsDtoes = new HashSet<>();
        for(SystemBlockModel model : allModels) {
            SvtModelDto dtoForSelectize = sysblockModelMapper.getDtoForSelectize(model);
            systemBlockModelsDtoes.add(dtoForSelectize);
        }

        return systemBlockModelsDtoes;
    }
    
    @GetMapping("/modmotherboard")
    public List<SvtModelDto> getModelMotherboard() {

        List<Motherboard> allModels = motherboardModelService.getAllActualModels();
        List<SvtModelDto> motherboardModelsDtoes = svtModelMapper.getModelMotherboardModelsDtoes(allModels);

        return motherboardModelsDtoes;
    }
    
     @GetMapping("/modcpu")
    public List<CpuModelDto> getModelCpu() {

        List<Cpu> allModels = cpuModelService.getAllActualModels();
        List<CpuModelDto> cpuDtoes = svtModelMapper.getCpuModelDtoes(allModels);

        return cpuDtoes;
    }
    
    @GetMapping("/modram")
    public List<RamDto> getModelRam() {

        List<Ram> allModels = ramModelService.getAllActualModels();
        List<RamDto> ramDtoes = svtModelMapper.getRamDtoes(allModels);

        return ramDtoes;
    }
    
    @GetMapping("/modhdd")
    public List<HddDto> getModelHdd() {

        List<Hdd> allModels = hddModelService.getAllActualModels();
        List<HddDto> hddDtoes = svtModelMapper.getHddDtoes(allModels);

        return hddDtoes;
    }
    
     @GetMapping("/modvideo")
    public List<SvtModelDto> getModelVideoCard() {

        List<VideoCard> allModels = videoCardModelService.getAllActualModels();
        List<SvtModelDto> videoCardDtoes = svtModelMapper.getVideoCardDtoes(allModels);

        return videoCardDtoes;
    }
    
       @GetMapping("/modcddrive")
    public List<SvtModelDto> getModelCdDrive() {

        List<CdDrive> allModels = cdDriveModelService.getAllActualModels();
        List<SvtModelDto> cdDriveDtoes = svtModelMapper.getCdDriveDtoes(allModels);

        return cdDriveDtoes;
    }
    
    @GetMapping("/modscard")
    public List<SvtModelDto> getModelSoundCard() {

        List<SoundCard> allModels = soundCardModelService.getAllActualModels();
        List<SvtModelDto> soundCardDtoes = svtModelMapper.getSoundCardDtoes(allModels);

        return soundCardDtoes;
    }
    
     @GetMapping("/modlcard")
    public List<SvtModelDto> getModelLanCard() {

        List<LanCard> allModels = lanCardModelService.getAllActualModels();
        List<SvtModelDto> lanCardDtoes = svtModelMapper.getLanCardDtoes(allModels);

        return lanCardDtoes;
    }
    
       @GetMapping("/modkeyboard")
    public List<SvtModelDto> getModelKeyboard() {

        List<Keyboard> allModels = keyboardModelService.getAllActualModels();
        List<SvtModelDto> keyboardDtoes = svtModelMapper.getKeyboardDtoes(allModels);

        return keyboardDtoes;
    }
    
     @GetMapping("/modmouse")
    public List<SvtModelDto> getModelMouse() {

        List<Mouse> allModels = mouseModelService.getAllActualModels();
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

        List<Speakers> allModels = speakersModelService.getAllActualModels();
        List<SvtModelDto> speakersDtoes = svtModelMapper.getSpeakersDtoes(allModels);

        return speakersDtoes;
    }
    
     @GetMapping("/typebatups")
    public List<BatteryTypeDto> getBatteryTypeUps() {

        List<BatteryType> allBatteryTypes = batteryTypeService.getAllActualBatteryTypes();
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
    public Set<SvtModelDto> getModelConditioner() {
        List<ConditionerModel> allModels = conditionerModelService.getAllModels();
        Set<SvtModelDto> dtoes = new HashSet<>();
        for(ConditionerModel model : allModels) {
            SvtModelDto dtoForSelectize = conditionerModelMapper.getDtoForSelectize(model);
            dtoes.add(dtoForSelectize);
        }
        return dtoes;
    }
    
    @GetMapping("/getconditioner")
    public SvtDTO getConditionerById(Long conditionerId) {
        Conditioner conditioner = conditionerService.getById(conditionerId);
        SvtConditionerDTO conditionerDto = conditionerMapper.getDto(conditioner);
        return conditionerDto;
    }
    
    @GetMapping("/modinfomat")
    public Set<SvtModelDto> getModelInfomat() {
        List<InfomatModel> allModels = infomatModelService.getAllModels();
        Set<SvtModelDto> dtoes = new HashSet<>();
        for(InfomatModel model : allModels) {
            SvtModelDto dtoForSelectize = infomatModelMapper.getDtoForSelectize(model);
            dtoes.add(dtoForSelectize);
        }
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
    

         @GetMapping("/moddisplay")
    public List<SvtModelDto> getModelDisplay() {
        List<DisplayModel> allModels = displayModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelDisplayDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getdisplay")
    public DisplayDto getDisplayById(Long displayId) {
        Display display = displayService.getById(displayId);
        DisplayDto dto = displayMapper.getDto(display);
        return dto;
    }
    
    @GetMapping("/getalldisplay")
    public List<DisplayDto> getAllDisplay() {
        List<Display> allDisplays = displayService.getAllDisplays();
        List<DisplayDto> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for(Display disp : allDisplays) {
                DisplayDto dto = displayMapper.getDto(disp);
                 dtoes.add(dto);
        }
        return dtoes;
    }
    
    @GetMapping("/getdisplays")
    public List<DisplayDto> getDisplays() {
        List<Display> allDisplays = displayService.getAllDisplays();
        List<DisplayDto> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for(Display disp : allDisplays) {
                DisplayDto dto = displayMapper.getDto(disp);
                 dtoes.add(dto);
        }
        return dtoes;
    }

    
     @GetMapping("/modsubdisplay")
    public List<SvtModelDto> getModelSubDisplay() {
        List<SubDisplayModel> allModels = subDisplayModelService.getAllActualModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelSubDisplayDtoes(allModels);
        return dtoes;
    }
    
    @GetMapping("/getallterminal")
    public List<SvtDTO> getAllTerminal() {
        List<Terminal> allTerminal = terminalService.getAllTerminal();
        List<SvtDTO> dtoes = new ArrayList<>();
        for(Terminal el : allTerminal) {
           SvtDTO dto = terminalMapper.getDto(el);
           dtoes.add(dto); 
        }
        return dtoes;
    }
    
     @GetMapping("/getterminals")
    public List<TerminalDto> getTerminals() {
        List<Terminal> allTerminal = terminalService.getAllTerminal();
        List<TerminalDto> dtoes = new ArrayList<>();
        for(Terminal terminal : allTerminal) {
                TerminalDto dto = terminalMapper.getDto(terminal);
                dtoes.add(dto);
        }
        return dtoes;
    }
    
    @GetMapping("/get-terminal-displays")
    public List<TerminalComponentDto> getTerminalDisplays() {
        List<TerminalDisplay> list = terminalDisplayService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for(TerminalDisplay el : list) {
                TerminalComponentDto dto = terminalDisplayMapper.getDto(el);
                dtoes.add(dto);
        }
        return dtoes;
    }
    
    
     @GetMapping("/get-terminal-printers")
    public List<TerminalComponentDto> getTerminalPrinters() {
        List<TerminalPrinter> list = terminalPrinterService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for(TerminalPrinter el : list) {
                TerminalComponentDto dto = terminalPrinterMapper.getDto(el);
                dtoes.add(dto);
        }
        return dtoes;
    }
    
    @GetMapping("/get-terminal-sensors")
    public List<TerminalComponentDto> getTerminalSensors() {
        List<TerminalSensor> list = terminalSensorService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for(TerminalSensor el : list) {
                TerminalComponentDto dto = terminalSensorMapper.getDto(el);
                dtoes.add(dto);
        }
        return dtoes;
    }
    @GetMapping("/get-terminal-servers")
    public List<TerminalComponentDto> getTerminalServers() {
        List<TerminalServer> list = terminalServerService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for(TerminalServer el : list) {
                TerminalComponentDto dto = terminalServerMapper.getDto(el);
                dtoes.add(dto);
        }
        return dtoes;
    }
    @GetMapping("/get-terminal-upses")
    public List<TerminalComponentDto> getTerminalUpses() {
        List<TerminalUps> list = terminalUpsService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for(TerminalUps el : list) {
                TerminalComponentDto dto = terminalUpsMapper.getDto(el);
                dtoes.add(dto);
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
    
    
       @GetMapping("/get-hubs")
    public List<HubDto> getHubs() {
        List<Hub> list = hubService.getAll();
        List<HubDto> dtoes = new ArrayList<>();
        for(Hub el : list) {
                HubDto dto = hubMapper.getDto(el);
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
    
    @GetMapping("/gethub")
    public HubDto getHubById(Long hubId) {
        Hub hub = hubService.getById(hubId);
        HubDto dto = hubMapper.getDto(hub);
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
