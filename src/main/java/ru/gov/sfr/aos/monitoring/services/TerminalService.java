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
import ru.gov.sfr.aos.monitoring.entities.Terminal;
import ru.gov.sfr.aos.monitoring.entities.TerminalModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalService extends SvtObjService<Terminal, TerminalRepo, SvtDTO> {

    @Autowired
    private TerminalRepo terminalRepo;
    @Autowired
    private TerminalModelRepo terminalModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;

    @Override
    public void createSvtObj(SvtDTO dto) throws ObjectAlreadyExists {
        if (terminalRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
            throw new ObjectAlreadyExists("Терминал с таким серийным номером уже есть в базе данных");

        } else {
            Terminal terminal = new Terminal();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            terminal.setPlace(place);
            TerminalModel terminalModel = terminalModelRepo.findById(dto.getModelId()).get();
            terminal.setTerminalModel(terminalModel);
            terminal.setInventaryNumber(dto.getInventaryNumber());
            terminal.setSerialNumber(dto.getSerialNumber());
            Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(terminal);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(terminal)));
                contract.setContractNumber("00000000");

            }
            terminal.setContract(contract);
            switch (dto.getStatus()) {
                case "REPAIR":
                    terminal.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    terminal.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    terminal.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    terminal.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    terminal.setStatus(Status.DEFECTIVE);
                    break;
            }
            terminalRepo.save(terminal);
        }
    }

    @Override
    public void updateSvtObj(SvtDTO dto) {
        Terminal terminal = terminalRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminal.setPlace(place);
        TerminalModel terminalModel = terminalModelRepo.findById(dto.getModelId()).get();
        terminal.setTerminalModel(terminalModel);
        terminal.setInventaryNumber(dto.getInventaryNumber());
        terminal.setSerialNumber(dto.getSerialNumber());

        switch (dto.getStatus()) {
            case "REPAIR":
                terminal.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                terminal.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                terminal.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                terminal.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                terminal.setStatus(Status.DEFECTIVE);
                break;
        }
        terminalRepo.save(terminal);
    }

}
