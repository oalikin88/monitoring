/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.Router;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;


/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface RouterRepo extends ObjectBuingWithSerialAndInventaryRepo<Router> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Router> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
}
