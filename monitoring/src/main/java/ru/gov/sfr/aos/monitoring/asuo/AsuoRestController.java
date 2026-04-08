package ru.gov.sfr.aos.monitoring.asuo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class AsuoRestController {

    @Autowired
    private AsuoRepo asuoRepo;
    @Autowired
    private AsuoService asuoService;
    @Autowired
    private AsuoMapper asuoMapper;

    @GetMapping("/getasuo")
    public AsuoDTO getAsuoById(Long asuoId) {
        Asuo asuo = asuoService.getById(asuoId);
        AsuoDTO dto = asuoMapper.getDto(asuo);
        return dto;
    }

}
