package ru.gov.sfr.aos.monitoring.phone;

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
public class PhoneViewController {
    
@Autowired
private PhoneModelMapper phoneModelMapper;
@Autowired
private PhoneOutDtoTreeService phoneOutDtoTreeService;   
@Autowired
private PhoneService phoneService;   
@Autowired
private PhoneMapper phoneMapper;
@Autowired
private PhoneModelService phoneModelService;
@Autowired
private LocationService locationService;
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/phones")
    public String getPhones(Model model, @RequestParam(value="username", required=false) String username, 
            @RequestParam(value="inventaryNumber", required = false) String inventaryNumber, 
            @RequestParam(value="serialNumber", required = false) String serialNumber, 
            @ModelAttribute FilterDto dto) {
        
        List<SvtDTO> filter = null;
        Map<Location, List<Phone>> svtObjectsByEmployee = null;
        List<LocationByTreeDto> treeSvtDtoByEmployee = null;
        Map<Location, List<Phone>> svtObjectsByStorage = null;
        List<LocationByTreeDto> treeSvtDtoByStorage = null;
        if(dto.model == null && dto.status == null && dto.yearCreatedOne == null && dto.yearCreatedTwo == null) {
        if(null != username) {
            svtObjectsByEmployee = phoneService.getSvtObjectsByName(username, PlaceType.EMPLOYEE);
        } else if(null != inventaryNumber) {
            svtObjectsByEmployee = phoneService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.EMPLOYEE);
        }else if(null != serialNumber) {
            svtObjectsByEmployee = phoneService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.EMPLOYEE);
        }else {
            svtObjectsByEmployee = phoneService.getSvtObjectsByPlaceType(PlaceType.EMPLOYEE);
        }
        
        treeSvtDtoByEmployee = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        
        if(null != username) {
            svtObjectsByStorage = phoneService.getSvtObjectsByName(username, PlaceType.STORAGE);
        }else if(null != inventaryNumber) {
            svtObjectsByStorage = phoneService.getSvtObjectsByInventaryNumberAndPlace(inventaryNumber, PlaceType.STORAGE);
        }else if(null != serialNumber) {
            svtObjectsByStorage = phoneService.getSvtObjectsBySerialNumberAndPlace(serialNumber, PlaceType.STORAGE);
        }else {
            svtObjectsByStorage = phoneService.getSvtObjectsByPlaceType(PlaceType.STORAGE);
        }
        
          treeSvtDtoByStorage = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        
        } else {
            List<Phone> phonesByFilter = phoneService.getPhonesByFilter(dto);
             filter = new ArrayList<>();
            for(Phone p : phonesByFilter) {
                SvtDTO phoneDto = phoneMapper.getDto(p);
                filter.add(phoneDto);
            }
            
            svtObjectsByEmployee = phoneService.getPhonesByPlaceTypeAndFilter(PlaceType.EMPLOYEE, phonesByFilter);
            treeSvtDtoByEmployee = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByEmployee)
                .stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
            
            svtObjectsByStorage = phoneService.getPhonesByPlaceTypeAndFilter(PlaceType.STORAGE, phonesByFilter);
               treeSvtDtoByStorage = phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(svtObjectsByStorage)
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
            Optional<PhoneModel> optModel = phoneModelService.getById(Long.parseLong(dto.getModel()));
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
        model.addAttribute("attribute", "phones");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("namePage","Телефоны");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
        
        return "svtobj";
    }
    
    
      //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @Log
    @PostMapping("/phones")
    public ResponseEntity<String> savePhone(@RequestBody SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        phoneService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
    
     //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @UpdLog
    @PutMapping("/updphone")
    public ResponseEntity<String> updatePhone(@RequestBody SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        phoneService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
     
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
  //  @SendArchive
    @DeleteMapping("/phonesarchived")
    public ResponseEntity<String> sendPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        phoneService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }   
    
    
      //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //  @UpdLog
    @PutMapping("/phonetostor")
    public ResponseEntity<String> sendToStorage (@RequestBody SvtDTO dto) throws ObjectAlreadyExists {
        Phone phone = phoneService.getById(dto.getId());
        phoneService.sendToStorage(phone);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mphones")
    public String getModelPhones(Model model) {

        List<PhoneModel> phoneModels = phoneModelService.getAllActualModels();
        List<SvtModelDto> phoneModelsDtoes = phoneModelMapper.getListDtoes(phoneModels);
        model.addAttribute("dtoes", phoneModelsDtoes);
        model.addAttribute("namePage", "Модели телефонов");
        model.addAttribute("attribute", "mphones");
        model.addAttribute("manufacturersSaveLink", "/save-phone-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-phone-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-phone-manufacturers");
        
        return "models";
    }
    
    
    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
 //   @Log
    @PostMapping("/mphones")
    public ResponseEntity<String> saveModelPhone(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        PhoneModel phoneModel = phoneModelMapper.getModel(dto);
        try{
            phoneModelService.saveModel(phoneModel);
        }catch(Exception e) {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
        @PutMapping("/mphonesupd")
    public ResponseEntity<String> updateModelPhone(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        PhoneModel phoneModel = phoneModelMapper.getModel(dto);
        try{
            phoneModelService.update(phoneModel);
        } catch(Exception e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
      //  @SendArchive
    @DeleteMapping("/mphonesarchived")
    public ResponseEntity<String> sendModelPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        phoneModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
        
    }
    
    
}
