/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.phone;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.interfaces.SvtGetTreeInterface;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.FilterRequest;
import ru.gov.sfr.aos.monitoring.place.PlaceType;

/**
 *
 * @author oalikin88
 */
@Service
public class PhoneTreeService implements SvtGetTreeInterface {

    @Autowired
    private PhoneService phoneService;
    @Autowired
    private PhoneOutDtoTreeService phoneOutDtoTreeService;

    @Override
    public List<LocationByTreeDto> getTree(FilterRequest request, PlaceType placeType) {

        Map<Location, List<Phone>> phones;

        if (isSimpleFilter(request)) {
            phones = getSimplePhones(request, placeType);
        } else {
            List<Phone> filtered = phoneService.getPhonesByFilter(request);
            phones = phoneService.getPhonesByPlaceTypeAndFilter(placeType, filtered);
        }

        return phoneOutDtoTreeService.getTreeSvtDtoByPlaceType(phones)
                .stream()
                .sorted(Comparator.comparing(LocationByTreeDto::getLocationName))
                .toList();
    }

    private boolean isSimpleFilter(FilterRequest request) {
        return request.getModel() == null
                && request.getStatus() == null
                && request.getYearCreatedFrom() == null
                && request.getYearCreatedTo() == null
                && request.getInventaryNumber() == null
                && request.getSerialNumber() == null
                && request.getLocation() == null;
    }

    private Map<Location, List<Phone>> getSimplePhones(
            FilterRequest request,
            PlaceType placeType
    ) {
        if (request.getUsername() != null) {
            return phoneService.getSvtObjectsByName(request.getUsername(), placeType);
        }
        if (request.getInventaryNumber() != null) {
            return phoneService.getSvtObjectsByInventaryNumberAndPlace(
                    request.getInventaryNumber(), placeType);
        }
        if (request.getSerialNumber() != null) {
            return phoneService.getSvtObjectsBySerialNumberAndPlace(
                    request.getSerialNumber(), placeType);
        }
        return phoneService.getSvtObjectsByPlaceType(placeType);
    }

}
