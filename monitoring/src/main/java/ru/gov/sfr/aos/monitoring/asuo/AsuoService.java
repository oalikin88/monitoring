/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo;

import ru.gov.sfr.aos.monitoring.asuo.subdisplay.SubDisplayModel;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.asuo.tv.Display;
import ru.gov.sfr.aos.monitoring.asuo.hub.Hub;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.asuo.terminal.Terminal;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.asuo.tv.DisplayRepo;
import ru.gov.sfr.aos.monitoring.asuo.hub.HubRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.ProgramSoftwareRepo;
import ru.gov.sfr.aos.monitoring.asuo.subdisplay.SubDisplayModelRepo;
import ru.gov.sfr.aos.monitoring.asuo.terminal.TerminalRepo;
import ru.gov.sfr.aos.monitoring.services.RegularOperation;
import ru.gov.sfr.aos.monitoring.svtobject.SvtObjectBuingService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class AsuoService extends SvtObjectBuingService<Asuo, AsuoRepo, AsuoDTO> {

    @Autowired
    private AsuoRepo asuoRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private SubDisplayModelRepo subDisplayModelRepo;
    @Autowired
    private DisplayRepo displayRepo;
    @Autowired
    private TerminalRepo terminalRepo;
    @Autowired
    private HubRepo hubRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private ProgramSoftwareRepo programSoftwareRepo;
    
    @Override
    public void createSvtObj(AsuoDTO dto) throws ObjectAlreadyExists {
        Asuo asuo = new Asuo();
        Set<Display> displaySet = new HashSet<>();
        List<Terminal> terminalSet = new ArrayList<>();
        ProgramSoftware programSoftware = null;
        Set<Hub> hubSet = new HashSet<>();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        asuo.setPlace(place);
        SubDisplayModel subDisplayModel = subDisplayModelRepo.findById(dto.getSubDisplayModelId()).get();
        asuo.setSubDisplayModel(subDisplayModel);
        asuo.setSubDisplayAmount(dto.getSubDisplayAmount());
        if(dto.getProgramSoftware() != null) {
            asuo.setProgramSoftware(programSoftwareRepo.findById(dto.getProgramSoftware()).orElseThrow());
        } else {
            asuo.setProgramSoftware(programSoftware);
        }
        if (!dto.getDisplays().isEmpty()) {
            for (AsuoComponentDto el : dto.getDisplays()) {
                if (el != null) {
                    Optional<Display> findById = displayRepo.findById(el.getId());
                    if (findById.isPresent()) {
                        displaySet.add(findById.get());
                    }
                }

            }
        }
        if (!dto.getTerminals().isEmpty()) {
            for (AsuoComponentDto el : dto.getTerminals()) {
                if (el != null) {
                    Optional<Terminal> findById = terminalRepo.findById(el.getId());
                    if (findById.isPresent()) {
                        terminalSet.add(findById.get());
                    }
                }

            }
        }
        
        if (!dto.getHubs().isEmpty()) {
            for (AsuoComponentDto el : dto.getHubs()) {
                if (el != null) {
                    Optional<Hub> findById = hubRepo.findById(el.getId());
                    if (findById.isPresent()) {
                        hubSet.add(findById.get());
                    }
                }

            }
        }
        
        asuo.setDisplay(displaySet);
        asuo.setTerminal(terminalSet);
        asuo.setHubSet(hubSet);
        Contract contract = null;
        if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
            contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
            List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
            objectBuingFromContractDB.add(asuo);
        } else {
            contract = new Contract();
            contract.setDateEndContract(Date.from(Instant.now()));
            contract.setDateStartContract(Date.from(Instant.now()));
            contract.setObjectBuing(new ArrayList<>(Arrays.asList(asuo)));
            contract.setContractNumber("00000000");
            
        }
        asuo.setContract(contract);
        asuo.setDateExploitationBegin(dto.getDateExploitationBegin());
        asuo.setYearCreated(dto.getYearCreated());
        asuo.setNameFromOneC(dto.getNameFromOneC());
        asuo.setInventaryNumber(dto.getInventaryNumber());
        asuo.setStatus(RegularOperation.getStatus(dto.getStatus()));
        asuoRepo.save(asuo);
    }
    
    @Override
    public void updateSvtObj(AsuoDTO dto) {
        Asuo asuo = asuoRepo.findById(dto.getId()).get();
        ProgramSoftware programSoftware = null;
        Set<Display> displaySet = new HashSet<>();
        List<Terminal> terminalSet = new ArrayList<>();
        Set<Hub> hubSet = new HashSet<>();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        asuo.setPlace(place);
        SubDisplayModel subDisplayModel = subDisplayModelRepo.findById(dto.getSubDisplayModelId()).get();
        asuo.setSubDisplayModel(subDisplayModel);
        asuo.setSubDisplayAmount(dto.getSubDisplayAmount());
        if(dto.getProgramSoftware() != null) {
            asuo.setProgramSoftware(programSoftwareRepo.findById(dto.getProgramSoftware()).orElseThrow());
        } else {
            asuo.setProgramSoftware(programSoftware);
        }
        
        if (!dto.getDisplays().isEmpty()) {
            for (AsuoComponentDto el : dto.getDisplays()) {
                if (el != null) {
                    Optional<Display> findById = displayRepo.findById(el.getId());
                    if (findById.isPresent()) {
                        displaySet.add(findById.get());
                    }
                }

            }
        }
        
        if (!dto.getTerminals().isEmpty()) {
            for (AsuoComponentDto el : dto.getTerminals()) {
                if (el != null) {
                    Optional<Terminal> findById = terminalRepo.findById(el.getId());
                    if (findById.isPresent()) {
                        terminalSet.add(findById.get());
                    }
                }

            }
        }
        
        if (!dto.getHubs().isEmpty()) {
            for (AsuoComponentDto el : dto.getHubs()) {
                if (el != null) {
                    Optional<Hub> findById = hubRepo.findById(el.getId());
                    if (findById.isPresent()) {
                        hubSet.add(findById.get());
                    }
                }

            }
        }
        
        
        asuo.setDisplay(displaySet);
        asuo.setTerminal(terminalSet);
        asuo.setHubSet(hubSet);
        asuo.setDateExploitationBegin(dto.getDateExploitationBegin());
        asuo.setYearCreated(dto.getYearCreated());
        asuo.setNameFromOneC(dto.getNameFromOneC());
        asuo.setInventaryNumber(dto.getInventaryNumber());
        asuo.setStatus(RegularOperation.getStatus(dto.getStatus()));
        asuo.setInventaryNumber(dto.getInventaryNumber());
        asuoRepo.save(asuo);
    }

    @Override
    public void svtObjToArchive(ArchivedDto dto) {
        Optional<Asuo> asuoOptional = asuoRepo.findById(dto.getId());
        if(asuoOptional.isPresent()) {
            Asuo asuo = asuoOptional.get();
            Set<Display> displaysSetArchivedOn = asuo.getDisplay().stream().peek(e -> e.setArchived(true)).collect(Collectors.toSet());
            Set<Hub> hubsSetSrchivedOn = asuo.getHubSet().stream().peek(e -> e.setArchived(true)).collect(Collectors.toSet());
            List<Terminal> terminalsListArchivedOn = asuo.getTerminal().stream().peek(e -> e.setArchived(true)).collect(Collectors.toList());
            asuo.setTerminal(terminalsListArchivedOn);
            asuo.setDisplay(displaysSetArchivedOn);
            asuo.setHubSet(hubsSetSrchivedOn);
            asuo.setArchived(true);
            asuoRepo.save(asuo);
        }
    }
    
    
     public Map<Location, List<Asuo>> getSvtObjectsByInventaryNumberAndPlace(String inventaryNumber, PlaceType placeType) {
        Map<Location, List<Asuo>> collect = (Map<Location, List<Asuo>>) asuoRepo.findByInventaryNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(inventaryNumber, placeType)
                .stream()
                .collect(Collectors
                        .groupingBy((Asuo el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }
    
    
}
