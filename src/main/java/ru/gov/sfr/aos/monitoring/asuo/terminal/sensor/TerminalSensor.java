package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import ru.gov.sfr.aos.monitoring.asuo.terminal.Terminal;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;

/**
 *
 * @author Alikin Oleg
 */
@Entity
@PrimaryKeyJoinColumn(name = "TERMINAL_SENSOR_ID")
public class TerminalSensor extends ObjectBuing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private String serialNumber;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TerminalSensorModel terminalSensorModel;
    @OneToOne(
            mappedBy = "terminalSensor",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Terminal terminal;

    public TerminalSensor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public TerminalSensorModel getTerminalSensorModel() {
        return terminalSensorModel;
    }

    public void setTerminalSensorModel(TerminalSensorModel terminalSensorModel) {
        this.terminalSensorModel = terminalSensorModel;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.status);
        hash = 83 * hash + Objects.hashCode(this.serialNumber);
        hash = 83 * hash + Objects.hashCode(this.terminalSensorModel);
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
        final TerminalSensor other = (TerminalSensor) obj;
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return Objects.equals(this.terminalSensorModel, other.terminalSensorModel);
    }

    @Override
    public String toString() {
        return "TerminalSensor{" + "id=" + id + ", status=" + status + ", serialNumber=" + serialNumber + ", terminalSensorModel=" + terminalSensorModel + ", terminals=" + terminal + '}';
    }
    
    
    
}
