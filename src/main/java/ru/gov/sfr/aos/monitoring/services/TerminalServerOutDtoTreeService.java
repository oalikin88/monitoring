package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalServer;
import ru.gov.sfr.aos.monitoring.mappers.TerminalServerMapper;
import ru.gov.sfr.aos.monitoring.models.TerminalComponentDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalServerOutDtoTreeService extends ObjectOutDtoTreeService<TerminalServer, TerminalServerMapper, TerminalComponentDto> {

}
