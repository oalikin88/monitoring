/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "CARTRIDGE_MANUFACTURER_ID")
public class CartridgeManufacturer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotEmpty(message = "Поле \"Производитель\" не может быть пустым")
    private String manufacturerName;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = CartridgeModel.class, mappedBy = "cartridgeManufacturer", cascade = CascadeType.ALL)
    private List<CartridgeModel> modelsList = new ArrayList<>();

    public CartridgeManufacturer() {
    }

    public CartridgeManufacturer(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<CartridgeModel> getModelsList() {
        return modelsList;
    }

    public void setModelsList(List<CartridgeModel> modelsList) {
        this.modelsList = modelsList;
    }
    
    
}
