package ru.gov.sfr.aos.monitoring.server;

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
public class ServerViewController {

    @Autowired
    private ServerModelService serverModelService;
    @Autowired
    private ServerService serverService;
    @Autowired
    private ServerOutDtoTreeService serverOutDtoTreeService;
    @Autowired
    private ServerMapper serverMapper;
    @Autowired
    private ServerModelMapper serverModelMapper;
    @Autowired
    private LocationService locationService;

    // @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mserver")
    public String getModelServer(Model model) {
        List<ServerModel> serverModels = serverModelService.getAllActualModels();
        List<SvtModelDto> getServerModelsDtoes = serverModelMapper.getListDtoes(serverModels);
        model.addAttribute("dtoes", getServerModelsDtoes);
        model.addAttribute("namePage", "Модели серверов");
        model.addAttribute("attribute", "mserver");
        model.addAttribute("manufacturersSaveLink", "/save-server-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-server-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-server-manufacturers");
        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mserver")
    public ResponseEntity<String> saveModelServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ServerModel serverModel = serverModelMapper.getModel(dto);
        try {
            serverModelService.saveModel(serverModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mserverupd")
    public ResponseEntity<String> updateModelServer(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        ServerModel serverModel = serverModelMapper.getModel(dto);
        try {
            serverModelService.update(serverModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @SendArchive
    @DeleteMapping("/mserverarchived")
    public ResponseEntity<String> sendModelServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        serverModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/server")
    public String getServers(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Server>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Server>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if (dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
            if (null != username) {
                svtObjectsByEmployee = serverService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
            } else if (null != inventaryNumber) {
                svtObjectsByEmployee = serverService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
            } else if (null != serialNumber) {
                svtObjectsByEmployee = serverService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
            } else {
                svtObjectsByEmployee = serverService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
            }
            treeSvtDtoByEmployee = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            if (null != username) {
                svtObjectsByStorage = serverService.getSvtObjectsByName(username, PlaceType.STORAGE);
            } else if (null != inventaryNumber) {
                svtObjectsByStorage = serverService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
            } else if (null != serialNumber) {
                svtObjectsByStorage = serverService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
            } else {
                svtObjectsByStorage = serverService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
            }
            treeSvtDtoByStorage = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());
        } else {

            List<Server> serverByFilter = serverService.getServerByFilter(dto);
            filter = new ArrayList<>();
            for (Server p : serverByFilter) {
                SvtDTO serverDto = serverMapper.getDto(p);
                filter.add(serverDto);
            }

            svtObjectsByEmployee = serverService.getServerByPlaceTypeAndFilter(PlaceType.SERVERROOM, serverByFilter);
            treeSvtDtoByEmployee = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                    .stream()
                    .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                    .collect(Collectors.toList());

            svtObjectsByStorage = serverService.getServerByPlaceTypeAndFilter(PlaceType.STORAGE, serverByFilter);
            treeSvtDtoByStorage = serverOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<ServerModel> optModel = serverModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "server");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage", "Серверы");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        return "svtobj";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PostMapping("/server")
    public ResponseEntity<String> saveServer(@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        serverService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PostMapping("/servertostor")
    public ResponseEntity<String> sendToStorageServer(@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists {
        Server server = serverService.getById(dto.getId());
        serverService.sendToStorage(server);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    // @UpdLog
    @PutMapping("/updserver")
    public ResponseEntity<String> updateServer(@RequestBody SvtServerDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        serverService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @SendArchive
    @DeleteMapping("/serverarchived")
    public ResponseEntity<String> sendServerToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        serverService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
