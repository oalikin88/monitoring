/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.interfaces.CartridgeServiceInterface;
import ru.gov.sfr.aos.monitoring.models.CartridgeChoiceDto;
import ru.gov.sfr.aos.monitoring.models.CartridgeInstallDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeLocationForCartridges;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class CartridgeService implements CartridgeServiceInterface {

    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private ListenerOperationService listenerOperationService;
    @Autowired
    private LocationRepo locationRepo;

    @Override
    @Transactional
    public void installCartridge(CartridgeInstallDTO dto) {
        ListenerOperation listener = new ListenerOperation();
        Printer findPrinterById = printerRepo.findByPrinterId(dto.getIdPrinter());
        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getIdCartridge());
        Set<Cartridge> cartridges = findPrinterById.getCartridge();
        if (!cartridges.isEmpty()) {
            for (Cartridge cartr : cartridges) {
                if (cartr.isUseInPrinter()) {
                    cartr.setDateEndExploitation(LocalDateTime.now());
                    cartr.setUseInPrinter(false);
                    cartr.setUtil(true);
                    cartr.setCount(dto.getCount());
                }
            }
        }
        findCartridgeById.get().setUseInPrinter(true);
        findCartridgeById.get().setPrinter(findPrinterById);
        findPrinterById.setCartridge(cartridges);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findCartridgeById.get().getPlace().getLocation());
        listener.setCurrentOperation("Установлен в принтер " + findPrinterById.getManufacturer().getName() + " " + findPrinterById.getModel().getName() + " серийный номер: " + findPrinterById.getSerialNumber());
        listener.setOperationType(OperationType.UTIL);
        Set<Cartridge> collectCartridgesOfLocation = cartridgeRepo.findByLocationId(findCartridgeById.get().getPlace().getLocation().getId()).stream()
                .filter(e -> !e.isUtil())
                .filter(e -> !e.isUseInPrinter())
                .collect(Collectors.toSet());
        listener.setAmountDevicesOfLocation(collectCartridgesOfLocation.size());

        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByPlaceLocationIdAndModelId(findCartridgeById.get().getPlace().getLocation().getId(), findCartridgeById.get().getModel().getId());
        List<Cartridge> collect = findByLocationIdAndModelId.stream()
                .filter(e -> !e.isUtil())
                .filter(e -> !e.isUseInPrinter())
                .collect(Collectors.toList());

        listener.setAmountCurrentModelOfLocation(collect.size());
        listener.setModel(findCartridgeById.get().getModel());
        listener.setPrinterID(dto.getIdPrinter());
        listener.setEmployeeToDoWork(dto.getEmployeeToDoWork());
        listener.setEmployeeToSetDevice(dto.getEmployeeToSetDevice());
        listener.setEmployeeMOL(dto.getEmployeeMOL());
        listener.setCartridgeID(dto.getIdCartridge());
        findCartridgeById.get().setDateStartExploitation(LocalDateTime.now());
        List<Cartridge> findAllCartridgesByModelId = cartridgeRepo.findByModelId(findCartridgeById.get().getModel().getId());
        List<Cartridge> findAllCartridgesExceptUtil = findAllCartridgesByModelId.stream()
                .filter(e -> !e.isUtil())
                .filter(el -> !el.isUseInPrinter())
                .collect(Collectors.toList());
        listener.setAmount(1);
        listener.setAmountAllCartridgesByModel(findAllCartridgesExceptUtil.size());
        printerRepo.save(findPrinterById);
        cartridgeRepo.save(findCartridgeById.get());
        listenerOperationService.saveListenerOperation(listener);

    }
    @Override
    @Transactional
    public void changeCartridgesLocation(ChangeLocationForCartridges dto) {
        Set<Cartridge> cartridgesFromLocationBeforeTransfer = cartridgeRepo.findByLocationId(dto.getFromLocation()).stream().collect(Collectors.toSet());
        Optional<Location> findLocationById = locationRepo.findById(dto.getLocation());
        Location currentLocation = findLocationById.get();
        Optional<Location> beforeTransferLocation = Optional.empty();
        if(cartridgesFromLocationBeforeTransfer.size() > 0) {
            beforeTransferLocation = Optional.of(cartridgesFromLocationBeforeTransfer.iterator().next().getPlace().getLocation());
        }
        List<Cartridge> cartridgesChangeLocation = new ArrayList<>();
        Map<Long, Integer> mapForListener = new HashMap<>();
        for (int i = 0; i < dto.getIdCartridge().size(); i++) {
            Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getIdCartridge().get(i));
            if (findCartridgeById.isPresent()) {
                findCartridgeById.get().getPlace().setLocation(currentLocation);
                cartridgeRepo.save(findCartridgeById.get());
                cartridgesChangeLocation.add(findCartridgeById.get());
            }
        }
       
        Set<Cartridge> cartridges = cartridgeRepo.findByLocationId(dto.getLocation()).stream().collect(Collectors.toSet()); // получение картриджей на переносимую локацию
        int actualAmountSizeLocation;
        
        
        Set<Cartridge> actualcartridgesFromLocationBeforeTransfer = new HashSet<>();
        for (Cartridge cart : cartridgesFromLocationBeforeTransfer) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualcartridgesFromLocationBeforeTransfer.add(cart);
            }
        }
        int actualAmountSizeLocationBeforeTransfer = actualcartridgesFromLocationBeforeTransfer.size();
        Map<CartridgeModel, List<Cartridge>> collectByCartridgeModel = cartridgesChangeLocation.stream().collect(Collectors.groupingBy(Cartridge::getModel));
        for (Map.Entry<CartridgeModel, List<Cartridge>> entry : collectByCartridgeModel.entrySet()) {
            Set<Long> cartridgesId = new HashSet<>();
            for (Cartridge cart : entry.getValue()) {
                cartridgesId.add(cart.getId());
            }
            mapForListener.put(entry.getKey().getId(), cartridgesId.size());
            ListenerOperation listenerOperation = new ListenerOperation();
            List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByPlaceLocationIdAndModelId(currentLocation.getId(), entry.getKey().getId());
            
            listenerOperation.setCurrentOperation("Перемещение из " + beforeTransferLocation.get().getName() + " в " + currentLocation.getName());
            listenerOperation.setLocation(currentLocation);
            listenerOperation.setOperationType(OperationType.TRANSFER);
            listenerOperation.setDateOperation(LocalDateTime.now());
            actualAmountSizeLocation = cartridges.size() + cartridgesId.size();
            listenerOperation.setAmountDevicesOfLocation(actualAmountSizeLocation);
            listenerOperation.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size());
            listenerOperation.setModel(entry.getKey());
            listenerOperation.setLocationBeforeTransfer(beforeTransferLocation.get());
          //  int amountCartridgesByModelBeforeTransaction = 
            List<Cartridge> findByBeforeTransferLocationIdAndModelId = cartridgeRepo.findByPlaceLocationIdAndModelId(beforeTransferLocation.get().getId(), entry.getKey().getId());
            listenerOperation.setAmountCurrentModelOfTransferedLocation(findByBeforeTransferLocationIdAndModelId.size());
            listenerOperation.setAmountDevicesOfTransferedLocation(actualAmountSizeLocationBeforeTransfer - cartridgesId.size());
            listenerOperationService.saveListenerOperation(listenerOperation);
        }
    }
    @Override
    @Transactional
    public void changeCartridgeLocation(ChangeDeviceLocationDTO dto) {
        String location1;
        String location2;
        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getId());
        location1 = findCartridgeById.get().getPlace().getLocation().getName();
        Optional<Location> findLocationByName = locationRepo.findByNameIgnoreCase(dto.location.toLowerCase());
        findCartridgeById.get().getPlace().setLocation(findLocationByName.get());
        location2 = findCartridgeById.get().getPlace().getLocation().getName();
        ListenerOperation listener = new ListenerOperation();
        Set<Cartridge> cartridges = cartridgeRepo.findByLocationId(findCartridgeById.get().getPlace().getLocation().getId()).stream().collect(Collectors.toSet());
        Set<Cartridge> actualCartSet = new HashSet<>();
        for (Cartridge cart : cartridges) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualCartSet.add(cart);
            }
        }
        listener.setAmountDevicesOfLocation(actualCartSet.size() + 1);
        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByPlaceLocationIdAndModelId(findCartridgeById.get().getPlace().getLocation().getId(), findCartridgeById.get().getModel().getId(), Pageable.ofSize(25));
        listener.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size() + 1);
        listener.setOperationType(OperationType.TRANSFER);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findLocationByName.get());
        listener.setModel(findCartridgeById.get().getModel());
        listener.setCurrentOperation("Перемещение из " + location1 + " в " + location2);
        cartridgeRepo.save(findCartridgeById.get());
        listenerOperationService.saveListenerOperation(listener);
    }
    @Override
    @Transactional
    public void utilCartridge(Long id) {
        ListenerOperation listener = new ListenerOperation();
        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(id);
        findCartridgeById.get().setUtil(true);
        Set<Cartridge> cartridges = cartridgeRepo.findByLocationId(findCartridgeById.get().getPlace().getLocation().getId()).stream().collect(Collectors.toSet());
        Set<Cartridge> actualCartSet = new HashSet<>();
        for (Cartridge cart : cartridges) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualCartSet.add(cart);
            }
        }
        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByPlaceLocationIdAndModelId(findCartridgeById.get().getPlace().getLocation().getId(), findCartridgeById.get().getId(), Pageable.ofSize(25));
        listener.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size() - 1);
        listener.setModel(findCartridgeById.get().getModel());
        listener.setAmountDevicesOfLocation(actualCartSet.size() - 1);
        listener.setOperationType(OperationType.UTIL);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findCartridgeById.get().getPlace().getLocation());
        listener.setCurrentOperation("Списан");
        cartridgeRepo.save(findCartridgeById.get());
    }
    @Override
    public List<CartridgeChoiceDto> showCartridgesForChoice(Long idPrinter, Long locationId) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        List<CartridgeChoiceDto> cartridgesByModelPrinter = new ArrayList<>();
        List<Cartridge> cartridges = cartridgeRepo.findCartridgesByLocationIdAndPrinterIdSuitable(locationId, idPrinter);
        for (Cartridge cartridge : cartridges) {
                    CartridgeChoiceDto dto = new CartridgeChoiceDto();
                    dto.setId(cartridge.getId());
                    dto.setName("Ном.код: " + cartridge.getItemCode() + ", модель: " + cartridge.getModel().getModel());
                    cartridgesByModelPrinter.add(dto);
        }
        return cartridgesByModelPrinter;
    }

     public static <T> Set<T> findCommonElements(List<T> first, List<T> second)
    {
        Set<T> common = new HashSet<>(first);
        common.retainAll(second);
        return common;
    }
}
