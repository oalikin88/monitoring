/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.scanner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.exceptions.DublicateInventoryNumberException;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import ru.gov.sfr.aos.monitoring.svtobject.SvtObjService;

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
    public void createSvtObj(SvtScannerDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {

            if (scannerRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
                throw new ObjectAlreadyExists("Сканер с таким серийным номером уже есть в базе данных");
            
        } else if(!dto.isIgnoreCheck() && scannerRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())){
            throw new DublicateInventoryNumberException("Сканер с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
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
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(scanner);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(scanner)));
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
    public void updateSvtObj(SvtScannerDTO dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
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
            List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
            objectBuingFromContractDB.add(scanner);
        } else {
            contract = new Contract();
            contract.setDateEndContract(Date.from(Instant.now()));
            contract.setDateStartContract(Date.from(Instant.now()));
            contract.setObjectBuing(new ArrayList<>(Arrays.asList(scanner)));
            contract.setContractNumber("00000000");

        }
        scanner.setContract(contract);
        if(scannerRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
            List<Scanner> checkSerial = scannerRepo.findBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim());
            for(Scanner el : checkSerial) {
                if(el.getId() != dto.getId()) {
                throw new ObjectAlreadyExists("Сканер с таким серийным номером уже есть в базе данных");
            }
            }
             
        } 
        
        scanner.setSerialNumber(dto.getSerialNumber().trim());
        
       
        if(scannerRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
            List<Scanner> checkInventary = scannerRepo.findByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim());
            for(Scanner el : checkInventary) {
                if(!dto.isIgnoreCheck() && dto.getId() != el.getId()) {
                throw new DublicateInventoryNumberException("Сканер с таким инвентарным номером уже есть в базе данных. \n Вы уверены, что хотите сохранить?");
            }
            }
            
        } 
        
        scanner.setInventaryNumber(dto.getInventaryNumber().trim());
        
        
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
