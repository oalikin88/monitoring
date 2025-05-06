package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Hub;
import ru.gov.sfr.aos.monitoring.mappers.HubMapper;
import ru.gov.sfr.aos.monitoring.models.SvtHubDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class HubOutDtoTreeService extends SvtOutDtoTreeService<Hub, HubMapper, SvtHubDto>  {

}
