package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.TerminalUps;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalUpsRepo extends ObjectBuingRepo<TerminalUps> {
    List<TerminalUps> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
