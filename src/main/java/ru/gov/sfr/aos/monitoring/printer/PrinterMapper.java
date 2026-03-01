package ru.gov.sfr.aos.monitoring.printer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class PrinterMapper implements SvtMapper<Printer, PrinterDto> {
    
    @Autowired
    protected PrinterRepo printerRepo;
    @Autowired
    protected PrinterModelRepo modelRepo;

    @Mapping(source = "printerModel.model", target = "model")
    @Mapping(source = "printerModel.id", target = "modelId")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "nameFromeOneC", target = "nameFromOneC")
    @Mapping(source = "numberRoom", target = "numberRoom")
    @Mapping(source = "printerModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "printerModel.manufacturer.id", target = "manufacturerId")
    @Override
    public abstract PrinterDto getDto(Printer printer);
    
    @Mapping(target = "contract", expression = "java(printerRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "printerModel.model", source = "model")
    @Mapping(target = "printerModel.id", source = "modelId")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "numberRoom", source = "numberRoom")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "nameFromeOneC", source = "nameFromOneC")
    @Override
    public abstract Printer getEntityFromDto(PrinterDto dto);
}
