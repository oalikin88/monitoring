/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ups;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.ups.UpsManufacturer;
import ru.gov.sfr.aos.monitoring.ups.UpsModel;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.ups.UpsManufacturerRepo;
import ru.gov.sfr.aos.monitoring.ups.UpsModelRepo;


/**
 *
 * @author Alikin Oleg
 */
@Component
public class UpsModelMapper {
    @Autowired
    private UpsManufacturerRepo upsManufacturerRepo;
    @Autowired
    private BatteryTypeRepo batteryTypeRepo;
    @Autowired
    private UpsModelRepo upsModelRepo;


    public UpsModelDto getDto(UpsModel upsModel) {
        UpsModelDto dto = new UpsModelDto();
        dto.setId(upsModel.getId());
        dto.setBatteryAmount(upsModel.getBatteryAmount());
        dto.setBatteryType(upsModel.getBatteryType().getType());
        dto.setBatteryTypeId(upsModel.getBatteryType().getId());
        dto.setManufacturer(upsModel.getManufacturer().getName());
        dto.setManufacturerId(upsModel.getManufacturer().getId());
        dto.setModel(upsModel.getModel());
        
    return dto;
    }
    

    public UpsModel getEntityFromDto(UpsModelDto dto) {
        UpsModel upsModel = new UpsModel();
        BatteryType batteryType = null;
        UpsManufacturer manufacturer = null;
        upsModel.setId(dto.getId());
        upsModel.setBatteryAmount(dto.getBatteryAmount());
        if(batteryTypeRepo.existsByTypeIgnoreCase(dto.getBatteryType().strip())){
            batteryType = batteryTypeRepo.findByTypeIgnoreCase(dto.getBatteryType().strip()).get(0);
        } else {
            batteryType = new BatteryType();
            batteryType.setType(dto.getBatteryType().strip());
        }
        upsModel.setBatteryType(batteryType);
        if(upsManufacturerRepo.existsByNameIgnoreCase(dto.getManufacturer().strip())) {
           manufacturer = upsManufacturerRepo.findByNameIgnoreCase(dto.getManufacturer()).get(0);
        } else {
            manufacturer = new UpsManufacturer();
            manufacturer.setName(dto.getManufacturer().strip());
        }
        upsModel.setModel(dto.getModel().strip());
        upsModel.setManufacturer(manufacturer);
        
        
        return upsModel;
    }
    
    
    public List<SvtModelDto> getUpsModelDtoForSelectize(List<UpsModel> input) {
        List<SvtModelDto> out = new ArrayList<>();
        for(UpsModel el : input) {
           SvtModelDto dto = new SvtModelDto();
           dto.setId(el.getId());
           dto.setModel(el.getManufacturer().getName() + " " + el.getModel());
           out.add(dto);
        }
    
    return out;
}
}