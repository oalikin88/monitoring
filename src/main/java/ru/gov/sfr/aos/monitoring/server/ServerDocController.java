package ru.gov.sfr.aos.monitoring.server;

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
import ru.gov.sfr.aos.monitoring.systemblock.SvtSystemBlockDTO;

/**
 *
 * @author Alikin Oleg
 */
@RestController
@RequestMapping("/get-doc")
public class ServerDocController {

    @Autowired
    private ServerService serverService;
    @Autowired
    private ServerOutDtoTreeService serverOutDtoTreeService;
    @Autowired
    private ServerMapper serverMapper;
    @Autowired
    private HddModelService hddModelService;
    @Autowired
    private OperationSystemService operationSystemService;
    @Autowired
    private HddMapper hddMapper;
    @Autowired
    private OperationSystemMapper osMapper;

    @GetMapping("/server")
    public ResponseEntity<Resource> getServers(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            FilterDto dto) throws FileNotFoundException, IOException {
        Map<Location, List<Server>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Server>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = serverService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = serverService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = serverService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
            } else {
                svtObjectsByEmployee = serverService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
            }
            treeSvtDtoByEmployee = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = serverService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = serverService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = serverService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = serverService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }
            treeSvtDtoByStorage = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
        } else {

            List<Server> serverByFilter = serverService.getServerByFilter(dto);
            filter = new ArrayList<>();
            for (Server p : serverByFilter) {
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

        rowData.put("1", new Object[]{"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС",
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска",
            "Дата модернизации", "Состояние", "Кабинет", "ip адрес", "Операционная система",
            "Процессор", "ОЗУ", "НЖМД", "Район"});
        int p = 2;
        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByEmployee) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int i = 0; i < departmentTreeDto.getDtoes().size(); i++) {
                    if (departmentTreeDto.getDtoes().get(i) instanceof SvtServerDTO) {
                        SvtServerDTO elem = (SvtServerDTO) departmentTreeDto.getDtoes().get(i);
                        String dateExp;
                        String dateUpgr;
                        if (null == elem.getDateExploitationBegin()) {
                            dateExp = "без даты";
                        } else {
                            dateExp = elem.getDateExploitationBegin().toString();
                        }
                        if (null == elem.getDateUpgrade()) {
                            dateUpgr = "без модернизации";
                        } else {
                            dateUpgr = elem.getDateUpgrade().toString();
                        }
                        List<Hdd> hddListBySysBlock = hddModelService.getHddListBySysBlock(elem.getId());
                        List<OperationSystem> operationSystemBySystemblock = operationSystemService.getOperationSystemBySystemblock(elem.getId());
                        StringBuilder sbHdd = new StringBuilder();
                        StringBuilder sbOs = new StringBuilder();
                        if (hddListBySysBlock.size() > 0) {
                            int count = 1;
                            for (Hdd el : hddListBySysBlock) {
                                HddDto hddDto = hddMapper.getHddDto(el);
                                sbHdd.append(count + ". Модель: " + hddDto.getModel() + ", ёмкость: " + hddDto.getCapacity() + hddDto.getUnit() + ". ");
                            }
                        } else {
                            sbHdd.append("нет");
                        }

                        if (operationSystemBySystemblock.size() > 0) {
                            int count = 1;
                            for (OperationSystem os : operationSystemBySystemblock) {
                                OperationSystemDto osDto = osMapper.getOperationSystemDto(os);
                                String license;
                                if (osDto.isLicense()) {
                                    license = "да";
                                } else {
                                    license = "нет";
                                }

                                sbOs.append(count + ". ОС: " + osDto.getModel() + ", лицензия: " + license + ". ");
                            }
                        } else {
                            sbOs.append("нет");
                        }

                        rowData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
                            elem.getSerialNumber(), elem.getInventaryNumber(), dateExp.toString(), String.valueOf(elem.getYearCreated()),
                            dateUpgr, DocReportController.getStatusToRus(elem.getStatus()), elem.getNumberRoom(), elem.getIpAdress(), sbOs.toString(),
                            elem.getCpuModel(), elem.getRamModel(), sbHdd.toString(), locationByTreeDto.getLocationName()});
                    }
                    p++;
                }
            }

        }

        for (LocationByTreeDto locationByTreeDto : treeSvtDtoByStorage) {
            for (DepartmentTreeDto departmentTreeDto : locationByTreeDto.getDepartments()) {
                for (int j = 0; j < departmentTreeDto.getDtoes().size(); j++) {
                    if (departmentTreeDto.getDtoes().get(j) instanceof SvtSystemBlockDTO) {
                        SvtSystemBlockDTO elem = (SvtSystemBlockDTO) departmentTreeDto.getDtoes().get(j);
                        String dateExp;
                        String dateUpgr;
                        if (null == elem.getDateExploitationBegin()) {
                            dateExp = "без даты";
                        } else {
                            dateExp = elem.getDateExploitationBegin().toString();
                        }
                        if (null == elem.getDateUpgrade()) {
                            dateUpgr = "без модернизации";
                        } else {
                            dateUpgr = elem.getDateUpgrade().toString();
                        }
                        List<Hdd> hddListBySysBlock = hddModelService.getHddListBySysBlock(elem.getId());
                        List<OperationSystem> operationSystemBySystemblock = operationSystemService.getOperationSystemBySystemblock(elem.getId());
                        StringBuilder sbHdd = new StringBuilder();
                        StringBuilder sbOs = new StringBuilder();
                        if (hddListBySysBlock.size() > 0) {
                            int count = 1;
                            for (Hdd el : hddListBySysBlock) {
                                HddDto hddDto = hddMapper.getHddDto(el);
                                sbHdd.append(count + ". Модель: " + hddDto.getModel() + ", ёмкость: " + hddDto.getCapacity() + hddDto.getUnit() + ". ");
                            }
                        } else {
                            sbHdd.append("нет");
                        }

                        if (operationSystemBySystemblock.size() > 0) {
                            int count = 1;
                            for (OperationSystem os : operationSystemBySystemblock) {
                                OperationSystemDto osDto = osMapper.getOperationSystemDto(os);
                                String license;
                                if (osDto.isLicense()) {
                                    license = "да";
                                } else {
                                    license = "нет";
                                }

                                sbOs.append(count + ". ОС: " + osDto.getModel() + ", лицензия: " + license + ". ");
                            }
                        } else {
                            sbOs.append("нет");
                        }

                        rowData.put(String.valueOf(p), new Object[]{elem.getManufacturerName() + " " + elem.getModel(), elem.getPlaceName(), DocReportController.getPlaceTypeRus(elem.getPlaceType()), elem.getNameFromOneC(),
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

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);

            Object[] objectArr = rowData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
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

}
