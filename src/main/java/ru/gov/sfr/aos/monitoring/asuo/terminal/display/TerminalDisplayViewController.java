package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

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
public class TerminalDisplayViewController {

    @Autowired
    private TerminalDisplayService terminalDisplayService;
    @Autowired
    private TerminalDisplayOutDtoTreeService terminalDisplayOutDtoTreeService;
    @Autowired
    private TerminalDisplayModelService terminalDisplayModelService;
    @Autowired
    private TerminalDisplayModelMapper terminalDisplayModelMapper;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-display")
    public String getModelTerminalDisplay(Model model) {
        List<TerminalDisplayModel> terminalDisplayModels = terminalDisplayModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalDisplayModelMapper.getListDtoes(terminalDisplayModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели экранов терминала");
        model.addAttribute("attribute", "mterminalDisplay");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-display-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-display-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-display-manufacturers");
        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mterminal-display")
    public ResponseEntity<String> saveModelTerminalDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalDisplayModel terminalDisplayModel = terminalDisplayModelMapper.getModel(dto);
        try {
            terminalDisplayModelService.saveModel(terminalDisplayModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mterminal-display")
    public ResponseEntity<String> updateModelTerminalDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalDisplayModel terminalDisplayModel = terminalDisplayModelMapper.getModel(dto);
        try {
            terminalDisplayModelService.update(terminalDisplayModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @SendArchive
    @DeleteMapping("/mterminal-display-archived")
    public ResponseEntity<String> sendModelTerminalDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalDisplayModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/terminal-display")
    public String getTerminalDisplay(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "serialNumber", required = false) String serialNumber) {
        Map<Location, List<TerminalDisplay>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = terminalDisplayService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = terminalDisplayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalDisplayService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalDisplayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalDisplay>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalDisplayService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalDisplayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalDisplay");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "Экран для терминала");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", false);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-display")
    public ResponseEntity<String> saveTerminalDisplay(@RequestBody TerminalDisplayDto dto) throws ObjectAlreadyExists {
        terminalDisplayService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //        @UpdLog
    @PutMapping("/terminal-display")
    public ResponseEntity<String> updateTerminalDisplay(@RequestBody TerminalDisplayDto dto) throws ObjectAlreadyExists {
        try {
            terminalDisplayService.updateSvtObj(dto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/terminal-display-tostor")
    public ResponseEntity<String> sendToStorageTerminalDisplay(@RequestBody TerminalDisplayDto dto) throws ObjectAlreadyExists {
        TerminalDisplay terminalDisplay = terminalDisplayService.getById(dto.getId());
        terminalDisplayService.sendToStorage(terminalDisplay);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @SendArchive
    @DeleteMapping("/terminal-display-archived")
    public ResponseEntity<String> sendTerminalDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalDisplayService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
