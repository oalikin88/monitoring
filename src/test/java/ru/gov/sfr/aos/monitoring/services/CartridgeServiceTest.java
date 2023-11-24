/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.PrintColorType;
import ru.gov.sfr.aos.monitoring.PrintFormatType;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;

/**
 *
 * @author 041AlikinOS
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartridgeServiceTest {
    @MockBean
    private CartridgeModelRepo cartridgeModelRepo;
    @MockBean
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private CartridgeService cartridgeService;
    

    
    
  @Test  
  public void saveCartridgeModel() throws ObjectAlreadyExists {
      
      CartridgeModel cartModel = new CartridgeModel("T1000", 10000L
              , Arrays.asList(new Model("ML3050", PrintColorType.COLOR, PrintFormatType.A4, 40L, new Manufacturer("Samsung")))
              , CartridgeType.ORIGINAL);
      
      

    cartridgeService.saveCartridgeModel(cartModel);
    Mockito.verify(cartridgeModelRepo, Mockito.times(1)).save(cartModel);
    Mockito.verify(cartridgeModelRepo, Mockito.times(2)).findByModelIgnoreCase(cartModel.getModel());
    
  }
}


