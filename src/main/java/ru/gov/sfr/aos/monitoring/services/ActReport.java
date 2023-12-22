/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.opfr.doccreatormodels.Block;
import org.opfr.doccreatormodels.Parameter;
import org.opfr.doccreatormodels.Record;
import org.opfr.doccreatormodels.Request;
import org.opfr.doccreatormodels.Row;
import org.opfr.springBootStarterDictionary.exceptions.EmployeeNotFoundException;
import org.opfr.springBootStarterDictionary.fallback.FallbackEmployeeClient;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;
import org.opfr.starter.report.annotations.ReportAttributes;
import org.opfr.starter.report.exception.ReportException;
import org.opfr.starter.report.interfaces.Report;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ListenerOperationRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;
import ru.gov.sfr.aos.reports.parameters.ActReportParameters;

/**
 *
 * @author 041AlikinOS
 */
@Component
@ReportAttributes(value = "actReport", templateFilename = "aos/act.xlsx")
@RequiredArgsConstructor
public class ActReport implements Report<ActReportParameters> {

    private final ListenerOperationRepo listenerOperationRepo;
    private final CartridgeRepo cartridgeRepo;
    private final PrinterRepo printerRepo;
    private final FallbackEmployeeClient employeeClient;

    @Override
    public Request proceed(ActReportParameters params, String uid) throws ReportException {

        ListenerOperation listener = listenerOperationRepo.findById(params.getId()).orElseThrow(() -> new ReportException("Запись не найдена"));
        Block.BlockBuilder footer = Block.builder();
        try {
            DictionaryEmployee employeeToSetDevice = employeeClient.getEmployeeByCode(listener.getEmployeeToSetDevice());
            footer
                    .parameter(new Parameter("workerName", employeeToSetDevice.getSurname() + " " + employeeToSetDevice.getName().substring(0, 1) + ". " + employeeToSetDevice.getMiddlename().substring(0, 1) + "."))
                    .parameter(new Parameter("workerPost", employeeToSetDevice.getPost().getName()));
        } catch (EmployeeNotFoundException ex) {
            footer
                    .parameter(new Parameter("workerName", listener.getEmployeeToSetDevice()))
                    .parameter(new Parameter("workerPost", ""));
        }
        try {
            DictionaryEmployee employeeToDoWork = employeeClient.getEmployeeByCode(listener.getEmployeeToDoWork());
            footer
                    .parameter(new Parameter("employeeToDoWork", employeeToDoWork.getSurname() + " " + employeeToDoWork.getName().substring(0, 1) + ". " + employeeToDoWork.getMiddlename().substring(0, 1) + "."))
                    .parameter(new Parameter("employeeToDoWorkPost", employeeToDoWork.getPost().getName()));
        } catch (EmployeeNotFoundException ex) {
            footer
                    .parameter(new Parameter("employeeToDoWork", listener.getEmployeeToDoWork()))
                    .parameter(new Parameter("employeeToDoWorkPost", ""));
        }

        try {
            DictionaryEmployee employeeMOL = employeeClient.getEmployeeByCode(listener.getEmployeeMOL());
            footer
                    .parameter(new Parameter("employeeMol", employeeMOL.getSurname() + " " + employeeMOL.getName().substring(0, 1) + ". " + employeeMOL.getMiddlename().substring(0, 1) + "."))
                    .parameter(new Parameter("employeeMOLPost", employeeMOL.getPost().getName()));
        } catch (EmployeeNotFoundException ex) {
            footer
                    .parameter(new Parameter("employeeMol", listener.getEmployeeMOL()))
                    .parameter(new Parameter("employeeMOLPost", ""));
        }

        Printer printer = printerRepo.findById(listener.getPrinterID()).orElseThrow(() -> new ReportException("Принтер не найден"));
        Cartridge cartridge = cartridgeRepo.findById(listener.getCartridgeID()).orElseThrow(() -> new ReportException("Картридж не найден"));
        Row.RowBuilder row = Row.builder()
                .name("num")
                .parameter(new Parameter("kod", cartridge.getItemCode()))
                .parameter(new Parameter("cartName", cartridge.getNameMaterial()))
                .parameter(new Parameter("date", cartridge.getDateStartExploitation().format(DateTimeFormatter.ISO_DATE)))
                .parameter(new Parameter("printerName", printer.getManufacturer().getName() + " " + printer.getModel().getName()))
                .parameter(new Parameter("printerNumber", printer.getInventoryNumber()));
        try {
            DictionaryEmployee employeeToSetDevice = employeeClient.getEmployeeByCode(listener.getEmployeeToSetDevice());
            row
                    .parameter(new Parameter("workerName", employeeToSetDevice.getSurname() + " " + employeeToSetDevice.getName().substring(0, 1) + ". " + employeeToSetDevice.getMiddlename().substring(0, 1) + "."))
                    .parameter(new Parameter("workerPost", employeeToSetDevice.getPost().getName()));
        } catch (EmployeeNotFoundException ex) {
            row
                    .parameter(new Parameter("workerName", listener.getEmployeeToSetDevice()))
                    .parameter(new Parameter("workerPost", ""));
        }
        Block dataBlock = Block.builder().name("dataBlock").row(row.build()).build();
        Record record = Record.builder().block(dataBlock).build();
        return Request.builder().mainBlock(footer.build()).record(record).build();
    }


}
