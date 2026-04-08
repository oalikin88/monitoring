package ru.gov.sfr.aos.monitoring.employee;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.aos.employees.models.EmployeeDto;
import ru.gov.sfr.aos.monitoring.models.EmployeePlaceRequest;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor

public class EmployeeController {


    private final EmployeeClient employeeClient;
    private final EmployeeService employeeService;

    @GetMapping("/api/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployee() {

        List<EmployeeDto> employees = employeeClient.getEmployees();
        return ResponseEntity.ok(employees);
    }

     @GetMapping("/api/employees/bydep")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDep(@RequestParam Long departmentId) {

        List<EmployeeDto> employees = employeeClient.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/api/req-emp-for-place")   
    public ResponseEntity<List<EmployeePlaceRequest>> getEmployeePlaceRequestByDepartment(@RequestParam Long departmentId) {
        List<EmployeeDto> employees = getEmployeesByDep(departmentId).getBody();
        List<EmployeePlaceRequest> employeesForPlaceRequest = employeeService.getEmployeesForPlaceRequest(employees);
        return ResponseEntity.ok(employeesForPlaceRequest);
    } 
}
