package ru.gov.sfr.aos.monitoring.infomat;

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
import ru.gov.sfr.aos.monitoring.controllers.DocReportController;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.department.DepartmentTreeDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;

/**
 *
 * @author Alikin Oleg
 */
@RestController
@RequestMapping("/get-doc")
public class InfomatDocController {
    
    @Autowired
    private InfomatService infomatService;
    @Autowired
    private InfomatOutDtoTreeService infomatOutDtoTreeService;
    @Autowired
    private InfomatMapper infomatMapper;
    
    
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

}
