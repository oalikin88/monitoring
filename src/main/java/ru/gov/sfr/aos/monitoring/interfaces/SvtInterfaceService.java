/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import java.util.List;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.models.LocationByTreeDto;

/**
 *
 * @author 041AlikinOS
 */
public interface SvtInterfaceService {
    
   List<LocationByTreeDto> getByPlace();
   List<LocationByTreeDto> getByPlaceType(PlaceType placeType);
    
}
