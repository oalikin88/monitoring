package ru.gov.sfr.aos.monitoring.department;

import ru.gov.sfr.aos.monitoring.place.PlaceDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alikin Oleg
 */

public class DepartmentByTreePlaceDto {
    private String department;
    private String departmentCode;
    private List<PlaceDTO> dtoes = new ArrayList<>();

    public DepartmentByTreePlaceDto() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public List<PlaceDTO> getDtoes() {
        return dtoes;
    }

    public void setDtoes(List<PlaceDTO> dtoes) {
        this.dtoes = dtoes;
    }

    
    
    
}
