package ru.gov.sfr.aos.monitoring.switchhub;

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
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class SwitchHubViewController {

    @Autowired
    private SwitchHubModelService switchHubModelService;
    @Autowired
    private SwitchHubService switchHubService;
    @Autowired
    private SwitchHubOutDtoTreeService switchHubOutDtoTreeService;
    @Autowired
    private SwitchHubMapper switchHubMapper;
    @Autowired
    private SwitchHubModelMapper switchHubModelMapper;
    @Autowired
    private LocationService locationService;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mswitch")
    public String getModelSwitchHub(Model model) {

        List<SwitchHubModel> switchHubModels = switchHubModelService.getAllActualModels();
        List<SvtModelDto> getSwitchHubModelsDtoes = switchHubModelMapper.getListDtoes(switchHubModels);
        model.addAttribute("dtoes", getSwitchHubModelsDtoes);
        model.addAttribute("namePage", "Модели коммутаторов/концентраторов");
        model.addAttribute("attribute", "mswitch");
        model.addAttribute("manufacturersSaveLink", "/save-switch-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-switch-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-switch-manufacturers");
        return "models";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mswitch")
    public ResponseEntity<String> saveModelSwitchHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SwitchHubModel switchHubModel = switchHubModelMapper.getModel(dto);
        try {
            switchHubModelService.saveModel(switchHubModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mswitchupd")
    public ResponseEntity<String> updateModelSwitchHub(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SwitchHubModel switchHubModel = switchHubModelMapper.getModel(dto);
        try {
            switchHubModelService.update(switchHubModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //     @SendArchive
    @DeleteMapping("/mswitcharchived")
    public ResponseEntity<String> sendModelSwitchHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        switchHubModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/switch")
    public String getSwitchHub(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<SwitchHub>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<SwitchHub>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = switchHubService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = switchHubService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = switchHubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
            } else {
                svtObjectsByEmployee = switchHubService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
            }
            treeSvtDtoByEmployee = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = switchHubService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = switchHubService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = switchHubService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = switchHubService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }
            treeSvtDtoByStorage = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
        } else {

            List<SwitchHub> switchHubByFilter = switchHubService.getSwitchHubByFilter(dto);
            filter = new ArrayList<>();
            for (SwitchHub p : switchHubByFilter) {
                SvtDTO switchHubDto = switchHubMapper.getDto(p);
                filter.add(switchHubDto);
            }

            svtObjectsByEmployee = switchHubService.getSwitchHubByPlaceTypeAndFilter(PlaceType.SERVERROOM, switchHubByFilter);
            treeSvtDtoByEmployee = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = switchHubService.getSwitchHubByPlaceTypeAndFilter(PlaceType.STORAGE, switchHubByFilter);
            treeSvtDtoByStorage = switchHubOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<SwitchHubModel> optModel = switchHubModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "switch");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage", "Коммутаторы/Концентраторы");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/switch")
    public ResponseEntity<String> saveSwitchHub(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        switchHubService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/switchtostor")
    public ResponseEntity<String> sendToStorageSwitchHub(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        SwitchHub switchHub = switchHubService.getById(dto.getId());
        switchHubService.sendToStorage(switchHub);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @UpdLog
    @PutMapping("/updswitch")
    public ResponseEntity<String> updateSwitchHub(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        switchHubService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @SendArchive
    @DeleteMapping("/switcharchived")
    public ResponseEntity<String> sendSwitchHubToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        switchHubService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
