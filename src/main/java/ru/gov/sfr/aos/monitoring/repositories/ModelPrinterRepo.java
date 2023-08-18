/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface ModelPrinterRepo extends JpaRepository<Model, Long> {
    Optional<Model> findByName(String name);
    List<Model> findByManufacturerNameContainingIgnoreCase(String name);
    
    
}
