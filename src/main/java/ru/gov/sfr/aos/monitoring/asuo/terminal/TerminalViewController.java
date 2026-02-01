package ru.gov.sfr.aos.monitoring.asuo.terminal;

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

import ru.gov.sfr.aos.monitoring.asuo.ProgramSoftware;
import ru.gov.sfr.aos.monitoring.controllers.SvtViewController;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.ProgramSoftwareDto;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class TerminalViewController {

    @Autowired
    private TerminalModelService terminalModelService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private TerminalOutDtoTreeService terminalOutDtoTreeService;
    @Autowired
    private TerminalModelMapper terminalModelMapper;
    @Autowired
    private TerminalProgramSoftwareService terminalProgramSoftwareService;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mterminal")
    public String getModelTerminal(Model model) {
        List<TerminalModel> terminalModels = terminalModelService.getAllActualModels();
        List<SvtModelDto> getTerminalModelsDtoes = terminalModelMapper.getListDtoes(terminalModels);
        model.addAttribute("dtoes", getTerminalModelsDtoes);
        model.addAttribute("namePage", "Модели терминалов");
        model.addAttribute("attribute", "mterminal");
        model.addAttribute("manufacturersSaveLink", "/save-terminal-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-terminal-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-terminal-manufacturers");
        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mterminal")
    public ResponseEntity<String> saveModelTerminal(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalModel terminalModel = terminalModelMapper.getModel(dto);
        try {
            terminalModelService.saveModel(terminalModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mterminal")
    public ResponseEntity<String> updateModelTerminal(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        TerminalModel terminalModel = terminalModelMapper.getModel(dto);
        try {
            terminalModelService.update(terminalModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @SendArchive
    @DeleteMapping("/mterminalarchived")
    public ResponseEntity<String> sendModelTerminalToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/terminal")
    public String getTerminal(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber
    ) {
        Map<Location, List<Terminal>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = terminalService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != inventaryNumber) {
            svtObjectsByEmployee = terminalService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = terminalService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = terminalService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = terminalOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Terminal>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = terminalService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != inventaryNumber) {
            svtObjectsByStorage = terminalService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = terminalService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = terminalService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = terminalOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "terminal");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "Терминалы");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", false);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/terminal")
    public ResponseEntity<String> saveTerminal(@RequestBody TerminalDto dto) throws ObjectAlreadyExists {
        terminalService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    //        @UpdLog

    @PutMapping("/terminal")
    public ResponseEntity<String> updateTerminal(@RequestBody TerminalDto dto) throws ObjectAlreadyExists {
        try {
            terminalService.updateSvtObj(dto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/terminaltostor")
    public ResponseEntity<String> sendToStorageTerminal(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Terminal terminal = terminalService.getById(dto.getId());
        terminalService.sendToStorage(terminal);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @SendArchive
    @DeleteMapping("/terminalarchived")
    public ResponseEntity<String> sendTerminalToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        terminalService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
      @GetMapping("/po")
    public String getProgramSoftwares(Model model) {
        List<ProgramSoftware> programSoftwares = terminalProgramSoftwareService.getProgramSoftwares();
        List<ProgramSoftwareDto> programSoftwareDtoesList = terminalProgramSoftwareService.getProgramSoftwareDtoesList(programSoftwares);

        model.addAttribute("dtoes", programSoftwareDtoesList);
        model.addAttribute("namePage", "Програмное обеспечение терминала");
        model.addAttribute("attribute", "programSoftware");
        return "models";
    }
    
    
        @PostMapping("/po")
    public ResponseEntity<String> saveProgramSoftware(@RequestBody ProgramSoftwareDto dto) throws ObjectAlreadyExists {
        try{
            terminalProgramSoftwareService.createProgramSoftware(dto);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @PutMapping("/po")
    public ResponseEntity<String> updateProgramSoftware(@RequestBody ProgramSoftwareDto dto) throws ObjectAlreadyExists {
        try{
            terminalProgramSoftwareService.updateProgramSoftware(dto);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
    @DeleteMapping("/po")
    public ResponseEntity<String> deleteProgramSoftware(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        try{
            terminalProgramSoftwareService.deleteProgramSoftware(dto);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    

}
