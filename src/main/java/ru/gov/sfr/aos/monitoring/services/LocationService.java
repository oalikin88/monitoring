/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;

/**
 *
 * @author 041AlikinOS
 */

@Component
public class LocationService {
    
    @Autowired
    private LocationRepo locationRepo;
    
    public List<LocationDTO> getAllLocations() {
        List<LocationDTO> dtoes = new ArrayList<>();
        List<Location> locations = locationRepo.findAll();
        for(Location location : locations) {
            LocationDTO dto = new LocationDTO(location.getId(), location.getName());
            dtoes.add(dto);
        }
        return dtoes;
    }

    
    public void addLocation(String nameLocation)  {
    
        Optional<Location> findLocationByName = locationRepo.findByNameIgnoreCase(nameLocation.toLowerCase());
        if(findLocationByName.isEmpty()) {
            Location location = new Location();
            location.setName(nameLocation);
            locationRepo.save(location);
        } 
        
    }
    
}
