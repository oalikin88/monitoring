package ru.gov.sfr.aos.monitoring.asuo.subdisplay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class SubDisplayRestController {

    @Autowired
    private SubDisplayModelService subDisplayModelService;
    @Autowired
    private SvtModelMapper svtModelMapper;

    @GetMapping("/modsubdisplay")
    public List<SvtModelDto> getModelSubDisplay() {
        List<SubDisplayModel> allModels = subDisplayModelService.getAllActualModels();
        List<SvtModelDto> dtoes = svtModelMapper.getModelSubDisplayDtoes(allModels);
        return dtoes;
    }
}
