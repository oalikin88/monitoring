/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.exceptions.EntityAlreadyExistsException;
import ru.gov.sfr.aos.monitoring.interfaces.ContractServiceInterface;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeInstallDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.ChangePrinterInventaryNumberDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangePrinterSerialNumberDTO;
import ru.gov.sfr.aos.monitoring.models.ContractDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.ModelCartridgeByModelPrinters;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.services.CartridgeMapper;
import ru.gov.sfr.aos.monitoring.services.CartridgeService;
import ru.gov.sfr.aos.monitoring.services.ContractServiceMapper;
import ru.gov.sfr.aos.monitoring.services.LocationService;
import ru.gov.sfr.aos.monitoring.services.PrinterOutInfoService;
import ru.gov.sfr.aos.monitoring.services.PrintersMapper;

/**
 *
 * @author 041AlikinOS
 */

@Controller
public class MainController {
    
    @Autowired
    private ContractServiceInterface contractServiceInterface;
    @Autowired
    private ContractServiceMapper mapper;
    @Autowired
    private CartridgeMapper cartridgeMapper;
    @Autowired
    private PrinterOutInfoService printerOutInfoService;
    @Autowired
    private PrintersMapper printerMapper;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CartridgeService cartridgeService;

    
    @GetMapping("/main")
    public String getData(Model model) {        
       List<Contract> contracts = contractServiceInterface.getContracts();
       ContractDTO contract = new ContractDTO();
       model.addAttribute("contract", contract);
      
       return "main";        
    }
   
    @PostMapping(value = "/main", consumes = "application/json", produces = "application/json")
    public String sendData(
         @RequestBody
         List<Map<String, String>> printersPlusCartridges) {
        
        mapper.createNewContract(printersPlusCartridges);
        
        return "redirect:/main";
        
     }
    
    
    @GetMapping("/inventories")
    public String getInventories(Model model) {        
        Map<String, List<ModelDTO>> outInfo = printerOutInfoService.outInfo();
        Map<LocationDTO, List<ModelCartridgeByModelPrinters>> showCartridgesAndPrintersByModelAndLocation = cartridgeMapper.showCartridgesAndPrintersByModelAndLocation();
        model.addAttribute("input", showCartridgesAndPrintersByModelAndLocation);
        model.addAttribute("input2", outInfo);

       return "inventories";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    }
    
    @GetMapping("/inventoriesbylocation")
    public String getInventoriesByLocation(Model model, @RequestParam String idLocation, @RequestParam String idModel)  {
        Long location = null;
        Long modelCartridge = null;
        if(idLocation != null) {
             location = Long.parseLong(idLocation);
        } else {
            location = 1L;
        }
        
        if(idModel != null) {
            modelCartridge = Long.parseLong(idModel);
        } else {
            modelCartridge = 1L;
        }
        
        Map<LocationDTO, List<CartridgeDTO>> showCartridgesByLocation = cartridgeMapper.showCartridgesByLocation(location, modelCartridge);
        
        model.addAttribute("input", showCartridgesByLocation);
        
        
        return "cartridgesbylocation";
    }
    
    @GetMapping("/printersbylocation")
    public String getPrintersByLocation(Model model, @RequestParam String idLocation, @RequestParam List<Long> idModel)  {
        Long location = null;
        List<Long> modelPrinters = new ArrayList<>();
        if(idLocation != null) {
             location = Long.parseLong(idLocation);
        } else {
            location = 1L;
        }
        

        List<Long> list = null;
        if(!idModel.isEmpty()) {
            list = idModel;
        }
        
        
        Map<LocationDTO, List<PrinterDTO>> printersByLocation = printerMapper.getPrintersByLocation(location, list);
        
        model.addAttribute("input", printersByLocation);
        
        
        return "printersbylocation";
    }
     
    
    @PostMapping("/cartridges")
    public String sendCartridges(
            @ModelAttribute ContractDTO contract) {
       
        
        return "redirect:/cartridges";
        
     }
    
        @GetMapping(value ="/cartridges")
    public String showCartridgesByLocations(Model model) {    
        
        Map<String, List<CartridgeDTO>> dtoes = cartridgeMapper.showCatridgesByLocation();
           
        // Подсчёт повторяющихся элементов
        Map<List<CartridgeDTO>, Map<String, Integer>> frequency = new HashMap<>();
        
        for(List<CartridgeDTO> list : dtoes.values()) {                       
            Map<String, Integer> collect2 = list.stream()
                .map(e -> e.model + " " + e.type)
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
            frequency.put(list, collect2);
            
        }
        int count = 0;
        for(Map.Entry<List<CartridgeDTO>, Map<String, Integer>> map : frequency.entrySet()) {
            ++count;
            System.out.println("\n" + "map: " + count);
            System.out.println(map.getKey());
            for(Map.Entry<String, Integer> entry : map.getValue().entrySet()) {
                
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }   
        model.addAttribute("dtoes",frequency);  
        return "cartridges";
    }
    
    @GetMapping(value ="/addmodelcart")
    public String addModelCartridge(Model model) { 
        return "addmodelcartridge";

}
    @PostMapping("/addmodelcart")
    public ResponseEntity<CartridgeModelDTO> saveModelCartridge(@ModelAttribute CartridgeModelDTO dto) {

        cartridgeMapper.saveCartridgeModel(dto);
        return new ResponseEntity<CartridgeModelDTO>(dto, HttpStatus.OK);

}
    

    
    
    @GetMapping("/editprinter")
    public String getPrinter(Model model, @RequestParam Long idPrinter) {
    
        PrinterDTO printerDto = printerMapper.getPrinterById(idPrinter);
        model.addAttribute("dto", printerDto);
        return "edit";
    }
    
    @GetMapping("/editcartridge")
    public String getCartridge(Model model, @RequestParam Long idCartridge) {
        
       CartridgeDTO cartridgeDTO = cartridgeMapper.getCartridge(idCartridge);
       model.addAttribute("dto", cartridgeDTO);
       
       return "editCartridge";
    }
    
    
    @PostMapping("/editprinterlocation")
    public ResponseEntity<String> changePrinterLocation(ChangeDeviceLocationDTO dto) {
        
        printerMapper.editPrinterLocation(dto);
       return new ResponseEntity<String>("Локация успешно изменена", HttpStatus.OK) ;
    }
    
    @PostMapping("/editcartridgelocation")
    public ResponseEntity<String> changeCartridgeLocation(ChangeDeviceLocationDTO dto) {
        
        cartridgeMapper.changeCartridgeLocation(dto);
        
        
       return new ResponseEntity<String>("Локация успешно изменена", HttpStatus.OK) ;
    }
    
    
    @PostMapping("/utilCartridge")
    public ResponseEntity<String> utilCartridge(Long id) {
        
        cartridgeMapper.utilCartridge(id);
        
        
       return new ResponseEntity<String>("Картридж успешно списан", HttpStatus.OK) ;
    }
    
    
    
    
    @PostMapping("/editprinterserial")
    public ResponseEntity<String> changePrinterSerialNumber(ChangePrinterSerialNumberDTO dto) {
    
        printerMapper.editPrinterSerialNumber(dto);
        
        return new ResponseEntity<>("Серийный номер успешно изменён", HttpStatus.OK);
    }
    
        @PostMapping("/editprinterinventary")
    public ResponseEntity<String> changePrinterInventaryNumber(ChangePrinterInventaryNumberDTO dto) {
    
        printerMapper.editPrinterInventaryNumber(dto);
        
        return new ResponseEntity<>("Инвентарный номер успешно изменён", HttpStatus.OK);
    }
    
        @GetMapping("/locaions")
    public String getLocations(Model model) {
        
        List<LocationDTO> allLocations = locationService.getAllLocations();
        model.addAttribute("dto", allLocations);
       
       return "locations";
    }
    
    @PostMapping("/locations")
    public ResponseEntity<String> addLocation(String nameLocation) {
        try {
            locationService.addLocation(nameLocation);
            
        } catch (EntityAlreadyExistsException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>("локация успешно добавлена", HttpStatus.OK);
    }
    
    @PostMapping("/installcart")
    public ResponseEntity<String> installCartridgeInPrinter(CartridgeInstallDTO dto) {
    
        cartridgeService.installCartridge(dto);
        
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
    
}