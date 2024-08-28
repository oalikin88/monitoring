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
import ru.gov.sfr.aos.monitoring.entities.Display;
import ru.gov.sfr.aos.monitoring.entities.DisplayModel;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.DisplayModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.DisplayRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class DisplayService extends SvtObjService<Display, DisplayRepo, SvtDTO> {
    
    @Autowired
    private DisplayRepo displayRepo;
    @Autowired
    private DisplayModelRepo displayModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;

    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
        if (displayRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
            throw new ObjectAlreadyExists("Главное табло с таким серийным номером уже есть в базе данных");

        } else {
            Display display = new Display();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            display.setPlace(place);
            DisplayModel displayModel = displayModelRepo.findById(dto.getModelId()).get();
            display.setDisplayModel(displayModel);
            display.setInventaryNumber(dto.getInventaryNumber());
            display.setSerialNumber(dto.getSerialNumber());
            Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(display);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(display)));
                contract.setContractNumber("00000000");

            }
            display.setContract(contract);
            switch (dto.getStatus()) {
                case "REPAIR":
                    display.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    display.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    display.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    display.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    display.setStatus(Status.DEFECTIVE);
                    break;
            }
            displayRepo.save(display);
        }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) {
            Display display = displayRepo.findById(dto.getId()).get();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            display.setPlace(place);
            DisplayModel displayModel = displayModelRepo.findById(dto.getModelId()).get();
            display.setDisplayModel(displayModel);
            display.setInventaryNumber(dto.getInventaryNumber());
            display.setSerialNumber(dto.getSerialNumber());
            
            switch (dto.getStatus()) {
                case "REPAIR":
                    display.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    display.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    display.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    display.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    display.setStatus(Status.DEFECTIVE);
                    break;
            }
            displayRepo.save(display);
    }
    
    public List<Display> getAllDisplays() {
        List<Display> displays = displayRepo.findAll();
        return displays;
    }
    
}
