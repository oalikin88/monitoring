package ru.gov.sfr.aos.monitoring.printer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ru.gov.sfr.aos.monitoring.controllers.SvtViewController;
import ru.gov.sfr.aos.monitoring.exceptions.DublicateInventoryNumberException;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.location.LocationService;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.place.PlaceType;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class PrinterViewController {

    private final PrinterModelMapper printerModelMapper;
    private final PrinterService printerService;
    private final PrinterMapper printerMapper;
    private final PrinterModelService printerModelService;
    private final PrinterOutDtoTreeService printerOutDtoTreeService;
    private final LocationService locationService;

    
    
    public PrinterViewController(PrinterModelMapper printerModelMapper, PrinterService printerService,
			PrinterMapper printerMapper, PrinterModelService printerModelService,
			PrinterOutDtoTreeService printerOutDtoTreeService, LocationService locationService) {
		super();
		this.printerModelMapper = printerModelMapper;
		this.printerService = printerService;
		this.printerMapper = printerMapper;
		this.printerModelService = printerModelService;
		this.printerOutDtoTreeService = printerOutDtoTreeService;
		this.locationService = locationService;
	}

	//   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/printers")
    public String getPrinters(Model model, @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "inventaryNumber", required = false) String inventaryNumber,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @ModelAttribute FilterDto dto) {

       List<LocationByTreeDto> employeePrinters = 
    		   loadPrintersByPlace(PlaceType.EMPLOYEE, username, inventaryNumber, serialNumber, dto);
    	List<LocationByTreeDto> storagePrinters = 
    			loadPrintersByPlace(PlaceType.STORAGE, username, inventaryNumber, serialNumber, dto);
       int amount = SvtViewController.getAmountDevices(employeePrinters, storagePrinters);
    	fillModel(model, employeePrinters, storagePrinters, dto, amount, username, inventaryNumber, serialNumber);
    	
    	return "svtobj";
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/printers")
    public ResponseEntity<String> savePhone(@RequestBody PrinterDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        printerService.createSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/updprinter")
    public ResponseEntity<String> updatePhone(@RequestBody PrinterDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        printerService.updateSvtObj(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @SendArchive
    @DeleteMapping("/printersarchived")
    public ResponseEntity<String> sendPhoneToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        printerService.svtObjToArchive(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @UpdLog
    @PutMapping("/printertostor")
    public ResponseEntity<String> sendToStorage(@RequestBody PrinterDto dto) throws ObjectAlreadyExists {
        Printer printer = printerService.getById(dto.getId());
        printerService.sendToStorage(printer);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mprinters")
    public String getModelPrinters(Model model) {

        List<PrinterModel> printerModels = printerModelService.getAllActualModels();
        List<PrinterModelDto> printerModelsDtoes = printerModelMapper.getListDtoes(printerModels);
        model.addAttribute("dtoes", printerModelsDtoes);
        model.addAttribute("namePage", "Модели принтеров");
        model.addAttribute("attribute", "mprinters");
        model.addAttribute("manufacturersSaveLink", "/save-printer-manufacturer");
        model.addAttribute("modelsByManufacturerLink", "/get-printer-modelsby-manufacturer?id=");
        model.addAttribute("manufacturersLink", "/get-printer-manufacturers");

        return "models";
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mprinters")
    public ResponseEntity<String> saveModelPrinter(@RequestBody PrinterModelDto dto) throws ObjectAlreadyExists {
        PrinterModel printerModel = printerModelMapper.getModel(dto);
        try {
            printerModelService.saveModel(printerModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/mprintersupd")
    public ResponseEntity<String> updateModelPrinter(@RequestBody PrinterModelDto dto) throws ObjectAlreadyExists {
        PrinterModel printerModel = printerModelMapper.getModel(dto);
        try {
            printerModelService.update(printerModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @SendArchive
    @DeleteMapping("/mprintersarchived")
    public ResponseEntity<String> sendModelPrinterToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        printerModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }
    
    public Map<Location, List<Printer>> findWhithOutFilter(
    		PlaceType placeType,
    		String username,
    		String inventary,
    		String serial
    		) {
    	if (username != null) {
    		return printerService.getSvtObjectsByName(username, placeType);
    	}
    	if (inventary != null) {
    		return printerService.getSvtObjectsByInventaryNumberAndPlace(inventary, placeType);
    	}
    	if (serial != null) {
    		return printerService.getSvtObjectsBySerialNumberAndPlace(serial, placeType);
    	}
    	return printerService.getSvtObjectsByPlaceType(placeType);
    }
    
    List<LocationByTreeDto> loadPrintersByPlace(PlaceType placeType,
    		String username,
    		String inventary,
    		String serial,
    		FilterDto filter) {
    	Map<Location, List<Printer>> printers;
    	
    	if(filter.isEmpty()) {
    		printers = findWhithOutFilter(placeType, username, inventary, serial);
    	} else {
    		List<Printer> filtered = printerService.getDevicesByFilter(filter);
    		printers = printerService.getDevicesByPlaceTypeAndFilter(placeType, filtered);
    		}
    	return printerOutDtoTreeService.getTreeSvtDtoByPlaceType(printers)
    			.stream()
    			.sorted(Comparator.comparing(LocationByTreeDto::getLocationName))
    			.collect(Collectors.toList());
    }
    
    private void fillModel(Model model,
    		List<LocationByTreeDto> employee,
    		List<LocationByTreeDto> storage,
    		FilterDto filter,
    		int amount,
    		String username,
    		String inventary,
    		String serial) {
    	
    	model.addAttribute("dtoes", employee);
        model.addAttribute("dtoesStorage", storage);
        model.addAttribute("searchFIO", username);
        model.addAttribute("searchInventary", inventary);
        model.addAttribute("searchSerial", serial);
        model.addAttribute("filterDateBegin", filter.getYearCreatedOne());
        model.addAttribute("filterDateEnd", filter.getYearCreatedTwo());
        model.addAttribute("filterStatus", filter.getStatus());
        model.addAttribute("filterModel", filter.getModel());
        model.addAttribute("filterLocation", filter.getLocation());
        model.addAttribute("namePage", "Принтеры");
        model.addAttribute("attribute", "printers");
        model.addAttribute("placeAttribute", "employee");
        model.addAttribute("amountDevice", amount);
        model.addAttribute("haveFilter", true);
        model.addAttribute("haveDownload", true);
    }

}
