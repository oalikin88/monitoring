/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.reports.parameters;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.opfr.starter.report.interfaces.ReportParametersInput;
import ru.gov.sfr.aos.monitoring.models.ActsByPeriodDto;

/**
 *
 * @author 041AlikinOS
 */
@Getter
@Setter
public class ActsByPeriodParameters implements ReportParametersInput {
    private LocalDate startDate;
    private LocalDate endDate;
    private String mol;
    private String employee;
    private Long location;
}
