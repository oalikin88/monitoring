package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.Hub;

/**
 *
 * @author Alikin Oleg
 */
@Repository 
public interface HubRepo extends ObjectBuingWithSerialAndInventaryRepo<Hub>{
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    
    
    @Query(value = "SELECT hub.*, ob.*, hub_model.*, place.* "
   + "FROM hub hub "
   + "JOIN object_buing ob "
   + "ON hub.hub_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN hub_model hub_model "
   + "ON hub.hub_model_id = hub_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (hub.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (hub_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (hub.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (hub.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5))", nativeQuery = true)
    List<Hub> findHubByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
}
