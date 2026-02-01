package ru.gov.sfr.aos.monitoring.ats;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class AtsRestController {

    @Autowired
    private AtsModelService atsModelService;
    @Autowired
    private AtsService atsService;
    @Autowired
    private AtsMapper atsMapper;
    @Autowired
    private AtsManufacturerService atsManufacturerService;
    @Autowired
    private AtsManufacturerMapper atsManufacturerMapper;
    @Autowired
    private AtsModelMapper atsModelMapper;

    @PostMapping("/save-ats-manufacturer")
    public ResponseEntity<?> saveAtsManufacturer(String name) throws ObjectAlreadyExists {
        AtsManufacturer savedManufacturer = null;
        AtsManufacturer potencialManufacturer = new AtsManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = atsManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = atsManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-ats-manufacturers")
    public List<ManufacturerDTO> getAtsManufacturers() {
        List<AtsManufacturer> allManufacturers = atsManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (AtsManufacturer el : allManufacturers) {
            ManufacturerDTO dto = atsManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-ats-modelsby-manufacturer")
    public Set<SvtModelDto> getAtsModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        AtsManufacturer manufacturer = atsManufacturerService.getManufacturer(id);
        Set<AtsModel> upsListModel = manufacturer.getModels();
        Set<SvtModelDto> out = new HashSet<>();
        for (AtsModel model : upsListModel) {
            SvtModelDto modelDto = atsModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }

    @GetMapping("/modats")
    public Set<SvtModelDto> getModelAts() {
        List<AtsModel> allModels = atsModelService.getAllModels();
        Set<SvtModelDto> dtoes = new HashSet<>();
        for (AtsModel model : allModels) {
            SvtModelDto dto = atsModelMapper.getDto(model);
            dtoes.add(dto);
        }

        return dtoes;
    }

    @GetMapping("/getats")
    public SvtDTO getAtsById(Long atsId) {
        Ats ats = atsService.getById(atsId);
        SvtAtsDTO atsDto = atsMapper.getDto(ats);

        return atsDto;
    }
}
