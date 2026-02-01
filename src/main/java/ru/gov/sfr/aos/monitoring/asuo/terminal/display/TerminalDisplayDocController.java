package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

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
public class TerminalDisplayDocController {

    @Autowired
    private TerminalDisplayService terminalDisplayService;
    @Autowired
    private TerminalDisplayOutDtoTreeService terminalDisplayOutDtoTreeService;

    @GetMapping("/terminal-display")
    public ResponseEntity<Resource> getTerminalDisplay(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "serialNumber", required = false) String serialNumber) throws FileNotFoundException, IOException {
        Map<Location, List<TerminalDisplay>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = terminalDisplayService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = terminalDisplayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalDisplayService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalDisplayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalDisplay>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalDisplayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet("Лист1");
        XSSFRow row;

        Map<String, Object[]> upsData = new TreeMap<String, Object[]>();

        upsData.put("1", new Object[]{"Модель", "ФИО", "Тип рабочего места",
            "Серийный номер", "Диагональ", "Состояние", "Район"});

        int p = 2;
        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                    if (departmentTreeDto.getDtoes().get(i) instanceof TerminalDisplayDto) {
                        TerminalDisplayDto elem = (TerminalDisplayDto) departmentTreeDto.getDtoes().get(i);

                        upsData.put(String.valueOf(p), new Object[]{elem.getManufacturer() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()),
                            elem.getSerialNumber(), elem.getScreenDiagonal(), DocReportController.getStatusToRus(elem.getStatus()), locationByTreeDto.getLocationName()});
                    }
                    p++;
                }
            }

        }

        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                    if (departmentTreeDto.getDtoes().get(j) instanceof TerminalDisplayDto) {

                        TerminalDisplayDto elem = (TerminalDisplayDto) departmentTreeDto.getDtoes().get(j);

                        upsData.put(String.valueOf(p), new Object[]{elem.getManufacturer() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()),
                            elem.getSerialNumber(), elem.getScreenDiagonal(), DocReportController.getStatusToRus(elem.getStatus()), locationByTreeDto.getLocationName()});
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

        File file = new File("./reports/docReportTerminalDisplay.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportTerminalDisplay.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
