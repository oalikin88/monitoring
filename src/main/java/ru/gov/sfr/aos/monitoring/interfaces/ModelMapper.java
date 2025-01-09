/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import java.util.List;
import ru.gov.sfr.aos.monitoring.models.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
public interface ModelMapper<T> {
    
    T getModel(SvtModelDto dto);
    SvtModelDto getDto(T entity);
    List<SvtModelDto> getListDtoes(List<T> inputList);
    SvtModelDto getDtoForSelectize(T entity);
    
}
