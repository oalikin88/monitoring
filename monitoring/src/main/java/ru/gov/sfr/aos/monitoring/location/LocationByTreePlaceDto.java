package ru.gov.sfr.aos.monitoring.location;

import java.util.ArrayList;
import java.util.List;
import ru.gov.sfr.aos.monitoring.department.DepartmentByTreePlaceDto;

/**
 *
 * @author Alikin Oleg
 */

public class LocationByTreePlaceDto {
    private Long LocationId;
    private String LocationName;
    private List<DepartmentByTreePlaceDto> departments = new ArrayList<>();

    public LocationByTreePlaceDto() {
    }

    public Long getLocationId() {
        return LocationId;
    }

    public void setLocationId(Long LocationId) {
        this.LocationId = LocationId;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String LocationName) {
        this.LocationName = LocationName;
    }

    public List<DepartmentByTreePlaceDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentByTreePlaceDto> departments) {
        this.departments = departments;
    }
    
     public int getAmount() {
        int result = 0;
        for(DepartmentByTreePlaceDto dep : this.departments) {
            result = result + dep.getDtoes().size();
        }
        return result;
    }

}
