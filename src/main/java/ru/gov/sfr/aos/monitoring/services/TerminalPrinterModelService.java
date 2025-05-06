package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinterModel;
import ru.gov.sfr.aos.monitoring.repositories.TerminalPrinterModelRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalPrinterModelService extends SvtModelService<TerminalPrinterModel, TerminalPrinterModelRepo> {

    @Autowired
    private TerminalPrinterModelRepo repo;

    public List<TerminalPrinterModel> getModelsByManufacturerId(Long id) {
        return repo.findByManufacturerId(id);
    }
}
