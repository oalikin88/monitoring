/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Router;
import ru.gov.sfr.aos.monitoring.entities.RouterModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.RouterModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.RouterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class RouterService extends SvtObjService<Router, RouterRepo, SvtSwitchHubDTO> {
    @Autowired
    private RouterRepo routerRepo;
    @Autowired
    private RouterModelRepo routerModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    
    
    @Override
    public void createSvtObj(SvtSwitchHubDTO dto) throws ObjectAlreadyExists {
            if(routerRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
                throw new ObjectAlreadyExists("АТС с таким серийным номером уже есть в базе данных");
    } else {
            
            Router router = new Router();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            RouterModel routerModel = routerModelRepo.findById(dto.getModelId()).get();
            router.setInventaryNumber(dto.getInventaryNumber());
            router.setSerialNumber(dto.getSerialNumber());
            router.setPlace(place);
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(router);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(router)));
                contract.setContractNumber("00000000");
                
            }
            router.setContract(contract);
            router.setRouterModel(routerModel);
             switch (dto.getStatus()) {
            case "REPAIR":
                router.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                router.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                router.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                router.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                router.setStatus(Status.DEFECTIVE);
                break;
        }
             router.setPortAmount(dto.getPortAmount());
             router.setNumberRoom(dto.getNumberRoom());
             router.setNameFromOneC(dto.getNameFromOneC());
             routerRepo.save(router);
        }
    }

    @Override
    public void updateSvtObj(SvtSwitchHubDTO dto) {
        Router router = routerRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
            RouterModel routerModel = routerModelRepo.findById(dto.getModelId()).get();
            router.setInventaryNumber(dto.getInventaryNumber());
            router.setSerialNumber(dto.getSerialNumber());
            router.setPlace(place);
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(router);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(router)));
                contract.setContractNumber("00000000");
                
            }
            router.setContract(contract);
            router.setRouterModel(routerModel);
             switch (dto.getStatus()) {
            case "REPAIR":
                router.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                router.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                router.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                router.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                router.setStatus(Status.DEFECTIVE);
                break;
        }
             router.setPortAmount(dto.getPortAmount());
             router.setNumberRoom(dto.getNumberRoom());
             router.setNameFromOneC(dto.getNameFromOneC());
             routerRepo.save(router);
    }
    
}
