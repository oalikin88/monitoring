package ru.aos.employees.interfaces;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.aos.employees.entity.Department;
import ru.aos.employees.models.DepartmentDto;
import ru.aos.employees.models.DepartmentRequest;

/**
 *
 * @author Oleg Alikin
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentRequest request);
    @Mapping(target = "employees", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(DepartmentRequest request, @MappingTarget Department department);
}
