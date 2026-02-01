package ru.gov.sfr.aos.monitoring.router;

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
import ru.gov.sfr.aos.monitoring.switchhub.SvtSwitchHubDTO;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class RouterViewController {

    @Autowired
    private RouterModelService routerModelService;
    @Autowired
    private RouterService routerService;
    @Autowired
    private RouterMapper routerMapper;
    @Autowired
    private RouterOutDtoTreeService routerOutDtoTreeService;
    @Autowired
    private RouterModelMapper routerModelMapper;
    @Autowired
    private LocationService locationService;
    
    
    
              //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mrouter")
    public String getModelRouterb(Model model) {
        List<RouterModel> routerModels = routerModelService.getAllActualModels();
        List<SvtModelDto> getRouterModelsDtoes = routerModelMapper.getListDtoes(routerModels);
        model.addAttribute("dtoes", getRouterModelsDtoes);
        model.addAttribute("namePage", "Модели маршрутизаторов");
        model.addAttribute("attribute", "mrouter");
        model.addAttribute("manufacturersSaveLink", "/save-router-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-router-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-router-manufacturers");
        
        return "models";
    }
    
//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mrouter")
    public ResponseEntity<String> saveModelRouter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        RouterModel routerModel = routerModelMapper.getModel(dto);
        try{
        routerModelService.saveModel(routerModel);
        }catch(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/mrouterupd")
    public ResponseEntity<String> updateModelRouter(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        RouterModel routerModel = routerModelMapper.getModel(dto);
        try{
            routerModelService.update(routerModel);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
  //  @SendArchive
    @DeleteMapping("/mrouterarchived")
    public ResponseEntity<String> sendModelRouterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        routerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
         //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/router")
    public String getRouter(Model model, @RequestParam(value="username",required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value="serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {
        Map<Location, List<Router>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Router>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        List<SvtDTO> filter = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = routerService.getSvtObjectsByName(username, PlaceType.SERVERROOM);
        } else if(null != inventaryNumber) {
            svtObjectsByEmployee = routerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.SERVERROOM);
        } else if(null != serialNumber) {
            svtObjectsByEmployee = routerService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.SERVERROOM);
        }else {
            svtObjectsByEmployee = routerService.getSvtObjectsByPlaceType(PlaceType.SERVERROOM);
        }
        treeSvtDtoByEmployee = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        if(null != username) {
            svtObjectsByStorage = routerService.getSvtObjectsByName(username, PlaceType.STORAGE);
        } else if(null != inventaryNumber) {
            svtObjectsByStorage = routerService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        } else {
            svtObjectsByStorage = routerService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        treeSvtDtoByStorage = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        } else {
            List<Router> routerByFilter = routerService.getRouterByFilter(dto);
             filter = new ArrayList<>();
            for(Router p : routerByFilter) {
                SvtDTO routerHubDto = routerMapper.getDto(p);
                filter.add(routerHubDto);
            }
            
            svtObjectsByEmployee = routerService.getRouterByPlaceTypeAndFilter(PlaceType.SERVERROOM, routerByFilter);
            treeSvtDtoByEmployee = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = routerService.getRouterByPlaceTypeAndFilter(PlaceType.STORAGE, routerByFilter);
               treeSvtDtoByStorage = routerOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        }
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);
        String filterLocation = null;
        if(null != dto.getLocation() && !dto.getLocation().isEmpty() && !dto.getLocation().isBlank()) {
            filterLocation = locationService.getLocationById(Long.parseLong(dto.getLocation())).getName();
        }
        
        String filterModel = null;
        if(null != dto.getModel() && !dto.getModel().isBlank() && !dto.getModel().isEmpty()) {
            Optional<RouterModel> optModel = routerModelService.getById(Long.parseLong(dto.getModel()));
            if(optModel.isPresent()) {
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
        model.addAttribute("attribute", "router");
        model.addAttribute("placeAttribute", "serverroom");
        model.addAttribute("namePage","Маршрутизаторы");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        return "svtobj";
    }
    
 //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/router")
    public ResponseEntity<String> saveRouter(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        routerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
           //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @UpdLog
    @PutMapping("/routertostor")
    public ResponseEntity<String> sendToStorageRouter(@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            Router router = routerService.getById(dto.getId());
        routerService.sendToStorage(router);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    

 //   @UpdLog
     @PutMapping("/updrouter")
    public ResponseEntity<String> updateRouter (@RequestBody SvtSwitchHubDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        routerService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
        //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @SendArchive
    @PutMapping("/routerarchived")
    public ResponseEntity<String> sendRouterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        routerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
}

