package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

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
