/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author 041AlikinOS
 */
@Controller
public class MainController {
    
    @GetMapping("/main")
    public String getData(Model model) {
        
       model.addAttribute("members", "Garry");
        return "main";
        
    }
    
    
}
