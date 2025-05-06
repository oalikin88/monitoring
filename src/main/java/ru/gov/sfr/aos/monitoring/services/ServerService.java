/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

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
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.OperationSystem;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.entities.Server;
import ru.gov.sfr.aos.monitoring.entities.ServerModel;
import ru.gov.sfr.aos.monitoring.enums.UnitHdd;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.SvtServerDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.CpuRepo;
import ru.gov.sfr.aos.monitoring.repositories.HddRepo;
import ru.gov.sfr.aos.monitoring.repositories.OperationSystemRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.RamRepo;
import ru.gov.sfr.aos.monitoring.repositories.ServerModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.ServerRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class ServerService extends SvtObjService <Server, ServerRepo, SvtServerDTO>{
    @Autowired
    private ServerRepo serverRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private ServerModelRepo serverModelRepo;
    @Autowired
    private CpuRepo cpuRepo;
    @Autowired
    private HddRepo hddRepo;
    @Autowired
    private OperationSystemRepo operationSystemRepo;
    @Autowired
    private RamRepo ramRepo;
    
    
    @Override
    public void createSvtObj(SvtServerDTO dto) throws ObjectAlreadyExists {

            if(serverRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
                throw new ObjectAlreadyExists("Сервер с таким серийным номером уже есть в базе данных");
        } else if(serverRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())) {
            throw new ObjectAlreadyExists("Сервер с таким инвентарным номером уже есть в базе данных");
        }else {
                Server server = new Server();
                Place place = null;
                ServerModel serverModel = null;
                Contract contract = null;
                Cpu cpu = null;
                Set<Hdd> hddSet = new HashSet<>();
                Set<OperationSystem> operatingSystemList = new HashSet<>();
                Ram ram = null;
                Hdd hdd = null;
                place = placeRepo.findById(dto.getPlaceId()).get();
                
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
                
                if(null == dto.getModelId()) {
                    if(serverModelRepo.existsByModelIgnoreCase("no name")) {
                        serverModel = serverModelRepo.findByModelIgnoreCase("no name").get(0);
                    } else {
                        serverModel = new ServerModel("no name");
                    }
                } else {
                    serverModel = serverModelRepo.findById(dto.getModelId()).get();
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
                if(dto.getOperationSystemId().size() > 0) {
                    for(Long el : dto.getOperationSystemId()) {
                        OperationSystem osFromDto = operationSystemRepo.findById(el).get();
                        operatingSystemList.add(osFromDto);
                    }
                }
                
                switch (dto.getStatus()) {
                    case "REPAIR":
                        server.setStatus(Status.REPAIR);
                        break;
                    case "MONITORING":
                        server.setStatus(Status.MONITORING);
                        break;
                    case "UTILIZATION":
                        server.setStatus(Status.UTILIZATION);
                        break;
                    case "OK":
                        server.setStatus(Status.OK);
                        break;
                    case "DEFECTIVE":
                        server.setStatus(Status.DEFECTIVE);
                        break;
                }
                
                server.setCpu(cpu);
                server.setCpuAmount(dto.getCpuAmount());
                server.setDateExploitationBegin(dto.getDateExploitationBegin());
                server.setDateUpgrade(dto.getDateUpgrade());
                server.setYearCreated(dto.getYearCreated());
                server.setHdd(hddSet);
                server.setOperationSystems(operatingSystemList);
                server.setInventaryNumber(dto.getInventaryNumber());
                server.setSerialNumber(dto.getSerialNumber());
                server.setServerModel(serverModel);
                server.setRam(ram);
                server.setPlace(place);
                
                if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(server);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(server)));
                contract.setContractNumber("00000000");
                
            }
                server.setContract(contract);
                server.setNameFromOneC(dto.getNameFromOneC());
                server.setNumberRoom(dto.getNumberRoom());
                server.setIpAdress(dto.getIpAdress());
                serverRepo.save(server);
            }
         
    }

    @Override
    public void updateSvtObj(SvtServerDTO dto) throws ObjectAlreadyExists {
        Server server = serverRepo.findById(dto.getId()).get();
        Place place = null;
                ServerModel serverModel = null;
                Contract contract = null;
                Cpu cpu = null;
                Set<Hdd> hddSet = new HashSet<>();
                Set<OperationSystem> operatingSystemList = new HashSet<>();
                Ram ram = null;
                place = placeRepo.findById(dto.getPlaceId()).get();
                Hdd hdd = null;
                
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
                
                 if(null == dto.getModelId()) {
                    if(serverModelRepo.existsByModelIgnoreCase("no name")) {
                        serverModel = serverModelRepo.findByModelIgnoreCase("no name").get(0);
                    } else {
                        serverModel = new ServerModel("no name");
                    }
                } else {
                    serverModel = serverModelRepo.findById(dto.getModelId()).get();
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
            
                if(dto.getOperationSystemId().size() > 0) {
                    for(Long el : dto.getOperationSystemId()) {
                        OperationSystem osFromDto = operationSystemRepo.findById(el).get();
                        operatingSystemList.add(osFromDto);
                    }
                }
                switch (dto.getStatus()) {
            case "REPAIR":
                server.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                server.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                server.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                server.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                server.setStatus(Status.DEFECTIVE);
                break;
        }
                server.setCpu(cpu);
                server.setCpuAmount(dto.getCpuAmount());
                server.setDateExploitationBegin(dto.getDateExploitationBegin());
                server.setDateUpgrade(dto.getDateUpgrade());
                server.setYearCreated(dto.getYearCreated());
                server.setHdd(hddSet);
                server.setOperationSystems(operatingSystemList);
                
                if(serverRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber())) {
                List<Server> checkSerial = serverRepo.findBySerialNumberIgnoreCase(dto.getSerialNumber());
                for(Server el : checkSerial) {
                    if(dto.getId() != el.getId()) {
                    throw new ObjectAlreadyExists("Сервер с таким серийным номером уже есть в базе данных");
                }
                }
                 
            } 
                server.setSerialNumber(dto.getSerialNumber());
                
                if(serverRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber())) {
                List<Server> checkInventary = serverRepo.findByInventaryNumberIgnoreCase(dto.getInventaryNumber());
                for(Server el : checkInventary) {
                    if(dto.getId() != el.getId()) {
                    throw new ObjectAlreadyExists("Сервер с таким инвентарным номером уже есть в базе данных");
                }
                }
                 
            } 
                server.setInventaryNumber(dto.getInventaryNumber());
             
                
                server.setServerModel(serverModel);
                server.setRam(ram);
                if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(server);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(server)));
                contract.setContractNumber("00000000");
                
            }
                server.setPlace(place);
                server.setContract(contract);
                server.setNameFromOneC(dto.getNameFromOneC());
                server.setNumberRoom(dto.getNumberRoom());
                server.setIpAdress(dto.getIpAdress());
                serverRepo.save(server);
    }
    
    
    public List<Server> getServerByFilter(FilterDto dto) {

        List<Server> result = serverRepo.findServerByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }

    public Map<Location, List<Server>> getServerByPlaceAndFilter(List<Server> input) {
        Map<Location, List<Server>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Server el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }

    public Map<Location, List<Server>> getServerByPlaceTypeAndFilter(PlaceType placeType, List<Server> input) {
        Map<Location, List<Server>> collect = (Map<Location, List<Server>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Server el) -> el.getPlace()
                        .getLocation()));

        return collect;
    }

    @Override
    public void svtObjToArchive(ArchivedDto dto) {
        Optional<Server> serverOptional = serverRepo.findById(dto.getId());
        if(serverOptional.isPresent()) {
            Server server = serverOptional.get();
            if (server.getHdd().size() > 0) {
                Set<Hdd> hddSetArchivedOn = server.getHdd().stream().filter(el -> !el.getModel().equalsIgnoreCase("не указано")).peek(e -> e.setArchived(true)).collect(Collectors.toSet());
                server.setHdd(hddSetArchivedOn);
            }
            server.setArchived(true);
            serverRepo.save(server);
            
        }
    }

    
}
