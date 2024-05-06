/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.Arrays;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.PrintColorType;
import ru.gov.sfr.aos.monitoring.PrintFormatType;
import ru.gov.sfr.aos.monitoring.dictionaries.DeviceType;
import ru.gov.sfr.aos.monitoring.entities.CartridgeManufacturer;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

/**
 *
 * @author 041AlikinOS
 */

@Profile("dev")
@SpringBootTest
public class CartridgeModelServiceTest {
    
    @Autowired
    private CartridgeModelService cartridgeModelService;
    @MockBean
    private CartridgeModelRepo cartridgeModelRepo;
    private CartridgeModel cartModel;
    private CartridgeModel cartModel2;
    
    @Before
    public void prepare() {
    cartModel = new CartridgeModel(new CartridgeManufacturer("Samsung"), "T1000", 10000L
          , Arrays.asList(new Model("ML3050", PrintColorType.COLOR, PrintFormatType.A4, 40L, new Manufacturer("Samsung"), DeviceType.PRINTER, false))
          , CartridgeType.ORIGINAL);
    cartModel.setId(1L);
    
    cartModel2 = new CartridgeModel(new CartridgeManufacturer("Samsung"), "T1000", 10000L
          , Arrays.asList(new Model("ML3050", PrintColorType.COLOR, PrintFormatType.A4, 40L, new Manufacturer("Samsung"), DeviceType.PRINTER, false))
          , CartridgeType.ORIGINAL);
    cartModel2.setId(1L);
    }
    
    
@Test
public void saveCartridgeModel() throws ObjectAlreadyExists {
        CartridgeModel returnedCartridgeModel = doReturn(cartModel).when(cartridgeModelRepo).save(any());
        Assertions.assertEquals(cartModel, returnedCartridgeModel);
}

@Test
public void saveCartridgeModelShouldAssertAlreadyExist() throws ObjectAlreadyExists {
doReturn(cartModel).when(cartridgeModelRepo).save(cartModel);
doReturn(ObjectAlreadyExists.class).when(cartridgeModelRepo).save(cartModel2);
    Mockito.when(cartridgeModelRepo.existsByModelIgnoreCase(Mockito.eq("T1000"))).thenReturn(true);

 // Assertions.assertThrows(ObjectAlreadyExists.class, () -> doReturn(cartModel2).when(cartridgeModelRepo).save(any()));    
   //CartridgeModel returnedCartridgeModel = cartridgeModelService.saveCartridgeModel(cartModel);
   
}


//@Test
//public void saveCartridgeModelShouldNotAssertAlreadyExist() throws ObjectAlreadyExists {
//     CartridgeModel cartModel1 = new CartridgeModel(new CartridgeManufacturer("Samsung"), "T1000", 10000L,
//              Arrays.asList(new Model("ML3050", PrintColorType.COLOR, PrintFormatType.A4, 40L, new Manufacturer("Samsung"), DeviceType.PRINTER, false)),
//              CartridgeType.ORIGINAL);
//     cartridgeModelService.saveCartridgeModel(cartModel1);
//     Assertions.assertDoesNotThrow(() -> cartridgeModelService.saveCartridgeModel(cartModel1));
//}






//public void findModelCartridgeByTypeAssertReqEquals() {
//  String request = "Оригинальный";
//  cartridgeService.findModelCartridgeByType(request);
//  Mockito.verify(cartridgeModelRepo, Mockito.times(1)).findByType(CartridgeType.ORIGINAL);
//
//}
    
}
