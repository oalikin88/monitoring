package ru.gov.sfr.aos.monitoring.ups;

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
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class UpsRestController {

    @Autowired
    private UpsMapper upsMapper;
    @Autowired
    private UpsService upsService;
    @Autowired
    private UpsModelService upsModelService;
    @Autowired
    private UpsManufacturerService upsManufacturerService;
    @Autowired
    private UpsManufacturerMapper upsManufacturerMapper;
    @Autowired
    private UpsModelMapper upsModelMapper;
    @Autowired
    private BatteryTypeService batteryTypeService;
    @Autowired
    private BatteryTypeMapper batteryTypeMapper;

    @GetMapping("/batterytype")
    public List<BatteryTypeDto> getBatteryTypes(@RequestParam(value = "id", required = false) Long id) {
        List<BatteryType> list = new ArrayList<>();
        if (null != id) {
            BatteryType batteryType = batteryTypeService.getBatteryType(id);
            list.add(batteryType);
        } else {
            list = batteryTypeService.getAllActualBatteryTypes();
        }

        List<BatteryTypeDto> out = batteryTypeMapper.getBatteryTypeDtoesList(list);
        return out;
    }

    @PostMapping("/save-ups-manufacturer")
    public ResponseEntity<?> saveUpsManufacturer(String name) throws ObjectAlreadyExists {
        UpsManufacturer savedManufacturer = null;
        UpsManufacturer potencialManufacturer = new UpsManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = upsManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        UpsManufacturerDto dto = upsManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-ups-manufacturers")
    public List<UpsManufacturerDto> getUpsManufacturers() {
        List<UpsManufacturer> allManufacturers = upsManufacturerService.getAllManufacturers();
        List<UpsManufacturerDto> out = upsManufacturerMapper.getListDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-modelsby-manufacturer")
    public List<UpsModelDto> getUpsModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        UpsManufacturer manufacturer = upsManufacturerService.getManufacturer(id);
        Set<UpsModel> upsListModel = manufacturer.getModels();
        List<UpsModelDto> out = new ArrayList<>();
        for (UpsModel model : upsListModel) {
            UpsModelDto modeDto = upsModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

    @GetMapping("/modups")
    public List<SvtModelDto> getModelUps() {
        List<UpsModel> allModels = upsModelService.getAllActualModels();
        List<SvtModelDto> upsModelDtoForSelectize = upsModelMapper.getUpsModelDtoForSelectize(allModels);
        return upsModelDtoForSelectize;
    }

    @GetMapping("/typebatups")
    public List<BatteryTypeDto> getBatteryTypeUps() {

        List<BatteryType> allBatteryTypes = batteryTypeService.getAllActualBatteryTypes();
        List<BatteryTypeDto> batteryTypeDtoesList = batteryTypeMapper.getBatteryTypeDtoesList(allBatteryTypes);
        return batteryTypeDtoesList;
    }

    @GetMapping("/getups")
    public SvtDTO getUpsById(Long upsId) {
        Ups ups = upsService.getById(upsId);
        SvtDTO upsDto = upsMapper.getDto(ups);
        return upsDto;
    }

}
