/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.fax;

import java.util.Date;
import ru.gov.sfr.aos.monitoring.svtobject.MainSvtDto;

/**
 *
 * @author Alikin Oleg
 */
public class FaxDto extends MainSvtDto {
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
    protected String nameFromOneC;
    protected String numberRoom;
    protected String ipAdress;
    protected String manufacturerName;
    protected Long manufacturerId;
    public boolean ignoreCheck;

    public FaxDto() {
    }

    public FaxDto(Long id, Long modelId, String model, String status, String inventaryNumber, String serialNumber, int yearCreated, Date dateExploitationBegin, Long placeId, String placeName, String placeType, String departmentCode, Long locationId, String nameFromOneC, String numberRoom, String ipAdress) {
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
        this.nameFromOneC = nameFromOneC;
        this.numberRoom = numberRoom;
        this.ipAdress = ipAdress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModel() {
        String result = "";
        if(null == model) {
            result = "не указано";
        } else {
            result = model;
        }
        return result;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        String result = "";
        if(null == result) {
            result = "не указан";
        } else {
            result = status;
        }
        return result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInventaryNumber() {
        String result = "";
        if(null == inventaryNumber) {
            result = "не указан";
        } else {
            result = inventaryNumber;
        }
        return result;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public String getSerialNumber() {
        String result = "";
        if(null == serialNumber) {
            result = "не указан";
        } else {
            result = serialNumber;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceType() {
        String result = "";
        if(null == placeType) {
            result = "не указан";
        } else {
            result = placeType;
        }
        return result;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getDepartmentCode() {
        String result = "";
        if(null == departmentCode) {
            result = "не указан";
        } else {
            result = departmentCode;
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

    public String getNameFromOneC() {
        String result = "";
        if(null == nameFromOneC) {
            result = "нет";
        } else {
            result = nameFromOneC;
        }
        return result;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public String getNumberRoom() {
        String result = "";
        if(null == numberRoom) {
            result = "не указан";
        } else {
            result = numberRoom;
        }
        return result;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getIpAdress() {
        String result = "";
        if(null == ipAdress) {
            result = "не указан";
        } else {
            result = ipAdress;
        }
        return result;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
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

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public boolean isIgnoreCheck() {
        return ignoreCheck;
    }

    public void setIgnoreCheck(boolean ignoreCheck) {
        this.ignoreCheck = ignoreCheck;
    }
    
    
    
    

    @Override
    public String toString() {
        return "FaxDto{" + "id=" + id + ", modelId=" + modelId + ", model=" + model + ", status=" + status + ", inventaryNumber=" + inventaryNumber + ", serialNumber=" + serialNumber + ", yearCreated=" + yearCreated + ", dateExploitationBegin=" + dateExploitationBegin + ", placeId=" + placeId + ", placeName=" + placeName + ", placeType=" + placeType + ", departmentCode=" + departmentCode + ", locationId=" + locationId + ", nameFromOneC=" + nameFromOneC + ", numberRoom=" + numberRoom + ", ipAdress=" + ipAdress + '}';
    }
    
    
}
