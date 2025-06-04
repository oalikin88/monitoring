/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.switchhub;

/**
 *
 * @author Alikin Oleg
 */
public class SwitchHubDto {
    private Long id;
    private String model;
    private int portAmount;
    private String type;

    public SwitchHubDto() {
    }

    public SwitchHubDto(Long id, String model, int portAmount, String type) {
        this.id = id;
        this.model = model;
        this.portAmount = portAmount;
        this.type = type;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPortAmount() {
        return portAmount;
    }

    public void setPortAmount(int portAmount) {
        this.portAmount = portAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
