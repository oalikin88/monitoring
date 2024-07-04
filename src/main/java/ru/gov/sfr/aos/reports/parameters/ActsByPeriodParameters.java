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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMol() {
        return mol;
    }

    public void setMol(String mol) {
        this.mol = mol;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }
    
    
}
