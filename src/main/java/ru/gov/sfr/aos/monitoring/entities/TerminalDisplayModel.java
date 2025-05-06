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
public class TerminalDisplayModel extends SvtModel  {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private TerminalDisplayManufacturer manufacturer;
    @OneToMany(targetEntity = TerminalDisplay.class, mappedBy = "terminalDisplayModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TerminalDisplay> terminalDisplay = new HashSet<>();

    public TerminalDisplayModel() {
    }

    public TerminalDisplayManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TerminalDisplayManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<TerminalDisplay> getTerminalDisplay() {
        return terminalDisplay;
    }

    public void setTerminalDisplay(Set<TerminalDisplay> terminalDisplay) {
        this.terminalDisplay = terminalDisplay;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.manufacturer);
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
        final TerminalDisplayModel other = (TerminalDisplayModel) obj;
        return Objects.equals(this.manufacturer, other.manufacturer);
    }

    @Override
    public String toString() {
        return "TerminalDisplayModel{" + "terminalDisplayManufacturer=" + manufacturer + ", terminalDisplay=" + terminalDisplay + '}';
    }
    
    
}
