/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.services.CartridgeMapper;
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
    
    @PostMapping("/manufacturers")
    public void saveManufacturer(@RequestParam String value) {
        mapper.saveManufacturer(value);
    }
    
    @PostMapping("/models")
    public void saveModel(ModelDTO dto) {
        modelMapper.saveModelByManufacturer(dto);
    }
    

    
    @RequestMapping(value = "/cartridge/{type}", method = RequestMethod.GET)
    public List<CartridgeDTO> getModelCartridgeByType(@PathVariable String type) {
        List<CartridgeDTO> list = cartridgeMapper.findModelCartridgeByType(type);
        return list;
    }    
        
    @RequestMapping(value = "/cartridge", method = RequestMethod.GET)
    public List<CartridgeModelDTO> getCartridges() {
            List<CartridgeModelDTO> list = cartridgeMapper.getCartridgeModels();
        return list;
    }
    
     @RequestMapping(value = "/cartridgebymodelprinter", method = RequestMethod.GET)
    public List<CartridgeModelDTO> getCartridgesByModelPrinter(@RequestParam("idModel") Long idModel) {
            List<CartridgeModelDTO> list = cartridgeMapper.showCartridgeModelByPrinterModel(idModel);
        return list;
    }
    
       @RequestMapping(value = "/cartridgebymodelprinter/{type}", method = RequestMethod.GET)
    public List<CartridgeModelDTO> getCartridgesByModelPrinterAndType(@RequestParam("model") String model, @PathVariable String type) {
            List<CartridgeModelDTO> list = cartridgeMapper.showCartridgeModelByPrinterModelAndType(model, type);
        return list;
    }
}
