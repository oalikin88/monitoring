package ru.aos.employees.interfaces;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.aos.employees.entity.Employee;
import ru.aos.employees.models.EmployeeDto;
import ru.aos.employees.models.EmployeeRequest;

/**
 *
 * @author Oleg Alikin
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.id", target = "departmentId")
    EmployeeDto toDto(Employee employee);
    @Mapping(source = "departmentId", target = "department.id")
    Employee toEntity(EmployeeRequest request);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(EmployeeRequest request, @MappingTarget Employee employee);
}
