package ru.aos.employees.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author oalikin88
 */
@Getter
@Setter
public class EmployeeDto {
    private Long id;
    private Long departmentId;
    private String name;
    private String surname;
    private String patronymic;
    private String username;
    private int phone;
    private String email;
}
