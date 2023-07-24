/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.CartridgeType;

/**
 *
 * @author 041AlikinOS
 */

@Entity
public class CartridgeModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String model;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private CartridgeType type;
    @NotNull
    private Long defaultNumberPrintPage;
    @OneToMany(targetEntity = Cartridge.class, mappedBy = "model", cascade = CascadeType.ALL)
    private List<Cartridge> cartridges;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cartridge_model_models_printers", joinColumns = @JoinColumn(name = "model_cartridges_id"), inverseJoinColumns = @JoinColumn(name = "models_printers_id"))
    private List<Model> modelsPrinters = new ArrayList<>();
    public CartridgeModel() {
    }

    public CartridgeModel(String model, CartridgeType type, Long defaultNumberPrintPage, List<Cartridge> cartridges) {
        this.model = model;
        this.type = type;
        this.defaultNumberPrintPage = defaultNumberPrintPage;
        this.cartridges = cartridges;
    }

    public CartridgeModel(String model, CartridgeType type, Long defaultNumberPrintPage, List<Cartridge> cartridges, List<Model> modelsPrinters) {
        this.model = model;
        this.type = type;
        this.defaultNumberPrintPage = defaultNumberPrintPage;
        this.cartridges = cartridges;
        this.modelsPrinters = modelsPrinters;
    }
    
    

    public Long getDefaultNumberPrintPage() {
        return defaultNumberPrintPage;
    }

    public void setDefaultNumberPrintPage(Long defaultNumberPrintPage) {
        this.defaultNumberPrintPage = defaultNumberPrintPage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Cartridge> getCartridges() {
        return cartridges;
    }

    public void setCartridges(List<Cartridge> cartridges) {
        this.cartridges = cartridges;
    }

    public CartridgeType getType() {
        return type;
    }

    public void setType(CartridgeType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Model> getModelsPrinters() {
        return modelsPrinters;
    }

    public void setModelsPrinters(List<Model> modelsPrinters) {
        this.modelsPrinters = modelsPrinters;
    }
    
    
    
    
    
}
