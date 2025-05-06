package ru.gov.sfr.aos.monitoring.mappers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.TerminalUpsManufacturer;
import ru.gov.sfr.aos.monitoring.entities.TerminalUpsModel;
import ru.gov.sfr.aos.monitoring.interfaces.ModelMapper;
import ru.gov.sfr.aos.monitoring.models.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class TerminalUpsModelMapper implements ModelMapper<TerminalUpsModel, SvtModelDto> {
    @Override
    public TerminalUpsModel getModel(SvtModelDto dto) {
        TerminalUpsManufacturer terminalUpsManufacturer = new TerminalUpsManufacturer();
        terminalUpsManufacturer.setId(dto.getManufacturerId());
        terminalUpsManufacturer.setName(dto.getManufacturerName());
        TerminalUpsModel model = new TerminalUpsModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(terminalUpsManufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(TerminalUpsModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<TerminalUpsModel> inputList) {
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalUpsModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(TerminalUpsModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getManufacturer().getName() + " " + entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
}
