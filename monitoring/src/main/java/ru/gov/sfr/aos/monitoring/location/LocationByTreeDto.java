/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.location;

import java.util.ArrayList;
import java.util.List;
import ru.gov.sfr.aos.monitoring.department.DepartmentTreeDto;

/**
 *
 * @author 041AlikinOS
 */
public class LocationByTreeDto {
    
    private Long locationId;
    private String locationName;
    private List<DepartmentTreeDto> departments = new ArrayList<>();
    public LocationByTreeDto() {
    }

    public LocationByTreeDto(Long locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<DepartmentTreeDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentTreeDto> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "LocationByTreeDto{" + "locationId=" + locationId + ", locationName=" + locationName + ", departments=" + departments + '}';
    }
    
    public int getAmount() {
        int result = 0;
        for(DepartmentTreeDto dep : this.departments) {
            result = result + dep.getDtoes().size();
        }
        return result;
    }
    
}
