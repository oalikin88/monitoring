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
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.interfaces.ContractServiceInterface;

/**
 *
 * @author 041AlikinOS
 */
@Controller
public class MainController {
    
    @Autowired
    private ContractServiceInterface contractServiceInterface;
    
    
    @GetMapping("/main")
    public String getData(Model model) {
       
        List<Contract> contracts = contractServiceInterface.getContracts();
        
       model.addAttribute("contracts", contracts);
        return "main";
        
    }
    
    
}
