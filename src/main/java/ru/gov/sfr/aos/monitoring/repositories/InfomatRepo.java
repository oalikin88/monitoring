/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Infomat;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface InfomatRepo extends ObjectBuingWithSerialAndInventaryRepo<Infomat> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Infomat> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
    
    @Query(value = "SELECT infomat.*, ob.*, infomat_model.* "
   + "FROM infomat infomat "
   + "JOIN object_buing ob "
   + "ON infomat.infomat_id = ob.id "
   + "JOIN infomat_model infomat_model "
   + "ON infomat.infomat_model_id = infomat_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (infomat.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (infomat_model.model = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (infomat.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (infomat.year_created <= ?4)) ", nativeQuery = true)
    List<Infomat> findInfomatByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo);
    
    
}
