package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalSensorModelRepo extends SvtModelsRepo<TerminalSensorModel> {
    List<TerminalSensorModel> findByManufacturerId(Long id);
}
