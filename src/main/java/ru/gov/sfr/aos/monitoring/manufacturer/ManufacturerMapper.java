/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.manufacturer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */

public interface ManufacturerMapper<R extends ManufacturerModel, E extends ManufacturerDTO, M extends SvtModel> {
   
    default R getEntity(E dto){
        R r = (R)new ManufacturerModel();
        r.setId(dto.getId());
        r.setName(dto.getName()); 
        return  r;
    }
    
    
     default E getDto(R entity) {
       
        E dto = (E)new ManufacturerDTO();
        Set<SvtModelDto> modelDtoes = new HashSet<>();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
       
        for(M el : (Set<M>)entity.getModels()) {
            SvtModelDto modelDto = new SvtModelDto();
            modelDto.setModel(el.getModel());
            modelDto.setId(el.getId());
            modelDtoes.add(modelDto);
        }
        dto.setModels(modelDtoes);
        return dto;
    }
     
     default List<E> getDtoes(List<R> inputList) {
         return inputList.stream().map(e -> getDto(e)).collect(Collectors.toList());
     }
    
}
