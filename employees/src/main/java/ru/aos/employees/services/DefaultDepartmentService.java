package ru.aos.employees.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aos.employees.entity.Department;
import ru.aos.employees.interfaces.DepartmentMapper;
import ru.aos.employees.interfaces.DepartmentService;
import ru.aos.employees.models.DepartmentDto;
import ru.aos.employees.models.DepartmentRequest;
import ru.aos.employees.repositories.DepartmentRepo;
import ru.aos.employees.repositories.EmployeeRepo;

/**
 *
 * @author oalikin88
 */
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultDepartmentService implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;
    private final DepartmentMapper mapper;
    
    @Override
    public DepartmentDto create(DepartmentRequest request) {
        if(departmentRepo.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Отдел " + request.getCode() + " уже есть в базе данных. ");
        }
        Department department = mapper.toEntity(request);
        Department saved = departmentRepo.save(department);
        return mapper.toDto(saved);
    }

    @Override
    public DepartmentDto getById(Long id) {
       return departmentRepo.findById(id).map(this::mapToDto)
               .orElseThrow(() -> new EntityNotFoundException("Отдел не найден"));
    }

    @Override
    public List<DepartmentDto> getAll(Pageable pageable) {
        List<Department> departmentPage = departmentRepo.findAll();
        return departmentPage.stream().map(this::mapToDto).toList();
    }

    @Override
    public DepartmentDto update(Long id, DepartmentRequest request) {
        Department department = departmentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Отдел не найден"));
        mapper.updateEntityFromRequest(request, department);
        return mapper.toDto(departmentRepo.save(department));
        
    }

    @Override
    public void delete(Long id) {
        Department department = departmentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Отдел не найден"));
        if(employeeRepo.existsByDepartmentId(id)) {
            throw new IllegalStateException("Нельзя удалить отдел, в котором есть сотрудники");
        }
        departmentRepo.delete(department);
        
    }
    
    
    private DepartmentDto mapToDto(Department department) {
        return new DepartmentDto(department.getId(),
                                    department.getName(),
                                        department.getCode());
    }
    

}
