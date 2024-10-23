/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Phone;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface PhoneRepo extends ObjectBuingWithSerialAndInventaryRepo <Phone> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Phone> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
   @Query(value = "SELECT phone.*, ob.*, phone_model.* "
   + "FROM phone phone "
   + "JOIN object_buing ob "
   + "ON phone.phone_id = ob.id "
   + "JOIN phone_model phone_model "
   + "ON phone.phone_model_id = phone_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (phone.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (phone_model.model = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (phone.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (phone.year_created <= ?4)) ", nativeQuery = true)
    List<Phone> findPhonesByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo);
    
    
  
}


