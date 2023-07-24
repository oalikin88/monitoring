/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.ModelCartridgeByModelPrinters;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.models.ModelPrinterByModelCartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
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
            cartridgeModelDTO.setModel(list.get(i).getModel());
            cartridgeModelDTO.setType(list.get(i).getType().getName());
            dtoes.add(cartridgeModelDTO);
        }
        return dtoes;
    }

    public void saveCartridgeModel(CartridgeModelDTO dto) {
        CartridgeType currentType = null;
        CartridgeModel model = null;
        List<Model> modelsPrinters = new ArrayList<>();
        if (dto.type.trim().toLowerCase().equals(CartridgeType.ORIGINAL.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ORIGINAL;
        } else if (dto.type.trim().toLowerCase().equals(CartridgeType.ANALOG.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ANALOG;
        } else {
            currentType = CartridgeType.START;
        }

        List<CartridgeModel> list = cartridgeModelRepo.findByModel(dto.getModel());
        if (list.size() == 0) {
            model = new CartridgeModel();

            model.setModel(dto.getModel());
            model.setType(currentType);
            long resource = Long.parseLong(dto.getResource());
            model.setDefaultNumberPrintPage(resource);

            model.setModelsPrinters(modelsPrinters);
            cartridgeModelRepo.save(model);
        } else {
            System.out.println("Модель " + dto.model + " уже есть в базе данных");
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
                    dto.setCartridge("отсутствует"); // нужно дорабатывать до получения id установленного картриджа в данный момент
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
                
                
                for(ModelDTO modelDTO : modelPrinterDTOes) {
                    for(Printer printer : storage.getPrinters()) {
                        if(modelDTO.getIdModel() == printer.getModel().getId()) {
                            printersID.add(printer.getId());
                        }
                    }
                }
                
                for (Cartridge cartridge : cartridges) {
                    
                    if (cartridgeModel.getId() == cartridge.getModel().getId()) {
                        
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

}
