/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class CartridgeInstallDTO {
    
    public Long idPrinter;
    public Long idCartridge;
    public Long count;
    public String employeeToDoWork;
    public String employeeToSetDevice;
    public String employeeMOL;

    public CartridgeInstallDTO(Long idPrinter, Long idCartridge, Long count, String employeeToDoWork, String employeeToSetDevice, String employeeMOL) {
        this.idPrinter = idPrinter;
        this.idCartridge = idCartridge;
        this.count = count;
        this.employeeToDoWork = employeeToDoWork;
        this.employeeToSetDevice = employeeToSetDevice;
        this.employeeMOL = employeeMOL;
    }

    

    public Long getIdPrinter() {
        return idPrinter;
    }

    public void setIdPrinter(Long idPrinter) {
        this.idPrinter = idPrinter;
    }

    public Long getIdCartridge() {
        return idCartridge;
    }

    public void setIdCartridge(Long idCartridge) {
        this.idCartridge = idCartridge;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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
    
    
    
}
