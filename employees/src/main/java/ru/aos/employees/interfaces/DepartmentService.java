package ru.aos.employees.interfaces;


import java.util.List;

import org.springframework.data.domain.Pageable;
import ru.aos.employees.models.DepartmentRequest;
import ru.aos.employees.models.DepartmentDto;


/**
 *
 * @author Oleg Alikin
 */
  
public interface DepartmentService {

    DepartmentDto create(DepartmentRequest request);
    DepartmentDto getById(Long id);
    List<DepartmentDto> getAll(Pageable pageable);
    DepartmentDto update(Long id, DepartmentRequest request);
    void delete(Long id);
    
}
