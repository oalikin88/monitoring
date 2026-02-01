package ru.gov.sfr.aos.monitoring.asuo.hub;

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
public class HubModelMapper implements ModelMapper<HubModel, SvtModelDto>  {

    @Override
    public HubModel getModel(SvtModelDto dto) {
        HubManufacturer manufacturer = new HubManufacturer();
        manufacturer.setId(dto.getManufacturerId());
        manufacturer.setName(dto.getManufacturerName());
        HubModel model = new HubModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(manufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(HubModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<HubModel> inputList) {
        List<SvtModelDto> out = new ArrayList<>();
        for(HubModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(HubModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

}
