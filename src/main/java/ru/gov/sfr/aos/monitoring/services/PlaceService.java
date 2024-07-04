/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.models.DepartmentDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.PlaceDTO;
import ru.gov.sfr.aos.monitoring.models.PlaceStatusDto;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class PlaceService {
    
    
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private LocationService locationService;
    @Autowired
    private PlaceMapper placeMapper;
    
    public void createPlace(PlaceDTO dto) {
        Place place = new Place();
        place.setUsername(dto.getUsername());
        switch (dto.getPlaceType()) {
            case "Сотрудник":
                place.setPlaceType(PlaceType.EMPLOYEE);
                break;
            case "Серверная":
                place.setPlaceType(PlaceType.SERVERROOM);
                break;
            case "Оргтехника":
                place.setPlaceType(PlaceType.OFFICEEQUIPMENT);
                break;
            case "Склад":
                place.setPlaceType(PlaceType.STORAGE);
                break;
        }
        place.setDepartment(dto.getDepartment());
        place.setDepartmentCode(dto.getDepartmentCode());
        Optional<Location> findLocationById = locationRepo.findById(dto.getLocationId());
        place.setLocation(findLocationById.get());
        
        placeRepo.save(place);
    }
    
    public List<PlaceDTO> getPlaces() {
        
        List<Place> findAllPlaces = placeRepo.findAll();
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(findAllPlaces);
        return dtoes;
    }
    
    public List<PlaceDTO> getPlacesByPlaceType(PlaceType placeType) {
        
        List<Place> places = placeRepo.findByPlaceType(placeType);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }
    
    public PlaceDTO getPlaceById(Long placeId) {
        Place place = placeRepo.findById(placeId).get();
        PlaceDTO placeDto = placeMapper.placeDtoFromPlace(place);
        return placeDto;
    }
    
    public List<PlaceDTO> getPlacesByLocationId(Long locationId) {
        List<Place> places = placeRepo.findByLocationId(locationId);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }
    
    public PlaceDTO getPlaceStorageByLocation(Long locationId) {
        List<Place> places = placeRepo.findByLocationIdAndPlaceType(locationId, PlaceType.STORAGE);
        PlaceDTO dto = null;
        if (places.size() > 0) {
            Place place = places.get(0);
            dto = placeMapper.placeDtoFromPlace(place);
        }
        return dto;
    }
    
    public List<PlaceDTO> getPlacesByDepartmentCode(String departmentCode) {
        List<Place> places = placeRepo.findByDepartmentCode(departmentCode);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }
    
    public List<DepartmentDTO> getDepartmentsByLocation(Long locationId) {
        Location location = locationRepo.findById(locationId).get();
        Set<Place> places = location.getPlacesSet();
        List<DepartmentDTO> dtoes = new ArrayList<>();
        for(Place place : places) {
            DepartmentDTO dto = new DepartmentDTO();
            dto.setName(place.getDepartment());
            dto.setCode(place.getDepartmentCode());
            dtoes.add(dto);
        }
        return dtoes;
    }
    
    public List<LocationDTO> getLocations() {
        Set<Location> locations = locationRepo.findAll().stream().filter(el -> !el.getPlacesSet().isEmpty()).collect(Collectors.toSet());
        List<LocationDTO> dtoes = new ArrayList<>();
        for(Location location : locations) {
            LocationDTO dto = new LocationDTO(location.getId(), location.getName());
            dtoes.add(dto);
        }
        return dtoes;
    }
    
    public List<PlaceDTO> getPlacesByLocationAndDepartment(Long locationId, String departmentCode) {
        List<Place> places = placeRepo.findByLocationIdAndDepartmentCodeAndPlaceType(locationId, departmentCode, PlaceType.EMPLOYEE);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }
    
    public List<PlaceStatusDto> getPlacesByStatus() {
        List<PlaceStatusDto> dtoes = new ArrayList<>();
        Map<String, String> collect = placeRepo.findAll().stream().collect(Collectors.groupingBy(Place::getPlaceType)).keySet().stream().collect(Collectors.toMap(PlaceType::name, PlaceType::getType));
        for(Map.Entry<String, String> entry : collect.entrySet()) {
            PlaceStatusDto dto = new PlaceStatusDto(entry.getKey(), entry.getValue());
            dtoes.add(dto);
        }
        return dtoes;
    }
    
}
