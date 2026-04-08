/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo.tv;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;
import ru.gov.sfr.aos.monitoring.asuo.AsuoRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class DisplayMapper implements SvtMapper<Display, DisplayDto> {
    @Autowired
    protected DisplayRepo displayRepo;
    @Autowired
    protected DisplayModelRepo displayModelRepo;
    @Autowired
    protected AsuoRepo asuoRepo;


    @Mapping(source = "displayModel.manufacturer.name", target = "manufacturer")
    @Mapping(source = "displayModel.model", target = "model")
    @Mapping(source = "displayModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(expression = "java(entity.getAsuo().stream().findFirst().isPresent() ? entity.getAsuo().stream().findFirst().get().getId() : null)", target = "asuoId")
    @Mapping(expression = "java(entity.getAsuo().size() == 0 ? false : true)", target = "hasInstalled")
    @Override
    public abstract DisplayDto getDto(Display entity);

    @Mapping(target = "contract", expression = "java(displayRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "displayModel", expression = "java(displayModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "displayModel.model", source = "model")
    @Mapping(target = "displayModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(expression = "java(null == dto.asuoId ? null : asuoRepo.findById(dto.asuoId).stream().collect(java.util.stream.Collectors.toSet()))", target = "asuo")
    @Override
    public abstract Display getEntityFromDto(DisplayDto dto);
    
}
