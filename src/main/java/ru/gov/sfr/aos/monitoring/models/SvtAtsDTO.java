/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;

/**
 *
 * @author 041AlikinOS
 */
public class SvtAtsDTO extends SvtDTO {
    public int cityNumberAmount;
    public String outerConnectionType;
    public int innerConnectionIp;
    public int innerConnectionAnalog;

    public SvtAtsDTO(int cityNumberAmount, String outerConnectionType, int innerConnectionIp, int innerConnectionAnalog, Long id, Long modelId, String model, String status, String inventaryNumber, String serialNumber, int yearCreated, Date dateExploitationBegin, Long placeId, String placeName, String placeType, String departmentCode, Long locationId, String phoneNumber, String nameFromOneC, String typeDto, String baseType, String batteryType, Long batteryTypeId, int batteryAmount, int yearReplacement, String numberRoom) {
        super(id, modelId, model, status, inventaryNumber, serialNumber, yearCreated, dateExploitationBegin, placeId, placeName, placeType, departmentCode, locationId, phoneNumber, nameFromOneC, typeDto, baseType, batteryType, batteryTypeId, batteryAmount, yearReplacement, numberRoom);
        this.cityNumberAmount = cityNumberAmount;
        this.outerConnectionType = outerConnectionType;
        this.innerConnectionIp = innerConnectionIp;
        this.innerConnectionAnalog = innerConnectionAnalog;
    }

    public int getCityNumberAmount() {
        return cityNumberAmount;
    }

    public void setCityNumberAmount(int cityNumberAmount) {
        this.cityNumberAmount = cityNumberAmount;
    }

    public String getOuterConnectionType() {
        return outerConnectionType;
    }

    public void setOuterConnectionType(String outerConnectionType) {
        this.outerConnectionType = outerConnectionType;
    }

    public int getInnerConnectionIp() {
        return innerConnectionIp;
    }

    public void setInnerConnectionIp(int innerConnectionIp) {
        this.innerConnectionIp = innerConnectionIp;
    }

    public int getInnerConnectionAnalog() {
        return innerConnectionAnalog;
    }

    public void setInnerConnectionAnalog(int innerConnectionAnalog) {
        this.innerConnectionAnalog = innerConnectionAnalog;
    }
    
    
    
}
