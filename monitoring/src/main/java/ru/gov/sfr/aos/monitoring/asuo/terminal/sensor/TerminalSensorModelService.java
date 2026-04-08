package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

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
