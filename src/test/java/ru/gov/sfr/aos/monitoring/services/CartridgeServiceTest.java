/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.PrintColorType;
import ru.gov.sfr.aos.monitoring.PrintFormatType;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.dictionaries.DeviceType;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeManufacturer;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.CartridgeChoiceDto;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */

@Profile("dev")
@SpringBootTest
public class CartridgeServiceTest {
    @MockBean
    private CartridgeModelRepo cartridgeModelRepo;
    @MockBean
    private CartridgeRepo cartridgeRepo;
    @MockBean
    private ModelPrinterRepo modelPrinterRepo;
    @MockBean
    private PrinterRepo printerRepo;
    @MockBean
    private LocationRepo locationRepo;
    @Autowired
    private CartridgeService cartridgeService;
    
    @Before
    public void setUp() {
        Location location = new Location("Склад");
        location.setId(3L);
        Manufacturer manufacturer = new Manufacturer("Samsung");
        
        Model model = new Model();
        model.setId(2L);
        model.setManufacturer(manufacturer);
        model.setName("ML-3050n");
        model.setPrintColorType(PrintColorType.BLACKANDWHITE);
        model.setPrintFormatType(PrintFormatType.A4);
        
        
        
        CartridgeModel cartModel = new CartridgeModel();
        cartModel.setId(1L);
        cartModel.setModel("ML-3050A");
        cartModel.setType(CartridgeType.ORIGINAL);
        cartModel.setModelsPrinters(List.of(model));
        Cartridge cartridge = new Cartridge();
        cartridge.setId(4L);
        cartridge.setModel(cartModel);
        cartridge.setLocation(location);
        cartridge.setItemCode("234235235");
        cartridge.setNameMaterial("Картридж оригинальный");
        cartridgeRepo.save(cartridge);
        
    }
    

  
  @Test
  public void showCartridgesForChoiceTest() {
      
        List<CartridgeChoiceDto> list = cartridgeService.showCartridgesForChoice(1L, 2L);
        Mockito.verify(cartridgeRepo, Mockito.times(1)).findCartridgesByLocationIdAndPrinterIdSuitable(2L, 1L);
      
  }
  
  @Test
  public void showCartridgesForChoiceShouldNotNullOut() {
      
      Location loc1 = new Location("Склад");
      
      Manufacturer manufacturer1 = new Manufacturer("Samsung");
      
      Model modelP1 = new Model();
      modelP1.setName("ML-3050");
      modelP1.setManufacturer(manufacturer1);
      modelP1.setPrintColorType(PrintColorType.BLACKANDWHITE);
      modelP1.setPrintSpeed(24L);
      modelP1.setPrintFormatType(PrintFormatType.A4);
      
      Printer printer1 = new Printer();
      printer1.setModel(modelP1);
      printer1.setManufacturer(manufacturer1);
      printer1.setInventoryNumber("4545454");
      printer1.setSerialNumber("23424234");
      printer1.setPrinterStatus(PrinterStatus.OK);
      printer1.setLocation(loc1);
      
      CartridgeModel modelCart1 = new CartridgeModel();
      modelCart1.setModel("ML-3050A");
      modelCart1.setDefaultNumberPrintPage(10000L);
      modelCart1.setType(CartridgeType.ORIGINAL);
      modelCart1.setModelsPrinters(Arrays.asList(modelP1));
      
      Cartridge cartridge1 = new Cartridge();
      cartridge1.setItemCode("43345345");
      cartridge1.setLocation(loc1);
      cartridge1.setNameMaterial("Картридж оригинальный");
      cartridge1.setModel(modelCart1);
      
      
        List<CartridgeChoiceDto> list = cartridgeService.showCartridgesForChoice(1L, 2L);
        Mockito.verify(cartridgeRepo, Mockito.times(1)).findCartridgesByLocationIdAndPrinterIdSuitable(2L, 1L);
             
  }
  
  
}


