package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinterModel;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalPrinterModelRepo extends SvtModelsRepo<TerminalPrinterModel> {
    List<TerminalPrinterModel> findByManufacturerId(Long id);
}
