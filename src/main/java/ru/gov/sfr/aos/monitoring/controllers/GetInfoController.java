/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.ArrayList;
import java.util.List;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.models.CartridgeChoiceDto;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.ConsumptionDTO;
import ru.gov.sfr.aos.monitoring.models.EmployeeDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.PlaningBuyDto;
import ru.gov.sfr.aos.monitoring.services.CartridgeMapper;
import ru.gov.sfr.aos.monitoring.services.DictionaryEmployeeHolder;
import ru.gov.sfr.aos.monitoring.services.LocationService;
import ru.gov.sfr.aos.monitoring.services.PlaningService;

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
        List<CartridgeModelDTO> showCartridgeModelByPrinterModel = cartridgeMapper.showCartridgeModelByPrinterModel(idModel);
        return showCartridgeModelByPrinterModel;
    }

    @GetMapping("/showcartridgesbymodel")
    public List<CartridgeDTO> getCartridgesByModelPrinter(@RequestParam("idPrinter") Long idPrinter, @RequestParam("location") String location) {

        List<CartridgeDTO> showCartridgesByModelPrinter = cartridgeMapper.showCartridgesByModelPrinter(idPrinter, location);

        return showCartridgesByModelPrinter;
    }
    
    
        @GetMapping("/showcartridgesforchoice")
    public List<CartridgeChoiceDto> showCartridgesForChoice(@RequestParam("idPrinter") Long idPrinter, @RequestParam("location") String location) {

        List<CartridgeChoiceDto> showCartridgesByModelPrinter = cartridgeMapper.showCartridgesForChoice(idPrinter, location);

        return showCartridgesByModelPrinter;
    }
    
    

//    @PostMapping("/amountcartridgesofday")
//    public List<ConsumptionDTO> getAmountCartridgesOfDay(PlaningBuyDto dto) {
//        List<ConsumptionDTO> calculatePlaningBuy = planingService.showPurchased(dto);
//        return calculatePlaningBuy;
//    }

}
