/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.SwitchingUnit;
import ru.gov.sfr.aos.monitoring.entities.SwitchingUnitModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.SwitchingUnitModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.SwitchingUnitRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class SwitchingUnitService extends SvtObjService<SwitchingUnit, SwitchingUnitRepo, SvtDTO> {

    @Autowired
    private SwitchingUnitRepo switchingUnitRepo;
    @Autowired
    private SwitchingUnitModelRepo switchingUnitModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    
    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
        if (switchingUnitRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
            throw new ObjectAlreadyExists("Блок коммутации с таким серийным номером уже есть в базе данных");

        } else {
            SwitchingUnit swunit = new SwitchingUnit();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            swunit.setPlace(place);
            SwitchingUnitModel swunitModel = switchingUnitModelRepo.findById(dto.getModelId()).get();
            swunit.setSwitchingUnitModel(swunitModel);
            swunit.setInventaryNumber(dto.getInventaryNumber());
            swunit.setSerialNumber(dto.getSerialNumber());
            Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(swunit);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(swunit)));
                contract.setContractNumber("00000000");

            }
            swunit.setContract(contract);
            switch (dto.getStatus()) {
                case "REPAIR":
                    swunit.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    swunit.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    swunit.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    swunit.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    swunit.setStatus(Status.DEFECTIVE);
                    break;
            }
            switchingUnitRepo.save(swunit);
        }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) {
            SwitchingUnit swunit = switchingUnitRepo.findById(dto.getId()).get();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            swunit.setPlace(place);
            SwitchingUnitModel swunitModel = switchingUnitModelRepo.findById(dto.getModelId()).get();
            swunit.setSwitchingUnitModel(swunitModel);
            swunit.setInventaryNumber(dto.getInventaryNumber());
            swunit.setSerialNumber(dto.getSerialNumber());
            
            switch (dto.getStatus()) {
                case "REPAIR":
                    swunit.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    swunit.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    swunit.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    swunit.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    swunit.setStatus(Status.DEFECTIVE);
                    break;
            }
            switchingUnitRepo.save(swunit);
    }
    
    public List<SwitchingUnit> getAllSwitchingUnit() {
        List<SwitchingUnit> switchingUnits = switchingUnitRepo.findAll();
        return switchingUnits;
    }
    
}
