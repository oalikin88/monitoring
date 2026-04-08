package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

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
public class TerminalSensorModelMapper implements ModelMapper<TerminalSensorModel, SvtModelDto> {
    @Override
    public TerminalSensorModel getModel(SvtModelDto dto) {
        TerminalSensorManufacturer terminalSensorManufacturer = new TerminalSensorManufacturer();
        terminalSensorManufacturer.setId(dto.getManufacturerId());
        terminalSensorManufacturer.setName(dto.getManufacturerName());
        TerminalSensorModel model = new TerminalSensorModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(terminalSensorManufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(TerminalSensorModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<TerminalSensorModel> inputList) {
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalSensorModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(TerminalSensorModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
}
