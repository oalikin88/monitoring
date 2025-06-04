package ru.gov.sfr.aos.monitoring.router;

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
import ru.gov.sfr.aos.monitoring.switchhub.SvtSwitchHubDTO;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class RouterRestController {

    @Autowired
    private RouterModelService routerModelService;
    @Autowired
    private RouterService routerService;
    @Autowired
    private RouterMapper routerMapper;
    @Autowired
    private RouterManufacturerService routerManufacturerService;
    @Autowired
    private RouterManufacturerMapper routerManufacturerMapper;
    @Autowired
    private RouterModelMapper routerModelMapper;

    @PostMapping("/save-router-manufacturer")
    public ResponseEntity<?> saveRouterHubManufacturer(String name) throws ObjectAlreadyExists {
        RouterManufacturer savedManufacturer = null;
        RouterManufacturer potencialManufacturer = new RouterManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = routerManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = routerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-router-manufacturers")
    public List<ManufacturerDTO> getRouterManufacturers() {
        List<RouterManufacturer> allManufacturers = routerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (RouterManufacturer el : allManufacturers) {
            ManufacturerDTO dto = routerManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-router-modelsby-manufacturer")
    public Set<SvtModelDto> getRouterModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        RouterManufacturer manufacturer = routerManufacturerService.getManufacturer(id);
        Set<RouterModel> upsListModel = manufacturer.getModels();
        Set<SvtModelDto> out = new HashSet<>();
        for (RouterModel model : upsListModel) {
            SvtModelDto modelDto = routerModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }

    @GetMapping("/modrouter")
    public Set<SvtModelDto> getModelRouter() {
        List<RouterModel> allModels = routerModelService.getAllModels();
        Set<SvtModelDto> dtoes = new HashSet<>();
        for (RouterModel model : allModels) {
            SvtModelDto dtoForSelectize = routerModelMapper.getDtoForSelectize(model);
            dtoes.add(dtoForSelectize);
        }
        return dtoes;
    }

    @GetMapping("/getrouter")
    public SvtDTO getRouterById(Long routerId) {

        Router router = routerService.getById(routerId);
        SvtSwitchHubDTO routerDto = routerMapper.getDto(router);

        return routerDto;
    }
    
   
}
