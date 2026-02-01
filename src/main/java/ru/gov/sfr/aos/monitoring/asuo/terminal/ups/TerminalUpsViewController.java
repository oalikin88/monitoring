package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

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

import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.controllers.SvtViewController;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class TerminalUpsViewController {

    @Autowired
    private TerminalUpsService terminalUpsService;
    @Autowired
    private TerminalUpsOutDtoTreeService terminalUpsOutDtoTreeService;
    @Autowired
    private TerminalUpsModelService terminalUpsModelService;
    @Autowired
    private TerminalUpsModelMapper terminalUpsModelMapper;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-ups")
    public String getModelTerminalUps(Model model) {
        List<TerminalUpsModel> terminalUpsModels = terminalUpsModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalUpsModelMapper.getListDtoes(terminalUpsModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели ИБП терминала");
        model.addAttribute("attribute", "mterminalUps");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-ups-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-ups-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-ups-manufacturers");
        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mterminal-ups")
    public ResponseEntity<String> saveModelTerminalUps(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalUpsModel terminalUpsModel = terminalUpsModelMapper.getModel(dto);
        try {
            terminalUpsModelService.saveModel(terminalUpsModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mterminal-ups")
    public ResponseEntity<String> updateModelTerminalUps(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalUpsModel terminalUpsModel = terminalUpsModelMapper.getModel(dto);
        try {
            terminalUpsModelService.update(terminalUpsModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @SendArchive
    @DeleteMapping("/mterminal-ups-archived")
    public ResponseEntity<String> sendModelTerminalUpsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalUpsModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/terminal-ups")
    public String getTerminalUps(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "serialNumber", required = false) String serialNumber) {
        Map<Location, List<TerminalUps>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = terminalUpsService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = terminalUpsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalUpsService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalUpsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalUps>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = terminalUpsService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = terminalUpsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalUpsService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalUpsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalUps");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "ИБП для терминала");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", false);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-ups")
    public ResponseEntity<String> saveTerminalUps(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        terminalUpsService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //        @UpdLog
    @PutMapping("/terminal-ups")
    public ResponseEntity<String> updateTerminalUps(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        try {
            terminalUpsService.updateSvtObj(dto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/terminal-ups-tostor")
    public ResponseEntity<String> sendToStorageTerminalUps(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalUps terminalUps = terminalUpsService.getById(dto.getId());
        terminalUpsService.sendToStorage(terminalUps);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @SendArchive
    @DeleteMapping("/terminal-ups-archived")
    public ResponseEntity<String> sendTerminalUpsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalUpsService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
