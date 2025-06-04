/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo.terminal;

import ru.gov.sfr.aos.monitoring.asuo.Asuo;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.asuo.terminal.display.TerminalDisplay;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventary;
import ru.gov.sfr.aos.monitoring.asuo.terminal.printer.TerminalPrinter;
import ru.gov.sfr.aos.monitoring.asuo.terminal.sensor.TerminalSensor;
import ru.gov.sfr.aos.monitoring.asuo.terminal.server.TerminalServer;
import ru.gov.sfr.aos.monitoring.asuo.terminal.ups.TerminalUps;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "TERMINAL_ID")
public class Terminal extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TerminalModel terminalModel;
    @ManyToMany(mappedBy = "terminal")
    private Set<Asuo> asuos = new HashSet<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_display_id")
    private TerminalDisplay terminalDisplay;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_ups_id")
    private TerminalUps terminalUps;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_printer_id")
    private TerminalPrinter terminalPrinter;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_server_id")
    private TerminalServer terminalServer;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_sensor_id")
    private TerminalSensor terminalSensor;

    public Terminal() {
    }

    public TerminalModel getTerminalModel() {
        return terminalModel;
    }

    public void setTerminalModel(TerminalModel terminalModel) {
        this.terminalModel = terminalModel;
    }

    public Set<Asuo> getAsuos() {
        return asuos;
    }

    public void setAsuos(Set<Asuo> asuos) {
        this.asuos = asuos;
    }

    public TerminalDisplay getTerminalDisplay() {
        return terminalDisplay;
    }

    public void setTerminalDisplay(TerminalDisplay terminalDisplay) {
        this.terminalDisplay = terminalDisplay;
    }

    public TerminalUps getTerminalUps() {
        return terminalUps;
    }

    public void setTerminalUps(TerminalUps terminalUps) {
        this.terminalUps = terminalUps;
    }

    public TerminalPrinter getTerminalPrinter() {
        return terminalPrinter;
    }

    public void setTerminalPrinter(TerminalPrinter terminalPrinter) {
        this.terminalPrinter = terminalPrinter;
    }

    public TerminalServer getTerminalServer() {
        return terminalServer;
    }

    public void setTerminalServer(TerminalServer terminalServer) {
        this.terminalServer = terminalServer;
    }

    public TerminalSensor getTerminalSensor() {
        return terminalSensor;
    }

    public void setTerminalSensor(TerminalSensor terminalSensor) {
        this.terminalSensor = terminalSensor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.terminalModel);
        hash = 89 * hash + Objects.hashCode(this.terminalDisplay);
        hash = 89 * hash + Objects.hashCode(this.terminalUps);
        hash = 89 * hash + Objects.hashCode(this.terminalPrinter);
        hash = 89 * hash + Objects.hashCode(this.terminalServer);
        hash = 89 * hash + Objects.hashCode(this.terminalSensor);
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
        final Terminal other = (Terminal) obj;
        if (!Objects.equals(this.terminalModel, other.terminalModel)) {
            return false;
        }
        if (!Objects.equals(this.terminalDisplay, other.terminalDisplay)) {
            return false;
        }
        if (!Objects.equals(this.terminalUps, other.terminalUps)) {
            return false;
        }
        if (!Objects.equals(this.terminalPrinter, other.terminalPrinter)) {
            return false;
        }
        if (!Objects.equals(this.terminalServer, other.terminalServer)) {
            return false;
        }
        return Objects.equals(this.terminalSensor, other.terminalSensor);
    }

    @Override
    public String toString() {
        return "Terminal{" + "terminalModel=" + terminalModel + ", asuos=" + asuos + ", terminalDisplay=" + terminalDisplay + ", terminalUps=" + terminalUps + ", terminalPrinter=" + terminalPrinter + ", terminalServer=" + terminalServer + ", terminalSensor=" + terminalSensor + '}';
    }


}
