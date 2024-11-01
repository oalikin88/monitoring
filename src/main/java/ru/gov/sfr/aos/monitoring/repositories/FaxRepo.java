/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Fax;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface FaxRepo extends ObjectBuingWithSerialAndInventaryRepo <Fax> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Fax> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
   @Query(value = "SELECT fax.*, ob.*, fax_model.* "
   + "FROM fax fax "
   + "JOIN object_buing ob "
   + "ON fax.fax_id = ob.id "
   + "JOIN fax_model fax_model "
   + "ON fax.model_id = fax_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (fax.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (fax_model.model = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (fax.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (fax.year_created <= ?4)) ", nativeQuery = true)
    List<Fax> findFaxByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo);
    
    
}
