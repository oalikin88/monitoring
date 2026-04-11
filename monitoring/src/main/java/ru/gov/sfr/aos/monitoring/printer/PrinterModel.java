package ru.gov.sfr.aos.monitoring.printer;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import ru.gov.sfr.aos.monitoring.cartridge.CartridgeModel;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class PrinterModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private PrinterManufacturer manufacturer;
    @OneToMany(targetEntity = Printer.class, mappedBy = "printerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Printer> printers = new HashSet<>();
    private int printSpeed;
    private PrintType printColorType;
    private PrintFormat printFormat;
    @ManyToMany(mappedBy = "modelsPrinters", fetch = FetchType.LAZY)
    private Set<CartridgeModel> modelCartridges = new HashSet<>();

    public PrinterModel() {
    }

    public PrinterModel(String model) {
        super(model);
    }
    
    

    public PrinterManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(PrinterManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(Set<Printer> printers) {
        this.printers = printers;
    }

    public int getPrintSpeed() {
        return printSpeed;
    }

    public void setPrintSpeed(int printSpeed) {
        this.printSpeed = printSpeed;
    }

    public PrintType getPrintColorType() {
        return printColorType;
    }

    public void setPrintColorType(PrintType printColorType) {
        this.printColorType = printColorType;
    }

    public PrintFormat getPrintFormat() {
        return printFormat;
    }

    public void setPrintFormat(PrintFormat printFormat) {
        this.printFormat = printFormat;
    }

    public Set<CartridgeModel> getModelCartridges() {
        return modelCartridges;
    }

    public void setModelCartridges(Set<CartridgeModel> modelCartridges) {
        this.modelCartridges = modelCartridges;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.manufacturer);
        hash = 89 * hash + this.printSpeed;
        hash = 89 * hash + Objects.hashCode(this.printColorType);
        hash = 89 * hash + Objects.hashCode(this.printFormat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PrinterModel other = (PrinterModel) obj;
        if (this.printSpeed != other.printSpeed) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (this.printColorType != other.printColorType) {
            return false;
        }
        return this.printFormat == other.printFormat;
    }
    
    
    
}
