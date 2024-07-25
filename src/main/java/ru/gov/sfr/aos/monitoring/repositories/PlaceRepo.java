/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Place;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface PlaceRepo extends JpaRepository<Place, Long> {
    List<Place> findByArchivedFalse();
    List<Place> findByLocationIdAndArchivedFalse(Long locationId);
    List<Place> findByDepartmentCodeAndArchivedFalse(String departmentCode);
    List<Place> findByLocationIdAndDepartmentCodeAndPlaceTypeAndArchivedFalse(Long locationId, String departmentCode, PlaceType placeType);
    List<Place> findByPlaceTypeAndArchivedFalse(PlaceType placetype);
    List<Place> findByLocationIdAndPlaceTypeAndArchivedFalse(Long locationId, PlaceType placeType);
    List<Place> findByUsernameContainingAndArchivedFalse(String username);
    List<Place> findByPlaceTypeAndLocationIdAndArchivedFalse(PlaceType placeType, Long idLocation);
}
