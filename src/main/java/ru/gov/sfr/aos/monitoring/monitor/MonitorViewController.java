package ru.gov.sfr.aos.monitoring.monitor;

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
public class MonitorViewController {

    @Autowired
    private MonitorModelService monitorModelService;
    @Autowired
    private MonitorOutDtoTreeService monitorOutDtoTreeService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private MonitorMapper monitorMapper;
    @Autowired
    private MonitorModelMapper monitorModelMapper;
    @Autowired
    private LocationService locationService;

//   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmonitors")
    public String getModelMonitors(Model model) {

        List<MonitorModel> monitorModels = monitorModelService.getAllActualModels();
        List<SvtModelDto> monitorModelsDtoes = monitorModelMapper.getListDtoes(monitorModels);
        model.addAttribute("dtoes", monitorModelsDtoes);
        model.addAttribute("namePage", "Модели мониторов");
        model.addAttribute("attribute", "mmonitors");
        model.addAttribute("manufacturersSaveLink", "/save-monitor-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-monitor-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-monitor-manufacturers");
        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @SendArchive
    @DeleteMapping("/mmonitorsarchived")
    public ResponseEntity<String> sendModelMonitorToArchive(@RequestBody ArchivedDto dto) {
        monitorModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/mmonitors")
    public ResponseEntity<String> saveModelMonitor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        MonitorModel monitorModel = monitorModelMapper.getModel(dto);
        try {
            monitorModelService.saveModel(monitorModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/mmonitorsupd")
    public ResponseEntity<String> updateModelMonitor(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        MonitorModel monitorModel = monitorModelMapper.getModel(dto);
        try {
            monitorModelService.update(monitorModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/updmonitor")
    public ResponseEntity<String> updateMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        monitorService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //  @SendArchive
    @DeleteMapping("/monitorsarchived")
    public ResponseEntity<String> sendMonitorToArchive(@RequestBody ArchivedDto dto) {
        monitorService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/monitortostor")
    public ResponseEntity<String> sendToStorageMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Monitor monitor = monitorService.getById(dto.getId());
        monitorService.sendToStorage(monitor);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/monitors")
    public String getMonitors(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Monitor>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Monitor>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = monitorService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = monitorService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = monitorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
            } else {
                svtObjectsByEmployee = monitorService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
            treeSvtDtoByEmployee = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
            if (null != username) {
                svtObjectsByStorage = monitorService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = monitorService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = monitorService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = monitorService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }
            treeSvtDtoByStorage = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

        } else {
            List<Monitor> monitorByFilter = monitorService.getMonitorByFilter(dto);
            filter = new ArrayList<>();
            for (Monitor p : monitorByFilter) {
                SvtDTO upsDto = monitorMapper.getDto(p);
                filter.add(upsDto);
            }

            svtObjectsByEmployee = monitorService.getMonitorByPlaceTypeAndFilter(PlaceType.EMPLOYEE, monitorByFilter);
            treeSvtDtoByEmployee = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = monitorService.getMonitorByPlaceTypeAndFilter(PlaceType.STORAGE, monitorByFilter);
            treeSvtDtoByStorage = monitorOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<MonitorModel> optModel = monitorModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "monitors");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage", "Мониторы");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/monitors")
    public ResponseEntity<String> saveMonitor(@RequestBody SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        monitorService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
