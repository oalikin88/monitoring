/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo.terminal;

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
 * @author 041AlikinOS
 */
@Entity
public class TerminalModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private TerminalManufacturer manufacturer;
    @OneToMany(targetEntity = Terminal.class, mappedBy = "terminalModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Terminal> terminals = new HashSet<>();

    public TerminalModel() {
    }


    public Set<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(Set<Terminal> terminals) {
        this.terminals = terminals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public TerminalManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TerminalManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.manufacturer);
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
        final TerminalModel other = (TerminalModel) obj;
        return Objects.equals(this.manufacturer, other.manufacturer);
    }

    @Override
    public String toString() {
        return "TerminalModel{" + "manufacturer=" + manufacturer + ", terminals=" + terminals + '}';
    }
    
    
    
    
    
     
}
