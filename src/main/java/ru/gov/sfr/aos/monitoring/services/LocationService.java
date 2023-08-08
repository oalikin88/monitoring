/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.exceptions.EntityAlreadyExistsException;
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

    
    public void addLocation(String nameLocation) throws EntityAlreadyExistsException {
    
        Optional<Location> findLocationByName = locationRepo.findByNameIgnoreCase(nameLocation.toLowerCase());
        if(findLocationByName.isEmpty()) {
            Location location = new Location();
            location.setName(nameLocation);
            locationRepo.save(location);
        } else {
            throw new EntityAlreadyExistsException("Локация " + nameLocation + " уже есть в базе данных.");
        }
        
    }
    
}
