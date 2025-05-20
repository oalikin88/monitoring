package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.HashSet;
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
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.DepDto;
import ru.gov.sfr.aos.monitoring.models.DepartmentByTreePlaceDto;
import ru.gov.sfr.aos.monitoring.models.DepartmentDTO;
import ru.gov.sfr.aos.monitoring.models.LocationByTreePlaceDto;
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

    public void createPlace(PlaceDTO dto) throws ObjectAlreadyExists {
        Place place = new Place();
        if(dto.getUsername().isBlank() || dto.getUsername().isEmpty()) {
            throw new ObjectAlreadyExists("поле \"ФИО\" не может быть пустым");
        } else {
            place.setUsername(dto.getUsername());
        }
        
        PlaceType placeType = getPlaceType(dto.getPlaceType());
        place.setPlaceType(placeType);
        if(dto.getDepartment().isBlank() || dto.getDepartment().isEmpty()) {
            throw new ObjectAlreadyExists("поле \"Отдел\" не может быть пустым");
        } else {
            place.setDepartment(dto.getDepartment());
            place.setDepartmentCode(dto.getDepartmentCode());
        }
        Optional<Location> findLocationById = locationRepo.findById(dto.getLocationId());
        
        if(findLocationById.isEmpty()) {
            throw new ObjectAlreadyExists("поле \"Район\" не может быть пустым");
        } else {
            
            place.setLocation(findLocationById.get());
        }
        
        List<Place> potencialDoublePlaceList = placeRepo.findByPlaceTypeAndDepartmentCodeAndArchivedFalse(place.getPlaceType(), place.getDepartmentCode());
        List<Place> afterFilter = null;
        if(!potencialDoublePlaceList.isEmpty()) {
            afterFilter = potencialDoublePlaceList.stream().filter(el -> el.getUsername().trim().equalsIgnoreCase(place.getUsername().trim())).collect(Collectors.toList());
        }
        if(afterFilter == null || afterFilter.isEmpty()) {
            placeRepo.save(place);
        } else {
            throw new ObjectAlreadyExists("Рабочее место: " + place.getUsername() +  ", тип рабочего места: " + place.getPlaceType().getType() + ", в отделе: " + place.getDepartment() + " уже есть в базе данных.");
        }
        
    }

    public void updatePlace(PlaceDTO dto) throws ObjectAlreadyExists {
        Place placeFromDb = placeRepo.findById(dto.getId()).get();
        if(dto.getDepartment().isBlank() || dto.getDepartment().isEmpty()) {
            throw new ObjectAlreadyExists("поле \"Отдел\" не может быть пустым");
        } else {
            placeFromDb.setDepartment(dto.getDepartment());
            placeFromDb.setDepartmentCode(dto.getDepartmentCode());
        }
       
            Optional<Location> location = locationRepo.findById(dto.getLocationId());
            if(location.isEmpty()) {
                throw new ObjectAlreadyExists("Район указан неверно");
            } else {
                placeFromDb.setLocation(location.get());
            }
            
        
        
        PlaceType placeType = getPlaceType(dto.getPlaceType());
        placeFromDb.setPlaceType(placeType);
        if(dto.getUsername().isBlank() || dto.getUsername().isEmpty()) {
            throw new ObjectAlreadyExists("поле \"ФИО\" не может быть пустым");
        } else {
            placeFromDb.setUsername(dto.getUsername());
        }
        placeRepo.save(placeFromDb);
    }

    public void sendPlaceToArchive(Long id) {
        Place placeFromDb = placeRepo.findById(id).get();
        placeFromDb.setArchived(true);
        placeRepo.save(placeFromDb);
    }

    public List<PlaceDTO> getPlaces() {
        List<Place> findAllPlaces = placeRepo.findByArchivedFalse();
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(findAllPlaces);
        return dtoes;
    }
    
    
    public List<Place> getAllPlaces() {
        List<Place> findAllPlaces = placeRepo.findByArchivedFalse();
        return findAllPlaces;
    }

    public List<PlaceDTO> getPlacesByPlaceType(PlaceType placeType) {
        List<Place> places = placeRepo.findByPlaceTypeAndArchivedFalse(placeType);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }
    

    public List<PlaceDTO> getPlaceByUsername(String username) {
        List<Place> places = placeRepo.findByUsernameContainingAndArchivedFalse(username);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }

    public List<Place> getPlaceListByUsername(String username) {
        List<Place> places = placeRepo.findByUsernameContainingAndArchivedFalse(username);
        return places;
    }
    
    public PlaceDTO getPlaceById(Long placeId) {
        Place place = placeRepo.findById(placeId).get();
        PlaceDTO placeDto = placeMapper.placeDtoFromPlace(place);
        return placeDto;
    }

    public List<PlaceDTO> getPlacesByLocationId(Long locationId) {
        List<Place> places = placeRepo.findByLocationIdAndArchivedFalse(locationId);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }

    public PlaceDTO getPlaceStorageByLocation(Long locationId) {
        List<Place> places = placeRepo.findByLocationIdAndPlaceTypeAndArchivedFalse(locationId, PlaceType.STORAGE);
        PlaceDTO dto = null;
        if (places.size() > 0) {
            Place place = places.get(0);
            dto = placeMapper.placeDtoFromPlace(place);
        }
        return dto;
    }

    public List<PlaceDTO> getPlacesByDepartmentCode(String departmentCode) {
        List<Place> places = placeRepo.findByDepartmentCodeAndArchivedFalse(departmentCode);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }

    public Set<DepartmentDTO> getDepartmentsByLocation(Long locationId) {
        Location location = locationRepo.findById(locationId).get();
        Set<Place> places = location.getPlacesSet();
        Set<DepartmentDTO> dtoes = new HashSet<>();
        for (Place place : places) {
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
        for (Location location : locations) {
            LocationDTO dto = new LocationDTO(location.getId(), location.getName());
            dtoes.add(dto);
        }
        return dtoes;
    }
    // 
    public List<LocationDTO> getLocationByPlaceType(PlaceType placeType) {
        List<Place> places = placeRepo.findByPlaceTypeAndArchivedFalse(placeType);
        Set<Location> locations = places.stream().map(el -> el.getLocation()).collect(Collectors.toSet());
        List<LocationDTO> dtoes = new ArrayList<>();
        for (Location location : locations) {
            LocationDTO dto = new LocationDTO(location.getId(), location.getName());
            dtoes.add(dto);
        }
        return dtoes;
    }
    
    public List<DepDto> getDepartmentsByPlaceTypeAndLocation(PlaceType placeType, Long idLocation) {
        List<Place> places = placeRepo.findByPlaceTypeAndLocationIdAndArchivedFalse(placeType, idLocation);
        Map<String, String> collect = places.stream().collect(Collectors.toMap(Place::getDepartmentCode, Place::getDepartment, (el1, el2) -> el1));
        List<DepDto> deps = new ArrayList<>();
        for(Map.Entry<String, String> entry : collect.entrySet()) {
            DepDto dto = new DepDto(entry.getValue(), entry.getKey());
            deps.add(dto);
    }
        return deps;       
    }
    
    public List<PlaceDTO> getPlaceByPlaceTypeAndLocationAndDepartmentCode(PlaceType placeType, Long idLocation, String departmentCode) {
        List<Place> places = placeRepo.findByLocationIdAndDepartmentCodeAndPlaceTypeAndArchivedFalse(idLocation, departmentCode, placeType);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }
    

    public List<PlaceDTO> getPlacesByLocationAndDepartment(Long locationId, String departmentCode) {
        List<Place> places = placeRepo.findByLocationIdAndDepartmentCodeAndPlaceTypeAndArchivedFalse(locationId, departmentCode, PlaceType.EMPLOYEE);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }

    public List<PlaceDTO> getPlacesByLocationAndDepartmentAndPlaceType(Long locationId, String departmentCode, PlaceType placetype) {
        List<Place> places = placeRepo.findByLocationIdAndDepartmentCodeAndPlaceTypeAndArchivedFalse(locationId, departmentCode, placetype);
        List<PlaceDTO> dtoes = placeMapper.listPlaceDtoFromListPlace(places);
        return dtoes;
    }

    public List<PlaceStatusDto> getPlacesByStatus() {
        List<PlaceStatusDto> dtoes = new ArrayList<>();
        Map<String, String> collect = placeRepo.findByArchivedFalse().stream().collect(Collectors.groupingBy(Place::getPlaceType)).keySet().stream().collect(Collectors.toMap(PlaceType::name, PlaceType::getType));
        for (Map.Entry<String, String> entry : collect.entrySet()) {
            PlaceStatusDto dto = new PlaceStatusDto(entry.getKey(), entry.getValue());
            dtoes.add(dto);
        }
        return dtoes;
    }
    
    public List<PlaceDTO> getPlacesByLocationAndPlaceType(Long locationId, PlaceType placeType) {
        
        List<Place> placesList = placeRepo.findByLocationIdAndPlaceTypeAndArchivedFalse(locationId, placeType);
        return placeMapper.listPlaceDtoFromListPlace(placesList);
    }
    
    
    public List<LocationByTreePlaceDto> getPlaceDtoByAllLocations(List<Place> inputList) {
        Map<Location, List<Place>> placesByLocations = inputList.stream().collect(Collectors.groupingBy(Place::getLocation));
        List<LocationByTreePlaceDto> out = new ArrayList<>();
        for(Map.Entry<Location, List<Place>> entry : placesByLocations.entrySet()) {
            LocationByTreePlaceDto locationByTreePlaceDto = new LocationByTreePlaceDto();
            locationByTreePlaceDto.setLocationId(entry.getKey().getId());
            locationByTreePlaceDto.setLocationName(entry.getKey().getName());
            List<DepartmentByTreePlaceDto> listDepartmentByTreePlaceDto = new ArrayList<>();
            Map<String, List<Place>> placesByDepartmentMap = entry.getValue().stream().collect(Collectors.groupingBy(Place::getDepartment));
            for(Map.Entry<String, List<Place>> innerEntry : placesByDepartmentMap.entrySet()) {
                DepartmentByTreePlaceDto departmentByTreePlaceDto = new DepartmentByTreePlaceDto();
                departmentByTreePlaceDto.setDepartment(innerEntry.getKey());
                departmentByTreePlaceDto.setDtoes(placeMapper.listPlaceDtoFromListPlace(innerEntry.getValue()));
                listDepartmentByTreePlaceDto.add(departmentByTreePlaceDto);
            }
            locationByTreePlaceDto.setDepartments(listDepartmentByTreePlaceDto);
            out.add(locationByTreePlaceDto);
        }
        
        return out;
    }
    
    
    
    
    public static PlaceType getPlaceType(String placeType) {
        PlaceType curentType = null;
        switch (placeType) {
            case "SERVERROOM":
                curentType = PlaceType.SERVERROOM;
                break;
            case "OFFICEEQUIPMENT":
                curentType = PlaceType.OFFICEEQUIPMENT;
                break;
            case "EMPLOYEE":
                curentType = PlaceType.EMPLOYEE;
                break;
            default:
                curentType = PlaceType.EMPLOYEE;
                break;
    }
        return curentType;
    
}
    
    public static String getPlaceTypeRus(String placeType) {
        String result = "";
        switch (placeType) {
            case "SERVERROOM":
                result = "Серверная";
                break;
            case "OFFICEEQUIPMENT":
                result = "Оргтехника";
                break;
            case "EMPLOYEE":
                result = "Сотрудник";
                break;
            case "STORAGE":
                result = "Склад";
                break;
    }
        return result;
    }
}
