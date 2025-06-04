package ru.gov.sfr.aos.monitoring.asuo.terminal.server;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.asuo.terminal.Terminal;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalComponentDto;
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalRepo;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingService;
import ru.gov.sfr.aos.monitoring.services.RegularOperation;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalServerService extends ObjectBuingService<TerminalServer, TerminalServerRepo, TerminalComponentDto> {

    @Autowired
    private TerminalServerModelRepo terminalServerModelRepo;
    @Autowired
    private TerminalServerRepo terminalServerRepo; 
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private TerminalRepo terminalRepo;
    
    @Override
    public void createSvtObj(TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalServer terminalServer = new TerminalServer();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalServer.setPlace(place);
        TerminalServerModel terminalServerModel = terminalServerModelRepo.findById(dto.getModelId()).get();
        terminalServer.setTerminalServerModel(terminalServerModel);
        terminalServer.setSerialNumber(dto.getSerialNumber());
        Contract contract = null;
        if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
            contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
            List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
            objectBuingFromContractDB.add(terminalServer);
        } else {
            contract = new Contract();
            contract.setDateEndContract(Date.from(Instant.now()));
            contract.setDateStartContract(Date.from(Instant.now()));
            contract.setObjectBuing(new ArrayList<>(Arrays.asList(terminalServer)));
            contract.setContractNumber("00000000");

        }
        terminalServer.setContract(contract);
        Status status = RegularOperation.getStatus(dto.getStatus());
        terminalServer.setStatus(status);
        terminalServerRepo.save(terminalServer);
    }

    @Override
    public void updateSvtObj(TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalServer terminalServer = terminalServerRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalServer.setPlace(place);
        TerminalServerModel terminalServerModel = terminalServerModelRepo.findById(dto.getModelId()).get();
        terminalServer.setTerminalServerModel(terminalServerModel);
        terminalServer.setSerialNumber(dto.getSerialNumber());
        Status status = RegularOperation.getStatus(dto.getStatus());
        if(terminalServer.getTerminal() != null && Status.DEFECTIVE.equals(status)) {
            Optional<Terminal> terminalHasInstalledOptional = terminalRepo.findById(terminalServer.getTerminal().getId());
            if(terminalHasInstalledOptional.isPresent()) {
                Terminal terminalHasInstalled = terminalHasInstalledOptional.get();
                terminalHasInstalled.setStatus(Status.DEFECTIVE);
                terminalRepo.save(terminalHasInstalled);
            }
        }
        terminalServer.setStatus(status);
        terminalServerRepo.save(terminalServer);
    }
    
    public Map<Location, List<TerminalServer>> getSvtObjectsBySerialNumberAndPlace(String serialNumber, PlaceType placeType) {
        Map<Location, List<TerminalServer>> collect = (Map<Location, List<TerminalServer>>) terminalServerRepo.findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(serialNumber, placeType)
                .stream()
                .collect(Collectors
                        .groupingBy((TerminalServer el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }

}
