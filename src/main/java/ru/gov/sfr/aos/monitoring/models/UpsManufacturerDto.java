/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alikin Oleg
 */
public class UpsManufacturerDto {
    private Long id;
    private String name;
    private List<UpsModelDto> modelsUps = new ArrayList<>();

    public UpsManufacturerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UpsModelDto> getModelsUps() {
        return modelsUps;
    }

    public void setModelsUps(List<UpsModelDto> modelsUps) {
        this.modelsUps = modelsUps;
    }
    
    
}
