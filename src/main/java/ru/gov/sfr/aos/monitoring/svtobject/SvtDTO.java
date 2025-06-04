/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.svtobject;

import java.util.Date;

/**
 *
 * @author 041AlikinOS
 */

public class SvtDTO extends MainSvtDto {
    
    protected Long id;
    protected Long modelId;
    protected String model;
    protected String status;
    protected String inventaryNumber;
    protected String serialNumber;
    protected int yearCreated;
    protected Date dateExploitationBegin;
    protected Long placeId;
    protected String placeName;
    protected String placeType;
    protected String departmentCode;
    protected Long locationId;
    protected String phoneNumber;
    protected String nameFromOneC;
    protected String typeDto;
    protected String baseType;
    protected String batteryType;
    protected Long batteryTypeId;
    protected int batteryAmount;
    protected int yearReplacement;
    protected String numberRoom;
    protected Long manufacturerId;
    protected String manufacturerName;
    protected boolean ignoreCheck;
    

    public SvtDTO() {
    }

    public SvtDTO(Long id, Long modelId, String model, String status, String inventaryNumber, String serialNumber, int yearCreated, Date dateExploitationBegin, Long placeId, String placeName, String placeType, String departmentCode, Long locationId, String phoneNumber, String nameFromOneC, String typeDto, String baseType, String batteryType, Long batteryTypeId, int batteryAmount, int yearReplacement, String numberRoom) {
        this.id = id;
        this.modelId = modelId;
        this.model = model;
        this.status = status;
        this.inventaryNumber = inventaryNumber;
        this.serialNumber = serialNumber;
        this.yearCreated = yearCreated;
        this.dateExploitationBegin = dateExploitationBegin;
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeType = placeType;
        this.departmentCode = departmentCode;
        this.locationId = locationId;
        this.phoneNumber = phoneNumber;
        this.nameFromOneC = nameFromOneC;
        this.typeDto = typeDto;
        this.baseType = baseType;
        this.batteryType = batteryType;
        this.batteryTypeId = batteryTypeId;
        this.batteryAmount = batteryAmount;
        this.yearReplacement = yearReplacement;
        this.numberRoom = numberRoom;
    }
    



    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInventaryNumber() {
        String result = "";
        if(null != inventaryNumber) {
            result = result + inventaryNumber;
        } 
        return result;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public String getSerialNumber() {
        String result = "";
        if(null != serialNumber) {
            result = result + serialNumber;
        }
        return result;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public Date getDateExploitationBegin() {
        return dateExploitationBegin;
    }

    public void setDateExploitationBegin(Date dateExploitationBegin) {
        this.dateExploitationBegin = dateExploitationBegin;
    }


    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getModel() {
        String result = "";
        if(null != model) {
            result = result + model;
        } 
        
        return result;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlaceName() {
        String result = "";
        if(null != placeName) {
            result = result + placeName;
        }
        return result;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceType() {
        String result = "";
        if(null != placeType) {
            result = result + placeType;
        }
        return result;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }


    public String getDepartmentCode() {
        String result = "";
        if(null != departmentCode) {
            result = result + departmentCode;
        }
        return result;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getTypeDto() {
        String result = "";
        if(null != typeDto) {
            result = result + typeDto;
        }
        return result;
    }

    public void setTypeDto(String typeDto) {
        this.typeDto = typeDto;
    }

    public String getPhoneNumber() {
        String result = "";
        if(null != phoneNumber) {
            result = result + phoneNumber;
        } 
        return result;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameFromOneC() {
        String result = "";
        if(null != nameFromOneC) {
            result = result + nameFromOneC;
        }
        return result;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public String getBaseType() {
        String result = "";
        if(null != baseType) {
            result = result + baseType;
        }
        
        return result;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getBatteryType() {
        String result = "";
        if(null != batteryType) {
            result = result + batteryType;
        }
        return result;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public Long getBatteryTypeId() {
        return batteryTypeId;
    }

    public void setBatteryTypeId(Long batteryTypeId) {
        this.batteryTypeId = batteryTypeId;
    }

    public int getBatteryAmount() {
        return batteryAmount;
    }

    public void setBatteryAmount(int batteryAmount) {
        this.batteryAmount = batteryAmount;
    }

    public int getYearReplacement() {
        return yearReplacement;
    }

    public void setYearReplacement(int yearReplacement) {
        this.yearReplacement = yearReplacement;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        String result = "не указан";
        if(manufacturerName != null) {
            result = manufacturerName;
        }
        return result;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public boolean isIgnoreCheck() {
        return ignoreCheck;
    }

    public void setIgnoreCheck(boolean ignoreCheck) {
        this.ignoreCheck = ignoreCheck;
    }
    
    
    
    

    @Override
    public String toString() {
        return "SvtDTO{" + "id=" + id + ", modelId=" + modelId + ", model=" + model + ", status=" + status + ", inventaryNumber=" + inventaryNumber + ", serialNumber=" + serialNumber + ", yearCreated=" + yearCreated + ", dateExploitationBegin=" + dateExploitationBegin + ", placeId=" + placeId + ", placeName=" + placeName + ", placeType=" + placeType + ", departmentCode=" + departmentCode + ", locationId=" + locationId + ", phoneNumber=" + phoneNumber + ", nameFromOneC=" + nameFromOneC + ", typeDto=" + typeDto + ", baseType=" + baseType + ", batteryType=" + batteryType + ", batteryTypeId=" + batteryTypeId + ", batteryAmount=" + batteryAmount + ", yearReplacement=" + yearReplacement + ", numberRoom=" + numberRoom + '}';
    }
    
    
    
    
}
