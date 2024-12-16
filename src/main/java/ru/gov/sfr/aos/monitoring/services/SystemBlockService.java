/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.CdDrive;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Keyboard;
import ru.gov.sfr.aos.monitoring.entities.LanCard;
import ru.gov.sfr.aos.monitoring.entities.Location;
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
import ru.gov.sfr.aos.monitoring.enums.UnitHdd;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
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
    public void createSvtObj(SvtSystemBlockDTO dto) throws ObjectAlreadyExists {

            if(systemBlockRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
                throw new ObjectAlreadyExists("Системный блок с таким серийным номером уже есть в базе данных");
            
        } else if(systemBlockRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber())) {
            throw new ObjectAlreadyExists("Системный блок с таким инвентарным номером уже есть в базе данных");
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
    public void updateSvtObj(SvtSystemBlockDTO dto) throws ObjectAlreadyExists {
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
            
            if(systemBlockRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
                SystemBlock checkSerial = systemBlockRepo.findBySerialNumberIgnoreCase(dto.getSerialNumber()).get(0);
                if(dto.getId() != checkSerial.getId()) {
                    throw new ObjectAlreadyExists("Системный блок с таким серийным номером уже есть в базе данных");
                } else {
                   systemBlock.setSerialNumber(dto.getSerialNumber());
                }
            } else {
                systemBlock.setSerialNumber(dto.getSerialNumber());
            } 
                
            if(systemBlockRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber())) {
                SystemBlock checkInventary = systemBlockRepo.findByInventaryNumberIgnoreCase(dto.getInventaryNumber()).get(0);
                if(dto.getId() != checkInventary.getId()) {
                    throw new ObjectAlreadyExists("Системный блок с таким инвентарным номером уже есть в базе данных");
                } else {
                   systemBlock.setInventaryNumber(dto.getInventaryNumber());
                }
            } else {
                systemBlock.setInventaryNumber(dto.getInventaryNumber());
            } 
            
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
    
    
}
