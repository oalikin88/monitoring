package ru.gov.sfr.aos.monitoring.asuo.terminal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import ru.gov.sfr.aos.monitoring.place.PlaceType;

/**
 *
 * @author Alikin Oleg
 */
@RestController
@RequestMapping("/get-doc")
public class TerminalDocController {

    @Autowired
    private TerminalService terminalService;
    @Autowired
    private TerminalOutDtoTreeService terminalOutDtoTreeService;

    @GetMapping("/terminal")
    public ResponseEntity<Resource> getTerminal(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber
    ) throws FileNotFoundException, IOException {
        Map<Location, List<Terminal>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = terminalService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != inventaryNumber) {
            svtObjectsByEmployee = terminalService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = terminalService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Terminal>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = terminalService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != inventaryNumber) {
            svtObjectsByStorage = terminalService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = terminalService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet("Лист1");
        XSSFRow row;

        Map<String, Object[]> rowData = new TreeMap<String, Object[]>();

        rowData.put("1", new Object[]{"Модель", "ФИО", "Тип рабочего места",
            "Серийный номер", "Инвентарный номер", "Состояние", "Район"});
        int p = 2;
        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                    if (departmentTreeDto.getDtoes().get(i) instanceof TerminalDto) {
                        TerminalDto elem = (TerminalDto) departmentTreeDto.getDtoes().get(i);

                        rowData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()),
                            elem.getSerialNumber(), elem.getInventaryNumber(), DocReportController.getStatusToRus(elem.getStatus()), locationByTreeDto.getLocationName()});
                    }
                    p++;
                }
            }

        }

        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                    if (departmentTreeDto.getDtoes().get(j) instanceof TerminalDto) {

                        TerminalDto elem = (TerminalDto) departmentTreeDto.getDtoes().get(j);

                        rowData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()),
                            elem.getSerialNumber(), elem.getInventaryNumber(), DocReportController.getStatusToRus(elem.getStatus()), locationByTreeDto.getLocationName()});
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

        File file = new File("./reports/docReportTerminals.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportTerminals.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }
}
