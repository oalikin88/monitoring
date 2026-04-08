package ru.gov.sfr.aos.monitoring.asuo.terminal.printer;

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
public class TerminalPrinterService extends ObjectBuingService<TerminalPrinter, TerminalPrinterRepo, TerminalComponentDto> {

    @Autowired
    private TerminalPrinterModelRepo terminalPrinterModelRepo;
    @Autowired
    private TerminalPrinterRepo terminalPrinterRepo; 
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private TerminalRepo terminalRepo;
    
    @Override
    public void createSvtObj(TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalPrinter terminalPrinter = new TerminalPrinter();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalPrinter.setPlace(place);
        TerminalPrinterModel terminalPrinterModel = terminalPrinterModelRepo.findById(dto.getModelId()).get();
        terminalPrinter.setTerminalPrinterModel(terminalPrinterModel);
        terminalPrinter.setSerialNumber(dto.getSerialNumber());
        Contract contract = null;
        if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
            contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
            List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
            objectBuingFromContractDB.add(terminalPrinter);
        } else {
            contract = new Contract();
            contract.setDateEndContract(Date.from(Instant.now()));
            contract.setDateStartContract(Date.from(Instant.now()));
            contract.setObjectBuing(new ArrayList<>(Arrays.asList(terminalPrinter)));
            contract.setContractNumber("00000000");

        }
        terminalPrinter.setContract(contract);
        Status status = RegularOperation.getStatus(dto.getStatus());
        terminalPrinter.setStatus(status);
        terminalPrinterRepo.save(terminalPrinter);
    }

    @Override
    public void updateSvtObj(TerminalComponentDto dto) throws ObjectAlreadyExists {
        TerminalPrinter terminalPrinter = terminalPrinterRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        terminalPrinter.setPlace(place);
        TerminalPrinterModel terminalPrinterModel = terminalPrinterModelRepo.findById(dto.getModelId()).get();
        terminalPrinter.setTerminalPrinterModel(terminalPrinterModel);
        terminalPrinter.setSerialNumber(dto.getSerialNumber());
        Status status = RegularOperation.getStatus(dto.getStatus());
        if(terminalPrinter.getTerminal() != null && Status.DEFECTIVE.equals(status)) {
            Optional<Terminal> terminalHasInstalledOptional = terminalRepo.findById(terminalPrinter.getTerminal().getId());
            if(terminalHasInstalledOptional.isPresent()) {
                Terminal terminalHasInstalled = terminalHasInstalledOptional.get();
                terminalHasInstalled.setStatus(Status.DEFECTIVE);
                terminalRepo.save(terminalHasInstalled);
            }
        }
        terminalPrinter.setStatus(status);
        terminalPrinterRepo.save(terminalPrinter);
    }

    public Map<Location, List<TerminalPrinter>> getSvtObjectsBySerialNumberAndPlace(String serialNumber, PlaceType placeType) {
        Map<Location, List<TerminalPrinter>> collect = (Map<Location, List<TerminalPrinter>>) terminalPrinterRepo.findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(serialNumber, placeType)
                .stream()
                .collect(Collectors
                        .groupingBy((TerminalPrinter el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
}
