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
   List<Cartridge> findByLocationIdAndModelId(Long idLocation, Long idModel);
   List<Cartridge> findByModelId(Long idModel);
   List<Cartridge> findByContractContractNumberAndLocationIdAndModelId(String contractNumber, Long idLocation, Long idModel, Pageable pageable);
   List<Cartridge> findByContractContractNumberAndLocationId(String contractNumber, Long idLocation);
   List<Cartridge> findByContractContractNumberAndPrinterModelAndLocationId(String contractNumber, Long idLocation, Model modelPrinter);
   
   
   @Query(value = "SELECT cart.*, ob.*, cart_print.*, loc.* "
   + "FROM cartridge cart "
   + "JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "JOIN object_buing ob "
   + "ob.id = cart.cartridge_id "
   + "JOIN location loc "
   + "ON ob.location_id = loc.id "
   + "WHERE cart_print.models_printers_id = ?1 "
   + "AND loc.name = ?2 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE", nativeQuery = true)
   List<Cartridge> findByModelPrinterIdAndLocationName(Long idPrinter, String location);
   
   @Query(value = "SELECT cart.*, ob.*, contr.*, model.* "
   + "FROM cartridge cart "
   + "JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?3 "
   + "AND contr.contract_number = ?1 "
   + "AND ob.location_id = ?2 "
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
   + "JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?3 "
   + "AND contr.contract_number = ?1 "
   + "AND ob.location_id = ?2 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE" ,nativeQuery = true)
   Page<Cartridge> findByContractNumberAndModelPrinterAndLocation(String contractNumber, Long idLocation, Long idPrinter, Pageable pageable);
   
   @Query(value = "SELECT cart.*, ob.*, contr.* " +
                    "FROM cartridge cart " +
                    "LEFT JOIN object_buing ob " +
                    "ON cart.cartridge_id = ob.id " +
                    "LEFT JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                    "LEFT JOIN cartridge_model cart_model " +
                    "ON cart.model_id = cart_model.id " +
                    "WHERE cart_model.archived = FALSE " +
                    "AND cart.cartridge_id IN " +
                    "(SELECT DISTINCT c.cartridge_id " +
                    "FROM cartridge c " +
                    "LEFT JOIN object_buing ob " +
                    "ON c.cartridge_id = ob.id " +
                    "LEFT JOIN cartridge_model_models_printers cart_print " +
                    "ON c.model_id = cart_print.model_cartridges_id " +
                    "LEFT JOIN model m " +
                    "ON cart_print.models_printers_id = m.id " +
                    "WHERE ob.location_id = ?1 " +
                    "AND m.device_type = ?2) ",
           countQuery = "SELECT count(*) " +
                    "FROM cartridge cart " +
                    "LEFT JOIN object_buing ob " +
                    "ON cart.cartridge_id = ob.id " +
                    "LEFT JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                   "LEFT JOIN cartridge_model cart_model " +
                    "ON cart.model_id = cart_model.id " +
                    "WHERE cart_model.archived = FALSE " +
                    "AND cart.cartridge_id IN " +
                    "(SELECT DISTINCT c.cartridge_id " +
                    "FROM cartridge c " +
                    "LEFT JOIN object_buing ob " +
                    "ON c.cartridge_id = ob.id " +
                    "LEFT JOIN cartridge_model_models_printers cart_print " +
                    "ON c.model_id = cart_print.model_cartridges_id " +
                    "LEFT JOIN model m " +
                    "ON cart_print.models_printers_id = m.id " +
                    "WHERE ob.location_id = ?1 " +
                    "AND m.device_type = ?2)", nativeQuery = true)
   Page<Cartridge> findByLocationIdAndDeviceType(Long idLocation, String deviceType, Pageable pageable);
   
   @Query(value = "SELECT cart.*, ob.*, contr.* " +
                    "FROM cartridge cart " +
                    "LEFT JOIN object_buing ob " +
                    "ON cart.cartridge_id = ob.id " +
                    "LEFT JOIN contract contr " +
                    "ON ob.contract_id = contr.id " +
                   "LEFT JOIN cartridge_model cart_model " +
                    "ON cart.model_id = cart_model.id " +
                    "WHERE cart_model.archived = FALSE " +
                    "AND cart.cartridge_id IN " +
                    "(SELECT DISTINCT c.cartridge_id " +
                    "FROM cartridge c " +
                    "LEFT JOIN object_buing ob " +
                    "ON c.cartridge_id = ob.id " +
                    "LEFT JOIN cartridge_model_models_printers cart_print " +
                    "ON c.model_id = cart_print.model_cartridges_id " +
                    "LEFT JOIN model m " +
                    "ON cart_print.models_printers_id = m.id " +
                    "WHERE ob.location_id = ?1 " +
                    "AND m.device_type = ?2)", nativeQuery = true)
   List<Cartridge> findByLocationIdAndDeviceType(Long idLocation, String deviceType);
   
   
   @Query(value = "SELECT cart.*, contr.*, ob.*, model.* "
   + "FROM cartridge cart "
   + "JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?3 "
   + "AND contr.contract_number = ?1 "
   + "AND ob.location_id = ?2 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE" ,nativeQuery = true)
   List<Cartridge>findByContractNumberAndModelPrinterAndLocation(String contractNumber, Long idLocation, Long idPrinter);
   
   
   @Query(value = "SELECT cart.*, ob.*, contr.*, model.* "
   + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = :idPrinter "
   + "AND cart.item_code LIKE CONCAT('%',:itemCode,'%')  "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE ",
   countQuery = "SELECT count(*) "
    + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = :idPrinter "
   + "AND cart.item_code LIKE CONCAT('%',:itemCode,'%')  "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE " ,nativeQuery = true)
   Page<Cartridge> findByItemCodeAndModelPrinterAndLocation(@Param("itemCode") String itemCode, @Param("idLocation") Long idLocation, @Param("idPrinter") Long idPrinter, Pageable pageable);
   
   @Query(value = "SELECT cart.*, ob.*, contr.*, model.* "
   + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = :idPrinter "
   + "AND cart.item_code LIKE CONCAT('%',:itemCode,'%')  "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE ", nativeQuery = true)
   List<Cartridge> findByItemCodeAndModelPrinterAndLocation(@Param("itemCode") String itemCode, @Param("idLocation") Long idLocation, @Param("idPrinter") Long idPrinter);
   
   
     @Query(value = "SELECT cart.*, ob.*, contr.*, model.* "
   + "FROM cartridge cart "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE cart.item_code LIKE CONCAT('%',:itemCode,'%') "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE ",
   countQuery = "SELECT count(*) "
    + "FROM cartridge cart "
    + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "WHERE cart.item_code LIKE CONCAT('%',:itemCode,'%')  "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE ", nativeQuery = true)
   Page<Cartridge> findByItemCodeAndLocation(@Param("itemCode") String itemCode, @Param("idLocation") Long idLocation, Pageable pageable);
   
   @Query(value = "SELECT cart.*, ob.*, contr.*, model.* "
   + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "LEFT JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?2 "
   + "AND ob.location_id = ?1 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE ",
   countQuery = "SELECT count(*) "
    + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "LEFT JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?2 "
   + "AND ob.location_id = ?1 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE " ,nativeQuery = true)
   Page<Cartridge> findCartridgesByModelPrinterAndLocation(Long idLocation, Long idPrinter, Pageable pageable);
   
   
     @Query(value = "SELECT cart.*, ob.*, contr.*, model.* "
   + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "LEFT JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = :idPrinter "
   + "AND model.model LIKE CONCAT('%',:cartridgeModel,'%') "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE ",
   countQuery = "SELECT count(*) "
    + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "LEFT JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = :idPrinter "
   + "AND model.model LIKE CONCAT('%',:cartridgeModel,'%') "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE " ,nativeQuery = true)
   Page<Cartridge> findCartridgesByModelPrinterAndLocationAndCartridgeModel(@Param("idLocation") Long idLocation, @Param("idPrinter") Long idPrinter, @Param("cartridgeModel") String cartridgeModel, Pageable pageable);
   
   
        @Query(value = "SELECT cart.*, ob.*, contr.*, model.* "
   + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "LEFT JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE model.model LIKE CONCAT('%',:cartridgeModel,'%') "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE ",
   countQuery = "SELECT count(*) "
    + "FROM cartridge cart "
   + "LEFT JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "LEFT JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "LEFT JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "LEFT JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE model.model LIKE CONCAT('%',:cartridgeModel,'%') "
   + "AND ob.location_id = :idLocation "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE " ,nativeQuery = true)
   Page<Cartridge> findCartridgesByLocationAndCartridgeModel(@Param("idLocation") Long idLocation, @Param("cartridgeModel") String cartridgeModel, Pageable pageable);
   
   
   @Query(value = "SELECT cart.*, ob.*, contr.*, model.* "
   + "FROM cartridge cart "
   + "JOIN object_buing ob "
   + "ON cart.cartridge_id = ob.id "
   + "JOIN contract contr "
   + "ON ob.contract_id = contr.id "
   + "INNER JOIN cartridge_model_models_printers cart_print "
   + "ON cart.model_id = cart_print.model_cartridges_id "
   + "JOIN cartridge_model model "
   + "ON cart.model_id = model.id "
   + "WHERE cart_print.models_printers_id = ?2 "
   + "AND ob.location_id = ?1 "
   + "AND cart.util = FALSE "
   + "AND cart.use_in_printer = FALSE "
   + "AND model.archived = FALSE ", nativeQuery = true)
   List<Cartridge> findCartridgesByModelPrinterAndLocation(Long idLocation, Long idPrinter);
   
   
   @Query(value = "SELECT cart.*, ob.*, cart_print.*, p.* " +
    "FROM cartridge cart " +
    "LEFT JOIN object_buing ob " +
    "ON cart.cartridge_id = ob.id " +
    "LEFT JOIN cartridge_model_models_printers cart_print " +
    "ON cart.model_id = cart_print.model_cartridges_id " +
    "RIGHT JOIN printer p " +
    "ON p.model_id = cart_print.models_printers_id " +
    "WHERE ob.location_id = ?1 " +
    "AND p.printer_id = ?2 " +
    "AND cart.util = FALSE " +
    "AND cart.use_in_printer = FALSE", nativeQuery = true)
   List<Cartridge> findCartridgesByLocationIdAndPrinterIdSuitable(Long locationId, Long printerId);
   
   @Query(value = "SELECT cart.*, ob.*, contr.*, cart_model.*, cart_manuf.* " +
                "FROM cartridge cart " +
                "LEFT JOIN object_buing ob " +
                "ON cart.cartridge_id = ob.id " +
                "LEFT JOIN contract contr " +
                "ON contr.id = ob.contract_id " +
                "LEFT JOIN cartridge_model cart_model " +
                "ON cart.model_id = cart_model.id " +
                "LEFT JOIN cartridge_manufacturer cart_manuf " +
                "ON cart_model.cartridge_manufacturer_id = cart_manuf.id " +
                "WHERE ob.location_id = ?1 " +
                "AND cart.use_in_printer = FALSE " +
                "AND cart.util = FALSE " +
                "AND cart_model.archived = FALSE ",
           countQuery = "SELECT count(*) " +
                "FROM cartridge cart " +
                "LEFT JOIN object_buing ob " +
                "ON cart.cartridge_id = ob.id " +
                   "LEFT JOIN contract contr " +
                "ON contr.id = ob.contract_id " +
               "LEFT JOIN cartridge_model cart_model " +
                "ON cart.model_id = cart_model.id " +
              "LEFT JOIN cartridge_manufacturer cart_manuf " +
                "ON cart_model.cartridge_manufacturer_id = cart_manuf.id " +
                "WHERE ob.location_id = ?1 " +
                "AND cart.use_in_printer = false " +
                "AND cart.util = false " +
                "AND cart_model.archived = FALSE ", nativeQuery = true)
   Page<Cartridge> findByLocationId(Long idLocation, Pageable pageable); // Изменить чтобы не подтягивались картриджи списанные и использующиеся
   @Query(value = "SELECT cart.*, ob.*, contr.*, cart_model.*, cart_manuf.* " +
                "FROM cartridge cart " +
                "LEFT JOIN object_buing ob " +
                "ON cart.cartridge_id = ob.id "  +
            "LEFT JOIN contract contr " +
            "ON ob.contract_id = contr.id " +
               "LEFT JOIN cartridge_model cart_model " +
                "ON cart.model_id = cart_model.id " +
                  "LEFT JOIN cartridge_manufacturer cart_manuf " +
                "ON cart_model.cartridge_manufacturer_id = cart_manuf.id " +
                "WHERE ob.location_id = ?1 " +
                "AND cart.use_in_printer = false " +
                "AND cart.util = false " +
                "AND cart_model.archived = FALSE ", nativeQuery = true)
   List<Cartridge> findByLocationId(Long idLocation);
   Page<Cartridge> findByLocationIdAndContractContractNumberIgnoreCaseLike(Long idLocation, String contractNumber, Pageable pageable);
   
}
