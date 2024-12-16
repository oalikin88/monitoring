/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.entities.PhoneModel;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PhoneModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.PhoneRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

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
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
          
            
        if(phoneRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
           throw new ObjectAlreadyExists("Телефон с таким серийным номером уже есть в базе данных");
        } else if(phoneRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())){
            throw new ObjectAlreadyExists("Телефон с таким инвентарным номером уже есть в базе данных");
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
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(phone);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(phone)));
                contract.setContractNumber("00000000");
                
            }
        phone.setContract(contract);
        phoneRepo.save(phone);
    }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
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
        if(phoneRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())) {
            Phone checkInventary = phoneRepo.findByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim()).get(0);
            if(checkInventary.getId() != dto.getId()) {
                throw new ObjectAlreadyExists("Телефон с таким инвентарным номером уже есть в базе данных");
            } else {
                phoneFromDB.setInventaryNumber(dto.getInventaryNumber().trim());
            }
        } else {
            phoneFromDB.setInventaryNumber(dto.getInventaryNumber().trim());
        }
        
        if(phoneRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
            Phone checkSerial = phoneRepo.findBySerialNumberIgnoreCase(dto.getSerialNumber().trim()).get(0);
            if(checkSerial.getId() != dto.getId()) {
                throw new ObjectAlreadyExists("Телефон с таким серийным номером уже есть в базе данных");
            } else {
                phoneFromDB.setSerialNumber(dto.getSerialNumber().trim());
            }
        } else {
            phoneFromDB.setSerialNumber(dto.getSerialNumber().trim());
        }
        
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
      
      
        
        List<Phone> result = phoneRepo.findPhonesByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
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
