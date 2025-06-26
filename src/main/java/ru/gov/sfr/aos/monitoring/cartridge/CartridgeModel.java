package ru.gov.sfr.aos.monitoring.cartridge;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.printer.PrinterModel;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class CartridgeModel extends SvtModel {

    @NotNull(message = "Поле \"тип картриджа\" не может быть пустым")
    @Enumerated(EnumType.STRING)
    private CartridgeType type;
    @NotNull(message = "Поле \"Номинальный ресурс\" не должно быть пустым")
    private Long defaultNumberPrintPage;
    @OneToMany(targetEntity = Cartridge.class, mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cartridge> cartridges;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cartridge_model_models_printers", joinColumns = @JoinColumn(name = "model_cartridges_id"), inverseJoinColumns = @JoinColumn(name = "models_printers_id"))
    private List<PrinterModel> modelsPrinters = new ArrayList<>();
    @NotNull(message = "Поле \"Производитель\" не может быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CartridgeManufacturer manufacturer;

    public CartridgeModel() {
    }

    public CartridgeType getType() {
        return type;
    }

    public void setType(CartridgeType type) {
        this.type = type;
    }

    public Long getDefaultNumberPrintPage() {
        return defaultNumberPrintPage;
    }

    public void setDefaultNumberPrintPage(Long defaultNumberPrintPage) {
        this.defaultNumberPrintPage = defaultNumberPrintPage;
    }

    public List<Cartridge> getCartridges() {
        return cartridges;
    }

    public void setCartridges(List<Cartridge> cartridges) {
        this.cartridges = cartridges;
    }

    public List<PrinterModel> getModelsPrinters() {
        return modelsPrinters;
    }

    public void setModelsPrinters(List<PrinterModel> modelsPrinters) {
        this.modelsPrinters = modelsPrinters;
    }

    public CartridgeManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(CartridgeManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    
    
    
    
}
