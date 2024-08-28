/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author 041AlikinOS
 */

@Entity
public class ServerModel extends SvtModel {

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
    
    
    
}
