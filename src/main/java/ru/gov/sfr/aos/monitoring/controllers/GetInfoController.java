/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.entities.Contract;

/**
 *
 * @author 041AlikinOS
 */

@RestController
public class GetInfoController {
    
    @PostMapping("/getinfo")
    public Contract  getInfo(Model model) {
            Contract contract = null;
            return contract;
        
    }
}
