/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.interfaces.ContractServiceInterface;
import ru.gov.sfr.aos.monitoring.repositories.ContractRepo;

/**
 *
 * @author 041AlikinOS
 */
@Validated
@Service
public class ContractServiceImpl implements ContractServiceInterface {
    
    @Autowired
    private ContractRepo contractRepo;

    
    @Override
    public List<Contract> getContracts() {
        
        return contractRepo.findAll();
    }

    @Override
    public void saveContract(Contract contract) throws ObjectAlreadyExists {
        Optional<Contract> findByContractNumber = contractRepo.findByContractNumberIgnoreCase(contract.getContractNumber().trim().toLowerCase());
        
        if(findByContractNumber.isEmpty()) {
            contractRepo.save(contract);
        } else {
            throw new ObjectAlreadyExists("Контракт с номером: " + contract.getContractNumber() + " уже есть в базе данных");
        }
        
    }
    
  
}
