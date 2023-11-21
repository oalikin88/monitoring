/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.CartridgeChoiceDto;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.DevicesByModelAndLocationDto;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeLocationForCartridges;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.ModelCartridgeByModelPrinters;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.models.ModelPrinterByModelCartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.models.PrintersByLocationandModelDto;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class CartridgeMapper {

    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private ListenerOperationService listenerOperatoionService;

    public List<CartridgeDTO> getDTO() {
        List<Cartridge> cartridges = cartridgeRepo.findAll();
        List<CartridgeDTO> dtoList = new ArrayList<>();
        for (Cartridge cart : cartridges) {
            CartridgeDTO dto = new CartridgeDTO(cart.getId(), cart.getModel().getType().getName(), cart.getModel().getModel(), cart.getLocation().getName(), Long.toString(cart.getModel().getDefaultNumberPrintPage()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<CartridgeDTO> findModelCartridgeByType(String type) {
        CartridgeType currentType;
        if (type.trim().toLowerCase().equals(CartridgeType.ORIGINAL.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ORIGINAL;
        } else if (type.trim().toLowerCase().equals(CartridgeType.ANALOG.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ANALOG;
        } else {
            currentType = CartridgeType.START;
        }
        List<CartridgeDTO> dtoes = new ArrayList<>();
        List<CartridgeModel> list = cartridgeModelRepo.findByType(currentType);

        for (int i = 0; i < list.size(); i++) {
            CartridgeDTO dto = new CartridgeDTO();
            dto.setType(list.get(i).getType().getName());
            dto.setModel(list.get(i).getModel());
            dtoes.add(dto);
        }

        return dtoes;
    }

    public List<CartridgeModelDTO> getCartridgeModels() {
        List<CartridgeModel> list = cartridgeModelRepo.findAll();
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CartridgeModelDTO cartridgeModelDTO = new CartridgeModelDTO();
            cartridgeModelDTO.setId(list.get(i).getId());
            cartridgeModelDTO.setResource(list.get(i).getDefaultNumberPrintPage().toString());
            cartridgeModelDTO.setModel(list.get(i).getModel());
            cartridgeModelDTO.setType(list.get(i).getType().getName());
            List<Long> printers = new ArrayList<>();
            for (int j = 0; j < list.get(i).getModelsPrinters().size(); j++) {
                printers.add(list.get(i).getModelsPrinters().get(j).getId());
            }
            cartridgeModelDTO.setIdModel(printers);
            dtoes.add(cartridgeModelDTO);
        }
        return dtoes;
    }

    public void saveCartridgeModel(CartridgeModelDTO dto) throws ObjectAlreadyExists {
        CartridgeType currentType = null;
        CartridgeModel model = null;
        List<Model> modelsPrinters = new ArrayList<>();
        for (Long l : dto.getIdModel()) {
            Optional<Model> findById = modelPrinterRepo.findById(l);
            if (findById.isPresent()) {
                modelsPrinters.add(findById.get());
            }
        }

        if (dto.type.trim().toLowerCase().equals(CartridgeType.ORIGINAL.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ORIGINAL;
        } else if (dto.type.trim().toLowerCase().equals(CartridgeType.ANALOG.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ANALOG;
        } else {
            currentType = CartridgeType.START;
        }
        String firstFromInput = dto.getModel().trim();
        String replaceAllBreakesFromInput = firstFromInput.replaceAll(" ", "");
        String replaceAllDeficesAndBreakesFromInput = replaceAllBreakesFromInput.replaceAll("-", "");
        
        Optional<CartridgeModel> findCartridgeModelByName = cartridgeModelRepo.findByModelIgnoreCase(firstFromInput);
        Optional<CartridgeModel> findCartridgeModelByNameReplaceDeficesAndBreakes = cartridgeModelRepo.findByModelIgnoreCase(replaceAllDeficesAndBreakesFromInput);
        if (findCartridgeModelByName.isEmpty() && findCartridgeModelByNameReplaceDeficesAndBreakes.isEmpty()) {
            model = new CartridgeModel();

            model.setModel(dto.getModel().trim());
            model.setType(currentType);
            long resource = Long.parseLong(dto.getResource().trim());
            model.setDefaultNumberPrintPage(resource);
            
            model.setModelsPrinters(modelsPrinters);
            cartridgeModelRepo.save(model);
        } else {
            throw new ObjectAlreadyExists("Модель " + dto.model + " уже есть в базе данных");
        }
    }

    public Map<String, List<CartridgeDTO>> showCatridgesByLocation() {

        Map<String, List<CartridgeDTO>> map = new HashMap<>();

        List<CartridgeModel> models = cartridgeModelRepo.findAll();

        List<Location> locations = locationRepo.findAll();

        for (int i = 0; i < locations.size(); i++) {
            List<CartridgeDTO> list = new ArrayList<>();

            List<Cartridge> cartridges = cartridgeRepo.findByLocationName(locations.get(i).getName());
            for (int j = 0; j < cartridges.size(); j++) {
                CartridgeDTO dto = new CartridgeDTO();
                if (!cartridges.get(j).getModel().getModel().isEmpty()) {
                    dto.setModel(cartridges.get(j).getModel().getModel());
                } else {
                    dto.setModel("По умолчанию");
                }
                if (!cartridges.get(j).getModel().getType().getName().isEmpty()) {
                    dto.setType(cartridges.get(j).getModel().getType().getName());
                } else {
                    dto.setType("По умолчанию");
                }

                if (!cartridges.get(j).getLocation().getName().isEmpty()) {
                    dto.setLocation(cartridges.get(j).getLocation().getName());
                } else {
                    dto.setLocation("Склад");
                }

                list.add(dto);

            }

            if (!list.isEmpty()) {
                map.put(locations.get(i).getName(), list);
            }

        }

        return map;
    }

    public Map<LocationDTO, List<List<PrinterDTO>>> showPrintersByModelAndLocations() {

        Map<Location, List<List<Printer>>> out = new HashMap<>();
        Map<LocationDTO, List<List<PrinterDTO>>> out2 = new HashMap<>();
        List<Location> locations = locationRepo.findAll();
        List<Model> models = modelPrinterRepo.findAll();
        List<Printer> findByLocationAndModel = null;
        List<Printer> printers = new ArrayList<>();
        Location currentLocation = null;
        for (int i = 0; i < locations.size(); i++) {
            currentLocation = locations.get(i);
            List<List<Printer>> temp = new ArrayList<>();
            List<List<PrinterDTO>> dtoes = new ArrayList<>();
            for (int j = 0; j < models.size(); j++) {
                temp.add(printerRepo.findByLocationAndModel(locations.get(i), models.get(j)));

            }

            out.put(currentLocation, temp);
        }

        for (Map.Entry<Location, List<List<Printer>>> entries : out.entrySet()) {
            LocationDTO locationDTO = new LocationDTO(entries.getKey().getId(), entries.getKey().getName());

            List<List<PrinterDTO>> innerDtoes = new ArrayList<>();
            for (int i = 0; i < entries.getValue().size(); i++) {
                List<PrinterDTO> list = new ArrayList<>();
                for (int j = 0; j < entries.getValue().get(i).size(); j++) {
                    PrinterDTO dto = new PrinterDTO();
                    dto.setId(entries.getValue().get(i).get(j).getId());

                    List<CartridgeDTO> cartridgesForPrinter = new ArrayList<>();
                    Set<Cartridge> cartridges = entries.getValue().get(i).get(j).getCartridge();
                    for (Cartridge car : cartridges) {
                        CartridgeDTO cartDto = new CartridgeDTO();
                        cartDto.setContract(car.getContract().getId());
                        cartDto.setContractNumber(car.getContract().getContractNumber());
                        cartDto.setId(car.getId());
                        cartDto.setLocation(car.getLocation().getName());
                        cartDto.setDateEndExploitation(car.getDateEndExploitation());
                        cartDto.setDateStartExploitation(car.getDateStartExploitation());
                        cartDto.setType(car.getModel().getType().getName());
                        cartDto.setResource(car.getModel().getDefaultNumberPrintPage().toString());
                        cartDto.setUtil(car.isUtil());
                        cartDto.setModel(car.getModel().getModel());
                    }

                    dto.setCartridge(cartridgesForPrinter); // нужно дорабатывать до получения id установленного картриджа в данный момент
                    dto.setInventaryNumber(entries.getValue().get(i).get(j).getInventoryNumber());
                    dto.setSerialNumber(entries.getValue().get(i).get(j).getSerialNumber());
                    dto.setManufacturer(entries.getValue().get(i).get(j).getManufacturer().getName());
                    dto.setModel(entries.getValue().get(i).get(j).getModel().getName());
                    dto.setLocation(entries.getValue().get(i).get(j).getLocation().getName());
                    list.add(dto);
                }
                innerDtoes.add(list);
            }
            out2.put(locationDTO, innerDtoes);
        }

        return out2;
    }

    public Map<LocationDTO, Map<ModelDTO, List<ModelPrinterByModelCartridgeDTO>>> showCartridgesByModelPrinterAndLocation() {

        List<Location> locations = locationRepo.findAll();

        Map<ModelDTO, List<ModelPrinterByModelCartridgeDTO>> out2 = null;
        Map<LocationDTO, Map<ModelDTO, List<ModelPrinterByModelCartridgeDTO>>> out3 = new HashMap<>();
        for (Location storage : locations) {
            LocationDTO locationDTO = new LocationDTO(storage.getId(), storage.getName());
            out2 = new HashMap<>();
            Set<Cartridge> cartridges = storage.getCartridges();

            List<Model> findAllModelsPrinters = modelPrinterRepo.findAll();
            List<ModelPrinterByModelCartridgeDTO> out = null;

            for (Model model : findAllModelsPrinters) {

                ModelDTO modelDTO = new ModelDTO(model.getId(), model.getName(), model.getManufacturer().getName());
                out = new ArrayList<>();
                for (CartridgeModel cartridgeModel : model.getModelCartridges()) {
                    for (Cartridge cartridge : cartridges) {
                        if (cartridge.getModel().getId() == cartridgeModel.getId()) {
                            ModelPrinterByModelCartridgeDTO dto = new ModelPrinterByModelCartridgeDTO();
                            dto.setModelPrinter(model.getName());
                            dto.setManufacturer(model.getManufacturer().getName());
                            dto.setModelCartridge(cartridgeModel.getModel());
                            dto.setIdCartridge(cartridge.getId());
                            boolean duplicate = false;
                            for (ModelPrinterByModelCartridgeDTO modelPrinterByModelCartridgeDTO : out) {
                                if (cartridge.getId() == modelPrinterByModelCartridgeDTO.getIdCartridge()) {
                                    duplicate = true;
                                }
                            }
                            if (!duplicate) {
                                out.add(dto);
                            }

                        }
                    }
                }

                out2.put(modelDTO, out);
            }
            out3.put(locationDTO, out2);

        }

        return out3;
    }

    public Map<LocationDTO, List<ModelCartridgeByModelPrinters>> showCartridgesAndPrintersByModelAndLocation() {
        List<Location> locations = locationRepo.findAll();

        Map<LocationDTO, List<ModelCartridgeByModelPrinters>> out = new HashMap<>();
        ModelCartridgeByModelPrinters dto = null;

        for (Location storage : locations) {
            LocationDTO locationDTO = new LocationDTO(storage.getId(), storage.getName());
            Set<Cartridge> cartridges = storage.getCartridges();

            List<CartridgeModel> findAllModelsCartrtridge = cartridgeModelRepo.findAll();
            List<ModelCartridgeByModelPrinters> list = new ArrayList<>();
            for (CartridgeModel cartridgeModel : findAllModelsCartrtridge) {
                List<Long> cartridgesID = new ArrayList<>();
                List<Long> printersID = new ArrayList<>();
                List<Model> models = cartridgeModel.getModelsPrinters();
                dto = new ModelCartridgeByModelPrinters();
                List<ModelDTO> modelPrinterDTOes = new ArrayList<>();
                for (Model modelPrinter : models) {
                    ModelDTO modelDTO = new ModelDTO(modelPrinter.getId(), modelPrinter.getName(), modelPrinter.getManufacturer().getName());
                    modelPrinterDTOes.add(modelDTO);
                }

                for (ModelDTO modelDTO : modelPrinterDTOes) {
                    for (Printer printer : storage.getPrinters()) {
                        if ((modelDTO.getIdModel() == printer.getModel().getId()) && !printer.getPrinterStatus().equals(PrinterStatus.DELETE)
                                && !printer.getPrinterStatus().equals(PrinterStatus.MONITORING) && !printer.getPrinterStatus().equals(PrinterStatus.UTILIZATION)) {
                            printersID.add(printer.getId());
                        }
                    }
                }

                for (Cartridge cartridge : cartridges) {

                    if ((cartridgeModel.getId() == cartridge.getModel().getId()) && (!cartridge.isUtil() && !cartridge.isUseInPrinter())) {

                        cartridgesID.add(cartridge.getId());

                    }
                }
                dto.setId(cartridgeModel.getId());
                dto.setModel(cartridgeModel.getModel());
                dto.setModelsPrinter(modelPrinterDTOes);
                dto.setCartridgesId(cartridgesID);
                dto.setPrintersID(printersID);
                list.add(dto);
            }

            out.put(locationDTO, list);

        }

        return out;
    }

    public Map<LocationDTO, List<CartridgeDTO>> showCartridgesByLocation(Long idLocation, Long idModel) {
        Optional<Location> findLocationById = locationRepo.findById(idLocation);
        LocationDTO locationDTO = new LocationDTO(findLocationById.get().getId(), findLocationById.get().getName());
        List<CartridgeDTO> list = new ArrayList<>();
        List<Cartridge> findCartridgesByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(idLocation, idModel, Pageable.ofSize(25));
        Map<LocationDTO, List<CartridgeDTO>> map = new HashMap<>();
        for (Cartridge cartridge : findCartridgesByLocationIdAndModelId) {
            if (!cartridge.isUtil()) {
                CartridgeDTO dto = new CartridgeDTO();
                dto.setId(cartridge.getId());
                dto.setModel(cartridge.getModel().getModel());
                dto.setType(cartridge.getModel().getType().getName());
                dto.setResource(cartridge.getModel().getDefaultNumberPrintPage().toString());
                dto.setContract(cartridge.getContract().getId());
                dto.setContractNumber(cartridge.getContract().getContractNumber().toString()); // Переделать в String
                list.add(dto);
            }
        }

        map.put(locationDTO, list);

        return map;
    }

    public CartridgeDTO getCartridge(Long id) {
        CartridgeDTO dto = new CartridgeDTO();

        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(id);

        dto.setId(findCartridgeById.get().getId());
        dto.setContract(findCartridgeById.get().getContract().getId());
        dto.setContractNumber(findCartridgeById.get().getContract().getContractNumber().toString());
        dto.setLocation(findCartridgeById.get().getLocation().getName());
        dto.setModel(findCartridgeById.get().getModel().getModel());
        dto.setType(findCartridgeById.get().getModel().getType().getName());
        dto.setUtil(findCartridgeById.get().isUtil());
        dto.setUsePrinter(findCartridgeById.get().isUseInPrinter());
        dto.setResource(findCartridgeById.get().getModel().getDefaultNumberPrintPage().toString());
        dto.setStartContract(findCartridgeById.get().getContract().getDateStartContract());
        dto.setEndContract(findCartridgeById.get().getContract().getDateEndContract());
        dto.setDateStartExploitation(findCartridgeById.get().getDateStartExploitation());
        dto.setDateEndExploitation(findCartridgeById.get().getDateEndExploitation());
        dto.setCount(findCartridgeById.get().getCount());
        dto.setStartContract(findCartridgeById.get().getContract().getDateStartContract());
        return dto;
    }

    public void changeCartridgeLocation(ChangeDeviceLocationDTO dto) {
        String location1;
        String location2;

        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getId());
        location1 = findCartridgeById.get().getLocation().getName();
        Optional<Location> findLocationByName = locationRepo.findByNameIgnoreCase(dto.location.toLowerCase());
        findCartridgeById.get().setLocation(findLocationByName.get());
        location2 = findCartridgeById.get().getLocation().getName();
        
        ListenerOperation listener = new ListenerOperation();
        Set<Cartridge> cartridges = findCartridgeById.get().getLocation().getCartridges();
        Set<Cartridge> actualCartSet = new HashSet<>();
        for (Cartridge cart : cartridges) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualCartSet.add(cart);
            }
        }
        listener.setAmountDevicesOfLocation(actualCartSet.size() + 1);

        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(findCartridgeById.get().getLocation().getId(), findCartridgeById.get().getModel().getId(), Pageable.ofSize(25));
        listener.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size() + 1);
        listener.setOperationType(OperationType.TRANSFER);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findLocationByName.get());
        listener.setModel(findCartridgeById.get().getModel());
        listener.setCurrentOperation("Перемещение из " + location1 + " в " + location2);
        cartridgeRepo.save(findCartridgeById.get());
        listenerOperatoionService.saveListenerOperation(listener);
    }

    public void utilCartridge(Long id) {
        ListenerOperation listener = new ListenerOperation();
        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(id);
        findCartridgeById.get().setUtil(true);
        Set<Cartridge> cartridges = findCartridgeById.get().getLocation().getCartridges();
        Set<Cartridge> actualCartSet = new HashSet<>();
        for (Cartridge cart : cartridges) {
            if (!cart.isUtil() && !cart.isUseInPrinter()) {
                actualCartSet.add(cart);
            }
        }
        List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(findCartridgeById.get().getLocation().getId(), findCartridgeById.get().getId(), Pageable.ofSize(25));
        listener.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size() - 1);
        listener.setModel(findCartridgeById.get().getModel());
        listener.setAmountDevicesOfLocation(actualCartSet.size() - 1);
        listener.setOperationType(OperationType.UTIL);
        listener.setDateOperation(LocalDateTime.now());
        listener.setLocation(findCartridgeById.get().getLocation());
        listener.setCurrentOperation("Списан");
        cartridgeRepo.save(findCartridgeById.get());

    }

    public List<CartridgeDTO> showCartridgesByModelPrinterAndLocation(Long idPrinter, String location) {
        List<Cartridge> cartridges = cartridgeRepo.findByLocationName(location);
        Optional<Location> findByNameIgnoreCase = locationRepo.findByNameIgnoreCase(location);
        LocationDTO locDto = new LocationDTO();
        locDto.setId(findByNameIgnoreCase.get().getId());
        locDto.setName(findByNameIgnoreCase.get().getName());
        List<CartridgeDTO> cartridgesByModelPrinter = new ArrayList<>();
        
        Optional<Model> findPrinterById = modelPrinterRepo.findById(idPrinter);
        Long idModelPrinter = findPrinterById.get().getId();
        for (Cartridge cartridge : cartridges) {
            List<Model> modelsPrinters = cartridge.getModel().getModelsPrinters();
           
            for (Model modelPrinter : modelsPrinters) {
                if (idModelPrinter == modelPrinter.getId() && (!cartridge.isUtil() && !cartridge.isUseInPrinter())) {
                    CartridgeDTO dto = new CartridgeDTO();
                    dto.setContract(cartridge.getContract().getId());
                    dto.setContractNumber(cartridge.getContract().getContractNumber());
                    dto.setId(cartridge.getId());
                    dto.setLocation(cartridge.getLocation().getName());
                    dto.setDateEndExploitation(cartridge.getDateEndExploitation());
                    dto.setDateStartExploitation(cartridge.getDateStartExploitation());
                    dto.setType(cartridge.getModel().getType().getName());
                    dto.setResource(cartridge.getModel().getDefaultNumberPrintPage().toString());
                    dto.setUtil(cartridge.isUtil());
                    dto.setModel(cartridge.getModel().getModel());
                    dto.setStartContract(cartridge.getContract().getDateStartContract());

                    cartridgesByModelPrinter.add(dto);
                }
            }
            
        }
        

        return cartridgesByModelPrinter;
    }
    
    
    public  Map<LocationDTO, List<CartridgeDTO>> getCartridgesByFilterContractNumber(DevicesByModelAndLocationDto dto) {
        Optional<Location> findLocationById = locationRepo.findById(dto.getLocation());
        LocationDTO locDto = new LocationDTO(findLocationById.get().getId(), findLocationById.get().getName());
        List<CartridgeDTO> cartDtoesList = new ArrayList<>();
        Map<LocationDTO, List<CartridgeDTO>> map = new HashMap<>();
        List<Cartridge> findLikeContractContractNumberByLocationIdAndModelId = cartridgeRepo.findByContractContractNumberAndLocationId(dto.getContractNumber(), dto.getLocation());
        Optional<Model> findPrinterById = modelPrinterRepo.findById(dto.getIdPrinter());
        Long idModelPrinter = findPrinterById.get().getId();
        for(Cartridge cart :findLikeContractContractNumberByLocationIdAndModelId) {
            List<Model> modelsPrinters = cart.getModel().getModelsPrinters();
            boolean duplicate = false;
            for (Model modelPrinter : modelsPrinters) {
            if(idModelPrinter == modelPrinter.getId() && (!cart.isUtil() && !cart.isUseInPrinter())){
            CartridgeDTO cartDto = new CartridgeDTO();
            cartDto.setContract(cart.getContract().getId());
            cartDto.setContractNumber(cart.getContract().getContractNumber());
            cartDto.setId(cart.getId());
            cartDto.setLocation(cart.getLocation().getName());
            cartDto.setDateEndExploitation(cart.getDateEndExploitation());
            cartDto.setDateStartExploitation(cart.getDateStartExploitation());
            cartDto.setType(cart.getModel().getType().getName());
            cartDto.setResource(cart.getModel().getDefaultNumberPrintPage().toString());
            cartDto.setUtil(cart.isUtil());
            cartDto.setModel(cart.getModel().getModel());
            cartDto.setStartContract(cart.getContract().getDateStartContract());
            cartDtoesList.add(cartDto);
            }
            map.put(locDto, cartDtoesList);
            }
            if(map.size() == 0) {
                map.put(locDto, cartDtoesList);
            }
        }
        return map;
    }
    
    public  List<CartridgeDTO> getCartridgesByFilterContractNumber2(DevicesByModelAndLocationDto dto, PageRequest pageRequest) {
        Optional<Location> findLocationById = locationRepo.findById(dto.getLocation());
        LocationDTO locDto = new LocationDTO(findLocationById.get().getId(), findLocationById.get().getName());
        List<CartridgeDTO> cartDtoesList = new ArrayList<>();
        Page<Cartridge> page = cartridgeRepo.findByContractNumberAndModelPrinterAndLocation(dto.getContractNumber(), dto.getLocation(), dto.getIdPrinter(), pageRequest);
        List<Cartridge> findLikeContractContractNumberByLocationIdAndModelId = page.getContent();
        for(Cartridge cart :findLikeContractContractNumberByLocationIdAndModelId) {
            CartridgeDTO cartDto = new CartridgeDTO();
            cartDto.setContract(cart.getContract().getId());
            cartDto.setContractNumber(cart.getContract().getContractNumber());
            cartDto.setId(cart.getId());
            cartDto.setLocation(cart.getLocation().getName());
            cartDto.setDateEndExploitation(cart.getDateEndExploitation());
            cartDto.setDateStartExploitation(cart.getDateStartExploitation());
            cartDto.setType(cart.getModel().getType().getName());
            cartDto.setResource(cart.getModel().getDefaultNumberPrintPage().toString());
            cartDto.setUtil(cart.isUtil());
            cartDto.setModel(cart.getModel().getModel());
            cartDto.setStartContract(cart.getContract().getDateStartContract());
            cartDtoesList.add(cartDto);
             
            
           
        
        }
        return cartDtoesList;
    }

    public List<CartridgeModelDTO> showCartridgeModelByPrinterModel(Long id) {
        List<CartridgeModel> findByModelsPrintersLikeId = cartridgeModelRepo.findByModelsPrintersId(id);
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        for (CartridgeModel cartModel : findByModelsPrintersLikeId) {
            CartridgeModelDTO dto = new CartridgeModelDTO();
            dto.setId(cartModel.getId());
            dto.setModel(cartModel.getModel());
            dto.setResource(cartModel.getDefaultNumberPrintPage().toString());
            dto.setType(cartModel.getType().getName());
            dtoes.add(dto);
        }
        return dtoes;
    }

    public List<CartridgeModelDTO> showCartridgeModelByPrinterModelAndType(String model, String type) {
        CartridgeType cartridgeType = null;
        switch (type) {
            case "ORIGINAL":
                cartridgeType = cartridgeType.ORIGINAL;
                break;
            case "ANALOG":
                cartridgeType = cartridgeType.ANALOG;
                break;
            case "START":
                cartridgeType = CartridgeType.START;
                break;

        }
        Optional<Model> findModelPrinterByName = modelPrinterRepo.findByNameIgnoreCase(model.toLowerCase());
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        Set<CartridgeModel> modelCartridges = null;
        if(findModelPrinterByName.isPresent()) {
            modelCartridges = findModelPrinterByName.get().getModelCartridges();
        }
        List<Long> idModel = new ArrayList<>(Arrays.asList(0L));
        Set<String> printr = new HashSet<>(Arrays.asList("отсутствует"));
        for (Iterator<CartridgeModel> it = modelCartridges.iterator(); it.hasNext();) {
            CartridgeModel cartModel = it.next();
            if (cartModel.getType().equals(cartridgeType)) {
                CartridgeModelDTO dto = new CartridgeModelDTO();
                dto.setId(cartModel.getId());
                dto.setModel(cartModel.getModel());
                dto.setResource(cartModel.getDefaultNumberPrintPage().toString());
                dto.setType(cartModel.getType().getName());
                dto.setIdModel(idModel);
                dto.setPrinters(printr);
                dtoes.add(dto);
                
            }
        }

        return dtoes;
    }

    public List<CartridgeDTO> showCartridgesByModelPrinter(Long idPrinter, String location) {
        List<Cartridge> cartridges = cartridgeRepo.findByLocationName(location);
        Optional<Location> findByNameIgnoreCase = locationRepo.findByNameIgnoreCase(location);
        LocationDTO locDto = new LocationDTO();
        locDto.setId(findByNameIgnoreCase.get().getId());
        locDto.setName(findByNameIgnoreCase.get().getName());
        List<CartridgeDTO> cartridgesByModelPrinter = new ArrayList<>();
        Optional<Printer> findPrinterById = printerRepo.findById(idPrinter);
        Long idModelPrinter = findPrinterById.get().getModel().getId();
        for (Cartridge cartridge : cartridges) {
            List<Model> modelsPrinters = cartridge.getModel().getModelsPrinters();
            boolean duplicate = false;
            for (Model modelPrinter : modelsPrinters) {
                if ((idModelPrinter == modelPrinter.getId()) && (!cartridge.isUtil() && !cartridge.isUseInPrinter())) {
                    CartridgeDTO dto = new CartridgeDTO();
                    dto.setContract(cartridge.getContract().getId());
                    dto.setContractNumber(cartridge.getContract().getContractNumber());
                    dto.setId(cartridge.getId());
                    dto.setLocation(cartridge.getLocation().getName());
                    dto.setDateEndExploitation(cartridge.getDateEndExploitation());
                    dto.setDateStartExploitation(cartridge.getDateStartExploitation());
                    dto.setType(cartridge.getModel().getType().getName());
                    dto.setResource(cartridge.getModel().getDefaultNumberPrintPage().toString());
                    dto.setUtil(cartridge.isUtil());
                    dto.setModel(cartridge.getModel().getModel());
                    dto.setStartContract(cartridge.getContract().getDateStartContract());

                    cartridgesByModelPrinter.add(dto);
                }
            }
        }

        return cartridgesByModelPrinter;
    }
    
    
       public List<CartridgeChoiceDto> showCartridgesForChoice(Long idPrinter, String location) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        List<Cartridge> cartridges = cartridgeRepo.findByLocationName(location);
        Optional<Location> findByNameIgnoreCase = locationRepo.findByNameIgnoreCase(location);
        LocationDTO locDto = new LocationDTO();
        locDto.setId(findByNameIgnoreCase.get().getId());
        locDto.setName(findByNameIgnoreCase.get().getName());
        List<CartridgeChoiceDto> cartridgesByModelPrinter = new ArrayList<>();
        Optional<Printer> findPrinterById = printerRepo.findById(idPrinter);
        Long idModelPrinter = findPrinterById.get().getModel().getId();
        for (Cartridge cartridge : cartridges) {
            List<Model> modelsPrinters = cartridge.getModel().getModelsPrinters();
            boolean duplicate = false;
            for (Model modelPrinter : modelsPrinters) {
                if ((idModelPrinter == modelPrinter.getId()) && (!cartridge.isUtil() && !cartridge.isUseInPrinter())) {
                    CartridgeChoiceDto dto = new CartridgeChoiceDto();
                    dto.setId(cartridge.getId());
                    
                    dto.setName("Модель: " + cartridge.getModel().getModel() + ", контракт № " +  cartridge.getContract().getContractNumber() + " от " + sdf.format(cartridge.getContract().getDateStartContract()));

                    cartridgesByModelPrinter.add(dto);
                }
            }
        }

        return cartridgesByModelPrinter;
    }

    public void changeCartridgesLocation(ChangeLocationForCartridges dto) {

        Optional<Location> findLocationById = locationRepo.findById(dto.getLocation());
        Location currentLocation = findLocationById.get();
        
        Optional<Location> beforeTransferLocation = Optional.empty();

        List<Cartridge> cartridgesChangeLocation = new ArrayList<>();
        Map<Long, Integer> mapForListener = new HashMap<>();
        for (int i = 0; i < dto.getIdCartridge().size(); i++) {
            Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(dto.getIdCartridge().get(i));
                if (findCartridgeById.isPresent()) {
                    beforeTransferLocation = Optional.of(findCartridgeById.get().getLocation());
                    findCartridgeById.get().setLocation(currentLocation);
                    cartridgeRepo.save(findCartridgeById.get());
                    cartridgesChangeLocation.add(findCartridgeById.get());
                }
        }
        
  
        
        Optional<Location> findLocById = locationRepo.findById(dto.getLocation());
            Set<Cartridge> cartridges = findLocById.get().getCartridges();
            Set<Cartridge> actualCartSet = new HashSet<>();
            for (Cartridge cart : cartridges) {
                if (!cart.isUtil() && !cart.isUseInPrinter()) {
                    actualCartSet.add(cart);
                }
            }
            
        int actualAmountSizeLocation = actualCartSet.size();
        
        
            Set<Cartridge> cartridgesFromLocationBeforeTransfer = beforeTransferLocation.get().getCartridges();
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
            List<Cartridge> findByLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(findLocById.get().getId(), entry.getKey().getId(), Pageable.ofSize(25));
            listenerOperation.setCurrentOperation("Перемещение из " + beforeTransferLocation.get().getName() + " в " + currentLocation.getName());
            listenerOperation.setLocation(currentLocation);
            listenerOperation.setOperationType(OperationType.TRANSFER);
            listenerOperation.setDateOperation(LocalDateTime.now());
            actualAmountSizeLocation = actualAmountSizeLocation + cartridgesId.size();
            listenerOperation.setAmountDevicesOfLocation(actualAmountSizeLocation);
            listenerOperation.setAmountCurrentModelOfLocation(findByLocationIdAndModelId.size());
            listenerOperation.setModel(entry.getKey());
            listenerOperation.setLocationBeforeTransfer(beforeTransferLocation.get());
            List<Cartridge> findByBeforeTransferLocationIdAndModelId = cartridgeRepo.findByLocationIdAndModelId(beforeTransferLocation.get().getId(), entry.getKey().getId(), Pageable.ofSize(25));
            listenerOperation.setAmountCurrentModelOfTransferedLocation(findByBeforeTransferLocationIdAndModelId.size());
            listenerOperation.setAmountDevicesOfTransferedLocation(actualAmountSizeLocationBeforeTransfer - cartridgesId.size());
            
            listenerOperatoionService.saveListenerOperation(listenerOperation);

        }

    }
    
    public Map<LocationDTO, List<PrintersByLocationandModelDto>> getPrintersByLocationAndModel() {
        
        List<Location> locations = locationRepo.findAll();
        List<Model> allModels = modelPrinterRepo.findAll();
        Map<LocationDTO, List<PrintersByLocationandModelDto>> map = new HashMap<>();
        for(Location loc : locations) {
            LocationDTO locDto = new LocationDTO(loc.getId(), loc.getName());
            List<PrintersByLocationandModelDto> listDtoes = new ArrayList<>();
            for(Model model : allModels) {
                List<Printer> printersByLocationAnModel = printerRepo.findByLocationNameAndModelIdAndPrinterStatusEquals(loc.getName(), model.getId(), PrinterStatus.OK);
                PrintersByLocationandModelDto printersByLocationandModelDto = new PrintersByLocationandModelDto();
                printersByLocationandModelDto.setIdModel(model.getId());
                printersByLocationandModelDto.setModelName(model.getName());
                printersByLocationandModelDto.setAmountPrinters(printersByLocationAnModel.size());
                printersByLocationandModelDto.setIdLocation(loc.getId());
                printersByLocationandModelDto.setLocationName(loc.getName());
                listDtoes.add(printersByLocationandModelDto);
            }
            map.put(locDto, listDtoes);
        }
        
        return map;
    }
}
