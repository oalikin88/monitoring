/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface CartridgeModelRepo extends JpaRepository<CartridgeModel, Long>  {
    
    Optional<CartridgeModel> findByModelIgnoreCase(String model);
    List<CartridgeModel> findByType(CartridgeType type);
    List<CartridgeModel> findByModelsPrintersId(Long id);
    boolean existsByModelIgnoreCase(String model);
    List<CartridgeModel> findByCartridgeManufacturerManufacturerNameIgnoreCaseAndType(String manufacturer, CartridgeType type);
    
    @Query(value = "SELECT cart_model.*, cart_manufacturer.* " +
                    "FROM cartridge_model cart_model " +
                    "LEFT JOIN cartridge_manufacturer cart_manufacturer " +
                    "ON cart_model.cartridge_manufacturer_id = cart_manufacturer.id " +
                    "WHERE cart_manufacturer.manufacturer_name LIKE CONCAT('%',:manufacturer,'%') " +
                    "AND cart_model.`type` = :type " +
                    "AND cart_model.archived = FALSE ", nativeQuery = true)
    List<CartridgeModel> findByManufacturerAndType(@Param("manufacturer") String manufacturer, @Param("type") String type);
    
    @Query(value = "SELECT cart_model.*, cart_print.*, m.* " +
                    "FROM cartridge_model cart_model " +
                    "LEFT JOIN cartridge_model_models_printers cart_print " +
                    "ON cart_model.id = cart_print.model_cartridges_id " +
                    "LEFT JOIN model m " +
                    "ON cart_print.models_printers_id = m.id " +
                    "WHERE m.name = ?1 " +
                    "AND cart_model.type = ?2 ", nativeQuery = true)
    List<CartridgeModel> findByModelPrinterAndCartridgeType(String modelPrinter, String cartridgeType);
    
    
    @Query(value = "SELECT cart_model.*, cart_manuf.*, cart_print.*, m.* " +
                    "FROM cartridge_model cart_model " +
                    "LEFT JOIN cartridge_manufacturer cart_manuf " +
                    "ON cart_model.cartridge_manufacturer_id = cart_manuf.id " +
                    "LEFT JOIN cartridge_model_models_printers cart_print " +
                    "ON cart_model.id = cart_print.model_cartridges_id " +
                    "LEFT JOIN model m " +
                    "ON cart_print.models_printers_id = m.id " +
                    "WHERE m.name = ?1 " +
                    "AND cart_model.type = ?2 " +
                    "AND cart_manuf.manufacturer_name = ?3 ", nativeQuery = true)
    List<CartridgeModel> findByModelPrinterAndCartridgeTypeAndCartridgeManufacturer(String modelPrinter, String cartridgeType, String cartridgeManufacturer);
    
    @Modifying
    @Query(value = "DELETE FROM cartridge_model " +
                    "WHERE id = ?1", nativeQuery = true )
    void deleteCartridgeModelById(Long id);
    
    @Query(value = "SELECT cart_model.* "
                 + "FROM cartridge_model cart_model "
                 + "WHERE cart_model.archived = FALSE ", nativeQuery = true)
    List<CartridgeModel> findAllIsArchivedFalse();
    
    @Query(value = "SELECT cart_model.* "
                 + "FROM cartridge_model cart_model "
                 + "WHERE cart_model.archived = TRUE ", nativeQuery = true)
    List<CartridgeModel> findAllIsArchivedTrue();
    
}
