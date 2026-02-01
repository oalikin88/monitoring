package ru.gov.sfr.aos.monitoring.asuo.hub;

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
import ru.gov.sfr.aos.monitoring.exceptions.DublicateInventoryNumberException;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.location.LocationService;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class HubViewController {

    @Autowired
    private HubModelMapper hubModelMapper;
    @Autowired
    private HubModelService hubModelService;
    @Autowired
    private HubService hubService;
    @Autowired
    private HubOutDtoTreeService hubOutDtoTreeService;
    @Autowired
    private HubMapper hubMapper;
    @Autowired
    private LocationService locationService;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mhub")
    public String getModelHub(Model model) {

        List<HubModel> models = hubModelService.getAllActualModels();
        List<SvtModelDto> modelDtoes = hubModelMapper.getListDtoes(models);
        model.addAttribute("dtoes", modelDtoes);
        model.addAttribute("namePage", "Модели коммутаторов");
        model.addAttribute("attribute", "mhub");
        model.addAttribute("manufacturersSaveLink", "/save-hub-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-hub-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-hub-manufacturers");
        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mhub")
    public ResponseEntity<String> saveModelHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        HubModel hubModel = hubModelMapper.getModel(dto);
        try {
            hubModelService.saveModel(hubModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mhub")
    public ResponseEntity<String> updateModelHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        HubModel hubModel = hubModelMapper.getModel(dto);
        try {
            hubModelService.update(hubModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //     @SendArchive
    @DeleteMapping("/mhubarchived")
    public ResponseEntity<String> sendModelHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        hubModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/hub")
    public String getHub(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Hub>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Hub>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<HubDto> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = hubService.getSvtObjectsByName(username, PlaceType.OFFICEEQUIPMENT);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = hubService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.OFFICEEQUIPMENT);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = hubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.OFFICEEQUIPMENT);
            } else {
                svtObjectsByEmployee = hubService.getSvtObjectsByPlaceType(PlaceType.OFFICEEQUIPMENT);
            }
            treeSvtDtoByEmployee = hubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = hubService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = hubService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = hubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = hubService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }
            treeSvtDtoByStorage = hubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
        } else {

            List<Hub> hubByFilter = hubService.getHubByFilter(dto);
            filter = new ArrayList<>();
            for (Hub p : hubByFilter) {
                HubDto hubDto = hubMapper.getDto(p);
                filter.add(hubDto);
            }

            svtObjectsByEmployee = hubService.getHubByPlaceTypeAndFilter(PlaceType.OFFICEEQUIPMENT, hubByFilter);
            treeSvtDtoByEmployee = hubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = hubService.getHubByPlaceTypeAndFilter(PlaceType.STORAGE, hubByFilter);
            treeSvtDtoByStorage = hubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<HubModel> optModel = hubModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "hub");
        model.addAttribute("placeAttribute", "officeequipment");
        model.addAttribute("namePage", "Коммутаторы");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/hub")
    public ResponseEntity<String> saveHub(@RequestBody HubDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        hubService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/hubtostor")
    public ResponseEntity<String> sendToStorageHub(@RequestBody HubDto dto) throws ObjectAlreadyExists {
        Hub hub = hubService.getById(dto.getId());
        hubService.sendToStorage(hub);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @UpdLog
    @PutMapping("/hub")
    public ResponseEntity<String> updateHub(@RequestBody HubDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        hubService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @SendArchive
    @DeleteMapping("/hubarchived")
    public ResponseEntity<String> sendHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        hubService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
