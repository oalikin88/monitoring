/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.contract;

import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author 041AlikinOS
 */

@Entity
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(targetEntity = ObjectBuing.class, mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <ObjectBuing> objectBuing;
    @NotEmpty(message = "Поле \"Номер контракта\" не должно быть пустым")
    private String contractNumber; 
    @NotNull(message = "Дата начала контракта не может быть пустой")
    @Past(message = "Дата начала контракта не может быть позже сегодняшнего дня")
    private Date dateStartContract;
    @NotNull(message = "Дата окончания контракта не может быть пустой")
    private Date dateEndContract;
   

    public Contract() {
    }
    
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
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
