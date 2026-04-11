package ru.gov.sfr.aos.monitoring.asuo.hub;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.asuo.hub.Hub;
import ru.gov.sfr.aos.monitoring.asuo.hub.HubDto;
import ru.gov.sfr.aos.monitoring.models.SvtHubDto;
import ru.gov.sfr.aos.monitoring.asuo.AsuoRepo;
import ru.gov.sfr.aos.monitoring.asuo.hub.HubModelRepo;
import ru.gov.sfr.aos.monitoring.asuo.hub.HubRepo;
import ru.gov.sfr.aos.monitoring.svtobject.SvtMapper;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class HubMapper implements SvtMapper<Hub, HubDto> {

    
    @Autowired
    protected HubRepo hubRepo;
    @Autowired
    protected HubModelRepo hubModelRepo;
    @Autowired
    protected AsuoRepo asuoRepo;
    
    @Mapping(source = "yearCreated", target = "yearCreated")
    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.username", target = "placeName")
    @Mapping(source = "place.placeType", target = "placeType")
    @Mapping(source = "place.departmentCode", target = "departmentCode")
    @Mapping(source = "place.location.id", target = "locationId")
    @Mapping(source = "hubModel.model", target = "model")
    @Mapping(source = "hubModel.id", target = "modelId")
    @Mapping(source = "portAmount", target = "portAmount")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "inventaryNumber", target = "inventaryNumber")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Mapping(source = "hubModel.manufacturer.name", target = "manufacturerName")
    @Mapping(source = "hubModel.manufacturer.id", target = "manufacturerId")
    @Mapping(expression = "java(hub.getAsuo().stream().findFirst().isPresent() ? hub.getAsuo().stream().findFirst().get().getId() : null)", target = "asuoId")
    @Mapping(expression = "java(hub.getAsuo().size() == 0 ? false : true)", target = "hasInstalled")
    @Override
    public abstract HubDto getDto(Hub hub);
    @Mapping(target = "contract", expression = "java(hubRepo.findById(dto.getId()).get().getContract())")
    @Mapping(target = "hubModel", expression = "java(hubModelRepo.findById(dto.getModelId()).get())")
    @Mapping(target = "place.id", source = "placeId")
    @Mapping(target = "place.username", source = "placeName")
    @Mapping(target = "place.placeType", source = "placeType")
    @Mapping(target = "place.departmentCode", source = "departmentCode")
    @Mapping(target = "place.location.id", source = "locationId")
    @Mapping(target = "yearCreated", source = "yearCreated")
    @Mapping(target = "portAmount", source = "portAmount")
    @Mapping(source = "nameFromOneC", target = "nameFromOneC")
    @Mapping(source = "inventaryNumber", target = "inventaryNumber")
    @Mapping(source = "serialNumber", target = "serialNumber")
    @Mapping(expression = "java(null == dto.asuoId ? null : asuoRepo.findById(dto.asuoId).stream().collect(java.util.stream.Collectors.toSet()))", target = "asuo")
    @Override
    public abstract Hub getEntityFromDto(HubDto dto);
    
}
