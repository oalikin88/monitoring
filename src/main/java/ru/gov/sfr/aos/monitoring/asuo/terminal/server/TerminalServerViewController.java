package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

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
public class TerminalServerViewController {

    @Autowired
    private TerminalServerService terminalServerService;
    @Autowired
    private TerminalServerOutDtoTreeService terminalServerOutDtoTreeService;
    @Autowired
    private TerminalServerModelService terminalServerModelService;
    @Autowired
    private TerminalServerModelMapper terminalServerModelMapper;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-server")
    public String getModelTerminalServer(Model model) {
        List<TerminalServerModel> terminalServerModels = terminalServerModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalServerModelMapper.getListDtoes(terminalServerModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели серверов терминала");
        model.addAttribute("attribute", "mterminalServer");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-server-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-server-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-server-manufacturers");
        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mterminal-server")
    public ResponseEntity<String> saveModelTerminalServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalServerModel terminalServerModel = terminalServerModelMapper.getModel(dto);
        try {
            terminalServerModelService.saveModel(terminalServerModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mterminal-server")
    public ResponseEntity<String> updateModelTerminalServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalServerModel terminalServerModel = terminalServerModelMapper.getModel(dto);
        try {
            terminalServerModelService.update(terminalServerModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @SendArchive
    @DeleteMapping("/mterminal-server-archived")
    public ResponseEntity<String> sendModelTerminalServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalServerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/terminal-server")
    public String getTerminalServer(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "serialNumber", required = false) String serialNumber) {
        Map<Location, List<TerminalServer>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = terminalServerService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = terminalServerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalServerService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalServerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalServer>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = terminalServerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = terminalServerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByStorage = terminalServerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalServerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalServer");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "Сервер для терминала");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", false);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-server")
    public ResponseEntity<String> saveTerminalServer(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        terminalServerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //        @UpdLog
    @PutMapping("/terminal-server")
    public ResponseEntity<String> updateTerminalServer(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        try {
            terminalServerService.updateSvtObj(dto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/terminal-server-tostor")
    public ResponseEntity<String> sendToStorageTerminalServer(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalServer terminalServer = terminalServerService.getById(dto.getId());
        terminalServerService.sendToStorage(terminalServer);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @SendArchive
    @DeleteMapping("/terminal-server-archived")
    public ResponseEntity<String> sendTerminalServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalServerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
