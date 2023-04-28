/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.List;
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
        return "printers";
    }
    
}
