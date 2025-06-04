/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.ArrayList;
import java.util.List;
import org.opfr.springBootStarterDictionary.fallback.FallbackOrganizationClient;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;
import org.opfr.springBootStarterDictionary.models.DictionaryOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;
import ru.gov.sfr.aos.monitoring.department.DepartmentDTO;
import ru.gov.sfr.aos.monitoring.models.EmployeeDTO;
import ru.gov.sfr.aos.monitoring.location.LocationDTO;
import ru.gov.sfr.aos.monitoring.services.DictionaryEmployeeHolder;
import ru.gov.sfr.aos.monitoring.location.LocationService;
import ru.gov.sfr.aos.monitoring.models.DeviceDto;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingServiceImpl;

/**
 *
 * @author 041AlikinOS
 */
@RestController
public class GetInfoController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private DictionaryEmployeeHolder dictionaryEmployeeHolder;
    @Autowired
    private ObjectBuingServiceImpl objectBuingServiceImpl;

    @GetMapping("/getinfooo")
    public List<EmployeeDTO> getEmpl() {
        List<DictionaryEmployee> employees = dictionaryEmployeeHolder.getEmployees();
        List<EmployeeDTO> list = new ArrayList<>();
        for (DictionaryEmployee dEmployee : employees) {
            EmployeeDTO dto = new EmployeeDTO(dEmployee.getCode(), dEmployee.getSurname() + " " + dEmployee.getName() + " " + dEmployee.getMiddlename());
            list.add(dto);
        }
        return list;
    }

 

    @GetMapping("/locations")
    public List<LocationDTO> getLocations(@RequestParam(value = "id", required = false) String id) {
        List<LocationDTO> locations = null;
        if (null != id) {
            locations = new ArrayList<>();
            long parseLong = Long.parseLong(id);
            LocationDTO locationById = locationService.getLocationById(parseLong);
            locations.add(locationById);
        } else {
            locations = locationService.getAllLocations();
        }
        return locations;
    }
//

    @PostMapping("/AJAXPing")
    public Integer getAllContracts() {
        return 0;
    }

    @GetMapping("/all-devices")
    public List<DeviceDto> getAllDevicesByPlace(Long id) {
        List<DeviceDto> allDevicesByPlaceId = objectBuingServiceImpl.getAllDevicesByPlaceId(id);
        return allDevicesByPlaceId;
    }
}
