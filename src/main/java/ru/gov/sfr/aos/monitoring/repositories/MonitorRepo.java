/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
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
    
   @Query(value = "SELECT monitor.*, ob.*, monitor_model.*, place.* "
   + "FROM monitor monitor "
   + "JOIN object_buing ob "
   + "ON monitor.monitor_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN monitor_model monitor_model "
   + "ON monitor.monitor_model_id = monitor_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (monitor.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (monitor_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (monitor.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (monitor.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5))", nativeQuery = true)
    List<Monitor> findMonitorByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
    
}
 