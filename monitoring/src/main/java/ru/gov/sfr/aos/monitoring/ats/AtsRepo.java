/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ats;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventaryRepo;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface AtsRepo extends ObjectBuingWithSerialAndInventaryRepo <Ats> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Ats> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
    
    @Query(value = "SELECT ats.*, ob.*, ats_model.*, place.* "
   + "FROM ats ats "
   + "JOIN object_buing ob "
   + "ON ats.ats_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN ats_model ats_model "
   + "ON ats.ats_model_id = ats_model.id "
   + "WHERE ob.archived = false AND ((?1 is NULL or ?1 = '') or (ats.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (ats_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (ats.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (ats.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5)) ", nativeQuery = true)
    List<Ats> findAtsByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
    
}
