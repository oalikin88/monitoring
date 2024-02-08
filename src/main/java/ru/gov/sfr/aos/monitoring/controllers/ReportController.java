/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import lombok.RequiredArgsConstructor;
import org.opfr.starter.report.exception.ReportException;
import org.opfr.starter.report.service.ReportCreateFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.reports.parameters.ActReportParameters;
import ru.gov.sfr.aos.reports.parameters.PrinterListReportParameters;

/**
 *
 * @author 041AlikinOS
 */
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportCreateFactory reportCreateFactory;

    @GetMapping("/act")
    public String actReport(ActReportParameters actReportParameters) throws ReportException {
        return reportCreateFactory.proceedReport("actReport", actReportParameters);
    }
    
    
     @GetMapping("/printers")
    public String printersReport(PrinterListReportParameters printerListReportParameters) throws ReportException {
        return reportCreateFactory.proceedReport("printersList", printerListReportParameters);
    }
    
    @GetMapping("/printersByModel")
    public String printersByModelReport(PrinterListReportParameters printerListReportParameters) throws ReportException {
        return reportCreateFactory.proceedReport("printersList", printerListReportParameters);
    }

}
