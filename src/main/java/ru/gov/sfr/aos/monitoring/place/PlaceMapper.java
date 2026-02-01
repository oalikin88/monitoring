/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.place;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class PlaceMapper {
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private LocationRepo locationRepo;
    
    public PlaceDTO placeDtoFromPlace(Place place) {
        PlaceDTO placeDto = new PlaceDTO();
        placeDto.setId(place.getId());
        placeDto.setDepartment(place.getDepartment());
        placeDto.setLocationId(place.getLocation().getId());
        placeDto.setLocationName(place.getLocation().getName());
        placeDto.setUsername(place.getUsername());
        placeDto.setPlaceType(place.getPlaceType().getType());
        placeDto.setDepartmentCode(place.getDepartmentCode());
        return placeDto;
    }
    
    
    public Place placeFromPlaceDto(PlaceDTO placeDto) {
        Place place = null;
        boolean existsById = placeRepo.existsById(placeDto.getId());
        if(existsById) {
        place = placeRepo.findById(placeDto.getId()).get();
        } else {
            place = new Place();
            place.setDepartment(placeDto.getDepartment());
            place.setDepartmentCode(placeDto.getDepartmentCode());
             switch (placeDto.getPlaceType()) {
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
        Optional<Location> findLocationById = locationRepo.findById(placeDto.getLocationId());
        place.setLocation(findLocationById.get());
        place.setUsername(placeDto.getUsername());
    }
    return place;
    }
    
    public List<PlaceDTO> listPlaceDtoFromListPlace(List<Place> input) {
        List<PlaceDTO> dtoes = new ArrayList<>();
        for(Place el : input) {
            PlaceDTO placeDto = new PlaceDTO();
            placeDto.setId(el.getId());
            placeDto.setDepartment(el.getDepartment());
            placeDto.setLocationId(el.getLocation().getId());
            placeDto.setLocationName(el.getLocation().getName());
            placeDto.setUsername(el.getUsername());
            placeDto.setPlaceType(el.getPlaceType().getType());
            placeDto.setDepartmentCode(el.getDepartmentCode());
            dtoes.add(placeDto);
        }
        return dtoes;
    }
    
}
