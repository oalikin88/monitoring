package ru.gov.sfr.aos.monitoring.switchhub;

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
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class SwitchHubRestController {

    @Autowired
    private SwitchHubModelService switchHubModelService;
    @Autowired
    private SwitchHubService switchHubService;
    @Autowired
    private SwitchHubMapper switchHubMapper;
    @Autowired
    private SwitchHubManufacturerService switchHubManufacturerService;
    @Autowired
    private SwitchHubManufacturerMapper switchHubManufacturerMapper;
    @Autowired
    private SwitchHubModelMapper switchHubModelMapper;

    @PostMapping("/save-switch-manufacturer")
    public ResponseEntity<?> saveSwitchHubManufacturer(String name) throws ObjectAlreadyExists {
        SwitchHubManufacturer savedManufacturer = null;
        SwitchHubManufacturer potencialManufacturer = new SwitchHubManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = switchHubManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = switchHubManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-switch-manufacturers")
    public List<ManufacturerDTO> getSwitchHubManufacturers() {
        List<SwitchHubManufacturer> allManufacturers = switchHubManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (SwitchHubManufacturer el : allManufacturers) {
            ManufacturerDTO dto = switchHubManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-switch-modelsby-manufacturer")
    public List<SvtModelDto> getSwitchHubModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        SwitchHubManufacturer manufacturer = switchHubManufacturerService.getManufacturer(id);
        Set<SwitchHubModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (SwitchHubModel model : upsListModel) {
            SvtModelDto modelDto = switchHubModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }

    @GetMapping("/modswitch")
    public List<SvtModelDto> getModelSwitchHub() {
        List<SwitchHubModel> allModels = switchHubModelService.getAllModels();
        List<SvtModelDto> dtoes = new ArrayList<>();
        for (SwitchHubModel model : allModels) {
            SvtModelDto dto = switchHubModelMapper.getDtoForSelectize(model);
            dtoes.add(dto);
        }
        return dtoes;
    }

    @GetMapping("/getswitch")
    public SvtDTO getSwitchById(Long switchId) {

        SwitchHub switchHub = switchHubService.getById(switchId);
        SvtSwitchHubDTO switchHubDto = switchHubMapper.getDto(switchHub);

        return switchHubDto;
    }

    @GetMapping("/getswitches")
    public List<SvtDTO> getSwitches() {
        List<SwitchHub> allSwitches = switchHubService.getAllSwitch();
        List<SvtDTO> dtoes = new ArrayList<>();
        for (SwitchHub switchHub : allSwitches) {
            SvtDTO dto = switchHubMapper.getDto(switchHub);
            dtoes.add(dto);
        }
        return dtoes;
    }
}
