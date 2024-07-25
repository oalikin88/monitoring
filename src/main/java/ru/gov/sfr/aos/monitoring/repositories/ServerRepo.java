/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Server;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface ServerRepo extends ObjectBuingWithSerialAndInventaryRepo <Server> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Server> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
}
