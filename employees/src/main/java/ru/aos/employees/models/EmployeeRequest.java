package ru.aos.employees.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author oalikin88
 */
@Getter
@Setter
public class EmployeeRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String patronymic;
    @NotBlank
    private String username;
    @NotBlank
    private int phone;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private Long departmentId;
    
    
}
