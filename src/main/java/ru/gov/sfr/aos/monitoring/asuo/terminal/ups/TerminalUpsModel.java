package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class TerminalUpsModel extends SvtModel   {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private TerminalUpsManufacturer manufacturer;
    @OneToMany(targetEntity = TerminalUps.class, mappedBy = "terminalUpsModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TerminalUps> terminalUps = new HashSet<>();

    public TerminalUpsModel() {
    }
    
    public TerminalUpsManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TerminalUpsManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<TerminalUps> getTerminalUps() {
        return terminalUps;
    }

    public void setTerminalUps(Set<TerminalUps> terminalUps) {
        this.terminalUps = terminalUps;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.manufacturer);
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
        final TerminalUpsModel other = (TerminalUpsModel) obj;
        return Objects.equals(this.manufacturer, other.manufacturer);
    }

    
    
    
    
}
