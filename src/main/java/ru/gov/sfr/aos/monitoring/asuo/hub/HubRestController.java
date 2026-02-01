package ru.gov.sfr.aos.monitoring.asuo.hub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class HubRestController {

    @Autowired
    private HubService hubService;
    @Autowired
    private HubMapper hubMapper;
    @Autowired
    private HubManufacturerService hubManufacturerService;
    @Autowired
    private HubManufacturerMapper hubManufacturerMapper;
    @Autowired
    private HubModelService hubModelService;
    @Autowired
    private HubModelMapper hubModelMapper;

    @PostMapping("/save-hub-manufacturer")
    public ResponseEntity<?> saveHubManufacturer(String name) throws ObjectAlreadyExists {
        HubManufacturer savedManufacturer = null;
        HubManufacturer potencialManufacturer = new HubManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = hubManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = hubManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-hub-manufacturers")
    public List<ManufacturerDTO> getHubManufacturers() {
        List<HubManufacturer> allManufacturers = hubManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (HubManufacturer el : allManufacturers) {
            ManufacturerDTO dto = hubManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-hub-modelsby-manufacturer")
    public List<SvtModelDto> getHubModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        List<HubModel> modelList = hubModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for (HubModel model : modelList) {
            SvtModelDto modeDto = hubModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-models-hub-all")
    public List<SvtModelDto> getHubModelsAll() {
        List<HubModel> modelList = hubModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (HubModel model : modelList) {
            SvtModelDto modeDto = hubModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-hubs")
    public List<HubDto> getHubs() {
        List<Hub> list = hubService.getAll();
        List<HubDto> dtoes = new ArrayList<>();
        for (Hub el : list) {
            HubDto dto = hubMapper.getDto(el);
            dtoes.add(dto);
        }
        return dtoes;
    }

    @GetMapping("/gethub")
    public HubDto getHubById(Long hubId) {
        Hub hub = hubService.getById(hubId);
        HubDto dto = hubMapper.getDto(hub);
        return dto;
    }

}
