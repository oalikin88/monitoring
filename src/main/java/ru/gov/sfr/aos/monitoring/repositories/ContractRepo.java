/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.Contract;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface ContractRepo extends JpaRepository<Contract, Long> {
    Optional<Contract> findByContractNumberIgnoreCase(String contractNumber);
    boolean existsByContractNumberIgnoreCase(String contractNumber);
    
}
