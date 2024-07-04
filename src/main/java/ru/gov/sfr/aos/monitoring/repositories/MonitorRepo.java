/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Monitor;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface MonitorRepo extends ObjectBuingWithSerialAndInventaryRepo <Monitor> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Monitor> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placeType);
    
}
 