/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.systemblock;

import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerModelRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface SystemBlockManufacturerRepo extends ManufacturerModelRepo<SystemBlockManufacturer> {
    
}
