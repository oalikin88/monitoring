/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import ru.gov.sfr.aos.reports.parameters.PrinterListReportParameters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.opfr.doccreatormodels.Block;
import org.opfr.doccreatormodels.Parameter;
import org.opfr.doccreatormodels.Record;
import org.opfr.doccreatormodels.Request;
import org.opfr.doccreatormodels.Row;
import org.opfr.springBootStarterDictionary.fallback.FallbackEmployeeClient;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;
import org.opfr.starter.report.annotations.ReportAttributes;
import org.opfr.starter.report.exception.ReportException;
import org.opfr.starter.report.interfaces.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.repositories.ListenerOperationRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
@ReportAttributes(value = "printersList", templateFilename = "aos/printers.xlsx")
@RequiredArgsConstructor
public class PrintersList implements Report<PrinterListReportParameters> {
    @Autowired
    private FallbackEmployeeClient employeeClient;
    @Autowired
    private ListenerOperationRepo listenerOperationRepo;
    @Autowired
    private PrinterRepo printerRepo;

    @Override
    public Request proceed(PrinterListReportParameters params, String uid) throws ReportException {
        
        List<Printer> findPrinters = null;
        if(null == params.getDeviceType()) {
            findPrinters = printerRepo.findByModelIdAndLocationId(params.getIdModel(), params.getIdLocation());
        }else if(params.getDeviceType().equals("ALL")) {
            findPrinters = printerRepo.findByLocation(params.getIdLocation());
        } else {
            findPrinters = printerRepo.findByLocationAndDeviceType(params.getIdLocation(), params.getDeviceType());
        } 
          List<Row> rows = new ArrayList<>();
          Block blo = null;
          int count = 1;
          try {
               for(Printer p : findPrinters) {
                   Row.RowBuilder row = Row.builder();
                    List<ListenerOperation> findByPrinterIdLastDayCartridgeInstall = listenerOperationRepo.findByPrinterIdLastDayCartridgeInstall(p.getId());
                    String employee = null;
                if(!findByPrinterIdLastDayCartridgeInstall.isEmpty()) {
                    
                        String employeeToSetDevice = findByPrinterIdLastDayCartridgeInstall.get(0).getEmployeeToSetDevice();
                        DictionaryEmployee employeeByCode = employeeClient.getEmployeeByCode(employeeToSetDevice);
                       employee = employeeByCode.getSurname() + " " + employeeByCode.getName().substring(0, 1) + ". " + employeeByCode.getMiddlename().substring(0, 1) + ".";
                    
                } else {
                    employee = "Отсутствует";
                }
                row
                        .name("num")
                        .parameter(new Parameter("count", count))
                        .parameter(new Parameter("model", p.getModel().getManufacturer().getName() + " " + p.getModel().getName()))
                        .parameter(new Parameter("inventaryNumber", p.getInventoryNumber()))
                        .parameter(new Parameter("serialNumber", p.getSerialNumber()))
                        .parameter(new Parameter("format", p.getModel().getPrintFormatType().toString()))
                        .parameter(new Parameter("printSpeed", p.getModel().getPrintSpeed()))
                        .parameter(new Parameter("printColor", p.getModel().getPrintColorType().getType()))
                        .parameter(new Parameter("printerStatus", p.getPrinterStatus().getStatus()))
                        .parameter(new Parameter("employee", employee));
               rows.add(row.build());
                
                count++;
                
                
        }
        
          }catch(Exception e) {
              e.printStackTrace();
          }
          Block.BlockBuilder footer = Block.builder();
          footer
                  .parameter(new Parameter("location", findPrinters.get(0).getPlace().getLocation().getName()));
          blo = Block.builder().name("dataBlock").rows(rows).build();
                Record rec = Record.builder().block(blo).build();
                Request.builder().record(rec).build();
//       Block dataBlock = Block.builder().name("dataBlock").row(row.build()).build();
//        Record record = Record.builder().block(dataBlock).build();
        return Request.builder().mainBlock(footer.build()).record(rec).build();
    }


    
   
    
}
