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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class UpsManufacturer extends ManufacturerModel<UpsModel> implements Serializable {
    
    private Long id;
    @NotBlank(message = "Поле \"Производитель\" не должно быть пустым")
    @NotEmpty(message = "Поле \"Производитель\" не должно быть пустым")
    private String name;
    private boolean archived;
    
    @OneToMany(targetEntity = UpsModel.class, mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UpsModel> models = new ArrayList<>();
    

    public UpsManufacturer() {
    }

    public UpsManufacturer(Long id, String name, boolean archived) {
        this.id = id;
        this.name = name;
        this.archived = archived;
    }
    
    
  

    @Override
    public Long getId() {
        return id;
    }

  
    @Override
    public void setId(Long id) {
        this.id = id;
    }

 
    @Override
    public String getName() {
        return name;
    }

  
    @Override
    public void setName(String name) {
        this.name = name;
    }

 
    @Override
    public boolean isArchived() {
        return archived;
    }

    
    @Override
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public List<UpsModel> getModels() {
        return models;
    }

    public void setModels(List<UpsModel> models) {
        this.models = models;
    }

    
    
    
}
