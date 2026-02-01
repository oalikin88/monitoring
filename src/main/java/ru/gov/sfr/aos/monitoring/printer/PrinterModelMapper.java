package ru.gov.sfr.aos.monitoring.printer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.gov.sfr.aos.monitoring.models.ModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class PrinterModelMapper implements ModelMapper<PrinterModel, PrinterModelDto> {

    @Override
    public PrinterModel getModel(PrinterModelDto dto) {
        PrinterManufacturer manufacturer = new PrinterManufacturer();
        manufacturer.setId(dto.getManufacturerId());
        manufacturer.setName(dto.getManufacturerName());
        PrinterModel model = new PrinterModel();
        if (dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setPrintColorType(PrinterModelRegularOperations.getPrintType(dto.getPrintColorType()));
        model.setPrintFormat(PrinterModelRegularOperations.getPrintFormat(dto.getPrintFormat()));
        model.setPrintSpeed(dto.getPrintSpeed());

        model.setModel(dto.getModel().strip());
        model.setManufacturer(manufacturer);
        return model;
    }

    @Override
    public PrinterModelDto getDto(PrinterModel entity) {
        PrinterModelDto dto = new PrinterModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setPrintColorType(PrinterModelRegularOperations.printTypeToString(entity.getPrintColorType()));
        dto.setPrintColorTypeRus(PrinterModelRegularOperations.printTypeToStringRus(entity.getPrintColorType()));
        dto.setPrintFormat(PrinterModelRegularOperations.printFormatToString(entity.getPrintFormat()));
        dto.setPrintSpeed(entity.getPrintSpeed());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<PrinterModelDto> getListDtoes(List<PrinterModel> inputList) {
        List<PrinterModelDto> out = new ArrayList<>();
        for (PrinterModel el : inputList) {
            PrinterModelDto dto = getDto(el);
            out.add(dto);
        }
        return out;
    }

    @Override
    public PrinterModelDto getDtoForSelectize(PrinterModel entity) {
        PrinterModelDto dto = new PrinterModelDto();
        dto.setModel(entity.getModel());
        dto.setManufacturerName(entity.getManufacturer().getName());
        dto.setManufacturerId(entity.getManufacturer().getId());
        dto.setId(entity.getId());
        return dto;
    }

}
