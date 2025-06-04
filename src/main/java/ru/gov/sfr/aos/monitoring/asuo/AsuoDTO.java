/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import ru.gov.sfr.aos.monitoring.svtobject.MainSvtDto;

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
    private Set<AsuoComponentDto> displays = new HashSet<>();
    private Set <AsuoComponentDto> terminals = new HashSet<>();
    private Long thermoprinterId;
    private String thermoprinterModel;
    private String thermoprinterSerial;
    private String thermoprinterInventary;
    private Set<AsuoComponentDto> hubs = new HashSet<>();
    private Long programSoftware;
    private String programSoftwareVersion;
    private Long subDisplayModelId;
    private String subDisplayModel;
    private int subDisplayAmount;
    private Long locationId;
    private String inventaryNumber;
    private String status;
    
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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

    public Set<AsuoComponentDto> getDisplays() {
        return displays;
    }

    public void setDisplays(Set<AsuoComponentDto> displays) {
        this.displays = displays;
    }

    public Set<AsuoComponentDto> getTerminals() {
        return terminals;
    }

    public void setTerminals(Set<AsuoComponentDto> terminals) {
        this.terminals = terminals;
    }

    public Long getThermoprinterId() {
        return thermoprinterId;
    }

    public void setThermoprinterId(Long thermoprinterId) {
        this.thermoprinterId = thermoprinterId;
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

    public Set<AsuoComponentDto> getHubs() {
        return hubs;
    }

    public void setHubs(Set<AsuoComponentDto> hubs) {
        this.hubs = hubs;
    }

    public Long getProgramSoftware() {
        return programSoftware;
    }

    public void setProgramSoftware(Long programSoftware) {
        this.programSoftware = programSoftware;
    }

    public String getProgramSoftwareVersion() {
        return programSoftwareVersion;
    }

    public void setProgramSoftwareVersion(String programSoftwareVersion) {
        this.programSoftwareVersion = programSoftwareVersion;
    }

    public Long getSubDisplayModelId() {
        return subDisplayModelId;
    }

    public void setSubDisplayModelId(Long subDisplayModelId) {
        this.subDisplayModelId = subDisplayModelId;
    }

    public String getSubDisplayModel() {
        return subDisplayModel;
    }

    public void setSubDisplayModel(String subDisplayModel) {
        this.subDisplayModel = subDisplayModel;
    }

    public int getSubDisplayAmount() {
        return subDisplayAmount;
    }

    public void setSubDisplayAmount(int subDisplayAmount) {
        this.subDisplayAmount = subDisplayAmount;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
