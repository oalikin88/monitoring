/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.MainSvtDto;
import ru.gov.sfr.aos.monitoring.repositories.ObjectBuingRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

/**
 *
 * @author Alikin Oleg
 * @param <E>
 * @param <T>
 * @param <D>
 */

public abstract class SvtObjectBuingService <E extends ObjectBuing, T extends ObjectBuingRepo, D extends MainSvtDto> {
    
    @Autowired
    private T repository;
    @Autowired
    private PlaceRepo placeRepo;
    
     public void save(E e) {
           
            repository.save(e);
    }
     
     
    
   
    public E getById(Long id) {
        E e = (E) repository.findById(id).get();
        return e;
    }
    
      public void backFromStorage(E objectBuing, Long placeId) {
        Place place = placeRepo.findById(placeId).get();
        objectBuing.setPlace(place);
        repository.save(objectBuing);
        
    }
      
        public void sendToStorage(E objectBuing) {
       
        List<Place> places = placeRepo.findByLocationIdAndPlaceTypeAndArchivedFalse(objectBuing.getPlace().getLocation().getId(), PlaceType.STORAGE);
        Place place = places.get(0);
        objectBuing.setPlace(place);
        repository.save(objectBuing);
        
    }
        
        public Map<Location, List<E>> getSvtObjectsByPlace() {
        Map<Location, List<E>> collect = (Map<Location, List<E>>) repository.findByPlaceArchivedFalse()
                .stream()
                .collect(Collectors
                        .groupingBy((E el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
          public Map<Location, List<E>> getSvtObjectsByPlaceType(PlaceType placeType) {
        Map<Location, List<E>> collect = (Map<Location, List<E>>) repository.findByPlacePlaceTypeLikeAndArchivedFalse(placeType)
                .stream()
                .collect(Collectors
                        .groupingBy((E el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
        
        public void svtObjToArchive(ArchivedDto dto) {
            E e = (E)repository.findById(dto.getId()).get();
            e.setArchived(true);
            repository.save(e);
        }
        
        public Map<Location, List<E>> getSvtObjectsByName(String nameEmployee, PlaceType placeType) {
             Map<Location, List<E>> collect = (Map<Location, List<E>>) repository.findByPlaceUsernameContainingAndPlacePlaceTypeLikeAndArchivedFalse(nameEmployee, placeType)
                .stream()
                .collect(Collectors
                        .groupingBy((E el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
}
          
        public abstract void createSvtObj(D dto) throws ObjectAlreadyExists;
        
        public abstract void updateSvtObj(D dto);
    
    
}
