package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

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
public class TerminalServerModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private TerminalServerManufacturer manufacturer;
    @OneToMany(targetEntity = TerminalServer.class, mappedBy = "terminalServerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TerminalServer> terminalServer = new HashSet<>();

    public TerminalServerModel() {
    }

    public TerminalServerManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TerminalServerManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<TerminalServer> getTerminalServer() {
        return terminalServer;
    }

    public void setTerminalServer(Set<TerminalServer> terminalServer) {
        this.terminalServer = terminalServer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.manufacturer);
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
        final TerminalServerModel other = (TerminalServerModel) obj;
        return Objects.equals(this.manufacturer, other.manufacturer);
    }

    @Override
    public String toString() {
        return "TerminalServerModel{" + "terminalServerManufacturer=" + manufacturer + ", terminalServer=" + terminalServer + '}';
    }
    
    
    
}
