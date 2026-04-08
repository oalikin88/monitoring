package ru.aos.employees.controllers;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aos.employees.interfaces.EmployeeService;
import ru.aos.employees.models.EmployeeDto;
import ru.aos.employees.models.EmployeeRequest;

/**
 *
 * @author oalikin88
 */
@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(Pageable pageable) {
        List<EmployeeDto> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable(name = "id") Long id) {
        EmployeeDto dto = employeeService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(EmployeeRequest request) {
        EmployeeDto dto = employeeService.create(request);
        return ResponseEntity.ok(dto);
    }
    
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam Long id, EmployeeRequest request) {
        EmployeeDto dto = employeeService.update(id, request);
        return ResponseEntity.ok(dto);
    }
    
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok("Сотрудник id: " + id + ", удлён.");
    }
    @GetMapping("/bydep")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@RequestParam Long departmentId) {
        List<EmployeeDto> employeesByDepartment = employeeService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(employeesByDepartment);
    }
}
