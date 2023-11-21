/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import java.util.Map;
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
    
   @Query(value = "SELECT printer.* "
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
   + "AND printer.location_id = ?1 ",
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
   + "AND printer.location_id = ?1 " ,nativeQuery = true)
    Page<Printer> findPrintersByModelAndLocation(Long idLocation, Long idModelCarttridge, Pageable pageable);
    
    @Query(value = "SELECT printer.* "
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
   + "AND printer.location_id = ?1 "
    + "AND contr.contract_number = ?3",
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
   + "AND printer.location_id = ?1 "
   + "AND contr.contract_number = ?3" ,nativeQuery = true)
    Page<Printer> findPrintersByModelAndLocationAndContractNumberFilter(Long idLocation, Long idModelCartridge, String contractNumber, Pageable pageable);
    
}
