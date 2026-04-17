package ru.gov.sfr.aos.monitoring.phone;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gov.sfr.aos.monitoring.controllers.SvtViewController;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.exceptions.DublicateInventoryNumberException;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.models.FilterRequest;

/**
 *
 * @author Alikin Oleg
 */

@Controller
public class PhoneViewController {
    
@Autowired
private PhoneModelMapper phoneModelMapper; 
@Autowired
private PhoneService phoneService;   
@Autowired
private PhoneModelService phoneModelService;
@Autowired
private PhoneTreeService phoneTreeService;
    
     //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/phones")
    public String getPhones(Model model, FilterRequest filterRequest) {
        

        List<LocationByTreeDto> treeSvtDtoByEmployee = phoneTreeService.getTree(filterRequest, PlaceType.EMPLOYEE);
        List<LocationByTreeDto> treeSvtDtoByStorage = phoneTreeService.getTree(filterRequest, PlaceType.STORAGE);
        
        int amount = SvtViewController.getAmountDevices(treeSvtDtoByEmployee, treeSvtDtoByStorage);

        model.addAttribute("searchFIO", filterRequest.getUsername());
        model.addAttribute("searchInventary", filterRequest.getInventaryNumber());
        model.addAttribute("searchSerial", filterRequest.getSerialNumber());
        model.addAttribute("filterDateBegin", filterRequest.getYearCreatedFrom());
        model.addAttribute("filterDateEnd", filterRequest.getYearCreatedTo());
        model.addAttribute("filterStatus", filterRequest.getStatus());
        model.addAttribute("filterModel", filterRequest.getModel());
        model.addAttribute("filterLocation", filterRequest.getLocation());
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
