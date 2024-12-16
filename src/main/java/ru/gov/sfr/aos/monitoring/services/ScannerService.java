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
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.entities.ScannerModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;
import ru.gov.sfr.aos.monitoring.repositories.ScannerModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.ScannerRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class ScannerService extends SvtObjService<Scanner, ScannerRepo, SvtScannerDTO> {

    @Autowired
    private ScannerRepo scannerRepo;
    @Autowired
    private ScannerModelRepo scannerModelRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ContractRepo contractRepo;

    @Override
    public void createSvtObj(SvtScannerDTO dto) throws ObjectAlreadyExists {

            if (scannerRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
                throw new ObjectAlreadyExists("Сканер с таким серийным номером уже есть в базе данных");
            
        } else if(scannerRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())){
            throw new ObjectAlreadyExists("Сканер с таким инвентарным номером уже есть в базе данных");
        } else {
            Scanner scanner = new Scanner();
            Place place = null;
            Contract contract = null;
            ScannerModel scannerModel = null;
            place = placeRepo.findById(dto.getPlaceId()).get();
            
            if(null == dto.getModelId()) {
                if(scannerModelRepo.existsByModelIgnoreCase("не указано")) {
                    scannerModel = scannerModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    scannerModel = new ScannerModel();
                    scannerModel.setModel("не указано");
                }
            } else {
                scannerModel = scannerModelRepo.findById(dto.getModelId()).get();
            }
            
            scanner.setPlace(place);
            scanner.setScannerModel(scannerModel);
            switch (dto.getStatus()) {
                case "REPAIR":
                    scanner.setStatus(Status.REPAIR);
                    break;
                case "MONITORING":
                    scanner.setStatus(Status.MONITORING);
                    break;
                case "UTILIZATION":
                    scanner.setStatus(Status.UTILIZATION);
                    break;
                case "OK":
                    scanner.setStatus(Status.OK);
                    break;
                case "DEFECTIVE":
                    scanner.setStatus(Status.DEFECTIVE);
                    break;
            }
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(scanner);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new HashSet<>(Arrays.asList(scanner)));
                contract.setContractNumber("00000000");

            }
            scanner.setContract(contract);
            scanner.setSerialNumber(dto.getSerialNumber());
            scanner.setInventaryNumber(dto.getInventaryNumber());
            scanner.setYearCreated(dto.getYearCreated());
            scanner.setNumberRoom(dto.getNumberRoom());
            scanner.setNameFromOneC(dto.getNameFromOneC());
            scanner.setDateExploitationBegin(dto.getDateExploitationBegin());
            scanner.setIpAdress(dto.getIpAdress());
            scannerRepo.save(scanner);
        }
    }

    @Override
    public void updateSvtObj(SvtScannerDTO dto) throws ObjectAlreadyExists {
        Scanner scanner = scannerRepo.findById(dto.getId()).get();
        Place place = null;
        Contract contract = null;
        ScannerModel scannerModel = null;
        place = placeRepo.findById(dto.getPlaceId()).get();
        
        if(null == dto.getModelId()) {
                if(scannerModelRepo.existsByModelIgnoreCase("не указано")) {
                    scannerModel = scannerModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    scannerModel = new ScannerModel();
                    scannerModel.setModel("не указано");
                }
            } else {
                scannerModel = scannerModelRepo.findById(dto.getModelId()).get();
            }
        
        scanner.setPlace(place);
        scanner.setScannerModel(scannerModel);
        switch (dto.getStatus()) {
            case "REPAIR":
                scanner.setStatus(Status.REPAIR);
                break;
            case "MONITORING":
                scanner.setStatus(Status.MONITORING);
                break;
            case "UTILIZATION":
                scanner.setStatus(Status.UTILIZATION);
                break;
            case "OK":
                scanner.setStatus(Status.OK);
                break;
            case "DEFECTIVE":
                scanner.setStatus(Status.DEFECTIVE);
                break;
        }
        if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
            contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
            Set<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
            objectBuingFromContractDB.add(scanner);
        } else {
            contract = new Contract();
            contract.setDateEndContract(Date.from(Instant.now()));
            contract.setDateStartContract(Date.from(Instant.now()));
            contract.setObjectBuing(new HashSet<>(Arrays.asList(scanner)));
            contract.setContractNumber("00000000");

        }
        scanner.setContract(contract);
        if(scannerRepo.existsBySerialNumberIgnoreCase(dto.getSerialNumber().trim())) {
            Scanner checkSerial = scannerRepo.findBySerialNumberIgnoreCase(dto.getSerialNumber().trim()).get(0);
            if(checkSerial.getId() != dto.getId()) {
                throw new ObjectAlreadyExists("Сканер с таким серийным номером уже есть в базе данных");
            } else {
                scanner.setSerialNumber(dto.getSerialNumber().trim());
            }
        } else {
             scanner.setSerialNumber(dto.getSerialNumber().trim());
        }
       
        if(scannerRepo.existsByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim())) {
            Scanner checkInventary = scannerRepo.findByInventaryNumberIgnoreCase(dto.getInventaryNumber().trim()).get(0);
            if(dto.getId() != checkInventary.getId()) {
                throw new ObjectAlreadyExists("Сканер с таким инвентарным номером уже есть в базе данных");
            } else {
                scanner.setInventaryNumber(dto.getInventaryNumber().trim());
            }
        } else {
            scanner.setInventaryNumber(dto.getInventaryNumber().trim());
        }
        
        scanner.setYearCreated(dto.getYearCreated());
        scanner.setNumberRoom(dto.getNumberRoom());
        scanner.setNameFromOneC(dto.getNameFromOneC());
        scanner.setDateExploitationBegin(dto.getDateExploitationBegin());
        scanner.setIpAdress(dto.getIpAdress());
        scannerRepo.save(scanner);
    }

       public List<Scanner> getScannerByFilter(FilterDto dto) {
      
      
        
        List<Scanner> result = scannerRepo.findScannerByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }
    
    
    
            public Map<Location, List<Scanner>> getScannerByPlaceAndFilter(List<Scanner> input) {
        Map<Location, List<Scanner>> collect = input
                .stream()
                .collect(Collectors
                        .groupingBy((Scanner el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
          public Map<Location, List<Scanner>> getScannerByPlaceTypeAndFilter(PlaceType placeType, List<Scanner> input) {
        Map<Location, List<Scanner>> collect = (Map<Location, List<Scanner>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Scanner el) -> el.getPlace()
                                .getLocation()));
        
        return collect;
    }
    
    
}
