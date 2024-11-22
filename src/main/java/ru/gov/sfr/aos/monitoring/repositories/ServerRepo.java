/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Server;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface ServerRepo extends ObjectBuingWithSerialAndInventaryRepo <Server> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Server> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
   @Query(value = "SELECT server.*, ob.*, server_model.*, place.* "
   + "FROM server server "
   + "JOIN object_buing ob "
   + "ON server.server_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN server_model server_model "
   + "ON server.server_model_id = server_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (server.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (server_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (server.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (server.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5))", nativeQuery = true)
    List<Server> findServerByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
    
    
}
