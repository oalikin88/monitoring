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
public class RouterModel extends SvtModel {

    @OneToMany(targetEntity = Router.class, mappedBy = "routerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Router> routers = new HashSet<>();

    public RouterModel() {
    }


    public Set<Router> getRouters() {
        return routers;
    }

    public void setRouters(Set<Router> routers) {
        this.routers = routers;
    }
    
        @Override
    public String toString() {
        return "RouterModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
}
