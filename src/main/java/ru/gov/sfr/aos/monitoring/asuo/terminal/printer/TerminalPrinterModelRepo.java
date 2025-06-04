package ru.gov.sfr.aos.monitoring.asuo.terminal.printer;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalPrinterModelRepo extends SvtModelsRepo<TerminalPrinterModel> {
    List<TerminalPrinterModel> findByManufacturerId(Long id);
}
