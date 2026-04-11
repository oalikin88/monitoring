package ru.gov.sfr.aos.monitoring.printer;

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

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class PrinterViewController {

    @Autowired
    private PrinterModelMapper printerModelMapper;
    @Autowired
    private PrinterService printerService;
    @Autowired
    private PrinterMapper printerMapper;
    @Autowired
    private PrinterModelService printerModelService;
    @Autowired
    private PrinterOutDtoTreeService printerOutDtoTreeService;
    @Autowired
    private LocationService locationService;

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/printers")
    public String getPrinters(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {

        List<PrinterDto> filter = null;
        Map<Location, List<Printer>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Printer>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = printerService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = printerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = printerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
            } else {
                svtObjectsByEmployee = printerService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }

            treeSvtDtoByEmployee = printerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = printerService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = printerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = printerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = printerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }

            treeSvtDtoByStorage = printerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

        } else {
            List<Printer> filteredList = printerService.getDevicesByFilter(dto);
            filter = new ArrayList<>();
            for (Printer p : filteredList) {
                PrinterDto phoneDto = printerMapper.getDto(p);
                filter.add(phoneDto);
            }

            svtObjectsByEmployee = printerService.getDevicesByPlaceTypeAndFilter(PlaceType.EMPLOYEE, filteredList);
            treeSvtDtoByEmployee = printerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = printerService.getDevicesByPlaceTypeAndFilter(PlaceType.STORAGE, filteredList);
            treeSvtDtoByStorage = printerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<PrinterModel> optModel = printerModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("numberRoom", dto.getNumberRoom());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("filterLocation", filterLocation);
        model.addAttribute("dtoes", treeSvtDtoByEmployee);
        model.addAttribute("dtoesStorage", treeSvtDtoByStorage);
        model.addAttribute("attribute", "printers");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage", "Принтеры");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/printers")
    public ResponseEntity<String> savePhone(@RequestBody PrinterDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        printerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/printers")
    public ResponseEntity<String> updatePhone(@RequestBody PrinterDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        printerService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @SendArchive
    @DeleteMapping("/printersarchived")
    public ResponseEntity<String> sendPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        printerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/printertostor")
    public ResponseEntity<String> sendToStorage(@RequestBody PrinterDto dto) throws ObjectAlreadyExists {
        Printer printer = printerService.getById(dto.getId());
        printerService.sendToStorage(printer);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mprinters")
    public String getModelPrinters(Model model) {

        List<PrinterModel> printerModels = printerModelService.getAllActualModels();
        List<PrinterModelDto> printerModelsDtoes = printerModelMapper.getListDtoes(printerModels);
        model.addAttribute("dtoes", printerModelsDtoes);
        model.addAttribute("namePage", "Модели принтеров");
        model.addAttribute("attribute", "mprinters");
        model.addAttribute("manufacturersSaveLink", "/save-printer-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-printer-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-printer-manufacturers");

        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mprinters")
    public ResponseEntity<String> saveModelPrinter(@RequestBody PrinterModelDto dto) throws ObjectAlreadyExists {
        PrinterModel printerModel = printerModelMapper.getModel(dto);
        try {
            printerModelService.saveModel(printerModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mprintersupd")
    public ResponseEntity<String> updateModelPrinter(@RequestBody PrinterModelDto dto) throws ObjectAlreadyExists {
        PrinterModel printerModel = printerModelMapper.getModel(dto);
        try {
            printerModelService.update(printerModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @SendArchive
    @DeleteMapping("/mprintersarchived")
    public ResponseEntity<String> sendModelPrinterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        printerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

}
