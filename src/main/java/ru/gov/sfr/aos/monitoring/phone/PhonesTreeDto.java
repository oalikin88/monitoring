/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.phone;

import java.util.ArrayList;
import java.util.List;
import ru.gov.sfr.aos.monitoring.department.DepartmentTreeDto;

/**
 *
 * @author 041AlikinOS
 */
public class PhonesTreeDto {
    private Long locationId;
    private String locationName;
    private List<DepartmentTreeDto> phonesByDepartment = new ArrayList<>();

    public PhonesTreeDto() {
    }

    public PhonesTreeDto(Long locationId, String locationName) {
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

    public List<DepartmentTreeDto> getPhonesByDepartment() {
        return phonesByDepartment;
    }

    public void setPhonesByDepartment(List<DepartmentTreeDto> phonesByDepartment) {
        this.phonesByDepartment = phonesByDepartment;
    }

    @Override
    public String toString() {
        return "PhonesTreeDto{" + "locationId=" + locationId + ", locationName=" + locationName + ", phonesByDepartment=" + phonesByDepartment + '}';
    }
    
    
    
    
}
