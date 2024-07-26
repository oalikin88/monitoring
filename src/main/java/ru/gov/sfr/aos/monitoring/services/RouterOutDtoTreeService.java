/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Router;
import ru.gov.sfr.aos.monitoring.mappers.RouterMapper;
import ru.gov.sfr.aos.monitoring.models.SvtSwitchHubDTO;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class RouterOutDtoTreeService extends SvtOutDtoTreeService<Router, RouterMapper, SvtSwitchHubDTO> {
    
}
