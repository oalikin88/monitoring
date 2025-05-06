package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.HubModel;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface HubModelRepo extends SvtModelsRepo<HubModel> {
    List<HubModel> findByManufacturerIdAndArchivedFalse(Long id);
}
