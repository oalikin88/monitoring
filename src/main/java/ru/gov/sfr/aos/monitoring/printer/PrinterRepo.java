package ru.gov.sfr.aos.monitoring.printer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventaryRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface PrinterRepo extends ObjectBuingWithSerialAndInventaryRepo <Printer>  {

    @Override
    boolean existsBySerialNumberIgnoreCaseAndArchivedFalse(String serialNumber);
    @Override
    List<Printer> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    
    @Query(value = "SELECT printer.*, ob.*, printer_model.*, place.* "
   + "FROM printer printer "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN printer_model printer_model "
   + "ON printer.printer_model_id = printer_model.id "
   + "WHERE (ob.archived = false) AND (((?1 is NULL or ?1 = '') or (printer.status = ?1)) "
   + "AND ((?2 is NULL or ?2 = '') or (printer_model.id = ?2)) "
   + "AND ((?3 is NULL or ?3 = '') or (printer.year_created >= ?3)) "
   + "AND ((?4 is NULL or ?4 = '') or (printer.year_created <= ?4)) "
   + "AND ((?5 is NULL or ?5 = '') or (place.location_id = ?5)))", nativeQuery = true)
    List<Printer> findDevicesByAllFilters (String status, String model, String yearCreatedOne, String yearCreatedTwo, String location);
}
