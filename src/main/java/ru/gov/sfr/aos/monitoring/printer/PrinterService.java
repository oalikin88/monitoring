package ru.gov.sfr.aos.monitoring.printer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.contract.Contract;
import ru.gov.sfr.aos.monitoring.contract.ContractRepo;
import ru.gov.sfr.aos.monitoring.exceptions.DublicateInventoryNumberException;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.models.FilterDto;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.place.PlaceType;
import ru.gov.sfr.aos.monitoring.services.RegularOperation;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;
import ru.gov.sfr.aos.monitoring.svtobject.SvtObjService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class PrinterService extends SvtObjService<Printer, PrinterRepo, PrinterDto> {

    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private PrinterModelRepo printerModelRepo;
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private PrinterRepo printerRepo;

    @Override
    public void createSvtObj(PrinterDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {

        if (printerRepo.existsBySerialNumberIgnoreCaseAndArchivedFalse(dto.getSerialNumber().trim())) {
            throw new ObjectAlreadyExists("Принтер с таким серийным номером уже есть в базе данных");
        } else if (!dto.isIgnoreCheck() && printerRepo.existsByInventaryNumberIgnoreCaseAndArchivedFalse(dto.getInventaryNumber().trim())) {
            throw new DublicateInventoryNumberException("Принтер с таким инвентарным номером уже есть в базе данных.  \n Вы уверены, что хотите сохранить?");
        } else {
            Printer printer = new Printer();
            Place place = null;
            PrinterModel printerModel = null;
            place = placeRepo.findById(dto.getPlaceId()).get();
            if (null == dto.getModelId()) {
                if (printerModelRepo.existsByModelIgnoreCase("не указано")) {
                    printerModel = printerModelRepo.findByModelIgnoreCase("не указано").get(0);
                } else {
                    printerModel = new PrinterModel("не указано");
                }
            } else {
                printerModel = printerModelRepo.findById(dto.getModelId()).orElseThrow(() -> new NoSuchElementException("Модель принтера не найдена"));
            }

            printer.setPrinterModel(printerModel);
            printer.setStatus(RegularOperation.getStatus(dto.getStatus()));
            printer.setInventaryNumber(dto.getInventaryNumber());
            printer.setSerialNumber(dto.getSerialNumber());
            printer.setYearCreated(dto.getYearCreated());
            printer.setDateExploitationBegin(dto.getDateExploitationBegin());
            printer.setPlace(place);
            printer.setNumberRoom(dto.getNumberRoom());
            printer.setNameFromeOneC(dto.getNameFromOneC());
            Contract contract = null;
            if (contractRepo.existsByContractNumberIgnoreCase("00000000")) {
                contract = contractRepo.findByContractNumberIgnoreCase("00000000").get();
                List<ObjectBuing> objectBuingFromContractDB = contract.getObjectBuing();
                objectBuingFromContractDB.add(printer);
            } else {
                contract = new Contract();
                contract.setDateEndContract(Date.from(Instant.now()));
                contract.setDateStartContract(Date.from(Instant.now()));
                contract.setObjectBuing(new ArrayList<>(Arrays.asList(printer)));
                contract.setContractNumber("00000000");

            }
            printer.setContract(contract);
            printerRepo.save(printer);

        }

    }

    @Override
    public void updateSvtObj(PrinterDto dto) throws ObjectAlreadyExists, DublicateInventoryNumberException {
        Printer existingPrinter = printerRepo.findById(dto.getId()).orElseThrow(() -> new NoSuchElementException("Принтер не найден"));
        Place placeFromDto = placeRepo.findById(dto.getPlaceId()).orElseThrow(() -> new NoSuchElementException("Рабочее место не найдено"));
        PrinterModel printerModel = null;

        if (null == dto.getModelId()) {
            if (printerModelRepo.existsByModelIgnoreCase("не указано")) {
                printerModel = printerModelRepo.findByModelIgnoreCase("не указано").get(0);
            } else {
                printerModel = new PrinterModel("не указано");
            }
        } else {
            printerModel = printerModelRepo.findById(dto.getModelId()).orElseThrow(() -> new NoSuchElementException("Модель не найдена"));
        }

        existingPrinter.setStatus(RegularOperation.getStatus(dto.getStatus()));
        existingPrinter.setInventaryNumber(dto.getInventaryNumber());
        existingPrinter.setSerialNumber(dto.getSerialNumber());
        existingPrinter.setYearCreated(dto.getYearCreated());
        existingPrinter.setDateExploitationBegin(dto.getDateExploitationBegin());
        existingPrinter.setPlace(placeFromDto);
        existingPrinter.setNumberRoom(dto.getNumberRoom());
        existingPrinter.setNameFromeOneC(dto.getNameFromOneC());
        existingPrinter.setPrinterModel(printerModel);
        printerRepo.save(existingPrinter);
    }

    public List<Printer> getDevicesByFilter(FilterDto dto) {
        List<Printer> result = printerRepo.findDevicesByAllFilters(dto.getStatus(), dto.getModel(), dto.getYearCreatedOne(), dto.getYearCreatedTwo(), dto.getLocation());
        return result;
    }

    public Map<Location, List<Printer>> getDevicesByPlaceTypeAndFilter(PlaceType placeType, List<Printer> input) {
        Map<Location, List<Printer>> collect = (Map<Location, List<Printer>>) input
                .stream().filter(e -> e.getPlace().getPlaceType().equals(placeType))
                .collect(Collectors
                        .groupingBy((Printer el) -> el.getPlace()
                        .getLocation()));
        return collect;
    }

}
