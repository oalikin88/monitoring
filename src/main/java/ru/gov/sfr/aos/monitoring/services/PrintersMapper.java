/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class PrintersMapper {

    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    @Autowired
    private CartridgeRepo cartridgeRepo;

    public List<PrinterDTO> showPrinters() {

        List<PrinterDTO> printers = new ArrayList<>();
        List<Cartridge> cartridges = cartridgeRepo.findAll();
        List<Printer> printerList = printerRepo.findAll();
        List<Model> models = modelPrinterRepo.findAll();

        for (Printer el : printerList) {

            PrinterDTO dto = new PrinterDTO();
            dto.setManufacturer(el.getManufacturer().getName());

            dto.setModel(models.stream().filter(e -> e.getManufacturer().getId()
                    .equals(el.getManufacturer().getId())).findFirst().orElseThrow().getName());

            dto.setInventaryNumber(el.getInventoryNumber());
            dto.setSerialNumber(el.getSerialNumber());

            for (Cartridge o : cartridges) {
                if (o.getPrinter() != null) {
                    if (el.getId() == o.getPrinter().getId()) {
                        dto.setCartridge(o.getModel());
                    }
                }
            }

            if (dto.getCartridge() == null) {
                dto.setCartridge("Отсутствует");
            }
            
            
            dto.setLocation(el.getLocation().getName());

            printers.add(dto);
        }

        
        
        return printers;
    }

}
