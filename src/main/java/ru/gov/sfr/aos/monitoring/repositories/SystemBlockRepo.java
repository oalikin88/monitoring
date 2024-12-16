/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface SystemBlockRepo extends ObjectBuingWithSerialAndInventaryRepo<SystemBlock>{
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    boolean existsByInventaryNumberIgnoreCase(String inventaryNumber);
    List<SystemBlock> findBySerialNumberIgnoreCase(String serialNumber);
    List<SystemBlock> findByInventaryNumberIgnoreCase(String inventaryNumber);
    
    List<SystemBlock> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
     @Query(value = "SELECT sysblock.*, ob.*, sysblock_model.*, place.* "
   + "FROM system_block sysblock "
   + "JOIN object_buing ob "
   + "ON sysblock.system_block_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN system_block_model sysblock_model "
   + "ON sysblock.system_block_model_id = sysblock_model.id "
   + "WHERE ((?1 is NULL or ?1 = '') or (sysblock.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (sysblock_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (sysblock.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (sysblock.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5))", nativeQuery = true)
    List<SystemBlock> findSystemblockByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
    
}
