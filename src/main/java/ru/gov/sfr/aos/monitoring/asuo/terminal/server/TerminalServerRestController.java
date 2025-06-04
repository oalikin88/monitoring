package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

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
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class TerminalServerRestController {

    @Autowired
    private TerminalServerManufacturerService terminalServerManufacturerService;
    @Autowired
    private TerminalServerModelMapper terminalServerModelMapper;
    @Autowired
    private TerminalServerManufacturerMapper terminalServerManufacturerMapper;
    @Autowired
    private TerminalServerModelService terminalServerModelService;
    @Autowired
    private TerminalServerService terminalServerService;
    @Autowired
    private TerminalServerMapper terminalServerMapper;

    @PostMapping("/save-terminal-server-manufacturer")
    public ResponseEntity<?> saveTerminalServerManufacturer(String name) throws ObjectAlreadyExists {
        TerminalServerManufacturer savedManufacturer = null;
        TerminalServerManufacturer potencialManufacturer = new TerminalServerManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = terminalServerManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalServerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-terminal-server-manufacturers")
    public List<ManufacturerDTO> getTerminalServerManufacturers() {
        List<TerminalServerManufacturer> allManufacturers = terminalServerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalServerManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-terminal-server")
    public TerminalComponentDto getTerminalServerById(Long id) {
        TerminalServer byId = terminalServerService.getById(id);
        return terminalServerMapper.getDto(byId);
    }

    @GetMapping("/get-models-terminal-server-all")
    public List<SvtModelDto> getTerminalServerModelsAll() {
        List<TerminalServerModel> modelList = terminalServerModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalServerModel model : modelList) {
            SvtModelDto modeDto = terminalServerModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-models-terminal-server-by-manufacturer")
    public List<SvtModelDto> getTerminalServerModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        List<TerminalServerModel> terminalServerModelList = terminalServerModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalServerModel model : terminalServerModelList) {
            SvtModelDto modeDto = terminalServerModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-terminal-servers")
    public List<TerminalComponentDto> getTerminalServers() {
        List<TerminalServer> list = terminalServerService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for (TerminalServer el : list) {
            TerminalComponentDto dto = terminalServerMapper.getDto(el);
            dtoes.add(dto);
        }
        return dtoes;
    }
}
