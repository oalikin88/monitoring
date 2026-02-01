package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalSensorRepo extends ObjectBuingRepo<TerminalSensor>{
    List<TerminalSensor> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
