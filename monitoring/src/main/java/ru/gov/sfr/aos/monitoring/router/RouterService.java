/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.router;

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
import ru.gov.sfr.aos.monitoring.switchhub.SvtSwitchHubDTO;
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.svtobject.SvtObjService;

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
    
    
    public boolean checkRouter(SvtSwitchHubDTO dto) {
        if(routerRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
            return false;
        } else {
            return true;
        }
    }
    
    
    @Override
    public void createSvtObj(SvtSwitchHubDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        RouterModel routerModel = null;
            if(routerRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
                throw new ObjectAlreadyExists("Маршрутизатор с таким серийным номером уже есть в базе данных.");
    }else if(!dto.isIgnoreCheck() && routerRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())){
        throw new DublicateInventoryNumberException("Маршрутизатор с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
    } else {
            
            Router router = new Router();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            
            if(null == dto.getModelId()) {
                if(routerModelRepo.existsByModelIgnoreCase("не указано")) {
                    routerModel = routerModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    routerModel = new RouterModel();
                    routerModel.setModel("не указано");
                }
            } else {
                routerModel = routerModelRepo.findById(dto.getModelId()).get();
            }
            
            router.setInventaryNumber(dto.getInventaryNumber());
            router.setSerialNumber(dto.getSerialNumber());
            router.setPlace(place);
            router.setYearCreated(dto.getYearCreated());
            router.setIpAdressInner(dto.getIpAdressInner());
            router.setIpAdressOuter(dto.getIpAdressOuter());
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(router);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(router)));
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
    public void updateSvtObj(SvtSwitchHubDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        Router router = routerRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        RouterModel routerModel = null;
            if(null == dto.getModelId()) {
                if(routerModelRepo.existsByModelIgnoreCase("не указано")) {
                    routerModel = routerModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    routerModel = new RouterModel();
                    routerModel.setModel("не указано");
                }
            } else {
                routerModel = routerModelRepo.findById(dto.getModelId()).get();
            }
            
            if(routerRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
                List<Router> checkInventary = routerRepo.findByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim());
               for (Router el : checkInventary) {
                if (!dto.isIgnoreCheck() && el.getId() != dto.getId()) {
                    throw new DublicateInventoryNumberException("Маршрутизатор с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
                }
            }
        }
                router.setInventaryNumber(dto.getInventaryNumber().trim());
            
            
            if(routerRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
                List<Router> checkSerial = routerRepo.findBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim());
                for(Router el : checkSerial) {
                    if(el.getId() != dto.getId()) {
                    throw new ObjectAlreadyExists("Маршрутизатор с таким серийным номером уже есть в базе данных");
                }
                }
            } 
            router.setSerialNumber(dto.getSerialNumber().trim());
            
            
            router.setYearCreated(dto.getYearCreated());
            router.setPlace(place);
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(router);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(router)));
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
             router.setIpAdressInner(dto.getIpAdressInner());
             router.setIpAdressOuter(dto.getIpAdressOuter());
             routerRepo.save(router);
    }
    
    
    public List<Router> getRouterByFilter(FilterDto dto) {

        List<Router> result = routerRepo.findRouterByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }

    public Map<Location, List<Router>> getRouterByPlaceAndFilter(List<Router> input) {
        Map<Location, List<Router>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Router el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }

    public Map<Location, List<Router>> getRouterByPlaceTypeAndFilter(PlaceType placeType, List<Router> input) {
        Map<Location, List<Router>> collect = (Map<Location, List<Router>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Router el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
    
}
