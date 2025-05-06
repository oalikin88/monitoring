/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Ats;
import ru.gov.sfr.aos.monitoring.entities.Conditioner;
import ru.gov.sfr.aos.monitoring.entities.Fax;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Infomat;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Monitor;
import ru.gov.sfr.aos.monitoring.entities.OperationSystem;
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.entities.Router;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.entities.Server;
import ru.gov.sfr.aos.monitoring.entities.SwitchHub;
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.mappers.AtsMapper;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerMapper;
import ru.gov.sfr.aos.monitoring.mappers.FaxMapper;
import ru.gov.sfr.aos.monitoring.mappers.HddMapper;
import ru.gov.sfr.aos.monitoring.mappers.InfomatMapper;
import ru.gov.sfr.aos.monitoring.mappers.MonitorMapper;
import ru.gov.sfr.aos.monitoring.mappers.OperationSystemMapper;
import ru.gov.sfr.aos.monitoring.mappers.PhoneMapper;
import ru.gov.sfr.aos.monitoring.mappers.RouterMapper;
import ru.gov.sfr.aos.monitoring.mappers.ScannerMapper;
import ru.gov.sfr.aos.monitoring.mappers.ServerMapper;
import ru.gov.sfr.aos.monitoring.mappers.SwitchHubMapper;
import ru.gov.sfr.aos.monitoring.mappers.SystemBlockMapper;
import ru.gov.sfr.aos.monitoring.mappers.UpsMapper;
import ru.gov.sfr.aos.monitoring.models.DepartmentTreeDto;
import ru.gov.sfr.aos.monitoring.models.FaxDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.HddDto;
import ru.gov.sfr.aos.monitoring.models.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.models.SvtAtsDTO;
import ru.gov.sfr.aos.monitoring.models.SvtConditionerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtServerDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.models.SvtSystemBlockDTO;
import ru.gov.sfr.aos.monitoring.services.AtsOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.AtsService;
import ru.gov.sfr.aos.monitoring.services.ConditionerOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.ConditionerService;
import ru.gov.sfr.aos.monitoring.services.FaxOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.FaxService;
import ru.gov.sfr.aos.monitoring.services.HddModelService;
import ru.gov.sfr.aos.monitoring.services.InfomatOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.InfomatService;
import ru.gov.sfr.aos.monitoring.services.MonitorOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.MonitorService;
import ru.gov.sfr.aos.monitoring.services.OperationSystemService;
import ru.gov.sfr.aos.monitoring.services.PhoneOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.PhoneService;
import ru.gov.sfr.aos.monitoring.services.RouterOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.RouterService;
import ru.gov.sfr.aos.monitoring.services.ScannerOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.ScannerService;
import ru.gov.sfr.aos.monitoring.services.ServerOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.ServerService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.SwitchHubService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.SystemBlockService;
import ru.gov.sfr.aos.monitoring.services.UpsOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.services.UpsService;

/**
 *
 * @author Alikin Oleg
 */
@RestController
@RequestMapping("/get-doc")
public class DocReportController {
    
    @Autowired
    private UpsService upsService;
    @Autowired
    private UpsOutDtoTreeService upsOutDtoTreeService;
    @Autowired
    private UpsMapper upsMapper;
    @Autowired
    private SystemBlockService systemblockService;
    @Autowired
    private SystemBlockOutDtoTreeService systemBlockOutDtoTreeService;
    @Autowired
    private SystemBlockMapper systemblockMapper;
    @Autowired
    private HddModelService hddModelService;
    @Autowired
    private HddMapper hddMapper;
    @Autowired
    private OperationSystemService operationSystemService;
    @Autowired
    private OperationSystemMapper osMapper;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private MonitorOutDtoTreeService monitorOutDtoTreeService;
    @Autowired
    private MonitorMapper monitorMapper;
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private ScannerOutDtoTreeService scannerOutDtoTreeService;
    @Autowired
    private ScannerMapper scannerMapper;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private PhoneOutDtoTreeService phoneOutDtoTreeService;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private ServerService serverService;
    @Autowired
    private ServerOutDtoTreeService serverOutDtoTreeService;
    @Autowired
    private ServerMapper serverMapper;
    @Autowired
    private SwitchHubService switchHubService;
    @Autowired
    private SwitchHubOutDtoTreeService switchHubOutDtoTreeService;
    @Autowired
    private SwitchHubMapper switchHubMapper;
    @Autowired
    private RouterService routerService;
    @Autowired
    private RouterOutDtoTreeService routerOutDtoTreeService;
    @Autowired
    private RouterMapper routerMapper;
    @Autowired
    private AtsService atsService;
    @Autowired
    private AtsOutDtoTreeService atsOutDtoTreeService;
    @Autowired
    private AtsMapper atsMapper;
    @Autowired
    private ConditionerService conditionerService;
    @Autowired
    private ConditionerOutDtoTreeService conditionerOutDtoTreeService;
    @Autowired
    private ConditionerMapper conditionerMapper;
    @Autowired
    private FaxService faxService;
    @Autowired
    private FaxOutDtoTreeService faxOutDtoTreeService;
    @Autowired
    private FaxMapper faxMapper;
    @Autowired
    private InfomatService infomatService;
    @Autowired
    private InfomatOutDtoTreeService infomatOutDtoTreeService;
    @Autowired
    private InfomatMapper infomatMapper;
    
    @GetMapping("/get-ups")
    public ResponseEntity<Resource>  getUpsDoc(@RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber, 
            @RequestParam(value="serialNumber", required = false) String serialNumber, 
            FilterDto dto) throws IOException {
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
        }  else {
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
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet spreadsheet = workbook.createSheet("выборка ИБП");
        XSSFRow row;
        
        Map<String, Object[]> upsData = new TreeMap<String, Object[]>(); 
        
        upsData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска", 
            "Год замены", "Состояние", "Кабинет", "Тип батареи", "Кол-во батарей", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtDTO) {
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    upsData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    String.valueOf(elem.getYearReplacement()), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getBatteryType(), String.valueOf(elem.getBatteryAmount()), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtAtsDTO) {
                    
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    upsData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp, String.valueOf(elem.getYearCreated()),
                    String.valueOf(elem.getYearReplacement()), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getBatteryType(), String.valueOf(elem.getBatteryAmount()), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = upsData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = upsData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportUps.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportUps.xlsx");
         return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
}
    
    @GetMapping("/sysblocks")
    public ResponseEntity<Resource> getSysBlocks(@RequestParam(value="username",required=false) String username,  
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
        } else if(null != serialNumber) {
            svtObjectsByEmployee = systemblockService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
        } else {
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
        
       
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet spreadsheet = workbook.createSheet("выборка системных блоков");
        XSSFRow row;
        
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска", 
            "Дата модернизации", "Состояние", "Кабинет", "ip адрес", "Операционная система", "Материнская плата",
            "Процессор", "ОЗУ", "НЖМД", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtSystemBlockDTO) {
                    SvtSystemBlockDTO elem = (SvtSystemBlockDTO)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    String dateUpgr;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    if(null == elem.getDateUpgrade()) {
                        dateUpgr = "без модернизации";
                    } else {
                        dateUpgr = elem.getDateUpgrade().toString();
                    }
                    List<Hdd> hddListBySysBlock = hddModelService.getHddListBySysBlock(elem.getId());
                    List<OperationSystem> operationSystemBySystemblock = operationSystemService.getOperationSystemBySystemblock(elem.getId());
                    StringBuilder sbHdd = new StringBuilder();
                    StringBuilder sbOs = new StringBuilder();
                    if(hddListBySysBlock.size() > 0) {
                        int count = 1;
                        for(Hdd el : hddListBySysBlock) {
                                HddDto hddDto = hddMapper.getHddDto(el);
                                sbHdd.append(count + ". Модель: " + hddDto.getModel() + ", ёмкость: " + hddDto.getCapacity() + hddDto.getUnit() + ". ");
                        }
                    } else {
                        sbHdd.append("нет");
                    }
                    
                   if(operationSystemBySystemblock.size() > 0) {
                       int count = 1;
                       for(OperationSystem os : operationSystemBySystemblock) {
                           OperationSystemDto osDto = osMapper.getOperationSystemDto(os);
                           String license;
                           if (osDto.isLicense()) {
                                   license = "да";
                           }else{
                                   license = "нет";
                           }
                           
                           sbOs.append(count + ". ОС: " + osDto.getModel() + ", лицензия: " + license + ". "); 
                       }
                   } else {
                       sbOs.append("нет");
                   }
                    
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    dateUpgr, DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getIpAdress(), sbOs.toString(), elem.getMotherboardModel(), 
                    elem.getCpuModel(), elem.getRamModel(), sbHdd.toString(), locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtSystemBlockDTO) {
                    SvtSystemBlockDTO elem = (SvtSystemBlockDTO)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    String dateUpgr;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    if(null == elem.getDateUpgrade()) {
                        dateUpgr = "без модернизации";
                    } else {
                        dateUpgr = elem.getDateUpgrade().toString();
                    }
                    List<Hdd> hddListBySysBlock = hddModelService.getHddListBySysBlock(elem.getId());
                    List<OperationSystem> operationSystemBySystemblock = operationSystemService.getOperationSystemBySystemblock(elem.getId());
                    StringBuilder sbHdd = new StringBuilder();
                    StringBuilder sbOs = new StringBuilder();
                    if(hddListBySysBlock.size() > 0) {
                        int count = 1;
                        for(Hdd el : hddListBySysBlock) {
                                HddDto hddDto = hddMapper.getHddDto(el);
                                sbHdd.append(count + ". Модель: " + hddDto.getModel() + ", ёмкость: " + hddDto.getCapacity() + hddDto.getUnit() + ". ");
                        }
                    } else {
                        sbHdd.append("нет");
                    }
                    
                   if(operationSystemBySystemblock.size() > 0) {
                       int count = 1;
                       for(OperationSystem os : operationSystemBySystemblock) {
                           OperationSystemDto osDto = osMapper.getOperationSystemDto(os);
                           String license;
                           if (osDto.isLicense()) {
                                   license = "да";
                           }else{
                                   license = "нет";
                           }
                           
                           sbOs.append(count + ". ОС: " + osDto.getModel() + ", лицензия: " + license + ". "); 
                       }
                   } else {
                       sbOs.append("нет");
                   }
                    
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    dateUpgr, DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getIpAdress(), sbOs.toString(), elem.getMotherboardModel(), 
                    elem.getCpuModel(), elem.getRamModel(), sbHdd.toString(), locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportSysBlocks.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportSysBlocks.xlsx");
         return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
     @GetMapping("/monitors")
    public ResponseEntity<Resource> getMonitors(@RequestParam(value="username",required=false) String username, 
             @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
             @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
            } else if(null != serialNumber) {
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
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet spreadsheet = workbook.createSheet("выборка Мониторов");
        XSSFRow row;
        
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска", 
            "По ведомости ОС", "Состояние", "Кабинет", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtDTO) {
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    elem.getBaseType(), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtDTO) {
                    
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    elem.getBaseType(), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportMonitors.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportMonitors.xlsx");
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    @GetMapping("/scanner")
    public ResponseEntity<Resource> getScanners(@RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
        
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
        } else if(null != serialNumber) {
            svtObjectsByStorage = scannerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
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
        
         XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet spreadsheet = workbook.createSheet("выборка Сканеров");
        XSSFRow row;
        
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска", 
            "ip адрес", "Состояние", "Кабинет", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtScannerDTO) {
                    SvtScannerDTO elem = (SvtScannerDTO)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    elem.getIpAdress(), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtScannerDTO) {
                    
                    SvtScannerDTO elem = (SvtScannerDTO)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    elem.getIpAdress(), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportScanners.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportScanners.xlsx");
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    
    
      @GetMapping("/phones")
    public ResponseEntity<Resource> getPhones(@RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber, 
            @RequestParam(value="serialNumber", required = false) String serialNumber, 
            FilterDto dto) throws FileNotFoundException, IOException {
        
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
        } else if(null != serialNumber) {
            svtObjectsByEmployee = phoneService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
        } else {
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
        } else if(null != serialNumber) {
            svtObjectsByStorage = phoneService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
         XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet spreadsheet = workbook.createSheet("выборка телефонов");
        XSSFRow row;
        
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска", 
            "Внутренний номер", "Состояние", "Кабинет", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtDTO) {
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    elem.getPhoneNumber(), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtDTO) {
                    
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    elem.getPhoneNumber(), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportPhones.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportPhones.xlsx");
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    
    }
    
    
    @GetMapping("/server")
    public ResponseEntity<Resource> getServers(@RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
        } else if(null != serialNumber) {
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
        }  else {
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
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet spreadsheet = workbook.createSheet("выборка серверов");
        XSSFRow row;
        
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска", 
            "Дата модернизации", "Состояние", "Кабинет", "ip адрес", "Операционная система", 
            "Процессор", "ОЗУ", "НЖМД", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtServerDTO) {
                    SvtServerDTO elem = (SvtServerDTO)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    String dateUpgr;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    if(null == elem.getDateUpgrade()) {
                        dateUpgr = "без модернизации";
                    } else {
                        dateUpgr = elem.getDateUpgrade().toString();
                    }
                    List<Hdd> hddListBySysBlock = hddModelService.getHddListBySysBlock(elem.getId());
                    List<OperationSystem> operationSystemBySystemblock = operationSystemService.getOperationSystemBySystemblock(elem.getId());
                    StringBuilder sbHdd = new StringBuilder();
                    StringBuilder sbOs = new StringBuilder();
                    if(hddListBySysBlock.size() > 0) {
                        int count = 1;
                        for(Hdd el : hddListBySysBlock) {
                                HddDto hddDto = hddMapper.getHddDto(el);
                                sbHdd.append(count + ". Модель: " + hddDto.getModel() + ", ёмкость: " + hddDto.getCapacity() + hddDto.getUnit() + ". ");
                        }
                    } else {
                        sbHdd.append("нет");
                    }
                    
                   if(operationSystemBySystemblock.size() > 0) {
                       int count = 1;
                       for(OperationSystem os : operationSystemBySystemblock) {
                           OperationSystemDto osDto = osMapper.getOperationSystemDto(os);
                           String license;
                           if (osDto.isLicense()) {
                                   license = "да";
                           }else{
                                   license = "нет";
                           }
                           
                           sbOs.append(count + ". ОС: " + osDto.getModel() + ", лицензия: " + license + ". "); 
                       }
                   } else {
                       sbOs.append("нет");
                   }
                    
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    dateUpgr, DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getIpAdress(), sbOs.toString(),  
                    elem.getCpuModel(), elem.getRamModel(), sbHdd.toString(), locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtSystemBlockDTO) {
                    SvtSystemBlockDTO elem = (SvtSystemBlockDTO)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    String dateUpgr;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    if(null == elem.getDateUpgrade()) {
                        dateUpgr = "без модернизации";
                    } else {
                        dateUpgr = elem.getDateUpgrade().toString();
                    }
                    List<Hdd> hddListBySysBlock = hddModelService.getHddListBySysBlock(elem.getId());
                    List<OperationSystem> operationSystemBySystemblock = operationSystemService.getOperationSystemBySystemblock(elem.getId());
                    StringBuilder sbHdd = new StringBuilder();
                    StringBuilder sbOs = new StringBuilder();
                    if(hddListBySysBlock.size() > 0) {
                        int count = 1;
                        for(Hdd el : hddListBySysBlock) {
                                HddDto hddDto = hddMapper.getHddDto(el);
                                sbHdd.append(count + ". Модель: " + hddDto.getModel() + ", ёмкость: " + hddDto.getCapacity() + hddDto.getUnit() + ". ");
                        }
                    } else {
                        sbHdd.append("нет");
                    }
                    
                   if(operationSystemBySystemblock.size() > 0) {
                       int count = 1;
                       for(OperationSystem os : operationSystemBySystemblock) {
                           OperationSystemDto osDto = osMapper.getOperationSystemDto(os);
                           String license;
                           if (osDto.isLicense()) {
                                   license = "да";
                           }else{
                                   license = "нет";
                           }
                           
                           sbOs.append(count + ". ОС: " + osDto.getModel() + ", лицензия: " + license + ". "); 
                       }
                   } else {
                       sbOs.append("нет");
                   }
                    
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    dateUpgr, DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getIpAdress(), sbOs.toString(),  
                    elem.getCpuModel(), elem.getRamModel(), sbHdd.toString(), locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportServers.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportServers.xlsx");
         return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    
     @GetMapping("/switch")
    public ResponseEntity<Resource> getSwitchHub(@RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
        } else if(null != serialNumber) {
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
        }  else if(null != serialNumber) {
            svtObjectsByStorage = switchHubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
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
        
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("выборка коммутаторов(концентраторов)");
        XSSFRow row;
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Тип устройства", "Количество портов", "Год выпуска", 
             "Состояние", "Кабинет", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtSwitchHubDTO) {
                    SvtSwitchHubDTO elem = (SvtSwitchHubDTO)departmentTreeDto.getDtoes().get(i);
                    String switchHubType;
                    if(elem.getSwitchHubType().equals("SWITCH")) {
                        switchHubType = "коммутатор";
                    } else {
                        switchHubType = "концентратор";
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), switchHubType, String.valueOf(elem.getPortAmount()), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        }
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtSwitchHubDTO) {
                    SvtSwitchHubDTO elem = (SvtSwitchHubDTO)departmentTreeDto.getDtoes().get(j);
                    String switchHubType;
                    if(elem.getSwitchHubType().equals("SWITCH")) {
                        switchHubType = "коммутатор";
                    } else {
                        switchHubType = "концентратор";
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), switchHubType, String.valueOf(elem.getPortAmount()), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportSwitches.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportSwitches.xlsx");
        
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    
    @GetMapping("/router")
    public ResponseEntity<Resource> getRouter(@RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
        }else if(null != serialNumber) {
            svtObjectsByEmployee = routerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        } else {
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
        }else if(null != serialNumber) {
            svtObjectsByStorage = routerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
         XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("выборка коммутаторов(концентраторов)");
        XSSFRow row;
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Количество портов", "Год выпуска", 
             "Состояние", "Кабинет", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtSwitchHubDTO) {
                    SvtSwitchHubDTO elem = (SvtSwitchHubDTO)departmentTreeDto.getDtoes().get(i);
                    
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), String.valueOf(elem.getPortAmount()), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        }
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtSwitchHubDTO) {
                    
                    SvtSwitchHubDTO elem = (SvtSwitchHubDTO)departmentTreeDto.getDtoes().get(j);
                    
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), String.valueOf(elem.getPortAmount()), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportRouters.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportRouters.xlsx");
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    
    
     @GetMapping("/upsforserver")
    public ResponseEntity<Resource> getServerUps(@RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber, 
            @RequestParam(value="serialNumber", required = false) String serialNumber, 
            FilterDto dto) throws FileNotFoundException, IOException {
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
        } else if(null != serialNumber) {
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
        } else if(null != inventaryNumber) {
            svtObjectsByStorage = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if(null != serialNumber) {
            svtObjectsByStorage = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
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
        
         XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet spreadsheet = workbook.createSheet("выборка ИБП");
        XSSFRow row;
        
        Map<String, Object[]> upsData = new TreeMap<String, Object[]>(); 
        
        upsData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска", 
            "Год замены", "Состояние", "Кабинет", "Тип батареи", "Кол-во батарей", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtDTO) {
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    upsData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    String.valueOf(elem.getYearReplacement()), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getBatteryType(), String.valueOf(elem.getBatteryAmount()), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtDTO) {
                    
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    upsData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp, String.valueOf(elem.getYearCreated()),
                    String.valueOf(elem.getYearReplacement()), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getBatteryType(), String.valueOf(elem.getBatteryAmount()), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = upsData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = upsData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportUpsForServer.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportUpsForServer.xlsx");
        
        
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    
     @GetMapping("/ats")
    public ResponseEntity<Resource> getAts(@RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
        } else if(null != serialNumber) {
        svtObjectsByEmployee = atsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        }else {
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
        } else if(null != serialNumber) {
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
        
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("выборка атс");
        XSSFRow row;
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Год выпуска", 
             "Состояние", "Кабинет", "Внешние подключения(тип)", "Количество городских номеров", "Внутренние подключения(ip)", "Внутренние подключения(аналоговые)", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtAtsDTO) {
                    SvtAtsDTO elem = (SvtAtsDTO)departmentTreeDto.getDtoes().get(i);
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getOuterConnectionType(), String.valueOf(elem.getCityNumberAmount()),
                    String.valueOf(elem.getInnerConnectionIp()), String.valueOf(elem.getInnerConnectionAnalog()), locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        }
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtAtsDTO) {
                    SvtAtsDTO elem = (SvtAtsDTO)departmentTreeDto.getDtoes().get(j);
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getOuterConnectionType(), String.valueOf(elem.getCityNumberAmount()),
                    String.valueOf(elem.getInnerConnectionIp()), String.valueOf(elem.getInnerConnectionAnalog()), locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportAts.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportAts.xlsx");
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    
     @GetMapping("/conditioner")
    public ResponseEntity<Resource> getConditioner(@RequestParam(value="username", required=false) String username,
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
        } else if(null != serialNumber) {
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
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("выборка атс");
        XSSFRow row;
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Год выпуска", 
             "Состояние", "Кабинет", "Тип", "Сплит система", "Зимний комплект", "Помпа", "Балансовая стоимость", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtConditionerDTO) {
                    SvtConditionerDTO elem = (SvtConditionerDTO)departmentTreeDto.getDtoes().get(i);
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), DocReportController.conditionerTypeToRus(elem.getConditionerType()), DocReportController.booleanToRus(elem.isSplitSystem()), DocReportController.booleanToRus(elem.isWinterKit()),
                    DocReportController.booleanToRus(elem.isHavePomp()), String.valueOf(elem.getPrice()), locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        }
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtConditionerDTO) {
                    SvtConditionerDTO elem = (SvtConditionerDTO)departmentTreeDto.getDtoes().get(j);
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), DocReportController.conditionerTypeToRus(elem.getConditionerType()), DocReportController.booleanToRus(elem.isSplitSystem()), DocReportController.booleanToRus(elem.isWinterKit()),
                    DocReportController.booleanToRus(elem.isHavePomp()), String.valueOf(elem.getPrice()), locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportConditioners.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportConditioners.xlsx");
        
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
   
    
      @GetMapping("/fax")
    public ResponseEntity<Resource> getFax(@RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
        } else if(null != serialNumber) {
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
        } else if(null != serialNumber) {
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
        
         XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet spreadsheet = workbook.createSheet("выборка факсов");
        XSSFRow row;
        
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Год выпуска", 
            "Состояние", "ip адрес", "Кабинет", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof FaxDto) {
                    FaxDto elem = (FaxDto)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getIpAdress(), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof FaxDto) {
                    
                    FaxDto elem = (FaxDto)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getIpAdress(), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportFax.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportFax.xlsx");
        
        
           return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
    
    
    @GetMapping("/infomat")
    public ResponseEntity<Resource> getInfomat(@RequestParam(value="username", required = false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
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
        }else if(null != serialNumber) {
        svtObjectsByEmployee = infomatService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        }  else {
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
        }else if(null != serialNumber) {
        svtObjectsByStorage = infomatService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
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
        
    
        
        
           XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("выборка инфоматов");
        XSSFRow row;
        Map<String, Object[]> rowData = new TreeMap<String, Object[]>(); 
        rowData.put("1", new Object[] {"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС", 
            "Серийный номер", "Инвентарный номер", "Год выпуска", 
            "Состояние", "Кабинет", "Район"} );
        int p = 2;
         for(LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                if(departmentTreeDto.getDtoes().get(i) instanceof SvtDTO) {
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(i);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        for(LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for(DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
            for(int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                if(departmentTreeDto.getDtoes().get(j) instanceof SvtDTO) {
                    
                    SvtDTO elem = (SvtDTO)departmentTreeDto.getDtoes().get(j);
                    String dateExp;
                    if(null == elem.getDateExploitationBegin()) {
                        dateExp = "без даты";
                    } else {
                        dateExp = elem.getDateExploitationBegin().toString();
                    }
                    rowData.put(String.valueOf(p), new Object[] {elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                    elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                    DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), 
                    locationByTreeDto.getLocationName()});
                }
                p++;
            }
        }
        
        }
        
        Set<String> keyid = rowData.keySet(); 
        int rowid = 0;
        
        for(String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            
            Object[] objectArr = rowData.get(key);
            int cellid = 0;
            
            for(Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        
        File file = new File("./reports/docReportInfomat.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders(); 
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportInfomat.xlsx");
        
        return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    
        
        
    }
    
     public static String conditionerTypeToRus(String input) {
        String result = "";
        switch (input) {
            case "WINDOW":
                result = "оконный";
                break;
            case "WALL":
                result = "настенный";
                break;
            case "CEILING":
                result = "потолочный";
                break;
            case "FLOOR":
                result = "напольный";
                break;
            default:
                result = "не указан";
                break;
        }
        return result;
    }
    
    public static String booleanToRus(boolean input) {
        String result;
        if(input) {
            result = "да";
        } else {
            result = "нет";
        }
        return result;
    }
    
    public static String getStatusToRus(String input) {
        String result = "";
        if(null == input) {
            input = "не указан";
        }
        switch (input) {
            case "OK":
               result = "Исправен";
               break;
            case "DEFECTIVE":
                result = "Неисправен";
                break;
            case "UTILIZATION":
                result = "Утилизирован";
                break;
            case "MONITORING":
                result = "Списание";
                break;
            case "REPAIR":
                result = "Ремонт";
                break;
            default:
                result = "не указан";
                break;
        }
        return result;
    }
    
    public static String getPlaceTypeRus(String input) {
        String result = "";
        switch (input) {
            case "SERVERROOM":
                result = "Серверная";
                break;
            case "OFFICEEQUIPMENT":
                result = "Оргтехника";
                break;
            case "STORAGE":
                result = "Склад";
                break;
            case "EMPLOYEE":
                result = "Сотрудник";
                break;
        }
        
        return result;
    }
    
    }

