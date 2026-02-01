/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.location;

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

import ru.gov.sfr.aos.monitoring.place.Place;

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
 
    @OneToMany(targetEntity = Place.class, mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Place> placesSet = new HashSet<>();

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

    public Set<Place> getPlacesSet() {
        return placesSet;
    }

    public void setPlacesSet(Set<Place> placesSet) {
        this.placesSet = placesSet;
    }

    public Location() {
    }

    public Location(String name) {
        this.name = name;
    }
    
    public Location(String name, Set<Place> placesSet) {
        this.name = name;
        this.placesSet = placesSet;
    }

    
    
    
    
    
}
