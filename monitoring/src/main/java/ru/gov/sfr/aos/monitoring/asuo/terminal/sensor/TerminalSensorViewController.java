package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gov.sfr.aos.monitoring.controllers.SvtViewController;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class TerminalSensorViewController {

    @Autowired
    private TerminalSensorService terminalSensorService;
    @Autowired
    private TerminalSensorOutDtoTreeService terminalSensorOutDtoTreeService;
    @Autowired
    private TerminalSensorModelService terminalSensorModelService;
    @Autowired
    private TerminalSensorModelMapper terminalSensorModelMapper;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-sensor")
    public String getModelTerminalSensor(Model model) {
        List<TerminalSensorModel> terminalSensorModels = terminalSensorModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalSensorModelMapper.getListDtoes(terminalSensorModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели сенсоров терминала");
        model.addAttribute("attribute", "mterminalSensor");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-sensor-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-sensor-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-sensor-manufacturers");
        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mterminal-sensor")
    public ResponseEntity<String> saveModelTerminalSensor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalSensorModel terminalSensorModel = terminalSensorModelMapper.getModel(dto);
        try {
            terminalSensorModelService.saveModel(terminalSensorModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PutMapping("/mterminal-sensor")
    public ResponseEntity<String> updateModelTerminalSensor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalSensorModel terminalSensorModel = terminalSensorModelMapper.getModel(dto);
        try {
            terminalSensorModelService.update(terminalSensorModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @SendArchive
    @DeleteMapping("/mterminal-sensor-archived")
    public ResponseEntity<String> sendModelTerminalSensorToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalSensorModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/terminal-sensor")
    public String getTerminalSensor(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "serialNumber", required = false) String serialNumber) {
        Map<Location, List<TerminalSensor>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = terminalSensorService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = terminalSensorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalSensorService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalSensorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalSensor>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = terminalSensorService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = terminalSensorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalSensorService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalSensorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalSensor");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "Сенсор для терминала");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", false);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-sensor")
    public ResponseEntity<String> saveTerminalSensor(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        terminalSensorService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //        @UpdLog
    @PutMapping("/terminal-sensor")
    public ResponseEntity<String> updateTerminalSensor(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        try {
            terminalSensorService.updateSvtObj(dto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/terminal-sensor-tostor")
    public ResponseEntity<String> sendToStorageTerminalSensor(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalSensor terminalSensor = terminalSensorService.getById(dto.getId());
        terminalSensorService.sendToStorage(terminalSensor);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @SendArchive
    @DeleteMapping("/terminal-sensor-archived")
    public ResponseEntity<String> sendTerminalSensorToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalSensorService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
