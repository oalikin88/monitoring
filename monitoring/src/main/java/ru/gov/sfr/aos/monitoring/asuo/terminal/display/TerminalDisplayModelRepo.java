package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalDisplayModelRepo extends SvtModelsRepo<TerminalDisplayModel> {
    List<TerminalDisplayModel> findByManufacturerId(Long id);
}
