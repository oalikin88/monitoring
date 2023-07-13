/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.interfaces.CartridgeServiceInterface;
import ru.gov.sfr.aos.monitoring.interfaces.ContractServiceInterface;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.ContractDTO;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.models.ModelPrinterByModelCartridgeDTO;
import ru.gov.sfr.aos.monitoring.services.CartridgeMapper;
import ru.gov.sfr.aos.monitoring.services.ContractServiceMapper;

/**
 *
 * @author 041AlikinOS
 */

@Controller
public class MainController {
    
    @Autowired
    private ContractServiceInterface contractServiceInterface;
    @Autowired
    private CartridgeServiceInterface cartridgeServiceInterface;
    @Autowired
    private ContractServiceMapper mapper;
    @Autowired
    private CartridgeMapper cartridgeMapper;

    
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
       Map<String, Map<ModelDTO, List<ModelPrinterByModelCartridgeDTO>>> showCartridgesByModelPrinterAndLocation = cartridgeMapper.showCartridgesByModelPrinterAndLocation();
        model.addAttribute("input", showCartridgesByModelPrinterAndLocation);

       return "inventories";        
    }

    
    @PostMapping("/cartridges")
    public String sendCartridges(
            @ModelAttribute ContractDTO contract) {
       
   //     mapper.createNewContract(contract);
        
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
        return new ResponseEntity<CartridgeModelDTO>(HttpStatus.OK);

}
    
    
}