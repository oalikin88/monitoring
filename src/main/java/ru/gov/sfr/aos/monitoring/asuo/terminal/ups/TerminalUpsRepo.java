package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalUpsRepo extends ObjectBuingRepo<TerminalUps> {
    List<TerminalUps> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
