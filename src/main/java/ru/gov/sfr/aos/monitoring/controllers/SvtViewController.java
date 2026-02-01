/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.location.LocationByTreePlaceDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author 041AlikinOS
 */
@Controller
public class SvtViewController {
  
    
    @Autowired
    private SvtModelMapper svtModelMapper;
    



    
    
//  @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/svt")
    public String getSvt(Model model) {

        return "svt";
    }

    
    public static int getAmountDevices(List<LocationByTreeDto> listNoStorage, List<LocationByTreeDto> listStorage) {
    int result = 0;
        for(LocationByTreeDto loc : listNoStorage) {
            result = result + loc.getAmount();
        }
        for(LocationByTreeDto loc : listStorage) {
            result = result + loc.getAmount();
        }
        return result;
    }
    
     public static int getAmountDevicesByPlace(List<LocationByTreePlaceDto> list) {
    int result = 0;
        for(LocationByTreePlaceDto loc : list) {
            result = result + loc.getAmount();
        }
    
        return result;
    }
    
}

