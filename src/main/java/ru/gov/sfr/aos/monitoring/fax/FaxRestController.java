package ru.gov.sfr.aos.monitoring.fax;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class FaxRestController {

    @Autowired
    private FaxModelService faxModelService;
    @Autowired
    private FaxMapper faxMapper;
    @Autowired
    private FaxService faxService;
    @Autowired
    private FaxManufacturerService faxManufacturerService;
    @Autowired
    private FaxManufacturerMapper faxManufacturerMapper;
    @Autowired
    private FaxModelMapper faxModelMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;

    @GetMapping("/get-fax-manufacturers")
    public List<ManufacturerDTO> getFaxManufacturers() {
        List<FaxManufacturer> allManufacturers = faxManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (FaxManufacturer el : allManufacturers) {
            ManufacturerDTO dto = faxManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-fax-modelsby-manufacturer")
    public List<SvtModelDto> getFaxModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        FaxManufacturer manufacturer = faxManufacturerService.getManufacturer(id);
        Set<FaxModel> listModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (FaxModel model : listModel) {
            SvtModelDto modeDto = svtModelMapper.getModelFaxDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/modfax")
    public List<SvtModelDto> getModelFax() {
        List<FaxModel> allModels = faxModelService.getAllModels();
        List<SvtModelDto> dtoes = new ArrayList<>();
        for (FaxModel model : allModels) {
            SvtModelDto dtoForSelectize = faxModelMapper.getDtoForSelectize(model);
            dtoes.add(dtoForSelectize);
        }
        return dtoes;
    }

    @GetMapping("/getfax")
    public FaxDto getFaxById(Long faxId) {
        Fax fax = faxService.getById(faxId);
        FaxDto faxDto = faxMapper.getDto(fax);

        return faxDto;
    }
}
