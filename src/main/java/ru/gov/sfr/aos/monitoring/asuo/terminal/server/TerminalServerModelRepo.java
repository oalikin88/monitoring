package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalServerModelRepo extends SvtModelsRepo<TerminalServerModel> {
    List<TerminalServerModel> findByManufacturerId(Long id);
}
