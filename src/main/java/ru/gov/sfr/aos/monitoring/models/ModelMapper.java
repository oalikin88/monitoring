/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.List;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
public interface ModelMapper<T, D extends SvtModelDto> {
    
    T getModel(D dto);
    D getDto(T entity);
    List<D> getListDtoes(List<T> inputList);
    D getDtoForSelectize(T entity);
    
}
