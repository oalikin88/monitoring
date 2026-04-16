package ru.gov.sfr.aos.monitoring.svtobject;

/**
 *
 * @author 041AlikinOS
 * @param <E>
 * @param <T>
 */
public interface SvtMapper<E extends ObjectBuingWithSerialAndInventary, T extends MainSvtDto> {
    
    public T getDto(E entity);
    public E getEntityFromDto(T dto);
    
}
