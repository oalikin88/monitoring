/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.transfer;

import java.util.Date;

/**
 *
 * @author Alikin Oleg
 */
public class TransferDto {
    private Long id;
    private Long idObjectBuing;
    private Date dateTransfer;
    private String inventaryNumberOld;
    private String inventaryNumberNew;
    private String transferFrom;
    private String transferTo;
    private String documentNumber;

    public TransferDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdObjectBuing() {
        return idObjectBuing;
    }

    public void setIdObjectBuing(Long idObjectBuing) {
        this.idObjectBuing = idObjectBuing;
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

    @Override
    public String toString() {
        return "TransferDto{" + "id=" + id + ", idObjectBuing=" + idObjectBuing + ", dateTransfer=" + dateTransfer + ", inventaryNumberOld=" + inventaryNumberOld + ", inventaryNumberNew=" + inventaryNumberNew + ", transferFrom=" + transferFrom + ", transferTo=" + transferTo + ", documentNumber=" + documentNumber + '}';
    }
    
    
}
