/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.CartridgeInstallDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class CartridgeService {

    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private ListenerOperationService listenerOperationService;

    public void installCartridge(CartridgeInstallDTO dto) {
        ListenerOperation listener = new ListenerOperation();
        
        
        Optional<Printer> findPrinterById = printerRepo.findById(dto.getIdPrinter());
        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getIdCartridge());
        Set<Cartridge> cartridges = findPrinterById.get().getCartridge();
        if (!cartridges.isEmpty()) {
            for (Cartridge cartr : cartridges) {
                if (cartr.isUseInPrinter()) {
                    cartr.setDateEndExploitation(LocalDateTime.now());
                    if(null != dto.getCount()) {
                        cartr.setCount(dto.getCount());
                    }
                    cartr.setUseInPrinter(false);
                }
            }
        }
        findCartridgeById.get().setUseInPrinter(true);
        findCartridgeById.get().setUtil(true);
        findCartridgeById.get().setPrinter(findPrinterById.get());
        findPrinterById.get().setCartridge(cartridges);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findCartridgeById.get().getLocation());
        listener.setCurrentOperation("Установлен в принтер " + findPrinterById.get().getManufacturer().getName() + " " + findPrinterById.get().getModel().getName() + " серийный номер: " + findPrinterById.get().getSerialNumber());
        listener.setOperationType(OperationType.UTIL);
        listener.setAmountDevicesOfLocation(findCartridgeById.get().getLocation().getCartridges().size() - 1);
        
        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(findCartridgeById.get().getLocation().getId(), findCartridgeById.get().getModel().getId());
        List<Cartridge> collect = findByLocationIdAndModelId.stream().filter(e -> !e.isUtil()).collect(Collectors.toList());
        listener.setAmountCurrentModelOfLocation(collect.size());
        listener.setModel(findCartridgeById.get().getModel());
        listener.setPrinterID(dto.getIdPrinter());
        listener.setEmployeeToDoWork(dto.getEmployeeToDoWork());
        listener.setEmployeeToSetDevice(dto.getEmployeeToSetDevice());
        listener.setEmployeeMOL(dto.getEmployeeMOL());
        listener.setCartridgeID(dto.getIdCartridge());
        findCartridgeById.get().setDateStartExploitation(LocalDateTime.now());
        printerRepo.save(findPrinterById.get());
        cartridgeRepo.save(findCartridgeById.get());
        listenerOperationService.saveListenerOperation(listener);

    }

}
