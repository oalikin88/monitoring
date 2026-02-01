package ru.gov.sfr.aos.monitoring.systemblock;

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

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class SystemblockRestController {

    @Autowired
    private SystemblockManufacturerMapper sysblockManufacturerMapper;
    @Autowired
    private SystemblockManufacturerService sysblockManufacturerService;
    @Autowired
    private SystemblockModelMapper sysblockModelMapper;
    @Autowired
    private SystemBlockModelService systemBlockModelService;
    @Autowired
    private SystemBlockService systemBlockService;
    @Autowired
    private SystemBlockMapper systemBlockMapper;

    @GetMapping("/getsystemblock")
    public SvtDTO getSystemblock(Long systemblockId) {
        SystemBlock systemblock = systemBlockService.getById(systemblockId);
        SvtSystemBlockDTO systemblockDto = systemBlockMapper.getDto(systemblock);
        return systemblockDto;
    }

    @PostMapping("/save-sysblock-manufacturer")
    public ResponseEntity<?> saveSysblockManufacturer(String name) throws ObjectAlreadyExists {
        SystemBlockManufacturer savedManufacturer = null;
        SystemBlockManufacturer potencialManufacturer = new SystemBlockManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = sysblockManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = sysblockManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-sysblock-manufacturers")
    public List<ManufacturerDTO> getSysblockManufacturers() {
        List<SystemBlockManufacturer> allManufacturers = sysblockManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (SystemBlockManufacturer el : allManufacturers) {
            ManufacturerDTO dto = sysblockManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-sysblock-modelsby-manufacturer")
    public List<SvtModelDto> getSysblockModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        SystemBlockManufacturer manufacturer = sysblockManufacturerService.getManufacturer(id);
        Set<SystemBlockModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (SystemBlockModel model : upsListModel) {
            SvtModelDto modelDto = sysblockModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }

    @GetMapping("/modsysblock")
    public Set<SvtModelDto> getModelSysBlocks() {
        List<SystemBlockModel> allModels = systemBlockModelService.getAllActualModels();
        Set<SvtModelDto> systemBlockModelsDtoes = new HashSet<>();
        for (SystemBlockModel model : allModels) {
            SvtModelDto dtoForSelectize = sysblockModelMapper.getDtoForSelectize(model);
            systemBlockModelsDtoes.add(dtoForSelectize);
        }
        return systemBlockModelsDtoes;
    }

}
