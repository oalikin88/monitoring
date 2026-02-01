package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalUpsModelService extends SvtModelService<TerminalUpsModel, TerminalUpsModelRepo> {

    @Autowired
    private TerminalUpsModelRepo repo;

    public List<TerminalUpsModel> getModelsByManufacturerId(Long id) {
        return repo.findByManufacturerId(id);
    }
}
