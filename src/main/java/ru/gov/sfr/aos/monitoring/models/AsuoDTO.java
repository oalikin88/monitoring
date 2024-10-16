/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Alikin Oleg
 */
public class AsuoDTO extends MainSvtDto{
    private Long id;
    private Long placeId;
    private String placeName;
    private String nameFromOneC;
    private Date dateExploitationBegin;
    private int yearCreated;
    private String numberRoom;
    private Long displayId;
    private String displayModel;
    private String displaySerial;
    private String displayInventary;
    private Long terminalId;
    private String terminalModel;
    private String terminalSerial;
    private String terminalInventary;
    private Long thermoprinterId;
    private String thermoprinterModel;
    private String thermoprinterSerial;
    private String thermoprinterInventary;
    private List<SwitchHubDto> switches;
    private List<Long> switchId;
    private Long subDisplayModelId;
    private String subDisplayModel;
    private int subDisplayAmount;
    private Long switchingUnitId;   
    private String switchingUnitModel;   
    private Long switchingUnitInventary;   
    private Long switchingUnitSerial;   
    private Long locationId;
    
    public AsuoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public Date getDateExploitationBegin() {
        return dateExploitationBegin;
    }

    public void setDateExploitationBegin(Date dateExploitationBegin) {
        this.dateExploitationBegin = dateExploitationBegin;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public Long getDisplayId() {
        return displayId;
    }

    public void setDisplayId(Long displayId) {
        this.displayId = displayId;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public Long getThermoprinterId() {
        return thermoprinterId;
    }

    public void setThermoprinterId(Long thermoprinterId) {
        this.thermoprinterId = thermoprinterId;
    }

    public List<SwitchHubDto> getSwitches() {
        return switches;
    }

    public void setSwitches(List<SwitchHubDto> switches) {
        this.switches = switches;
    }


    public Long getSubDisplayModelId() {
        return subDisplayModelId;
    }

    public void setSubDisplayModelId(Long subDisplayModelId) {
        this.subDisplayModelId = subDisplayModelId;
    }

    public int getSubDisplayAmount() {
        return subDisplayAmount;
    }

    public void setSubDisplayAmount(int subDisplayAmount) {
        this.subDisplayAmount = subDisplayAmount;
    }

    public Long getSwitchingUnitId() {
        return switchingUnitId;
    }

    public void setSwitchingUnitId(Long switchingUnitId) {
        this.switchingUnitId = switchingUnitId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDisplayModel() {
        return displayModel;
    }

    public void setDisplayModel(String displayModel) {
        this.displayModel = displayModel;
    }

    public String getDisplaySerial() {
        return displaySerial;
    }

    public void setDisplaySerial(String displaySerial) {
        this.displaySerial = displaySerial;
    }

    public String getDisplayInventary() {
        return displayInventary;
    }

    public void setDisplayInventary(String displayInventary) {
        this.displayInventary = displayInventary;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public void setTerminalModel(String terminalModel) {
        this.terminalModel = terminalModel;
    }

    public String getTerminalSerial() {
        return terminalSerial;
    }

    public void setTerminalSerial(String terminalSerial) {
        this.terminalSerial = terminalSerial;
    }

    public String getTerminalInventary() {
        return terminalInventary;
    }

    public void setTerminalInventary(String terminalInventary) {
        this.terminalInventary = terminalInventary;
    }

    public String getThermoprinterModel() {
        return thermoprinterModel;
    }

    public void setThermoprinterModel(String thermoprinterModel) {
        this.thermoprinterModel = thermoprinterModel;
    }

    public String getThermoprinterSerial() {
        return thermoprinterSerial;
    }

    public void setThermoprinterSerial(String thermoprinterSerial) {
        this.thermoprinterSerial = thermoprinterSerial;
    }

    public String getThermoprinterInventary() {
        return thermoprinterInventary;
    }

    public void setThermoprinterInventary(String thermoprinterInventary) {
        this.thermoprinterInventary = thermoprinterInventary;
    }

    public String getSubDisplayModel() {
        return subDisplayModel;
    }

    public void setSubDisplayModel(String subDisplayModel) {
        this.subDisplayModel = subDisplayModel;
    }

    public String getSwitchingUnitModel() {
        return switchingUnitModel;
    }

    public void setSwitchingUnitModel(String switchingUnitModel) {
        this.switchingUnitModel = switchingUnitModel;
    }

    public Long getSwitchingUnitInventary() {
        return switchingUnitInventary;
    }

    public void setSwitchingUnitInventary(Long switchingUnitInventary) {
        this.switchingUnitInventary = switchingUnitInventary;
    }

    public Long getSwitchingUnitSerial() {
        return switchingUnitSerial;
    }

    public void setSwitchingUnitSerial(Long switchingUnitSerial) {
        this.switchingUnitSerial = switchingUnitSerial;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public List<Long> getSwitchId() {
        return switchId;
    }

    public void setSwitchId(List<Long> switchId) {
        this.switchId = switchId;
    }

    @Override
    public String toString() {
        return "AsuoDTO{" + "id=" + id + ", placeId=" + placeId + ", placeName=" + placeName + ", nameFromOneC=" + nameFromOneC + ", dateExploitationBegin=" + dateExploitationBegin + ", yearCreated=" + yearCreated + ", numberRoom=" + numberRoom + ", displayId=" + displayId + ", displayModel=" + displayModel + ", displaySerial=" + displaySerial + ", displayInventary=" + displayInventary + ", terminalId=" + terminalId + ", terminalModel=" + terminalModel + ", terminalSerial=" + terminalSerial + ", terminalInventary=" + terminalInventary + ", thermoprinterId=" + thermoprinterId + ", thermoprinterModel=" + thermoprinterModel + ", thermoprinterSerial=" + thermoprinterSerial + ", thermoprinterInventary=" + thermoprinterInventary + ", switches=" + switches + ", switchId=" + switchId + ", subDisplayModelId=" + subDisplayModelId + ", subDisplayModel=" + subDisplayModel + ", subDisplayAmount=" + subDisplayAmount + ", switchingUnitId=" + switchingUnitId + ", switchingUnitModel=" + switchingUnitModel + ", switchingUnitInventary=" + switchingUnitInventary + ", switchingUnitSerial=" + switchingUnitSerial + ", locationId=" + locationId + '}';
    }
    
    
    
}
