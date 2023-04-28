/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "MANUFACTURER_ID")
public class Manufacturer {
    
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Model.class, mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Model> modelsList = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Printer.class, mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List <Printer> printers = new ArrayList<>();

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

    public List<Printer> getPrintersList() {
        return printers;
    }

    public void setPrintersList(List<Printer> printers) {
        this.printers = printers;
    }

    public void addPrinter(Printer printer) {
        printers.add(printer);
    }
    
    
}
