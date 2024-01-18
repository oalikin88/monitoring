/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.services.CartridgeMapper;
import ru.gov.sfr.aos.monitoring.services.CartridgeService;
import ru.gov.sfr.aos.monitoring.services.ManufacturersMapper;
import ru.gov.sfr.aos.monitoring.services.ModelMapper;

/**
 *
 * @author 041AlikinOS
 */
@RestController
public class ManufacturersController {

    @Autowired
    private ManufacturersMapper mapper;
    @Autowired
    private ModelPrinterRepo modelRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartridgeMapper cartridgeMapper;
    @Autowired
    private CartridgeRepo repo;
    @Autowired
    private CartridgeService cartridgeService; 

    @RequestMapping(value = "/manufacturers", method = RequestMethod.GET)
    public List<ManufacturerDTO> showPrinters() {

        List<ManufacturerDTO> list = mapper.showManufacturers();
        return list;
    }

    @RequestMapping(value = "/models", method = RequestMethod.GET)
    public List<ModelDTO> showModels() {

        List<ModelDTO> list = modelMapper.showModels();
        return list;
    }

    @RequestMapping(value = "/models/{manufacturer}", method = RequestMethod.GET)
    public List<ModelDTO> showModelsByManufacturer(@PathVariable String manufacturer) {

        List<ModelDTO> list = modelMapper.showModelsByManufacturer(manufacturer);
        return list;
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/manufacturers")
    public void saveManufacturer(@RequestParam String value) {
        mapper.saveManufacturer(value);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/models")
    public void saveModel(@Valid ModelDTO dto) throws ObjectAlreadyExists {
        modelMapper.saveModelByManufacturer(dto);
    }
    

    
    @RequestMapping(value = "/cartridge/{type}", method = RequestMethod.GET)
    public List<CartridgeModelDTO> getModelCartridgeByType(@PathVariable String type) {
        List<CartridgeModel> list = cartridgeService.findModelCartridgeByType(type);
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        for(CartridgeModel model : list) {
            CartridgeModelDTO dto = cartridgeMapper.cartridgeModelToCartridgeModelDto(model);
            dtoes.add(dto);
        }
        return dtoes;
    }    
        
    @RequestMapping(value = "/cartridge", method = RequestMethod.GET)
    public List<CartridgeModelDTO> getCartridges() {
            List<CartridgeModelDTO> list = cartridgeMapper.getCartridgeModels();
        return list;
    }
    
     @RequestMapping(value = "/cartridgebymodelprinterid", produces={"application/json; charset=UTF-8"}, method = RequestMethod.GET)
    public List<CartridgeModelDTO> getCartridgesByModelPrinter(@RequestParam("idModel") Long idModel) {
            List<CartridgeModel> list = cartridgeService.showCartridgesModelByPrinterModel(idModel);
            List<CartridgeModelDTO> dtoes = new ArrayList<>();
            for(CartridgeModel model : list) {
                CartridgeModelDTO dto = cartridgeMapper.cartridgeModelToCartridgeModelDto(model);
                dtoes.add(dto);
            }
        return dtoes;
    }
    
       @RequestMapping(value = "/cartridgebymodelprinter/{type}", produces={"application/json; charset=UTF-8"}, method = RequestMethod.GET)
    public List<CartridgeModelDTO> getCartridgesByModelPrinterAndType(@RequestParam("model") String model, @PathVariable String type) {
            List<CartridgeModelDTO> list = cartridgeService.showCartridgeModelByPrinterModelAndType(model, type);
        return list;
    }
}
