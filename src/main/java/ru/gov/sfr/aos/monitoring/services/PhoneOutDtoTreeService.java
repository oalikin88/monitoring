/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.mappers.PhoneMapper;
import ru.gov.sfr.aos.monitoring.models.SvtDTO;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class PhoneOutDtoTreeService extends SvtOutDtoTreeService <Phone, PhoneMapper, SvtDTO>{
    
}
