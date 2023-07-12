/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.time.LocalDateTime;

/**
 *
 * @author 041AlikinOS
 */
public class CartridgeDTO {
    public Long id;
    public String type;
    public String model;
    public String location;
    public String resource;
    public boolean util;
    public String dateStartExploitation;
    public String dateEndExploitation;
    public Long count;
    public String usePrinter;
    
    
    public CartridgeDTO() {
    }

      public CartridgeDTO(Long id, String type, String model, String location, String resource) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.location = location;
        this.resource = resource;
    }
    
    public CartridgeDTO(Long id, String type, String model, String location, String resource, boolean util, String dateStartExploitation, String dateEndExploitation, Long count, String usePrinter) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.location = location;
        this.resource = resource;
        this.util = util;
        this.dateStartExploitation = dateStartExploitation;
        this.dateEndExploitation = dateEndExploitation;
        this.count = count;
        this.usePrinter = usePrinter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isUtil() {
        return util;
    }

    public void setUtil(boolean util) {
        this.util = util;
    }

    public String getDateStartExploitation() {
        return dateStartExploitation;
    }

    public void setDateStartExploitation(String dateStartExploitation) {
        this.dateStartExploitation = dateStartExploitation;
    }

    public String getDateEndExploitation() {
        return dateEndExploitation;
    }

    public void setDateEndExploitation(String dateEndExploitation) {
        this.dateEndExploitation = dateEndExploitation;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getUsePrinter() {
        return usePrinter;
    }

    public void setUsePrinter(String usePrinter) {
        this.usePrinter = usePrinter;
    }
    
    
    
}
