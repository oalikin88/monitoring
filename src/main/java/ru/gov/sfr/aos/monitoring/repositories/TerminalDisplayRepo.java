package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplay;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalDisplayRepo extends ObjectBuingRepo<TerminalDisplay> {
    List<TerminalDisplay> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
