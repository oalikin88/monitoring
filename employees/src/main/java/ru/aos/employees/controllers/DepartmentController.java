package ru.aos.employees.controllers;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aos.employees.interfaces.DepartmentService;
import ru.aos.employees.models.DepartmentDto;
import ru.aos.employees.models.DepartmentRequest;

/**
 *
 * @author oalikin88
 */
@RestController
@RequestMapping("api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments(Pageable pageable) {
        List<DepartmentDto> departments = departmentService.getAll(pageable);
        return ResponseEntity.ok(departments);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable(name = "id") Long id) {
        DepartmentDto dto = departmentService.getById(id);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(DepartmentRequest request) {
        DepartmentDto dto = departmentService.create(request);
        return ResponseEntity.ok(dto);
    }
    
    @DeleteMapping
    public ResponseEntity<String> deleteDepartment(long id) {
        departmentService.delete(id);
        return ResponseEntity.ok("Отдел успешно удалён");
    }
    
    @PutMapping
    public ResponseEntity<DepartmentDto> updateDepartment(Long id, DepartmentRequest request) {
        DepartmentDto dto = departmentService.update(id, request);
        return ResponseEntity.ok(dto);
    }
    
}
