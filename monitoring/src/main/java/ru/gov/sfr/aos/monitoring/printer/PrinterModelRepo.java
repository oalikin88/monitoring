package ru.gov.sfr.aos.monitoring.printer;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface PrinterModelRepo extends SvtModelsRepo<PrinterModel> {
    List<PrinterModel> findByManufacturerId(Long id);
}
