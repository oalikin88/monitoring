/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
