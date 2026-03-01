/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.svtobject;

import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import java.util.Objects;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;

/**
 *
 * @author 041AlikinOS
 */
@MappedSuperclass
public abstract class ObjectBuingWithSerialAndInventary extends ObjectBuing {
    @Enumerated(EnumType.STRING)
    protected Status status;
    protected String inventaryNumber;
    protected String serialNumber;
   

    protected ObjectBuingWithSerialAndInventary() {
        super();
    }
    

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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


  
    
 
}
