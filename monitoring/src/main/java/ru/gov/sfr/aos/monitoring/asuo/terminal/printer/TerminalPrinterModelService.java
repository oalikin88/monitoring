package ru.gov.sfr.aos.monitoring.asuo.terminal.printer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

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
