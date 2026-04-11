package ru.gov.sfr.aos.monitoring.asuo.terminal.sensor;

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
public class TerminalSensorService extends ObjectBuingService<TerminalSensor, TerminalSensorRepo, TerminalComponentDto> {

    @Autowired
    private TerminalSensorModelRepo terminalSensorModelRepo;
    @Autowired
    private TerminalSensorRepo terminalSensorRepo; 
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private TerminalRepo terminalRepo;
    
    @Override
    public void createSvtObj(TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalSensor terminalSensor = new TerminalSensor();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalSensor.setPlace(place);
        TerminalSensorModel terminalSensorModel = terminalSensorModelRepo.findById(dto.getModelId()).get();
        terminalSensor.setTerminalSensorModel(terminalSensorModel);
        terminalSensor.setSerialNumber(dto.getSerialNumber());
        Contract contract = null;
        if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
            contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
            List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
            objectBuingFromContractDB.add(terminalSensor);
        } else {
            contract = new Contract();
            contract.setDateEndContract(Date.from(Instant.now()));
            contract.setDateStartContract(Date.from(Instant.now()));
            contract.setObjectBuing(new ArrayList<>(Arrays.asList(terminalSensor)));
            contract.setContractNumber("00000000");

        }
        terminalSensor.setContract(contract);
        Status status = RegularOperation.getStatus(dto.getStatus());
        terminalSensor.setStatus(status);
        terminalSensorRepo.save(terminalSensor);
    }

    @Override
    public void updateSvtObj(TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalSensor terminalSensor = terminalSensorRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalSensor.setPlace(place);
        TerminalSensorModel terminalSensorModel = terminalSensorModelRepo.findById(dto.getModelId()).get();
        terminalSensor.setTerminalSensorModel(terminalSensorModel);
        terminalSensor.setSerialNumber(dto.getSerialNumber());
        Status status = RegularOperation.getStatus(dto.getStatus());
        if(terminalSensor.getTerminal() != null && Status.DEFECTIVE.equals(status)) {
            Optional<Terminal> terminalHasInstalledOptional = terminalRepo.findById(terminalSensor.getTerminal().getId());
            if(terminalHasInstalledOptional.isPresent()) {
                Terminal terminalHasInstalled = terminalHasInstalledOptional.get();
                terminalHasInstalled.setStatus(Status.DEFECTIVE);
                terminalRepo.save(terminalHasInstalled);
            }
        }
        terminalSensor.setStatus(status);
        terminalSensorRepo.save(terminalSensor);
    }

    public Map<Location, List<TerminalSensor>> getSvtObjectsBySerialNumberAndPlace(String serialNumber, PlaceType placeType) {
        Map<Location, List<TerminalSensor>> collect = (Map<Location, List<TerminalSensor>>) terminalSensorRepo.findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(serialNumber, placeType)
                .stream()
                .collect(Collectors
                        .groupingBy((TerminalSensor el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
}
