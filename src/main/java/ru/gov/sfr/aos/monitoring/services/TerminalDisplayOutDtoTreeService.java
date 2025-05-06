package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplay;
import ru.gov.sfr.aos.monitoring.mappers.TerminalDisplayMapper;
import ru.gov.sfr.aos.monitoring.models.TerminalComponentDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalDisplayOutDtoTreeService extends ObjectOutDtoTreeService<TerminalDisplay, TerminalDisplayMapper, TerminalComponentDto> {

}
