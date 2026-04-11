package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalDisplayRepo extends ObjectBuingRepo<TerminalDisplay> {
    List<TerminalDisplay> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
