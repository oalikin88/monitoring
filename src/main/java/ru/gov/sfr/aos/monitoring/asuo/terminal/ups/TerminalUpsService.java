package ru.gov.sfr.aos.monitoring.asuo.terminal.ups;

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
public class TerminalUpsService extends ObjectBuingService<TerminalUps, TerminalUpsRepo, TerminalComponentDto> {
    
    @Autowired
    private TerminalUpsModelRepo terminalUpsModelRepo;
    @Autowired
    private TerminalUpsRepo terminalUpsRepo; 
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private TerminalRepo terminalRepo;

    @Override
    public void createSvtObj(TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalUps terminalUps = new TerminalUps();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalUps.setPlace(place);
        TerminalUpsModel terminalUpsModel = terminalUpsModelRepo.findById(dto.getModelId()).get();
        terminalUps.setTerminalUpsModel(terminalUpsModel);
        terminalUps.setSerialNumber(dto.getSerialNumber());
        Contract contract = null;
        if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
            contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
            List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
            objectBuingFromContractDB.add(terminalUps);
        } else {
            contract = new Contract();
            contract.setDateEndContract(Date.from(Instant.now()));
            contract.setDateStartContract(Date.from(Instant.now()));
            contract.setObjectBuing(new ArrayList<>(Arrays.asList(terminalUps)));
            contract.setContractNumber("00000000");

        }
        terminalUps.setContract(contract);
        Status status = RegularOperation.getStatus(dto.getStatus());
        terminalUps.setStatus(status);
        terminalUpsRepo.save(terminalUps);
    }

    @Override
    public void updateSvtObj(TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalUps terminalUps = terminalUpsRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalUps.setPlace(place);
        TerminalUpsModel terminalUpsModel = terminalUpsModelRepo.findById(dto.getModelId()).get();
        terminalUps.setTerminalUpsModel(terminalUpsModel);
        terminalUps.setSerialNumber(dto.getSerialNumber());
        Status status = RegularOperation.getStatus(dto.getStatus());
        if(terminalUps.getTerminal() != null && Status.DEFECTIVE.equals(status)) {
            Optional<Terminal> terminalHasInstalledOptional = terminalRepo.findById(terminalUps.getTerminal().getId());
            if(terminalHasInstalledOptional.isPresent()) {
                Terminal terminalHasInstalled = terminalHasInstalledOptional.get();
                terminalHasInstalled.setStatus(Status.DEFECTIVE);
                terminalRepo.save(terminalHasInstalled);
            }
        }
        terminalUps.setStatus(status);
        terminalUpsRepo.save(terminalUps);
    }

    public Map<Location, List<TerminalUps>> getSvtObjectsBySerialNumberAndPlace(String serialNumber, PlaceType placeType) {
        Map<Location, List<TerminalUps>> collect = (Map<Location, List<TerminalUps>>) terminalUpsRepo.findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(serialNumber, placeType)
                .stream()
                .collect(Collectors
                        .groupingBy((TerminalUps el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
}
