/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.dao.PrinterAndCartridgeCountByLocationTableDAO;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.models.CartridgeChoiceDto;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.EmployeeDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterAndCartridgeCountByLocationTable;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.services.CartridgeMapper;
import ru.gov.sfr.aos.monitoring.services.CartridgeService;
import ru.gov.sfr.aos.monitoring.services.DictionaryEmployeeHolder;
import ru.gov.sfr.aos.monitoring.services.LocationService;
import ru.gov.sfr.aos.monitoring.services.PlaningService;
import ru.gov.sfr.aos.monitoring.services.PrinterService;

/**
 *
 * @author 041AlikinOS
 */
@RestController
public class GetInfoController {

   
    @Autowired
    private LocationService locationService;
    @Autowired
    private CartridgeMapper cartridgeMapper;

    @Autowired
    private PlaningService planingService;

    @Autowired
    private DictionaryEmployeeHolder dictionaryEmployeeHolder;
    @Autowired
    private CartridgeService cartridgeService;
    @Autowired
    private PrinterService printerService;
    
    private final PrinterAndCartridgeCountByLocationTableDAO dao;

    public GetInfoController(PrinterAndCartridgeCountByLocationTableDAO dao) {
        this.dao = dao;
    }
    
    
    
    
    @GetMapping("/getinfooo")
    public  List<EmployeeDTO> getEmpl() {
        List<DictionaryEmployee> employees = dictionaryEmployeeHolder.getEmployees();
        List<EmployeeDTO> list = new ArrayList<>();
        for(DictionaryEmployee dEmployee : employees) {
            EmployeeDTO dto = new EmployeeDTO(dEmployee.getCode(), dEmployee.getSurname() + " " + dEmployee.getName() + " " + dEmployee.getMiddlename());
            list.add(dto);
        }
        return list;


    }

    @GetMapping("/locations")
    public List<LocationDTO> getLocations() {

        List<LocationDTO> locations = locationService.getAllLocations();
        return locations;
    }
    

    @GetMapping("/getmodelcartridge")
    public List<CartridgeModelDTO> getModelCartridgeByModelPrinter(@RequestParam("idModel") Long idModel) {
        List<CartridgeModel> list = cartridgeService.showCartridgesModelByPrinterModel(idModel);
        List<CartridgeModelDTO> dtoes = new ArrayList<>();
        for(CartridgeModel model : list) {
            CartridgeModelDTO dto = cartridgeMapper.cartridgeModelToCartridgeModelDto(model);
            dtoes.add(dto);
            }
        return dtoes;
    }

    
    
        @GetMapping("/showcartridgesforchoice")
    public List<CartridgeChoiceDto> showCartridgesForChoice(@RequestParam("idPrinter") Long idPrinter, @RequestParam("locationId") Long locationId) {

        List<CartridgeChoiceDto> showCartridgesByModelPrinter = cartridgeService.showCartridgesForChoice(idPrinter, locationId);
        

        return showCartridgesByModelPrinter;
    }
    
    
    @GetMapping("/testtest")
    public List<PrinterAndCartridgeCountByLocationTable> showAll() throws SQLException {
        List<PrinterAndCartridgeCountByLocationTable> data = dao.getData();
        return data;
    }
    
    @PostMapping("/AJAXPing")
    public Integer getAllContracts() {
        return 0;
    }
    
//    @GetMapping("/searchprinter")
//    public List<PrinterDTO> searchPrinters(@RequestParam("inventaryNumber") String inventaryNumber) {
//        List<Printer> printersByInventaryNumber = printerService.getPrintersByInventaryNumber(inventaryNumber);
//        List<PrinterDTO> printersDtoListFromPrintersList = printerService.getPrintersDtoListFromPrintersList(printersByInventaryNumber);
//        return printersDtoListFromPrintersList;
//    }

}
