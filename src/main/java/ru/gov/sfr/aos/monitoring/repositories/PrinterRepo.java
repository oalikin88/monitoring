/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.PrinterStatus;
import ru.gov.sfr.aos.monitoring.entities.Location;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.entities.Printer;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface PrinterRepo extends JpaRepository<Printer, Long> {
    
    List<Printer> findByModel(String model);
    List<Printer> findByLocationAndModel(Location location, Model model);
    List<Printer> findByLocationName(String name);
    List<Printer> findByLocationIdAndModelId(Long idLocation, Long idModel);
    List<Printer> findByLocationNameAndModelIdAndPrinterStatusEquals(String location, Long idModel, PrinterStatus status);
    
}
