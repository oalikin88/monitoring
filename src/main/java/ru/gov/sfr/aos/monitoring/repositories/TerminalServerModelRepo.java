package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.TerminalServerModel;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalServerModelRepo extends SvtModelsRepo<TerminalServerModel> {
    List<TerminalServerModel> findByManufacturerId(Long id);
}
