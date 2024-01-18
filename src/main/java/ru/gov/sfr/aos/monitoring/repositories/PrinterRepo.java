/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    List<Printer> findByLocationAndModel(Location location, Model model);
    List<Printer> findByLocationName(String name);
    List<Printer> findByLocationIdAndModelId(Long idLocation, Long idModel);
    List<Printer> findByLocationNameAndModelIdAndPrinterStatusEquals(String location, Long idModel, PrinterStatus status);
    List<Printer> findByModelId(Long id);
    
    @Query(value = "SELECT * FROM printer p " +
                    "WHERE p.inventory_number LIKE '%?1%' " +
                    "AND NOT p.printer_status = 'DELETE' ", nativeQuery = true)
    List<Printer> findByInventaryNumber(String inventaryNumber);
    
    @Query(value = "SELECT p.*, ob.*, c.* " +
                   "FROM printer p " +
                   "LEFT JOIN object_buing ob " +
                   "ON ob.id = p.printer_id " +
                   "LEFT JOIN contract c " +
                   "ON c.id = ob.contract_id " +
                   "WHERE p.printer_id = ?1" , nativeQuery = true)
    Printer findByPrinterId(Long id);
    
   @Query(value = "SELECT printer.*, manufacturer.*, model.*, cartridge_printer.*, contr.*, ob.* "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN cartridge_model_models_printers cartridge_printer "
   + "ON printer.model_id = cartridge_printer.models_printers_id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE cartridge_printer.model_cartridges_id = ?2 "
   + "AND ob.location_id = ?1 "
   + "AND NOT printer.printer_status = 'DELETE' ",
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
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE cartridge_printer.model_cartridges_id = ?2 "
   + "AND ob.location_id = ?1 " 
   + "AND NOT printer.printer_status = 'DELETE' ", nativeQuery = true)
    Page<Printer> findPrintersByModelAndLocation(Long idLocation, Long idModelCarttridge, Pageable pageable);
    
    @Query(value = "SELECT printer.*, manufacturer.*, model.*, cartridge_printer.*, ob.*, contr.* "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN cartridge_model_models_printers cartridge_printer "
   + "ON printer.model_id = cartridge_printer.models_printers_id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE cartridge_printer.model_cartridges_id = ?2 "
   + "AND ob.location_id = ?1 "
    + "AND contr.contract_number LIKE ?3%",
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
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE cartridge_printer.model_cartridges_id = ?2 "
   + "AND ob.location_id = ?1 "
   + "AND contr.contract_number LIKE ?3%" ,nativeQuery = true)
    Page<Printer> findPrintersByModelAndLocationAndContractNumberFilter(Long idLocation, Long idModelCartridge, String contractNumber, Pageable pageable);
    
    
    @Query(value = "SELECT printer.*, manufacturer.*, model.*, cartridge_printer.*, ob.*, contr.* "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN cartridge_model_models_printers cartridge_printer "
   + "ON printer.model_id = cartridge_printer.models_printers_id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE contr.contract_number LIKE ?3% "
   + "AND ob.location_id = ?1 "
   + "AND model.id = ?2 ",
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
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE contr.contract_number LIKE ?3% "
   + "AND ob.location_id = ?1 "
   + "AND model.id = ?2 " ,nativeQuery = true)
    Page<Printer> findPrintersByModelPrinterAndLocationAndContractNumberFilter(Long idLocation, Long idModelPrinter, String contractNumber, Pageable pageable);
    
    
    @Query(value = "SELECT printer.*, manufacturer.*, model.*, ob.*, contr.* "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE model.id = ?2 "
   + "AND ob.location_id = ?1 ",
   countQuery = "SELECT count(*) "
   + "FROM printer printer "
   + "JOIN manufacturer manufacturer "
   + "ON printer.manufacturer_id = manufacturer.id "
   + "JOIN model model "
   + "ON printer.model_id = model.id "
   + "JOIN object_buing ob "
   + "ON printer.printer_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE model.id = ?2 "
   + "AND ob.location_id = ?1 " ,nativeQuery = true)
    Page<Printer> findPrintersByModelPrinterAndLocation(Long idLocation, Long idModelPrinter, Pageable pageable);


   Page<Printer> findByLocationId(Long idLocation, Pageable pageable);
   List<Printer> findByLocationId(Long idLocation);
   
      @Query(value = "SELECT pr.* "
   + "FROM printer pr "
   + "JOIN object_buing ob "
   + "ON ob.id = pr.printer_id "
   + "WHERE pr.model_id = ?1 "
   + "AND ob.location_id = ?2 "
   + "AND pr.printer_status = 'OK' ", nativeQuery = true)
   List<Printer> findByModelIdAndLocationId(Long idModel, Long idLocation);
   
   @Query(value = "SELECT pr.* "
   + "FROM printer pr "
   + "JOIN object_buing ob "
   + "ON ob.id = pr.printer_id "
   + "WHERE pr.model_id = ?1 "
   + "AND ob.location_id = ?2 "
   + "AND pr.printer_status = 'OK' "
   + "OR pr.printer_status = 'DEFECTIVE' "
   + "OR pr.printer_status = 'REPAIR' ",
      countQuery = "SELECT count(*) "
   + "FROM printer pr "
   + "JOIN object_buing ob "
   + "ON ob.id = pr.printer_id "
   + "WHERE pr.model_id = ?1 "
   + "AND ob.location_id = ?2 "
   + "AND pr.printer_status = 'OK' "
   + "OR pr.printer_status = 'DEFECTIVE' "
   + "OR pr.printer_status = 'REPAIR' " ,nativeQuery = true)
   Page<Printer> findByModelIdAndLocationId(Long idModel, Long idLocation, Pageable paging);
   Page<Printer> findByLocationIdAndContractContractNumberIgnoreCaseLike(Long parseLong, String contractNumber, Pageable paging);
}
