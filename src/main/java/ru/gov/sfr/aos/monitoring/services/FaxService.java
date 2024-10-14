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
import ru.gov.sfr.aos.monitoring.entities.Fax;
import ru.gov.sfr.aos.monitoring.entities.FaxModel;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FaxDto;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.FaxModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.FaxRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class FaxService extends SvtObjService<Fax, FaxRepo, FaxDto>  {

    @Autowired
    private PlaceRepo placeRepo;
    
    @Autowired
    private ContractRepo contractRepo;
    
    @Autowired
    private FaxModelRepo faxModelRepo;
    
    @Autowired
    private FaxRepo faxRepo;
    
    
    @Override
    public void createSvtObj(FaxDto dto) throws ObjectAlreadyExists {
        if(faxRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
           throw new ObjectAlreadyExists("Факс с таким серийным номером уже есть в базе данных");
        }else {
            Fax fax = new Fax();
            Place place = null;
            FaxModel faxModel = null;
            
            place = placeRepo.findById(dto.getPlaceId()).get();
            faxModel = faxModelRepo.findById(dto.getModelId()).get();
            switch (dto.getStatus()) {
            case "REPAIR":
                fax.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                fax.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                fax.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                fax.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                fax.setStatus(Status.DEFECTIVE);
                break;
        }
        fax.setInventaryNumber(dto.getInventaryNumber());
        fax.setSerialNumber(dto.getSerialNumber());
        fax.setYearCreated(dto.getYearCreated());
        fax.setDateExploitationBegin(dto.getDateExploitationBegin());
        fax.setIpAdress(dto.getIpAdress());
        fax.setPlace(place);
        fax.setModel(faxModel);
        fax.setNameFromeOneC(dto.getNameFromOneC());
        fax.setNumberRoom(dto.getNumberRoom());
        Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(fax);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(fax)));
                contract.setContractNumber("00000000");
                
            }
        fax.setContract(contract);
        faxRepo.save(fax);
    }
    }

    @Override
    public void updateSvtObj(FaxDto dto) {
          Fax fax = faxRepo.findById(dto.getId()).get();
            Place place = null;
            FaxModel faxModel = null;
            
            place = placeRepo.findById(dto.getPlaceId()).get();
            faxModel = faxModelRepo.findById(dto.getModelId()).get();
            switch (dto.getStatus()) {
            case "REPAIR":
                fax.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                fax.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                fax.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                fax.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                fax.setStatus(Status.DEFECTIVE);
                break;
        }
        fax.setInventaryNumber(dto.getInventaryNumber());
        fax.setSerialNumber(dto.getSerialNumber());
        fax.setYearCreated(dto.getYearCreated());
        fax.setDateExploitationBegin(dto.getDateExploitationBegin());
        fax.setNameFromeOneC(dto.getNameFromOneC());
        fax.setNumberRoom(dto.getNumberRoom());
        fax.setIpAdress(dto.getIpAdress());
        fax.setPlace(place);
        fax.setModel(faxModel);
        
   
        faxRepo.save(fax);
    }
    
}
