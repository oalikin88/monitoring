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
import ru.gov.sfr.aos.monitoring.entities.Infomat;
import ru.gov.sfr.aos.monitoring.entities.InfomatModel;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.InfomatModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.InfomatRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

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
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
        if(infomatRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
                throw new ObjectAlreadyExists("Инфомат с таким серийным номером уже есть в базе данных");
    } else {
            
            Infomat infomat = new Infomat();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            InfomatModel infomatModel = infomatModelRepo.findById(dto.getModelId()).get();
            infomat.setInventaryNumber(dto.getInventaryNumber());
            infomat.setSerialNumber(dto.getSerialNumber());
            infomat.setPlace(place);
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(infomat);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(infomat)));
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
    public void updateSvtObj(SvtDTO dto) {
            Infomat infomat = infomatRepo.findById(dto.getId()).get();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            InfomatModel infomatModel = infomatModelRepo.findById(dto.getModelId()).get();
            infomat.setInventaryNumber(dto.getInventaryNumber());
            infomat.setSerialNumber(dto.getSerialNumber());
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
    
}
