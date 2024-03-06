/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;


/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "MANUFACTURER_ID")
public class Manufacturer implements Serializable {
    
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле \"Производитель\" не может быть пустым")
    private String name;
    @OneToMany(targetEntity = Model.class, mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Model> modelsList = new ArrayList<>();


    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModelList() {
        return modelsList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelsList = modelList;
        
    }
    public void addModel(Model model) {
        modelsList.add(model);
    }


    @Override
    public String toString() {
        return "Manufacturer{" +  "name=" + name +  '}';
    }

    @Override
    public int hashCode() {
        int hash = Long.hashCode(this.id);
        hash = 31 * hash + (this.name == null ? 0 : this.name.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Manufacturer other = (Manufacturer) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        return this.id == other.id;
    }
    
    
    
}
