/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.conditioner;

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
public interface ConditionerRepo extends ObjectBuingWithSerialAndInventaryRepo <Conditioner> {
    @Override
    boolean existsBySerialNumberIgnoreCaseAndArchivedFalse(String serialNumber);
    @Override
    List<Conditioner> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
    
   @Query(value = "SELECT conditioner.*, ob.*, conditioner_model.*, place.* "
   + "FROM conditioner conditioner "
   + "JOIN object_buing ob "
   + "ON conditioner.conditioner_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN conditioner_model conditioner_model "
   + "ON conditioner.conditioner_model_id = conditioner_model.id "
   + "WHERE (ob.archived = false) AND (((?1 is NULL or ?1 = '') or (conditioner.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (conditioner_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (conditioner.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (conditioner.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5))) ", nativeQuery = true)
    List<Conditioner> findConditionerByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
    
}
