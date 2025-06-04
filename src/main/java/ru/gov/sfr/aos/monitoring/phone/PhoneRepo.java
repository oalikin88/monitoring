/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.phone;

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
public interface PhoneRepo extends ObjectBuingWithSerialAndInventaryRepo <Phone> {
    @Override
    boolean existsBySerialNumberIgnoreCaseAndArchivedFalse(String serialNumber);
    @Override
    List<Phone> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
   @Query(value = "SELECT phone.*, ob.*, phone_model.*, place.* "
   + "FROM phone phone "
   + "JOIN object_buing ob "
   + "ON phone.phone_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN phone_model phone_model "
   + "ON phone.phone_model_id = phone_model.id "
   + "WHERE (ob.archived = false) AND (((?1 is NULL or ?1 = '') or (phone.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (phone_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (phone.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (phone.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5)))", nativeQuery = true)
    List<Phone> findPhonesByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
    
    
  
}


