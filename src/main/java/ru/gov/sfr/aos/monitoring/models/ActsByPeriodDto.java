/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 041AlikinOS
 */
@Getter
@Setter
public class ActsByPeriodDto {
    private Long location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String mol;
    private String employee;

    public ActsByPeriodDto() {
    }

    public ActsByPeriodDto(Long location, LocalDate startDate, LocalDate endDate, String mol, String employee) {
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mol = mol;
        this.employee = employee;
    }
    
    
}
