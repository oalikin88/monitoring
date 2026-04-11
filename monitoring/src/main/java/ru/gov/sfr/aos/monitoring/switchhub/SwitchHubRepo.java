/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.switchhub;

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
public interface SwitchHubRepo extends ObjectBuingWithSerialAndInventaryRepo<SwitchHub>{
    @Override
    boolean existsBySerialNumberIgnoreCaseAndArchivedFalse(String serialNumber);
    @Override
    List<SwitchHub> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    List<SwitchHub> findBySwitchHubTypeAndArchivedFalse(SwitchHubType switchHubType);
    
    
    @Query(value = "SELECT switch_hub.*, ob.*, switch_hub_model.*, place.* "
   + "FROM switch_hub switch_hub "
   + "JOIN object_buing ob "
   + "ON switch_hub.switch_hub_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN switch_hub_model switch_hub_model "
   + "ON switch_hub.switch_hub_model_id = switch_hub_model.id "
   + "WHERE (ob.archived = false) AND (((?1 is NULL or ?1 = '') or (switch_hub.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (switch_hub_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (switch_hub.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (switch_hub.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5))) ", nativeQuery = true)
    List<SwitchHub> findSwitchHubByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
    
}
