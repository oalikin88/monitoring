/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface ListenerOperationRepo extends JpaRepository<ListenerOperation, Long> {
    
    Set<ListenerOperation> findAllByDateOperationBetween(LocalDate dateBegin, LocalDate dateEnd);
    List<ListenerOperation> findByLocationIdAndModelId(Long locationId, Long modelId);
    List<ListenerOperation> findByCartridgeID(Long cartridgeID);
    List<ListenerOperation> findByDateOperationAfterAndDateOperationBefore(LocalDateTime startPeriod, LocalDateTime endPeriod);
}
