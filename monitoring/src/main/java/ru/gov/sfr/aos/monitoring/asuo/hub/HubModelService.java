package ru.gov.sfr.aos.monitoring.asuo.hub;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class HubModelService extends SvtModelService <HubModel, HubModelRepo> {
    @Autowired
    private HubModelRepo repo;
    
       public List<HubModel> getModelsByManufacturerId(Long id) {
        return repo.findByManufacturerIdAndArchivedFalse(id);
    }
}
