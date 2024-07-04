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
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.entities.PhoneModel;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PhoneModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.PhoneRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class PhoneService extends SvtObjService<Phone, PhoneRepo, SvtDTO>{

    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private PhoneModelRepo phoneModelRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private PhoneRepo phoneRepo;
    
    
    @Override
    public void createSvtObj(SvtDTO dto) {
          
            if(null != dto.getId()) {
        if(phoneRepo.existsById(dto.getId())) {
            System.out.println("такой телефон уже есть в базе данных");
        }}else {
            Phone phone = new Phone();
            Place place = null;
            PhoneModel phoneModel = null;
            
            place = placeRepo.findById(dto.getPlaceId()).get();
            phoneModel = phoneModelRepo.findById(dto.getModelId()).get();
            switch (dto.getStatus()) {
            case "REPAIR":
                phone.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                phone.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                phone.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                phone.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                phone.setStatus(Status.DEFECTIVE);
                break;
        }
        phone.setInventaryNumber(dto.getInventaryNumber());
        phone.setSerialNumber(dto.getSerialNumber());
        phone.setYearCreated(dto.getYearCreated());
        phone.setDateExploitationBegin(dto.getDateExploitationBegin());
        phone.setPhoneNumber(dto.getPhoneNumber());
        phone.setPlace(place);
        phone.setPhoneModel(phoneModel);
        
        Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(phone);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(phone)));
                contract.setContractNumber("00000000");
                
            }
        phone.setContract(contract);
        phoneRepo.save(phone);
    }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) {
        Phone phoneFromDB = phoneRepo.findById(dto.getId()).get();
        Place placeFromDto = placeRepo.findById(dto.getPlaceId()).get();
        PhoneModel phoneModelFromDto = phoneModelRepo.findById(dto.getModelId()).get();
        phoneFromDB.setDateExploitationBegin(dto.getDateExploitationBegin());
        phoneFromDB.setInventaryNumber(dto.getInventaryNumber());
        phoneFromDB.setSerialNumber(dto.getSerialNumber());
        
        phoneFromDB.setPlace(placeFromDto);
        switch (dto.getStatus()) {
            case "REPAIR":
                phoneFromDB.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                phoneFromDB.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                phoneFromDB.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                phoneFromDB.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                phoneFromDB.setStatus(Status.DEFECTIVE);
                break;
        }
        phoneFromDB.setYearCreated(dto.getYearCreated());
        phoneFromDB.setPhoneModel(phoneModelFromDto);
        phoneRepo.save(phoneFromDB);
    }
    
}
