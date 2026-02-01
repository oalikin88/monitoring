package ru.gov.sfr.aos.monitoring.systemblock;

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

import ru.gov.sfr.aos.monitoring.components.Hdd;
import ru.gov.sfr.aos.monitoring.components.HddDto;
import ru.gov.sfr.aos.monitoring.components.HddMapper;
import ru.gov.sfr.aos.monitoring.components.HddModelService;
import ru.gov.sfr.aos.monitoring.components.OperationSystem;
import ru.gov.sfr.aos.monitoring.components.OperationSystemMapper;
import ru.gov.sfr.aos.monitoring.controllers.DocReportController;
import ru.gov.sfr.aos.monitoring.department.DepartmentTreeDto;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.services.OperationSystemService;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;

/**
 *
 * @author Alikin Oleg
 */
@RestController
@RequestMapping("/get-doc")
public class SystemblockDocController {
    
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

}
