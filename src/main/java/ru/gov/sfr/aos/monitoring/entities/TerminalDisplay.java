package ru.gov.sfr.aos.monitoring.entities;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;

/**
 *
 * @author Alikin Oleg
 */
@Entity
@PrimaryKeyJoinColumn(name = "TERMINAL_DISPLAY_ID")
public class TerminalDisplay extends ObjectBuing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double screenDiagonal;
    private Status status;
    private String serialNumber;
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TerminalDisplayModel terminalDisplayModel;
    
    @OneToOne(
            mappedBy = "terminalDisplay",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Terminal terminal;

    public TerminalDisplay() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getScreenDiagonal() {
        return screenDiagonal;
    }

    public void setScreenDiagonal(double screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TerminalDisplayModel getTerminalDisplayModel() {
        return terminalDisplayModel;
    }

    public void setTerminalDisplayModel(TerminalDisplayModel terminalDisplayModel) {
        this.terminalDisplayModel = terminalDisplayModel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.screenDiagonal) ^ (Double.doubleToLongBits(this.screenDiagonal) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Objects.hashCode(this.terminalDisplayModel);
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
        final TerminalDisplay other = (TerminalDisplay) obj;
        if (Double.doubleToLongBits(this.screenDiagonal) != Double.doubleToLongBits(other.screenDiagonal)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return Objects.equals(this.terminalDisplayModel, other.terminalDisplayModel);
    }

    @Override
    public String toString() {
        return "TerminalDisplay{" + "id=" + id + ", screenDiagonal=" + screenDiagonal + ", status=" + status + ", terminalDisplayModel=" + terminalDisplayModel + '}';
    }
    
    
}
