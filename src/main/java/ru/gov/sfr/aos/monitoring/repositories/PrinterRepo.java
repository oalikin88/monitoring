/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface PrinterRepo extends JpaRepository<Printer, Long> {

    List<Printer> findByModel(String model);
    List<Printer> findByPlaceLocationAndModel(Location location, Model model);
    List<Printer> findByPlaceLocationName(String name);
    List<Printer> findByPlaceLocationIdAndModelId(Long idLocation, Long idModel);
    List<Printer> findByPlaceLocationNameAndModelIdAndPrinterStatusEquals(String location, Long idModel, PrinterStatus status);
    @Query(value = "SELECT p.*, ob.*, c.*, m.* " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "LEFT JOIN model m " +
                   "ON p.model_id = m.id " +
                   "WHERE p.printer_id = ?1 " +
                   "AND m.archived = FALSE ", nativeQuery = true)
    List<Printer> findByModelId(Long id);
    
    @Query(value = "SELECT p.*, ob.*, c.*, l.*, m.*, place.* " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                    "LEFT JOIN place place " +
                    "ON ob.place_id = place.id " +
                   "LEFT JOIN location l " +
                   "ON place.location_id = l.id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "LEFT JOIN model m " +
                   "ON p.model_id = m.id " +
                   "WHERE p.inventory_number LIKE CONCAT('%',:inventaryNumber,'%') " +
                   "AND l.id = :idLocation " +
                   "AND p.model_id = :idModel " +
                   "AND NOT p.printer_status = 'DELETE' " +
                   "AND m.archived = FALSE ", nativeQuery = true)
    List<Printer> findByInventaryNumber(@Param("inventaryNumber") String inventaryNumber, @Param("idModel") Long idModel, @Param("idLocation") Long idLocation);
    @Query(value = "SELECT p.*, ob.*, c.*, l.*, m.*, place.* " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                   "LEFT JOIN place place " +
                    "ON ob.place_id = place.id " +
                   "LEFT JOIN location l " +
                   "ON place.location_id = l.id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "LEFT JOIN model m " +
                   "ON p.model_id = m.id " +
                   "WHERE p.inventory_number LIKE CONCAT('%',:inventaryNumber,'%') " +
                   "AND l.id = :idLocation " +
                   "AND p.model_id = :idModel " +
                   "AND NOT p.printer_status = 'DELETE' " +
                   "AND m.archived = FALSE ",
            countQuery = "SELECT count(*) " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                   "LEFT JOIN place place " +
                    "ON ob.place_id = place.id " +
                   "LEFT JOIN location l " +
                   "ON place.location_id = l.id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "LEFT JOIN model m " +
                   "ON p.model_id = m.id " +
                   "WHERE p.inventory_number LIKE CONCAT('%',:inventaryNumber,'%') " +
                   "AND l.id = :idLocation " +
                   "AND p.model_id = :idModel " +
                   "AND NOT p.printer_status = 'DELETE' " +
                   "AND m.archived = FALSE ", nativeQuery = true)
    Page<Printer> findByInventaryNumber(@Param("inventaryNumber") String inventaryNumber, @Param("idModel") Long idModel, @Param("idLocation") Long idLocation, Pageable pageable);
    
     @Query(value = "SELECT p.*, ob.*, c.*, l.*, m.*, place.* " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                    "LEFT JOIN place place " +
                    "ON ob.place_id = place.id " +
                   "LEFT JOIN location l " +
                   "ON place.location_id = l.id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "LEFT JOIN model m " +
                   "ON p.model_id = m.id " +
                   "WHERE p.inventory_number LIKE CONCAT('%',:inventaryNumber,'%') " +
                   "AND l.id = :idLocation " +
                   "AND NOT p.printer_status = 'DELETE' " +
                   "AND m.archived = FALSE ", nativeQuery = true)
    List<Printer> findByInventaryNumberWhithOutIdModel(@Param("inventaryNumber") String inventaryNumber, @Param("idLocation") Long idLocation);
    @Query(value = "SELECT p.*, ob.*, c.*, l.*, m.*, place.* " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                   "LEFT JOIN place place " +
                    "ON ob.place_id = place.id " +
                   "LEFT JOIN location l " +
                   "ON place.location_id = l.id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "LEFT JOIN model m " +
                   "ON p.model_id = m.id " +
                   "WHERE p.inventory_number LIKE CONCAT('%',:inventaryNumber,'%') " +
                   "AND l.id = :idLocation " +
                   "AND NOT p.printer_status = 'DELETE' " +
                   "AND m.archived = FALSE ",
            countQuery = "SELECT count(*) " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                   "LEFT JOIN place place " +
                    "ON ob.place_id = place.id " +
                   "LEFT JOIN location l " +
                   "ON place.location_id = l.id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "LEFT JOIN model m " +
                   "ON p.model_id = m.id " +
                   "WHERE p.inventory_number LIKE CONCAT('%',:inventaryNumber,'%') " +
                   "AND l.id = :idLocation " +
                   "AND NOT p.printer_status = 'DELETE' " +
                   "AND m.archived = FALSE ", nativeQuery = true)
    Page<Printer> findByInventaryNumberWhithOutIdModel(@Param("inventaryNumber") String inventaryNumber, @Param("idLocation") Long idLocation, Pageable pageable);
    
    @Query(value = "SELECT p.*, ob.*, c.* " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "WHERE p.printer_id = ?1" , nativeQuery = true)
    Printer findByPrinterId(Long id);
    
   @Query(value = "SELECT printer.*, manufacturer.*, model.*, cartridge_printer.*, contr.*, ob.*, place.* "
                   + "FROM printer printer "
                   + "JOIN manufacturer manufacturer "
                   + "ON printer.manufacturer_id = manufacturer.id "
                   + "JOIN model model "
                   + "ON printer.model_id = model.id "
                   + "JOIN cartridge_model_models_printers cartridge_printer "
                   + "ON printer.model_id = cartridge_printer.models_printers_id "
                   + "JOIN object_buing ob "
                   + "ON printer.printer_id = ob.id "
                   + "JOIN place place " 
                   + "ON ob.place_id = place.id " 
                   + "JOIN contract contr "
                   + "ON ob.contract_id = contr.id "
                   + "WHERE cartridge_printer.model_cartridges_id = ?2 "
                   + "AND place.location_id = ?1 "
                   + "AND NOT printer.printer_status = 'DELETE' "
                   + "AND model.archived = FALSE ",
       countQuery = "SELECT count(*) "
                   + "FROM printer printer "
                   + "JOIN manufacturer manufacturer "
                   + "ON printer.manufacturer_id = manufacturer.id "
                   + "JOIN model model "
                   + "ON printer.model_id = model.id "
                   + "JOIN cartridge_model_models_printers cartridge_printer "
                   + "ON printer.model_id = cartridge_printer.models_printers_id "
                   + "JOIN object_buing ob "
                   + "ON printer.printer_id = ob.id "
                   + "JOIN place place " 
                   + "ON ob.place_id = place.id " 
                   + "JOIN contract contr "
                   + "ON ob.contract_id = contr.id "
                   + "WHERE cartridge_printer.model_cartridges_id = ?2 "
                   + "AND place.location_id = ?1 " 
                   + "AND NOT printer.printer_status = 'DELETE' "
                   + "AND model.archived = FALSE ", nativeQuery = true)
    Page<Printer> findPrintersByModelAndLocation(Long idLocation, Long idModelCarttridge, Pageable pageable);
    
    @Query(value = "SELECT printer.*, manufacturer.*, model.*, cartridge_printer.*, ob.*, contr.*, place.* "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN cartridge_model_models_printers cartridge_printer "
   + "ON printer.model_id = cartridge_printer.models_printers_id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id " 
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE cartridge_printer.model_cartridges_id = ?2 "
   + "AND place.location_id = ?1 "
   + "AND contr.contract_number LIKE ?3% "
   + "AND model.archived = FALSE ",
   countQuery = "SELECT count(*) "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN cartridge_model_models_printers cartridge_printer "
   + "ON printer.model_id = cartridge_printer.models_printers_id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE cartridge_printer.model_cartridges_id = ?2 "
   + "AND place.location_id = ?1 "
   + "AND contr.contract_number LIKE ?3% "
   + "AND model.archived = FALSE " ,nativeQuery = true)
    Page<Printer> findPrintersByModelAndLocationAndContractNumberFilter(Long idLocation, Long idModelCartridge, String contractNumber, Pageable pageable);
    
    
    @Query(value = "SELECT printer.*, manufacturer.*, model.*, cartridge_printer.*, ob.*, contr.*, place.* "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN cartridge_model_models_printers cartridge_printer "
   + "ON printer.model_id = cartridge_printer.models_printers_id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE contr.contract_number LIKE ?3% "
   + "AND place.location_id = ?1 "
   + "AND model.id = ?2 "
   + "AND model.archived = FALSE ",
   countQuery = "SELECT count(*) "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN cartridge_model_models_printers cartridge_printer "
   + "ON printer.model_id = cartridge_printer.models_printers_id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
  + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE contr.contract_number LIKE ?3% "
   + "AND place.location_id = ?1 "
   + "AND model.id = ?2 "
   + "AND model.archived = FALSE ", nativeQuery = true)
    Page<Printer> findPrintersByModelPrinterAndLocationAndContractNumberFilter(Long idLocation, Long idModelPrinter, String contractNumber, Pageable pageable);
    
    
    @Query(value = "SELECT printer.*, manufacturer.*, model.*, ob.*, contr.*, place.* "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE model.id = ?2 "
   + "AND place.location_id = ?1 "
   + "AND model.archived = FALSE ",
   countQuery = "SELECT count(*) "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE model.id = ?2 "
   + "AND place.location_id = ?1 "
   + "AND model.archived = FALSE " ,nativeQuery = true)
    Page<Printer> findPrintersByModelPrinterAndLocation(Long idLocation, Long idModelPrinter, Pageable pageable);

    @Query(value = "SELECT p.*, ob.*, contr.*, manuf.*, m.*, place.* " +
                    "FROM printer p " +
                    "LEFT JOIN manufacturer manuf " +
                    "ON p.manufacturer_id = manuf.id " +
                    "LEFT JOIN object_buing ob " +
                    "ON p.printer_id = ob.id " +
                    "JOIN place place " + 
                    "ON ob.place_id = place.id " +
                    "LEFT JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                    "LEFT JOIN model m " +
                    "ON p.model_id = m.id " +
                    "WHERE place.location_id = ?1 " +
                    "AND NOT p.printer_status = 'DELETE' " +
                    "AND m.archived = FALSE ",
            countQuery = "SELECT count(*) " +
                    "FROM printer p " +
                    "LEFT JOIN manufacturer manuf " +
                    "ON p.manufacturer_id = manuf.id " +
                    "LEFT JOIN object_buing ob " +
                    "ON p.printer_id = ob.id " +
                     "JOIN place place " +
                    "ON ob.place_id = place.id " +
                    "LEFT JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                    "LEFT JOIN model m " +
                    "ON p.model_id = m.id " +
                    "WHERE place.location_id = ?1 " +
                    "AND NOT p.printer_status = 'DELETE' " +
                    "AND m.archived = FALSE ", nativeQuery = true)
   Page<Printer> findByLocation(Long idLocation, Pageable pageable);
   
   @Query(value = "SELECT p.*, ob.*, contr.*, manuf.*, m.*, place.* " +
                     "FROM printer p " +
                     "LEFT JOIN manufacturer manuf " +
                     "ON p.manufacturer_id = manuf.id " +
                    "LEFT JOIN object_buing ob " +
                    "ON p.printer_id = ob.id " +
                    "JOIN place place " +
                    "ON ob.place_id = place.id " +
                    "JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                    "LEFT JOIN model m " +
                    "ON p.model_id = m.id " +
                    "WHERE place.location_id = ?1 " +
                    "AND NOT p.printer_status = 'DELETE' " +
                    "AND m.archived = FALSE ", nativeQuery = true)
   List<Printer> findByLocation(Long idLocation);
   
      @Query(value = "SELECT p.*, ob.*, contr.*, m.*, place.* " +
                    "FROM printer p " +
                    "LEFT JOIN object_buing ob " +
                    "ON p.printer_id = ob.id " +
                    "JOIN place place " + 
                    "ON ob.place_id = place.id " +
                    "LEFT JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                    "LEFT JOIN model m " +
                    "ON p.model_id = m.id " +
                    "WHERE place.location_id = ?1 " +
                    "AND NOT p.printer_status = 'DELETE' " +
                    "AND m.device_type = ?2 " +
                    "AND m.archived = FALSE ",
            countQuery = "SELECT count(*) " +
                    "FROM printer p " +
                    "LEFT JOIN object_buing ob " +
                    "ON p.printer_id = ob.id " +
                    "JOIN place place " +
                    "ON ob.place_id = place.id " +
                    "LEFT JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                    "LEFT JOIN model m " +
                    "ON p.model_id = m.id " +
                    "WHERE place.location_id = ?1 " +
                    "AND NOT p.printer_status = 'DELETE' " +
                    "AND m.device_type = ?2 " +
                    "AND m.archived = FALSE ", nativeQuery = true)
   Page<Printer> findByLocationAndDeviceType(Long idLocation, String deviceType, Pageable pageable);
   
   @Query(value = "SELECT p.*, ob.*, contr.*, m.*, place.* " +
                    "FROM printer p " +
                    "LEFT JOIN object_buing ob " +
                    "ON p.printer_id = ob.id " +
                    "JOIN place place " +
                    "ON ob.place_id = place.id " +
                    "LEFT JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                    "LEFT JOIN model m " +
                    "ON p.model_id = m.id " +
                    "WHERE place.location_id = ?1 " +
                    "AND NOT p.printer_status = 'DELETE' " +
                    "AND m.device_type = ?2 " +
                    "AND m.archived = FALSE ", nativeQuery = true)
   List<Printer> findByLocationAndDeviceType(Long idLocation, String deviceType);
   
      @Query(value = "SELECT pr.*, ob.*, contr.*, m.*, place.* "
   + "FROM printer pr "
   + "LEFT JOIN object_buing ob "
   + "ON ob.id = pr.printer_id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id " 
   + "LEFT JOIN contract contr " 
   + "ON ob.contract_id = contr.id " 
   + "LEFT JOIN model m " 
   + "ON pr.model_id = m.id " 
   + "WHERE pr.model_id = ?1 "
   + "AND place.location_id = ?2 "
   + "AND (pr.printer_status = 'OK' "
   + "OR pr.printer_status = 'DEFECTIVE' "
   + "OR pr.printer_status = 'REPAIR') "
   + "AND m.archived = FALSE ", nativeQuery = true)
   List<Printer> findByModelIdAndLocationId(Long idModel, Long idLocation);
   
   @Query(value = "SELECT pr.* "
   + "FROM printer pr "
   + "JOIN object_buing ob "
   + "ON ob.id = pr.printer_id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id " 
   + "LEFT JOIN model m "
   + "ON pr.model_id = m.id "
   + "WHERE pr.model_id = ?1 "
   + "AND place.location_id = ?2 "
   + "AND (pr.printer_status = 'OK' "
   + "OR pr.printer_status = 'DEFECTIVE' "
   + "OR pr.printer_status = 'REPAIR') "
   + "AND m.archived = FALSE ",
      countQuery = "SELECT count(*) "
   + "FROM printer pr "
   + "JOIN object_buing ob "
   + "ON ob.id = pr.printer_id "
   + "JOIN place place " 
   + "ON ob.place_id = place.id " 
   + "LEFT JOIN model m "
   + "ON pr.model_id = m.id "
   + "WHERE pr.model_id = ?1 "
   + "AND place.location_id = ?2 "
   + "AND (pr.printer_status = 'OK' "
   + "OR pr.printer_status = 'DEFECTIVE' "
   + "OR pr.printer_status = 'REPAIR') "
   + "AND m.archived = FALSE " ,nativeQuery = true)
   Page<Printer> findByModelIdAndPlaceLocationId(Long idModel, Long idLocation, Pageable paging);
   Page<Printer> findByPlaceLocationIdAndContractContractNumberIgnoreCaseLike(Long parseLong, String contractNumber, Pageable paging);
}
