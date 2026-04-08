package ru.gov.sfr.aos.monitoring.svtobject;

/**
 *
 * @author Alikin Oleg
 */
public interface ObjectBuingMapper <E extends ObjectBuing, T extends MainSvtDto> {

    public T getDto(E entity);
    public E getEntityFromDto(T dto);
}
