package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplayModel;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalDisplayModelRepo extends SvtModelsRepo<TerminalDisplayModel> {
    List<TerminalDisplayModel> findByManufacturerId(Long id);
}
