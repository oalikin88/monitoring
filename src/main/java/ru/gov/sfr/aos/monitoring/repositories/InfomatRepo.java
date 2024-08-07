/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Infomat;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface InfomatRepo extends ObjectBuingWithSerialAndInventaryRepo<Infomat> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<Infomat> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
}
