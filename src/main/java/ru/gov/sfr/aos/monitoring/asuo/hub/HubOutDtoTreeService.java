package ru.gov.sfr.aos.monitoring.asuo.hub;

import org.springframework.stereotype.Service;

import ru.gov.sfr.aos.monitoring.models.SvtHubDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtOutDtoTreeService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class HubOutDtoTreeService extends SvtOutDtoTreeService<Hub, HubMapper, SvtHubDto>  {

}
