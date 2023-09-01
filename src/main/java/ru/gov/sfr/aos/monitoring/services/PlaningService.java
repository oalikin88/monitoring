/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.OperationType;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.ConsumptionDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.PlaningBuyDto;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.repositories.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.repositories.ListenerOperationRepo;
import ru.gov.sfr.aos.monitoring.repositories.LocationRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class PlaningService {

    @Autowired
    private ListenerOperationRepo listenerOperationRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;

    public List<ConsumptionDTO> calculatePlaningBuy(PlaningBuyDto dto) {
        long amountDaysFromDto = ChronoUnit.DAYS.between(dto.dateBegin, dto.dateEnd);
        List<CartridgeModel> models = cartridgeModelRepo.findAll();
        List<Location> locations = locationRepo.findByNameNot("Склад");
        List<ConsumptionDTO> result = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            for (int j = 0; j < locations.size(); j++) {
                ConsumptionDTO comDto = new ConsumptionDTO();
                CartridgeModelDTO modelDto = new CartridgeModelDTO();
                modelDto.setId(models.get(i).getId());
                modelDto.setResource(models.get(i).getDefaultNumberPrintPage().toString());
                modelDto.setType(models.get(i).getType().getName());
                modelDto.setModel(models.get(i).getModel());
                LocationDTO locationDto = new LocationDTO();
                locationDto.setId(locations.get(j).getId());
                locationDto.setName(locations.get(j).getName());

                List<ListenerOperation> listenersByLocationsAndModel = listenerOperationRepo.findByLocationIdAndModelId(locations.get(j).getId(), models.get(i).getId());
                if (listenersByLocationsAndModel.size() > 0) {
                    List<ListenerOperation> utilled = new ArrayList<>();
                    if (listenersByLocationsAndModel.size() > 0) {
                        List<ListenerOperation> listenersBetweenDay = getListenersBetweenDay(listenersByLocationsAndModel, dto.dateBegin, dto.dateEnd);
                        Collections.sort(listenersBetweenDay, new ListenerOperationComparator());
                        long amountDay = ChronoUnit.DAYS.between(listenersBetweenDay.get(0).getDateOperation(), listenersBetweenDay.get(listenersBetweenDay.size() - 1).getDateOperation());
                        if (amountDay == 0 && listenersBetweenDay.size() > 0) {
                            amountDay = 1;
                        }

                        for (long l = 0L; l < amountDay; l++) {
                            for (ListenerOperation listener : listenersBetweenDay) {
                                if (listener.getOperationType().equals(OperationType.UTIL)) {
                                    utilled.add(listener);
                                }
                            }
                        }

                    }

                    comDto.setConsumption(utilled.size());
                } else {
                    comDto.setConsumption(0);
                }
                comDto.setLocation(locationDto);
                comDto.setModel(modelDto);
                comDto.setPeriod(amountDaysFromDto);
                result.add(comDto);

            }
        }

        return result;
    }

    public static List<ListenerOperation> getListenersBetweenDay(List<ListenerOperation> input, LocalDate start, LocalDate end) {

        List<ListenerOperation> result = new ArrayList<>();

        for (ListenerOperation listener : input) {

            if (!listener.getDateOperation().isBefore(start.atStartOfDay()) && !listener.getDateOperation().isAfter(end.atStartOfDay().plusDays(1))) {
                result.add(listener);
            }
        }

        return result;
    }

}
