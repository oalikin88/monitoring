/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "THERMO_PRINTER_ID")
public class ThermoPrinter extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ThermoPrinterModel thermoPrinterModel;

    public ThermoPrinter() {
    }

    public ThermoPrinterModel getThermoPrinterModel() {
        return thermoPrinterModel;
    }

    public void setThermoPrinterModel(ThermoPrinterModel thermoPrinterModel) {
        this.thermoPrinterModel = thermoPrinterModel;
    }

    @Override
    public String toString() {
        return "ThermoPrinter{" + "id=" + this.id + ", serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + ", thermoPrinterModel=" + thermoPrinterModel + '}';
    }
    
    
}
