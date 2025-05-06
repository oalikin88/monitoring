package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalServerModel;
import ru.gov.sfr.aos.monitoring.repositories.TerminalServerModelRepo;

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