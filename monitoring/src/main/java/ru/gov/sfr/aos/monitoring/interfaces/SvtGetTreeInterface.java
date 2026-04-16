/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import java.util.List;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.FilterRequest;
import ru.gov.sfr.aos.monitoring.place.PlaceType;

/**
 *
 * @author oalikin88
 */

public interface SvtGetTreeInterface {
    
    public List<LocationByTreeDto> getTree(FilterRequest filterRequest,
                                           PlaceType placeType);
}
