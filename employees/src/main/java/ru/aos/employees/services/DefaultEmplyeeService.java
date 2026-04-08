package ru.aos.employees.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aos.employees.entity.Employee;
import ru.aos.employees.interfaces.EmployeeMapper;
import ru.aos.employees.interfaces.EmployeeService;
import ru.aos.employees.models.EmployeeDto;
import ru.aos.employees.models.EmployeeRequest;
import ru.aos.employees.repositories.EmployeeRepo;

/**
 *
 * @author oalikin88
 */
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultEmplyeeService implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper mapper;
    
    @Override
    public EmployeeDto create(EmployeeRequest request) {
        if(employeeRepo.existsByUsername(request.getUsername())) {
            throw new EntityExistsException("Сотрудник с именем пользователя: " + request.getUsername() + ", уже существует.");
        }
        Employee employee = mapper.toEntity(request);
        Employee saved = employeeRepo.save(employee);
        return mapper.toDto(saved);
    }

    @Override
    public EmployeeDto getById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Сотрудника с id: " + id + ", нет в базе данных"));
        return mapper.toDto(employee);
    }

    @Override
    public EmployeeDto update(Long id, EmployeeRequest request) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Сотрудника с id: " + id + ", нет в базе данных"));
        mapper.updateEntityFromRequest(request, employee);
        return mapper.toDto(employeeRepo.save(employee));
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> page = employeeRepo.findAll();
       
         return page.stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Сотрудника с id: " + id + ", нет в базе данных"));
        employeeRepo.delete(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(Long departmentId) {
        List<Employee> employees = employeeRepo.findByDepartmentId(departmentId);
        return employees.stream().map(mapper::toDto).toList();
    }

    
}
