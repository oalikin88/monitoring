package ru.gov.sfr.aos.monitoring.ups;

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
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.location.LocationService;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class UpsViewController {

    @Autowired
    private UpsService upsService;
    @Autowired
    private UpsMapper upsMapper;
    @Autowired
    private UpsOutDtoTreeService upsOutDtoTreeService;
    @Autowired
    private UpsModelService upsModelService;
    @Autowired
    private UpsModelMapper upsModelMapper;
    @Autowired
    private BatteryTypeService batteryTypeService;
    @Autowired
    private BatteryTypeMapper batteryTypeMapper;
    @Autowired
    private LocationService locationService;

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mups")
    public String getModelUps(Model model) {

        List<UpsModel> upsModels = upsModelService.getAllActualModels();
        List<UpsModelDto> upsModelsDtoes = new ArrayList<>();
        for (UpsModel upsModel : upsModels) {
            UpsModelDto dto = upsModelMapper.getDto(upsModel);
            upsModelsDtoes.add(dto);
        }

        model.addAttribute("dtoes", upsModelsDtoes);
        model.addAttribute("namePage", "Модели ИБП");
        model.addAttribute("attribute", "mups");
        model.addAttribute("manufacturersSaveLink", "/save-ups-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-ups-manufacturers");
        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/mups")
    public ResponseEntity<String> saveModelUps(@RequestBody UpsModelDto dto) throws ObjectAlreadyExists {
        UpsModel upsModel = upsModelMapper.getEntityFromDto(dto);
        try {
            upsModelService.saveModel(upsModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mupsupd")
    public ResponseEntity<String> updateModelUps(@RequestBody UpsModelDto dto) throws ObjectAlreadyExists {
        UpsModel upsModel = upsModelMapper.getEntityFromDto(dto);
        try {
            upsModelService.update(upsModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @SendArchive
    @DeleteMapping("/mupsarchived")
    public ResponseEntity<String> sendModelUpsToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        upsModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mupsbat")
    public String getBatteryTypeUps(Model model) {

        List<BatteryType> allBatteryTypes = batteryTypeService.getAllActualBatteryTypes();
        List<BatteryTypeDto> batteryTypeDtoesList = batteryTypeMapper.getBatteryTypeDtoesList(allBatteryTypes);
        model.addAttribute("dtoes", batteryTypeDtoesList);
        model.addAttribute("namePage", "Типы батарей для ИБП");
        model.addAttribute("attribute", "mupsbat");
        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mupsbat")
    public ResponseEntity<String> saveBatteryTypeUps(@RequestBody BatteryTypeDto dto) throws ObjectAlreadyExists {
        BatteryType batteryType = batteryTypeMapper.getBatteryType(dto);
        try {
            batteryTypeService.saveBatteryType(batteryType);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mupsbatupd")
    public ResponseEntity<String> updateBatteryTypeUps(@RequestBody BatteryTypeDto dto) throws ObjectAlreadyExists {
        BatteryType batteryType = batteryTypeMapper.getBatteryType(dto);
        try {
            batteryTypeService.update(batteryType);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/mupsbatarchived")
    public ResponseEntity<String> sendToArchiveBatteryTypeUps(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        batteryTypeService.sendToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/ups")
    public String getUps(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {

        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
            } else {
                svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
            treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = upsService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }
            treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

        } else {
            List<Ups> upsByFilter = upsService.getUpsByFilter(dto);
            filter = new ArrayList<>();
            for (Ups p : upsByFilter) {
                SvtDTO upsDto = upsMapper.getDto(p);
                filter.add(upsDto);
            }

            svtObjectsByEmployee = upsService.getUpsByPlaceTypeAndFilter(PlaceType.EMPLOYEE, upsByFilter);
            treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = upsService.getUpsByPlaceTypeAndFilter(PlaceType.STORAGE, upsByFilter);
            treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<UpsModel> optModel = upsModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "ups");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage", "ИБП");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        return "svtobj";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    // @Log
    @PostMapping("/ups")
    public ResponseEntity<String> saveUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        upsService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    // @SendArchive
    @DeleteMapping("/upsarchived")
    public ResponseEntity<String> sendUpsToArchive(@RequestBody ArchivedDto dto) {
        upsService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/upsforserver")
    public String getServerUps(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Ups>> svtObjectsByEmployee = null;
        Map<Location, List<Ups>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = upsService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
            } else {
                svtObjectsByEmployee = upsService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
            }
            treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = upsService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = upsService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = upsService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = upsService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }

            treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
        } else {

            List<Ups> upsByFilter = upsService.getUpsByFilter(dto);
            filter = new ArrayList<>();
            for (Ups p : upsByFilter) {
                SvtDTO upsDto = upsMapper.getDto(p);
                filter.add(upsDto);
            }

            svtObjectsByEmployee = upsService.getUpsByPlaceTypeAndFilter(PlaceType.SERVERROOM, upsByFilter);
            treeSvtDtoByEmployee = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = upsService.getUpsByPlaceTypeAndFilter(PlaceType.STORAGE, upsByFilter);
            treeSvtDtoByStorage = upsOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<UpsModel> optModel = upsModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "upsforserver");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage", "ИБП");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        return "svtobj";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/upsforserver")
    public ResponseEntity<String> saveServerUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        upsService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //  @SendArchive
    @DeleteMapping("/upsforserverarchived")
    public ResponseEntity<String> sendUpsForServerToArchive(@RequestBody ArchivedDto dto) {
        upsService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/updupsforserver")
    public ResponseEntity<String> updateServerUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        upsService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/updups")
    public ResponseEntity<String> updateUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        upsService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/upstostor")
    public ResponseEntity<String> sendToStorageUps(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {

        Ups ups = upsService.getById(dto.getId());
        upsService.sendToStorage(ups);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
