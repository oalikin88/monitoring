/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Fax;
import ru.gov.sfr.aos.monitoring.entities.FaxModel;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FaxDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
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
        if(faxRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
           throw new ObjectAlreadyExists("Факс с таким серийным номером уже есть в базе данных");
        }else if(faxRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())) {
            throw new ObjectAlreadyExists("Факс с таким инвентарным номером уже есть в базе данных");
        }else {
            Fax fax = new Fax();
            Place place = null;
            FaxModel faxModel = null;
            
            place = placeRepo.findById(dto.getPlaceId()).get();
            
            if(null == dto.getModelId()) {
                if(faxModelRepo.existsByModelIgnoreCase("не указано")) {
                    faxModel = faxModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    faxModel = new FaxModel();
                    faxModel.setModel("не указано");
                }
            } else {
                faxModel = faxModelRepo.findById(dto.getModelId()).get();
            }
            
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
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(fax);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(fax)));
                contract.setContractNumber("00000000");
                
            }
        fax.setContract(contract);
        faxRepo.save(fax);
    }
    }

    @Override
    public void updateSvtObj(FaxDto dto) throws ObjectAlreadyExists {
          Fax fax = faxRepo.findById(dto.getId()).get();
            Place place = null;
            FaxModel faxModel = null;
            
            place = placeRepo.findById(dto.getPlaceId()).get();
              if(null == dto.getModelId()) {
                if(faxModelRepo.existsByModelIgnoreCase("не указано")) {
                    faxModel = faxModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    faxModel = new FaxModel();
                    faxModel.setModel("не указано");
                }
            } else {
                faxModel = faxModelRepo.findById(dto.getModelId()).get();
            }
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
        if (faxRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())) {
            List<Fax> checkInventary = faxRepo.findByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim());
            for (Fax el : checkInventary) {
                if (el.getId() != dto.getId()) {
                    throw new ObjectAlreadyExists("Факс с таким инвентарным номером уже есть в базе данных");
                }
            }

        }
        fax.setInventaryNumber(dto.getInventaryNumber().trim());
        
       
        if (faxRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
            List<Fax> checkSerial = faxRepo.findBySerialNumberIgnoreCase(dto.getSerialNumber().trim());
            for (Fax el : checkSerial) {
                if (el.getId() != dto.getId()) {
                    throw new ObjectAlreadyExists("Факс с таким серийным номером уже есть в базе данных");
                }
            }
        } 
        fax.setSerialNumber(dto.getSerialNumber().trim());
        fax.setYearCreated(dto.getYearCreated());
        fax.setDateExploitationBegin(dto.getDateExploitationBegin());
        fax.setNameFromeOneC(dto.getNameFromOneC());
        fax.setNumberRoom(dto.getNumberRoom());
        fax.setIpAdress(dto.getIpAdress());
        fax.setPlace(place);
        fax.setModel(faxModel);
        
   
        faxRepo.save(fax);
    }
    
    
        public List<Fax> getFaxByFilter(FilterDto dto) {

        List<Fax> result = faxRepo.findFaxByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }

    public Map<Location, List<Fax>> getFaxByPlaceAndFilter(List<Fax> input) {
        Map<Location, List<Fax>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Fax el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }

    public Map<Location, List<Fax>> getFaxByPlaceTypeAndFilter(PlaceType placeType, List<Fax> input) {
        Map<Location, List<Fax>> collect = (Map<Location, List<Fax>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Fax el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
    
    
}
