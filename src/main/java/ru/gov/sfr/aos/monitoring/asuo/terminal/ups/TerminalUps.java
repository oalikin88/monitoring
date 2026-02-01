package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import ru.gov.sfr.aos.monitoring.asuo.terminal.Terminal;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;

/**
 *
 * @author Alikin Oleg
 */
@Entity
@PrimaryKeyJoinColumn(name = "TERMINAL_UPS_ID")
public class TerminalUps extends ObjectBuing {
    private String serialNumber;
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TerminalUpsModel terminalUpsModel;
    private Status status;
    @OneToOne(
            mappedBy = "terminalUps",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Terminal terminal;

    public TerminalUps() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public TerminalUpsModel getTerminalUpsModel() {
        return terminalUpsModel;
    }

    public void setTerminalUpsModel(TerminalUpsModel terminalUpsModel) {
        this.terminalUpsModel = terminalUpsModel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.serialNumber);
        hash = 29 * hash + Objects.hashCode(this.terminalUpsModel);
        hash = 29 * hash + Objects.hashCode(this.status);
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
        final TerminalUps other = (TerminalUps) obj;
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.terminalUpsModel, other.terminalUpsModel)) {
            return false;
        }
        return this.status == other.status;
    }

    @Override
    public String toString() {
        return "TerminalUps{" + "serialNumber=" + serialNumber + ", terminalUpsModel=" + terminalUpsModel + ", status=" + status + ", terminal=" + terminal + '}';
    }

    
    
    
}
