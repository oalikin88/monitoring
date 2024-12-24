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
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.UpsModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.UpsRepo;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class UpsService extends SvtObjService<Ups, UpsRepo, SvtDTO>{
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private UpsModelRepo upsModelRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private UpsRepo upsRepo;



    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {

        if(upsRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
            throw new ObjectAlreadyExists("ИБП с таким серийным номером уже есть в базе данных");
        } else if(upsRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())) {
            throw new ObjectAlreadyExists("ИБП с таким инвентарным номером уже есть в базе данных");
        } else {
            Ups ups = new Ups();
            Place place = null;
            UpsModel upsModel = null;
            place = placeRepo.findById(dto.getPlaceId()).get();     
                
             if (null == dto.getModelId()) {
                if (upsModelRepo.existsByModelIgnoreCase("не указано")) {
                    upsModel = upsModelRepo.findByModelIgnoreCase("не указано").get(0);
                    
                } 
            } else {
                upsModel = upsModelRepo.findById(dto.getModelId()).get();
                
            }
            
            
            switch (dto.getStatus()) {
            case "REPAIR":
                ups.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                ups.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                ups.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                ups.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                ups.setStatus(Status.DEFECTIVE);
                break;
        }
            
            ups.setInventaryNumber(dto.getInventaryNumber());
            ups.setSerialNumber(dto.getSerialNumber());
            ups.setYearCreated(dto.getYearCreated());
            ups.setYearReplacement(dto.getYearReplacement());
            ups.setYearCreated(dto.getYearCreated());
            ups.setPlace(place);
            ups.setUpsModel(upsModel);
            ups.setDateExploitationBegin(dto.getDateExploitationBegin());
            ups.setNumberRoom(dto.getNumberRoom());
            ups.setNameFromOneC(dto.getNameFromOneC());
            
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(ups);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(ups)));
                contract.setContractNumber("00000000");
            }
            ups.setContract(contract);
            upsRepo.save(ups);
        }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
        Ups upsFromDB = upsRepo.findById(dto.getId()).get();
        Place placeFromDB = placeRepo.findById(dto.getPlaceId()).get();
        UpsModel upsModel = null;

        
            
             if (null == dto.getModelId()) {
                if (upsModelRepo.existsByModelIgnoreCase("не указано")) {
                    upsModel = upsModelRepo.findByModelIgnoreCase("не указано").get(0);
                    
                } 
            } else {
                upsModel = upsModelRepo.findById(dto.getModelId()).get();
                
            }
       
        upsFromDB.setYearCreated(dto.getYearCreated());
        if(upsRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())) {
            Ups checkInventary = upsRepo.findByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim()).get(0);
            if(checkInventary.getId() != dto.getId()) {
                throw new ObjectAlreadyExists("ИБП с таким инвентарным номером уже есть в базе данных");
            } else {
                upsFromDB.setInventaryNumber(dto.getInventaryNumber().trim());
            }
        } else {
            upsFromDB.setInventaryNumber(dto.getInventaryNumber().trim());
        }
        
        if(upsRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
            Ups checkSerial = upsRepo.findBySerialNumberIgnoreCase(dto.getSerialNumber().trim()).get(0);
            if(checkSerial.getId() != dto.getId()) {
                throw new ObjectAlreadyExists("ИБП с таким серийным номером уже есть в базе данных");
            }else {
                upsFromDB.setSerialNumber(dto.getSerialNumber().trim());
            }
        } else {
            upsFromDB.setSerialNumber(dto.getSerialNumber().trim());
        }
        
        upsFromDB.setYearReplacement(dto.getYearReplacement());
        upsFromDB.setPlace(placeFromDB);
        upsFromDB.setDateExploitationBegin(dto.getDateExploitationBegin());
        switch (dto.getStatus()) {
            case "REPAIR":
                upsFromDB.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                upsFromDB.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                upsFromDB.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                upsFromDB.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                upsFromDB.setStatus(Status.DEFECTIVE);
                break;
        }
        upsFromDB.setUpsModel(upsModel);
        upsFromDB.setNumberRoom(dto.getNumberRoom());
        upsFromDB.setNameFromOneC(dto.getNameFromOneC());
        upsRepo.save(upsFromDB);
        
    }
    
    
        public List<Ups> getUpsByFilter(FilterDto dto) {
      
      
        
        List<Ups> result = upsRepo.findUpsByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }
    
    
    
            public Map<Location, List<Ups>> getUpsByPlaceAndFilter(List<Ups> input) {
        Map<Location, List<Ups>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Ups el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
          public Map<Location, List<Ups>> getUpsByPlaceTypeAndFilter(PlaceType placeType, List<Ups> input) {
        Map<Location, List<Ups>> collect = (Map<Location, List<Ups>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Ups el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
    
    
}
