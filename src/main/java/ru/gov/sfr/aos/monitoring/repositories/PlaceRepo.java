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
    List<Place> findByLocationId(Long locationId);
    List<Place> findByDepartmentCode(String departmentCode);
    List<Place> findByLocationIdAndDepartmentCodeAndPlaceType(Long locationId, String departmentCode, PlaceType placeType);
    List<Place> findByPlaceType(PlaceType placetype);
    List<Place> findByLocationIdAndPlaceType(Long locationId, PlaceType placeType);
}
