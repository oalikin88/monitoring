package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensorModel;
import ru.gov.sfr.aos.monitoring.repositories.TerminalSensorModelRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalSensorModelService extends SvtModelService<TerminalSensorModel, TerminalSensorModelRepo>  {
   @Autowired
   private TerminalSensorModelRepo repo;
    
    public List<TerminalSensorModel> getModelsByManufacturerId(Long id) {
        return repo.findByManufacturerId(id);
    }
}
