/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import java.util.List;
import ru.gov.sfr.aos.monitoring.models.CartridgeChoiceDto;
import ru.gov.sfr.aos.monitoring.models.CartridgeInstallDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeLocationForCartridges;

/**
 *
 * @author 041AlikinOS
 */
public interface CartridgeServiceInterface {
    public void installCartridge(CartridgeInstallDTO dto);
    void utilCartridge(Long id);
    void changeCartridgeLocation(ChangeDeviceLocationDTO dto);
    void changeCartridgesLocation(ChangeLocationForCartridges dto);
    List<CartridgeChoiceDto> showCartridgesForChoice(Long idPrinter, Long locationId);
}

