package ru.gov.sfr.aos.monitoring.asuo.terminal.printer;

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
public class TerminalPrinterModelMapper implements ModelMapper<TerminalPrinterModel, SvtModelDto>  {
    @Override
    public TerminalPrinterModel getModel(SvtModelDto dto) {
        TerminalPrinterManufacturer terminalPrinterManufacturer = new TerminalPrinterManufacturer();
        terminalPrinterManufacturer.setId(dto.getManufacturerId());
        terminalPrinterManufacturer.setName(dto.getManufacturerName());
        TerminalPrinterModel model = new TerminalPrinterModel();
        if(dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setModel(dto.getModel().strip());
        model.setManufacturer(terminalPrinterManufacturer);
        return model;
    }

    @Override
    public SvtModelDto getDto(TerminalPrinterModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<SvtModelDto> getListDtoes(List<TerminalPrinterModel> inputList) {
        List<SvtModelDto> out = new ArrayList<>();
        for(TerminalPrinterModel el : inputList) {
            SvtModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }

    @Override
    public SvtModelDto getDtoForSelectize(TerminalPrinterModel entity) {
        SvtModelDto dto = new SvtModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }
}
