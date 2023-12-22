/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.dao.PrinterAndCartridgeCountByLocationTableDAO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterAndCartridgeCountByLocationTable;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class PrinterAndCartridgeCountByLocationTableService {

    @Autowired
    private PrinterAndCartridgeCountByLocationTableDAO dao;

    public List<PrinterAndCartridgeCountByLocationTable> getAllData() throws SQLException {
        List<PrinterAndCartridgeCountByLocationTable> data = dao.getData();

        Map<Long, Set<Long>> modelsPrinterByModelsCartridge = new HashMap<>();
        List<PrinterAndCartridgeCountByLocationTable> out = new ArrayList<>();
        for (PrinterAndCartridgeCountByLocationTable p : data) {
            if (p.getModelCartridgeId() != 0) {
                if (modelsPrinterByModelsCartridge.containsKey(p.getModelCartridgeId())) {
                    modelsPrinterByModelsCartridge.get(p.getModelCartridgeId()).add(p.getModelId());
                } else {
                    Set<Long> set = new HashSet<>();
                    set.add(p.getModelId());
                    modelsPrinterByModelsCartridge.put(p.getModelCartridgeId(), set);
                }
            }
        }
        Map<LocationDTO, Set<PrinterAndCartridgeCountByLocationTable>> outMap = new HashMap<>();
        Map<Long, List<PrinterAndCartridgeCountByLocationTable>> collect = data.stream().collect(Collectors.groupingBy(PrinterAndCartridgeCountByLocationTable::getModelCartridgeId));
        Set<PrinterAndCartridgeCountByLocationTable> set = new HashSet<>();
        Map<Long, Map<Long, List<PrinterAndCartridgeCountByLocationTable>>> outM = new HashMap<>();
        Map<Long, List<PrinterAndCartridgeCountByLocationTable>> dataGroupByLocation = data.stream().collect(Collectors.groupingBy(PrinterAndCartridgeCountByLocationTable::getLocationId));
        for (Map.Entry<Long, List<PrinterAndCartridgeCountByLocationTable>> entry : dataGroupByLocation.entrySet()) {
            Map<Long, List<PrinterAndCartridgeCountByLocationTable>> dataGroupByModelPrinter = entry.getValue().stream().collect(Collectors.groupingBy(PrinterAndCartridgeCountByLocationTable::getModelId));
            outM.put(entry.getKey(), dataGroupByModelPrinter);
        }

        for (Map.Entry<Long, Map<Long, List<PrinterAndCartridgeCountByLocationTable>>> entry : outM.entrySet()) {
            for (Map.Entry<Long, List<PrinterAndCartridgeCountByLocationTable>> entryInner : entry.getValue().entrySet()) {
                if (entryInner.getValue().size() > 1) {
                    PrinterAndCartridgeCountByLocationTable buf = entryInner.getValue().get(0);
                    int countCartridges = 0;
                    for (PrinterAndCartridgeCountByLocationTable el : entryInner.getValue()) {
                        countCartridges = countCartridges + el.getCountCartridge();
                    }
                    buf.setCountCartridge(countCartridges);
                    entryInner.getValue().clear();
                    entryInner.getValue().add(buf);
                }
            }
        }

        List<PrinterAndCartridgeCountByLocationTable> collectReverse = outM.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Map<Long, List<PrinterAndCartridgeCountByLocationTable>> collectByLocation = collectReverse.stream().collect(Collectors.groupingBy(PrinterAndCartridgeCountByLocationTable::getLocationId));
        Map<Long, Map<Long, List<PrinterAndCartridgeCountByLocationTable>>> outByCartridgeModel = new HashMap<>();
        for (Map.Entry<Long, List<PrinterAndCartridgeCountByLocationTable>> entry : collectByLocation.entrySet()) {
            Map<Long, List<PrinterAndCartridgeCountByLocationTable>> dataGroupByModelCartridge = entry.getValue().stream().collect(Collectors.groupingBy(PrinterAndCartridgeCountByLocationTable::getModelCartridgeId));
            outByCartridgeModel.put(entry.getKey(), dataGroupByModelCartridge);
        }

        for (Map.Entry<Long, Map<Long, List<PrinterAndCartridgeCountByLocationTable>>> entry : outByCartridgeModel.entrySet()) {
            for (Map.Entry<Long, List<PrinterAndCartridgeCountByLocationTable>> entryInner : entry.getValue().entrySet()) {
                if (entryInner.getValue().size() > 1 && entryInner.getKey() != 0) {
                    PrinterAndCartridgeCountByLocationTable buf = entryInner.getValue().get(0);
                    int countPrinters = 0;
                    StringBuilder sb = new StringBuilder();
                    for (PrinterAndCartridgeCountByLocationTable el : entryInner.getValue()) {
                        countPrinters = el.getCountPrinter() + countPrinters;
                        if (sb.length() != 0) {
                            sb.append(" / ");
                            sb.append(el.getModelPrinter());
                        } else {
                            sb.append(el.getModelPrinter());
                        }
                    }
                    buf.setCountPrinter(countPrinters);
                    buf.setModelPrinter(sb.toString());
                    entryInner.getValue().clear();
                    entryInner.getValue().add(buf);
                }
            }
        }
        List<PrinterAndCartridgeCountByLocationTable> collectMapReverse = outByCartridgeModel.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return collectMapReverse;
    }

}
