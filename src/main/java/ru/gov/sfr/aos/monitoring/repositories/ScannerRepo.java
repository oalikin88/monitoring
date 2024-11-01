/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Scanner;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface ScannerRepo extends ObjectBuingWithSerialAndInventaryRepo<Scanner> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Scanner> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
    
   @Query(value = "SELECT scanner.*, ob.*, scanner_model.* "
   + "FROM scanner scanner "
   + "JOIN object_buing ob "
   + "ON scanner.scanner_id = ob.id "
   + "JOIN scanner_model scanner_model "
   + "ON scanner.scanner_model_id = scanner_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (scanner.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (scanner_model.model = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (scanner.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (scanner.year_created <= ?4)) ", nativeQuery = true)
    List<Scanner> findScannerByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo);
    
}
