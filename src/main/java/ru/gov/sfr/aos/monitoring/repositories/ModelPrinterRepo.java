/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface ModelPrinterRepo extends JpaRepository<Model, Long> {
    Optional<Model> findByNameIgnoreCase(String name);
    List<Model> findByManufacturerNameContainingIgnoreCase(String name);
    
    @Query(value = "SELECT DISTINCT m.* " +
                    "FROM model m " +
                    "LEFT JOIN cartridge_model_models_printers cart_print " +
                    "ON m.id = cart_print.models_printers_id " +
                    "WHERE cart_print.model_cartridges_id IN (SELECT cart_print.model_cartridges_id " +
                    "FROM model m " +
                    "LEFT JOIN cartridge_model_models_printers cart_print " +
                    "ON m.id = cart_print.models_printers_id " +
                    "WHERE m.id = ?1)", nativeQuery = true)
    List<Model> findAnalogModelByModelId(Long idModel);
}
