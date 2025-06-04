/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.transfer;

import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Alikin Oleg
 */
@Entity
@PrimaryKeyJoinColumn(name = "TRANSFER_ID")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateTransfer;
    private String inventaryNumberOld;
    private String inventaryNumberNew;
    private String transferFrom;
    private String transferTo;
    private String documentNumber;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ObjectBuing objectBuing;

    public Transfer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }

    public String getInventaryNumberOld() {
        return inventaryNumberOld;
    }

    public void setInventaryNumberOld(String inventaryNumberOld) {
        this.inventaryNumberOld = inventaryNumberOld;
    }

    public String getInventaryNumberNew() {
        return inventaryNumberNew;
    }

    public void setInventaryNumberNew(String inventaryNumberNew) {
        this.inventaryNumberNew = inventaryNumberNew;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public String getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(String transferTo) {
        this.transferTo = transferTo;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public ObjectBuing getObjectBuing() {
        return objectBuing;
    }

    public void setObjectBuing(ObjectBuing objectBuing) {
        this.objectBuing = objectBuing;
    }

    @Override
    public String toString() {
        return "Transfer{" + "id=" + id + ", dateTransfer=" + dateTransfer + ", inventaryNumberOld=" + inventaryNumberOld + ", inventaryNumberNew=" + inventaryNumberNew + ", transferFrom=" + transferFrom + ", transferTo=" + transferTo + ", documentNumber=" + documentNumber + ", objectBuing=" + objectBuing + '}';
    }
    
    
}
