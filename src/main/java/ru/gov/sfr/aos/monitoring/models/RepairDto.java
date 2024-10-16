/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;

/**
 *
 * @author Alikin Oleg
 */
public class RepairDto {
    private Long id;
    private Long idObjectBuing;
    private Date dateRepair;
    private String documentNumber;
    private String definition;

    public RepairDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateRepair() {
        return dateRepair;
    }

    public void setDateRepair(Date dateRepair) {
        this.dateRepair = dateRepair;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Long getIdObjectBuing() {
        return idObjectBuing;
    }

    public void setIdObjectBuing(Long idObjectBuing) {
        this.idObjectBuing = idObjectBuing;
    }

    @Override
    public String toString() {
        return "RepairDto{" + "id=" + id + ", idObjectBuing=" + idObjectBuing + ", dateRepair=" + dateRepair + ", documentNumber=" + documentNumber + ", definition=" + definition + '}';
    }

    
    
    
    
}
