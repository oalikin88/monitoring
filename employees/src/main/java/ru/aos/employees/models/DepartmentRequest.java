package ru.aos.employees.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author oalikin88
 */
@Setter
@Getter
public class DepartmentRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
}
