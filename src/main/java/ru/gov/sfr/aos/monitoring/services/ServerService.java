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
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Cpu;
import ru.gov.sfr.aos.monitoring.entities.Hdd;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.OperationSystem;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Ram;
import ru.gov.sfr.aos.monitoring.entities.Server;
import ru.gov.sfr.aos.monitoring.entities.ServerModel;
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
    public void createSvtObj(SvtServerDTO dto) {
        if(null != dto.getId()) {
            if(serverRepo.existsById(dto.getId())) {
                System.out.println("такой сервер уже есть в базе данных");
            }
        }else {
                Server server = new Server();
                Place place = null;
                ServerModel serverModel = null;
                Contract contract = null;
                Cpu cpu = null;
                Set<Hdd> hddList = new HashSet<>();
                Set<OperationSystem> operatingSystemList = new HashSet<>();
                Ram ram = null;
                place = placeRepo.findById(dto.getPlaceId()).get();
                cpu = cpuRepo.findById(dto.getCpuId()).get();
                serverModel = serverModelRepo.findById(dto.getModelId()).get();
                ram = ramRepo.findById(dto.getRamId()).get();
                if(dto.getHddIdList().size() > 0) {
                    for(Long el : dto.getHddIdList()) {
                        Hdd hddFromDto = hddRepo.findById(el).get();
                        hddList.add(hddFromDto);
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
                server.setHdd(hddList);
                server.setOperationSystems(operatingSystemList);
                server.setInventaryNumber(dto.getInventaryNumber());
                server.setSerialNumber(dto.getSerialNumber());
                server.setServerModel(serverModel);
                server.setRam(ram);
                server.setPlace(place);
                if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(server);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(server)));
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
    public void updateSvtObj(SvtServerDTO dto) {
        Server server = serverRepo.findById(dto.getId()).get();
        Place place = null;
                ServerModel serverModel = null;
                Contract contract = null;
                Cpu cpu = null;
                Set<Hdd> hddList = new HashSet<>();
                Set<OperationSystem> operatingSystemList = new HashSet<>();
                Ram ram = null;
                place = placeRepo.findById(dto.getPlaceId()).get();
                cpu = cpuRepo.findById(dto.getCpuId()).get();
                serverModel = serverModelRepo.findById(dto.getModelId()).get();
                ram = ramRepo.findById(dto.getRamId()).get();
                if(dto.getHddIdList().size() > 0) {
                    for(Long el : dto.getHddIdList()) {
                        Hdd hddFromDto = hddRepo.findById(el).get();
                        hddList.add(hddFromDto);
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
                server.setHdd(hddList);
                server.setOperationSystems(operatingSystemList);
                server.setInventaryNumber(dto.getInventaryNumber());
                server.setSerialNumber(dto.getSerialNumber());
                server.setServerModel(serverModel);
                server.setRam(ram);
                if(contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(server);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(server)));
                contract.setContractNumber("00000000");
                
            }
                server.setPlace(place);
                server.setContract(contract);
                server.setNameFromOneC(dto.getNameFromOneC());
                server.setNumberRoom(dto.getNumberRoom());
                server.setIpAdress(dto.getIpAdress());
                serverRepo.save(server);
    }
    
}
