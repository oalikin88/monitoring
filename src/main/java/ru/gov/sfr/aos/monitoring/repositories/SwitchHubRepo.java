/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.dictionaries.SwitchHubType;
import ru.gov.sfr.aos.monitoring.entities.SwitchHub;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface SwitchHubRepo extends ObjectBuingWithSerialAndInventaryRepo<SwitchHub>{
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<SwitchHub> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    List<SwitchHub> findBySwitchHubType(SwitchHubType switchHubType);
    
    
    @Query(value = "SELECT switch_hub.*, ob.*, switch_hub_model.* "
   + "FROM switch_hub switch_hub "
   + "JOIN object_buing ob "
   + "ON switch_hub.switch_hub_id = ob.id "
   + "JOIN switch_hub_model switch_hub_model "
   + "ON switch_hub.switch_hub_model_id = switch_hub_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (switch_hub.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (switch_hub_model.model = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (switch_hub.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (switch_hub.year_created <= ?4)) ", nativeQuery = true)
    List<SwitchHub> findSwitchHubByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo);
    
}
