/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinter;
import ru.gov.sfr.aos.monitoring.mappers.ThermoprinterMapper;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class ThermoprinterOutDtoTreeService extends SvtOutDtoTreeService<ThermoPrinter, ThermoprinterMapper, SvtDTO> {
    
}
