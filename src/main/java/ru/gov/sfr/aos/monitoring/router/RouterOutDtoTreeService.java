/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.router;

import org.springframework.stereotype.Service;

import ru.gov.sfr.aos.monitoring.svtobject.SvtOutDtoTreeService;
import ru.gov.sfr.aos.monitoring.switchhub.SvtSwitchHubDTO;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class RouterOutDtoTreeService extends SvtOutDtoTreeService<Router, RouterMapper, SvtSwitchHubDTO> {
    
}
