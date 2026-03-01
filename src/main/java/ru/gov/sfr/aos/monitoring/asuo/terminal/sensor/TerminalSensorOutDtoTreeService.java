package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectOutDtoTreeService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalSensorOutDtoTreeService extends ObjectOutDtoTreeService<TerminalSensor, TerminalSensorMapper, TerminalComponentDto> {

}
