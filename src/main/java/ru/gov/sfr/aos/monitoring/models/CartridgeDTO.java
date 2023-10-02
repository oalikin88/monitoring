/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.time.LocalDateTime;
import java.util.Date;

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
    public LocalDateTime dateStartExploitation;
    public LocalDateTime dateEndExploitation;
    public Long count;
    public boolean usePrinter;
    public Long contract;
    public String contractNumber;
    public Date startContract;
    public Date endContract;
    public String itemCode;
    public String nameMaterial;
    public String employeeToDoWork;
    public String employeeToSetDevice;
    public String employeeMOL;
    public Long listenerId;
    
    public CartridgeDTO() {
    }

      public CartridgeDTO(Long id, String type, String model, String location, String resource) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.location = location;
        this.resource = resource;
        
    }
    
    public CartridgeDTO(Long id, String type, String model, String location, String resource,
            boolean util, LocalDateTime dateStartExploitation, LocalDateTime dateEndExploitation, Long count,
            boolean usePrinter, Long contract, String contractNumber) {
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
        this.contract = contract;
        this.contractNumber = contractNumber;
    }
    
        public CartridgeDTO(Long id, String type, String model, String location, String resource,
            boolean util, LocalDateTime dateStartExploitation, LocalDateTime dateEndExploitation, Long count,
            boolean usePrinter, Long contract, String contractNumber, Date startContract, Date endContract) {
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
        this.contract = contract;
        this.contractNumber = contractNumber;
        this.startContract = startContract;
        this.endContract = endContract;
    }

    public CartridgeDTO(Long id, String type, String model, String location, String resource, boolean util, LocalDateTime dateStartExploitation, LocalDateTime dateEndExploitation, Long count, boolean usePrinter, Long contract, String contractNumber, Date startContract, Date endContract, String itemCode, String nameMaterial) {
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
        this.contract = contract;
        this.contractNumber = contractNumber;
        this.startContract = startContract;
        this.endContract = endContract;
        this.itemCode = itemCode;
        this.nameMaterial = nameMaterial;
    }

    public CartridgeDTO(String type, String model, String location, String resource, boolean util, LocalDateTime dateStartExploitation, LocalDateTime dateEndExploitation, Long count, boolean usePrinter, Long contract, String contractNumber, Date startContract, Date endContract, String itemCode, String nameMaterial, String employeeToDoWork, String employeeToSetDevice, String employeeMOL) {
        this.type = type;
        this.model = model;
        this.location = location;
        this.resource = resource;
        this.util = util;
        this.dateStartExploitation = dateStartExploitation;
        this.dateEndExploitation = dateEndExploitation;
        this.count = count;
        this.usePrinter = usePrinter;
        this.contract = contract;
        this.contractNumber = contractNumber;
        this.startContract = startContract;
        this.endContract = endContract;
        this.itemCode = itemCode;
        this.nameMaterial = nameMaterial;
        this.employeeToDoWork = employeeToDoWork;
        this.employeeToSetDevice = employeeToSetDevice;
        this.employeeMOL = employeeMOL;
    }

    public CartridgeDTO(Long id, String type, String model, String location, String resource, boolean util, LocalDateTime dateStartExploitation, LocalDateTime dateEndExploitation, Long count, boolean usePrinter, Long contract, String contractNumber, Date startContract, Date endContract, String itemCode, String nameMaterial, String employeeToDoWork, String employeeToSetDevice, String employeeMOL, Long listenerId) {
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
        this.contract = contract;
        this.contractNumber = contractNumber;
        this.startContract = startContract;
        this.endContract = endContract;
        this.itemCode = itemCode;
        this.nameMaterial = nameMaterial;
        this.employeeToDoWork = employeeToDoWork;
        this.employeeToSetDevice = employeeToSetDevice;
        this.employeeMOL = employeeMOL;
        this.listenerId = listenerId;
    }
    
    
    
    

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }
        
        

    public Date getStartContract() {
        return startContract;
    }

    public void setStartContract(Date startContract) {
        this.startContract = startContract;
    }

    public Date getEndContract() {
        return endContract;
    }

    public void setEndContract(Date endContract) {
        this.endContract = endContract;
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

    public LocalDateTime getDateStartExploitation() {
        return dateStartExploitation;
    }

    public void setDateStartExploitation(LocalDateTime dateStartExploitation) {
        this.dateStartExploitation = dateStartExploitation;
    }

    public LocalDateTime getDateEndExploitation() {
        return dateEndExploitation;
    }

    public void setDateEndExploitation(LocalDateTime dateEndExploitation) {
        this.dateEndExploitation = dateEndExploitation;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public boolean getUsePrinter() {
        return usePrinter;
    }

    public void setUsePrinter(boolean usePrinter) {
        this.usePrinter = usePrinter;
    }

    public Long getContract() {
        return contract;
    }

    public void setContract(Long contract) {
        this.contract = contract;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getEmployeeToDoWork() {
        return employeeToDoWork;
    }

    public void setEmployeeToDoWork(String employeeToDoWork) {
        this.employeeToDoWork = employeeToDoWork;
    }

    public String getEmployeeToSetDevice() {
        return employeeToSetDevice;
    }

    public void setEmployeeToSetDevice(String employeeToSetDevice) {
        this.employeeToSetDevice = employeeToSetDevice;
    }

    public String getEmployeeMOL() {
        return employeeMOL;
    }

    public void setEmployeeMOL(String employeeMOL) {
        this.employeeMOL = employeeMOL;
    }

    public Long getListenerId() {
        return listenerId;
    }

    public void setListenerId(Long listenerId) {
        this.listenerId = listenerId;
    }
    
    
    
    
}
