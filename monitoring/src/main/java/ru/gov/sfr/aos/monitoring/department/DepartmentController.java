package ru.gov.sfr.aos.monitoring.department;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.aos.employees.models.DepartmentDto;
import org.springframework.web.bind.annotation.GetMapping;


/*
 * @author Oleg Alikin
 */

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentClient departmentClient;

    @GetMapping("/api/departments")    
    public List<DepartmentDto> getDepartments() {
        return departmentClient.getDepartments();
    }
}
