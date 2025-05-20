/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class PlaceDTO {
    private Long id;
    private Long modelId;
    private String username;
    private String placeType;
    private Long locationId;
    private String locationName;
    private String department;
    private String departmentCode;

    public PlaceDTO() {
    }

    public PlaceDTO(Long id, String username, String placeType, Long locationId, String locationName, String department) {
        this.id = id;
        this.username = username;
        this.placeType = placeType;
        this.locationId = locationId;
        this.locationName = locationName;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
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

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
    
    

    @Override
    public String toString() {
        return "PlaceDTO{" + "placeId=" + id + ", username=" + username + ", placeType=" + placeType + ", locationId=" + locationId + ", locationName=" + locationName + ", department=" + department + ", departmentCode=" + departmentCode + '}';
    }
    
    
    
}
