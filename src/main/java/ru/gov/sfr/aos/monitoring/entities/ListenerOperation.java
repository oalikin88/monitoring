/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import ru.gov.sfr.aos.monitoring.OperationType;

/**
 *
 * @author 041AlikinOS
 */

@Entity
public class ListenerOperation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateOperation;
    private String currentOperation;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CartridgeModel model;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Location location;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Location locationBeforeTransfer;
    private int amount;
    private int amountAllCartridgesByModel;
    private int amountDevicesOfLocation;
    private int amountCurrentModelOfLocation;
    private int amountDevicesOfTransferedLocation;
    private int amountCurrentModelOfTransferedLocation;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private Long printerID;
    private Long cartridgeID;
    private String employeeToDoWork;
    private String employeeToSetDevice;
    private String employeeMOL;
    
    
    public ListenerOperation() {
    }

    public ListenerOperation(LocalDateTime dateOperation, String currentOperation, CartridgeModel model, Location location, Location locationBeforeTransfer, int amount, int amountAllCartridgesByModel, int amountDevicesOfLocation, int amountCurrentModelOfLocation, int amountDevicesOfTransferedLocation, int amountCurrentModelOfTransferedLocation, OperationType operationType, Long printerID, Long cartridgeID, String employeeToDoWork, String employeeToSetDevice, String employeeMOL) {
        this.dateOperation = dateOperation;
        this.currentOperation = currentOperation;
        this.model = model;
        this.location = location;
        this.locationBeforeTransfer = locationBeforeTransfer;
        this.amount = amount;
        this.amountAllCartridgesByModel = amountAllCartridgesByModel;
        this.amountDevicesOfLocation = amountDevicesOfLocation;
        this.amountCurrentModelOfLocation = amountCurrentModelOfLocation;
        this.amountDevicesOfTransferedLocation = amountDevicesOfTransferedLocation;
        this.amountCurrentModelOfTransferedLocation = amountCurrentModelOfTransferedLocation;
        this.operationType = operationType;
        this.printerID = printerID;
        this.cartridgeID = cartridgeID;
        this.employeeToDoWork = employeeToDoWork;
        this.employeeToSetDevice = employeeToSetDevice;
        this.employeeMOL = employeeMOL;
    }

 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    
    public String getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(String operation) {
        this.currentOperation = operation;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getAmountDevicesOfLocation() {
        return amountDevicesOfLocation;
    }

    public void setAmountDevicesOfLocation(int amountDevicesOfLocation) {
        this.amountDevicesOfLocation = amountDevicesOfLocation;
    }

    public CartridgeModel getModel() {
        return model;
    }

    public void setModel(CartridgeModel model) {
        this.model = model;
    }

    public int getAmountCurrentModelOfLocation() {
        return amountCurrentModelOfLocation;
    }

    public void setAmountCurrentModelOfLocation(int amountCurrentModelOfLocation) {
        this.amountCurrentModelOfLocation = amountCurrentModelOfLocation;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
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

    public Long getPrinterID() {
        return printerID;
    }

    public void setPrinterID(Long printerID) {
        this.printerID = printerID;
    }

    public Long getCartridgeID() {
        return cartridgeID;
    }

    public void setCartridgeID(Long cartridgeID) {
        this.cartridgeID = cartridgeID;
    }

    public int getAmountDevicesOfTransferedLocation() {
        return amountDevicesOfTransferedLocation;
    }

    public void setAmountDevicesOfTransferedLocation(int amountDevicesOfTransferedLocation) {
        this.amountDevicesOfTransferedLocation = amountDevicesOfTransferedLocation;
    }

    public int getAmountCurrentModelOfTransferedLocation() {
        return amountCurrentModelOfTransferedLocation;
    }

    public void setAmountCurrentModelOfTransferedLocation(int amountCurrentModelOfTransferedLocation) {
        this.amountCurrentModelOfTransferedLocation = amountCurrentModelOfTransferedLocation;
    }

    public Location getLocationBeforeTransfer() {
        return locationBeforeTransfer;
    }

    public void setLocationBeforeTransfer(Location locationBeforeTransfer) {
        this.locationBeforeTransfer = locationBeforeTransfer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountAllCartridgesByModel() {
        return amountAllCartridgesByModel;
    }

    public void setAmountAllCartridgesByModel(int amountAllCartridgesByModel) {
        this.amountAllCartridgesByModel = amountAllCartridgesByModel;
    }
    
    

    
}
