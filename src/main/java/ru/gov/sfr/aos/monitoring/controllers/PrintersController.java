/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.services.DistinctByPrinterDTO;
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
        List<List<String>> buf = new ArrayList<>();
        List<List<PrinterDTO>> buf2 = new ArrayList<>();
        
        
        for(List<PrinterDTO> list : dtoes.values()) {            
            List<String> collect = list.stream().map(e -> e.model).distinct().collect(Collectors.toList());
            buf.add(collect);
        }
        
        
          for(List<PrinterDTO> list : dtoes.values()) {            
            List<PrinterDTO> collect2 = list.stream()
                    .filter(DistinctByPrinterDTO.distinctByKey(PrinterDTO::getModel))
                    .collect(Collectors.toList());
            buf2.add(collect2);
        }
     
        for(int i = 0; i < buf.size(); i++) {
            System.out.println("bufEl" + i + "\n");
            for(int j = 0; j < buf.get(i).size(); j++) {
                System.out.println(buf.get(i).get(j));
            }
        }
        
        System.out.println("***********");
        System.out.println("distinct по printerDTO");
        
        for(int i = 0; i < buf2.size(); i++) {
            System.out.println("bufEl" + i + "\n");
            for(int j = 0; j < buf.get(i).size(); j++) {
                System.out.println(buf2.get(i).get(j));
            }
        }
        
        
        
        
        
        model.addAttribute("dtoes",dtoes);
        
        return "printers";
    }
    
}
