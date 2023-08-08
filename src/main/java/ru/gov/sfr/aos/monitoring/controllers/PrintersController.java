/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.services.PrintersMapper;

/**
 *
 * @author 041AlikinOS
 */

@Controller
public class PrintersController {
    @Autowired
    private PrintersMapper mapper;
    
    @GetMapping(value = "/showprinters")
    public String showPrinters(Model model) {
        
        List<PrinterDTO> dtoes = mapper.showPrinters();
        model.addAttribute("dtoes", dtoes);
        return "showprinters";
    }
    
    
    @GetMapping(value ="/printers")
    public String showPrintersModels(Model model) {
    
        
        Map<String, List<PrinterDTO>> dtoes = mapper.showPrintersByLocation();
           
        // Подсчёт повторяющихся элементов
        Map<List<PrinterDTO>, Map<String, Integer>> frequency = new HashMap<>();
        
        for(List<PrinterDTO> list : dtoes.values()) {                       
            Map<String, Integer> collect2 = list.stream()
                .map(e -> e.manufacturer + " " + e.model)
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
          
            frequency.put(list, collect2);
            
        }
        int count = 0;
        for(Map.Entry<List<PrinterDTO>, Map<String, Integer>> map : frequency.entrySet()) {
            ++count;
            System.out.println("\n" + "map: " + count);
            System.out.println(map.getKey());
            for(Map.Entry<String, Integer> entry : map.getValue().entrySet()) {
                
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }   
        model.addAttribute("dtoes",frequency);
        
        return "printers";
    }
    
    
    
}
