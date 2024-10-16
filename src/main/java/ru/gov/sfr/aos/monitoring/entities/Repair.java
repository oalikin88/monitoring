/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

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
@PrimaryKeyJoinColumn(name = "REPAIR_ID")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateRepair;
    private String documentNumber;
    private String definition; 
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ObjectBuing objectBuing;

    public Repair() {
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

    public ObjectBuing getObjectBuing() {
        return objectBuing;
    }

    public void setObjectBuing(ObjectBuing objectBuing) {
        this.objectBuing = objectBuing;
    }

    @Override
    public String toString() {
        return "Repair{" + "id=" + id + ", dateRepair=" + dateRepair + ", documentNumber=" + documentNumber + ", definition=" + definition + ", objectBuing=" + objectBuing + '}';
    }


    
    
}
