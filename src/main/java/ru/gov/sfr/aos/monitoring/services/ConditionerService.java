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
import ru.gov.sfr.aos.monitoring.dictionaries.ConditionerType;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.Conditioner;
import ru.gov.sfr.aos.monitoring.entities.ConditionerModel;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.SvtConditionerDTO;
import ru.gov.sfr.aos.monitoring.repositories.ConditionerModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.ConditionerRepo;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

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
    public void createSvtObj(SvtConditionerDTO dto) {
        if (null != dto.getId()) {
            if (conditionerRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
                System.out.println("такой кондиционер уже есть в базе данных");
            }
        } else {
            Conditioner conditioner = new Conditioner();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            ConditionerModel conditionerModel = conditionerModelRepo.findById(dto.getModelId()).get();
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
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(conditioner);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(conditioner)));
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
    public void updateSvtObj(SvtConditionerDTO dto) {
        Conditioner conditioner = conditionerRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        ConditionerModel conditionerModel = conditionerModelRepo.findById(dto.getModelId()).get();
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

        List<Conditioner> result = conditionerRepo.findConditionerByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo());
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
