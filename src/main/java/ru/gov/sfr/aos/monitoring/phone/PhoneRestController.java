package ru.gov.sfr.aos.monitoring.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class PhoneRestController {
    
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private PhoneModelService phoneModelService;
    @Autowired
    private PhoneManufacturerService phoneManufacturerService;
    @Autowired
    private PhoneManufacturerMapper phoneManufacturerMapper;
    @Autowired 
    private PhoneModelMapper phoneModelMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;

        @GetMapping("/modphones")
    public List<SvtModelDto> getModelPhones() {
        List<PhoneModel> allModels = phoneModelService.getAllActualModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(PhoneModel el : allModels) {
            SvtModelDto dto = phoneModelMapper.getDtoForSelectize(el);
            out.add(dto);
        }
        return out;
    }
    
        @GetMapping("/getphone")
    public SvtDTO getPhoneById(Long phoneId) {

        Phone phone = phoneService.getById(phoneId);
        SvtDTO phoneDto = phoneMapper.getDto(phone);

        return phoneDto;
    }
    
    @PostMapping("/save-phone-manufacturer")
    public ResponseEntity<?> savePhoneManufacturer(String name) throws ObjectAlreadyExists {
            PhoneManufacturer savedManufacturer = null;
        PhoneManufacturer potencialManufacturer = new PhoneManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = phoneManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        PhoneManufacturerDto dto = phoneManufacturerMapper.getDto(savedManufacturer);
        
        return ResponseEntity.ok(dto);
    }
    
     @GetMapping("/get-phone-manufacturers")
    public List<PhoneManufacturerDto> getPhoneManufacturers() {
        List<PhoneManufacturer> allManufacturers = phoneManufacturerService.getAllManufacturers();
        List<PhoneManufacturerDto> out = phoneManufacturerMapper.getListDtoes(allManufacturers);
        return out;
    }
    
    @GetMapping("/get-phone-modelsby-manufacturer")
    public List<SvtModelDto> getPhoneModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        PhoneManufacturer manufacturer = phoneManufacturerService.getManufacturer(id);
        Set<PhoneModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(PhoneModel model : upsListModel) {
            SvtModelDto modeDto = svtModelMapper.getPhoneModelDto(model);
            out.add(modeDto);
        }
        return out;
    }
}
