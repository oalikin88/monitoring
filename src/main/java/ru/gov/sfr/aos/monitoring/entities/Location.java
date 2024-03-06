/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "LOCATION_ID")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле \"Локация\" не может быть пустым")
    private String name;
 
    @OneToMany(targetEntity = ObjectBuing.class, mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ObjectBuing> objectsSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ObjectBuing> getObjectsSet() {
        return objectsSet;
    }

    public void setObjectsSet(Set<ObjectBuing> objectsSet) {
        this.objectsSet = objectsSet;
    }

    public Location() {
    }

    public Location(String name) {
        this.name = name;
    }
    
    public Location(String name, Set<ObjectBuing> objectsSet) {
        this.name = name;
        this.objectsSet = objectsSet;
    }

    
    
    
    
    
}
