package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

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
public class TerminalDisplayRestController {

    @Autowired
    private TerminalDisplayManufacturerService terminalDisplayManufacturerService;
    @Autowired
    private TerminalDisplayModelMapper terminalDisplayModelMapper;
    @Autowired
    private TerminalDisplayManufacturerMapper terminalDisplayManufacturerMapper;
    @Autowired
    private TerminalDisplayModelService terminalDisplayModelService;
    @Autowired
    private TerminalDisplayService terminalDisplayService;
    @Autowired
    private TerminalDisplayMapper terminalDisplayMapper;

    @PostMapping("/save-terminal-display-manufacturer")
    public ResponseEntity<?> saveTerminalDisplayManufacturer(String name) throws ObjectAlreadyExists {
        TerminalDisplayManufacturer savedManufacturer = null;
        TerminalDisplayManufacturer potencialManufacturer = new TerminalDisplayManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = terminalDisplayManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalDisplayManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-terminal-display-manufacturers")
    public List<ManufacturerDTO> getTerminalDisplayManufacturers() {
        List<TerminalDisplayManufacturer> allManufacturers = terminalDisplayManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalDisplayManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-terminal-display")
    public TerminalDisplayDto getTerminalDisplayById(Long id) {
        TerminalDisplay byId = terminalDisplayService.getById(id);
        return terminalDisplayMapper.getDto(byId);
    }

    @GetMapping("/get-models-terminal-display-by-manufacturer")
    public List<SvtModelDto> getTerminalDisplayModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        List<TerminalDisplayModel> terminalDisplayModelList = terminalDisplayModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalDisplayModel model : terminalDisplayModelList) {
            SvtModelDto modeDto = terminalDisplayModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-models-terminal-display-all")
    public List<SvtModelDto> getTerminalDisplayModelsAll() {
        List<TerminalDisplayModel> terminalDisplayModelList = terminalDisplayModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalDisplayModel model : terminalDisplayModelList) {
            SvtModelDto modeDto = terminalDisplayModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-terminal-displays")
    public List<TerminalComponentDto> getTerminalDisplays() {
        List<TerminalDisplay> list = terminalDisplayService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for (TerminalDisplay el : list) {
            TerminalComponentDto dto = terminalDisplayMapper.getDto(el);
            dtoes.add(dto);
        }
        return dtoes;
    }

}
