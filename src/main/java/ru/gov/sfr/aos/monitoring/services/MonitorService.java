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
import ru.gov.sfr.aos.monitoring.entities.Monitor;
import ru.gov.sfr.aos.monitoring.entities.MonitorModel;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.enums.BaseType;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.MonitorModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.MonitorRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;



/**
 *
 * @author 041AlikinOS
 * @param <MonitorRepo>
 * @param <Monitor>
 */
@Service
public class MonitorService extends SvtObjService <Monitor, MonitorRepo, SvtDTO> {
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private MonitorModelRepo monitorModelRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private MonitorRepo monitorRepo;

    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {

        if(monitorRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
            throw new ObjectAlreadyExists("Монитор с таким серийным номером уже есть в базе данных");
        } else if(monitorRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())){
            throw new ObjectAlreadyExists("Монитор с таким инвентарным номером уже есть в базе данных");
        }else {
            Monitor monitor = new Monitor();
            Place place = null;
             MonitorModel monitorModel = null;
            
            place = placeRepo.findById(dto.getPlaceId()).get();
            
            if (null == dto.getModelId()) {
                if (monitorModelRepo.existsByModelIgnoreCase("не указано")) {
                    monitorModel = monitorModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    monitorModel = new MonitorModel("не указано");
                }
            } else {
                monitorModel = monitorModelRepo.findById(dto.getModelId()).get();
            }
            
            switch (dto.getStatus()) {
            case "REPAIR":
                monitor.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                monitor.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                monitor.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                monitor.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                monitor.setStatus(Status.DEFECTIVE);
                break;
            default:
                monitor.setStatus(Status.OK);
                break;
        }
           
        monitor.setNumberRoom(dto.getNumberRoom());
        monitor.setInventaryNumber(dto.getInventaryNumber().trim());
        monitor.setSerialNumber(dto.getSerialNumber().trim());
        monitor.setYearCreated(dto.getYearCreated());
        monitor.setDateExploitationBegin(dto.getDateExploitationBegin());
        monitor.setNameFromeOneC(dto.getNameFromOneC());
        monitor.setPlace(place);
        monitor.setMonitorModel(monitorModel);
        monitor.setNameFromeOneC(dto.getNameFromOneC());
             switch (dto.getBaseType()) {
                 case "ARM":
                    monitor.setBaseType(BaseType.ARM);    
                    break;
                 case "SINGLE":
                    monitor.setBaseType(BaseType.SINGLE);    
                    break;
             }
        
        Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(monitor);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(monitor)));
                contract.setContractNumber("00000000");
                
            }
        monitor.setContract(contract);
        monitorRepo.save(monitor);
    }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
        Monitor monitorFromDB = monitorRepo.findById(dto.getId()).get();
        Place placeFromDto = placeRepo.findById(dto.getPlaceId()).get();
         MonitorModel monitorModelFromDto = null;
        if(null == dto.getModelId()) {
            if(monitorModelRepo.existsByModelIgnoreCase("не указано")) {
                monitorModelFromDto = monitorModelRepo.findByModelIgnoreCase("не указано").get(0);
            } else {
                monitorModelFromDto = new MonitorModel();
                monitorModelFromDto.setModel("не указано");
            }
        } else {
            monitorModelFromDto = monitorModelRepo.findById(dto.getModelId()).get();
        }
        
        monitorFromDB.setDateExploitationBegin(dto.getDateExploitationBegin());
        
        if (monitorRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())) {
            List<Monitor> checkInventary = monitorRepo.findByInventaryNumberIgnoreCase(dto.getInventaryNumber());
            for (Monitor el : checkInventary) {
                if (el.getId() != dto.getId()) {
                    throw new ObjectAlreadyExists("Монитор с таким инвентарным номером уже есть в базе данных");
                }
            }
        }
        
        monitorFromDB.setInventaryNumber(dto.getInventaryNumber().trim());
        
    
        if(monitorRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
            List<Monitor> checkSerial = monitorRepo.findBySerialNumberIgnoreCase(dto.getSerialNumber().trim());
            for(Monitor el : checkSerial) {
                if(el.getId() != dto.getId()) {
                throw new ObjectAlreadyExists("Монитор с таким серийным номером уже есть в базе данных");
            }
            }
        } 
        monitorFromDB.setSerialNumber(dto.getSerialNumber().trim());
        
        
        monitorFromDB.setNameFromeOneC(dto.getNameFromOneC());
        monitorFromDB.setPlace(placeFromDto);
        monitorFromDB.setMonitorModel(monitorModelFromDto);
        monitorFromDB.setNumberRoom(dto.getNumberRoom());
        switch (dto.getStatus()) {
            case "REPAIR":
                monitorFromDB.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                monitorFromDB.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                monitorFromDB.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                monitorFromDB.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                monitorFromDB.setStatus(Status.DEFECTIVE);
                break;
            default:
                monitorFromDB.setStatus(Status.OK);
                break;
        }
        switch (dto.getBaseType()) {
            case "ARM":
                monitorFromDB.setBaseType(BaseType.ARM);
                break;
            case "SINGLE":
                monitorFromDB.setBaseType(BaseType.SINGLE);
                break;
        }
        monitorFromDB.setYearCreated(dto.getYearCreated());
        monitorRepo.save(monitorFromDB);
        
    }
        
          public List<Monitor> getMonitorByFilter(FilterDto dto) {
      
      
        
        List<Monitor> result = monitorRepo.findMonitorByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }
    
    
    
            public Map<Location, List<Monitor>> getMonitorByPlaceAndFilter(List<Monitor> input) {
        Map<Location, List<Monitor>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Monitor el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
          public Map<Location, List<Monitor>> getMonitorByPlaceTypeAndFilter(PlaceType placeType, List<Monitor> input) {
        Map<Location, List<Monitor>> collect = (Map<Location, List<Monitor>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Monitor el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
    
    
    }
    
  
 

