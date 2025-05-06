package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.TerminalUpsModel;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalUpsModelRepo extends SvtModelsRepo<TerminalUpsModel> {
    List<TerminalUpsModel> findByManufacturerId(Long id);
}
