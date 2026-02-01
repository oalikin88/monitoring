package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class TerminalUpsRestController {

    @Autowired
    private TerminalUpsManufacturerService terminalUpsManufacturerService;
    @Autowired
    private TerminalUpsModelMapper terminalUpsModelMapper;
    @Autowired
    private TerminalUpsManufacturerMapper terminalUpsManufacturerMapper;
    @Autowired
    private TerminalUpsModelService terminalUpsModelService;
    @Autowired
    private TerminalUpsService terminalUpsService;
    @Autowired
    private TerminalUpsMapper terminalUpsMapper;

    @PostMapping("/save-terminal-ups-manufacturer")
    public ResponseEntity<?> saveTerminalUpsManufacturer(String name) throws ObjectAlreadyExists {
        TerminalUpsManufacturer savedManufacturer = null;
        TerminalUpsManufacturer potencialManufacturer = new TerminalUpsManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = terminalUpsManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalUpsManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-terminal-ups-manufacturers")
    public List<ManufacturerDTO> getTerminalUpsManufacturers() {
        List<TerminalUpsManufacturer> allManufacturers = terminalUpsManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalUpsManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-terminal-ups")
    public TerminalComponentDto getTerminalUpsById(Long id) {
        TerminalUps byId = terminalUpsService.getById(id);
        return terminalUpsMapper.getDto(byId);
    }

    @GetMapping("/get-models-terminal-ups-all")
    public List<SvtModelDto> getTerminalUpsModelsAll() {
        List<TerminalUpsModel> modelList = terminalUpsModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalUpsModel model : modelList) {
            SvtModelDto modeDto = terminalUpsModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-models-terminal-ups-by-manufacturer")
    public List<SvtModelDto> getTerminalUpsModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        List<TerminalUpsModel> terminalUpsModelList = terminalUpsModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalUpsModel model : terminalUpsModelList) {
            SvtModelDto modeDto = terminalUpsModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-terminal-upses")
    public List<TerminalComponentDto> getTerminalUpses() {
        List<TerminalUps> list = terminalUpsService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for (TerminalUps el : list) {
            TerminalComponentDto dto = terminalUpsMapper.getDto(el);
            dtoes.add(dto);
        }
        return dtoes;
    }

}
