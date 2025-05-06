package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinter;
import ru.gov.sfr.aos.monitoring.mappers.TerminalPrinterMapper;
import ru.gov.sfr.aos.monitoring.models.TerminalComponentDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalPrinterOutDtoTreeService extends ObjectOutDtoTreeService<TerminalPrinter, TerminalPrinterMapper, TerminalComponentDto> {

}
