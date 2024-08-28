/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Display;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.DisplayModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.DisplayRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class DisplayMapper implements SvtMapper<Display, SvtDTO> {
    @Autowired
    protected DisplayRepo displayRepo;
    @Autowired
    protected DisplayModelRepo displayModelRepo;

    @Mapping(source = "displayModel.model", target = "model")
    @Mapping(source = "displayModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Override
    public abstract SvtDTO getDto(Display entity);

    @Mapping(target = "contract", expression = "java(displayRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "displayModel", expression = "java(displayModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "displayModel.model", source = "model")
    @Mapping(target = "displayModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Override
    public abstract Display getEntityFromDto(SvtDTO dto);
    
}
