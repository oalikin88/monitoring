/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import java.util.List;
import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;

/**
 *
 * @author 041AlikinOS
 */
public interface ContractServiceInterface {

    List<Contract> getContracts();

    void saveContract(Contract contract) throws ObjectAlreadyExists;

}
