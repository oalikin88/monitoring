/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;

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

    @Transactional
    public void addLocation(String nameLocation) throws ObjectAlreadyExists  {
        String firstFromInput = nameLocation.trim();
        String replaceAllBreakesFromInput = firstFromInput.replaceAll(" ", "");
        String replaceAllDeficesBreakesFromInput = replaceAllBreakesFromInput.replaceAll("-", "");
        Optional<Location> findLocationByNameFirst = locationRepo.findByNameIgnoreCase(firstFromInput);
        Optional<Location> findLocationByNameWhithOutBreakesAndDefices = locationRepo.findByNameIgnoreCase(replaceAllDeficesBreakesFromInput);
        if(findLocationByNameFirst.isEmpty() && findLocationByNameWhithOutBreakesAndDefices.isEmpty()) {
            Location location = new Location();
            location.setName(nameLocation.trim());
            locationRepo.save(location);
        } else {
            throw new ObjectAlreadyExists("Локация " + nameLocation.trim() + " уже есть в базе данных");
        }
        
    }
    
    public LocationDTO getLocationById(Long id) {
        Optional<Location> findLocationById = locationRepo.findById(id);
        LocationDTO dto = new LocationDTO(findLocationById.get().getId(), findLocationById.get().getName());
        return dto;
    }
    
    
}
