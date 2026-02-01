package ru.gov.sfr.aos.monitoring.asuo.tv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.asuo.Asuo;
import ru.gov.sfr.aos.monitoring.asuo.AsuoRepo;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class DisplayRestController {

    @Autowired
    private DisplayMapper displayMapper;
    @Autowired
    private DisplayService displayService;
    @Autowired
    private DisplayModelService displayModelService;
    @Autowired
    private DisplayManufacturerService displayManufacturerService;
    @Autowired
    private DisplayManufacturerMapper displayManufacturerMapper;
    @Autowired
    private DisplayModelMapper displayModelMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;
    @Autowired
    private AsuoRepo asuoRepo;

    @GetMapping("/get-display-manufacturers")
    public List<ManufacturerDTO> getDisplayManufacturers() {
        List<DisplayManufacturer> allManufacturers = displayManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = displayManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-models-display-by-manufacturer")
    public List<SvtModelDto> getDisplayModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        List<DisplayModel> displayModelList = displayModelService.getModelsByManufacturerId(id);
        List<SvtModelDto> out = new ArrayList<>();
        for (DisplayModel model : displayModelList) {
            SvtModelDto modeDto = displayModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/moddisplay")
    public List<SvtModelDto> getModelDisplay() {
        List<DisplayModel> allModels = displayModelService.getAllModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelDisplayDtoes(allModels);
        return dtoes;
    }

    @GetMapping("/getdisplay")
    public DisplayDto getDisplayById(Long displayId) {
        Display display = displayService.getById(displayId);
        DisplayDto dto = displayMapper.getDto(display);
        return dto;
    }

    @GetMapping("/getalldisplay")
    public List<DisplayDto> getAllDisplay() {
        List<Display> allDisplays = displayService.getAllDisplays();
        List<DisplayDto> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for (Display disp : allDisplays) {
            DisplayDto dto = displayMapper.getDto(disp);
            dtoes.add(dto);
        }
        return dtoes;
    }

    @GetMapping("/getdisplays")
    public List<DisplayDto> getDisplays() {
        List<Display> allDisplays = displayService.getAllDisplays();
        List<DisplayDto> dtoes = new ArrayList<>();
        List<Asuo> asuos = asuoRepo.findAll();
        for (Display disp : allDisplays) {
            DisplayDto dto = displayMapper.getDto(disp);
            dtoes.add(dto);
        }
        return dtoes;
    }
    
        @PostMapping("/save-display-manufacturer")
    public ResponseEntity<?> saveDisplayManufacturer(String name) throws ObjectAlreadyExists {
        DisplayManufacturer savedManufacturer = null;
        DisplayManufacturer potencialManufacturer = new DisplayManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = displayManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        ManufacturerDTO dto = displayManufacturerMapper.getDto(savedManufacturer);
        
        return ResponseEntity.ok(dto);
    }
    


}
