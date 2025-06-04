package ru.gov.sfr.aos.monitoring.systemblock;

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
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.location.LocationService;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class SystemblockViewController {

    @Autowired
    private SystemBlockModelService systemBlockModelService;
    @Autowired
    private SystemBlockService systemblockService;
    @Autowired
    private SystemBlockOutDtoTreeService systemBlockOutDtoTreeService;
    @Autowired
    private SystemBlockMapper systemblockMapper;
    @Autowired
    private SystemblockModelMapper sysblockModelMapper;
    @Autowired
    private LocationService locationService;

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/sysblocks")
    public String getSysBlocks(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<SystemBlock>> svtObjectsByEmployee = null;
        List<SvtDTO> filter = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<SystemBlock>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;

        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = systemblockService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = systemblockService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = systemblockService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
            } else {
                svtObjectsByEmployee = systemblockService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
            }
            treeSvtDtoByEmployee = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = systemblockService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = systemblockService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = systemblockService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = systemblockService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }
            treeSvtDtoByStorage = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
        } else {

            List<SystemBlock> systemblockByFilter = systemblockService.getSystemBlockByFilter(dto);
            filter = new ArrayList<>();
            for (SystemBlock p : systemblockByFilter) {
                SvtDTO upsDto = systemblockMapper.getDto(p);
                filter.add(upsDto);
            }

            svtObjectsByEmployee = systemblockService.getSystemblockByPlaceTypeAndFilter(PlaceType.EMPLOYEE, systemblockByFilter);
            treeSvtDtoByEmployee = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = systemblockService.getSystemblockByPlaceTypeAndFilter(PlaceType.STORAGE, systemblockByFilter);
            treeSvtDtoByStorage = systemBlockOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<SystemBlockModel> optModel = systemBlockModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "systemblock");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage", "Системные блоки");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);

        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/sysblocks")
    public ResponseEntity<String> saveSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        systemblockService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //@PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/sysblockstostor")
    public ResponseEntity<String> sendToStorageSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
        SystemBlock systemblock = systemblockService.getById(dto.getId());
        systemblockService.sendToStorage(systemblock);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @UpdLog
    @PutMapping("/updsysblocks")
    public ResponseEntity<String> updateSystemblock(@RequestBody SvtSystemBlockDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        systemblockService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @SendArchive
    @DeleteMapping("/systemblockarchived")
    public ResponseEntity<String> sendSystemBlockToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        systemblockService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/msysblock")
    public String getModelSystemblock(Model model) {

        List<SystemBlockModel> systemBlockModels = systemBlockModelService.getAllActualModels();
        List<SvtModelDto> systemBlockModelsDtoes = sysblockModelMapper.getListDtoes(systemBlockModels);
        model.addAttribute("dtoes", systemBlockModelsDtoes);
        model.addAttribute("namePage", "Модели системных блоков");
        model.addAttribute("attribute", "msysblock");
        model.addAttribute("manufacturersSaveLink", "/save-sysblock-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-sysblock-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-sysblock-manufacturers");
        return "models";
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/msysblock")
    public ResponseEntity<String> saveModelSystemBlock(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SystemBlockModel systemBlockModel = sysblockModelMapper.getModel(dto);
        try {
            systemBlockModelService.saveModel(systemBlockModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/msysblockupd")
    public ResponseEntity<String> updateModelSystemBlock(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SystemBlockModel systemBlockModel = sysblockModelMapper.getModel(dto);
        try {
            systemBlockModelService.update(systemBlockModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @SendArchive
    @DeleteMapping("/msysblockarchived")
    public ResponseEntity<String> sendModelSystemBlockToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        systemBlockModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

}
