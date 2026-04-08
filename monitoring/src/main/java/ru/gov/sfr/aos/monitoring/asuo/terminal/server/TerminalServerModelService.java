package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalServerModelService extends SvtModelService<TerminalServerModel, TerminalServerModelRepo> {

    @Autowired
    private TerminalServerModelRepo repo;

    public List<TerminalServerModel> getModelsByManufacturerId(Long id) {
        return repo.findByManufacturerId(id);
    }
}