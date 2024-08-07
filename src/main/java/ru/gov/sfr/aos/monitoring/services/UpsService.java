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
import ru.gov.sfr.aos.monitoring.entities.BatteryType;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.entities.UpsModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.BatteryTypeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.UpsModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.UpsRepo;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class UpsService extends SvtObjService<Ups, UpsRepo, SvtDTO>{
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private UpsModelRepo upsModelRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private UpsRepo upsRepo;
    @Autowired
    private BatteryTypeRepo batteryTypeRepo;

    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {

        if(upsRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
            throw new ObjectAlreadyExists("ИБП с таким серийным номером уже есть в базе данных");
        
        } else {
            Ups ups = new Ups();
            Place place = null;
            UpsModel upsModel = null;
            BatteryType batteryType = null; 
            place = placeRepo.findById(dto.getPlaceId()).get();
            upsModel = upsModelRepo.findById(dto.getModelId()).get();
            batteryType = batteryTypeRepo.findById(dto.getBatteryTypeId()).get();
            switch (dto.getStatus()) {
            case "REPAIR":
                ups.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                ups.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                ups.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                ups.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                ups.setStatus(Status.DEFECTIVE);
                break;
        }
            ups.setInventaryNumber(dto.getInventaryNumber());
            ups.setSerialNumber(dto.getSerialNumber());
            ups.setYearCreated(dto.getYearCreated());
            ups.setYearReplacement(dto.getYearReplacement());
            ups.setYearCreated(dto.getYearCreated());
            ups.setPlace(place);
            ups.setUpsModel(upsModel);
            ups.setBatteryType(batteryType);
            ups.setBatteryAmount(dto.getBatteryAmount());
            ups.setDateExploitationBegin(dto.getDateExploitationBegin());
            ups.setNumberRoom(dto.getNumberRoom());
            ups.setNameFromOneC(dto.getNameFromOneC());
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(ups);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(ups)));
                contract.setContractNumber("00000000");
            }
            ups.setContract(contract);
            upsRepo.save(ups);
        }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) {
        Ups upsFromDB = upsRepo.findById(dto.getId()).get();
        Place placeFromDB = placeRepo.findById(dto.getPlaceId()).get();
        UpsModel upsModelFromDto = upsModelRepo.findById(dto.getModelId()).get();
        BatteryType batteryTypeFromDto = batteryTypeRepo.findById(dto.getBatteryTypeId()).get();
        upsFromDB.setYearCreated(dto.getYearCreated());
        upsFromDB.setInventaryNumber(dto.getInventaryNumber());
        upsFromDB.setSerialNumber(dto.getSerialNumber());
        upsFromDB.setYearReplacement(dto.getYearReplacement());
        upsFromDB.setPlace(placeFromDB);
        upsFromDB.setDateExploitationBegin(dto.getDateExploitationBegin());
        switch (dto.getStatus()) {
            case "REPAIR":
                upsFromDB.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                upsFromDB.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                upsFromDB.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                upsFromDB.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                upsFromDB.setStatus(Status.DEFECTIVE);
                break;
        }
        upsFromDB.setUpsModel(upsModelFromDto);
        upsFromDB.setBatteryType(batteryTypeFromDto);
        upsFromDB.setBatteryAmount(dto.getBatteryAmount());
        upsFromDB.setNumberRoom(dto.getNumberRoom());
        upsFromDB.setNameFromOneC(dto.getNameFromOneC());
        upsRepo.save(upsFromDB);
        
    }
    
}
