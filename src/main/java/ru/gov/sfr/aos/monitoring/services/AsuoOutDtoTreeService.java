/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Asuo;
import ru.gov.sfr.aos.monitoring.mappers.AsuoMapper;
import ru.gov.sfr.aos.monitoring.models.MainSvtDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class AsuoOutDtoTreeService extends SvtObjectBuingOutDtoTreeService<Asuo, AsuoMapper, MainSvtDto>{
    
}
