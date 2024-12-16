/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.PhoneRepo;


/**
 *
 * @author 041AlikinOS
 */

@Mapper(componentModel = "spring")
public abstract class PhoneMapper implements SvtMapper<Phone, SvtDTO> {
    @Autowired
    protected PhoneRepo phoneRepo;
            
    
    @Mapping(source = "phoneModel.model", target = "model")
    @Mapping(source = "phoneModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "nameFromeOneC", target = "nameFromOneC")
    public abstract SvtDTO getDto(Phone phone);
    
    @Mapping(target = "contract", expression = "java(phoneRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "phoneModel.model", source = "model")
    @Mapping(target = "phoneModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "nameFromeOneC", source = "nameFromOneC")
    public abstract Phone getEntityFromDto(SvtDTO dto);
    

   
}
