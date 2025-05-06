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
public class TerminalSensorModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private TerminalSensorManufacturer manufacturer;
    @OneToMany(targetEntity = TerminalPrinter.class, mappedBy = "terminalPrinterModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TerminalPrinter> terminalPrinter = new HashSet<>();

    public TerminalSensorModel() {
    }

    public TerminalSensorManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TerminalSensorManufacturer manufacturer) {
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
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.manufacturer);
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
        final TerminalSensorModel other = (TerminalSensorModel) obj;
        return Objects.equals(this.manufacturer, other.manufacturer);
    }

    @Override
    public String toString() {
        return "TerminalSensorModel{" + "terminalSensorManufacturer=" + manufacturer + ", terminalPrinter=" + terminalPrinter + '}';
    }
    
    
}
