package ru.gov.sfr.aos.monitoring.asuo.tv;

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
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class DisplayViewController {

    @Autowired
    private DisplayModelService displayModelService;
    @Autowired
    private DisplayService displayService;
    @Autowired
    private DisplayOutDtoTreeService displayOutDtoTreeService;
    @Autowired
    private DisplayModelMapper displayModelMapper;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mdisplay")
    public String getModelDisplay(Model model) {
        List<DisplayModel> displayModels = displayModelService.getAllActualModels();
        List<SvtModelDto> getDisplayModelsDtoes = displayModelMapper.getListDtoes(displayModels);
        model.addAttribute("dtoes", getDisplayModelsDtoes);
        model.addAttribute("namePage", "Модели главного табло");
        model.addAttribute("attribute", "mdisplay");
        model.addAttribute("manufacturersSaveLink", "/save-display-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-models-display-by-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-display-manufacturers");
        return "models";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mdisplay")
    public ResponseEntity<String> saveModelDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        DisplayModel displayModel = displayModelMapper.getModel(dto);
        try {
            displayModelService.saveModel(displayModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mdisplay")
    public ResponseEntity<String> updateModelDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        DisplayModel displayModel = displayModelMapper.getModel(dto);
        try {
            displayModelService.update(displayModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //    @SendArchive
    @DeleteMapping("/mdisplayarchived")
    public ResponseEntity<String> sendModelDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        displayModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/display")
    public String getDisplay(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber) {
        Map<Location, List<Display>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = displayService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != inventaryNumber) {
            svtObjectsByEmployee = displayService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
        } else if (null != serialNumber) {
            svtObjectsByEmployee = displayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = displayService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = displayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Display>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = displayService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != inventaryNumber) {
            svtObjectsByStorage = displayService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else if (null != serialNumber) {
            svtObjectsByStorage = displayService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = displayService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = displayOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "display");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "Главное табло");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", false);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/display")
    public ResponseEntity<String> saveDisplay(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        displayService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    //         @UpdLog

    @PutMapping("/upddisplay")
    public ResponseEntity<String> updateDisplay(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        displayService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @DeleteMapping("/displaytostor")
    public ResponseEntity<String> sendToStorageDisplay(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Display display = displayService.getById(dto.getId());
        displayService.sendToStorage(display);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//   @SendArchive
    @DeleteMapping("/displayarchived")
    public ResponseEntity<String> sendDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        displayService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
