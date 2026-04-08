package ru.aos.employees.interfaces;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aos.employees.models.EmployeeDto;

/**
 *
 * @author Oleg Alikin
 */
@FeignClient(name = "employee-service", url = "${employee-service.url}")
public interface EmployeeClient {

    @GetMapping("/api/v1/employees")
    List<EmployeeDto> getEmployees();
}
