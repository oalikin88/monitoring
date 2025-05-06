package ru.gov.sfr.aos.monitoring.interfaces;

import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.models.MainSvtDto;

/**
 *
 * @author Alikin Oleg
 */
public interface ObjectBuingMapper <E extends ObjectBuing, T extends MainSvtDto> {

    public T getDto(E entity);
    public E getEntityFromDto(T dto);
}
