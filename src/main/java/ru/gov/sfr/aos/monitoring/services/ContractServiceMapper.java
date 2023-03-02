/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.ContractDTO;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class ContractServiceMapper {
    
    @Autowired
    private ContractServiceImpl contractServiceImpl;
    
    public void createNewContract(ContractDTO dto) {
        Long contractNumber;
        Contract contract = new Contract();
        if(dto.contractNumber != null && !dto.contractNumber.isBlank()) {
            contractNumber = Long.parseLong(dto.contractNumber);
            contract.setContractNumber(contractNumber); 
        }
        if(dto.dateStartContract != null) {
            contract.setDateStartContract(dto.dateStartContract);
        }
        if(dto.dateEndContract != null) {
            contract.setDateEndContract(dto.dateEndContract);
        }
        if(dto.objectBuing.equals("printer")) {
            Printer printer = new Printer();
            List<ObjectBuing> printers = new ArrayList<>();
            contract.setObjectBuing(printers);
            if(dto.modelPrinter != null) {
                printer.setModel(dto.modelPrinter);
            }
            if(dto.serialNumberPrinter != null) {
                printer.setSerialNumber(dto.serialNumberPrinter);
            }
            if(dto.inventoryNumberPrinter != null) {
                printer.setInventoryNumber(dto.inventoryNumberPrinter);
            }
            if(dto.isSwitched.equals("true")) {
                Cartridge cartridge = new Cartridge();
                List<Cartridge> cartridges = new ArrayList<>();
                 printer.setCartridge(cartridges);
                if(dto.typeCartridge != null) {
                   switch (dto.typeCartridge) {
                       case ("ORIGINAL"):
                           cartridge.setType(CartridgeType.ORIGINAL);
                           break;
                           
                       case ("ANALOG"):
                           cartridge.setType(CartridgeType.ANALOG);
                           break;
                           
                       case ("START"):
                           cartridge.setType(CartridgeType.START);
                           break;
                   } 
                   
                } else {
                    cartridge.setType(CartridgeType.START);
                }
                
                if(dto.modelCartridge != null)  {
                    cartridge.setModel(dto.modelCartridge);
                }
                
                if(dto.nominalResource != null) {
                    cartridge.setDefaultNumberPrintPage(Integer.parseInt(dto.nominalResource));
                }
                cartridge.setPrinter(printer);
                cartridges.add(cartridge);
               
            }
            printer.setContract(contract);
            printers.add(printer);
            
            
           
        }
        
        contractServiceImpl.saveContract(contract);
        
    }
    
    
}
