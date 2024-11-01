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
import ru.gov.sfr.aos.monitoring.dictionaries.SwitchHubType;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.SwitchHub;
import ru.gov.sfr.aos.monitoring.entities.SwitchHubModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.SwitchHubModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.SwitchHubRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class SwitchHubService extends SvtObjService<SwitchHub, SwitchHubRepo, SvtSwitchHubDTO> {
    @Autowired
    private SwitchHubRepo switchHubRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private SwitchHubModelRepo switchHubModelRepo;
    

    @Override
    public void createSvtObj(SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
        SwitchHubType switchHubType = null;
        String switchHubTypeRus = null;
        switch (dto.getSwitchHubType()) {
            case "SWITCH":
                switchHubTypeRus = "коммутатор";
                switchHubType = SwitchHubType.SWITCH;
                break;
            case "HUB":
                switchHubTypeRus = "концентратор";
                switchHubType = SwitchHubType.HUB;
                break;
        }
        
            if(switchHubRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
                throw new ObjectAlreadyExists(switchHubTypeRus + " с таким серийным номером уже есть в базе данных");
            
    } else {
            
            SwitchHub switchHub = new SwitchHub();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            SwitchHubModel switchHubModel = switchHubModelRepo.findById(dto.getModelId()).get();
            switchHub.setInventaryNumber(dto.getInventaryNumber());
            switchHub.setSerialNumber(dto.getSerialNumber());
            switchHub.setPlace(place);
            switchHub.setYearCreated(dto.getYearCreated());
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(switchHub);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(switchHub)));
                contract.setContractNumber("00000000");
                
            }
            switchHub.setContract(contract);
            switchHub.setSwitchHubModel(switchHubModel);
            switchHub.setSwitchHubType(switchHubType);
             switch (dto.getStatus()) {
            case "REPAIR":
                switchHub.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                switchHub.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                switchHub.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                switchHub.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                switchHub.setStatus(Status.DEFECTIVE);
                break;
        }
             switchHub.setPortAmount(dto.getPortAmount());
             switchHub.setNumberRoom(dto.getNumberRoom());
             switchHub.setNameFromOneC(dto.getNameFromOneC());
             switchHubRepo.save(switchHub);
        }
        
    }

    @Override
    public void updateSvtObj(SvtSwitchHubDTO dto) {
        SwitchHub switchHub = switchHubRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
            SwitchHubModel switchHubModel = switchHubModelRepo.findById(dto.getModelId()).get();
            switchHub.setInventaryNumber(dto.getInventaryNumber());
            switchHub.setSerialNumber(dto.getSerialNumber());
            switchHub.setPlace(place);
            switchHub.setYearCreated(dto.getYearCreated());
            switchHub.setSwitchHubModel(switchHubModel);
            SwitchHubType switchHubType = null;
            switch (dto.getSwitchHubType()) {
            case "SWITCH":
                switchHubType = SwitchHubType.SWITCH;
                break;
            case "HUB":
                switchHubType = SwitchHubType.HUB;
                break;
        }
            switchHub.setSwitchHubType(switchHubType);
             switch (dto.getStatus()) {
            case "REPAIR":
                switchHub.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                switchHub.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                switchHub.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                switchHub.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                switchHub.setStatus(Status.DEFECTIVE);
                break;
        }
             switchHub.setPortAmount(dto.getPortAmount());
             switchHub.setNumberRoom(dto.getNumberRoom());
             switchHub.setNameFromOneC(dto.getNameFromOneC());
             switchHubRepo.save(switchHub);
    }
    
    public List<SwitchHub> getAllSwitch() {
        List<SwitchHub> switches = switchHubRepo.findBySwitchHubType(SwitchHubType.SWITCH);
        return switches;
    }
    
            public List<SwitchHub> getSwitchHubByFilter(FilterDto dto) {
      
      
        
        List<SwitchHub> result = switchHubRepo.findSwitchHubByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo());
        return result;
    }
    
    
    
            public Map<Location, List<SwitchHub>> getSwitchHubByPlaceAndFilter(List<SwitchHub> input) {
        Map<Location, List<SwitchHub>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((SwitchHub el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
          public Map<Location, List<SwitchHub>> getSwitchHubByPlaceTypeAndFilter(PlaceType placeType, List<SwitchHub> input) {
        Map<Location, List<SwitchHub>> collect = (Map<Location, List<SwitchHub>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((SwitchHub el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
    
}
