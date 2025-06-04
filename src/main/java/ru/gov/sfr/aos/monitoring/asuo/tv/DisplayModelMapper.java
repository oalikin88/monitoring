package ru.gov.sfr.aos.monitoring.asuo.tv;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.models.ModelMapper;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class DisplayModelMapper implements ModelMapper<DisplayModel, SvtModelDto> {

    @Override
     public DisplayModel getModel(SvtModelDto dto) {
        DisplayManufacturer manufacturer = new DisplayManufacturer();
        manufacturer.setId(dto.getManufacturerId());
        manufacturer.setName(dto.getManufacturerName());
        DisplayModel model = new DisplayModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(manufacturer);
        return model;
        
    }
    
     @Override
    public SvtModelDto getDto(DisplayModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
    
     @Override
    public List<SvtModelDto> getListDtoes(List<DisplayModel> inputList) {
        List<SvtModelDto> out = new ArrayList<>();
        for(DisplayModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }
    
    
     @Override
    public SvtModelDto getDtoForSelectize(DisplayModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
}
