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
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.models.DeviceDto;
import ru.gov.sfr.aos.monitoring.place.PlaceDTO;
import ru.gov.sfr.aos.monitoring.location.LocationService;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingServiceImpl;
import ru.gov.sfr.aos.monitoring.place.PlaceService;
import ru.gov.sfr.aos.monitoring.services.RegularOperation;

/**
 *
 * @author Alikin Oleg
 */
@RestController
@RequestMapping("/get-doc")
public class DocReportController {

    @Autowired
    private ObjectBuingServiceImpl objectBuingServiceImpl;
    @Autowired
    private PlaceService placeService;

    @Autowired
    private LocationService locationService;

    @GetMapping("/all-devicespr")
    public ResponseEntity<Resource> getAllDevicesByPlace(Long id) throws FileNotFoundException, IOException {
        List<DeviceDto> allDevicesByPlaceId = objectBuingServiceImpl.getAllDevicesByPlaceId(id);
        PlaceDTO placeById = placeService.getPlaceById(id);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Лист1");
        Row row1 = sheet.createRow(0);

        row1.createCell(0).setCellValue("Список оборудования по сотруднику: " + placeById.getUsername() + " (" + placeById.getPlaceType() + ")");  // Ячейка A1 со словом «Привет»
        //row1.createCell(1).setCellValue("World");  // И ячейка B1 с изложением «Мир»

        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("Модель");
        row2.createCell(1).setCellValue("Инвентарный номер");

        Iterator<DeviceDto> iterator = allDevicesByPlaceId.iterator();
        String deviceType = "";
        int count = 2;
        while (iterator.hasNext()) {
            DeviceDto elem = iterator.next();
            if (deviceType.equals("") || !elem.getDeviceType().toString().equals(deviceType)) {
                Row rowLabel = sheet.createRow(count);
                rowLabel.createCell(0).setCellValue(RegularOperation.getDeviceTypeRus(elem.getDeviceType()));
                deviceType = elem.getDeviceType().toString();
                count++;
            }
            Row rowN = sheet.createRow(count);
            rowN.createCell(0).setCellValue(elem.getModel());
            rowN.createCell(1).setCellValue(elem.getInventaryNumber());
            count++;
        }

        File file = new File("./reports/docReportDeviceByPerson.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; docReportDeviceByPerson.xlsx");
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
        if (input) {
            result = "да";
        } else {
            result = "нет";
        }
        return result;
    }

    public static String getStatusToRus(String input) {
        String result = "";
        if (null == input) {
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
