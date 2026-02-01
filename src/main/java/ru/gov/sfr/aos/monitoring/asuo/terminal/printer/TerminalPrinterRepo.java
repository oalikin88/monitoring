package ru.gov.sfr.aos.monitoring.asuo.terminal.printer;

import java.util.List;

import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingRepo;

/**
 *
 * @author Alikin Oleg
 */
public interface TerminalPrinterRepo extends ObjectBuingRepo<TerminalPrinter> {
List<TerminalPrinter> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
