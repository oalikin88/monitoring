package ru.gov.sfr.aos.monitoring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeePlaceRequest {
    private Long id;
    private String name;
    private Long departmentId;

    
}
