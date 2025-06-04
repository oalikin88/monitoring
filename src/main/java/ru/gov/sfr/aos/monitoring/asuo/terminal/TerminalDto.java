package ru.gov.sfr.aos.monitoring.asuo.terminal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;

/**
 *
 * @author Alikin Oleg
 */

public class TerminalDto extends SvtDTO {
    public Long terminalDisplayId;
    public Long terminalSensorId;
    public Long terminalServerId;
    public Long terminalPrinterId;
    public Long terminalUpsId;
    public Set <Long> asuos = new HashSet<>();

    public TerminalDto() {
    }

    public Long getTerminalDisplayId() {
        return terminalDisplayId;
    }

    public void setTerminalDisplayId(Long terminalDisplayId) {
        this.terminalDisplayId = terminalDisplayId;
    }

    public Long getTerminalSensorId() {
        return terminalSensorId;
    }

    public void setTerminalSensorId(Long terminalSensorId) {
        this.terminalSensorId = terminalSensorId;
    }

    public Long getTerminalServerId() {
        return terminalServerId;
    }

    public void setTerminalServerId(Long terminalServerId) {
        this.terminalServerId = terminalServerId;
    }

    public Long getTerminalPrinterId() {
        return terminalPrinterId;
    }

    public void setTerminalPrinterId(Long terminalPrinterId) {
        this.terminalPrinterId = terminalPrinterId;
    }

    public Long getTerminalUpsId() {
        return terminalUpsId;
    }

    public void setTerminalUpsId(Long terminalUpsId) {
        this.terminalUpsId = terminalUpsId;
    }

    public Set<Long> getAsuos() {
        return asuos;
    }

    public void setAsuos(Set<Long> asuos) {
        this.asuos = asuos;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.terminalDisplayId);
        hash = 47 * hash + Objects.hashCode(this.terminalSensorId);
        hash = 47 * hash + Objects.hashCode(this.terminalServerId);
        hash = 47 * hash + Objects.hashCode(this.terminalPrinterId);
        hash = 47 * hash + Objects.hashCode(this.terminalUpsId);
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
        final TerminalDto other = (TerminalDto) obj;
        if (!Objects.equals(this.terminalDisplayId, other.terminalDisplayId)) {
            return false;
        }
        if (!Objects.equals(this.terminalSensorId, other.terminalSensorId)) {
            return false;
        }
        if (!Objects.equals(this.terminalServerId, other.terminalServerId)) {
            return false;
        }
        if (!Objects.equals(this.terminalPrinterId, other.terminalPrinterId)) {
            return false;
        }
        return Objects.equals(this.terminalUpsId, other.terminalUpsId);
    }

    @Override
    public String toString() {
        return "TerminalDto{" + "terminalDisplayModelId=" + terminalDisplayId + ", terminalSensorModelId=" + terminalSensorId + ", terminalServerModelId=" + terminalServerId + ", terminalPrinterModelId=" + terminalPrinterId + ", terminalUpsModelId=" + terminalUpsId + '}';
    }
    
      
}
