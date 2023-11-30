/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gov.sfr.aos.monitoring.MonitoringApplication;
import ru.gov.sfr.aos.monitoring.entities.Cartridge;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.Printer;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.interfaces.ContractServiceInterface;
import ru.gov.sfr.aos.monitoring.models.CartridgeDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeInstallDTO;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.DevicesByModelAndLocationDto;
import ru.gov.sfr.aos.monitoring.models.ChangePrinterInventaryNumberDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeLocationForCartridges;
import ru.gov.sfr.aos.monitoring.models.ChangePrinterSerialNumberDTO;
import ru.gov.sfr.aos.monitoring.models.ConsumptionDTO;
import ru.gov.sfr.aos.monitoring.models.ContractDTO;
import ru.gov.sfr.aos.monitoring.models.ContractForViewDTO;
import ru.gov.sfr.aos.monitoring.models.ContractFromInputDto;
import ru.gov.sfr.aos.monitoring.models.EditContractDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.ModelCartridgeByModelPrinters;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.models.PlaningBuyDto;
import ru.gov.sfr.aos.monitoring.models.PrinterDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterStatusDto;
import ru.gov.sfr.aos.monitoring.models.PrintersByLocationandModelDto;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.PrinterRepo;
import ru.gov.sfr.aos.monitoring.services.CartridgeMapper;
import ru.gov.sfr.aos.monitoring.services.CartridgeService;
import ru.gov.sfr.aos.monitoring.services.ContractServiceMapper;
import ru.gov.sfr.aos.monitoring.services.LocationService;
import ru.gov.sfr.aos.monitoring.services.PlaningService;
import ru.gov.sfr.aos.monitoring.services.PrinterOutInfoService;
import ru.gov.sfr.aos.monitoring.services.PrinterService;
import ru.gov.sfr.aos.monitoring.services.PrintersMapper;

/**
 *
 * @author 041AlikinOS
 */
@Controller
public class MainController {

    @Autowired
    private ContractServiceInterface contractServiceInterface;
    @Autowired
    private ContractServiceMapper mapper;
    @Autowired
    private CartridgeMapper cartridgeMapper;
    @Autowired
    private PrinterOutInfoService printerOutInfoService;
    @Autowired
    private PrintersMapper printerMapper;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CartridgeService cartridgeService;
    @Autowired
    private ContractServiceMapper contractServiceMapper;
    @Autowired
    private PlaningService planingService;
    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private PrinterService printerService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/main")
    public String getData(Model model) {
    
        return "main";
    }

    @PostMapping(value = "/main", consumes = "application/json", produces = "application/json")
    public String sendData(
            @Valid
            @RequestBody List<Map<String, String>> printersPlusCartridges) throws ObjectAlreadyExists {
        ContractFromInputDto transformInputToDto = mapper.transformInputToDto(
                printersPlusCartridges);
        mapper.createContract(transformInputToDto);
        return "redirect:/main";
    }

    @GetMapping("/inventories")
    public String getInventories(Model model) {
        Map<String, List<ModelDTO>> outInfo = printerOutInfoService.outInfo();
        Map<LocationDTO, List<ModelCartridgeByModelPrinters>> showCartridgesAndPrintersByModelAndLocation = cartridgeService.showCartridgesAndPrintersByModelAndLocation();
        Map<LocationDTO, List<PrintersByLocationandModelDto>> printersByLocationAndModel = printerService.getPrintersByLocationAndModel();
        model.addAttribute("input", showCartridgesAndPrintersByModelAndLocation);
        model.addAttribute("input2", outInfo);
        model.addAttribute("input3", printersByLocationAndModel);
        return "inventories";
    }

    @GetMapping("/inventories/{location}")
    public String getInventoriesByLocation(Model model, @PathVariable(name = "location") String location) {
        long parseLong = Long.parseLong(location);
        LocationDTO locationById = locationService.getLocationById(parseLong);
        model.addAttribute("locationInfo", locationById);
        return "inlocation";

    }

    @GetMapping("/inventories/{location}/{device}")
    public String getInventoriesbyLocationAndDevice(Model model,
            @PathVariable(name = "location") String location,
            @PathVariable(name = "device") String device,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "25", required = false) Integer pageSize,
            @RequestParam(required = false) String contractNumber,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "up") String direction) {
        long parseLong = Long.parseLong(location);

        Sort.Direction direct = null;
        if (direction.equals("up")) {
            direct = Sort.Direction.ASC;
        } else {
            direct = Sort.Direction.DESC;
        }
        Pageable paging = PageRequest.of(page, pageSize, JpaSort.unsafe(direct, sortBy));
        if (device.equals("printer")) {
            Page<Printer> getPage = null;
            if (contractNumber != null) {
                getPage = printerRepo.findByLocationIdAndContractContractNumberIgnoreCaseLike(parseLong, contractNumber, paging);
            } else {
                getPage = printerRepo.findByLocationId(parseLong, paging);
            }
            List<PrinterDTO> dtoes = new ArrayList<>();
            for (Printer printer : getPage.getContent()) {
                PrinterDTO printerDto = new PrinterDTO();
                printerDto.setId(printer.getId());
                printerDto.setModel(printer.getModel().getName());
                printerDto.setManufacturer(printer.getManufacturer().getName());
                printerDto.setInventaryNumber(printer.getInventoryNumber());
                printerDto.setSerialNumber(printer.getSerialNumber());
                printerDto.setContractNumber(printer.getContract().getContractNumber());
                printerDto.setLocation(printer.getLocation().getName());
                printerDto.setStartContract(printer.getContract().getDateStartContract());
                printerDto.setEndContract(printer.getContract().getDateStartContract());
                dtoes.add(printerDto);
            }
            model.addAttribute("input", dtoes);
            model.addAttribute("pages", getPage.getTotalPages());
        } else {
            Page<Cartridge> getCartridge = null;
            if (contractNumber != null) {
                getCartridge = cartridgeRepo.findByLocationIdAndContractContractNumberIgnoreCaseLike(parseLong, contractNumber, paging);
            } else {
                getCartridge = cartridgeRepo.findByLocationId(parseLong, paging);
            }

            List<CartridgeDTO> dtoes = new ArrayList<>();
            for (Cartridge cart : getCartridge.getContent()) {
                CartridgeDTO cartDto = new CartridgeDTO();
                cartDto.setContract(cart.getContract().getId());
                cartDto.setContractNumber(cart.getContract().getContractNumber());
                cartDto.setId(cart.getId());
                cartDto.setLocation(cart.getLocation().getName());
                cartDto.setDateEndExploitation(cart.getDateEndExploitation());
                cartDto.setDateStartExploitation(cart.getDateStartExploitation());
                cartDto.setType(cart.getModel().getType().getName());
                cartDto.setResource(cart.getModel().getDefaultNumberPrintPage().toString());
                cartDto.setUtil(cart.isUtil());
                cartDto.setModel(cart.getModel().getModel());
                cartDto.setStartContract(cart.getContract().getDateStartContract());
                dtoes.add(cartDto);
            }
            model.addAttribute("input", dtoes);
            model.addAttribute("pages", getCartridge.getTotalPages());
        }
        LocationDTO locationById = locationService.getLocationById(parseLong);
        model.addAttribute("locationInfo", locationById);
        model.addAttribute("pageable", paging);
        model.addAttribute("direction", direction);
        return "devices";
    }

    @RequestMapping("/help")
    public String getHelp(Model model) {
        return "help";
    }

    @GetMapping(value = "/help/instruction", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] getImage() throws IOException {
        InputStream resourceAsStream = MonitoringApplication.class.getClassLoader().getResourceAsStream("instruction.pdf");
        return IOUtils.toByteArray(resourceAsStream);
    }

    @GetMapping("/planing")
    public String getPlaningResult(Model model, PlaningBuyDto dto) {
        Map<String, List<ConsumptionDTO>> calculatePlaningBuy = planingService.showUtilled(dto);
        Map<String, List<ConsumptionDTO>> showPurchased = planingService.showPurchased(dto);
        Map<String, ConsumptionDTO> amountAllCartridgesByModelAndPeriod = planingService.getAmountAllCartridgesByModelAndPeriod(dto);
        Map<String, Set<CartridgeModelDTO>> amountModelsByCartridgeModel = planingService.getAmountCartridgeModel();
        Map<String, List<ModelCartridgeByModelPrinters>> printersByCartridgesModel = planingService.getPrintersByCartridgesModel();
        model.addAttribute("dateStart", dto.dateBegin);
        model.addAttribute("dateEnd", dto.dateEnd);
        model.addAttribute("utilled", calculatePlaningBuy);
        model.addAttribute("purchased", showPurchased);
        model.addAttribute("amountModels", amountModelsByCartridgeModel);
        model.addAttribute("balance", amountAllCartridgesByModelAndPeriod);
        model.addAttribute("printers", printersByCartridgesModel);
        return "planing";
    }

    @GetMapping("/contract")
    public String getContract(@RequestParam("idContract") Long idContract, Model model) {
        EditContractDTO contract = contractServiceMapper.getContract(idContract);
        model.addAttribute("input", contract);
        return "contract";
    }

    @GetMapping("/printersbylocation")
    public String getPrintersByLocation(Model model,
            @ModelAttribute(name = "dto") DevicesByModelAndLocationDto dto,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "25", required = false) Integer pageSize,
            @RequestParam(defaultValue = "printer_id") String sortBy,
            @RequestParam(defaultValue = "up") String direction) {

        Sort.Direction direct = null;
        if (direction.equals("up")) {
            direct = Sort.Direction.ASC;
        } else {
            direct = Sort.Direction.DESC;
        }

        Pageable paging = PageRequest.of(page, pageSize, JpaSort.unsafe(direct, sortBy));

        LocationDTO locationById = locationService.getLocationById(dto.location);
        List<Printer> printersList = printerMapper.getPrintersByModelId(dto.idModel);
        Page<Printer> getPage = null;
        if (null != dto.getContractNumber()) {

            try {
                getPage = printerRepo.findPrintersByModelAndLocationAndContractNumberFilter(locationById.getId(), printersList.get(0).getModel().getId(), dto.getContractNumber(), paging);

            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } finally {
                getPage = printerRepo.findPrintersByModelPrinterAndLocationAndContractNumberFilter(locationById.getId(), printersList.get(0).getModel().getId(), dto.getContractNumber(), paging);
            }
        } else {
            try {
                getPage = printerRepo.findPrintersByModelAndLocation(locationById.getId(), printersList.get(0).getModel().getModelCartridges().iterator().next().getId(), paging);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } finally {
                getPage = printerRepo.findPrintersByModelPrinterAndLocation(locationById.getId(), printersList.get(0).getModel().getId(), paging);
            }
        }

        List<Printer> content = getPage.getContent();
        List<PrinterDTO> dtoes = new ArrayList<>();
        for (Printer printer : content) {
            PrinterDTO printerDto = new PrinterDTO();
            printerDto.setId(printer.getId());
            printerDto.setModel(printer.getModel().getName());
            printerDto.setManufacturer(printer.getManufacturer().getName());
            printerDto.setInventaryNumber(printer.getInventoryNumber());
            printerDto.setSerialNumber(printer.getSerialNumber());
            printerDto.setContractNumber(printer.getContract().getContractNumber());
            printerDto.setLocation(printer.getLocation().getName());
            printerDto.setStartContract(printer.getContract().getDateStartContract());
            printerDto.setEndContract(printer.getContract().getDateStartContract());
            dtoes.add(printerDto);
        }

        model.addAttribute("input", dtoes);
        model.addAttribute("locationInfo", locationById);
        model.addAttribute("pageable", paging);
        model.addAttribute("pages", getPage.getTotalPages());
        model.addAttribute("direction", direction);
        return "printersbylocation";
    }

    @PostMapping("/cartridges")
    public String sendCartridges(
            @ModelAttribute ContractDTO contract) {

        return "redirect:/cartridges";

    }


    @GetMapping(value = "/addmodelcart")
    public String addModelCartridge(Model model) {
        return "addmodelcartridge";

    }

    @PostMapping("/addmodelcart")
    public ResponseEntity<CartridgeModelDTO> saveModelCartridge(@Valid @ModelAttribute CartridgeModelDTO dto) throws ObjectAlreadyExists {
        CartridgeModel cartridgeModelDtoToCartridgeModel = cartridgeMapper.cartridgeModelDtoToCartridgeModel(dto);
        cartridgeService.saveCartridgeModel(cartridgeModelDtoToCartridgeModel);
        return new ResponseEntity<CartridgeModelDTO>(dto, HttpStatus.OK);

    }

    @GetMapping("/editprinter")
    public String getPrinter(Model model, @RequestParam Long idPrinter) {

        PrinterDTO printerDto = printerMapper.getPrinterById(idPrinter);
        model.addAttribute("dto", printerDto);
        return "edit";
    }

    @GetMapping("/editcartridge")
    public String getCartridge(Model model, @RequestParam Long idCartridge) {

        CartridgeDTO cartridgeDTO = cartridgeMapper.getCartridgeById(idCartridge);
        model.addAttribute("dto", cartridgeDTO);

        return "editCartridge";
    }

    @PostMapping("/editprinterlocation")
    public ResponseEntity<String> changePrinterLocation(ChangeDeviceLocationDTO dto) {

        printerMapper.editPrinterLocation(dto);
        return new ResponseEntity<String>("Локация успешно изменена", HttpStatus.OK);
    }

    @PostMapping("/editcartridgelocation")
    public ResponseEntity<String> changeCartridgeLocation(ChangeDeviceLocationDTO dto) {

        cartridgeService.changeCartridgeLocation(dto);

        return new ResponseEntity<String>("Локация успешно изменена", HttpStatus.OK);
    }

    @PostMapping("/editcartridgeslocation")
    public ResponseEntity<String> changeCartridgeLocation(ChangeLocationForCartridges dto) {

        cartridgeService.changeCartridgesLocation(dto);

        return new ResponseEntity<String>("Локация успешно изменена", HttpStatus.OK);
    }

    @PostMapping("/utilCartridge")
    public ResponseEntity<String> utilCartridge(Long id) {

        cartridgeService.utilCartridge(id);

        return new ResponseEntity<String>("Картридж успешно списан", HttpStatus.OK);
    }

    @PostMapping("/editprinterserial")
    public ResponseEntity<String> changePrinterSerialNumber(ChangePrinterSerialNumberDTO dto) {

        printerMapper.editPrinterSerialNumber(dto);

        return new ResponseEntity<>("Серийный номер успешно изменён", HttpStatus.OK);
    }

    @PostMapping("/editprinterinventary")
    public ResponseEntity<String> changePrinterInventaryNumber(ChangePrinterInventaryNumberDTO dto) {

        printerMapper.editPrinterInventaryNumber(dto);

        return new ResponseEntity<>("Инвентарный номер успешно изменён", HttpStatus.OK);
    }

    @GetMapping("/locaions")
    public String getLocations(Model model) {

        List<LocationDTO> allLocations = locationService.getAllLocations();
        model.addAttribute("dto", allLocations);

        return "locations";
    }

    @PostMapping("/locations")
    public ResponseEntity<String> addLocation(String nameLocation) throws ObjectAlreadyExists {

        locationService.addLocation(nameLocation);

        return new ResponseEntity<>("локация успешно добавлена", HttpStatus.OK);
    }

    @PostMapping("/installcart")
    public ResponseEntity<String> installCartridgeInPrinter(CartridgeInstallDTO dto) {

        cartridgeService.installCartridge(dto);

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @GetMapping("/getcartridgesbymodel")
    public String getCartridgesByModelPrinterAndLocation(Model model,
            @ModelAttribute(name = "dto") DevicesByModelAndLocationDto dto,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "25", required = false) Integer pageSize,
            @RequestParam(defaultValue = "cartridge_id") String sortBy,
            @RequestParam(defaultValue = "up") String direction) {
        Sort.Direction direct = null;
        if (direction.equals("up")) {
            direct = Sort.Direction.ASC;
        } else {
            direct = Sort.Direction.DESC;
        }
        Pageable paging = PageRequest.of(page, pageSize, JpaSort.unsafe(direct, sortBy));
        LocationDTO locationById = locationService.getLocationById(dto.getLocation());
        List<CartridgeDTO> cartDtoesList = new ArrayList<>();
        Page<Cartridge> getPage = null;
        if (null != dto.getContractNumber()) {
            getPage = cartridgeRepo.findByContractNumberAndModelPrinterAndLocation(dto.getContractNumber(), dto.getLocation(), dto.getIdModel(), paging);
        } else {
            getPage = cartridgeRepo.findCartridgesByModelPrinterAndLocation(dto.getLocation(), dto.idModel, paging);
        }
        List<Cartridge> findLikeContractContractNumberByLocationIdAndModelId = getPage.getContent();
        for (Cartridge cart : findLikeContractContractNumberByLocationIdAndModelId) {
            CartridgeDTO cartDto = new CartridgeDTO();
            cartDto.setContract(cart.getContract().getId());
            cartDto.setContractNumber(cart.getContract().getContractNumber());
            cartDto.setId(cart.getId());
            cartDto.setLocation(cart.getLocation().getName());
            cartDto.setDateEndExploitation(cart.getDateEndExploitation());
            cartDto.setDateStartExploitation(cart.getDateStartExploitation());
            cartDto.setType(cart.getModel().getType().getName());
            cartDto.setResource(cart.getModel().getDefaultNumberPrintPage().toString());
            cartDto.setUtil(cart.isUtil());
            cartDto.setModel(cart.getModel().getModel());
            cartDto.setStartContract(cart.getContract().getDateStartContract());
            cartDtoesList.add(cartDto);
        }

        model.addAttribute("input", cartDtoesList);
        model.addAttribute("location", locationById);
        model.addAttribute("pageable", paging);
        model.addAttribute("pages", getPage.getTotalPages());
        model.addAttribute("url", "/getcartridgesbymodel");
        model.addAttribute("direction", direction);
        return "cartridgesbylocation";
    }

    @GetMapping("/contracts")
    public String getAllContracts(Model model) {

        List<ContractForViewDTO> getAllContracts = contractServiceMapper.getAllContracts();
        model.addAttribute("input", getAllContracts);
        return "contracts";
    }

    @PostMapping("changestatus")
    public void changePrinterStatus(PrinterStatusDto dto) {
        printerMapper.editPrinterStatus(dto);
    }
}
