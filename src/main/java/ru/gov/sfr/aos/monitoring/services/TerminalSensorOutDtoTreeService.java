package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensor;
import ru.gov.sfr.aos.monitoring.mappers.TerminalSensorMapper;
import ru.gov.sfr.aos.monitoring.models.TerminalComponentDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalSensorOutDtoTreeService extends ObjectOutDtoTreeService<TerminalSensor, TerminalSensorMapper, TerminalComponentDto> {

}
