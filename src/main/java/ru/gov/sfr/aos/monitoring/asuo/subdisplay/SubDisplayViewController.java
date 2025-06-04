package ru.gov.sfr.aos.monitoring.asuo.subdisplay;

import java.util.List;
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
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class SubDisplayViewController {

    @Autowired
    private SubDisplayModelService subDisplayModelService;
    @Autowired
    private SvtModelMapper svtModelMapper;

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/msubdisplay")
    public String getModelSubDisplay(Model model) {
        List<SubDisplayModel> subDisplayModels = subDisplayModelService.getAllActualModels();
        List<SvtModelDto> getSubDisplayModelsDtoes = svtModelMapper.getModelSubDisplayDtoes(subDisplayModels);
        model.addAttribute("dtoes", getSubDisplayModelsDtoes);
        model.addAttribute("namePage", "Модели электронных табло");
        model.addAttribute("attribute", "msubdisplay");
        return "models";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/msubdisplay")
    public ResponseEntity<String> saveModelSubDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SubDisplayModel subDisplayModel = svtModelMapper.getModelSubDisplay(dto);
        try {
            subDisplayModelService.saveModel(subDisplayModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/msubdisplay")
    public ResponseEntity<String> updateModelSubDisplay(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SubDisplayModel subDisplayModel = svtModelMapper.getModelSubDisplay(dto);
        try {
            subDisplayModelService.update(subDisplayModel);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

//    @SendArchive
    @DeleteMapping("/msubdisplayarchived")
    public ResponseEntity<String> sendModelSubDisplayToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        subDisplayModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
