package ru.gov.sfr.aos.monitoring.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.aos.employees.models.EmployeeDto;
import ru.gov.sfr.aos.monitoring.models.EmployeePlaceRequest;

@Service
public class EmployeeService {

public List<EmployeePlaceRequest> getEmployeesForPlaceRequest(List<EmployeeDto> employees) {
    return employees.stream().map(this::toRequest).toList();
}

private EmployeePlaceRequest toRequest(EmployeeDto dto) {
    return new EmployeePlaceRequest(dto.getId(),
     dto.getSurname() + " " + dto.getName() + " " + dto.getPatronymic(),
      dto.getDepartmentId());
}

}
