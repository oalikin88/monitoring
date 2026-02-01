package ru.gov.sfr.aos.monitoring.asuo.terminal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.asuo.ProgramSoftware;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.models.ProgramSoftwareDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class TerminalRestController {

    @Autowired
    private TerminalModelService terminalModelService;
    @Autowired
    private TerminalMapper terminalMapper;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private TerminalManufacturerService terminalManufacturerService;
    @Autowired
    private TerminalManufacturerMapper terminalManufacturerMapper;
    @Autowired
    private TerminalModelMapper terminalModelMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;
    @Autowired
    private TerminalProgramSoftwareService terminalProgramSoftwareService;

    @PostMapping("/save-terminal-manufacturer")
    public ResponseEntity<?> saveTerminalManufacturer(String name) throws ObjectAlreadyExists {
        TerminalManufacturer savedManufacturer = null;
        TerminalManufacturer potencialManufacturer = new TerminalManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = terminalManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-terminal-manufacturers")
    public List<ManufacturerDTO> getTerminalManufacturers() {
        List<TerminalManufacturer> allManufacturers = terminalManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-models-terminal-by-manufacturer")
    public List<SvtModelDto> getTerminalModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        List<TerminalModel> terminalModelList = terminalModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalModel model : terminalModelList) {
            SvtModelDto modeDto = terminalModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/modterminal")
    public List<SvtModelDto> getModelTermial() {
        List<TerminalModel> allModels = terminalModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelTerminalDtoes(allModels);
        return dtoes;
    }

    @GetMapping("/getterminal")
    public SvtDTO getTerminalById(Long terminalId) {
        Terminal terminal = terminalService.getById(terminalId);
        SvtDTO dto = terminalMapper.getDto(terminal);
        return dto;
    }

    @GetMapping("/getallterminal")
    public List<SvtDTO> getAllTerminal() {
        List<Terminal> allTerminal = terminalService.getAllTerminal();
        List<SvtDTO> dtoes = new ArrayList<>();
        for (Terminal el : allTerminal) {
            SvtDTO dto = terminalMapper.getDto(el);
            dtoes.add(dto);
        }
        return dtoes;
    }

    @GetMapping("/getterminals")
    public List<TerminalDto> getTerminals() {
        List<Terminal> allTerminal = terminalService.getAllTerminal();
        List<TerminalDto> dtoes = new ArrayList<>();
        for (Terminal terminal : allTerminal) {
            TerminalDto dto = terminalMapper.getDto(terminal);
            dtoes.add(dto);
        }
        return dtoes;
    }

    @GetMapping("/get-all-po")
    public List<ProgramSoftwareDto> getPoAll() {
        List<ProgramSoftware> programSoftwares = terminalProgramSoftwareService.getProgramSoftwares();
        List<ProgramSoftwareDto> out = new ArrayList<>();
        return terminalProgramSoftwareService.getProgramSoftwareDtoesList(programSoftwares);
    }
}
