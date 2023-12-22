/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.bouncycastle.crypto.prng.drbg.HashSP800DRBG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;


/**
 *
 * @author 041AlikinOS
 */
@Component
public class CartridgeMapper {

    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;



    public List<CartridgeModelDTO> getCartridgeModels() {
        List<CartridgeModel> list = cartridgeModelRepo.findAll();
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CartridgeModelDTO cartridgeModelDTO = new CartridgeModelDTO();
            cartridgeModelDTO.setId(list.get(i).getId());
            cartridgeModelDTO.setResource(list.get(i).getDefaultNumberPrintPage().toString());
            cartridgeModelDTO.setModel(list.get(i).getModel());
            cartridgeModelDTO.setType(list.get(i).getType().getName());
            List<Long> printers = new ArrayList<>();
            for (int j = 0; j < list.get(i).getModelsPrinters().size(); j++) {
                printers.add(list.get(i).getModelsPrinters().get(j).getId());
            }
            cartridgeModelDTO.setIdModel(printers);
            dtoes.add(cartridgeModelDTO);
        }
        return dtoes;
    }

    public CartridgeDTO getCartridgeById(Long id) {
        CartridgeDTO dto = new CartridgeDTO();

        Optional<Cartridge> findCartridgeById = cartridgeRepo.findById(id);

        dto.setId(findCartridgeById.get().getId());
        dto.setContract(findCartridgeById.get().getContract().getId());
        dto.setContractNumber(findCartridgeById.get().getContract().getContractNumber().toString());
        dto.setLocation(findCartridgeById.get().getLocation().getName());
        dto.setModel(findCartridgeById.get().getModel().getModel());
        dto.setType(findCartridgeById.get().getModel().getType().getName());
        dto.setUtil(findCartridgeById.get().isUtil());
        dto.setUsePrinter(findCartridgeById.get().isUseInPrinter());
        dto.setResource(findCartridgeById.get().getModel().getDefaultNumberPrintPage().toString());
        dto.setStartContract(findCartridgeById.get().getContract().getDateStartContract());
        dto.setEndContract(findCartridgeById.get().getContract().getDateEndContract());
        dto.setDateStartExploitation(findCartridgeById.get().getDateStartExploitation());
        dto.setDateEndExploitation(findCartridgeById.get().getDateEndExploitation());
        dto.setCount(findCartridgeById.get().getCount());
        dto.setStartContract(findCartridgeById.get().getContract().getDateStartContract());
        return dto;
    }
    
    public CartridgeDTO getCartridgeDtoWhithoutListener(Cartridge cartridge) {
        CartridgeDTO dto = new CartridgeDTO();
        dto.setId(cartridge.getId());
        dto.setContract(cartridge.getContract().getId());
        dto.setContractNumber(cartridge.getContract().getContractNumber().toString());
        dto.setLocation(cartridge.getLocation().getName());
        dto.setModel(cartridge.getModel().getModel());
        dto.setType(cartridge.getModel().getType().getName());
        dto.setUtil(cartridge.isUtil());
        dto.setUsePrinter(cartridge.isUseInPrinter());
        dto.setResource(cartridge.getModel().getDefaultNumberPrintPage().toString());
        dto.setStartContract(cartridge.getContract().getDateStartContract());
        dto.setEndContract(cartridge.getContract().getDateEndContract());
        dto.setDateStartExploitation(cartridge.getDateStartExploitation());
        dto.setDateEndExploitation(cartridge.getDateEndExploitation());
        dto.setCount(cartridge.getCount());
        dto.setStartContract(cartridge.getContract().getDateStartContract());
        dto.setItemCode(cartridge.getItemCode());
        dto.setNameMaterial(cartridge.getNameMaterial());
        return dto;
    }
    
    public CartridgeModel cartridgeModelDtoToCartridgeModel(CartridgeModelDTO dto) {
        
        CartridgeType currentType = null;
        CartridgeModel model = null;
        List<Model> modelsPrinters = new ArrayList<>();
        for (Long l : dto.getIdModel()) {
            Optional<Model> findById = modelPrinterRepo.findById(l);
            if (findById.isPresent()) {
                modelsPrinters.add(findById.get());
            }
        }
        if (dto.type.trim().toLowerCase().equals(CartridgeType.ORIGINAL.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ORIGINAL;
        } else if (dto.type.trim().toLowerCase().equals(CartridgeType.ANALOG.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ANALOG;
        } else {
            currentType = CartridgeType.START;
        }
        long parseResource = Long.parseLong(dto.getResource().trim());
        
        String modelNameTrim = dto.getModel().trim();
        model = new CartridgeModel();
        model.setModel(modelNameTrim);
        model.setType(currentType);
        model.setDefaultNumberPrintPage(parseResource);
        model.setModelsPrinters(modelsPrinters);
    return model;  
}
    public CartridgeModelDTO cartridgeModelToCartridgeModelDto(CartridgeModel cartridgeModel) {
        CartridgeModelDTO dto = new CartridgeModelDTO();
        dto.setId(cartridgeModel.getId());
        dto.setModel(cartridgeModel.getModel());
        dto.setResource(cartridgeModel.getDefaultNumberPrintPage().toString());
        dto.setType(cartridgeModel.getType().getName());
        List<Long> listModelsPrinters = new ArrayList<>();
        Set<String> printers = new HashSet<>();
        List<Model> modelsPrinters = cartridgeModel.getModelsPrinters();
        for(Model model : modelsPrinters) {
            listModelsPrinters.add(model.getId());
            for(Printer printer : model.getPrinters()) {
                printers.add(printer.getManufacturer().getName() + " " + printer.getModel().getName());
            }
        }
        dto.setIdModel(listModelsPrinters);
        dto.setPrinters(printers);
        
        return dto;
    }
    
    
}
