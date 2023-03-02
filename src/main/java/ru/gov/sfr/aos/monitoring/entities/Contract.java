/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 041AlikinOS
 */

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @OneToMany(targetEntity = ObjectBuing.class, mappedBy = "contract", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List <ObjectBuing> objectBuing;
    @NotNull
    private Long contractNumber;
    @NotNull
    private Date dateStartContract;
    @NotNull
    private Date dateEndContract;
    
    

    public Contract() {
    }
    
    
    public Contract(List <ObjectBuing> objectBuing, Long contractNumber, Date dateStartContract, Date dateEndContract) {
        this.objectBuing = objectBuing;
        this.contractNumber = contractNumber;
        this.dateStartContract = dateStartContract;
        this.dateEndContract = dateEndContract;
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getDateStartContract() {
        return dateStartContract;
    }

    public void setDateStartContract(Date dateStartContract) {
        this.dateStartContract = dateStartContract;
    }

    public Date getDateEndContract() {
        return dateEndContract;
    }

    public void setDateEndContract(Date dateEndContract) {
        this.dateEndContract = dateEndContract;
    }

    public List<ObjectBuing> getObjectBuing() {
        return objectBuing;
    }

    public void setObjectBuing(List<ObjectBuing> objectBuing) {
        this.objectBuing = objectBuing;
    }

    

    @Override
    public String toString() {
        return "ID: " + this.id + "; Contract №: " + this.contractNumber + "; ObjectBuing: " + this.objectBuing +  
                "; Date start contract: " + this.dateStartContract + "; Date end contract: " + this.dateEndContract + ".";
    }

  
    
    

    
}
