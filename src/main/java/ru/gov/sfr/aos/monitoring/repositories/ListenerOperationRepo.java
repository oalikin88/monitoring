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
import org.springframework.data.jpa.repository.Query;
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
    
    @Query(value = "SELECT listener.* " +
                   "FROM listener_operation listener " +
                    "WHERE listener.printerid = ?1 " +
                    "AND listener.date_operation = " +
                    "(SELECT MAX(date_operation) FROM listener_operation WHERE printerid = ?1) ", nativeQuery = true)
    List<ListenerOperation> findByPrinterIdLastDayCartridgeInstall(Long id);
    
    @Query(value = "SELECT * FROM listener_operation listener " +
                    "WHERE listener.location_id = ?1 " +
                    "AND listener.operation_type = 'UTIL' " +
                    "AND DATE(listener.date_operation) = ?2 ", nativeQuery = true)
    List<ListenerOperation> findByLocationAndDate(Long location, LocalDate startDate);
    
    @Query(value = "SELECT * FROM listener_operation listener " +
                    "WHERE listener.location_id = ?1 " +
                    "AND listener.operation_type = 'UTIL' " +
                    "AND (DATE(listener.date_operation) BETWEEN ?2 AND ?3) ", nativeQuery = true)
    List<ListenerOperation> findByLocationAndPeriod(Long location, LocalDate startDate, LocalDate endDate);
}
