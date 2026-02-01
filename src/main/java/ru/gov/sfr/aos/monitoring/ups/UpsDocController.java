package ru.gov.sfr.aos.monitoring.ups;

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

import ru.gov.sfr.aos.monitoring.ats.SvtAtsDTO;
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
public class UpsDocController {

    @Autowired
    private UpsService upsService;
    @Autowired
    private UpsOutDtoTreeService upsOutDtoTreeService;
    @Autowired
    private UpsMapper upsMapper;

    @GetMapping("/get-ups")
    public ResponseEntity<Resource> getUpsDoc(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            FilterDto dto) throws IOException {
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
            } else {
                svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
            treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
            if (null != username) {
                svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
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
            for (Ups p : upsByFilter) {
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

        upsData.put("1", new Object[]{"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС",
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска",
            "Год замены", "Состояние", "Кабинет", "Тип батареи", "Кол-во батарей", "Район"});
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
                        upsData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                            elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                            String.valueOf(elem.getYearReplacement()), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getBatteryType(), String.valueOf(elem.getBatteryAmount()),
                            locationByTreeDto.getLocationName()});
                    }
                    p++;
                }
            }

        }

        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                    if (departmentTreeDto.getDtoes().get(j) instanceof SvtAtsDTO) {

                        SvtDTO elem = (SvtDTO) departmentTreeDto.getDtoes().get(j);
                        String dateExp;
                        if (null == elem.getDateExploitationBegin()) {
                            dateExp = "без даты";
                        } else {
                            dateExp = elem.getDateExploitationBegin().toString();
                        }
                        upsData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
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

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);

            Object[] objectArr = upsData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
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

    @GetMapping("/upsforserver")
    public ResponseEntity<Resource> getServerUps(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
            } else {
                svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
            }
            treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
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
            for (Ups p : upsByFilter) {
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

        upsData.put("1", new Object[]{"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС",
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска",
            "Год замены", "Состояние", "Кабинет", "Тип батареи", "Кол-во батарей", "Район"});
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
                        upsData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                            elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                            String.valueOf(elem.getYearReplacement()), DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getBatteryType(), String.valueOf(elem.getBatteryAmount()),
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
                        upsData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
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

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);

            Object[] objectArr = upsData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
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

}
