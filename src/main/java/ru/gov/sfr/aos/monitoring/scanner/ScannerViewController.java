package ru.gov.sfr.aos.monitoring.scanner;

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
public class ScannerViewController {

    @Autowired
    private ScannerModelService scannerModelService;
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private ScannerOutDtoTreeService scannerOutDtoTreeService;
    @Autowired
    private ScannerMapper scannerMapper;
    @Autowired
    private ScannerModelMapper scannerModelMapper;
    @Autowired
    private LocationService locationService;

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mscanner")
    public String getModelScanner(Model model) {

        List<ScannerModel> scannerModels = scannerModelService.getAllActualModels();
        List<SvtModelDto> scannerModelsDtoes = scannerModelMapper.getListDtoes(scannerModels);
        model.addAttribute("dtoes", scannerModelsDtoes);
        model.addAttribute("namePage", "Модели сканеров");
        model.addAttribute("attribute", "mscanner");
        model.addAttribute("manufacturersSaveLink", "/save-scanner-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-scanner-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-scanner-manufacturers");
        return "models";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mscanner")
    public ResponseEntity<String> saveModelScanner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ScannerModel scannerModel = scannerModelMapper.getModel(dto);
        try {
            scannerModelService.saveModel(scannerModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    @PutMapping("/mscannerupd")
    public ResponseEntity<String> updateModelScanner(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ScannerModel scannerModel = scannerModelMapper.getModel(dto);
        try {
            scannerModelService.update(scannerModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //    @SendArchive
    @DeleteMapping("/mscannerarchived")
    public ResponseEntity<String> sendModelScannerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        scannerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/scanner")
    public String getScanners(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {

        Map<Location, List<Scanner>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Scanner>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = scannerService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = scannerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = scannerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
            } else {
                svtObjectsByEmployee = scannerService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
            treeSvtDtoByEmployee = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = scannerService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = scannerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = scannerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = scannerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }

            treeSvtDtoByStorage = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
        } else {

            List<Scanner> scannerByFilter = scannerService.getScannerByFilter(dto);
            filter = new ArrayList<>();
            for (Scanner p : scannerByFilter) {
                SvtDTO scannerDto = scannerMapper.getDto(p);
                filter.add(scannerDto);
            }

            svtObjectsByEmployee = scannerService.getScannerByPlaceTypeAndFilter(PlaceType.EMPLOYEE, scannerByFilter);
            treeSvtDtoByEmployee = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = scannerService.getScannerByPlaceTypeAndFilter(PlaceType.STORAGE, scannerByFilter);
            treeSvtDtoByStorage = scannerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<ScannerModel> optModel = scannerModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "scanner");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage", "Сканеры");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        return "svtobj";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/scanner")
    public ResponseEntity<String> saveScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        scannerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/scannertostor")
    public ResponseEntity<String> sendToStorageScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists {
        Scanner scanner = scannerService.getById(dto.getId());
        scannerService.sendToStorage(scanner);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //       @UpdLog
    @PutMapping("/updscanner")
    public ResponseEntity<String> updateScanner(@RequestBody SvtScannerDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        scannerService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @SendArchive
    @DeleteMapping("/scannerarchived")
    public ResponseEntity<String> sendScannerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        scannerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
