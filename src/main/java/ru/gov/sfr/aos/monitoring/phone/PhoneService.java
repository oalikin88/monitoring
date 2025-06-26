/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.phone;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.exceptions.DublicateInventoryNumberException;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.svtobject.SvtObjService;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class PhoneService extends SvtObjService<Phone, PhoneRepo, SvtDTO>{

    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private PhoneModelRepo phoneModelRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private PhoneRepo phoneRepo;
    
    
    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
          
            
        if(phoneRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
           throw new ObjectAlreadyExists("Телефон с таким серийным номером уже есть в базе данных");
        } else if(!dto.isIgnoreCheck() && phoneRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())){
            throw new DublicateInventoryNumberException("Телефон с таким инвентарным номером уже есть в базе данных.  \n Вы уверены, что хотите сохранить?");
        }else {
            Phone phone = new Phone();
            Place place = null;
            PhoneModel phoneModel = null;
            
            place = placeRepo.findById(dto.getPlaceId()).get();
            
            if(null == dto.getModelId()) {
                if(phoneModelRepo.existsByModelIgnoreCase("не указано")) {
                    phoneModel = phoneModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    phoneModel = new PhoneModel("не указано");
                } 
            } else {
                phoneModel = phoneModelRepo.findById(dto.getModelId()).get();
            }
            
            switch (dto.getStatus()) {
            case "REPAIR":
                phone.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                phone.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                phone.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                phone.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                phone.setStatus(Status.DEFECTIVE);
                break;
        }
        
        phone.setInventaryNumber(dto.getInventaryNumber());
        phone.setSerialNumber(dto.getSerialNumber());
        phone.setYearCreated(dto.getYearCreated());
        phone.setDateExploitationBegin(dto.getDateExploitationBegin());
        phone.setPhoneNumber(dto.getPhoneNumber());
        phone.setPlace(place);
        phone.setPhoneModel(phoneModel);
        phone.setNameFromeOneC(dto.getNameFromOneC());
        phone.setNumberRoom(dto.getNumberRoom());
        Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(phone);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(phone)));
                contract.setContractNumber("00000000");
                
            }
        phone.setContract(contract);
        phoneRepo.save(phone);
    }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        Phone phoneFromDB = phoneRepo.findById(dto.getId()).get();
        Place placeFromDto = placeRepo.findById(dto.getPlaceId()).get();
        PhoneModel phoneModel = null;
        
        if(null == dto.getModelId()) {
                if(phoneModelRepo.existsByModelIgnoreCase("не указано")) {
                    phoneModel = phoneModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    phoneModel = new PhoneModel("не указано");
                } 
            } else {
                phoneModel = phoneModelRepo.findById(dto.getModelId()).get();
            }
        phoneFromDB.setDateExploitationBegin(dto.getDateExploitationBegin());
        if (phoneRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
            List<Phone> findByInventaryNumberIgnoreCase = phoneRepo.findByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim());
            for (Phone phone : findByInventaryNumberIgnoreCase) {
                if (!dto.isIgnoreCheck() && phone.getId() != dto.getId()) {
                    throw new DublicateInventoryNumberException("Телефон с таким инвентарным номером уже есть в базе данных.  \n Вы уверены, что хотите сохранить?");
                }
            }
        }
        phoneFromDB.setInventaryNumber(dto.getInventaryNumber().trim());
        
        
        if (phoneRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
            List<Phone> checkSerial = phoneRepo.findBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim());
            for (Phone phone : checkSerial) {
                if (phone.getId() != dto.getId()) {
                    throw new ObjectAlreadyExists("Телефон с таким серийным номером уже есть в базе данных");
                }
            }
        }
        phoneFromDB.setSerialNumber(dto.getSerialNumber().trim());
        
        
        phoneFromDB.setPhoneNumber(dto.getPhoneNumber());
        phoneFromDB.setPlace(placeFromDto);
        phoneFromDB.setNameFromeOneC(dto.getNameFromOneC());
        phoneFromDB.setNumberRoom(dto.getNumberRoom());
        switch (dto.getStatus()) {
            case "REPAIR":
                phoneFromDB.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                phoneFromDB.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                phoneFromDB.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                phoneFromDB.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                phoneFromDB.setStatus(Status.DEFECTIVE);
                break;
        }
        phoneFromDB.setYearCreated(dto.getYearCreated());
        phoneFromDB.setPhoneModel(phoneModel);
        phoneRepo.save(phoneFromDB);
    }
    
    
    public List<Phone> getPhonesByFilter(FilterDto dto) {

        List<Phone> result = phoneRepo.findDevicesByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }
    
    
    
            public Map<Location, List<Phone>> getPhonesByPlaceAndFilter(List<Phone> input) {
        Map<Location, List<Phone>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Phone el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
          public Map<Location, List<Phone>> getPhonesByPlaceTypeAndFilter(PlaceType placeType, List<Phone> input) {
        Map<Location, List<Phone>> collect = (Map<Location, List<Phone>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Phone el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
    
}
