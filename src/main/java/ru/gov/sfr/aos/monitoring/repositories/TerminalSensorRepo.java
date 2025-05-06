package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensor;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalSensorRepo extends ObjectBuingRepo<TerminalSensor>{
    List<TerminalSensor> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
