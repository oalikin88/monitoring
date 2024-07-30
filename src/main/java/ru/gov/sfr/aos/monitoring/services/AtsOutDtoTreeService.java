/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Ats;
import ru.gov.sfr.aos.monitoring.mappers.AtsMapper;
import ru.gov.sfr.aos.monitoring.models.SvtAtsDTO;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class AtsOutDtoTreeService extends SvtOutDtoTreeService<Ats, AtsMapper, SvtAtsDTO>{
    
}
