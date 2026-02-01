/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.conditioner;

import java.util.Date;

import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;

/**
 *
 * @author 041AlikinOS
 */
public class SvtConditionerDTO extends SvtDTO {
    
    public String description;
    public int yearCreated;
    public String conditionerType;
    public boolean splitSystem;
    public boolean winterKit;
    public boolean havePomp;
    public double price;
    public boolean ignoreCheck;

    public SvtConditionerDTO(String decription, int yearCreated, String conditionerType, double price, boolean splitSystem, boolean winterKit, boolean havePomp, Long id, Long modelId, String model, String status, String inventaryNumber, String serialNumber, Date dateExploitationBegin, Long placeId, String placeName, String placeType, String departmentCode, Long locationId, String phoneNumber, String nameFromOneC, String typeDto, String baseType, String batteryType, Long batteryTypeId, int batteryAmount, int yearReplacement, String numberRoom) {
        super(id, modelId, model, status, inventaryNumber, serialNumber, yearCreated, dateExploitationBegin, placeId, placeName, placeType, departmentCode, locationId, phoneNumber, nameFromOneC, typeDto, baseType, batteryType, batteryTypeId, batteryAmount, yearReplacement, numberRoom);
        this.description = decription;
        this.yearCreated = yearCreated;
        this.conditionerType = conditionerType;
        this.splitSystem = splitSystem;
        this.winterKit = winterKit;
        this.havePomp = havePomp;
        this.price = price;
    }

    public String getDescription() {
         String result = "";
        if(null == description) {
            result = "отсутствует";
        } else {
            result = description;
        }
        return result;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getConditionerType() {
        String result = "";
        if(null == conditionerType) {
            result = "не указан";
        } else {
            result = conditionerType;
        }
        return result;
    }

    public void setConditionerType(String conditionerType) {
        this.conditionerType = conditionerType;
    }

    public boolean isSplitSystem() {
        return splitSystem;
    }

    public void setSplitSystem(boolean splitSystem) {
        this.splitSystem = splitSystem;
    }

    public boolean isWinterKit() {
        return winterKit;
    }

    public void setWinterKit(boolean winterKit) {
        this.winterKit = winterKit;
    }

    public boolean isHavePomp() {
        return havePomp;
    }

    public void setHavePomp(boolean havePomp) {
        this.havePomp = havePomp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIgnoreCheck() {
        return ignoreCheck;
    }

    public void setIgnoreCheck(boolean ignoreCheck) {
        this.ignoreCheck = ignoreCheck;
    }
    
    

    @Override
    public String toString() {
        return "SvtConditionerDTO{" + "id=" + this.id + ", model=" + this.model + ", serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + ", description=" + description + ", yearCreated=" + yearCreated + ", conditionerType=" + conditionerType + ", splitSystem=" + splitSystem + ", winterKit=" + winterKit + ", havePomp=" + havePomp + ", price=" + price + '}';
    }
    
    
    
}
