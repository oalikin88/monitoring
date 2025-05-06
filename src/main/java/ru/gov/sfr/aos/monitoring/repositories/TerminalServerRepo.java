package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensor;
import ru.gov.sfr.aos.monitoring.entities.TerminalServer;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalServerRepo extends ObjectBuingRepo<TerminalServer> {
    List<TerminalServer> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
