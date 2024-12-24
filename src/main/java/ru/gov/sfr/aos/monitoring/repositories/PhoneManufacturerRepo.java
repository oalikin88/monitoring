/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.PhoneManufacturer;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface PhoneManufacturerRepo extends ManufacturerModelRepo<PhoneManufacturer> {
    
}
