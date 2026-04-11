package ru.gov.sfr.aos.monitoring.place;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gov.sfr.aos.monitoring.controllers.SvtViewController;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.location.LocationByTreePlaceDto;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class PlaceViewController {

    @Autowired
    private PlaceService placeService;

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/places")
    public String getPlaces(Model model, @RequestParam(value = "username", required = false) String username) {
        List<PlaceDTO> places = null;
        if (null != username) {
            places = placeService.getPlaceByUsername(username);
        } else {
            places = placeService.getPlaces();
        }
        model.addAttribute("dtoes", places);

        return "places";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    // @Log
    @PostMapping("/places")
    public ResponseEntity<String> addPlace(@RequestBody PlaceDTO dto) throws ObjectAlreadyExists {
        placeService.createPlace(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/places")
    public ResponseEntity<String> updatePlace(@RequestBody PlaceDTO dto) throws ObjectAlreadyExists {

        placeService.updatePlace(dto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    // @SendArchive
    @DeleteMapping("/placetoarchive")
    public ResponseEntity<String> sendPlaceToArchived(@RequestBody ArchivedDto dto) {
        placeService.sendPlaceToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/all-places")
    public String getAllPlaces(Model model,
            @RequestParam(value = "username", required = false) String username) {

        List<LocationByTreePlaceDto> placeDtoByAllLocations = null;

        if (null != username) {
            List<Place> placeByUsername = placeService.getPlaceListByUsername(username);
            placeDtoByAllLocations = placeService.getPlaceDtoByAllLocations(placeByUsername);
        } else {
            List<Place> allPlaces = placeService.getAllPlaces();
            placeDtoByAllLocations = placeService.getPlaceDtoByAllLocations(allPlaces);
        }

        List<LocationByTreePlaceDto> out = placeDtoByAllLocations.stream()
                .sorted((o1, o2) -> o1.getLocationName().compareTo(o2.getLocationName()))
                .collect(Collectors.toList());
        int amount = SvtViewController.getAmountDevicesByPlace(placeDtoByAllLocations);

        model.addAttribute("amountDevice", amount);
        model.addAttribute("searchFIO", username);
        model.addAttribute("dtoes", out);
        model.addAttribute("placeAttribute", "all-places");
        model.addAttribute("attribute", "places");
        model.addAttribute("namePage", "Оборудование по сотруднику");

        return "svtobj";
    }
}
