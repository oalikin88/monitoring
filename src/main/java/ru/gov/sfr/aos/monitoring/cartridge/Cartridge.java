package ru.gov.sfr.aos.monitoring.cartridge;

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

import ru.gov.sfr.aos.monitoring.printer.Printer;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;

/**
 *
 * @author Alikin Oleg
 */
@Entity
@PrimaryKeyJoinColumn(name = "CARTRIDGE_ID")
public class Cartridge extends ObjectBuing implements Serializable {

    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CartridgeModel model;
    
    private LocalDateTime dateStartExploitation;
    
    private LocalDateTime dateEndExploitation;
    
    private boolean util;
    
    private boolean useInPrinter;
    
    private Long count;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRINTER_ID",
			foreignKey = @ForeignKey(name = "PRINTER_ID_FK"))
    private Printer printer;
    @NotEmpty(message = "Поле \"Номенклатурный номер\" не может быть пустым")
    private String itemCode;
    @NotEmpty(message = "Поле \"Наименование расходного материала\" не может быть пустым")
    private String nameMaterial;

    public Cartridge() {
    }

    public CartridgeModel getModel() {
        return model;
    }

    public void setModel(CartridgeModel model) {
        this.model = model;
    }

    public LocalDateTime getDateStartExploitation() {
        return dateStartExploitation;
    }

    public void setDateStartExploitation(LocalDateTime dateStartExploitation) {
        this.dateStartExploitation = dateStartExploitation;
    }

    public LocalDateTime getDateEndExploitation() {
        return dateEndExploitation;
    }

    public void setDateEndExploitation(LocalDateTime dateEndExploitation) {
        this.dateEndExploitation = dateEndExploitation;
    }

    public boolean isUtil() {
        return util;
    }

    public void setUtil(boolean util) {
        this.util = util;
    }

    public boolean isUseInPrinter() {
        return useInPrinter;
    }

    public void setUseInPrinter(boolean useInPrinter) {
        this.useInPrinter = useInPrinter;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
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
    
    
    
    
}
