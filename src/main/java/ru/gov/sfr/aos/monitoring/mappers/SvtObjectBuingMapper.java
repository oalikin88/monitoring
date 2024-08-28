/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.models.MainSvtDto;

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
