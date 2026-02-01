/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.location;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {
    Optional<Location> findByNameIgnoreCase(String name); 
    List<Location> findByNameNot(String name);
    
    
}
