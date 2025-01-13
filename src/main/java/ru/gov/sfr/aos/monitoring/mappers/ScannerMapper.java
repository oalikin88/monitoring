/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;
import ru.gov.sfr.aos.monitoring.repositories.ScannerRepo;
/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring")
public abstract class ScannerMapper implements SvtMapper<Scanner, SvtScannerDTO> {
    @Autowired
    protected ScannerRepo scannerRepo;
    
    
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "ipAdress", target = "ipAdress")
    @Mapping(source = "scannerModel.model", target = "model")
    @Mapping(source = "scannerModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "scannerModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "scannerModel.manufacturer.id", target = "manufacturerId")
    public abstract SvtScannerDTO getDto(Scanner scanner);
    
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "ipAdress", source = "ipAdress")
    @Mapping(target = "contract", expression = "java(scannerRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "scannerModel.model", source = "model")
    @Mapping(target = "scannerModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    public abstract Scanner getEntityFromDto(SvtScannerDTO dto);
    
    
}
