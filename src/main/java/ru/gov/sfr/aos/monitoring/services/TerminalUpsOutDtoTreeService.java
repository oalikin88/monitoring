package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalUps;
import ru.gov.sfr.aos.monitoring.mappers.TerminalUpsMapper;
import ru.gov.sfr.aos.monitoring.models.TerminalComponentDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalUpsOutDtoTreeService extends ObjectOutDtoTreeService<TerminalUps, TerminalUpsMapper, TerminalComponentDto> {

}
