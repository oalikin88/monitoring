/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Objects;

/**
 *
 * @author 041AlikinOS
 */
public class PrinterAndCartridgeCountByLocationTable {
    
    private Long locationId;
    private String location;
    private String modelPrinter;
    private Long modelId;
    private int countPrinter;
    private int countCartridge;
    private Long modelCartridgeId;

    public PrinterAndCartridgeCountByLocationTable() {
    }

    public PrinterAndCartridgeCountByLocationTable(Long locationId, String location, String modelPrinter, Long modelId, int countPrinter, int countCartridge, Long modelCartridgeId) {
        this.locationId = locationId;
        this.location = location;
        this.modelPrinter = modelPrinter;
        this.modelId = modelId;
        this.countPrinter = countPrinter;
        this.countCartridge = countCartridge;
        this.modelCartridgeId = modelCartridgeId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModelPrinter() {
        return modelPrinter;
    }

    public void setModelPrinter(String modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public int getCountPrinter() {
        return countPrinter;
    }

    public void setCountPrinter(int countPrinter) {
        this.countPrinter = countPrinter;
    }

    public int getCountCartridge() {
        return countCartridge;
    }

    public void setCountCartridge(int countCartridge) {
        this.countCartridge = countCartridge;
    }

    public Long getModelCartridgeId() {
        return modelCartridgeId;
    }

    public void setModelCartridgeId(Long modelCartridgeId) {
        this.modelCartridgeId = modelCartridgeId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PrinterAndCartridgeCountByLocationTable other = (PrinterAndCartridgeCountByLocationTable) obj;
        if (this.countPrinter != other.countPrinter) {
            return false;
        }
        if (this.countCartridge != other.countCartridge) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.modelPrinter, other.modelPrinter)) {
            return false;
        }
        if (!Objects.equals(this.locationId, other.locationId)) {
            return false;
        }
        if (!Objects.equals(this.modelId, other.modelId)) {
            return false;
        }
        return Objects.equals(this.modelCartridgeId, other.modelCartridgeId);
    }
    
    
    
    
}
