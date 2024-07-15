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
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.entities.ScannerModel;
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
    public void createSvtObj(SvtScannerDTO dto) {
        if (null != dto.getId()) {
            if (scannerRepo.existsById(dto.getId())) {
                System.out.println("такой сканер уже есть в базе данных");
            }
        } else {
            Scanner scanner = new Scanner();
            Place place = null;
            Contract contract = null;
            ScannerModel scannerModel = null;
            place = placeRepo.findById(dto.getPlaceId()).get();
            scannerModel = scannerModelRepo.findById(dto.getModelId()).get();
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
    public void updateSvtObj(SvtScannerDTO dto) {
        Scanner scanner = scannerRepo.findById(dto.getId()).get();
        Place place = null;
        Contract contract = null;
        ScannerModel scannerModel = null;
        place = placeRepo.findById(dto.getPlaceId()).get();
        scannerModel = scannerModelRepo.findById(dto.getModelId()).get();
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
