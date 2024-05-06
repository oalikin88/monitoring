/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import java.util.List;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;

/**
 *
 * @author 041AlikinOS
 */
public interface CartridgeModelServiceInterface {
    List<CartridgeModel> findModelCartridgeByType(String type);
    void updateCartridgeModel(CartridgeModelDTO dto);
    CartridgeModelDTO getCartridgeModelById(Long id);
    List<CartridgeModelDTO> showCartridgeModelByPrinterModelAndType(String model, String type);
    List<CartridgeModel> findModelCartridgeByCartridgeManufacturerAndType(String cartridgeManufacturer, String type);
    void deleteModelCartridge(Long id);
    void saveCartridgeModel(CartridgeModel cartridgeModel) throws ObjectAlreadyExists;
    void repearCartridgeModel(Long id);
    List<CartridgeModelDTO> getArchivedModelsCartridgeListDto();
    
    
}
