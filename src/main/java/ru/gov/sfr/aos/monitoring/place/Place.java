/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.place;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.location.Location;

/**
 *
 * @author 041AlikinOS
 */

@Entity
public class Place {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "Поле \"ФИО\" не должно быть пустым")
    @NotEmpty(message = "Поле \"ФИО\" не должно быть пустым")
    private String username;
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;
    @NotNull(message = "Поле \"Район\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Location location;
    @NotBlank(message = "Поле \"Отдел\" не должно быть пустым")
    @NotEmpty(message = "Поле \"Отдел\" не должно быть пустым")
    private String department;
    private String departmentCode;
    private boolean archived;

    public Place() {
    }

    public Place(String username, PlaceType placeType, Location location, String department, boolean archived) {
        this.username = username;
        this.placeType = placeType;
        this.location = location;
        this.department = department;
        this.archived = archived;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "Place{" + "id=" + id + ", username=" + username + ", placeType=" + placeType + ", location=" + location + ", department=" + department + ", departmentCode=" + departmentCode + ", archived=" + archived + '}';
    }
    
    
    
}
