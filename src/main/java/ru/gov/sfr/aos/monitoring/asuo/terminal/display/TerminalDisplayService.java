package ru.gov.sfr.aos.monitoring.asuo.terminal.display;

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

import ru.gov.sfr.aos.monitoring.asuo.terminal.Terminal;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalRepo;
import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.services.RegularOperation;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingService;


/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalDisplayService extends ObjectBuingService<TerminalDisplay, TerminalDisplayRepo, TerminalDisplayDto> {

    @Autowired
    private TerminalDisplayModelRepo terminalDisplayModelRepo;
    @Autowired
    private TerminalDisplayRepo terminalDisplayRepo; 
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private TerminalRepo terminalRepo;
    
    
    @Override
    public void createSvtObj(TerminalDisplayDto dto) throws ObjectAlreadyExists {
       
            TerminalDisplay terminalDisplay = new TerminalDisplay();
            Place place = placeRepo.findById(dto.getPlaceId()).get();
            terminalDisplay.setPlace(place);
            TerminalDisplayModel terminalDisplayModel = terminalDisplayModelRepo.findById(dto.getModelId()).get();
            terminalDisplay.setTerminalDisplayModel(terminalDisplayModel);
            terminalDisplay.setSerialNumber(dto.getSerialNumber());
            terminalDisplay.setScreenDiagonal(Double.parseDouble(dto.getScreenDiagonal()));
            Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(terminalDisplay);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(terminalDisplay)));
                contract.setContractNumber("00000000");

            }
            terminalDisplay.setContract(contract);
            Status status = RegularOperation.getStatus(dto.getStatus());
            terminalDisplay.setStatus(status);
            terminalDisplayRepo.save(terminalDisplay);
        
    }

    @Override
    public void updateSvtObj(TerminalDisplayDto dto) throws ObjectAlreadyExists {
        TerminalDisplay terminalDisplay = terminalDisplayRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalDisplay.setPlace(place);
        TerminalDisplayModel terminalDisplayModel = terminalDisplayModelRepo.findById(dto.getModelId()).get();
        terminalDisplay.setTerminalDisplayModel(terminalDisplayModel);
        terminalDisplay.setSerialNumber(dto.getSerialNumber());
        terminalDisplay.setScreenDiagonal(Double.parseDouble(dto.getScreenDiagonal()));
        Status status = RegularOperation.getStatus(dto.getStatus());
        if(terminalDisplay.getTerminal() != null && Status.DEFECTIVE.equals(status)) {
            Optional<Terminal> terminalHasInstalledOptional = terminalRepo.findById(terminalDisplay.getTerminal().getId());
            if(terminalHasInstalledOptional.isPresent()) {
                Terminal terminalHasInstalled = terminalHasInstalledOptional.get();
                terminalHasInstalled.setStatus(Status.DEFECTIVE);
                terminalRepo.save(terminalHasInstalled);
            }
        }
        terminalDisplay.setStatus(status);
        terminalDisplayRepo.save(terminalDisplay);
        
    }

    public Map<Location, List<TerminalDisplay>> getSvtObjectsBySerialNumberAndPlace(String serialNumber, PlaceType placeType) {
        Map<Location, List<TerminalDisplay>> collect = (Map<Location, List<TerminalDisplay>>) terminalDisplayRepo.findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(serialNumber, placeType)
                .stream()
                .collect(Collectors
                        .groupingBy((TerminalDisplay el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
}
