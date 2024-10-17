/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinterModel;
import ru.gov.sfr.aos.monitoring.repositories.ThermoprinterModelRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class ThermoprinterModelService  extends SvtModelService<ThermoPrinterModel, ThermoprinterModelRepo> {
    
}