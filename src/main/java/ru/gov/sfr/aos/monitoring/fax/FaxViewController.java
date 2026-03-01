package ru.gov.sfr.aos.monitoring.fax;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gov.sfr.aos.monitoring.controllers.SvtViewController;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.exceptions.DublicateInventoryNumberException;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.location.LocationService;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class FaxViewController {

    @Autowired
    private FaxModelService faxModelService;
    @Autowired
    private FaxService faxService;
    @Autowired
    private FaxOutDtoTreeService faxOutDtoTreeService;
    @Autowired
    private FaxMapper faxMapper;
    @Autowired
    private FaxModelMapper faxModelMapper;
    @Autowired
    private LocationService locationService;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mfax")
    public String getModelFax(Model model) {
        List<FaxModel> faxModels = faxModelService.getAllActualModels();
        List<SvtModelDto> getFaxModelsDtoes = faxModelMapper.getListDtoes(faxModels);
        model.addAttribute("dtoes", getFaxModelsDtoes);
        model.addAttribute("namePage", "Модели факсов");
        model.addAttribute("attribute", "mfax");
        model.addAttribute("manufacturersSaveLink", "/save-fax-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-fax-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-fax-manufacturers");
        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/mfaxupd")
    public ResponseEntity<String> updateModelFax(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        FaxModel faxModel = faxModelMapper.getModel(dto);
        try {
            faxModelService.update(faxModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mfax")
    public ResponseEntity<String> saveModelFax(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        FaxModel faxModel = faxModelMapper.getModel(dto);
        try {
            faxModelService.saveModel(faxModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @SendArchive
    @DeleteMapping("/mfaxarchived")
    public ResponseEntity<String> sendModelFaxToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        faxModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/fax")
    public String getFax(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Fax>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Fax>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<FaxDto> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = faxService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = faxService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = faxService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
            } else {
                svtObjectsByEmployee = faxService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
            }
            treeSvtDtoByEmployee = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = faxService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = faxService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = faxService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = faxService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }
            treeSvtDtoByStorage = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

        } else {

            List<Fax> faxByFilter = faxService.getFaxByFilter(dto);
            filter = new ArrayList<>();
            for (Fax p : faxByFilter) {
                FaxDto faxDto = faxMapper.getDto(p);
                filter.add(faxDto);
            }
            svtObjectsByEmployee = faxService.getFaxByPlaceTypeAndFilter(PlaceType.OFFICEEQUIPMENT, faxByFilter);
            treeSvtDtoByEmployee = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = faxService.getFaxByPlaceTypeAndFilter(PlaceType.STORAGE, faxByFilter);
            treeSvtDtoByStorage = faxOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

        }
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if (null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }

        String filterModel = null;
        if (null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<FaxModel> optModel = faxModelService.getById(Long.parseLong(dto.getModel()));
            if (optModel.isPresent()) {
                filterModel = optModel.get().getModel();
            }

        }
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventaryNumber);
        model.addAttribute("searchSerial", serialNumber);
        model.addAttribute("filterDateBegin", dto.getYearCreatedOne());
        model.addAttribute("filterDateEnd", dto.getYearCreatedTwo());
        model.addAttribute("filterStatus", dto.getStatus());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "fax");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "Факсы");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/fax")
    public ResponseEntity<String> saveFax(@RequestBody FaxDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        faxService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    //    @UpdLog

    @PutMapping("/updfax")
    public ResponseEntity<String> updateFax(@RequestBody FaxDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        faxService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @DeleteMapping("/faxtostor")
    public ResponseEntity<String> sendToStorageFax(@RequestBody FaxDto dto) throws ObjectAlreadyExists {
        Fax fax = faxService.getById(dto.getId());
        faxService.sendToStorage(fax);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @SendArchive
    @DeleteMapping("/faxarchived")
    public ResponseEntity<String> sendFaxToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        faxService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
