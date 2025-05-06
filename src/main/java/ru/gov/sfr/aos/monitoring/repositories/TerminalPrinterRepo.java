package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinter;

/**
 *
 * @author Alikin Oleg
 */
public interface TerminalPrinterRepo extends ObjectBuingRepo<TerminalPrinter> {
List<TerminalPrinter> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
}
