/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author 041AlikinOS
 */

@Entity
public class ServerModel extends SvtModel {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ServerManufacturer manufacturer;
    @OneToMany(targetEntity = Server.class, mappedBy = "serverModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Server> servers = new HashSet<>();

    public ServerModel() {
    }

    public ServerModel(String model) {
        this.model = model;
    }


    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public ServerManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ServerManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 71 * hash + Objects.hashCode(this.manufacturer);
        hash = 71 * hash + Objects.hashCode(this.servers);
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
        final ServerModel other = (ServerModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        
        if (!Objects.equals(this.archived, other.archived)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        
        return Objects.equals(this.manufacturer, other.manufacturer);
    }
    
    
    
    
        @Override
    public String toString() {
        return "ServerModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
