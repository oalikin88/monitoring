/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.TerminalModel;
import ru.gov.sfr.aos.monitoring.repositories.TerminalModelRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalModelService extends SvtModelService<TerminalModel, TerminalModelRepo> {
    
}
