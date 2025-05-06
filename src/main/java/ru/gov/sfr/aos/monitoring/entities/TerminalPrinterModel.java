package ru.gov.sfr.aos.monitoring.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class TerminalPrinterModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private TerminalPrinterManufacturer manufacturer;
    @OneToMany(targetEntity = TerminalPrinter.class, mappedBy = "terminalPrinterModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TerminalPrinter> terminalPrinter = new HashSet<>();

    public TerminalPrinterModel() {
    }

    public TerminalPrinterManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TerminalPrinterManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<TerminalPrinter> getTerminalPrinter() {
        return terminalPrinter;
    }

    public void setTerminalPrinter(Set<TerminalPrinter> terminalPrinter) {
        this.terminalPrinter = terminalPrinter;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.manufacturer);
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
        final TerminalPrinterModel other = (TerminalPrinterModel) obj;
        return Objects.equals(this.manufacturer, other.manufacturer);
    }

    @Override
    public String toString() {
        return "TerminalPrinterModel{" + "terminalPrinterManufacturer=" + manufacturer + ", terminalPrinter=" + terminalPrinter + '}';
    }
    
    
}
