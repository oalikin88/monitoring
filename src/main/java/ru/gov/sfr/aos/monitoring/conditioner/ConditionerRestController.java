package ru.gov.sfr.aos.monitoring.conditioner;

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
public class ConditionerRestController {

    @Autowired
    private ConditionerModelService conditionerModelService;
    @Autowired
    private ConditionerService conditionerService;
    @Autowired
    private ConditionerMapper conditionerMapper;
    @Autowired
    private ConditionerManufacturerService conditionerManufacturerService;
    @Autowired
    private ConditionerManufacturerMapper conditionerManufacturerMapper;
    @Autowired
    private ConditionerModelMapper conditionerModelMapper;

    @PostMapping("/save-conditioner-manufacturer")
    public ResponseEntity<?> saveConditionerManufacturer(String name) throws ObjectAlreadyExists {
        ConditionerManufacturer savedManufacturer = null;
        ConditionerManufacturer potencialManufacturer = new ConditionerManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = conditionerManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = conditionerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-conditioner-manufacturers")
    public List<ManufacturerDTO> getConditionerManufacturers() {
        List<ConditionerManufacturer> allManufacturers = conditionerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (ConditionerManufacturer el : allManufacturers) {
            ManufacturerDTO dto = conditionerManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-conditioner-modelsby-manufacturer")
    public Set<SvtModelDto> getConditionerModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        ConditionerManufacturer manufacturer = conditionerManufacturerService.getManufacturer(id);
        Set<ConditionerModel> upsListModel = manufacturer.getModels();
        Set<SvtModelDto> out = new HashSet<>();
        for (ConditionerModel model : upsListModel) {
            SvtModelDto modelDto = conditionerModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }

    @GetMapping("/modconditioner")
    public Set<SvtModelDto> getModelConditioner() {
        List<ConditionerModel> allModels = conditionerModelService.getAllModels();
        Set<SvtModelDto> dtoes = new HashSet<>();
        for (ConditionerModel model : allModels) {
            SvtModelDto dtoForSelectize = conditionerModelMapper.getDtoForSelectize(model);
            dtoes.add(dtoForSelectize);
        }
        return dtoes;
    }

    @GetMapping("/getconditioner")
    public SvtDTO getConditionerById(Long conditionerId) {
        Conditioner conditioner = conditionerService.getById(conditionerId);
        SvtConditionerDTO conditionerDto = conditionerMapper.getDto(conditioner);
        return conditionerDto;
    }

}
