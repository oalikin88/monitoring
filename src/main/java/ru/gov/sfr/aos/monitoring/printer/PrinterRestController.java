package ru.gov.sfr.aos.monitoring.printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class PrinterRestController {

    @Autowired
    private PrinterService printerService;
    @Autowired
    private PrinterMapper printerMapper;
    @Autowired
    private PrinterModelService printerModelService;
    @Autowired
    private PrinterManufacturerService printerManufacturerService;
    @Autowired
    private PrinterManufacturerMapper printerManufacturerMapper;
    @Autowired
    private PrinterModelMapper printerModelMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;

    @GetMapping("/modprinters")
    public List<PrinterModelDto> getModelPrinters() {
        List<PrinterModel> allModels = printerModelService.getAllActualModels();
        List<PrinterModelDto> out = new ArrayList<>();
        for (PrinterModel el : allModels) {
            PrinterModelDto dto = printerModelMapper.getDtoForSelectize(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/getprinter")
    public PrinterDto getPrinterById(Long id) {
        Printer printer = printerService.getById(id);
        PrinterDto printerDto = printerMapper.getDto(printer);
        return printerDto;
    }

    @PostMapping("/save-printer-manufacturer")
    public ResponseEntity<?> savePrinterManufacturer(String name) throws ObjectAlreadyExists {
        PrinterManufacturer savedManufacturer = null;
        PrinterManufacturer potencialManufacturer = new PrinterManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = printerManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        ManufacturerDTO dto = null;
        dto = printerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-printer-manufacturers")
    public List<ManufacturerDTO> getPrinterManufacturers() {
        List<PrinterManufacturer> allManufacturers = printerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = printerManufacturerMapper.getDtoes(allManufacturers);
        return out;
    }

    @GetMapping("/get-printer-modelsby-manufacturer")
    public List<PrinterModelDto> getPrinterModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        PrinterManufacturer manufacturer = printerManufacturerService.getManufacturer(id);
        Set<PrinterModel> upsListModel = manufacturer.getModels();
        List<PrinterModelDto> out = new ArrayList<>();
        for (PrinterModel model : upsListModel) {
            PrinterModelDto modeDto = printerModelMapper.getDto(model);
            out.add(modeDto);
        }
        return out;
    }

}
