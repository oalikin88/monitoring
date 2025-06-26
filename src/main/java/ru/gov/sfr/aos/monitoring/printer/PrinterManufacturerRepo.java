package ru.gov.sfr.aos.monitoring.printer;

import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerModelRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface PrinterManufacturerRepo extends ManufacturerModelRepo<PrinterManufacturer>  {

}
