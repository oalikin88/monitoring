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
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Terminal;
import ru.gov.sfr.aos.monitoring.entities.TerminalDisplay;
import ru.gov.sfr.aos.monitoring.entities.TerminalModel;
import ru.gov.sfr.aos.monitoring.entities.TerminalPrinter;
import ru.gov.sfr.aos.monitoring.entities.TerminalSensor;
import ru.gov.sfr.aos.monitoring.entities.TerminalServer;
import ru.gov.sfr.aos.monitoring.entities.TerminalUps;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.TerminalDto;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalDisplayRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalSensorRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalServerRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalUpsRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalService extends SvtObjService<Terminal, TerminalRepo, TerminalDto> {

    @Autowired
    private TerminalRepo terminalRepo;
    @Autowired
    private TerminalModelRepo terminalModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private TerminalDisplayRepo terminalDisplayRepo;
    @Autowired
    private TerminalSensorRepo terminalSensorRepo;
    @Autowired
    private TerminalServerRepo terminalServerRepo;
    @Autowired
    private TerminalPrinterRepo terminalPrinterRepo;
    @Autowired
    private TerminalUpsRepo terminalUpsRepo;

    @Override
    public void createSvtObj(TerminalDto dto) throws ObjectAlreadyExists {
        if (terminalRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
            throw new ObjectAlreadyExists("Терминал с таким серийным номером уже есть в базе данных");

        } else {
            Terminal terminal = new Terminal();
            Optional<TerminalDisplay> terminalDisplayOptional = terminalDisplayRepo.findById(dto.getTerminalDisplayId());
            Optional<TerminalSensor> terminalSensorOptional = terminalSensorRepo.findById(dto.getTerminalSensorId());
            Optional<TerminalServer> terminalServerOptional = terminalServerRepo.findById(dto.getTerminalServerId());
            Optional<TerminalUps> terminalUpsOptional = terminalUpsRepo.findById(dto.getTerminalUpsId());
            Optional<TerminalPrinter> terminalPrinterOptional = terminalPrinterRepo.findById(dto.getTerminalPrinterId());

            if (terminalPrinterOptional.isPresent()) {
                terminal.setTerminalPrinter(terminalPrinterOptional.get());
            }

            if (terminalUpsOptional.isPresent()) {
                terminal.setTerminalUps(terminalUpsOptional.get());
            }

            if (terminalServerOptional.isPresent()) {
                terminal.setTerminalServer(terminalServerOptional.get());
            }

            if (terminalSensorOptional.isPresent()) {
                terminal.setTerminalSensor(terminalSensorOptional.get());
            }

            if (terminalDisplayOptional.isPresent()) {
                terminal.setTerminalDisplay(terminalDisplayOptional.get());
            }

            Place place = placeRepo.findById(dto.getPlaceId()).get();
            terminal.setPlace(place);
            TerminalModel terminalModel = terminalModelRepo.findById(dto.getModelId()).get();
            terminal.setTerminalModel(terminalModel);
            terminal.setInventaryNumber(dto.getInventaryNumber());
            terminal.setSerialNumber(dto.getSerialNumber());

            Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(terminal);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(terminal)));
                contract.setContractNumber("00000000");

            }
            terminal.setContract(contract);
            Status status = RegularOperation.getStatus(dto.getStatus());
            boolean checkStatus = checkStatus(terminal);
            if (Status.OK.equals(status) && checkStatus) {
                terminal.setStatus(status);
            } else if (Status.OK.equals(status) && !checkStatus) {
                terminal.setStatus(Status.DEFECTIVE);
            } else {
                terminal.setStatus(status);
            }
            terminalRepo.save(terminal);
        }
    }

    @Override
    public void updateSvtObj(TerminalDto dto) {
        Terminal terminal = terminalRepo.findById(dto.getId()).get();
        Optional<TerminalDisplay> terminalDisplayOptional = terminalDisplayRepo.findById(dto.getTerminalDisplayId());
        Optional<TerminalSensor> terminalSensorOptional = terminalSensorRepo.findById(dto.getTerminalSensorId());
        Optional<TerminalServer> terminalServerOptional = terminalServerRepo.findById(dto.getTerminalServerId());
        Optional<TerminalUps> terminalUpsOptional = terminalUpsRepo.findById(dto.getTerminalUpsId());
        Optional<TerminalPrinter> terminalPrinterOptional = terminalPrinterRepo.findById(dto.getTerminalPrinterId());

        if (terminalPrinterOptional.isPresent()) {
            terminal.setTerminalPrinter(terminalPrinterOptional.get());
        }

        if (terminalUpsOptional.isPresent()) {
            terminal.setTerminalUps(terminalUpsOptional.get());
        }

        if (terminalServerOptional.isPresent()) {
            terminal.setTerminalServer(terminalServerOptional.get());
        }

        if (terminalSensorOptional.isPresent()) {
            terminal.setTerminalSensor(terminalSensorOptional.get());
        }

        if (terminalDisplayOptional.isPresent()) {
            terminal.setTerminalDisplay(terminalDisplayOptional.get());
        }
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminal.setPlace(place);
        TerminalModel terminalModel = terminalModelRepo.findById(dto.getModelId()).get();
        terminal.setTerminalModel(terminalModel);
        terminal.setInventaryNumber(dto.getInventaryNumber());
        terminal.setSerialNumber(dto.getSerialNumber());
        Status status = RegularOperation.getStatus(dto.getStatus());
        boolean checkStatus = checkStatus(terminal);
        if (Status.OK.equals(status) && checkStatus) {
            terminal.setStatus(status);
        } else if (Status.OK.equals(status) && !checkStatus) {
            terminal.setStatus(Status.DEFECTIVE);
        } else {
            terminal.setStatus(status);
        }

        terminalRepo.save(terminal);
    }

    public static boolean checkStatus(Terminal terminal) {
        List<Status> terminalComponentStatusList = new ArrayList<>();
        terminalComponentStatusList.add(terminal.getTerminalDisplay().getStatus());
        terminalComponentStatusList.add(terminal.getTerminalPrinter().getStatus());
        terminalComponentStatusList.add(terminal.getTerminalSensor().getStatus());
        terminalComponentStatusList.add(terminal.getTerminalServer().getStatus());
        terminalComponentStatusList.add(terminal.getTerminalUps().getStatus());
        List<Status> afterFilterOkList = terminalComponentStatusList.stream().filter(element -> Status.DEFECTIVE.equals(element)).collect(Collectors.toList());
        if (!afterFilterOkList.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public List<Terminal> getAllTerminal() {
        List<Terminal> terminals = terminalRepo.findAll();
        return terminals;
    }

    @Override
    public void svtObjToArchive(ArchivedDto dto) {
        Optional<Terminal> terminalOptional = terminalRepo.findById(dto.getId());
        if(terminalOptional.isPresent()) {
            Terminal terminal = terminalOptional.get();
            if(terminal.getTerminalDisplay() != null) {
                terminal.getTerminalDisplay().setArchived(true);
            }
            if(terminal.getTerminalSensor() != null) {
                terminal.getTerminalSensor().setArchived(true);
            }
            if(terminal.getTerminalServer() != null) {
                terminal.getTerminalServer().setArchived(true);
            }
            if(terminal.getTerminalUps() != null) {
                terminal.getTerminalUps().setArchived(true);
            }
            if(terminal.getTerminalPrinter() != null) {
               terminal.getTerminalPrinter().setArchived(true); 
            }
            terminal.setArchived(true);
            terminalRepo.save(terminal);
       }
    }
    
    

}
