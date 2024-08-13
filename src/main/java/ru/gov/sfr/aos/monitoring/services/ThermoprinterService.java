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
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinter;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinterModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.ThermoprinterModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.ThermoprinterRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class ThermoprinterService extends SvtObjService<ThermoPrinter, ThermoprinterRepo, SvtDTO>{

    @Autowired
    private ThermoprinterRepo thermoprinterRepo;
    @Autowired
    private ThermoprinterModelRepo thermoprinterModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    
    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
        if (thermoprinterRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
            throw new ObjectAlreadyExists("Термопринтер с таким серийным номером уже есть в базе данных");

        } else {
            ThermoPrinter thermoprinter = new ThermoPrinter();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            thermoprinter.setPlace(place);
            ThermoPrinterModel thermoprinterModel = thermoprinterModelRepo.findById(dto.getModelId()).get();
            thermoprinter.setThermoPrinterModel(thermoprinterModel);
            thermoprinter.setInventaryNumber(dto.getInventaryNumber());
            thermoprinter.setSerialNumber(dto.getSerialNumber());
            Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(thermoprinter);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(thermoprinter)));
                contract.setContractNumber("00000000");

            }
            thermoprinter.setContract(contract);
            switch (dto.getStatus()) {
                case "REPAIR":
                    thermoprinter.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    thermoprinter.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    thermoprinter.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    thermoprinter.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    thermoprinter.setStatus(Status.DEFECTIVE);
                    break;
            }
            thermoprinterRepo.save(thermoprinter);
        }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) {
        ThermoPrinter thermoprinter = thermoprinterRepo.findById(dto.getId()).get();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            thermoprinter.setPlace(place);
            ThermoPrinterModel thermoprinterModel = thermoprinterModelRepo.findById(dto.getModelId()).get();
            thermoprinter.setThermoPrinterModel(thermoprinterModel);
            thermoprinter.setInventaryNumber(dto.getInventaryNumber());
            thermoprinter.setSerialNumber(dto.getSerialNumber());
            
            switch (dto.getStatus()) {
                case "REPAIR":
                    thermoprinter.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    thermoprinter.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    thermoprinter.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    thermoprinter.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    thermoprinter.setStatus(Status.DEFECTIVE);
                    break;
            }
            thermoprinterRepo.save(thermoprinter);
    }
    
}
