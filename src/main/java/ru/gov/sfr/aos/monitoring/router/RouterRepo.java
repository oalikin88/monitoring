/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.router;

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
public interface RouterRepo extends ObjectBuingWithSerialAndInventaryRepo<Router> {
    @Override
    boolean existsBySerialNumberIgnoreCaseAndArchivedFalse(String serialNumber);
    @Override
    List<Router> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
    
   @Query(value = "SELECT router.*, ob.*, router_model.*, place.* "
   + "FROM router router "
   + "JOIN object_buing ob "
   + "ON router.router_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN router_model router_model "
   + "ON router.router_model_id = router_model.id "
   + "WHERE (ob.archived = false) AND (((?1 is NULL or ?1 = '') or (router.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (router_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (router.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (router.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5))) ", nativeQuery = true)
    List<Router> findRouterByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
    
    
}
