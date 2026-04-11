package ru.gov.sfr.aos.monitoring.employee;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.aos.employees.models.EmployeeDto;

/*
 * @author Oleg Alikin
 */

@FeignClient(name = "employees", url = "http://localhost:8080")
public interface EmployeeClient {
    @GetMapping("/api/v1/employees")
    List<EmployeeDto> getEmployees();
    @GetMapping("/api/v1/employees/bydep")
    List<EmployeeDto> getEmployeesByDepartment(@RequestParam Long departmentId);

}
