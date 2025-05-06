package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplayModel;
import ru.gov.sfr.aos.monitoring.repositories.TerminalDisplayModelRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalDisplayModelService extends SvtModelService<TerminalDisplayModel, TerminalDisplayModelRepo>  {
    @Autowired
    private TerminalDisplayModelRepo repo;
    
       public List<TerminalDisplayModel> getModelsByManufacturerId(Long id) {
        return repo.findByManufacturerId(id);
    }
}
