package ru.gov.sfr.aos.monitoring.asuo.hub;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface HubModelRepo extends SvtModelsRepo<HubModel> {
    List<HubModel> findByManufacturerIdAndArchivedFalse(Long id);
}
