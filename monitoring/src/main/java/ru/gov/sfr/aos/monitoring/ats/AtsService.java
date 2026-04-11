/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ats;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.OuterConnectionType;
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
public class AtsService extends SvtObjService <Ats, AtsRepo, SvtAtsDTO> {
    @Autowired
    private AtsRepo atsRepo;
    @Autowired
    private AtsModelRepo atsModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    
    
    @Override
    public void createSvtObj(SvtAtsDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
    AtsModel atsModel = null;
    
            if(atsRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
                 throw new ObjectAlreadyExists("Маршрутизатор с таким серийным номером уже есть в базе данных");
            
    }else if(!dto.isIgnoreCheck() && atsRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())){
        throw new DublicateInventoryNumberException("Маршрутизатор с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
    } else {
            
            Ats ats = new Ats();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            
            if(null == dto.getModelId()) {
                if(atsModelRepo.existsByModelIgnoreCase("не указано")) {
                    atsModel = atsModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    atsModel = new AtsModel();
                    atsModel.setModel("не указано");
                }
            } else {
                atsModel = atsModelRepo.findById(dto.getModelId()).get();
            }
            
            
            ats.setInventaryNumber(dto.getInventaryNumber());
            ats.setSerialNumber(dto.getSerialNumber());
            ats.setPlace(place);
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(ats);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(ats)));
                contract.setContractNumber("00000000");
                
            }
            ats.setContract(contract);
            ats.setAtsModel(atsModel);
             switch (dto.getStatus()) {
            case "REPAIR":
                ats.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                ats.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                ats.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                ats.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                ats.setStatus(Status.DEFECTIVE);
                break;
        }
             ats.setNumberRoom(dto.getNumberRoom());
             ats.setNameFromOneC(dto.getNameFromOneC());
             ats.setInnerConnectionAnalog(dto.getInnerConnectionAnalog());
             ats.setInnerConnectionIp(dto.getInnerConnectionIp());
             ats.setCityNumberAmount(dto.getCityNumberAmount());
             ats.setYearCreated(dto.getYearCreated());
             ats.setIpAdress(dto.getIpAdress());
             if(dto.getOuterConnectionType().equals("SIP")) {
                 ats.setOuterConnectionType(OuterConnectionType.SIP);
             } else {
                 ats.setOuterConnectionType(OuterConnectionType.E1);
             }
             atsRepo.save(ats);
        }
        
    }

    @Override
    public void updateSvtObj(SvtAtsDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        AtsModel atsModel = null;
        Ats ats = atsRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        
            if(null == dto.getModelId()) {
                if(atsModelRepo.existsByModelIgnoreCase("не указано")) {
                    atsModel = atsModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    atsModel = new AtsModel();
                    atsModel.setModel("не указано");
                }
            } else {
                atsModel = atsModelRepo.findById(dto.getModelId()).get();
            }
            
            if (atsRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
            List<Ats> checkInventary = atsRepo.findByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim());
                for(Ats el : checkInventary) {
                    if (!dto.isIgnoreCheck() && el.getId() != dto.getId()) {
                        throw new DublicateInventoryNumberException("АТС с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
                    }
                }
            }         
        ats.setInventaryNumber(dto.getInventaryNumber().trim());

            if (atsRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
            List<Ats> checkSerial = atsRepo.findBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim());
            for (Ats el : checkSerial) {
                if (el.getId() != dto.getId()) {
                    throw new ObjectAlreadyExists("АТС с таким серийным номером уже есть в базе данных");
                }
            }

        } 
            
            ats.setSerialNumber(dto.getSerialNumber().trim());
            
            ats.setPlace(place);
             ats.setAtsModel(atsModel);
             switch (dto.getStatus()) {
            case "REPAIR":
                ats.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                ats.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                ats.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                ats.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                ats.setStatus(Status.DEFECTIVE);
                break;
        }
             ats.setNumberRoom(dto.getNumberRoom());
             ats.setNameFromOneC(dto.getNameFromOneC());
             ats.setInnerConnectionAnalog(dto.getInnerConnectionAnalog());
             ats.setInnerConnectionIp(dto.getInnerConnectionIp());
             ats.setCityNumberAmount(dto.getCityNumberAmount());
             ats.setYearCreated(dto.getYearCreated());
             ats.setIpAdress(dto.getIpAdress());
             if(dto.getOuterConnectionType().equals("SIP")) {
                 ats.setOuterConnectionType(OuterConnectionType.SIP);
             } else {
                 ats.setOuterConnectionType(OuterConnectionType.E1);
             }
             atsRepo.save(ats);
    }
    
    
      public List<Ats> getAtsByFilter(FilterDto dto) {

        List<Ats> result = atsRepo.findAtsByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }

    public Map<Location, List<Ats>> getAtsByPlaceAndFilter(List<Ats> input) {
        Map<Location, List<Ats>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Ats el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }

    public Map<Location, List<Ats>> getAtsByPlaceTypeAndFilter(PlaceType placeType, List<Ats> input) {
        Map<Location, List<Ats>> collect = (Map<Location, List<Ats>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Ats el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
    
    
}
