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
import ru.gov.sfr.aos.monitoring.dictionaries.OuterConnectionType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.Ats;
import ru.gov.sfr.aos.monitoring.entities.AtsModel;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.SvtAtsDTO;
import ru.gov.sfr.aos.monitoring.repositories.AtsModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.AtsRepo;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

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
    public void createSvtObj(SvtAtsDTO dto) throws ObjectAlreadyExists {
         
            if(atsRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
                 throw new ObjectAlreadyExists("Маршрутизатор с таким серийным номером уже есть в базе данных");
            
    } else {
            
            Ats ats = new Ats();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            AtsModel atsModel = atsModelRepo.findById(dto.getModelId()).get();
            ats.setInventaryNumber(dto.getInventaryNumber());
            ats.setSerialNumber(dto.getSerialNumber());
            ats.setPlace(place);
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(ats);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(ats)));
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
             if(dto.getOuterConnectionType().equals("SIP")) {
                 ats.setOuterConnectionType(OuterConnectionType.SIP);
             } else {
                 ats.setOuterConnectionType(OuterConnectionType.E1);
             }
             atsRepo.save(ats);
        }
        
    }

    @Override
    public void updateSvtObj(SvtAtsDTO dto) {
        Ats ats = atsRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
            AtsModel atsModel = atsModelRepo.findById(dto.getModelId()).get();
            ats.setInventaryNumber(dto.getInventaryNumber());
            ats.setSerialNumber(dto.getSerialNumber());
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
             if(dto.getOuterConnectionType().equals("SIP")) {
                 ats.setOuterConnectionType(OuterConnectionType.SIP);
             } else {
                 ats.setOuterConnectionType(OuterConnectionType.E1);
             }
             atsRepo.save(ats);
    }
    
}
