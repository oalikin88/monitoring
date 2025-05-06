package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.HubModel;
import ru.gov.sfr.aos.monitoring.repositories.HubModelRepo;

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
