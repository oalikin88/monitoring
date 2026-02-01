package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalServerRepo extends ObjectBuingRepo<TerminalServer> {
    List<TerminalServer> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
