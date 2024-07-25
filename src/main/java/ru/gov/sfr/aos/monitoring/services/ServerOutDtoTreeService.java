/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Server;
import ru.gov.sfr.aos.monitoring.mappers.ServerMapper;
import ru.gov.sfr.aos.monitoring.models.SvtServerDTO;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class ServerOutDtoTreeService extends SvtOutDtoTreeService<Server, ServerMapper, SvtServerDTO>{
    
}
