package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalUpsModelRepo extends SvtModelsRepo<TerminalUpsModel> {
    List<TerminalUpsModel> findByManufacturerId(Long id);
}
