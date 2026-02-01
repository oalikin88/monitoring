package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

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
public class TerminalServerModelMapper implements ModelMapper<TerminalServerModel, SvtModelDto> {
    @Override
    public TerminalServerModel getModel(SvtModelDto dto) {
        TerminalServerManufacturer terminalServerManufacturer = new TerminalServerManufacturer();
        terminalServerManufacturer.setId(dto.getManufacturerId());
        terminalServerManufacturer.setName(dto.getManufacturerName());
        TerminalServerModel model = new TerminalServerModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(terminalServerManufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(TerminalServerModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<TerminalServerModel> inputList) {
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalServerModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(TerminalServerModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
}
