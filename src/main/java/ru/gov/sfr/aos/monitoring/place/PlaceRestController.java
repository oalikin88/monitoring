package ru.gov.sfr.aos.monitoring.place;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.department.DepDto;
import ru.gov.sfr.aos.monitoring.department.DepartmentDTO;
import ru.gov.sfr.aos.monitoring.department.DepartmentService;
import ru.gov.sfr.aos.monitoring.location.LocationDTO;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class PlaceRestController {

    @Autowired
    private PlaceService placeService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/locplacetype")
    public List<LocationDTO> getLocByPlaceType(String placeType) {
        PlaceType currentPlaceType = PlaceService.getPlaceType(placeType);
        List<LocationDTO> dtoes = placeService.getLocationByPlaceType(currentPlaceType);
        return dtoes;

    }

    @GetMapping("deplocplacetype")
    public List<DepDto> getDepsByLocAndPlaceType(String placeType, Long idLocation) {
        PlaceType currentPlaceType = null;
        switch (placeType) {
            case "EMPLOYEE":
                currentPlaceType = PlaceType.EMPLOYEE;
                break;
            case "SERVERROOM":
                currentPlaceType = PlaceType.SERVERROOM;
                break;
            case "STORAGE":
                currentPlaceType = PlaceType.STORAGE;
                break;
            case "OFFICEEQUIPMENT":
                currentPlaceType = PlaceType.OFFICEEQUIPMENT;
                break;

        }
        List<DepDto> dtoes = placeService.getDepartmentsByPlaceTypeAndLocation(currentPlaceType, idLocation);
        return dtoes;
    }

    @GetMapping("/place-by-loc-and-placetype")
    public List<PlaceDTO> getPlacesByLocationAndPlaceType(Long locationId, String placeType) {
        PlaceType curentPlaceType = PlaceService.getPlaceType(placeType);
        return placeService.getPlacesByLocationAndPlaceType(locationId, curentPlaceType);
    }

    @GetMapping("/placelocdepplacetype")
    public List<PlaceDTO> getPlacesByPlaceTypeLocationDeps(String placeType, Long idLocation, String departmentCode) {
        PlaceType currentPlaceType = null;
        switch (placeType) {
            case "EMPLOYEE":
                currentPlaceType = PlaceType.EMPLOYEE;
                break;
            case "SERVERROOM":
                currentPlaceType = PlaceType.SERVERROOM;
                break;
            case "STORAGE":
                currentPlaceType = PlaceType.STORAGE;
                break;
            case "OFFICEEQUIPMENT":
                currentPlaceType = PlaceType.OFFICEEQUIPMENT;
                break;

        }
        List<PlaceDTO> dtoes = placeService.getPlaceByPlaceTypeAndLocationAndDepartmentCode(currentPlaceType, idLocation, departmentCode);
        return dtoes;
    }

    @GetMapping("/placesel")
    public List<PlaceDTO> getPlacesEmployee() {
        List<PlaceDTO> places = placeService.getPlacesByPlaceType(PlaceType.EMPLOYEE);
        return places;
    }

    @GetMapping("/placeserver")
    public List<PlaceDTO> getPlacesServer() {
        List<PlaceDTO> places = placeService.getPlacesByPlaceType(PlaceType.SERVERROOM);
        return places;
    }

    @GetMapping("/placebyplacetype")
    public List<PlaceDTO> getPlacesByPlaceType(String placeType) {
        PlaceType curentType = PlaceService.getPlaceType(placeType);
        List<PlaceDTO> places = placeService.getPlacesByPlaceType(curentType);
        return places;
    }

    @GetMapping("/placebyid")
    public PlaceDTO getPlacesById(Long placeId) {
        PlaceDTO place = placeService.getPlaceById(placeId);
        return place;
    }

    @GetMapping("/placebyloc")
    public List<PlaceDTO> getPlacesByLocation(Long locationId) {

        List<PlaceDTO> dtoes = placeService.getPlacesByLocationId(locationId);

        return dtoes;
    }

    @GetMapping("/placebydep")
    public List<PlaceDTO> getPlacesByDepartment(String departmentCode) {

        List<PlaceDTO> dtoes = placeService.getPlacesByDepartmentCode(departmentCode);

        return dtoes;
    }

    @GetMapping("/placebydepandloc")
    public List<PlaceDTO> getplacesByLocationAndDepartments(Long locationId, String departmentCode) {
        List<PlaceDTO> dtoes = null;

        dtoes = placeService.getPlacesByLocationAndDepartment(locationId, departmentCode);

        return dtoes;
    }

    @GetMapping("/placeserverbydepandloc")
    public List<PlaceDTO> getplacesServerByLocationAndDepartments(Long locationId, String departmentCode, PlaceType placetype) {

        List<PlaceDTO> dtoes = placeService.getPlacesByLocationAndDepartmentAndPlaceType(locationId, departmentCode, placetype);
        return dtoes;
    }

    @GetMapping("/getplacesbystatus")
    public List<PlaceStatusDto> getplacesbystatus() {

        List<PlaceStatusDto> placesByStatus = placeService.getPlacesByStatus();

        return placesByStatus;
    }

//    @GetMapping("/depbyplaces")
//    public Set<DepartmentDTO> getDepsByPlaces() {
//
//        Set<DepartmentDTO> departmentsByPlaces = departmentService.getDepartmentsByPlaces();
//        return departmentsByPlaces;
//    }

    @GetMapping("/getstor")
    public PlaceDTO getStorageByPlace(Long locationId) {

        PlaceDTO dto = placeService.getPlaceStorageByLocation(locationId);
        return dto;
    }

    @GetMapping("/getstorage")
    public PlaceDTO getStorageByPlaceNameAndLocation(String username, Long locationId) {

        PlaceDTO dto = placeService.getPlaceStorageByUsernameAndLocation(username, locationId);
        return dto;
    }

    @GetMapping("/depbyloc")
    public Set<DepartmentDTO> getDepartmentsByLocation(Long locationId) {

        Set<DepartmentDTO> dtoes = placeService.getDepartmentsByLocation(locationId);

        return dtoes;
    }

    @GetMapping("/loc")
    public List<LocationDTO> getCurrentLocations() {

        List<LocationDTO> dtoes = placeService.getLocations();

        return dtoes;
    }
}
