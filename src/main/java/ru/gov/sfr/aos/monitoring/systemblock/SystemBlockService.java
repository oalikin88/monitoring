/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.systemblock;

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

import ru.gov.sfr.aos.monitoring.components.CdDrive;
import ru.gov.sfr.aos.monitoring.components.CdDriveRepo;
import ru.gov.sfr.aos.monitoring.components.Cpu;
import ru.gov.sfr.aos.monitoring.components.CpuRepo;
import ru.gov.sfr.aos.monitoring.components.Hdd;
import ru.gov.sfr.aos.monitoring.components.HddRepo;
import ru.gov.sfr.aos.monitoring.components.Keyboard;
import ru.gov.sfr.aos.monitoring.components.KeyboardRepo;
import ru.gov.sfr.aos.monitoring.components.LanCard;
import ru.gov.sfr.aos.monitoring.components.LanCardRepo;
import ru.gov.sfr.aos.monitoring.components.Motherboard;
import ru.gov.sfr.aos.monitoring.components.MotherboardRepo;
import ru.gov.sfr.aos.monitoring.components.Mouse;
import ru.gov.sfr.aos.monitoring.components.MouseRepo;
import ru.gov.sfr.aos.monitoring.components.OperationSystem;
import ru.gov.sfr.aos.monitoring.components.Ram;
import ru.gov.sfr.aos.monitoring.components.RamRepo;
import ru.gov.sfr.aos.monitoring.components.SoundCard;
import ru.gov.sfr.aos.monitoring.components.SoundCardRepo;
import ru.gov.sfr.aos.monitoring.components.Speakers;
import ru.gov.sfr.aos.monitoring.components.SpeakersRepo;
import ru.gov.sfr.aos.monitoring.components.VideoCard;
import ru.gov.sfr.aos.monitoring.components.VideoCardRepo;
import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.enums.UnitHdd;
import ru.gov.sfr.aos.monitoring.exceptions.DublicateInventoryNumberException;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.services.OperationSystemService;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import ru.gov.sfr.aos.monitoring.svtobject.SvtObjService;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class SystemBlockService extends SvtObjService <SystemBlock, SystemBlockRepo, SvtSystemBlockDTO> {
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
    public void createSvtObj(SvtSystemBlockDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {

            if(systemBlockRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber())) {
                throw new ObjectAlreadyExists("Системный блок с таким серийным номером уже есть в базе данных");
            
        } else if(!dto.isIgnoreCheck() && systemBlockRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber())) {
            throw new DublicateInventoryNumberException("Системный блок с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
        } else {
            SystemBlock systemBlock = new SystemBlock();
            Place place = null;
            SystemBlockModel systemblockModel = null;
            Motherboard motherboard = null;
            CdDrive cdDrive = null;
            Cpu cpu = null;
            Set<Hdd> hddSet = new HashSet<>();
            Keyboard keyboard = null;
            LanCard lanCard = null;
            Mouse mouse = null;
            Ram ram = null;
            SoundCard soundCard = null;
            Speakers speakers = null;
            VideoCard videoCard = null;
            Hdd hdd = null;
            
            place = placeRepo.findById(dto.getPlaceId()).get();
            
            // Модель системного блока
            if(null == dto.getModelId()) {
                if(systemBlockModelRepo.existsByModelIgnoreCase("no name")) {
                    systemblockModel = systemBlockModelRepo.findByModelIgnoreCase("no name").get(0);
                } else {
                    systemblockModel = new SystemBlockModel("no name");
                }
                
            } else {
                systemblockModel = systemBlockModelRepo.findById(dto.getModelId()).get();
            }
            
            
            // Материнская плата
            if(null == dto.getMotherboardId()) {
                if(motherboardRepo.existsByModelIgnoreCase("не указано")) {
                    motherboard = motherboardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                     motherboard = new Motherboard("не указано");
                }
            } else {
                motherboard = motherboardRepo.findById(dto.getMotherboardId()).get();
            }
            
            // Привод
            if(null == dto.getCdDriveId()) {
                if(cdDriveRepo.existsByModelIgnoreCase("не указано")) {
                    cdDrive = cdDriveRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    cdDrive = new CdDrive("не указано");
                }
            } else {
                cdDrive = cdDriveRepo.findById(dto.getCdDriveId()).get();
            }
            
            
            // Процессор
            if(null == dto.getCpuId()) {
                if(cpuRepo.existsByModelIgnoreCase("не указано")) {
                    cpu = cpuRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    cpu = new Cpu("не указано");
                }
            } else {
              cpu = cpuRepo.findById(dto.getCpuId()).get();  
            }
            
            
            // Жесткий диск
            if(dto.getHddIdList().size() == 0) {
                if(hddRepo.existsByModelIgnoreCase("не указано")) {
                    hdd = hddRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    hdd = new Hdd();
                    hdd.setCapacity(0);
                    hdd.setInventaryNumber("не указано");
                    hdd.setModel("не указано");
                    hdd.setUnit(UnitHdd.GB);
                    hdd.setSerialNumber("не указано");
                    hddSet.add(hdd);
                }
            } else {
                for(Long el : dto.getHddIdList()) {
                    hdd = hddRepo.findById(el).get();
                    hddSet.add(hdd);
                }
            }
            
            // Клавиатура
            if(null == dto.getKeyboardId()) {
                if(keyboardRepo.existsByModelIgnoreCase("не указано")) {
                    keyboard = keyboardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                     keyboard = new Keyboard("не указано");
                }
            } else {
                keyboard = keyboardRepo.findById(dto.getKeyboardId()).get();
            }
            
            
            // Сетевая карта
            if(null == dto.getLanCardId()) {
                if(lanCardRepo.existsByModelIgnoreCase("не указано")) {
                    lanCard = lanCardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    lanCard = new LanCard("не указано");
                }
            } else {
                lanCard = lanCardRepo.findById(dto.getLanCardId()).get();
            }
            
            
            // Мышь
            if(null == dto.getMouseId()) {
                if(mouseRepo.existsByModelIgnoreCase("не указано")) {
                    mouse = mouseRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                     mouse = new Mouse("не указано");
                }
            } else {
                mouse = mouseRepo.findById(dto.getMouseId()).get();
            }
            
            
            // Оперативная память
            if(null == dto.getRamId()) {
                if(ramRepo.existsByModelIgnoreCase("не указао")) {
                    ram = ramRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                     ram = new Ram("не указано");
                }
            } else {
                ram = ramRepo.findById(dto.getRamId()).get();
            }
            
            // Звуковая карта
            if(null == dto.getSoundCardId()) {
                if(soundCardRepo.existsByModelIgnoreCase("не указано")) {
                    soundCard = soundCardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    soundCard = new SoundCard("не указано");
                }
            } else {
                soundCard = soundCardRepo.findById(dto.getSoundCardId()).get();
            }
                
            
            // Колонки
            if(null == dto.getSpeakersId()) {
                if(speakersRepo.existsByModelIgnoreCase("не указано")) {
                    speakers = speakersRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    speakers = new Speakers("не указано");
                }
            } else {
                speakers = speakersRepo.findById(dto.getSpeakersId()).get();
            }
            
            
            // Видеокарта
            if(null == dto.getVideoCardId()) {
                if(videoCardRepo.existsByModelIgnoreCase("не указано")) {
                    videoCard = videoCardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    videoCard = new VideoCard("не указано");
                }
            } else {
                videoCard = videoCardRepo.findById(dto.getVideoCardId()).get();
            }
            
            
            for(int i = 0; i < dto.getOperationSystemId().size(); i++) {
                OperationSystem operationSystem = operationSystemService.getOperationSystem(dto.getOperationSystemId().get(i));
                systemBlock.getOperationSystems().add(operationSystem);
            }
            
            
            systemBlock.setMotherBoard(motherboard);
            systemBlock.setCdDrive(cdDrive);
            systemBlock.setCpu(cpu);
            systemBlock.setHdd(hddSet);
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
            default: 
                systemBlock.setStatus(Status.OK);
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
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(systemBlock);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(systemBlock)));
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
    public void updateSvtObj(SvtSystemBlockDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        SystemBlock systemBlock = systemBlockRepo.findById(dto.getId()).get();
      
            Place place = null;
            SystemBlockModel systemblockModel = null;
            Motherboard motherboard = null;
            CdDrive cdDrive = null;
            Cpu cpu = null;
            Set<Hdd> hddSet = new HashSet<>();
            Keyboard keyboard = null;
            LanCard lanCard = null;
            Mouse mouse = null;
            Ram ram = null;
            SoundCard soundCard = null;
            Speakers speakers = null;
            VideoCard videoCard = null;
            Hdd hdd = null;
            place = placeRepo.findById(dto.getPlaceId()).get();
            
            // Модель системного блока
            if(null == dto.getModelId()) {
                if(systemBlockModelRepo.existsByModelIgnoreCase("no name")) {
                    systemblockModel = systemBlockModelRepo.findByModelIgnoreCase("no name").get(0);
                } else {
                    systemblockModel = new SystemBlockModel("no name");
                }
            } else {
                systemblockModel = systemBlockModelRepo.findById(dto.getModelId()).get();
            }
            
            
            // Материнская плата
            if(null == dto.getMotherboardId()) {
                if(motherboardRepo.existsByModelIgnoreCase("не указано")) {
                    motherboard = motherboardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                     motherboard = new Motherboard("не указано");
                }
            } else {
                motherboard = motherboardRepo.findById(dto.getMotherboardId()).get();
            }
            
            // Привод
            if(null == dto.getCdDriveId()) {
                if(cdDriveRepo.existsByModelIgnoreCase("не указано")) {
                    cdDrive = cdDriveRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    cdDrive = new CdDrive("не указано");
                }
            } else {
                cdDrive = cdDriveRepo.findById(dto.getCdDriveId()).get();
            }
            
            
            // Процессор
            if(null == dto.getCpuId()) {
                if(cpuRepo.existsByModelIgnoreCase("не указано")) {
                    cpu = cpuRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    cpu = new Cpu("не указано");
                }
            } else {
              cpu = cpuRepo.findById(dto.getCpuId()).get();  
            }
            
            
            // Жесткий диск
            if(dto.getHddIdList().size() == 0) {
                if(hddRepo.existsByModelIgnoreCase("не указано")) {
                    hdd = hddRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    hdd = new Hdd();
                    hdd.setCapacity(0);
                    hdd.setInventaryNumber("не указано");
                    hdd.setModel("не указано");
                    hdd.setUnit(UnitHdd.GB);
                    hdd.setSerialNumber("не указано");
                    hddSet.add(hdd);
                }
            } else {
                for(Long el : dto.getHddIdList()) {
                    hdd = hddRepo.findById(el).get();
                    hddSet.add(hdd);
                }
            }
            
            // Клавиатура
            if(null == dto.getKeyboardId()) {
                if(keyboardRepo.existsByModelIgnoreCase("не указано")) {
                    keyboard = keyboardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                     keyboard = new Keyboard("не указано");
                }
            } else {
                keyboard = keyboardRepo.findById(dto.getKeyboardId()).get();
            }
            
            
            // Сетевая карта
            if(null == dto.getLanCardId()) {
                if(lanCardRepo.existsByModelIgnoreCase("не указано")) {
                    lanCard = lanCardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    lanCard = new LanCard("не указано");
                }
            } else {
                lanCard = lanCardRepo.findById(dto.getLanCardId()).get();
            }
            
            
            // Мышь
            if(null == dto.getMouseId()) {
                if(mouseRepo.existsByModelIgnoreCase("не указано")) {
                    mouse = mouseRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                     mouse = new Mouse("не указано");
                }
            } else {
                mouse = mouseRepo.findById(dto.getMouseId()).get();
            }
            
            
            // Оперативная память
            if(null == dto.getRamId()) {
                if(ramRepo.existsByModelIgnoreCase("не указао")) {
                    ram = ramRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                     ram = new Ram("не указано");
                }
            } else {
                ram = ramRepo.findById(dto.getRamId()).get();
            }
            
            // Звуковая карта
            if(null == dto.getSoundCardId()) {
                if(soundCardRepo.existsByModelIgnoreCase("не указано")) {
                    soundCard = soundCardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    soundCard = new SoundCard("не указано");
                }
            } else {
                soundCard = soundCardRepo.findById(dto.getSoundCardId()).get();
            }
                
            
            // Колонки
            if(null == dto.getSpeakersId()) {
                if(speakersRepo.existsByModelIgnoreCase("не указано")) {
                    speakers = speakersRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    speakers = new Speakers("не указано");
                }
            } else {
                speakers = speakersRepo.findById(dto.getSpeakersId()).get();
            }
            
            
            // Видеокарта
            if(null == dto.getVideoCardId()) {
                if(videoCardRepo.existsByModelIgnoreCase("не указано")) {
                    videoCard = videoCardRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    videoCard = new VideoCard("не указано");
                }
            } else {
                videoCard = videoCardRepo.findById(dto.getVideoCardId()).get();
            }
            
            
            for(int i = 0; i < dto.getOperationSystemId().size(); i++) {
                OperationSystem operationSystem = operationSystemService.getOperationSystem(dto.getOperationSystemId().get(i));
                systemBlock.getOperationSystems().add(operationSystem);
            }
            
            
            systemBlock.setMotherBoard(motherboard);
            systemBlock.setCdDrive(cdDrive);
            systemBlock.setCpu(cpu);
            systemBlock.setHdd(hddSet);
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
            default: 
                systemBlock.setStatus(Status.OK);
                break;
        }
            
            if(systemBlockRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber())) {
                List<SystemBlock> checkSerial = systemBlockRepo.findBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber());
                for(SystemBlock el : checkSerial) {
                    if(dto.getId() != el.getId()) {
                    throw new ObjectAlreadyExists("Системный блок с таким серийным номером уже есть в базе данных");
                }
                }
                
            }
             systemBlock.setSerialNumber(dto.getSerialNumber());
             
                
            if(systemBlockRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber())) {
                List<SystemBlock> checkInventary = systemBlockRepo.findByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber());
                for(SystemBlock el : checkInventary) {
                   if(!dto.isIgnoreCheck() && dto.getId() != el.getId()) {
                    throw new DublicateInventoryNumberException("Системный блок с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
                } 
                }
                 
            } 
            
            systemBlock.setInventaryNumber(dto.getInventaryNumber());
            
            
            systemBlock.setYearCreated(dto.getYearCreated());
            systemBlock.setDateExploitationBegin(dto.getDateExploitationBegin());
            systemBlock.setPlace(place);
            
            Contract contract = null;
            if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(systemBlock);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(systemBlock)));
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

    
            public List<SystemBlock> getSystemBlockByFilter(FilterDto dto) {
      
      
        
        List<SystemBlock> result = systemBlockRepo.findSystemblockByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }
    
    
    
            public Map<Location, List<SystemBlock>> getSystemblockByPlaceAndFilter(List<SystemBlock> input) {
        Map<Location, List<SystemBlock>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((SystemBlock el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
          public Map<Location, List<SystemBlock>> getSystemblockByPlaceTypeAndFilter(PlaceType placeType, List<SystemBlock> input) {
        Map<Location, List<SystemBlock>> collect = (Map<Location, List<SystemBlock>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((SystemBlock el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }

    @Override
    public void svtObjToArchive(ArchivedDto dto) {
        Optional<SystemBlock> sysblockOptional = systemBlockRepo.findById(dto.getId());
        if(sysblockOptional.isPresent()) {
            SystemBlock systemBlock = sysblockOptional.get();
            if (systemBlock.getHdd().size() > 0) {
                Set<Hdd> hddSetArchivedOn = systemBlock.getHdd().stream().filter(el -> !el.getModel().equalsIgnoreCase("не указано")).peek(e -> e.setArchived(true)).collect(Collectors.toSet());
                systemBlock.setHdd(hddSetArchivedOn);
            }
            systemBlock.setArchived(true);
            systemBlockRepo.save(systemBlock);
            
        }
    }
    
          
    
}
