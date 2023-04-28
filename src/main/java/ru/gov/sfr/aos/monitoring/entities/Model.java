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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author user
 */

@Entity
@PrimaryKeyJoinColumn(name = "MODEL_ID")
public class Model {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Printer.class, mappedBy = "model", cascade = CascadeType.ALL)
    private List <Printer> printers = new ArrayList<>();

    public Model() {
    }

    public Model(String name, Manufacturer manufacturer, List<Printer> printer) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.printers = printer;
    }

    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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

