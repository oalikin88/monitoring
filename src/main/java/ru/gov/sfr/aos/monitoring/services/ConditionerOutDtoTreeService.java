/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Conditioner;
import ru.gov.sfr.aos.monitoring.mappers.ConditionerMapper;
import ru.gov.sfr.aos.monitoring.models.SvtConditionerDTO;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class ConditionerOutDtoTreeService extends SvtOutDtoTreeService<Conditioner, ConditionerMapper, SvtConditionerDTO> {
    
}
