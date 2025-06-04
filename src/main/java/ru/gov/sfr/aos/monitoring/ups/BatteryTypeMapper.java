/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ups;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.gov.sfr.aos.monitoring.ups.BatteryType;
import ru.gov.sfr.aos.monitoring.ups.BatteryTypeDto;

/**
 *
 * @author 041AlikinOS
 */
@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class BatteryTypeMapper {
     public abstract BatteryType getBatteryType (BatteryTypeDto dto);
     public abstract BatteryTypeDto getBatteryTypeDto(BatteryType batteryType);
     public abstract List<BatteryType> getBatteryTypeList(List<BatteryTypeDto> dtoes);
     public abstract List<BatteryTypeDto> getBatteryTypeDtoesList(List<BatteryType> batteryTypes);
    
}
