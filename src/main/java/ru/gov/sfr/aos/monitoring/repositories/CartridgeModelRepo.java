/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface CartridgeModelRepo extends JpaRepository<CartridgeModel, Long>  {
    
    List<CartridgeModel> findByModel(String model);
    List<CartridgeModel> findByType(CartridgeType type);
}
