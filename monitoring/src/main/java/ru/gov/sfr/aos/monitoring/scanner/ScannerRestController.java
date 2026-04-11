package ru.gov.sfr.aos.monitoring.scanner;

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
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class ScannerRestController {

    @Autowired
    private ScannerService scannerService;
    @Autowired
    private ScannerMapper scannerMapper;
    @Autowired
    private ScannerModelService scannerModelService;
    @Autowired
    private ScannerManufacturerService scannerManufacturerService;
    @Autowired
    private ScannerManufacturerMapper scannerManufacturerMapper;
    @Autowired
    private ScannerModelMapper scannerModelMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;

    @PostMapping("/save-scanner-manufacturer")
    public ResponseEntity<?> saveScannerManufacturer(String name) throws ObjectAlreadyExists {
        ScannerManufacturer savedManufacturer = null;
        ScannerManufacturer potencialManufacturer = new ScannerManufacturer();
        potencialManufacturer.setName(name);
        try {
            savedManufacturer = scannerManufacturerService.save(potencialManufacturer);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = scannerManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-scanner-manufacturers")
    public List<ManufacturerDTO> getScannerManufacturers() {
        List<ScannerManufacturer> allManufacturers = scannerManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for (ScannerManufacturer el : allManufacturers) {
            ManufacturerDTO dto = scannerManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }

    @GetMapping("/get-scanner-modelsby-manufacturer")
    public List<SvtModelDto> getScannerModelsByManufacturer(@RequestParam(value = "id", required = true) Long id) {
        ScannerManufacturer manufacturer = scannerManufacturerService.getManufacturer(id);
        Set<ScannerModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for (ScannerModel model : upsListModel) {
            SvtModelDto modelDto = svtModelMapper.getModelScannerDto(model);
            out.add(modelDto);
        }
        return out;
    }

    @GetMapping("/getscanner")
    public SvtDTO getScannerById(Long scannerId) {

        Scanner scanner = scannerService.getById(scannerId);
        SvtScannerDTO scannerDto = scannerMapper.getDto(scanner);

        return scannerDto;
    }

    @GetMapping("/modscanner")
    public List<SvtModelDto> getModelScanner() {
        List<ScannerModel> allModels = scannerModelService.getAllModels();
        List<SvtModelDto> scannerModelsDtoes = new ArrayList<>();
        for (ScannerModel model : allModels) {
            SvtModelDto dtoForSelectize = scannerModelMapper.getDtoForSelectize(model);
            scannerModelsDtoes.add(dtoForSelectize);
        }
        return scannerModelsDtoes;
    }

}
