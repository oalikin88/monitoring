/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 041AlikinOS
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ObjectBuing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTRACT_ID",
			foreignKey = @ForeignKey(name = "CONTRACT_ID_FK"))
    protected Contract contract;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Place place;
    
    protected boolean archived;
     @OneToMany(targetEntity = Repair.class, mappedBy = "objectBuing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Repair> repairs = new ArrayList<>();
     @OneToMany(targetEntity = Transfer.class, mappedBy = "objectBuing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Transfer> transfers = new ArrayList<>();
    
    protected ObjectBuing() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }


    
    
}
