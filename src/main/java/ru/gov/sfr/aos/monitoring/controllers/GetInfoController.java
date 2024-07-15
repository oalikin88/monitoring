/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.opfr.springBootStarterDictionary.fallback.FallbackOrganizationClient;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;
import org.opfr.springBootStarterDictionary.models.DictionaryOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.BatteryType;
import ru.gov.sfr.aos.monitoring.entities.CdDrive;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Keyboard;
import ru.gov.sfr.aos.monitoring.entities.LanCard;
import ru.gov.sfr.aos.monitoring.entities.Monitor;
import ru.gov.sfr.aos.monitoring.entities.MonitorModel;
import ru.gov.sfr.aos.monitoring.entities.Motherboard;
import ru.gov.sfr.aos.monitoring.entities.Mouse;
import ru.gov.sfr.aos.monitoring.entities.OperationSystem;
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.entities.PhoneModel;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.entities.ScannerModel;
import ru.gov.sfr.aos.monitoring.entities.SoundCard;
import ru.gov.sfr.aos.monitoring.entities.Speakers;
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.entities.SystemBlockModel;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.entities.VideoCard;
import ru.gov.sfr.aos.monitoring.mappers.BatteryTypeMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorMapper;
import ru.gov.sfr.aos.monitoring.mappers.OperationSystemMapper;
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
import ru.gov.sfr.aos.monitoring.mappers.ScannerMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemBlockMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsMapper;
import ru.gov.sfr.aos.monitoring.models.BatteryTypeDto;
import ru.gov.sfr.aos.monitoring.models.CpuModelDto;
import ru.gov.sfr.aos.monitoring.models.HddDto;
import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.models.RamDto;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSystemBlockDTO;
import ru.gov.sfr.aos.monitoring.repositories.BatteryTypeRepo;
import ru.gov.sfr.aos.monitoring.services.BatteryTypeService;
import ru.gov.sfr.aos.monitoring.services.CdDriveModelService;
import ru.gov.sfr.aos.monitoring.services.CpuModelService;
import ru.gov.sfr.aos.monitoring.services.HddModelService;
import ru.gov.sfr.aos.monitoring.services.KeyboardModelService;
import ru.gov.sfr.aos.monitoring.services.LanCardModelService;
import ru.gov.sfr.aos.monitoring.services.MonitorService;
import ru.gov.sfr.aos.monitoring.services.MotherboardModelService;
import ru.gov.sfr.aos.monitoring.services.MouseModelService;
import ru.gov.sfr.aos.monitoring.services.OperationSystemService;
import ru.gov.sfr.aos.monitoring.services.RamModelService;
import ru.gov.sfr.aos.monitoring.services.ScannerModelService;
import ru.gov.sfr.aos.monitoring.services.ScannerService;
import ru.gov.sfr.aos.monitoring.services.SoundCardModelService;
import ru.gov.sfr.aos.monitoring.services.SpeakersModelService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockModelService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockService;
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
    public List<LocationDTO> getLocations() {

        List<LocationDTO> locations = locationService.getAllLocations();
        return locations;
    }




    @PostMapping("/AJAXPing")
    public Integer getAllContracts() {
        return 0;
    }

    
    @GetMapping("/placesel")
    public List<PlaceDTO> getPlacesByLocationAndDepartment() {
        List<PlaceDTO> places = placeService.getPlacesByPlaceType(PlaceType.EMPLOYEE);
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
        List<SvtModelDto> phoneModelsDtoes = svtModelMapper.getPhoneModelsDtoes(allModels);
        return phoneModelsDtoes;
    }
    
       @GetMapping("/modmonitors")
    public List<SvtModelDto> getModelMonitors() {
        List<MonitorModel> allModels = monitorModelService.getAllModels();
        List<SvtModelDto> monitorModelsDtoes = svtModelMapper.getMonitorModelsDtoes(allModels);
        return monitorModelsDtoes;
    }
    
     @GetMapping("/modups")
    public List<SvtModelDto> getModelUps() {
        List<UpsModel> allModels = upsModelService.getAllModels();
        List<SvtModelDto> monitorModelsDtoes = svtModelMapper.getUpsModelsDtoes(allModels);
        return monitorModelsDtoes;
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
    public List<DepartmentDTO> getDepartmentsByLocation(Long locationId) {

        List<DepartmentDTO> dtoes = placeService.getDepartmentsByLocation(locationId);

        return dtoes;
    }

    @GetMapping("/placebydepandloc")
    public List<PlaceDTO> getplacesByLocationAndDepartments(Long locationId, String departmentCode) {

        List<PlaceDTO> dtoes = placeService.getPlacesByLocationAndDepartment(locationId, departmentCode);
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
        List<SvtModelDto> scannerModelsDtoes = svtModelMapper.getModelScannerDtoes(allModels);
        return scannerModelsDtoes;
    }
    
    
}
