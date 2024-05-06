/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.CartridgeType;
import ru.gov.sfr.aos.monitoring.entities.CartridgeManufacturer;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.interfaces.CartridgeModelServiceInterface;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeManufacturerRepo;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import static ru.gov.sfr.aos.monitoring.services.CartridgeService.findCommonElements;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class CartridgeModelService implements CartridgeModelServiceInterface {

    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;
    @Autowired
    private CartridgeManufacturerRepo cartridgeManufacturerRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private CartridgeMapper cartridgeMapper;

    @Override
    public List<CartridgeModel> findModelCartridgeByType(String type) {
        CartridgeType currentType;
        if (type.trim().toLowerCase().equals(CartridgeType.ORIGINAL.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ORIGINAL;
        } else if (type.trim().toLowerCase().equals(CartridgeType.ANALOG.getName().trim().toLowerCase())) {
            currentType = CartridgeType.ANALOG;
        } else {
            currentType = CartridgeType.START;
        }
        List<CartridgeModel> list = cartridgeModelRepo.findByType(currentType);

        return list;
    }

    // Редактирование модели картриджа
    @Override
    @Transactional
    public void updateCartridgeModel(CartridgeModelDTO dto) {
        CartridgeModel cartridgeModelFromDB = cartridgeModelRepo.findById(dto.getId()).orElseThrow();
        if (cartridgeModelFromDB.getCartridgeManufacturer().getId() != dto.getIdManufacturer()) {
            CartridgeManufacturer updatedManufacturer = cartridgeManufacturerRepo.findById(dto.getIdManufacturer()).orElseThrow();
            cartridgeModelFromDB.setCartridgeManufacturer(updatedManufacturer);
        }
        cartridgeModelFromDB.setModel(dto.getModel().trim());
        cartridgeModelFromDB.setDefaultNumberPrintPage(Long.parseLong(dto.getResource()));
        CartridgeType cartridgeType = null;
        switch (dto.getType()) {
            case "ORIGINAL":
                cartridgeType = CartridgeType.ORIGINAL;
                break;
            case "ANALOG":
                cartridgeType = CartridgeType.ANALOG;
                break;
            case "START":
                cartridgeType = CartridgeType.START;
                break;
        }
        cartridgeModelFromDB.setType(cartridgeType);
        List<Model> modelsPrintersFromCartridgeModelDB = cartridgeModelFromDB.getModelsPrinters();
        List<Long> idModelsPrinters = modelsPrintersFromCartridgeModelDB.stream().map(e -> e.getId()).collect(Collectors.toList());
        Set<Long> commonSet = findCommonElements(idModelsPrinters, dto.idModel);
        if (commonSet.size() != cartridgeModelFromDB.getModelsPrinters().size()) {
            List<Model> updatedModelsPrinters = new ArrayList<>();
            for (Long el : dto.idModel) {
                Model model = modelPrinterRepo.findById(el).orElseThrow();
                updatedModelsPrinters.add(model);
            }
            cartridgeModelFromDB.setModelsPrinters(updatedModelsPrinters);
        }

        cartridgeModelRepo.save(cartridgeModelFromDB);
    }

    @Override
    public CartridgeModelDTO getCartridgeModelById(Long id) {
        CartridgeModel cartridgeModel = cartridgeModelRepo.findById(id).orElseThrow();
        CartridgeModelDTO cartridgeModelDto = cartridgeMapper.cartridgeModelToCartridgeModelDto(cartridgeModel);
        return cartridgeModelDto;
    }

    public List<CartridgeModel> showCartridgesModelByPrinterModel(Long id) {
        List<CartridgeModel> list = cartridgeModelRepo.findByModelsPrintersId(id);
        return list;
    }
    
    @Override
    public List<CartridgeModel> findModelCartridgeByCartridgeManufacturerAndType(String cartridgeManufacturer, String type) {
        String currentType;
        if (type.trim().toLowerCase().equals(CartridgeType.ORIGINAL.getName().trim().toLowerCase())) {
            currentType = "ORIGINAL";
        } else if (type.trim().toLowerCase().equals(CartridgeType.ANALOG.getName().trim().toLowerCase())) {
            currentType = "ANALOG";
        } else {
            currentType = "START";
        }
        List<CartridgeModel> list = cartridgeModelRepo.findByManufacturerAndType(cartridgeManufacturer, currentType);

        return list;
    }

    @Override
    public List<CartridgeModelDTO> showCartridgeModelByPrinterModelAndType(String model, String type) {
        CartridgeType cartridgeType = null;
        switch (type) {
            case "ORIGINAL":
                cartridgeType = CartridgeType.ORIGINAL;
                break;
            case "ANALOG":
                cartridgeType = CartridgeType.ANALOG;
                break;
            case "START":
                cartridgeType = CartridgeType.START;
                break;
        }
        Optional<Model> findModelPrinterByName = modelPrinterRepo.findByNameIgnoreCase(model.toLowerCase());
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        Set<CartridgeModel> modelCartridges = null;
        if (findModelPrinterByName.isPresent()) {
            modelCartridges = findModelPrinterByName.get().getModelCartridges();
        }
        List<Long> idModel = new ArrayList<>(Arrays.asList(0L));
        Set<String> printr = new HashSet<>(Arrays.asList("отсутствует"));
        for (Iterator<CartridgeModel> it = modelCartridges.iterator(); it.hasNext();) {
            CartridgeModel cartModel = it.next();
            if (cartModel.getType().equals(cartridgeType)) {
                CartridgeModelDTO dto = new CartridgeModelDTO();
                dto.setId(cartModel.getId());
                dto.setModel(cartModel.getModel());
                dto.setResource(cartModel.getDefaultNumberPrintPage().toString());
                dto.setType(cartModel.getType().getName());
                dto.setIdModel(idModel);
                dto.setPrinters(printr);
                dtoes.add(dto);
            }
        }
        return dtoes;
    }

    @Override
    @Transactional
    public void deleteModelCartridge(Long id) {
        CartridgeModel cartridgeModel = cartridgeModelRepo.findById(id).orElseThrow();
        if (cartridgeModel.getCartridges().size() < 1) {
            cartridgeModelRepo.deleteCartridgeModelById(id);
        } else {
            cartridgeModel.setArchived(true);
        }

    }

    @Override
    @Transactional
    public void saveCartridgeModel(CartridgeModel cartridgeModel) throws ObjectAlreadyExists {
        String replaceAllBreakesFromInput = cartridgeModel.getModel().replaceAll(" ", "");
        String replaceAllDeficesAndBreakesFromInput = replaceAllBreakesFromInput.replaceAll("-", "");

        if (cartridgeModelRepo.existsByModelIgnoreCase(replaceAllDeficesAndBreakesFromInput)) {
            throw new ObjectAlreadyExists("Модель " + cartridgeModel.getModel() + " уже есть в базе данных");
        } else {

            cartridgeModelRepo.save(cartridgeModel);
        }
    }

    @Override
    public void repearCartridgeModel(Long id) {
        CartridgeModel cartridgeModel = cartridgeModelRepo.findById(id).orElseThrow();
        cartridgeModel.setArchived(false);
        cartridgeModelRepo.save(cartridgeModel);
    }

    @Override
    public List<CartridgeModelDTO> getArchivedModelsCartridgeListDto() {
        List<CartridgeModel> list = cartridgeModelRepo.findAllIsArchivedTrue();
        List<CartridgeModelDTO> cartridgeModelListDto = cartridgeMapper.cartridgeModelListToCartridgeModelListDto(list);
        return cartridgeModelListDto;
    }

}
