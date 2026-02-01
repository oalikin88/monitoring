/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OperationSystemMapper {
    public abstract OperationSystem getOperationSystem(OperationSystemDto dto);
    public abstract OperationSystemDto getOperationSystemDto(OperationSystem operationSystem);
    public abstract List<OperationSystem> getOperationSystemList(List<OperationSystemDto> dtoes);
    public abstract List<OperationSystemDto> getOperationSystemDtoesList(List<OperationSystem> operationSystemList);
}
