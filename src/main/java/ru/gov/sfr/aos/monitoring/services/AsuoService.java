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
import ru.gov.sfr.aos.monitoring.entities.Asuo;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Display;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.SubDisplayModel;
import ru.gov.sfr.aos.monitoring.entities.SwitchHub;
import ru.gov.sfr.aos.monitoring.entities.SwitchingUnit;
import ru.gov.sfr.aos.monitoring.entities.Terminal;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinter;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.AsuoDTO;
import ru.gov.sfr.aos.monitoring.repositories.AsuoRepo;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.DisplayRepo;
import ru.gov.sfr.aos.monitoring.repositories.ObjectBuingRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.SubDisplayModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.SwitchHubRepo;
import ru.gov.sfr.aos.monitoring.repositories.SwitchingUnitRepo;
import ru.gov.sfr.aos.monitoring.repositories.TerminalRepo;
import ru.gov.sfr.aos.monitoring.repositories.ThermoprinterRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class AsuoService extends SvtObjectBuingService<Asuo, ObjectBuingRepo, AsuoDTO> {
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
    private ThermoprinterRepo thermoprinterRepo;
    @Autowired
    private SwitchingUnitRepo switchingUnitRepo;
    @Autowired
    private SwitchHubRepo switchHubRepo;
    @Autowired
    private ContractRepo contractRepo;


    @Override
    public void createSvtObj(AsuoDTO dto) throws ObjectAlreadyExists {
        Asuo asuo = new Asuo();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        asuo.setPlace(place);
        SubDisplayModel subDisplayModel = subDisplayModelRepo.findById(dto.getSubDisplayModelId()).get();
        asuo.setSubDisplayModel(subDisplayModel);
        asuo.setSubDisplayAmount(dto.getSubDisplayAmount());
        Display display = displayRepo.findById(dto.getDisplayId()).get();
        asuo.setDisplay(display);
        Terminal terminal = terminalRepo.findById(dto.getTerminalId()).get();
        asuo.setTerminal(terminal);
        ThermoPrinter thermoprinter = thermoprinterRepo.findById(dto.getThermoprinterId()).get();
        asuo.setThermoPrinter(thermoprinter);
        Set<SwitchHub> switches = new HashSet<>();
        for (Long item : dto.getSwitchId()) {
            SwitchHub switchhub = switchHubRepo.findById(item).get();
            switches.add(switchhub);
        }
        
        asuo.setSwitchHubSet(switches);
        SwitchingUnit switchingUnit = switchingUnitRepo.findById(dto.getSwitchingUnitId()).get();
        asuo.setSwitchingUnit(switchingUnit);
        Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(asuo);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(asuo)));
                contract.setContractNumber("00000000");

            }
            asuo.setContract(contract);
            asuo.setDateExploitationBegin(dto.getDateExploitationBegin());
            asuo.setYearCreated(dto.getYearCreated());
            asuo.setNameFromOneC(dto.getNameFromOneC());
            asuo.setNumberRoom(dto.getNumberRoom());
        asuoRepo.save(asuo);
    }

    @Override
    public void updateSvtObj(AsuoDTO dto) {
        Asuo asuo = asuoRepo.findById(dto.getId()).get();
        Place place = placeRepo.findById(dto.getPlaceId()).get();
        asuo.setPlace(place);
        SubDisplayModel subDisplayModel = subDisplayModelRepo.findById(dto.getSubDisplayModelId()).get();
        asuo.setSubDisplayModel(subDisplayModel);
        asuo.setSubDisplayAmount(dto.getSubDisplayAmount());
        Display display = displayRepo.findById(dto.getDisplayId()).get();
        asuo.setDisplay(display);
        Terminal terminal = terminalRepo.findById(dto.getTerminalId()).get();
        asuo.setTerminal(terminal);
        ThermoPrinter thermoprinter = thermoprinterRepo.findById(dto.getThermoprinterId()).get();
        asuo.setThermoPrinter(thermoprinter);
        Set<SwitchHub> switches = new HashSet<>();
        for (Long item : dto.getSwitchId()) {
            SwitchHub switchhub = switchHubRepo.findById(item).get();
            switches.add(switchhub);
        }
        
        asuo.setSwitchHubSet(switches);
        SwitchingUnit switchingUnit = switchingUnitRepo.findById(dto.getSwitchingUnitId()).get();
        asuo.setSwitchingUnit(switchingUnit);
        asuo.setDateExploitationBegin(dto.getDateExploitationBegin());
        asuo.setYearCreated(dto.getYearCreated());
        asuo.setNameFromOneC(dto.getNameFromOneC());
        asuo.setNumberRoom(dto.getNumberRoom());
        asuoRepo.save(asuo);
    }
    
}
