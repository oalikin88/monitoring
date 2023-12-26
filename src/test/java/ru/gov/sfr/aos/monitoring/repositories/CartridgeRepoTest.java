/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;

/**
 *
 * @author 041AlikinOS
 */
@ActiveProfiles("test1")
@DataJpaTest
public class CartridgeRepoTest {
    
    @Autowired
    private CartridgeRepo cartridgeRepo;
    
    @Test
    void findByContractNumberAndModelPrinterAndLocationShouldNotEmpty() {
        List<Cartridge> list = cartridgeRepo.findByContractNumberAndModelPrinterAndLocation("43Белгород31", 2L, 2L);
        Assertions.assertTrue(!list.isEmpty());
    }
    
    @Test
    void findByContractNumberAndModelPrinterAndLocationShouldEqReference() {
        List<Cartridge> list = cartridgeRepo.findByContractNumberAndModelPrinterAndLocation("43Белгород31", 2L, 2L);
        Assertions.assertEquals("ML-D3050B", list.get(0).getModel().getModel());
    }
    
    @Test
    void findCartridgesByModelPrinterAndLocationShouldNotEmpty() {
        List<Cartridge> list = cartridgeRepo.findCartridgesByModelPrinterAndLocation(2L, 2L);
        Assertions.assertTrue(!list.isEmpty());
    }
    
    @Test
    void findCartridgesByModelPrinterAndLocationShouldEqRef() {
        List<Cartridge> list = cartridgeRepo.findCartridgesByModelPrinterAndLocation(2L, 2L);
        Assertions.assertEquals("ML-D3050B", list.get(0).getModel().getModel());
    }
    
    @Test
    void findCartridgesByLocationIdAndPrinterIdSuitableShouldNotEmpty() {
        List<Cartridge> list = cartridgeRepo.findCartridgesByLocationIdAndPrinterIdSuitable(2L, 1L);
        Assertions.assertTrue(!list.isEmpty());
    }
 
    @Test
    void findCartridgesByLocationIdAndPrinterIdSuitableShouldBeCurrentLocation() {
        List<Cartridge> list = cartridgeRepo.findCartridgesByLocationIdAndPrinterIdSuitable(2L, 1L);
        Assertions.assertEquals(2L, list.get(0).getLocation().getId());
    }
    
    @Test
    void findCartridgesByLocationIdAndPrinterIdSuitableShouldBeCurrentModelCartridge() {
        List<Cartridge> list = cartridgeRepo.findCartridgesByLocationIdAndPrinterIdSuitable(2L, 1L);
        Assertions.assertEquals(1L, list.get(0).getModel().getId());
    }
    
    
}
