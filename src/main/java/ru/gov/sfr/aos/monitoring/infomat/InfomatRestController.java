package ru.gov.sfr.aos.monitoring.infomat;

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
public class InfomatRestController {

    @Autowired
    private InfomatModelService infomatModelService;
    @Autowired
    private InfomatMapper infomatMapper;
    @Autowired
    private InfomatService infomatService;
    @Autowired
    private InfomatManufacturerMapper infomatManufacturerMapper;
    @Autowired
    private InfomatManufacturerService infomatManufacturerService;
    @Autowired
    private InfomatModelMapper infomatModelMapper;

    @PostMapping("/save-infomat-manufacturer")
    public ResponseEntity<?> saveInfomatManufacturer(String name) throws ObjectAlreadyExists {
        InfomatManufacturer savedManufacturer = null;
        InfomatManufacturer potencialManufacturer = new InfomatManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = infomatManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = infomatManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-infomat-manufacturers")
    public List<ManufacturerDTO> getInfomatManufacturers() {
        List<InfomatManufacturer> allManufacturers = infomatManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (InfomatManufacturer el : allManufacturers) {
            ManufacturerDTO dto = infomatManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-infomat-modelsby-manufacturer")
    public Set<SvtModelDto> getInfomatModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        InfomatManufacturer manufacturer = infomatManufacturerService.getManufacturer(id);
        Set<InfomatModel> upsListModel = manufacturer.getModels();
        Set<SvtModelDto> out = new HashSet<>();
        for (InfomatModel model : upsListModel) {
            SvtModelDto modelDto = infomatModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }

    @GetMapping("/modinfomat")
    public Set<SvtModelDto> getModelInfomat() {
        List<InfomatModel> allModels = infomatModelService.getAllModels();
        Set<SvtModelDto> dtoes = new HashSet<>();
        for (InfomatModel model : allModels) {
            SvtModelDto dtoForSelectize = infomatModelMapper.getDtoForSelectize(model);
            dtoes.add(dtoForSelectize);
        }
        return dtoes;
    }

    @GetMapping("/getinfomat")
    public SvtDTO getInfomatById(Long infomatId) {
        Infomat infomat = infomatService.getById(infomatId);
        SvtDTO dto = infomatMapper.getDto(infomat);
        return dto;
    }
}
