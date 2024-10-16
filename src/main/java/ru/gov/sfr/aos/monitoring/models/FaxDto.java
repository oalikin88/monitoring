/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;

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
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
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
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getDepartmentCode() {
        return departmentCode;
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
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    @Override
    public String toString() {
        return "FaxDto{" + "id=" + id + ", modelId=" + modelId + ", model=" + model + ", status=" + status + ", inventaryNumber=" + inventaryNumber + ", serialNumber=" + serialNumber + ", yearCreated=" + yearCreated + ", dateExploitationBegin=" + dateExploitationBegin + ", placeId=" + placeId + ", placeName=" + placeName + ", placeType=" + placeType + ", departmentCode=" + departmentCode + ", locationId=" + locationId + ", nameFromOneC=" + nameFromOneC + ", numberRoom=" + numberRoom + ", ipAdress=" + ipAdress + '}';
    }
    
    
}
