/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class CartridgeMapper {
   
    @Autowired private CartridgeRepo cartridgeRepo;
    
  
    
    public List<CartridgeDTO> getDTO() {
      List<Cartridge> cartridges = cartridgeRepo.findAll();
      List<CartridgeDTO> dtoList = new ArrayList<>();
      for(Cartridge cart : cartridges) {
          CartridgeDTO dto = new CartridgeDTO(cart.getType().getName(), cart.getType().toString(), cart.getModel(), Long.toString(cart.getDefaultNumberPrintPage()));
          dtoList.add(dto);
      }
      return dtoList;
    }
    
}
