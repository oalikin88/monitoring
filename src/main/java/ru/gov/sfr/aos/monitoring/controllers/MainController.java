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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gov.sfr.aos.monitoring.entities.Contract;
import ru.gov.sfr.aos.monitoring.interfaces.ContractServiceInterface;
import ru.gov.sfr.aos.monitoring.models.ContractDTO;
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
    private ContractServiceMapper mapper;
    
    @GetMapping("/main")
    public String getData(Model model) {
        
        List<Contract> contracts = contractServiceInterface.getContracts();
        ContractDTO contract = new ContractDTO();
        
       model.addAttribute("contracts", contracts);
       model.addAttribute("contract", contract);
       
        return "main";
        
    }
    
    @PostMapping("/main")
    public String sendData(
            @ModelAttribute ContractDTO contract) {
       
        mapper.createNewContract(contract);
        
        return "redirect:/main";
        
     }
    
}
