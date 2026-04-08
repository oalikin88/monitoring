/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.infomat;

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
public class InfomatService extends SvtObjService<Infomat, InfomatRepo, SvtDTO> {

    @Autowired
    private InfomatRepo infomatRepo;
    @Autowired
    private InfomatModelRepo infomatModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    
    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        InfomatModel infomatModel = null;
        if(infomatRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
                throw new ObjectAlreadyExists("Инфомат с таким серийным номером уже есть в базе данных");
    }else if(!dto.isIgnoreCheck() && infomatRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
         throw new DublicateInventoryNumberException("Инфомат с таким инвентарным номером уже есть в базе данных.  \n Вы уверены, что хотите сохранить?");
    } else {
            
            Infomat infomat = new Infomat();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            if(null == dto.getModelId()){
                if(infomatModelRepo.existsByModelIgnoreCase("не указано")) {
                    infomatModel = infomatModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    infomatModel = new InfomatModel("не указано");
                }
            } else {
                infomatModel = infomatModelRepo.findById(dto.getModelId()).get();
            }
            
            infomat.setInventaryNumber(dto.getInventaryNumber());
            infomat.setSerialNumber(dto.getSerialNumber());
            infomat.setPlace(place);
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(infomat);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(infomat)));
                contract.setContractNumber("00000000");
                
            }
            infomat.setContract(contract);
            infomat.setInfomatModel(infomatModel);
             switch (dto.getStatus()) {
            case "REPAIR":
                infomat.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                infomat.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                infomat.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                infomat.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                infomat.setStatus(Status.DEFECTIVE);
                break;
        }
             infomat.setNameFromOneC(dto.getNameFromOneC());
             infomat.setYearCreated(dto.getYearCreated());
             infomat.setNumberRoom(dto.getNumberRoom());
             infomatRepo.save(infomat);
        }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        InfomatModel infomatModel = null;
            Infomat infomat = infomatRepo.findById(dto.getId()).get();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            if(null == dto.getModelId()){
                if(infomatModelRepo.existsByModelIgnoreCase("не указано")) {
                    infomatModel = infomatModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    infomatModel = new InfomatModel("не указано");
                }
            } else {
                infomatModel = infomatModelRepo.findById(dto.getModelId()).get();
            }
            
           if (infomatRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
            List<Infomat> checkInventary = infomatRepo.findByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim());
            for (Infomat el : checkInventary) {
                if (!dto.isIgnoreCheck() && el.getId() != dto.getId()) {
                    throw new DublicateInventoryNumberException("Инфомат с таким инвентарным номером уже есть в базе данных.  \n Вы уверены, что хотите сохранить?");
                }
            }

        } 
            
            infomat.setInventaryNumber(dto.getInventaryNumber().trim());
            
            
            if(infomatRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
                List<Infomat> checkSerial = infomatRepo.findBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim());
                for(Infomat el : checkSerial) {
                    if(el.getId() != dto.getId()) {
                    throw new ObjectAlreadyExists("Инфомат с таким серийным номером уже есть в базе данных");
                }
                }
                 
            } 
            infomat.setSerialNumber(dto.getSerialNumber().trim());
            
            
            infomat.setPlace(place);
            
            infomat.setInfomatModel(infomatModel);
             switch (dto.getStatus()) {
            case "REPAIR":
                infomat.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                infomat.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                infomat.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                infomat.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                infomat.setStatus(Status.DEFECTIVE);
                break;
        }
             infomat.setNameFromOneC(dto.getNameFromOneC());
             infomat.setYearCreated(dto.getYearCreated());
             infomat.setNumberRoom(dto.getNumberRoom());
             infomatRepo.save(infomat);
    }
    
    
        public List<Infomat> getInfomatByFilter(FilterDto dto) {

        List<Infomat> result = infomatRepo.findInfomatByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }

    public Map<Location, List<Infomat>> getInfomatByPlaceAndFilter(List<Infomat> input) {
        Map<Location, List<Infomat>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Infomat el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }

    public Map<Location, List<Infomat>> getInfomatByPlaceTypeAndFilter(PlaceType placeType, List<Infomat> input) {
        Map<Location, List<Infomat>> collect = (Map<Location, List<Infomat>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Infomat el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
    
}
