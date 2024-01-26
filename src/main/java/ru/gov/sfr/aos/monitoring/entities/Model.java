/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.gov.sfr.aos.monitoring.PrintColorType;
import ru.gov.sfr.aos.monitoring.PrintFormatType;
import ru.gov.sfr.aos.monitoring.dictionaries.DeviceType;

/**
 *
 * @author 041AlikinOS
 */

@Entity
@PrimaryKeyJoinColumn(name = "MODEL_ID")
public class Model implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Поле \"Модель\" не может быть пустым")
    @NotNull
    private String name;
    @NotNull(message = "Поле \"Цветность печати\" не может быть пустым")
    @Enumerated(EnumType.STRING)
    private PrintColorType printColorType;
    @NotNull(message = "Поле \"Формат печати\" не может быть пустым")
    @Enumerated
    private PrintFormatType printFormatType;
    @NotNull(message = "Поле \"Скорость печати\" не может быть пустым")
    private Long printSpeed;
    @NotNull(message = "Поле \"Производитель\" не может быть пустым")
    @ManyToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Printer.class, mappedBy = "model", cascade = CascadeType.ALL)
    private List <Printer> printers = new ArrayList<>();
    @NotNull
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    
    @ManyToMany(mappedBy = "modelsPrinters", fetch = FetchType.EAGER)
    private Set<CartridgeModel> modelCartridges = new HashSet<>();
    
    public Model() {
    }

    public Model(String name, PrintColorType printColorType, PrintFormatType printFormatType, Long printSpeed, Manufacturer manufacturer, DeviceType deviceType) {
        this.name = name;
        this.printColorType = printColorType;
        this.printFormatType = printFormatType;
        this.printSpeed = printSpeed;
        this.manufacturer = manufacturer;
        this.deviceType = deviceType;
    }

    public PrintColorType getPrintColorType() {
        return printColorType;
    }

    public void setPrintColorType(PrintColorType printColorType) {
        this.printColorType = printColorType;
    }

    public PrintFormatType getPrintFormatType() {
        return printFormatType;
    }

    public void setPrintFormatType(PrintFormatType printFormatType) {
        this.printFormatType = printFormatType;
    }

    public Long getPrintSpeed() {
        return printSpeed;
    }

    public void setPrintSpeed(Long printSpeed) {
        this.printSpeed = printSpeed;
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

    public Set<CartridgeModel> getModelCartridges() {
        return modelCartridges;
    }

    public void setModelCartridges(Set<CartridgeModel> modelCartridges) {
        this.modelCartridges = modelCartridges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(List<Printer> printers) {
        this.printers = printers;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
    
    

    @Override
    public String toString() {
        return "Model{" + "name=" + name + ", printColorType=" + printColorType + ", printFormatType=" + printFormatType + ", printSpeed=" + printSpeed + ", manufacturer=" + manufacturer + '}';
    }
    
    
    
}

