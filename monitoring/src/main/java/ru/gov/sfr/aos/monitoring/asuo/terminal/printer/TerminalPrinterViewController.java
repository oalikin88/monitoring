package ru.gov.sfr.aos.monitoring.asuo.terminal.printer;

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
public class TerminalPrinterViewController {

    @Autowired
    private TerminalPrinterService terminalPrinterService;
    @Autowired
    private TerminalPrinterOutDtoTreeService terminalPrinterOutDtoTreeService;
    @Autowired
    private TerminalPrinterModelService terminalPrinterModelService;
    @Autowired
    private TerminalPrinterModelMapper terminalPrinterModelMapper;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal-printer")
    public String getModelTerminalPrinter(Model model) {
        List<TerminalPrinterModel> terminalModelsPrinter = terminalPrinterModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalPrinterModelMapper.getListDtoes(terminalModelsPrinter);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели принтеров терминала");
        model.addAttribute("attribute", "mterminalPrinter");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-printer-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-printer-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-printer-manufacturers");
        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mterminal-printer")
    public ResponseEntity<String> saveModelTerminalPrinter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalPrinterModel terminalPrinterModel = terminalPrinterModelMapper.getModel(dto);
        try {
            terminalPrinterModelService.saveModel(terminalPrinterModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mterminal-printer")
    public ResponseEntity<String> updateModelTerminalPrinter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalPrinterModel terminalPrinterModel = terminalPrinterModelMapper.getModel(dto);
        try {
            terminalPrinterModelService.update(terminalPrinterModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @SendArchive
    @DeleteMapping("/mterminal-printer-archived")
    public ResponseEntity<String> sendModelTerminalPrinterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalPrinterModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/terminal-printer")
    public String getTerminalPrinter(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "serialNumber", required = false) String serialNumber) {
        Map<Location, List<TerminalPrinter>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = terminalPrinterService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = terminalPrinterService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalPrinterService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalPrinterOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<TerminalPrinter>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = terminalPrinterService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = terminalPrinterService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalPrinterService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalPrinterOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", username);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminalPrinter");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "Принтер для терминала");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", false);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal-printer")
    public ResponseEntity<String> saveTerminalPrinter(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        terminalPrinterService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //        @UpdLog
    @PutMapping("/terminal-printer")
    public ResponseEntity<String> updateTerminalPrinter(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        try {
            terminalPrinterService.updateSvtObj(dto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/terminal-printer-tostor")
    public ResponseEntity<String> sendToStorageTerminalPrinter(@RequestBody TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalPrinter terminalPrinter = terminalPrinterService.getById(dto.getId());
        terminalPrinterService.sendToStorage(terminalPrinter);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @SendArchive
    @DeleteMapping("/terminal-printer-archived")
    public ResponseEntity<String> sendTerminalPrinterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalPrinterService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
