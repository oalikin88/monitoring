/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.ApplicationLog;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface ApplicationLogRepo extends JpaRepository<ApplicationLog, Long> {
    
}
