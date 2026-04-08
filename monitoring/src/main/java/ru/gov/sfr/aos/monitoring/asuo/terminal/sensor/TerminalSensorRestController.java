package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

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
public class TerminalSensorRestController {

    @Autowired
    private TerminalSensorManufacturerService terminalSensorManufacturerService;
    @Autowired
    private TerminalSensorModelMapper terminalSensorModelMapper;
    @Autowired
    private TerminalSensorManufacturerMapper terminalSensorManufacturerMapper;
    @Autowired
    private TerminalSensorModelService terminalSensorModelService;
    @Autowired
    private TerminalSensorService terminalSensorService;
    @Autowired
    private TerminalSensorMapper terminalSensorMapper;

    @PostMapping("/save-terminal-sensor-manufacturer")
    public ResponseEntity<?> saveTerminalSensorManufacturer(String name) throws ObjectAlreadyExists {
        TerminalSensorManufacturer savedManufacturer = null;
        TerminalSensorManufacturer potencialManufacturer = new TerminalSensorManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = terminalSensorManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = terminalSensorManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-terminal-sensor-manufacturers")
    public List<ManufacturerDTO> getTerminalSensorManufacturers() {
        List<TerminalSensorManufacturer> allManufacturers = terminalSensorManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = terminalSensorManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-terminal-sensor")
    public TerminalComponentDto getTerminalSensorById(Long id) {
        TerminalSensor byId = terminalSensorService.getById(id);
        return terminalSensorMapper.getDto(byId);
    }

    @GetMapping("/get-models-terminal-sensor-all")
    public List<SvtModelDto> getTerminalSensorModelsAll() {
        List<TerminalSensorModel> terminalSensorModelList = terminalSensorModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalSensorModel model : terminalSensorModelList) {
            SvtModelDto modeDto = terminalSensorModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-models-terminal-sensor-by-manufacturer")
    public List<SvtModelDto> getTerminalSensorModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        List<TerminalSensorModel> modelsByManufacturerId = terminalSensorModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for (TerminalSensorModel model : modelsByManufacturerId) {
            SvtModelDto modeDto = terminalSensorModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/get-terminal-sensors")
    public List<TerminalComponentDto> getTerminalSensors() {
        List<TerminalSensor> list = terminalSensorService.getAll();
        List<TerminalComponentDto> dtoes = new ArrayList<>();
        for (TerminalSensor el : list) {
            TerminalComponentDto dto = terminalSensorMapper.getDto(el);
            dtoes.add(dto);
        }
        return dtoes;
    }
}
