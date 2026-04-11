package ru.gov.sfr.aos.monitoring.asuo.hub;

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
 * @author Alikin Oleg
 */

@Service
public class HubService extends SvtObjService<Hub, HubRepo, HubDto> {
    
    @Autowired
    private HubRepo hubRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private HubModelRepo hubModelRepo;

    @Override
    public void createSvtObj(HubDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        
        if (hubRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
            throw new ObjectAlreadyExists("Коммутатор с таким серийным номером уже есть в базе данных");
        }else if(!dto.isIgnoreCheck() && hubRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())){
            throw new DublicateInventoryNumberException("Коммутатор с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
        } else {
            Hub hub = new Hub();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            HubModel hubModel = null;
            if(null == dto.getModelId()) {
                if(hubModelRepo.existsByModelIgnoreCase("не указано")) {
                    hubModel = hubModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    hubModel = new HubModel();
                    hubModel.setModel("не указано");
                }
            } else {
                hubModel = hubModelRepo.findById(dto.getModelId()).get();
            }
            
            hub.setInventaryNumber(dto.getInventaryNumber());
            hub.setSerialNumber(dto.getSerialNumber());
            hub.setPlace(place);
            hub.setYearCreated(dto.getYearCreated());
            hub.setDateExploitationBegin(dto.getDateExploitationBegin());
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(hub);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(hub)));
                contract.setContractNumber("00000000");
                
            }
            hub.setContract(contract);
            hub.setHubModel(hubModel);

             switch (dto.getStatus()) {
            case "REPAIR":
                hub.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                hub.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                hub.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                hub.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                hub.setStatus(Status.DEFECTIVE);
                break;
        }
             hub.setPortAmount(dto.getPortAmount());
             hub.setNameFromOneC(dto.getNameFromOneC());
             hubRepo.save(hub);
        }
        }
    

    @Override
    public void updateSvtObj(HubDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        
        HubModel hubModel = null;
       
        Hub hub = hubRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        
            
            if(null == dto.getModelId()) {
                if(hubModelRepo.existsByModelIgnoreCase("не указано")) {
                    hubModel = hubModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    hubModel = new HubModel();
                    hubModel.setModel("не указано");
                }
            } else {
                hubModel = hubModelRepo.findById(dto.getModelId()).get();
            }
            
            if(hubRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
                List<Hub> checkInventary = hubRepo.findByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim());
                for(Hub el : checkInventary) {
                    if(!dto.isIgnoreCheck() && el.getId() != dto.getId()) {
                    throw new DublicateInventoryNumberException("Коммутатор с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
                }
                }
            } 
            hub.setInventaryNumber(dto.getInventaryNumber().trim());
            
            
            if(hubRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
                List<Hub> checkSerial = hubRepo.findBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim());
                for(Hub el : checkSerial) {
                    if(el.getId() != dto.getId()) {
                    throw new ObjectAlreadyExists("Коммутатор с таким серийным номером уже есть в базе данных");
                }
                }
            } 
            
            hub.setSerialNumber(dto.getSerialNumber().trim());
            hub.setPlace(place);
            hub.setYearCreated(dto.getYearCreated());
            hub.setHubModel(hubModel);
            hub.setDateExploitationBegin(dto.getDateExploitationBegin());
             switch (dto.getStatus()) {
            case "REPAIR":
                hub.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                hub.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                hub.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                hub.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                hub.setStatus(Status.DEFECTIVE);
                break;
        }
             hub.setPortAmount(dto.getPortAmount());
             hub.setNameFromOneC(dto.getNameFromOneC());
             hubRepo.save(hub);


    }
    
     
    
    public List<Hub> getHubByFilter(FilterDto dto) {
        List<Hub> result = hubRepo.findHubByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }
    
          public Map<Location, List<Hub>> getHubByPlaceAndFilter(List<Hub> input) {
        Map<Location, List<Hub>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Hub el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
          public Map<Location, List<Hub>> getHubByPlaceTypeAndFilter(PlaceType placeType, List<Hub> input) {
        Map<Location, List<Hub>> collect = (Map<Location, List<Hub>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Hub el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }

}
