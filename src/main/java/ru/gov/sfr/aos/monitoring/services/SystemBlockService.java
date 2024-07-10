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
import ru.gov.sfr.aos.monitoring.entities.CdDrive;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Keyboard;
import ru.gov.sfr.aos.monitoring.entities.LanCard;
import ru.gov.sfr.aos.monitoring.entities.Motherboard;
import ru.gov.sfr.aos.monitoring.entities.Mouse;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.OperationSystem;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.entities.SoundCard;
import ru.gov.sfr.aos.monitoring.entities.Speakers;
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.entities.SystemBlockModel;
import ru.gov.sfr.aos.monitoring.entities.VideoCard;
import ru.gov.sfr.aos.monitoring.models.SvtSystemBlockDTO;
import ru.gov.sfr.aos.monitoring.repositories.CdDriveRepo;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.CpuRepo;
import ru.gov.sfr.aos.monitoring.repositories.HddRepo;
import ru.gov.sfr.aos.monitoring.repositories.KeyboardRepo;
import ru.gov.sfr.aos.monitoring.repositories.LanCardRepo;
import ru.gov.sfr.aos.monitoring.repositories.MotherboardRepo;
import ru.gov.sfr.aos.monitoring.repositories.MouseRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.RamRepo;
import ru.gov.sfr.aos.monitoring.repositories.SoundCardRepo;
import ru.gov.sfr.aos.monitoring.repositories.SpeakersRepo;
import ru.gov.sfr.aos.monitoring.repositories.SystemBlockModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.SystemBlockRepo;
import ru.gov.sfr.aos.monitoring.repositories.VideoCardRepo;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class SystemBlockService extends SvtObjService<SystemBlock, SystemBlockRepo, SvtSystemBlockDTO>{
    @Autowired
    private SystemBlockRepo systemBlockRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private MotherboardRepo motherboardRepo;
    @Autowired
    private CdDriveRepo cdDriveRepo;
    @Autowired
    private SystemBlockModelRepo systemBlockModelRepo;
    @Autowired
    private CpuRepo cpuRepo;
    @Autowired
    private HddRepo hddRepo;
    @Autowired
    private KeyboardRepo keyboardRepo;
    @Autowired
    private LanCardRepo lanCardRepo;
    @Autowired
    private MouseRepo mouseRepo;
    @Autowired
    private RamRepo ramRepo;
    @Autowired
    private SoundCardRepo soundCardRepo;
    @Autowired
    private SpeakersRepo speakersRepo;
    @Autowired
    private VideoCardRepo videoCardRepo;
    @Autowired
    private OperationSystemService operationSystemService;
    

    
    
        
    

    @Override
    public void createSvtObj(SvtSystemBlockDTO dto) {
        if(null != dto.getId()) {
            if(systemBlockRepo.existsById(dto.getId())) {
                System.out.println("такой системный блок уже есть в базе данных");
            }
        } else {
            SystemBlock systemBlock = new SystemBlock();
            Place place = null;
            SystemBlockModel systemblockModel = null;
            Motherboard motherboard = null;
            CdDrive cdDrive = null;
            Cpu cpu = null;
            Hdd hdd = null;
            Keyboard keyboard = null;
            LanCard lanCard = null;
            Mouse mouse = null;
            Ram ram = null;
            SoundCard soundCard = null;
            Speakers speakers = null;
            VideoCard videoCard = null;
            place = placeRepo.findById(dto.getPlaceId()).get();
            systemblockModel = systemBlockModelRepo.findById(dto.getModelId()).get();
            motherboard = motherboardRepo.findById(dto.getMotherboardId()).get();
            cdDrive = cdDriveRepo.findById(dto.getCdDriveId()).get();
            cpu = cpuRepo.findById(dto.getCpuId()).get();
            hdd = hddRepo.findById(dto.getHddId()).get();
            keyboard = keyboardRepo.findById(dto.getKeyboardId()).get();
            lanCard = lanCardRepo.findById(dto.getLanCardId()).get();
            mouse = mouseRepo.findById(dto.getMouseId()).get();
            ram = ramRepo.findById(dto.getRamId()).get();
            soundCard = soundCardRepo.findById(dto.getSoundCardId()).get();
            speakers = speakersRepo.findById(dto.getSpeakersId()).get();
            videoCard = videoCardRepo.findById(dto.getVideoCardId()).get();
            
            
            
            for(int i = 0; i < dto.getOperationSystemId().size(); i++) {
                OperationSystem operationSystem = operationSystemService.getOperationSystem(dto.getOperationSystemId().get(i));
                systemBlock.getOperationSystems().add(operationSystem);
            }
            
            
            systemBlock.setMotherBoard(motherboard);
            systemBlock.setCdDrive(cdDrive);
            systemBlock.setCpu(cpu);
            systemBlock.setHdd(hdd);
            systemBlock.setKeyboard(keyboard);
            systemBlock.setLanCard(lanCard);
            systemBlock.setMouse(mouse);
            systemBlock.setRam(ram);
            systemBlock.setSystemBlockModel(systemblockModel);
            systemBlock.setSoundCard(soundCard);
            systemBlock.setSpeakers(speakers);
            systemBlock.setVideoCard(videoCard);
            switch (dto.getStatus()) {
            case "REPAIR":
                systemBlock.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                systemBlock.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                systemBlock.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                systemBlock.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                systemBlock.setStatus(Status.DEFECTIVE);
                break;
        }
            systemBlock.setInventaryNumber(dto.getInventaryNumber());
            systemBlock.setSerialNumber(dto.getSerialNumber());
            systemBlock.setYearCreated(dto.getYearCreated());
            systemBlock.setDateExploitationBegin(dto.getDateExploitationBegin());
            systemBlock.setPlace(place);
            
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(systemBlock);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(systemBlock)));
                contract.setContractNumber("00000000");
                
            }
            systemBlock.setContract(contract);
            systemBlock.setNameFromOneC(dto.getNameFromOneC());
            Set<OperationSystem> operationSystemsFromDtoes = operationSystemService.getOperationSystemsFromDtoes(dto.getOperationSystemId());
            systemBlock.setOperationSystems(operationSystemsFromDtoes);
            systemBlock.setIpAdress(dto.getIpAdress());
            systemBlock.setDateUpgrade(dto.getDateUpgrade());
            systemBlock.setNumberRoom(dto.getNumberRoom());
            systemBlockRepo.save(systemBlock);
            
        }
    }

    @Override
    public void updateSvtObj(SvtSystemBlockDTO dto) {
        SystemBlock systemBlockFromDB = systemBlockRepo.findById(dto.getId()).get();
        Place place = null;
            SystemBlockModel systemblockModel = null;
            Motherboard motherboard = null;
            CdDrive cdDrive = null;
            Cpu cpu = null;
            Hdd hdd = null;
            Keyboard keyboard = null;
            LanCard lanCard = null;
            Mouse mouse = null;
            Ram ram = null;
            SoundCard soundCard = null;
            Speakers speakers = null;
            VideoCard videoCard = null;
            place = placeRepo.findById(dto.getPlaceId()).get();
            systemblockModel = systemBlockModelRepo.findById(dto.getModelId()).get();
            motherboard = motherboardRepo.findById(dto.getMotherboardId()).get();
            cdDrive = cdDriveRepo.findById(dto.getCdDriveId()).get();
            cpu = cpuRepo.findById(dto.getCpuId()).get();
            hdd = hddRepo.findById(dto.getHddId()).get();
            keyboard = keyboardRepo.findById(dto.getKeyboardId()).get();
            lanCard = lanCardRepo.findById(dto.getLanCardId()).get();
            mouse = mouseRepo.findById(dto.getMouseId()).get();
            ram = ramRepo.findById(dto.getRamId()).get();
            soundCard = soundCardRepo.findById(dto.getSoundCardId()).get();
            speakers = speakersRepo.findById(dto.getSpeakersId()).get();
            videoCard = videoCardRepo.findById(dto.getVideoCardId()).get();
            systemBlockFromDB.setMotherBoard(motherboard);
            systemBlockFromDB.setCdDrive(cdDrive);
            systemBlockFromDB.setCpu(cpu);
            systemBlockFromDB.setHdd(hdd);
            systemBlockFromDB.setKeyboard(keyboard);
            systemBlockFromDB.setLanCard(lanCard);
            systemBlockFromDB.setMouse(mouse);
            systemBlockFromDB.setRam(ram);
            systemBlockFromDB.setSystemBlockModel(systemblockModel);
            systemBlockFromDB.setSoundCard(soundCard);
            systemBlockFromDB.setSpeakers(speakers);
            systemBlockFromDB.setVideoCard(videoCard);
            switch (dto.getStatus()) {
            case "REPAIR":
                systemBlockFromDB.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                systemBlockFromDB.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                systemBlockFromDB.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                systemBlockFromDB.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                systemBlockFromDB.setStatus(Status.DEFECTIVE);
                break;
        }
            systemBlockFromDB.setInventaryNumber(dto.getInventaryNumber());
            systemBlockFromDB.setSerialNumber(dto.getSerialNumber());
            systemBlockFromDB.setYearCreated(dto.getYearCreated());
            systemBlockFromDB.setDateExploitationBegin(dto.getDateExploitationBegin());
            systemBlockFromDB.setPlace(place);
            Set<OperationSystem> operationSystemsFromDtoes = operationSystemService.getOperationSystemsFromDtoes(dto.getOperationSystemId());
            systemBlockFromDB.setOperationSystems(operationSystemsFromDtoes);
            systemBlockFromDB.setIpAdress(dto.getIpAdress());
            systemBlockFromDB.setNameFromOneC(dto.getNameFromOneC());
            systemBlockFromDB.setDateUpgrade(dto.getDateUpgrade());
            systemBlockFromDB.setNumberRoom(dto.getNumberRoom());
            systemBlockRepo.save(systemBlockFromDB);
    }

}
