/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.ModelCartridgeByModelPrinters;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */

@Component
public class PrinterOutInfoService {
    @Autowired
    private CartridgeMapper cartridgeMapper;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private PrinterRepo printerRepo;
    
    public Map<String, List<ModelDTO>> outInfo() {
        List<CartridgeModel> findAllCartridgeModels = cartridgeModelRepo.findAll();
        Map<String, List<ModelDTO>> map = new HashMap<>();
       
        for(CartridgeModel cartridgeModel : findAllCartridgeModels) {
            List<Model> modelsPrintersByCurrentCartridgeModel = cartridgeModel.getModelsPrinters();
            List<ModelDTO> list = new ArrayList<>();
            String key = "";
          
            for(int i = 0; i < modelsPrintersByCurrentCartridgeModel.size(); i++) {
                ModelDTO modelPrinterDTO = new ModelDTO();
                modelPrinterDTO.setIdModel(modelsPrintersByCurrentCartridgeModel.get(i).getId());
                modelPrinterDTO.setModel(modelsPrintersByCurrentCartridgeModel.get(i).getName());
                modelPrinterDTO.setManufacturer(modelsPrintersByCurrentCartridgeModel.get(i).getManufacturer().getName());
                list.add(modelPrinterDTO);
                key += modelPrinterDTO.getManufacturer() + " " + modelPrinterDTO.getModel();
                if(i < modelsPrintersByCurrentCartridgeModel.size() - 1) {
                    key += " / ";
                }
            }
            if(list.size() > 0 || !key.isBlank()) {
                map.put(key, list);
            }
            
            
        }
        
        
      List<Model> modelPrintersFromRepo = modelPrinterRepo.findAll();
      List<ModelDTO> dtoList = new ArrayList<>();
      
      for(Model model : modelPrintersFromRepo) {
          ModelDTO dto = new ModelDTO();
          dto.setIdModel(model.getId());
          dto.setModel(model.getName());
          dto.setManufacturer(model.getManufacturer().getName());
          dtoList.add(dto);
      }
        
        
        for(Map.Entry<String, List<ModelDTO>> entry : map.entrySet()) {
            for(ModelDTO modelDTO : entry.getValue()) {
                  for(int i = 0; i < dtoList.size(); i++) {
                   if(modelDTO.getIdModel() == dtoList.get(i).getIdModel()) {
                       dtoList.remove(i);
                   }
                }
            }
        }
        
    
      for(ModelDTO dto : dtoList) {
         List<ModelDTO> list = new ArrayList<>();
         list.add(dto);
         map.put(dto.getManufacturer() + " " + dto.getModel(), list);
      }
      
        
        
        return map;
    }
    
    
    public  List<PrinterDTO> showPrintersByModelsAndLocation(List<Long> list, Long location) {
        
        Optional<Location> findLocationById = locationRepo.findById(location);
        LocationDTO locDto = new LocationDTO();
        locDto.setId(findLocationById.get().getId());
        locDto.setName(findLocationById.get().getName());
        List<PrinterDTO> dtoes = new ArrayList<>();
        for(Long l : list) {
            List<Printer> findPrintersByLocationIdAndModelId = printerRepo.findByLocationIdAndModelId(location, l);
            for(Printer printer : findPrintersByLocationIdAndModelId) {
                PrinterDTO dto = new PrinterDTO();
                dto.setId(printer.getId());
                dto.setModel(printer.getModel().getName());
                dto.setManufacturer(printer.getManufacturer().getName());
                dto.setInventaryNumber(printer.getInventoryNumber());
                dto.setSerialNumber(printer.getInventoryNumber());
                dto.setContractNumber(printer.getContract().getContractNumber());
                dto.setLocation(printer.getLocation().getName());
                dto.setStartContract(printer.getContract().getDateStartContract());
                dto.setEndContract(printer.getContract().getDateStartContract());
                dtoes.add(dto);
            }
        }
        return dtoes;
    }
    
}
