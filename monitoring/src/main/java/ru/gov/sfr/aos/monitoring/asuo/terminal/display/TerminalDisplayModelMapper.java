package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

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
public class TerminalDisplayModelMapper implements ModelMapper<TerminalDisplayModel, SvtModelDto>  {
      @Override
    public TerminalDisplayModel getModel(SvtModelDto dto) {
        TerminalDisplayManufacturer terminalDisplaymanufacturer = new TerminalDisplayManufacturer();
        terminalDisplaymanufacturer.setId(dto.getManufacturerId());
        terminalDisplaymanufacturer.setName(dto.getManufacturerName());
        TerminalDisplayModel model = new TerminalDisplayModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(terminalDisplaymanufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(TerminalDisplayModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<TerminalDisplayModel> inputList) {
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalDisplayModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(TerminalDisplayModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
}
