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
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.Model;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface CartridgeRepo extends JpaRepository<Cartridge, Long> {
   List<Cartridge> findByLocationName(String location);
   List<Cartridge> findByLocationIdAndModelId(Long idLocation, Long idModel, Pageable pageable);
   List<Cartridge> findByModelId(Long idModel);
   List<Cartridge> findByContractContractNumberAndLocationIdAndModelId(String contractNumber, Long idLocation, Long idModel, Pageable pageable);
   List<Cartridge> findByContractContractNumberAndLocationId(String contractNumber, Long idLocation);
   List<Cartridge> findByContractContractNumberAndPrinterModelAndLocationId(String contractNumber, Long idLocation, Model modelPrinter);
   
   @Query(value = "SELECT cart.*, contr.contract_number, contr.date_start_contract, model.* "
   + "FROM cartridge cart "
   + "JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "JOIN model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?3 "
   + "AND contr.contract_number = ?1 "
   + "AND cart.location_id = ?2 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE",
   countQuery = "SELECT count(*) "
    + "FROM cartridge cart "
   + "JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "JOIN model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?3 "
   + "AND contr.contract_number = ?1 "
   + "AND cart.location_id = ?2 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE" ,nativeQuery = true)
   Page<Cartridge> findByContractNumberAndModelPrinterAndLocation(String contractNumber, Long idLocation, Long idPrinter, Pageable pageable);
   
   @Query(value = "SELECT cart.*, contr.contract_number, contr.date_start_contract, model.* "
   + "FROM cartridge cart "
   + "JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "JOIN model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?2 "
   + "AND cart.location_id = ?1 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE",
   countQuery = "SELECT count(*) "
    + "FROM cartridge cart "
   + "JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "JOIN model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?2 "
   + "AND cart.location_id = ?1 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE" ,nativeQuery = true)
   Page<Cartridge> findCartridgesByModelPrinterAndLocation(Long idLocation, Long idPrinter, Pageable pageable);
   
   Page<Cartridge> findByLocationId(Long idLocation, Pageable pageable);
   Page<Cartridge> findByLocationIdAndContractContractNumberIgnoreCaseLike(Long idLocation, String contractNumber, Pageable pageable);
   
}
