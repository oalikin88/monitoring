package ru.gov.sfr.aos.monitoring.monitor;

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
import ru.gov.sfr.aos.monitoring.department.DepartmentTreeDto;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;

/**
 *
 * @author Alikin Oleg
 */
@RestController
@RequestMapping("/get-doc")
public class MonitorDocController {

    @Autowired
    private MonitorService monitorService;
    @Autowired
    private MonitorOutDtoTreeService monitorOutDtoTreeService;
    @Autowired
    private MonitorMapper monitorMapper;

    @GetMapping("/monitors")
    public ResponseEntity<Resource> getMonitors(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
        Map<Location, List<Monitor>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Monitor>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = monitorService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = monitorService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = monitorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
            } else {
                svtObjectsByEmployee = monitorService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
            treeSvtDtoByEmployee = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
            if (null != username) {
                svtObjectsByStorage = monitorService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = monitorService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
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
            for (Monitor p : monitorByFilter) {
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

        rowData.put("1", new Object[]{"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС",
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска",
            "По ведомости ОС", "Состояние", "Кабинет", "Район"});
        int p = 2;
        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                    if (departmentTreeDto.getDtoes().get(i) instanceof SvtDTO) {
                        SvtDTO elem = (SvtDTO) departmentTreeDto.getDtoes().get(i);
                        String dateExp;
                        if (null == elem.getDateExploitationBegin()) {
                            dateExp = "без даты";
                        } else {
                            dateExp = elem.getDateExploitationBegin().toString();
                        }
                        rowData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                            elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                            elem.getBaseType(), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(),
                            locationByTreeDto.getLocationName()});
                    }
                    p++;
                }
            }

        }

        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                    if (departmentTreeDto.getDtoes().get(j) instanceof SvtDTO) {

                        SvtDTO elem = (SvtDTO) departmentTreeDto.getDtoes().get(j);
                        String dateExp;
                        if (null == elem.getDateExploitationBegin()) {
                            dateExp = "без даты";
                        } else {
                            dateExp = elem.getDateExploitationBegin().toString();
                        }
                        rowData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
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

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);

            Object[] objectArr = rowData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
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
}
