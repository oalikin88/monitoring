/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.svtobject;

/**
 *
 * @author Alikin Oleg
 * @param <E>
 * @param <T>
 */
public interface SvtObjectBuingMapper <E extends ObjectBuing, T extends MainSvtDto> {
    public T getDto(E entity);
    public E getEntityFromDto(T dto);
}
