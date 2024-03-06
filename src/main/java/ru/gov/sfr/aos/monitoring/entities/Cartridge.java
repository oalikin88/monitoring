/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 041AlikinOS
 */

@Entity
@PrimaryKeyJoinColumn(name = "CARTRIDGE_ID")
public class Cartridge extends ObjectBuing implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected CartridgeModel model;
    
    protected LocalDateTime dateStartExploitation;
    
    protected LocalDateTime dateEndExploitation;
    
    protected boolean util;
    
    protected boolean useInPrinter;
    
    protected Long count;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRINTER_ID",
			foreignKey = @ForeignKey(name = "PRINTER_ID_FK"))
    protected Printer printer;
    @NotEmpty(message = "Поле \"Номенклатурный номер\" не может быть пустым")
    protected String itemCode;
    @NotEmpty(message = "Поле \"Наименование расходного материала\" не может быть пустым")
    protected String nameMaterial;
    
    public Cartridge() {
        
    }
   
    public Cartridge(CartridgeModel model, LocalDateTime dateStartExploitation, 
            LocalDateTime dateEndExploitation, boolean util, boolean useInPrinter, Long count, String itemCode, String nameMaterial) {
        this.model = model;
        this.dateStartExploitation = dateStartExploitation;
        this.dateEndExploitation = dateEndExploitation;
        this.util = util;
        this.count = count;
        this.useInPrinter = useInPrinter;
        this.itemCode = itemCode;
        this.nameMaterial = nameMaterial;
        
    } 
    
    
  
    
    

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }

    
    
    public boolean isUseInPrinter() {
        return useInPrinter;
    }

    public void setUseInPrinter(boolean useInPrinter) {
        this.useInPrinter = useInPrinter;
    }

    
    
    public Long getId() {
        return id;
    }



    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public LocalDateTime getDateStartExploitation() {
        return dateStartExploitation;
    }

    public LocalDateTime getDateEndExploitation() {
        return dateEndExploitation;
    }

    public boolean isUtil() {
        return util;
    }

    public Long getCount() {
        return count;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    
    public Printer getPrinter() {
        return printer;
    }

    
    
    


    public void setDateStartExploitation(LocalDateTime dateStartExploitation) {
        this.dateStartExploitation = dateStartExploitation;
    }

    public void setDateEndExploitation(LocalDateTime dateEndExploitation) {
        this.dateEndExploitation = dateEndExploitation;
    }

    public void setUtil(boolean util) {
        this.util = util;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public CartridgeModel getModel() {
        return model;
    }

    public void setModel(CartridgeModel model) {
        this.model = model;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
    
    
    
    @Override
    public String toString() {
        return "Model: " + this.model.getModel() + "; Type: " + this.model.getType().getName() + "; Location: " + this.location +
                "; Date start exploitation: " + this.dateStartExploitation + "; Date end exploitation: " + this.dateEndExploitation 
                + "; Count: " + this.count + "; Default number print page: " + this.model.getDefaultNumberPrintPage();
    }

}
