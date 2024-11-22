/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.Ups;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface UpsRepo extends ObjectBuingWithSerialAndInventaryRepo<Ups> {
    
   @Query(value = "SELECT ups.*, ob.*, ups_model.*, place.* "
   + "FROM ups ups "
   + "JOIN object_buing ob "
   + "ON ups.ups_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN ups_model ups_model "
   + "ON ups.ups_model_id = ups_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (ups.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (ups_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (ups.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (ups.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5))", nativeQuery = true)
    List<Ups> findUpsByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
}
