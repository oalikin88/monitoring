package ru.gov.sfr.aos.monitoring.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gov.sfr.aos.monitoring.asuo.ProgramSoftware;
import ru.gov.sfr.aos.monitoring.models.ProgramSoftwareDto;
import ru.gov.sfr.aos.monitoring.asuo.AsuoRepo;

/**
 *
 * @author Alikin Oleg
 */
@Mapper(componentModel = "spring")
public abstract class ProgramSoftwareMapper {
    
    @Autowired
    protected AsuoRepo asuoRepo;
    
    @Mapping(expression = "java(entity.getAsuos().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toSet()))", target = "asuos")
    public abstract ProgramSoftwareDto toDto(ProgramSoftware entity);
    @Mapping(target = "asuos", expression = "java(asuoRepo.findAllById(dto.getAsuos()).stream().collect(java.util.stream.Collectors.toSet()))")
    public abstract ProgramSoftware toEntity(ProgramSoftwareDto dto);
}
