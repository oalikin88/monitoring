/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ListenerOperationRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;
import ru.gov.sfr.aos.reports.parameters.ActsByPeriodParameters;

/**
 *
 * @author 041AlikinOS
 */
@Component
@ReportAttributes(value = "actsByPeriod", templateFilename = "aos/act3.xlsx")
@RequiredArgsConstructor
public class ActsByLocationReport implements Report<ActsByPeriodParameters> {

    
    @Autowired
    private ListenerOperationRepo listenerOperationRepo;
    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private FallbackEmployeeClient employeeClient;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    @Override
    public Request proceed(ActsByPeriodParameters params, String uid) throws ReportException {

         List<ListenerOperation> findActs = null;
        if(null != params.getEndDate()) {
            findActs = listenerOperationRepo.findByLocationAndPeriod(params.getLocation(), params.getStartDate(), params.getEndDate());
        } else {
            findActs = listenerOperationRepo.findByLocationAndDate(params.getLocation(), params.getStartDate());
        }
        
        Block.BlockBuilder footer = Block.builder();

        try {
            DictionaryEmployee employeeToDoWork = employeeClient.getEmployeeByCode(params.getEmployee());
            String inputNameEmployeeToDoWorkPost = employeeToDoWork.getOrganization().getName();
            String outNameEmployeeToDoWorkPost = "";
            String[] splitName = inputNameEmployeeToDoWorkPost.split(" ");
            for(String s : splitName) {
                 if(s.equals("и")) {
                    outNameEmployeeToDoWorkPost += s.toLowerCase();
                } else {
                    outNameEmployeeToDoWorkPost += s.substring(0, 1).toUpperCase();
                } 

            }
            footer
                    .parameter(new Parameter("employeeToDoWork", employeeToDoWork.getSurname() + " " + employeeToDoWork.getName().substring(0, 1) + ". " + employeeToDoWork.getMiddlename().substring(0, 1) + "."))
                    .parameter(new Parameter("employeeToDoWorkPost", employeeToDoWork.getPost().getName() + " " 
                            + outNameEmployeeToDoWorkPost));
        } catch (Exception ex) {
            footer
                    .parameter(new Parameter("employeeToDoWork", params.getEmployee()))
                    .parameter(new Parameter("employeeToDoWorkPost", ""));
        }

        try {
            DictionaryEmployee employeeMOL = employeeClient.getEmployeeByCode(params.getMol());
            String inputNameEmployeeMOL = employeeMOL.getOrganization().getName();
            String outNameEmployeeMOL = "";
            String[] splitName = inputNameEmployeeMOL.split(" ");
            for(String s : splitName) {
                 if(s.equals("и")) {
                    outNameEmployeeMOL += s.toLowerCase();
                } else {
                    outNameEmployeeMOL += s.substring(0, 1).toUpperCase();
                } 
 
            }
            footer
                    .parameter(new Parameter("employeeMol", employeeMOL.getSurname() + " " + employeeMOL.getName().substring(0, 1) + ". " + employeeMOL.getMiddlename().substring(0, 1) + "."))
                    .parameter(new Parameter("employeeMOLPost", employeeMOL.getPost().getName() + " " + outNameEmployeeMOL));
        } catch (Exception ex) {
            footer
                    .parameter(new Parameter("employeeMol", params.getMol()))
                    .parameter(new Parameter("employeeMOLPost", ""));
        }
  
        int count = 1;
        List<Row> rows = new ArrayList<>();
        for(ListenerOperation listener : findActs) {
             Cartridge cartridge = cartridgeRepo.findById(listener.getCartridgeID()).orElseThrow(() -> new ReportException("Картридж не найден!"));
             Printer printer = printerRepo.findByPrinterId(listener.getPrinterID());
                
            String namePrinterFromOneC;
            if(null != printer.getNameFromOneC()) {
               namePrinterFromOneC = printer.getNameFromOneC().getName();
            } else {
                namePrinterFromOneC = "";
            }
        Row.RowBuilder row = Row.builder()
          
                .name("num")
                .parameter(new Parameter("count", count))
                .parameter(new Parameter("kod", cartridge.getItemCode()))
                .parameter(new Parameter("cartName", cartridge.getNameMaterial()))
                .parameter(new Parameter("date", cartridge.getDateStartExploitation().format(formatter)))
                .parameter(new Parameter("printerName", namePrinterFromOneC))
                .parameter(new Parameter("printerNumber", printer.getInventoryNumber()));
        try {
            DictionaryEmployee employeeToSetDevice = employeeClient.getEmployeeByCode(listener.getEmployeeToSetDevice());
            row
                    .parameter(new Parameter("workerName", employeeToSetDevice.getSurname() + " " + employeeToSetDevice.getName().substring(0, 1) + ". " + employeeToSetDevice.getMiddlename().substring(0, 1) + "."))
                    .parameter(new Parameter("workerPost", employeeToSetDevice.getPost().getName()));
        } catch (Exception ex) {
            row
                    .parameter(new Parameter("workerName", listener.getEmployeeToSetDevice() + "(Сотрудник уволен)"))
                    .parameter(new Parameter("workerPost", ""));
        }
        
        rows.add(row.build());
        count++;
        
        }
        Block dataBlock = Block.builder().name("dataBlock").rows(rows).build();
        Record record = Record.builder().block(dataBlock).build();
        return Request.builder().mainBlock(footer.build()).record(record).build();
    }
        
    }
    
    
    

