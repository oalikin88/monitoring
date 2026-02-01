package ru.gov.sfr.aos.monitoring.asuo;

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

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class AsuoViewController {

    @Autowired
    private AsuoService asuoService;
    @Autowired
    private AsuoOutDtoTreeService asuoOutDtoTreeService;
    

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/asuo")
    public String getAsuo(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber) {
        Map<Location, List<Asuo>> svtObjectsByEmployee = null;
        if (null != username) {
            svtObjectsByEmployee = asuoService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
        } else if (null != inventaryNumber) {
            svtObjectsByEmployee = asuoService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
        } else {
            svtObjectsByEmployee = asuoService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
        }
        List<LocationByTreeDto> treeSvtDtoByEmployee = asuoOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        Map<Location, List<Asuo>> svtObjectsByStorage = null;

        if (null != username) {
            svtObjectsByStorage = asuoService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if (null != inventaryNumber) {
            svtObjectsByStorage = asuoService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = asuoService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        List<LocationByTreeDto> treeSvtDtoByStorage = asuoOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());

        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("attribute", "asuo");
        model.addAttribute("namePage", "Электронные очереди");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", false);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/asuo")
    public ResponseEntity<String> saveAsuo(@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
        asuoService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    //          @UpdLog

    @PutMapping("/asuo")
    public ResponseEntity<String> updateAsuo(@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
        asuoService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/asuotostor")
    public ResponseEntity<String> sendToStorageAsuo(@RequestBody AsuoDTO dto) throws ObjectAlreadyExists {
        Asuo asuo = asuoService.getById(dto.getId());
        asuoService.sendToStorage(asuo);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//   @SendArchive
    @DeleteMapping("/asuoarchived")
    public ResponseEntity<String> sendAsuoToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        asuoService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
