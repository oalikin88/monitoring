package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensorModel;
import ru.gov.sfr.aos.monitoring.repositories.SvtModelsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalSensorModelRepo extends SvtModelsRepo<TerminalSensorModel> {
    List<TerminalSensorModel> findByManufacturerId(Long id);
}
