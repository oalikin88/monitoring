package ru.gov.sfr.aos.monitoring.phone;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.department.DepartmentTreeDto;
import ru.gov.sfr.aos.monitoring.interfaces.ExcelServiceInterface;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;

/**
 *
 * @author oalikin88
 */
@Service
public class ExcelPhoneService implements ExcelServiceInterface {

    @Override
    public byte[] buildReport(List<LocationByTreeDto> employeeTree,
           List<LocationByTreeDto> storageTree, String name) throws IOException {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(name);
            createHeader(sheet);
            int rowIndex = 1;
            rowIndex = fillData(sheet, employeeTree, rowIndex);
            fillData(sheet, storageTree, rowIndex);
            workbook.write(out);
            return out.toByteArray();
        }
    }

    private void createHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        
        String[] headers = new String[]{"Модель", "ФИО", "Тип рабочего места", "Наменование в ведомости ОС",
            "Серийный номер", "Инвентарный номер", "Дата ввода в экспл", "Год выпуска",
            "Внутренний номер", "Состояние", "Кабинет", "Район"};

        Workbook workbook = sheet.getWorkbook();
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        
        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }

    }
    
    private int fillData(Sheet sheet, List<LocationByTreeDto> tree, int rowIndex) {
          for (LocationByTreeDto location : tree) {
        for (DepartmentTreeDto department : location.getDepartments()) {
            for (Object dto : department.getDtoes()) {
                if (dto instanceof SvtDTO elem) {

                    Row row = sheet.createRow(rowIndex++);

                    row.createCell(0).setCellValue(elem.getManufacturerName() + " " + elem.getModel());
                    row.createCell(1).setCellValue(elem.getPlaceName());
                    row.createCell(2).setCellValue(elem.getPlaceType().toString());
                    row.createCell(3).setCellValue(elem.getNameFromOneC());
                    row.createCell(4).setCellValue(elem.getSerialNumber());
                    row.createCell(5).setCellValue(elem.getInventaryNumber());

                    String date = elem.getDateExploitationBegin() == null
                            ? "без даты"
                            : elem.getDateExploitationBegin().toString();

                    row.createCell(6).setCellValue(date);
                    row.createCell(7).setCellValue(String.valueOf(elem.getYearCreated()));
                    row.createCell(8).setCellValue(elem.getPhoneNumber());
                    row.createCell(9).setCellValue(elem.getStatus().toString());
                    row.createCell(10).setCellValue(elem.getNumberRoom());
                    row.createCell(11).setCellValue(elem.getLocation());
                }
            }
        }
    }

    return rowIndex;
        
        
    }

}
