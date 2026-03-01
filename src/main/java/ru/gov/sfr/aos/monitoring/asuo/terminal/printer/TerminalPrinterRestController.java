package ru.gov.sfr.aos.monitoring.asuo.terminal.printer;

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
public class TerminalPrinterRestController {

    @Autowired
    private TerminalPrinterManufacturerService terminalPrinterManufacturerService;
    @Autowired
    private TerminalPrinterModelMapper terminalPrinterModelMapper;
    @Autowired
    private TerminalPrinterManufacturerMapper terminalPrinterManufacturerMapper;
    @Autowired
    private TerminalPrinterModelService terminalPrinterModelService;
    @Autowired
    private TerminalPrinterService terminalPrinterService;
    @Autowired
    private TerminalPrinterMapper terminalPrinterMapper;

    @PostMapping("/save-terminal-printer-manufacturer")
    public ResponseEntity<?> saveTerminalPrinterManufacturer(String name) throws ObjectAlreadyExists {
        TerminalPrinterManufacturer savedManufacturer = null;
        TerminalPrinterManufacturer potencialManufacturer = new TerminalPrinterManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = terminalPrinterManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalPrinterManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-terminal-printer-manufacturers")
    public List<ManufacturerDTO> getTerminalPrinterManufacturers() {
        List<TerminalPrinterManufacturer> allManufacturers = terminalPrinterManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalPrinterManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-terminal-printer")
    public TerminalComponentDto getTerminalPrinterById(Long id) {
        TerminalPrinter byId = terminalPrinterService.getById(id);
        return terminalPrinterMapper.getDto(byId);
    }

    @GetMapping("/get-models-terminal-printer-all")
    public List<SvtModelDto> getTerminalPrinterModelsAll() {
        List<TerminalPrinterModel> modelList = terminalPrinterModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalPrinterModel model : modelList) {
            SvtModelDto modeDto = terminalPrinterModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-models-terminal-printer-by-manufacturer")
    public List<SvtModelDto> getTerminalPrinterModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        List<TerminalPrinterModel> terminalPrinterModelList = terminalPrinterModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalPrinterModel model : terminalPrinterModelList) {
            SvtModelDto modeDto = terminalPrinterModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-terminal-printers")
    public List<TerminalComponentDto> getTerminalPrinters() {
        List<TerminalPrinter> list = terminalPrinterService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for (TerminalPrinter el : list) {
            TerminalComponentDto dto = terminalPrinterMapper.getDto(el);
            dtoes.add(dto);
        }
        return dtoes;
    }
}
