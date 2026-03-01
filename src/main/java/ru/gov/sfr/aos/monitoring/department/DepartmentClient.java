package ru.gov.sfr.aos.monitoring.department;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import ru.aos.employees.models.DepartmentDto;

@FeignClient(name = "department", url = "http://localhost:8080")
public interface DepartmentClient {
    @GetMapping("/api/v1/departments")
    List<DepartmentDto> getDepartments();

    
}
