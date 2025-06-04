/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.conditioner;

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
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.svtobject.SvtObjService;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class ConditionerService extends SvtObjService<Conditioner, ConditionerRepo, SvtConditionerDTO> {

    @Autowired
    private ConditionerRepo conditionerRepo;
    @Autowired
    private ConditionerModelRepo conditionerModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;

    @Override
    public void createSvtObj(SvtConditionerDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        ConditionerModel conditionerModel = null;
        
            if (!dto.isIgnoreCheck() && conditionerRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
                throw new ObjectAlreadyExists("Кондиционер с таким серийным номеров уже есть в базе данных");
            } else if(!dto.isIgnoreCheck() && conditionerRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
                throw new DublicateInventoryNumberException("Кондиционер с таким инвентарным номеров уже есть в базе данных.  \n Вы уверены, что хотите сохранить?");
            
        } else {
            Conditioner conditioner = new Conditioner();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            
            if(null == dto.getModelId()) {
                if(conditionerModelRepo.existsByModelIgnoreCase("не указано")) {
                    conditionerModel = conditionerModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    conditionerModel = new ConditionerModel();
                    conditionerModel.setModel("не указано");
                }
            } else {
                conditionerModel = conditionerModelRepo.findById(dto.getModelId()).get();
            }
            
            conditioner.setPlace(place);
            conditioner.setConditionerModel(conditionerModel);
            conditioner.setSerialNumber(dto.getSerialNumber());
            conditioner.setInventaryNumber(dto.getInventaryNumber());
            conditioner.setYearCreated(dto.getYearCreated());
            conditioner.setDescription(dto.getDescription());
            conditioner.setNameFromOneC(dto.getNameFromOneC());
            conditioner.setSplitSystem(dto.isSplitSystem());
            conditioner.setWinterKit(dto.isWinterKit());
            conditioner.setHavePomp(dto.isHavePomp());
            conditioner.setPrice(dto.getPrice());
            Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(conditioner);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(conditioner)));
                contract.setContractNumber("00000000");

            }
            conditioner.setContract(contract);
            switch (dto.getStatus()) {
                case "REPAIR":
                    conditioner.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    conditioner.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    conditioner.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    conditioner.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    conditioner.setStatus(Status.DEFECTIVE);
                    break;
            }
            conditioner.setNumberRoom(dto.getNumberRoom());
            switch (dto.getConditionerType()) {
                case "WINDOW":
                    conditioner.setConditionerType(ConditionerType.WINDOW);
                    break;
                case "WALL":
                    conditioner.setConditionerType(ConditionerType.WALL);
                    break;
                case "CEILING":
                    conditioner.setConditionerType(ConditionerType.CEILING);
                    break;
                case "FLOOR":
                    conditioner.setConditionerType(ConditionerType.FLOOR);
                    break;
            }
            conditionerRepo.save(conditioner);
        }
    }

    @Override
    public void updateSvtObj(SvtConditionerDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        ConditionerModel conditionerModel = null;
        Conditioner conditioner = conditionerRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        
        if(null == dto.getModelId()) {
                if(conditionerModelRepo.existsByModelIgnoreCase("не указано")) {
                    conditionerModel = conditionerModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    conditionerModel = new ConditionerModel();
                    conditionerModel.setModel("не указано");
                }
            } else {
                conditionerModel = conditionerModelRepo.findById(dto.getModelId()).get();
            }
        
        conditioner.setPlace(place);
        conditioner.setConditionerModel(conditionerModel);
        
        if(conditionerRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
            List<Conditioner> checkSerial = conditionerRepo.findBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim());
            for(Conditioner el : checkSerial) {
                if(!dto.isIgnoreCheck() && el.getId() != dto.getId()) {
                throw new ObjectAlreadyExists("Кондиционер с таким серийным номеров уже есть в базе данных");
            }
            }
             
        } 
        
        conditioner.setSerialNumber(dto.getSerialNumber().trim());
        
        
        if (conditionerRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
            List<Conditioner> checkInventary = conditionerRepo.findByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim());
            for (Conditioner el : checkInventary) {
                if (!dto.isIgnoreCheck() && el.getId() != dto.getId()) {
                    throw new DublicateInventoryNumberException("Кондиционер с таким инвентарным номеров уже есть в базе данных.  \n Вы уверены, что хотите сохранить?");
                }
            }

        } 
        conditioner.setInventaryNumber(dto.getInventaryNumber().trim());
        
        
        conditioner.setYearCreated(dto.getYearCreated());
        conditioner.setDescription(dto.getDescription());
        conditioner.setNameFromOneC(dto.getNameFromOneC());
        conditioner.setSplitSystem(dto.isSplitSystem());
        conditioner.setWinterKit(dto.isWinterKit());
        conditioner.setHavePomp(dto.isHavePomp());
        conditioner.setPrice(dto.getPrice());

        switch (dto.getStatus()) {
            case "REPAIR":
                conditioner.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                conditioner.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                conditioner.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                conditioner.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                conditioner.setStatus(Status.DEFECTIVE);
                break;
        }
        conditioner.setNumberRoom(dto.getNumberRoom());
        switch (dto.getConditionerType()) {
            case "WINDOW":
                conditioner.setConditionerType(ConditionerType.WINDOW);
                break;
            case "WALL":
                conditioner.setConditionerType(ConditionerType.WALL);
                break;
            case "CEILING":
                conditioner.setConditionerType(ConditionerType.CEILING);
                break;
            case "FLOOR":
                conditioner.setConditionerType(ConditionerType.FLOOR);
                break;
        }
        conditionerRepo.save(conditioner);
    }

    
    public List<Conditioner> getConditionerByFilter(FilterDto dto) {

        List<Conditioner> result = conditionerRepo.findConditionerByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }

    public Map<Location, List<Conditioner>> getConditionerByPlaceAndFilter(List<Conditioner> input) {
        Map<Location, List<Conditioner>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Conditioner el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }

    public Map<Location, List<Conditioner>> getConditionerByPlaceTypeAndFilter(PlaceType placeType, List<Conditioner> input) {
        Map<Location, List<Conditioner>> collect = (Map<Location, List<Conditioner>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Conditioner el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
    
    
}
