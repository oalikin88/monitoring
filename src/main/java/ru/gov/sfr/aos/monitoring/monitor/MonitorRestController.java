package ru.gov.sfr.aos.monitoring.monitor;

import java.util.ArrayList;
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
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class MonitorRestController {

    @Autowired
    private MonitorModelService monitorModelService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private MonitorMapper monitorMapper;
    @Autowired
    private MonitorModelMapper monitorModelMapper;
    @Autowired
    private MonitorManufacturerService monitorManufacturerService;
    @Autowired
    private MonitorManufacturerMapper monitorManufacturerMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;

    @PostMapping("/save-monitor-manufacturer")
    public ResponseEntity<?> saveMonitorManufacturer(String name) throws ObjectAlreadyExists {
        MonitorManufacturer savedManufacturer = null;
        MonitorManufacturer potencialManufacturer = new MonitorManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = monitorManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = monitorManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-monitor-manufacturers")
    public List<ManufacturerDTO> getMonitorManufacturers() {
        List<MonitorManufacturer> allManufacturers = monitorManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (MonitorManufacturer el : allManufacturers) {
            ManufacturerDTO dto = monitorManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-monitor-modelsby-manufacturer")
    public List<SvtModelDto> getMonitorModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        MonitorManufacturer manufacturer = monitorManufacturerService.getManufacturer(id);
        Set<MonitorModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (MonitorModel model : upsListModel) {
            SvtModelDto modelDto = svtModelMapper.getMonitorModelDto(model);
            out.add(modelDto);
        }
        return out;
    }

    @GetMapping("/modmonitors")
    public List<SvtModelDto> getModelMonitors() {
        List<MonitorModel> allModels = monitorModelService.getAllActualModels();
        List<SvtModelDto> monitorModelsDtoes = new ArrayList<>();
        for (MonitorModel model : allModels) {
            SvtModelDto dtoForSelectize = monitorModelMapper.getDtoForSelectize(model);
            monitorModelsDtoes.add(dtoForSelectize);
        }

        return monitorModelsDtoes;
    }

    @GetMapping("/getmonitor")
    public SvtDTO getMonitorById(Long monitorId) {

        Monitor monitor = monitorService.getById(monitorId);
        SvtDTO monitorDto = monitorMapper.getDto(monitor);
        return monitorDto;
    }
}
