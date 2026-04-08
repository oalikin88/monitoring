package ru.aos.employees.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;
import ru.aos.employees.models.EmployeeDto;
import ru.aos.employees.models.EmployeeRequest;

/**
 *
 * @author Oleg Alikin
 */

public interface EmployeeService {

    EmployeeDto create(EmployeeRequest request);
    EmployeeDto getById(Long id);
    EmployeeDto update(Long id, EmployeeRequest request);
    List<EmployeeDto> getAll();
    List<EmployeeDto> getEmployeesByDepartment(Long departmentId);
    void delete(Long id);
}
