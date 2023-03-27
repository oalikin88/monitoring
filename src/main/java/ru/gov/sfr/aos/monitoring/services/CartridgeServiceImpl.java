/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.interfaces.CartridgeServiceInterface;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service

public class CartridgeServiceImpl implements CartridgeServiceInterface {
    @Autowired
    private CartridgeRepo cartridgeRepo;

    @Override
    public List<Cartridge> getCartridges() {
        return cartridgeRepo.findAll();
    }
    
}
